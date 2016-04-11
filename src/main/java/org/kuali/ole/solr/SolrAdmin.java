package org.kuali.ole.solr;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by pvsubrah on 10/25/15.
 */
public class SolrAdmin {
    Logger logger = org.slf4j.LoggerFactory.getLogger(SolrAdmin.class);
    private CoreAdminRequest coreAdminRequest;
    private HttpSolrClient httpSolrClient;
    private String solrURL;
    private String primaryCore;
    private String fileSeparator;


    public void mergeCores(String sourceCore, List coreNames) {
        String solrHome = getSolrHome();

        List<String> tempCores = new ArrayList();
        List<String> tempCoreNames = new ArrayList();

        for (Iterator<String> iterator = coreNames.iterator(); iterator.hasNext(); ) {
            String coreName = iterator.next();
            tempCores.add(solrHome + getFileSeparator() + coreName + fileSeparator + "data" + fileSeparator + "index");
            tempCoreNames.add(coreName);
        }

        String[] indexDirs = tempCores.toArray(new String[tempCores.size()]);
        String[] tempCoreNamesObjectArray = tempCoreNames.toArray(new String[tempCores.size()]);

        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();
        SolrClient solrClient = getSolrClient();
        try {
            CoreAdminResponse coreAdminResponse =
                    coreAdminRequest.mergeIndexes(sourceCore, indexDirs, tempCoreNamesObjectArray, solrClient);

            SolrClient solrClientForPrimaryCore = getSolrClientForPrimaryCore();
            solrClientForPrimaryCore.commit();

        } catch (SolrServerException e) {
            logger.error("Error in merging Solr cores");
        } catch (IOException e) {
            logger.error("Error in merging Solr cores");
        }

    }

    public void unLoadSolrCores(List coreNames) {
        String fileSeparator = getFileSeparator();
        for (Iterator iterator = coreNames.iterator(); iterator.hasNext(); ) {
            String coreName = (String) iterator.next();
            try {
                getCoreAdminRequest().unloadCore(coreName, getSolrClient());
                FileUtils.deleteDirectory(new File(getSolrHome() + fileSeparator + coreName));
            } catch (SolrServerException e) {
                logger.error("Error in unloading Solr core with name: " + coreName);
            } catch (IOException e) {
                logger.error("Error in unloading Solr core with name: " + coreName);
            }
        }
    }

    public List createSolrCores(List coreNames) {
        List tempCoreCreationResults = new ArrayList();

        String solrCore = null;
        String solrHome = getSolrHome();
        logger.info("Solr Home in use is: " + solrHome);

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

                    for (Iterator fileIterator = coreNames.iterator(); fileIterator.hasNext(); ) {
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

        return tempCoreCreationResults;
    }

    private String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }

    private CoreAdminRequest getCoreAdminRequest() {
        if (null == coreAdminRequest) {
            coreAdminRequest = new CoreAdminRequest();
        }
        return coreAdminRequest;
    }

    private SolrClient getSolrClient() {
        if (null == httpSolrClient) {
            httpSolrClient = new HttpSolrClient(getSolrURL());
        }
        return httpSolrClient;
    }

    public String getSolrURL() {
        readSolrProperties();
        return null == solrURL ? "http://localhost:8983/solr" : solrURL;
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

    private String getFileSeparator() {
        if (null == fileSeparator) {
            fileSeparator = System.getProperty("file.separator");
        }
        return fileSeparator;
    }

    public String getPrimaryCore() {
        return null == primaryCore ? "oleds-primary" : primaryCore;
    }

    public SolrClient getSolrClientForPrimaryCore() {
        return new HttpSolrClient(getSolrURL() + "/" + getPrimaryCore());
    }

    public void deletePreviousIndexes(List<String> tempCores) {
    }
}
