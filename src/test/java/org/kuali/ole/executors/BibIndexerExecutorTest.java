package org.kuali.ole.executors;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.dao.BibDAO;
import org.kuali.ole.solr.SolrAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by pvsubrah on 10/25/15.
 */
public class BibIndexerExecutorTest extends BaseTestCase {

    @Autowired
    BibDAO bibDAO;

    @Autowired
    SolrIndexer solrIndexer;

    @Test
    public void deleteSolrIndexes() throws Exception {
        String urlString = "http://localhost:8983/solr/oleds-primary";
        SolrClient solr = new HttpSolrClient(urlString);

        solr.deleteByQuery("*:*");
        solr.commit();
    }



    @Test
    public void indexBibs() {
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
        solrAdmin.createSolrCores(cores);

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
        boolean indexResult = solrIndexer.fullIndex(5, 2000);
        System.out.println(indexResult);

    }

}