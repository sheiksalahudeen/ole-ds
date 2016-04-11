package org.kuali.ole.extractor;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.ole.bo.SearchConfigurationDocFieldBo;
import org.kuali.ole.builder.BibSearchIndexBuilder;
import org.kuali.ole.context.ApplicationContextProvider;
import org.kuali.ole.model.OleDsDocFieldT;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by pvsubrah on 10/21/15.
 */

public class BibInfoExtractor {

    Logger logger = LoggerFactory.getLogger(BibInfoExtractor.class);

    public Object transformRow(Map<String, Object> row) {
        String unique_id_prefix = (String) row.get("UNIQUE_ID_PREFIX");
        row.remove(unique_id_prefix);
        Timestamp date_updated = (Timestamp) row.get("DATE_UPDATED");
        String fast_add = (String) row.get("FAST_ADD");
        String status = (String) row.get("STATUS");
        String staff_only = (String) row.get("STAFF_ONLY");
        String created_by = (String) row.get("CREATED_BY");
        String content = (String) row.get("CONTENT");
        row.remove(content);
        String updated_by = (String) row.get("UPDATED_BY");
        Integer bib_id = (Integer) row.get("BIB_ID");
        String status_updated_by = (String) row.get("STATUS_UPDATED_BY");
        Timestamp date_created = (Timestamp) row.get("DATE_CREATED");
        String former_id = (String) row.get("FORMER_ID");
        Timestamp status_updated_date = (Timestamp) row.get("STATUS_UPDATED_DATE");


        MarcReader marcReader = new MarcXmlReader(IOUtils.toInputStream(content));

        Record record = null;
        if (marcReader.hasNext()) {
            record = marcReader.next();
        }

        row.clear();
        row.put("oleds_unique_id_prefix", unique_id_prefix);
        row.put("oleds_date_date_updated", date_updated);
        row.put("oleds_fast_add", fast_add);
        row.put("oleds_status", status);
        row.put("oleds_staff_only", staff_only);
        row.put("oleds_created_by", created_by);
        row.put("oleds_bib_id", bib_id);
        row.put("oleds_updated_by", updated_by);
        row.put("oleds_status_updated_by", status_updated_by);
        row.put("oleds_date_created", date_created);
        row.put("oleds_former_id", former_id);
        row.put("oleds_status_updated_date", status_updated_date);

        ApplicationContextProvider applicationContextProvider = ApplicationContextProvider.getInstance();
        org.springframework.context.ApplicationContext instance = applicationContextProvider.getApplicationContext();

        SearchConfigurationDocFieldBo searchConfigurationDocFieldBo =
                (SearchConfigurationDocFieldBo) instance.getBean("searchConfigurationDocFieldBo");

        List searchFieldConfigurations = searchConfigurationDocFieldBo.getCachedBibSearchFieldConfigurations();
        for (Iterator iterator = searchFieldConfigurations.iterator(); iterator.hasNext(); ) {
            OleDsDocFieldT oleDsDocFieldT = (OleDsDocFieldT) iterator.next();
            String fieldName = oleDsDocFieldT.getName();
            String subFieldsToConsider = oleDsDocFieldT.getIncludePath();
            if (StringUtils.isNotBlank(subFieldsToConsider)) {
                String indexedValues = null;
                try {
                    indexedValues = getSearchIndexBuilder().buildIndexedValues(record, subFieldsToConsider, null);
                } catch (Exception e) {
                    logger.error("Indexing failed for :" + fieldName + " for the include path: " + subFieldsToConsider);
                    logger.error(e.getMessage());
                }
                row.put("oleds_" + fieldName, indexedValues);
            }
        }
        return row;
    }

    private BibSearchIndexBuilder getSearchIndexBuilder() {
        return new BibSearchIndexBuilder();
    }
}
