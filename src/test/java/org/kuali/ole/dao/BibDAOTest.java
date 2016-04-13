package org.kuali.ole.dao;

import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.model.OleDsBibT;
import org.kuali.ole.model.OleDsHoldingsT;
import org.kuali.ole.model.OleDsItemT;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pvsubrah on 10/23/15.
 */
public class BibDAOTest extends BaseTestCase{

    @Autowired
    BibDAO bibDAO;

    @Test
    public void bibAndRelatedData() throws Exception {
        assertNotNull(bibDAO);
        OleDsBibT dsBibT = bibDAO.findByPrimaryKey(1);
        assertNotNull(dsBibT);
        System.out.println(dsBibT.getBibId());
        List<OleDsHoldingsT> oleDsHoldingsTs = dsBibT.getOleDsHoldingsTs();
        System.out.println("Num Holdings: " + oleDsHoldingsTs.size());
        if(!oleDsHoldingsTs.isEmpty()){
            OleDsHoldingsT oleDsHoldingsT = oleDsHoldingsTs.get(0);
            System.out.println("Holdings Id: " + oleDsHoldingsT.getHoldingsId());
            List<OleDsItemT> oleDsItemTs = oleDsHoldingsT.getOleDsItemTs();
            if(!oleDsItemTs.isEmpty()){
                System.out.println("Num Items: " + oleDsHoldingsTs.size());
                System.out.println("Item Barcode: " + oleDsItemTs.get(0).getBarcode());
            }
        }
        assertNotNull(dsBibT);
    }

    @Test
    public void holdingsRelatedData() throws Exception {
        assertNotNull(bibDAO);
        OleDsBibT dsBibT = bibDAO.findByPrimaryKey(10000001);
        System.out.println(dsBibT.getBibId());
        List<OleDsHoldingsT> oleDsHoldingsTs = dsBibT.getOleDsHoldingsTs();
        System.out.println("Num Holdings: " + oleDsHoldingsTs.size());
        if(!oleDsHoldingsTs.isEmpty()){
            OleDsHoldingsT oleDsHoldingsT = oleDsHoldingsTs.get(0);
            System.out.println("Holdings Id: " + oleDsHoldingsT.getHoldingsId());

            assertNotNull(oleDsHoldingsT.getOleCatShvlgSchmT());
            assertNotNull(oleDsHoldingsT.getOleCatRcptStatT());

            List<OleDsItemT> oleDsItemTs = oleDsHoldingsT.getOleDsItemTs();
            if(!oleDsItemTs.isEmpty()){
                System.out.println("Num Items: " + oleDsHoldingsTs.size());
                System.out.println("Item Barcode: " + oleDsItemTs.get(0).getBarcode());
            }
        }
        assertNotNull(dsBibT);
    }

    @Test
    public void getBibDataUsingCriteria() throws Exception {
        assertNotNull(bibDAO);
        List<OleDsBibT> dsBibTs1 = bibDAO.findAllByLimit(0, 10,true, "bibId", "ASC");
        assertNotNull(dsBibTs1);
        for (Iterator<OleDsBibT> iterator = dsBibTs1.iterator(); iterator.hasNext(); ) {
            OleDsBibT oleDsBibT = iterator.next();
            System.out.println("Bib Id : " + oleDsBibT.getBibId());
            List<OleDsHoldingsT> oleDsHoldingsTs = oleDsBibT.getOleDsHoldingsTs();
            System.out.println("Num holdings: " + oleDsHoldingsTs.size());
        }
        System.out.println("Number of Bib records : " + dsBibTs1.size());
        System.out.println("**************** Batch One finished ***********************");

        List<OleDsBibT> dsBibTs2 = bibDAO.findAllByLimit(10, 10, true, "bibId", "DESC");
        assertNotNull(dsBibTs2);
        for (Iterator<OleDsBibT> iterator = dsBibTs2.iterator(); iterator.hasNext(); ) {
            OleDsBibT oleDsBibT = iterator.next();
            System.out.println("Bib Id : " + oleDsBibT.getBibId());
        }
        System.out.println("Number of Bib records : " + dsBibTs2.size());
        System.out.println("**************** Batch Two finished ***********************");
    }

    @Test
    public void getTotalCount() throws Exception {
        long batchSize = 10000;

        Long totalCount = bibDAO.totalCount();
        System.out.println("Total num bibs: " + totalCount);

        long numLoops = totalCount/batchSize;
        long remainder = totalCount%batchSize;

        System.out.println(numLoops);
        System.out.println(remainder);

    }
}
