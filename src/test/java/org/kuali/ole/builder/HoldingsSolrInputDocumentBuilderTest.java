package org.kuali.ole.builder;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.kuali.ole.BaseTestCase;
import org.kuali.ole.dao.HoldingsDAO;
import org.kuali.ole.model.OleCatShvlgSchmT;
import org.kuali.ole.model.OleDsHoldingsNoteT;
import org.kuali.ole.model.OleDsHoldingsT;
import org.kuali.ole.model.OleDsHoldingsUriT;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;


/**
 * Created by senthilkumars on 4/21/16.
 */
public class HoldingsSolrInputDocumentBuilderTest extends BaseTestCase {
    Logger logger = org.slf4j.LoggerFactory.getLogger(HoldingsSolrInputDocumentBuilderTest.class);

    @Test
    public void build() {

        //List<OleDsHoldingsT> dsHoldingsTs1 = holdingsDAO.findAllByLimit(0, 1,true, "bibId", "ASC");
        OleDsHoldingsT oleDsHoldingsT = buildOleDsHoldingsT();
        //assertNotNull(dsHoldingsTs1);

        HoldingsSolrInputDocumentBuilder holdingsSolrInputDocumentBuilder = new HoldingsSolrInputDocumentBuilder();
        List<SolrInputDocument> solrInputDocumentList = holdingsSolrInputDocumentBuilder.build(oleDsHoldingsT);
        assertNotNull(solrInputDocumentList);
    }

    private OleDsHoldingsT buildOleDsHoldingsT() {
        OleDsHoldingsT oleDsHoldingsT = new OleDsHoldingsT();

        oleDsHoldingsT.setUniqueIdPrefix("who");
        oleDsHoldingsT.setHoldingsId(13);

        //oleDsHoldingsT.setGokbIdentifier();

        oleDsHoldingsT.setStaffOnly("Y");
        oleDsHoldingsT.setReceiptStatusId(6);
        oleDsHoldingsT.setCopyNumber("c.1");
        oleDsHoldingsT.setCallNumber("z 17");
        oleDsHoldingsT.setCallNumberPrefix("microfc");

        OleCatShvlgSchmT oleCatShvlgSchmT = new OleCatShvlgSchmT();
        oleCatShvlgSchmT.setShvlgSchmCd("LCC");
        oleCatShvlgSchmT.setShvlgSchmNm("LCC - Library of Congress classification");
        oleCatShvlgSchmT.setSrc("MFHD 852 1st Indicator: http://www.loc.gov/marc/holdings/hd852.html");
        oleCatShvlgSchmT.setShvlgSchmId(2);

        String date = "2012-03-22 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            oleCatShvlgSchmT.setSrcDt(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        oleDsHoldingsT.setOleCatShvlgSchmT(oleCatShvlgSchmT);

        oleDsHoldingsT.setLocation("UC/JRL/Mic");
        oleDsHoldingsT.setLocationLevel("Institution/Library/Shelving");
        oleDsHoldingsT.setHoldingsType("print");

        OleDsHoldingsNoteT oleDsHoldingsNoteT = new OleDsHoldingsNoteT();
        oleDsHoldingsNoteT.setHoldingsNoteId(15);
        oleDsHoldingsNoteT.setType("nonPublic");
        oleDsHoldingsNoteT.setNote("HLI:19930820 ALL HOLDINGS ONLINE.jrmCOR:19930817 Moribund order cancelled after 181 per fc.log no c94-0265.hmsWNT:No. 171 and cont. Supersedes our order no. 68-182 025 to the University of Rochester and our order no. 105789 for ACRL microcard series.OST:72DST:2");

        OleDsHoldingsNoteT oleDsHoldingsNoteT2 = new OleDsHoldingsNoteT();
        oleDsHoldingsNoteT2.setHoldingsNoteId(1546669);
        oleDsHoldingsNoteT2.setType("nonPublic");
        oleDsHoldingsNoteT2.setNote(" HLI:19930820 ALL HOLDINGS ONLINE.jrm\n" +
                "Fund:lib//s\n" +
                "REC:100672                                                                                                                                                                                                         ");

        List<OleDsHoldingsNoteT> oleDsHoldingsNoteTList = new ArrayList<OleDsHoldingsNoteT>();
        oleDsHoldingsNoteTList.add(oleDsHoldingsNoteT);
        oleDsHoldingsNoteTList.add(oleDsHoldingsNoteT2);

        oleDsHoldingsT.setOleDsHoldingsNoteTs(oleDsHoldingsNoteTList);

        //Is it a mandatory field? Does not have this entity for certain holdings in db.
        // If not needed then check to be added to HoldingsSolrInputDocumentBuilder.indexPHoldingsInformation().
        // Currently throws NullPointer if the below object is not available in parent (OleDsHoldingsT)
        OleDsHoldingsUriT oleDsHoldingsUriT = new OleDsHoldingsUriT();
        oleDsHoldingsUriT.setHoldingsUriId(2);
        oleDsHoldingsUriT.setText("test");

        List<OleDsHoldingsUriT> oleDsHoldingsUriTList = new ArrayList<OleDsHoldingsUriT>();
        oleDsHoldingsUriTList.add(oleDsHoldingsUriT);

        oleDsHoldingsT.setOleDsHoldingsUriTs(oleDsHoldingsUriTList);

        return oleDsHoldingsT;
    }
}
