package org.kuali.ole.builder.resolvers;

import org.apache.solr.common.SolrInputDocument;
import org.kuali.ole.model.OleDsHoldingsT;

/**
 * Created by pvsubrah on 10/30/15.
 */
public interface HoldingsFieldValueResolver {
    Boolean isInterested(String fieldName);

    void setFieldValue(SolrInputDocument solrInputDocument, OleDsHoldingsT oleDsHoldingsT);
}
