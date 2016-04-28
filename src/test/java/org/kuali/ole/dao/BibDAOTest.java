package org.kuali.ole.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.dao.impl.BibDAOImpl;
import org.kuali.ole.datasource.AbstractJpaDAO;
import org.kuali.ole.model.OleDsBibT;
import org.kuali.ole.model.OleDsHoldingsT;
import org.kuali.ole.model.OleDsItemT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by pvsubrah on 10/23/15.
 */
public class BibDAOTest extends BaseTestCase{
    Logger logger = LoggerFactory.getLogger(BibDAOTest.class);

    //private ApplicationContext applicationContext;

    @Autowired
    BibDAO bibDAO;

    @Test
    public void bibAndRelatedData() throws Exception {
        assertNotNull(bibDAO);
        OleDsBibT dsBibT = bibDAO.findByPrimaryKey(10000001);
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
        List<OleDsBibT> dsBibTs1 = bibDAO.findAllByLimit(0, 10, true, "bibId", "ASC");
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

    @Test
    public void findByPrimaryKey() {
        logger.info("running findByPrimaryKey() test");
        // create one Bib record
        OleDsBibT oleDsBibT = new OleDsBibT();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd hh:mm:ss");
        oleDsBibT.setUniqueIdPrefix("wbm");

        String dateUpdated = "2005-02-25 05:28:00";
        try {
            oleDsBibT.setDateUpdated(dateFormat.parse(dateUpdated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        oleDsBibT.setFastAdd("N");
        oleDsBibT.setStatus("Cataloging complete");
        oleDsBibT.setStaffOnly("N");
        oleDsBibT.setCreatedBy("bulk loader");
        oleDsBibT.setUpdatedBy("kennelm");

        String dateCreated = "1974-12-02 18:00:00";

        try {
            oleDsBibT.setDateCreated(dateFormat.parse(dateCreated));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsBibT.setFormerId("hzn1");

        oleDsBibT.setStatusUpdatedBy(null);
        oleDsBibT.setStatusUpdatedDate(null);

        String bibContent =
                "<collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n" +
                        "<record>\n" +
                        "  <leader>00956nam a2200301   4500</leader>\n" +
                        "  <controlfield tag=\"001\">81989</controlfield>\n" +
                        "  <controlfield tag=\"005\">19741022000000.0</controlfield>\n" +
                        "  <controlfield tag=\"008\">760326m19721973paua     b    00100 eng u</controlfield>\n" +
                        "  <datafield tag=\"010\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">   72077585 //r73</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"020\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">0803630654 (v. 2)</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">(ICU)BID1564611</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">(OCoLC)508240</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"040\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">DLC</subfield>\n" +
                        "    <subfield code=\"c\">DLC</subfield>\n" +
                        "    <subfield code=\"d\">ICU</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"041\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">eng</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"050\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">RC681.A1</subfield>\n" +
                        "    <subfield code=\"b\">C27 vol. 4, no. 2, etc.</subfield>\n" +
                        "    <subfield code=\"a\">RC683</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"082\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">616.1/008 s</subfield>\n" +
                        "    <subfield code=\"a\">616.1/2/07</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"245\" ind1=\"0\" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Clinical-pathologic correlations.</subfield>\n" +
                        "    <subfield code=\"c\">Jesse E. Edwards, guest editor.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"260\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Philadelphia,</subfield>\n" +
                        "    <subfield code=\"b\">F. A. Davis Co.</subfield>\n" +
                        "    <subfield code=\"c\">[1972-73]</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"300\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">2 v.</subfield>\n" +
                        "    <subfield code=\"b\">illus.</subfield>\n" +
                        "    <subfield code=\"c\">27 cm.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"490\" ind1=\"1\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Cardiovascular clinics,</subfield>\n" +
                        "    <subfield code=\"v\">v. 4, no. 2; v. 5, no. 1</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"504\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Includes bibliographical references.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Cardiovascular system</subfield>\n" +
                        "    <subfield code=\"x\">Diseases.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Anatomy, Pathological</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"700\" ind1=\"1\" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Edwards, Jesse E.</subfield>\n" +
                        "    <subfield code=\"e\">ed.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"830\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Cardiovascular clinics</subfield>\n" +
                        "    <subfield code=\"v\">4/2; 5/1.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"900\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">ICU:76406121</subfield>\n" +
                        "    <subfield code=\"c\">HST:500</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"903\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">ANAL</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"903\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">SMON</subfield>\n" +
                        "  </datafield>\n" +
                        "</record>\n" +
                        "</collection>\n";

        oleDsBibT.setContent(bibContent);
        assertNotNull(bibDAO);
        // insert the created record
        bibDAO.save(oleDsBibT);

        logger.info("------------Bib id of the generated record is --------------" + oleDsBibT.getBibId() +"-------------------");

        OleDsBibT oleDsBibTCreated = bibDAO.findByPrimaryKey(oleDsBibT.getBibId());
        logger.info("------------fetching the created bib record --------------" + oleDsBibTCreated.getBibId() + "---------------");
        // Assert the fetched record for correctness of details
        assertNotNull(oleDsBibTCreated);
    }

    @Test
    public void findAll() {
        logger.info("running findAll() test");
        // create one Bib record
        OleDsBibT oleDsBibT = new OleDsBibT();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd hh:mm:ss");
        oleDsBibT.setUniqueIdPrefix("wbm");

        String dateUpdated = "2005-02-25 05:28:00";
        try {
            oleDsBibT.setDateUpdated(dateFormat.parse(dateUpdated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        oleDsBibT.setFastAdd("N");
        oleDsBibT.setStatus("Cataloging complete");
        oleDsBibT.setStaffOnly("N");
        oleDsBibT.setCreatedBy("bulk loader");
        oleDsBibT.setUpdatedBy("kennelm");

        String dateCreated = "1974-12-02 18:00:00";

        try {
            oleDsBibT.setDateCreated(dateFormat.parse(dateCreated));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsBibT.setFormerId("hzn1");

        oleDsBibT.setStatusUpdatedBy(null);
        oleDsBibT.setStatusUpdatedDate(null);

        String bibContent =
                "<collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n" +
                        "<record>\n" +
                        "  <leader>00956nam a2200301   4500</leader>\n" +
                        "  <controlfield tag=\"001\">81989</controlfield>\n" +
                        "  <controlfield tag=\"005\">19741022000000.0</controlfield>\n" +
                        "  <controlfield tag=\"008\">760326m19721973paua     b    00100 eng u</controlfield>\n" +
                        "  <datafield tag=\"010\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">   72077585 //r73</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"020\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">0803630654 (v. 2)</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">(ICU)BID1564611</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">(OCoLC)508240</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"040\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">DLC</subfield>\n" +
                        "    <subfield code=\"c\">DLC</subfield>\n" +
                        "    <subfield code=\"d\">ICU</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"041\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">eng</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"050\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">RC681.A1</subfield>\n" +
                        "    <subfield code=\"b\">C27 vol. 4, no. 2, etc.</subfield>\n" +
                        "    <subfield code=\"a\">RC683</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"082\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">616.1/008 s</subfield>\n" +
                        "    <subfield code=\"a\">616.1/2/07</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"245\" ind1=\"0\" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Clinical-pathologic correlations.</subfield>\n" +
                        "    <subfield code=\"c\">Jesse E. Edwards, guest editor.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"260\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Philadelphia,</subfield>\n" +
                        "    <subfield code=\"b\">F. A. Davis Co.</subfield>\n" +
                        "    <subfield code=\"c\">[1972-73]</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"300\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">2 v.</subfield>\n" +
                        "    <subfield code=\"b\">illus.</subfield>\n" +
                        "    <subfield code=\"c\">27 cm.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"490\" ind1=\"1\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Cardiovascular clinics,</subfield>\n" +
                        "    <subfield code=\"v\">v. 4, no. 2; v. 5, no. 1</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"504\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Includes bibliographical references.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Cardiovascular system</subfield>\n" +
                        "    <subfield code=\"x\">Diseases.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Anatomy, Pathological</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"700\" ind1=\"1\" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Edwards, Jesse E.</subfield>\n" +
                        "    <subfield code=\"e\">ed.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"830\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Cardiovascular clinics</subfield>\n" +
                        "    <subfield code=\"v\">4/2; 5/1.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"900\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">ICU:76406121</subfield>\n" +
                        "    <subfield code=\"c\">HST:500</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"903\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">ANAL</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"903\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">SMON</subfield>\n" +
                        "  </datafield>\n" +
                        "</record>\n" +
                        "</collection>\n";

        oleDsBibT.setContent(bibContent);
        assertNotNull(bibDAO);

        Long count = bibDAO.totalCount();
        logger.info("---------- count size before save --------" + count + "---------------");
        // insert the created record
        bibDAO.save(oleDsBibT);

        logger.info("------------Bib id of the generated record is --------------" + oleDsBibT.getBibId() + "-------------------");
        List<OleDsBibT> oleDsBibTList = bibDAO.findAll();
        logger.info("size of the fetched records is --------------" + oleDsBibTList.size() + "-----------------");

        assertTrue((count + 1) == oleDsBibTList.size());
        boolean bibCreated = false;
        for(OleDsBibT oleDsBibT1 : oleDsBibTList){
            logger.info("-----------fetched bib id ------------" + oleDsBibT1.getBibId() + "------------ created bib id is ----------" + oleDsBibT.getBibId() +"----------");
            if(oleDsBibT1.getBibId().equals(oleDsBibT.getBibId())) {
                logger.info("------------created bib found ----------------");
                bibCreated = true;
            }
        }

        assertTrue(bibCreated);
    }

    @Test
    public void findAllByLimit() {
        logger.info("running findAllByLimit() test");
        // create one Bib record
        OleDsBibT oleDsBibT = new OleDsBibT();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd hh:mm:ss");
        oleDsBibT.setUniqueIdPrefix("wbm");

        String dateUpdated = "2005-02-25 05:28:00";
        try {
            oleDsBibT.setDateUpdated(dateFormat.parse(dateUpdated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        oleDsBibT.setFastAdd("N");
        oleDsBibT.setStatus("Cataloging complete");
        oleDsBibT.setStaffOnly("N");
        oleDsBibT.setCreatedBy("bulk loader");
        oleDsBibT.setUpdatedBy("kennelm");

        String dateCreated = "1974-12-02 18:00:00";

        try {
            oleDsBibT.setDateCreated(dateFormat.parse(dateCreated));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsBibT.setFormerId("AAAA");

        oleDsBibT.setStatusUpdatedBy(null);
        oleDsBibT.setStatusUpdatedDate(null);

        String bibContent =
                "<collection xmlns=\"http://www.loc.gov/MARC21/slim\">\n" +
                        "<record>\n" +
                        "  <leader>00956nam a2200301   4500</leader>\n" +
                        "  <controlfield tag=\"001\">81989</controlfield>\n" +
                        "  <controlfield tag=\"005\">19741022000000.0</controlfield>\n" +
                        "  <controlfield tag=\"008\">760326m19721973paua     b    00100 eng u</controlfield>\n" +
                        "  <datafield tag=\"010\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">   72077585 //r73</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"020\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">0803630654 (v. 2)</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">(ICU)BID1564611</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"035\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">(OCoLC)508240</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"040\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">DLC</subfield>\n" +
                        "    <subfield code=\"c\">DLC</subfield>\n" +
                        "    <subfield code=\"d\">ICU</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"041\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">eng</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"050\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">RC681.A1</subfield>\n" +
                        "    <subfield code=\"b\">C27 vol. 4, no. 2, etc.</subfield>\n" +
                        "    <subfield code=\"a\">RC683</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"082\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">616.1/008 s</subfield>\n" +
                        "    <subfield code=\"a\">616.1/2/07</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"245\" ind1=\"0\" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Clinical-pathologic correlations.</subfield>\n" +
                        "    <subfield code=\"c\">Jesse E. Edwards, guest editor.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"260\" ind1=\"0\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Philadelphia,</subfield>\n" +
                        "    <subfield code=\"b\">F. A. Davis Co.</subfield>\n" +
                        "    <subfield code=\"c\">[1972-73]</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"300\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">2 v.</subfield>\n" +
                        "    <subfield code=\"b\">illus.</subfield>\n" +
                        "    <subfield code=\"c\">27 cm.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"490\" ind1=\"1\" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Cardiovascular clinics,</subfield>\n" +
                        "    <subfield code=\"v\">v. 4, no. 2; v. 5, no. 1</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"504\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">Includes bibliographical references.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Cardiovascular system</subfield>\n" +
                        "    <subfield code=\"x\">Diseases.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"650\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Anatomy, Pathological</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"700\" ind1=\"1\" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Edwards, Jesse E.</subfield>\n" +
                        "    <subfield code=\"e\">ed.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"830\" ind1=\" \" ind2=\"0\">\n" +
                        "    <subfield code=\"a\">Cardiovascular clinics</subfield>\n" +
                        "    <subfield code=\"v\">4/2; 5/1.</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"900\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">ICU:76406121</subfield>\n" +
                        "    <subfield code=\"c\">HST:500</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"903\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">ANAL</subfield>\n" +
                        "  </datafield>\n" +
                        "  <datafield tag=\"903\" ind1=\" \" ind2=\" \">\n" +
                        "    <subfield code=\"a\">SMON</subfield>\n" +
                        "  </datafield>\n" +
                        "</record>\n" +
                        "</collection>\n";

        oleDsBibT.setContent(bibContent);
        assertNotNull(bibDAO);

        // insert the created record
        bibDAO.save(oleDsBibT);

        //List<OleDsBibT> oleDsBibTList = bibDAO.findAll();
        List<OleDsBibT> oleDsBibTList = bibDAO.findAllByLimit(0, 1, true, "formerId", "ASC");
        logger.info("size of the fetched records is --------------" + oleDsBibTList.size() + "-----------------");

/*        boolean bibCreated = false;
        for(OleDsBibT oleDsBibT1 : oleDsBibTList){
            logger.info("-----------fetched bib id ------------" + oleDsBibT1.getBibId() + "------------ created bib id is ----------" + oleDsBibT.getBibId() +"----------");
            if(oleDsBibT1.getBibId().equals(oleDsBibT.getBibId())) {
                logger.info("------------created bib found ----------------");
                bibCreated = true;
            }
        }*/

        assertTrue(oleDsBibTList.get(0).getFormerId().equals("AAAA"));


        // List findAllByLimit(int startIndex, int maxResult, boolean orderBy, String columnName,String orderType)
    }

}
