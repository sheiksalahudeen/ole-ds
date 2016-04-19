package org.kuali.ole.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.common.util.NamedList;
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

    public String getPrimaryCore() {
        return null == primaryCore ? "oleds_primary" : primaryCore;
    }

    public String getSolrURL() {
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
        String sourceCore = "oleds_primary";

        // populate temp cores with sample data

        // solrAdmin.mergeCores(sourceCore, List<tempCores>);

        // test and verify that the sourceCore has the documents that were in the temp cores

        // delete the temp core

        // delete the tmp dir
    }
}
