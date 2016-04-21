package org.kuali.ole.solr;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.rules.ExpectedException;
import org.kuali.ole.BaseTestCase;
import org.slf4j.Logger;
import org.springframework.context.annotation.DependsOn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


/**
 * Created by senthilkumars on 4/18/16.
 */
public class SolrAdminTest extends BaseTestCase {
    Logger logger = org.slf4j.LoggerFactory.getLogger(SolrAdminTest.class);

    SolrAdmin solrAdmin = new SolrAdmin();
    private String fileSeparator;
    private String solrURL;
    private String primaryCore;


    private String getSolrHome() {
        return System.getenv("SOLR_HOME");
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

    private String getPrimaryCore() {
        return null == primaryCore ? "oleds_primary" : primaryCore;
    }
    OleDsSolrClient oleDsSolrClient = new OleDsSolrClient(getSolrURL());

    private String getSolrURL() {
        readSolrProperties();
        return null == solrURL ? "http://localhost:8983/solr" : solrURL;
    }

    private CoreAdminRequest getCoreAdminRequest() {
        return new CoreAdminRequest();
    }

    private SolrClient getSolrClient() {
        String solrURl = "http://localhost:8983/solr";
        return new HttpSolrClient(solrURl);
    }

    private String getFileSeparator() {
        if (null == fileSeparator) {
            fileSeparator = System.getProperty("file.separator");
        }

        return fileSeparator;
    }


    private void unloadCore(String coreName) throws SolrServerException, IOException {
        try {
            getCoreAdminRequest().unloadCore(coreName, getSolrClient());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unloadCores(List<String> coreNames) {
        try {
            for(String coreName : coreNames) {
                unloadCore(coreName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for(String coreName : coreNames) {
                FileUtils.deleteDirectory(new File(getSolrHome()+ File.separator + coreName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer indexDocument(String coreName, List<SolrInputDocument> solrInputDocuments) {
        Integer commitCount = 0;
        if(CollectionUtils.isNotEmpty(solrInputDocuments)) {
            SolrClient solrClient = new HttpSolrClient(getSolrURL() + "/" + coreName);
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


    private void deleteSolrIndexes(List<Integer> idsToDelete) {
        String urlString = "http://localhost:8983/solr/oleds_primary";
        try {
            SolrClient solr = new HttpSolrClient(urlString);
            for(Integer id : idsToDelete) {
                solr.deleteByQuery("id:" + id);
                solr.commit();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createSolrCores() {
        List<String> tempCores = new ArrayList<String>();
        String tempCore0 = "tempCore0";
        String tempCore1 = "tempCore1";
        String tempCore2 = "tempCore2";
        String tempCore3 = "tempCore3";

        tempCores.add(tempCore0);
        tempCores.add(tempCore1);
        tempCores.add(tempCore2);

        unloadCores(tempCores);

        solrAdmin.createSolrCores(tempCores);

        try {
            for(String createdCore : tempCores) {
                CoreAdminResponse caResponse = CoreAdminRequest.unloadCore(createdCore, getSolrClient());
            }
        }
        catch (SolrServerException sse){
            logger.info("Exception while unloading cores" + sse);
        }
        catch (IOException ioe){
            logger.info("Exception while unloading cores" + ioe);
        }

        try {
            for(String tempCore : tempCores) {
                FileUtils.deleteDirectory(new File(getSolrHome(), tempCore));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unloadCoresAndDeleteTempDirs() throws SolrServerException, IOException {
        List<String> tempCores = new ArrayList<String>();
        String tempCore0 = "tempCore0";
        String tempCore1 = "tempCore1";
        String tempCore2 = "tempCore2";
        String tempCore3 = "tempCore3";

        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();
        SolrClient solrClient = getSolrClient();

        tempCores.add(tempCore0);
        tempCores.add(tempCore1);
        tempCores.add(tempCore2);

        unloadCores(tempCores);

        List tempCoreCreationResults = new ArrayList();

        String solrCore = null;
        String solrHome = getSolrHome();

        List<File> files = (List<File>) FileUtils.listFiles(new File(solrHome), new String[]{"properties"}, true);

        for (Iterator<File> iterator = files.iterator(); iterator.hasNext(); ) {
            File file = iterator.next();
            if (file.getName().equals("core.properties")) {
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream(file));
                    solrCore = properties.getProperty("name");

                    for (Iterator fileIterator = tempCores.iterator(); fileIterator.hasNext(); ) {
                        String tempCoreName = (String) fileIterator.next();
                        String fileSeperator = getFileSeparator();
                        File srcCoreDir = new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf");
                        String destCoreDir = tempCoreName + fileSeperator + "conf";
                        FileUtils.copyDirectory(srcCoreDir, new File(solrHome + fileSeperator + destCoreDir));
                        CoreAdminResponse tempSolrCore = coreAdminRequest.createCore(tempCoreName, tempCoreName, solrClient);
                        if (tempSolrCore.getStatus() == 0) {
                            tempCoreCreationResults.add(tempCoreName);
                            logger.info("Created Solr core with name: " + tempCoreName);
                        } else {
                            logger.error("Error in creating Solr core with name: " + tempCoreName);
                        }
                    }

                } catch (IOException e) {
                    logger.error("Failed to create Solr core: " + e.getMessage());
                } catch (SolrServerException e) {
                    logger.error("Failed to create Solr core: " + e.getMessage());
                }
            }
        }

        solrAdmin.unloadCoresAndDeleteTempDirs(tempCores);

        for(String tempCore : tempCores) {
            NamedList<Object> namedListOfTempCore = (CoreAdminRequest.getStatus(tempCore, getSolrClient())).getCoreStatus(tempCore);
            assertNull(namedListOfTempCore.get("name"));
        }

        for(String tempCore : tempCores) {
            assertFalse((new File(getSolrHome(), tempCore)).isDirectory());
        }
    }

    @Test
    public void mergeCores() {
        //SolrClient solrClientForPrimaryCore = new HttpSolrClient(getSolrURL() + "/" + getPrimaryCore());
        List tempCoreCreationResults = new ArrayList();
        List<String> tempCores1 = new ArrayList<String>();
        //List<String> tempCores2 = new ArrayList<String>();
        //List<String> allTempCores = new ArrayList<String>();

        String primaryCore = "oleds_primary";
        String tempCore0 = "tempCore0";
        String tempCore1 = "tempCore1";
        //String tempCore2 = "tempCore2";
        //String tempCore3 = "tempCore3";

        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();
        SolrClient solrClient = getSolrClient();
        String solrCore = null;
        String solrHome = getSolrHome();

        tempCores1.add(tempCore0);
        //allTempCores.add(tempCore0);
        tempCores1.add(tempCore1);
        //allTempCores.add(tempCore1);

        //unloadCores(allTempCores);
        unloadCores(tempCores1);

        List<SolrInputDocument> solrInputDocumentList = new ArrayList<SolrInputDocument>();
        List<SolrInputDocument> solrInputDocumentList2 = new ArrayList<SolrInputDocument>();

        List<File> files = (List<File>) FileUtils.listFiles(new File(solrHome), new String[]{"properties"}, true);

        for (Iterator<File> iterator = files.iterator(); iterator.hasNext(); ) {
            File file = iterator.next();
            if (file.getName().equals("core.properties")) {
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream(file));
                    solrCore = properties.getProperty("name");

                    for (Iterator fileIterator = tempCores1.iterator(); fileIterator.hasNext(); ) {
                        String tempCoreName = (String) fileIterator.next();
                        String fileSeperator = getFileSeparator();
                        File srcCoreDir = new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf");
                        String destCoreDir = tempCoreName + fileSeperator + "conf";
                        FileUtils.copyDirectory(srcCoreDir, new File(solrHome + fileSeperator + destCoreDir));
                        CoreAdminResponse tempSolrCore = coreAdminRequest.createCore(tempCoreName, tempCoreName, solrClient);
                        if (tempSolrCore.getStatus() == 0) {
                            tempCoreCreationResults.add(tempCoreName);
                            logger.info("Created Solr core with name: " + tempCoreName);
                        } else {
                            logger.error("Error in creating Solr core with name: " + tempCoreName);
                        }
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

        SolrInputDocument document3 = new SolrInputDocument();
        document3.addField("oleds_id", "7");
        document3.addField("oleds_name", "Temp31");
        document3.addField("id", "4");

        solrInputDocumentList.add(document);
        solrInputDocumentList.add(document1);

        solrInputDocumentList2.add(document2);
        solrInputDocumentList2.add(document3);

        // populate temp cores with sample data
        Integer commitCount = 0;
        Integer commitCount2 = 0;
        logger.info("indexing solr documents");
        commitCount = indexDocument(tempCore0, solrInputDocumentList);
        commitCount2 = indexDocument(tempCore1, solrInputDocumentList2);
        logger.info("-------------------commit count is ------------" + commitCount + "----------------");
        logger.info("-------------------commit count2 is ------------" + commitCount2 + "----------------");

        // solrAdmin.mergeCores(sourceCore, List<tempCores>);
        solrAdmin.mergeCores(primaryCore, tempCores1);

        // test and verify that the sourceCore has the documents that were in the temp cores
        try {
            String urlString = "http://localhost:8983/solr/oleds_primary";
            SolrClient solr = new HttpSolrClient(urlString);

            SolrQuery query = new SolrQuery( "id:1" );
            QueryResponse queryResponse = solr.query(query);

            SolrDocumentList list = queryResponse.getResults();
            Assert.assertNotNull(list);
            Assert.assertTrue(!list.isEmpty());
            logger.info("found bib with id 1 in primary core");

            SolrQuery query2 = new SolrQuery("id:2");
            QueryResponse queryResponse2 = solr.query(query2);

            SolrDocumentList list2 = queryResponse2.getResults();
            Assert.assertNotNull(list2);
            Assert.assertTrue(!list2.isEmpty());
            logger.info("found bib with id 2 in primary core");

            SolrQuery query3 = new SolrQuery("id:3");
            QueryResponse queryResponse3 = solr.query(query3);

            SolrDocumentList list3 = queryResponse3.getResults();
            Assert.assertNotNull(list3);
            Assert.assertTrue(!list3.isEmpty());
            logger.info("found bib with id 3 in primary core");

            SolrQuery query4 = new SolrQuery("id:4");
            QueryResponse queryResponse4 = solr.query(query4);

            SolrDocumentList list4 = queryResponse4.getResults();
            Assert.assertNotNull(list4);
            Assert.assertTrue(!list4.isEmpty());
            logger.info("found bib with id 4 in primary core");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // delete the temp core
        // delete the tmp dir
        unloadCores(tempCores1);

        //delete the indexes from primary core
        deleteSolrIndexes(new ArrayList<Integer> (Arrays.asList(1,2,3,4)));
    }
}
