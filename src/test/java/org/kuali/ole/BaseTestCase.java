package org.kuali.ole;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.junit.runner.RunWith;
import org.kuali.ole.solr.SolrAdmin;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by pvsubrah on 10/23/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/beanlocations.xml" })
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class BaseTestCase {
    Logger logger = org.slf4j.LoggerFactory.getLogger(BaseTestCase.class);

    private CoreAdminRequest coreAdminRequest;
    private HttpSolrClient httpSolrClient;
    private String fileSeparator;
    private String solrURL;
    private String primaryCore;

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
        if (null == coreAdminRequest) {
            coreAdminRequest = new CoreAdminRequest();
        }

        return coreAdminRequest;
    }

    public SolrClient getSolrClient() {
        if (null == httpSolrClient) {
            httpSolrClient = new HttpSolrClient(getSolrURL());
        }
        logger.info("getting solr client for url --------" + getSolrURL());
        return httpSolrClient;
    }

    public String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }

    public String getSolrURL() {
        if (null == solrURL) {
            solrURL = new SolrAdmin().getSolrURL();
        }
        return solrURL;
    }

    private void readSolrProperties() {
        Properties properties = new Properties();
        File file = new File(System.getProperty("user.home") + getFileSeparator() +
                "kuali" + getFileSeparator() +
                "main" + getFileSeparator() +
                "local" + getFileSeparator() +
                "common-config.xml");
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
                solrURL = properties.getProperty("solr.url");
                primaryCore = properties.getProperty("solr.primary.core");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileSeparator() {
        if (null == fileSeparator) {
            fileSeparator = System.getProperty("file.separator");
        }

        return fileSeparator;
    }
}
