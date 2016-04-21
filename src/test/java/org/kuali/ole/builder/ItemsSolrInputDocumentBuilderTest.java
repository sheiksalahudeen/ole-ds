package org.kuali.ole.builder;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.model.*;
import org.slf4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by senthilkumars on 4/21/16.
 */
public class ItemsSolrInputDocumentBuilderTest extends BaseTestCase {
    Logger logger = org.slf4j.LoggerFactory.getLogger(ItemsSolrInputDocumentBuilderTest.class);

    @Test
    public void build() {
        OleDsItemT oleDsItemT = buildOleDsItemT();

        ItemsSolrInputDocumentBuilder itemSolrInputDocumentBuilder = new ItemsSolrInputDocumentBuilder();
        List<SolrInputDocument> solrInputDocumentList = itemSolrInputDocumentBuilder.build(oleDsItemT);
        assertNotNull(solrInputDocumentList);
    }

    private OleDsItemT buildOleDsItemT() {
        OleDsItemT oleDsItemT = new OleDsItemT();

        oleDsItemT.setItemId(243);
        oleDsItemT.setUniqueIdPrefix("wio");
        oleDsItemT.setClaimsReturned("N");

        oleDsItemT.setClaimsReturnedDateCreated(null);
        oleDsItemT.setClaimsReturnedNote(null);
        oleDsItemT.setCurrentBorrower(null);
        oleDsItemT.setProxyBorrower(null);
        oleDsItemT.setDueDateTime(null);
        oleDsItemT.setOrgDueDateTime(null);

        String itemStatusDateUpdated = "2014-08-09 00:11:52";
        DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd hh:mm:ss");
        try {
            oleDsItemT.setItemStatusDateUpdated(dateFormat.parse(itemStatusDateUpdated));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsItemT.setCheckOutDateTime(null);

        oleDsItemT.setStaffOnly("N");
        oleDsItemT.setBarcodeArsl(null);
        oleDsItemT.setCopyNumber(null);
        oleDsItemT.setPurchaseOrderLineItemId(null);
        oleDsItemT.setVendorLineItemId(null);
        oleDsItemT.setEnumeration(null);
        oleDsItemT.setChronology(null);
        oleDsItemT.setMissingPiecesNote(null);
        oleDsItemT.setClaimsReturnedNote(null);
        oleDsItemT.setItemDamagedNote(null);
        oleDsItemT.setMissingPieces("N");
        oleDsItemT.setClaimsReturned("N");
        oleDsItemT.setItemDamagedStatus("N");
        oleDsItemT.setMissingPiecesCount(null);
        oleDsItemT.setNumPieces(null);
        oleDsItemT.setCallNumber("Z 17");
        oleDsItemT.setCallNumberPrefix("microfc");

        OleCatShvlgSchmT oleCatShvlgSchmT = new OleCatShvlgSchmT();
        oleCatShvlgSchmT.setShvlgSchmCd("LCC");
        oleCatShvlgSchmT.setShvlgSchmNm("LCC - Library of Congress classification");
        oleCatShvlgSchmT.setSrc("MFHD 852 1st Indicator: http://www.loc.gov/marc/holdings/hd852.html");
        oleCatShvlgSchmT.setShvlgSchmId(2);

        String date = "2012-03-22 00:00:00";
        try {
            oleCatShvlgSchmT.setSrcDt(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsItemT.setOleCatShvlgSchmT(oleCatShvlgSchmT);
        oleDsItemT.setShelvingOrder(null);

        oleDsItemT.setBarcode("C1000001");

        OleDlvrItemAvailStatT oleDlvrItemAvailStatT = new OleDlvrItemAvailStatT();
        oleDlvrItemAvailStatT.setItemAvailStatCd("AVAILABLE");
        oleDlvrItemAvailStatT.setItemAvailStatNm("Available");

        oleDsItemT.setOleDlvrItemAvailStatT(oleDlvrItemAvailStatT);

        OleCatItmTypT oleCatItmTypT = new OleCatItmTypT();
        oleCatItmTypT.setItmTypCd("stks");
        oleCatItmTypT.setItmTypNm("stks - Regular loan");
        oleCatItmTypT.setItmTypDesc(null);
        oleCatItmTypT.setSrc("UC");

        String srcDate = "2014-03-13";
        DateFormat srcDateFormat = new SimpleDateFormat("YYYY-mm-dd");

        try {
            oleCatItmTypT.setSrcDt(srcDateFormat.parse(srcDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsItemT.setItemTypeRecord(oleCatItmTypT);
        oleDsItemT.setTempItemTypeRecord(null);

        // instantiating with empty list as setting null throws NullPointerException
        oleDsItemT.setOleDsItemStatSearchTs(new ArrayList<OleDsItemStatSearchT>());

        oleDsItemT.setCreatedBy("bulk loader");
        oleDsItemT.setUpdatedBy("bulk loader");

        String dateCreated = "2014-08-09 00:11:52";
        String dateUpdated = "2014-08-09 00:11:52";

        try {
            oleDsItemT.setDateCreated(dateFormat.parse(dateCreated));
            oleDsItemT.setDateUpdated(dateFormat.parse(dateUpdated));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsItemT.setOleDsItemDonorTs(new ArrayList<OleDsItemDonorT>());

        oleDsItemT.setOleDsHighDensityStorageT(null);

        OleDsItemNoteT oleDsItemNoteT = new OleDsItemNoteT();
        oleDsItemNoteT.setType("nonPublic");
        oleDsItemNoteT.setNote("Dummy item built by program - Aug 30 1998  9:06AM");

        oleDsItemT.setOleDsItemNoteTs(new ArrayList<OleDsItemNoteT>(Arrays.asList(oleDsItemNoteT)));

        oleDsItemT.setNumOfRenew(null);

        return oleDsItemT;
    }
}
