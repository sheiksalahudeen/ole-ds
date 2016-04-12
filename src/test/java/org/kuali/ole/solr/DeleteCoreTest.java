package org.kuali.ole.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by SheikS on 4/12/2016.
 */
public class DeleteCoreTest {
    private String tempCore1 = "temp0";

    private String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }

    private CoreAdminRequest getCoreAdminRequest() {
        return new CoreAdminRequest();
    }

    private SolrClient getSolrClient() {
        String solrURl = "http://localhost:8983/solr";
        return new HttpSolrClient(solrURl);
    }

    @Test
    public void deleteCore() {

        try {
            getCoreAdminRequest().unloadCore(tempCore1, getSolrClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileUtils.deleteDirectory(new File(getSolrHome(),tempCore1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

