package org.kuali.ole.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by senthilkumars on 4/19/16.
 */
public class OleDsSolrClientTest extends BaseTestCase {
    Logger logger = org.slf4j.LoggerFactory.getLogger(OleDsSolrClientTest.class);

    private CoreAdminRequest coreAdminRequest;
    private String solrURL;
    private String fileSeparator;
    private String primaryCore;
    private HttpSolrClient httpSolrClient;

    private String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }

    private String getFileSeparator() {
        if (null == fileSeparator) {
            fileSeparator = System.getProperty("file.separator");
        }

        return fileSeparator;
    }

    private SolrClient getSolrClient() {
        if (null == httpSolrClient) {
            httpSolrClient = new HttpSolrClient(getSolrURL());
        }
        logger.info("getting solr client for url --------" + getSolrURL());
        return httpSolrClient;
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

    OleDsSolrClient oleDsSolrClient = new OleDsSolrClient(getSolrURL());

    public String getSolrURL() {
        readSolrProperties();
        return null == solrURL ? "http://localhost:8983/solr" : solrURL;
    }

    private CoreAdminRequest getCoreAdminRequest() {
        if (null == coreAdminRequest) {
            coreAdminRequest = new CoreAdminRequest();
        }
        return coreAdminRequest;
    }

    @Test
    public void indexDocument () {
        String tempCoreName = "temp0";
        List<SolrInputDocument> solrInputDocumentList = new ArrayList<SolrInputDocument>();

        String solrCore = null;
        String solrHome = getSolrHome();
        SolrClient solrClient = getSolrClient();
        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();

        List<File> files = (List<File>) FileUtils.listFiles(new File(solrHome), new String[]{"properties"}, true);

        for (Iterator<File> iterator = files.iterator(); iterator.hasNext(); ) {
            File file = iterator.next();
            if (file.getName().equals("core.properties")) {
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream(file));
                    solrCore = properties.getProperty("name");

                    String fileSeperator = getFileSeparator();
                    File srcCoreDir = new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf");
                    String destCoreDir = tempCoreName + fileSeperator + "conf";
                    FileUtils.copyDirectory(srcCoreDir, new File(solrHome + fileSeperator + destCoreDir));
                    CoreAdminResponse tempSolrCore = coreAdminRequest.createCore(tempCoreName, tempCoreName, solrClient);
                    if (tempSolrCore.getStatus() == 0) {
                        logger.info("Created Solr core with name: " + tempCoreName);
                    } else {
                        logger.error("Error in creating Solr core with name: " + tempCoreName);
                    }
                } catch (IOException e) {
                    logger.error("Failed to create Solr core: " + e.getMessage());
                } catch (SolrServerException e) {
                    logger.error("Failed to create Solr core: " + e.getMessage());
                }
            }
        }

        logger.info("building solr documents");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("oleds_id", "553199");
        document.addField("oleds_name", "Gouda cheese wheel");
        document.addField("oleds_price", "49.99");
        document.addField("id", "1");

        SolrInputDocument document1 = new SolrInputDocument();
        document1.addField("oleds_id", "5");
        document1.addField("oleds_name", "Temp11");
        document1.addField("id", "3");

        SolrInputDocument document2 = new SolrInputDocument();
        document2.addField("oleds_id", "6");
        document2.addField("oleds_name", "Temp21");
        document2.addField("id", "2");

        solrInputDocumentList.add(document);
        solrInputDocumentList.add(document1);
        solrInputDocumentList.add(document2);

        Integer commitCount = 0;
        logger.info("indexing solr documents");
        commitCount = oleDsSolrClient.indexDocument(tempCoreName, solrInputDocumentList);
        logger.info("-------------------commit count is ------------" + commitCount + "----------------");

        try {
            String urlString = "http://localhost:8983/solr/temp0";
            SolrClient solr = new HttpSolrClient(urlString);

            SolrQuery query = new SolrQuery( "id:1" );
            QueryResponse queryResponse = solr.query(query);

            SolrDocumentList list = queryResponse.getResults();
            assertNotNull(list);
            assertTrue(!list.isEmpty());

            SolrQuery query2 = new SolrQuery("id:2");
            QueryResponse queryResponse2 = solr.query(query2);

            SolrDocumentList list2 = queryResponse2.getResults();
            assertNotNull(list2);
            assertTrue(!list2.isEmpty());

            SolrQuery query3 = new SolrQuery("id:3");
            QueryResponse queryResponse3 = solr.query(query3);

            SolrDocumentList list3 = queryResponse3.getResults();
            assertNotNull(list3);
            assertTrue(!list3.isEmpty());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        try {
            CoreAdminResponse caResponse = CoreAdminRequest.unloadCore(tempCoreName, getSolrClient());
        }
        catch (SolrServerException sse){
            logger.info("Exception while unloading cores" + sse);
        }
        catch (IOException ioe){
            logger.info("Exception while unloading cores" + ioe);
        }

        try {
            FileUtils.deleteDirectory(new File(getSolrHome(), tempCoreName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
