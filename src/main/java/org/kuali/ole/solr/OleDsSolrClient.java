package org.kuali.ole.solr;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sheiksalahudeenm on 10/24/15.
 */
public class OleDsSolrClient {

    Logger logger = LoggerFactory.getLogger(OleDsSolrClient.class);

    private String solrCoreURL;

    public OleDsSolrClient(String solrCoreURL) {
        this.solrCoreURL = solrCoreURL;
    }

    public Integer indexDocument(String coreName, List<SolrInputDocument> solrInputDocuments) {
        Integer commitCount = 0;
        if(CollectionUtils.isNotEmpty(solrInputDocuments)) {
            SolrClient solrClient = new HttpSolrClient(getSolrUrl() + "/" + coreName);
            try {
                solrClient.add(solrInputDocuments);
                solrClient.commit();
                commitCount = solrInputDocuments.size();
            } catch (SolrServerException e) {
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return commitCount;
    }

    private List<String> getDeleteList(List<SolrInputDocument> solrInputDocuments) {
        ArrayList<String> idList = new ArrayList<String>();
        for (Iterator<SolrInputDocument> iterator = solrInputDocuments.iterator(); iterator.hasNext(); ) {
            SolrInputDocument solrInputDocument = iterator.next();
            idList.add(String.valueOf(solrInputDocument.get("id")));
        }
        return idList;
    }

    private String getSolrUrl() {
        return solrCoreURL;
    }

    public void setSolrCoreURL(String solrCoreURL) {
        this.solrCoreURL = solrCoreURL;
    }
}
