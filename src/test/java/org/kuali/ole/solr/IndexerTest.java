package org.kuali.ole.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by pvsubrah on 10/24/15.
 */
public class IndexerTest {

    private String tempCore1 = "temp1";
    private String tempCore2 = "temp2";


    @Before
    public void setUp(){
        try {
            getCoreAdminRequest().unloadCore(tempCore1, getSolrClient());
            getCoreAdminRequest().unloadCore(tempCore2, getSolrClient());


        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileUtils.deleteDirectory(new File(getSolrHome()+tempCore1));
            FileUtils.deleteDirectory(new File(getSolrHome()+tempCore2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleteSolrIndexes() throws Exception {
        String urlString = "http://localhost:8983/solr/oleds_primary";
        SolrClient solr = new HttpSolrClient(urlString);

        solr.deleteByQuery("*:*");
        solr.commit();
    }


    @Test
    public void sampleIndex() throws Exception {

        String urlString = "http://localhost:8983/solr/oleds_primary";
        SolrClient solr = new HttpSolrClient(urlString);

        SolrInputDocument document = new SolrInputDocument();
        document.addField("oleds_id", "552199");
        document.addField("oleds_name", "Gouda cheese wheel");
        document.addField("oleds_price", "49.99");
        document.addField("id", "1");
        UpdateResponse response = solr.add(document);
        UpdateResponse commitResponse = solr.commit();
        assertTrue(commitResponse.getStatus() == 0);


        assertNotNull(response);
        System.out.println(response);


        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");

        QueryResponse queryResponse = solr.query(query);
        SolrDocumentList list = queryResponse.getResults();
        assertNotNull(list);
        assertTrue(!list.isEmpty());
    }


    @Test
    public void createAndUpdateTempCores() throws Exception {
        String solrCore = null;
        String solrHome = getSolrHome();
        System.out.println(solrHome);

        List<File> files = (List<File>) FileUtils.listFiles(new File(solrHome), new String[]{"properties"}, true);
        assertNotNull(files);

        for (Iterator<File> iterator = files.iterator(); iterator.hasNext(); ) {
            File file = iterator.next();
            if (file.getName().equals("core.properties")) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));

                solrCore = properties.getProperty("name");
                System.out.println(solrCore);
            }
        }


        String fileSeperator = System.getProperty("file.separator");
        String tempCoreName1 = "temp1";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName1 + fileSeperator + "conf"));

        String tempCoreName2 = "temp2";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName2 + fileSeperator + "conf"));


        SolrClient solrClient = getSolrClient();

        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();

        CoreAdminResponse tempSolrCore = coreAdminRequest.createCore(tempCoreName1, tempCoreName1, solrClient);
        assertNotNull(tempSolrCore);

        CoreAdminResponse tempSolrCore2 = coreAdminRequest.createCore(tempCoreName2, tempCoreName2, solrClient);
        assertNotNull(tempSolrCore2);



        String temp1URLString = "http://localhost:8983/solr/temp1";
        SolrClient solrTempCore1Client = new HttpSolrClient(temp1URLString);

        SolrInputDocument document1 = new SolrInputDocument();
        document1.addField("oleds_id", "1");
        document1.addField("oleds_name", "Temp11");
        document1.addField("id", "1");
        solrTempCore1Client.add(document1);
        UpdateResponse commitResponse1 = solrTempCore1Client.commit();
        assertTrue(commitResponse1.getStatus() == 0);


        String temp2URLString = "http://localhost:8983/solr/temp2";
        SolrClient solrTempCore2Client = new HttpSolrClient(temp2URLString);

        SolrInputDocument document2 = new SolrInputDocument();
        document2.addField("oleds_id", "2");
        document2.addField("oleds_name", "Temp21");
        document2.addField("id", "2");
        solrTempCore2Client.add(document2);
        UpdateResponse commitResponse2 = solrTempCore2Client.commit();
        assertTrue(commitResponse2.getStatus() == 0);


        mergeIndexes(tempCoreName1, tempCoreName2);

    }


    @Test
    public void mergeTempIndexesIntoMainCore() throws Exception {
        mergeIndexes("temp1", "temp2");
    }

    private String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }

    private CoreAdminRequest getCoreAdminRequest() {
        return new CoreAdminRequest();
    }

    private void mergeIndexes(String... coreNames) throws SolrServerException, IOException {
        String solrHome = getSolrHome();

        List<String> tempCores = new ArrayList();
        List<String> tempCoreNames = new ArrayList();

        for(String coreName:coreNames){
            tempCores.add(solrHome + "/" +coreName+"/data/index");
            tempCoreNames.add(coreName);
        }

        String[] indexDirs = tempCores.toArray(new String[tempCores.size()]);
        String[] tempCoreNamesObjectArray = tempCoreNames.toArray(new String[tempCores.size()]);

        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();
        SolrClient solrClient = getSolrClient();
        CoreAdminResponse coreAdminResponse =
                coreAdminRequest.mergeIndexes("oleds_primary", indexDirs, tempCoreNamesObjectArray, solrClient);

        assertNotNull(coreAdminResponse);



        String temp1URLString = "http://localhost:8983/solr/oleds_primary";
        SolrClient solrTempCore1Client = new HttpSolrClient(temp1URLString);
        solrTempCore1Client.commit();

    }

    private SolrClient getSolrClient() {
        String solrURl = "http://localhost:8983/solr";
        return new HttpSolrClient(solrURl);
    }
}
