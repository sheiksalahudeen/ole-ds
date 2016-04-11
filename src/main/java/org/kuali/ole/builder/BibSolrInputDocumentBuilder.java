package org.kuali.ole.builder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrInputDocument;
import org.kuali.ole.bo.SearchConfigurationDocFieldBo;
import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.model.OleDsBibT;
import org.kuali.ole.model.OleDsDocFieldT;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * Created by pvsubrah on 10/29/15.
 */
public class BibSolrInputDocumentBuilder {
    Logger logger = LoggerFactory.getLogger(BibSolrInputDocumentBuilder.class);

    private SearchConfigurationDocFieldBo searchConfigurationDocFieldBo;

    public SolrInputDocument build(OleDsBibT oleDsBibT) {
        SolrInputDocument solrInputDocument = new SolrInputDocument();

        buildBibFIelds(oleDsBibT, solrInputDocument);
        buildMarcFields(oleDsBibT, solrInputDocument);

        return solrInputDocument;

    }

    private void buildBibFIelds(OleDsBibT oleDsBibT, SolrInputDocument document) {
        document.addField("oleds_unique_id_prefix", oleDsBibT.getUniqueIdPrefix());
        document.addField("oleds_date_updated", oleDsBibT.getDateUpdated());
        document.addField("oleds_fast_add", oleDsBibT.getFastAdd());
        document.addField("oleds_status", oleDsBibT.getStatus());
        document.addField("oleds_staff_only", oleDsBibT.getStaffOnly());
        document.addField("oleds_created_by", oleDsBibT.getCreatedBy());
        document.addField("oleds_bib_id", oleDsBibT.getBibId());
        document.addField("oleds_updated_by", oleDsBibT.getUpdatedBy());
        document.addField("oleds_status_updated_by", oleDsBibT.getStatusUpdatedBy());
        document.addField("oleds_date_created", oleDsBibT.getDateCreated());
        document.addField("oleds_former_id", oleDsBibT.getFormerId());
        document.addField("oleds_status_updated_date", oleDsBibT.getStatusUpdatedDate());
        document.addField("id", oleDsBibT.getBibId());
    }

    private void buildMarcFields(OleDsBibT oleDsBibT, SolrInputDocument document) {
        Record record = null;
        try {
            MarcReader marcReader = new MarcXmlReader(IOUtils.toInputStream(oleDsBibT.getContent()));
            if (marcReader.hasNext()) {
                record = marcReader.next();

                SearchConfigurationDocFieldBo searchConfigurationDocFieldBo =
                        getSearchConfigurationDocFieldBo();

                List searchFieldConfigurations = searchConfigurationDocFieldBo.getCachedBibSearchFieldConfigurations();

                for (Iterator searchFieldConfigurationIterator = searchFieldConfigurations.iterator(); searchFieldConfigurationIterator.hasNext(); ) {
                    OleDsDocFieldT oleDsDocFieldT = (OleDsDocFieldT) searchFieldConfigurationIterator.next();
                    String fieldName = oleDsDocFieldT.getName();
                    String subFieldsToConsider = oleDsDocFieldT.getIncludePath();
                    if (StringUtils.isNotBlank(subFieldsToConsider)) {
                        String indexedValues = null;
                        try {
                            indexedValues = getSearchIndexBuilder().buildIndexedValues(record, subFieldsToConsider, null);
                        } catch (Exception e) {
//                            logger.error("Indexing failed for :" + fieldName + " for the include path: " + subFieldsToConsider);
//                            logger.error(e.getMessage());
                        }
                        document.addField("oleds_" + fieldName, indexedValues);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing bib content for bib with id: " + oleDsBibT.getBibId());
        }
    }

    private SearchConfigurationDocFieldBo getSearchConfigurationDocFieldBo() {
        ApplicationContextProvider applicationContextProvider = ApplicationContextProvider.getInstance();
        org.springframework.context.ApplicationContext instance = applicationContextProvider.getApplicationContext();

        if (null == searchConfigurationDocFieldBo) {
            searchConfigurationDocFieldBo = (SearchConfigurationDocFieldBo) instance.getBean("searchConfigurationDocFieldBo");
        }
        return searchConfigurationDocFieldBo;
    }


    private BibSearchIndexBuilder getSearchIndexBuilder() {
        return new BibSearchIndexBuilder();
    }

}
