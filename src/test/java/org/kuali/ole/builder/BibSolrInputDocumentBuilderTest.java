package org.kuali.ole.builder;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.dao.BibDAO;
import org.kuali.ole.model.OleDsBibT;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pvsubrah on 10/29/15.
 */
public class BibSolrInputDocumentBuilderTest extends BaseTestCase{

    @Autowired
    BibDAO bibDAO;

    @Test
    public void buildSolrInputDocument() {
        List<OleDsBibT> dsBibTs1 = bibDAO.findAllByLimit(0, 1,true, "bibId", "ASC");
        assertNotNull(dsBibTs1);

        BibSolrInputDocumentBuilder bibSolrInputDocumentBuilder = new BibSolrInputDocumentBuilder();
        SolrInputDocument solrInputDocument = bibSolrInputDocumentBuilder.build(dsBibTs1.get(0));
        assertNotNull(solrInputDocument);
    }

}