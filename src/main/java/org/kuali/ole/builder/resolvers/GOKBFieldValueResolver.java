package org.kuali.ole.builder.resolvers;

import org.apache.solr.common.SolrInputDocument;
import org.kuali.ole.constants.OleDsConstants;
import org.kuali.ole.model.OleDsHoldingsT;

/**
 * Created by pvsubrah on 10/30/15.
 */
public class GOKBFieldValueResolver extends OleDsConstants implements HoldingsFieldValueResolver {
    public Boolean isInterested(String fieldName) {
        return OleDsConstants.HoldingsConstants.GOKB_IDENTIFIER.equals(fieldName);
    }

    public void setFieldValue(SolrInputDocument solrInputDocument, OleDsHoldingsT oleDsHoldingsT) {

        if(oleDsHoldingsT != null && oleDsHoldingsT.getGokbIdentifier() != null) {
            solrInputDocument.addField(OLE_DS_SOLR_PREFIX + OleDsConstants.HoldingsConstants.GOKB_IDENTIFIER, oleDsHoldingsT.getGokbIdentifier());
        }
    }
}
