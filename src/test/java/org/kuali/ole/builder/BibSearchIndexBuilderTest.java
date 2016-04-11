package org.kuali.ole.builder;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.Record;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pvsubrah on 10/22/15.
 */
public class BibSearchIndexBuilderTest {

    @Test
    public void buildIndexedValue() throws Exception {
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


        MarcReader marcReader = new MarcXmlReader(IOUtils.toInputStream(bibContent));
        Record record = marcReader.next();

        BibSearchIndexBuilder bibSearchIndexBuilder = new BibSearchIndexBuilder();
        String includePath = "245-a;b;c;f;g;h;k;n;p;s";

        String titleDisplayIndexedValues = bibSearchIndexBuilder.buildIndexedValues(record, includePath, null);
        assertNotNull(titleDisplayIndexedValues);
        System.out.println("Title_Display: " + titleDisplayIndexedValues);


        String authorFacetIncluePath = "100,110,111,700,710,711,800,810,811,400,410,411";
        String authorFacetIndexedValues = bibSearchIndexBuilder.buildIndexedValues(record, authorFacetIncluePath, null);
        assertNotNull(authorFacetIndexedValues);
        System.out.println("Author_facet: " + authorFacetIndexedValues);

    }

}