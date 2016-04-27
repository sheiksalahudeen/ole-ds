package org.kuali.ole;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by pvsubrah on 10/23/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/beanlocations.xml" })
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class BaseTestCase {
/*
    public void unloadCore(String coreName) throws SolrServerException, IOException {
        try {
            getCoreAdminRequest().unloadCore(coreName, getSolrClient());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unloadCores(List<String> coreNames) {
        try {
            for(String coreName : coreNames) {
                unloadCore(coreName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for(String coreName : coreNames) {
                FileUtils.deleteDirectory(new File(getSolrHome() + File.separator + coreName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CoreAdminRequest getCoreAdminRequest() {
        return new CoreAdminRequest();
    }

    public SolrClient getSolrClient() {
        String solrURl = "http://localhost:8983/solr";
        return new HttpSolrClient(solrURl);
    }

    public String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }*/
}
