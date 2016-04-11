package org.kuali.ole.executors;

import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.dao.BibDAO;
import org.kuali.ole.solr.SolrAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by pvsubrah on 10/24/15.
 */
@Service("solrIndexer")
public class SolrIndexer {

    Logger logger = LoggerFactory.getLogger(SolrIndexer.class);

    @Autowired
    BibDAO bibDAO;

    private Map<String, Integer> indexMap;
    private SolrAdmin solrAdmin;

    public boolean fullIndex(Integer numThreads, Integer batchSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        Long totalBibs = getBibDAO().totalCount();

        //1. Create temp cores;
        long startTime = System.currentTimeMillis();
        List<String> tempCores = setupTempCores(numThreads);
        long endTime = System.currentTimeMillis();
        logger.info("Time to create temp " + tempCores.size() + " cores: " + (endTime - startTime) + " ms");

        if (tempCores.size() != numThreads) {
            logger.error("Mismatch in num temp cores created and num threads: " + "Temp cores (" + tempCores + ")" + "vs" + "Num threads (" + numThreads + ")");
            return false;
        }

        long numLoops = totalBibs / batchSize;

        for (int loop = 0; loop <= numLoops; loop++) {
            indexBatch(batchSize, executorService, tempCores);
            deletePreviousIndexes(tempCores);
        }

        executorService.shutdown();

        return true;
    }

    private void deletePreviousIndexes(List<String> tempCores) {
        getSolrAdmin().deletePreviousIndexes(tempCores);
    }

    private void indexBatch(Integer batchSize, ExecutorService executorService, List<String> tempCores) {
        //Set up threads;
        Map<Future, Boolean> futuresMap = new HashMap<Future, Boolean>();
        for (Iterator<String> iterator = tempCores.iterator(); iterator.hasNext(); ) {
            String tempCoreName = iterator.next();
            Integer startIndex = getStartIndex(batchSize);
            BibIndexerExecutor bibIndexerExecutor =
                    (BibIndexerExecutor) ApplicationContextProvider.getInstance().getApplicationContext().getBean("bibIndexerExecutor");
            bibIndexerExecutor.setBatchSize(batchSize);
            bibIndexerExecutor.setCoreName(tempCoreName);
            bibIndexerExecutor.setStartIndex(startIndex);
            Future future = executorService.submit(bibIndexerExecutor);
            logger.info("StartIndex: " + startIndex);
            futuresMap.put(future, Boolean.FALSE);
        }

        // Get results from threads

        for (Iterator<Future> iterator = futuresMap.keySet().iterator(); iterator.hasNext(); ) {
            Future future = iterator.next();
            while (!futuresMap.get(future))
                try {
                    Integer resultCount = (Integer) future.get();
                    System.out.println("Num docs indexed: " + resultCount);
                    futuresMap.put(future, Boolean.TRUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        }

        //4. Merge indexes from the temp core
        long startTime = System.currentTimeMillis();
        solrAdmin.mergeCores(solrAdmin.getPrimaryCore(), tempCores);
        long endTime = System.currentTimeMillis();
        logger.info("Time taken to merge indexes: " + (endTime-startTime) + " ms");
    }

    public BibDAO getBibDAO() {
        return bibDAO;
    }

    public void setBibDAO(BibDAO bibDAO) {
        this.bibDAO = bibDAO;
    }

    public List<String> setupTempCores(Integer numTempCores) {
        ArrayList<String> tempCoreNames = new ArrayList<String>();

        for (int i = 0; i < numTempCores; i++) {
            tempCoreNames.add("temp" + i);
        }
        solrAdmin = getSolrAdmin();
        List createdCores = solrAdmin.createSolrCores(tempCoreNames);
        return createdCores;
    }

    private SolrAdmin getSolrAdmin() {
        if (null == solrAdmin) {
            solrAdmin = new SolrAdmin();
        }
        return solrAdmin;
    }

    public Integer getStartIndex(Integer batchSize) {
        String coreName = "tempCore";
        if (null == indexMap) {
            indexMap = new HashMap();
        }

        if (indexMap.get(coreName) == null) {
            indexMap.put(coreName, 0);
        } else {
            Integer prevBatch = indexMap.get(coreName);
            indexMap.put(coreName, prevBatch + batchSize);
        }

        return indexMap.get(coreName);
    }
}
