package org.kuali.ole.executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.kuali.ole.bo.SearchConfigurationDocFieldBo;
import org.kuali.ole.builder.BibSearchIndexBuilder;
import org.kuali.ole.builder.BibSolrInputDocumentBuilder;
import org.kuali.ole.builder.HoldingsSolrInputDocumentBuilder;
import org.kuali.ole.builder.ItemsSolrInputDocumentBuilder;
import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.dao.BibDAO;
import org.kuali.ole.model.OleDsBibT;
import org.kuali.ole.model.OleDsDocFieldT;
import org.kuali.ole.model.OleDsHoldingsT;
import org.kuali.ole.model.OleDsItemT;
import org.kuali.ole.solr.OleDsSolrClient;
import org.kuali.ole.solr.SolrAdmin;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created by sheiksalahudeenm on 10/24/15.
 */

@Component
@Scope("prototype")
public class BibIndexerExecutor implements Callable {
    Logger logger = LoggerFactory.getLogger(BibIndexerExecutor.class);
    private String coreName;
    private Integer startIndex;
    private Integer batchSize;
    @Autowired
    private BibDAO bibDAO;
    private String solrURL;

    public BibIndexerExecutor() {

    }

    public BibDAO getBibDAO() {
        return bibDAO;
    }

    public BibIndexerExecutor(Integer startIndex, Integer batchSize, String coreName, BibDAO bibDAO) {
        this.coreName = coreName;
        this.startIndex = startIndex;
        this.batchSize = batchSize;
//        this.bibDAO = bibDAO;
    }

    public Object call() {
        Integer resultCount;
        long startTime = System.currentTimeMillis();
        OleDsSolrClient oleDsSolrClient = new OleDsSolrClient(getSolrURL());
        List<SolrInputDocument> solrInputDocuments = buildSolrInputDocuments();
        resultCount = oleDsSolrClient.indexDocument(coreName, solrInputDocuments);
        long endTime = System.currentTimeMillis();

        if (resultCount == solrInputDocuments.size()) {
            logger.info("Time taken to update index " + solrInputDocuments.size() + " of document to solr : " + (endTime - startTime));
        } else {
            logger.info("Error occurred during indexing");
        }

        return resultCount;
    }

    private List<SolrInputDocument> buildSolrInputDocuments() {
        ArrayList<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();


        List bibDAOAllByLimit = bibDAO.findAllByLimit(startIndex, batchSize, true, "bibId", "ASC");

        if (CollectionUtils.isNotEmpty(bibDAOAllByLimit)) {
            for (Iterator<OleDsBibT> iterator = bibDAOAllByLimit.iterator(); iterator.hasNext(); ) {
                OleDsBibT oleDsBibT = iterator.next();

                SolrInputDocument bibSolrInputDocument = new BibSolrInputDocumentBuilder().build(oleDsBibT);
                solrInputDocuments.add(bibSolrInputDocument);
                List<SolrInputDocument> holdingsSolrInputDocumentsForBib = buildHoldingsInformation(oleDsBibT);
                if (CollectionUtils.isNotEmpty(holdingsSolrInputDocumentsForBib)) {
                    solrInputDocuments.addAll(holdingsSolrInputDocumentsForBib);
                }
            }
        }
        return solrInputDocuments;
    }

    private List<SolrInputDocument> buildHoldingsInformation(OleDsBibT oleDsBibT) {
        List<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();
        for (Iterator<OleDsHoldingsT> iterator =  oleDsBibT.getOleDsHoldingsTs().iterator(); iterator.hasNext(); ) {
            OleDsHoldingsT holdingsT = iterator.next();
            List<SolrInputDocument> holdingsDocument = new HoldingsSolrInputDocumentBuilder().build(holdingsT);
            if (CollectionUtils.isNotEmpty(holdingsDocument)) {
                solrInputDocuments.addAll(holdingsDocument);
            }
            List<SolrInputDocument> itemInformation = buildItemsInformation(holdingsT);
            if (CollectionUtils.isNotEmpty(itemInformation)) {
                solrInputDocuments.addAll(itemInformation);
            }
        }
        return solrInputDocuments;
    }

    private List<SolrInputDocument> buildItemsInformation(OleDsHoldingsT holdingsT) {
        List<SolrInputDocument> solrInputDocuments = new ArrayList<SolrInputDocument>();
        for (Iterator<OleDsItemT> iterator = holdingsT.getOleDsItemTs().iterator(); iterator.hasNext(); ) {
            OleDsItemT oleDsItemT = iterator.next();
            List<SolrInputDocument> itemDocument = new ItemsSolrInputDocumentBuilder().build(oleDsItemT);
            solrInputDocuments.addAll(itemDocument);
        }
        return solrInputDocuments;
    }

    public String getSolrURL() {
        if (null == solrURL) {
            solrURL = new SolrAdmin().getSolrURL();
        }
        return solrURL;
    }

    public void setSolrURL(String solrURL) {
        this.solrURL = solrURL;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
}
