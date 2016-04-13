package org.kuali.ole.executors;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.dao.BibDAO;
import org.kuali.ole.solr.SolrAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pvsubrah on 10/25/15.
 */
public class BibIndexerExecutorTest extends BaseTestCase {

    @Autowired
    BibDAO bibDAO;

    @Autowired
    SolrIndexer solrIndexer;



    private String tempCore0 = "temp0";
    private String tempCore1 = "temp1";
    private String tempCore2 = "temp2";
    private String tempCore3 = "temp3";
    private String tempCore4 = "temp4";
    private String tempCore5 = "temp5";


    public void unloadOldCoresAndCreateNewCores(){
        unloadCores();
        try {
            createAndUpdateTempCores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unloadCores() {
        try {
            unloadCore(tempCore0);
            unloadCore(tempCore1);
            unloadCore(tempCore2);
            unloadCore(tempCore3);
            unloadCore(tempCore4);
            unloadCore(tempCore5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileUtils.deleteDirectory(new File(getSolrHome()+ File.separator + tempCore0));
            FileUtils.deleteDirectory(new File(getSolrHome()+ File.separator + tempCore1));
            FileUtils.deleteDirectory(new File(getSolrHome()+File.separator + tempCore2));
            FileUtils.deleteDirectory(new File(getSolrHome()+File.separator + tempCore3));
            FileUtils.deleteDirectory(new File(getSolrHome()+File.separator + tempCore4));
            FileUtils.deleteDirectory(new File(getSolrHome()+File.separator + tempCore5));
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    private CoreAdminRequest getCoreAdminRequest() {
        return new CoreAdminRequest();
    }

    private String getSolrHome() {
        return System.getenv("SOLR_HOME");
    }

    private SolrClient getSolrClient() {
        String solrURl = "http://localhost:8983/solr";
        return new HttpSolrClient(solrURl);
    }

    @Test
    public void deleteSolrIndexes() throws Exception {
        String urlString = "http://localhost:8983/solr/oleds_primary";
        SolrClient solr = new HttpSolrClient(urlString);

        solr.deleteByQuery("*:*");
        solr.commit();
    }



    @Test
    public void indexBibs() {
        unloadOldCoresAndCreateNewCores();
        Map<Future, Boolean> futresMap = new HashMap();
        String coreName1 = "temp1";
        String coreName2 = "temp2";
        String coreName3 = "temp3";
        String coreName4 = "temp4";
        SolrAdmin solrAdmin = new SolrAdmin();
        List<String> cores = new ArrayList<String>();
        cores.add(coreName1);
        cores.add(coreName2);
        cores.add(coreName3);
        cores.add(coreName4);

        List<Future> futures = new ArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        BibIndexerExecutor bibIndexerExecutor1 =
                (BibIndexerExecutor) ApplicationContextProvider.getInstance().getApplicationContext().getBean("bibIndexerExecutor");
        bibIndexerExecutor1.setBatchSize(1000);
        bibIndexerExecutor1.setCoreName(coreName1);
        bibIndexerExecutor1.setStartIndex(0);
        Future future1 = executorService.submit(bibIndexerExecutor1);
        futures.add(future1);
        futresMap.put(future1, Boolean.FALSE);

        BibIndexerExecutor bibIndexerExecutor2 =
                (BibIndexerExecutor) ApplicationContextProvider.getInstance().getApplicationContext().getBean("bibIndexerExecutor");
        bibIndexerExecutor2.setBatchSize(1000);
        bibIndexerExecutor2.setCoreName(coreName2);
        bibIndexerExecutor2.setStartIndex(1000);
        Future future2 = executorService.submit(bibIndexerExecutor2);
        futures.add(future2);
        futresMap.put(future2, Boolean.FALSE);

        BibIndexerExecutor bibIndexerExecutor3 =
                (BibIndexerExecutor) ApplicationContextProvider.getInstance().getApplicationContext().getBean("bibIndexerExecutor");
        bibIndexerExecutor3.setBatchSize(1000);
        bibIndexerExecutor3.setCoreName(coreName3);
        bibIndexerExecutor3.setStartIndex(2000);
        Future future3 = executorService.submit(bibIndexerExecutor3);
        futures.add(future3);
        futresMap.put(future3, Boolean.FALSE);

        BibIndexerExecutor bibIndexerExecutor4 =
                (BibIndexerExecutor) ApplicationContextProvider.getInstance().getApplicationContext().getBean("bibIndexerExecutor");
        bibIndexerExecutor4.setBatchSize(1000);
        bibIndexerExecutor4.setCoreName(coreName4);
        bibIndexerExecutor4.setStartIndex(3000);
        Future future4 = executorService.submit(bibIndexerExecutor4);
        futures.add(future4);
        futresMap.put(future4, Boolean.FALSE);



        for (Iterator<Future> iterator = futures.iterator(); iterator.hasNext(); ) {
            Future future = iterator.next();
            while (!futresMap.get(future)) {
                try {
                    Integer resultCount = (Integer) future.get();
                    futresMap.put(future, Boolean.TRUE);
                    System.out.println("Num docs indexed: " + resultCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        long startTime = System.currentTimeMillis();

        executorService.shutdown();


        solrAdmin.mergeCores(solrAdmin.getPrimaryCore(), cores);

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken to merging indexes: " + (endTime-startTime) + "ms");

        solrAdmin.unLoadSolrCores(cores);

    }


    @Test
    public void fullReIndex() {
        unloadCores();
        boolean indexResult = solrIndexer.fullIndex(5, 2000);
        System.out.println(indexResult);
        unloadCores();

    }

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
                break;
            }
        }


        String fileSeperator = System.getProperty("file.separator");
        String tempCoreName0 = "temp0";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName0 + fileSeperator + "conf"));
        String tempCoreName1 = "temp1";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName1 + fileSeperator + "conf"));

        String tempCoreName2 = "temp2";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName2 + fileSeperator + "conf"));

        String tempCoreName3 = "temp3";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName3 + fileSeperator + "conf"));

        String tempCoreName4 = "temp4";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName4 + fileSeperator + "conf"));

        String tempCoreName5 = "temp5";
        FileUtils.copyDirectory(new File(solrHome + fileSeperator + solrCore + fileSeperator + "conf"), new File(solrHome + fileSeperator + tempCoreName5 + fileSeperator + "conf"));


        SolrClient solrClient = getSolrClient();

        CoreAdminRequest coreAdminRequest = getCoreAdminRequest();

        CoreAdminResponse tempSolrCore0 = coreAdminRequest.createCore(tempCoreName0, tempCoreName0, solrClient);
        assertNotNull(tempSolrCore0);
        CoreAdminResponse tempSolrCore = coreAdminRequest.createCore(tempCoreName1, tempCoreName1, solrClient);
        assertNotNull(tempSolrCore);

        CoreAdminResponse tempSolrCore2 = coreAdminRequest.createCore(tempCoreName2, tempCoreName2, solrClient);
        assertNotNull(tempSolrCore2);

        CoreAdminResponse tempSolrCore3 = coreAdminRequest.createCore(tempCoreName3, tempCoreName3, solrClient);
        assertNotNull(tempSolrCore3);

        CoreAdminResponse tempSolrCore4 = coreAdminRequest.createCore(tempCoreName4, tempCoreName4, solrClient);
        assertNotNull(tempSolrCore4);

        CoreAdminResponse tempSolrCore5 = coreAdminRequest.createCore(tempCoreName5, tempCoreName5, solrClient);
        assertNotNull(tempSolrCore5);
    }


}