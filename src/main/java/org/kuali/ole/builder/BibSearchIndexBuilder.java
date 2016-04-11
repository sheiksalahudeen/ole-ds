package org.kuali.ole.builder;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by pvsubrah on 10/22/15.
 */
public class BibSearchIndexBuilder {
    public String buildIndexedValues(Record record, String includePath, String excludePath) {
        StringBuilder indexedValue = new StringBuilder();

        Map<String, List<String>> dataFieldMap = new HashMap();
        String dataField = null;

        StringTokenizer dataFieldsTokenizer = new StringTokenizer(includePath, ",");

        while (dataFieldsTokenizer.hasMoreTokens()) {
            List<String> subFieldsList = new ArrayList();

            String dataAndSubfields = dataFieldsTokenizer.nextToken();
            StringTokenizer subFieldsTokenizer = new StringTokenizer(dataAndSubfields, ";");

            if (subFieldsTokenizer.countTokens() > 1) {
                while (subFieldsTokenizer.hasMoreTokens()) {
                    String df = subFieldsTokenizer.nextToken();
                    StringTokenizer dfTokeinzer = new StringTokenizer(df, "-");
                    if (dfTokeinzer.countTokens() == 2) {
                        dataField = dfTokeinzer.nextToken();
                        String sf = dfTokeinzer.nextToken();
                        subFieldsList.add(sf);
                    } else {
                        subFieldsList.add(df);
                    }
                }
            } else {
                dataField = subFieldsTokenizer.nextToken();
            }

            dataFieldMap.put(dataField, subFieldsList);
        }


        for (Iterator<String> iterator = dataFieldMap.keySet().iterator(); iterator.hasNext(); ) {
            String dataFieldString = iterator.next();
            DataField marcDataField = (DataField) record.getVariableField(dataFieldString);
            if (null != marcDataField) {
                List<String> subFields = dataFieldMap.get(dataFieldString);
                if (!subFields.isEmpty()) {
                    String valuesFromIncludePath = resolveSubFieldValuesFromIncludePath(marcDataField, subFields);
                    if (null != valuesFromIncludePath) {
                        indexedValue.append(valuesFromIncludePath).append(",");
                    }
                } else {
                    String resolveAllSubfieldValues = resolveAllSubfieldValues(marcDataField, excludePath);
                    if(null != resolveAllSubfieldValues){
                        indexedValue.append(resolveAllSubfieldValues);
                    }
                }
            }
        }

        return indexedValue.toString();
    }

    private String resolveAllSubfieldValues(DataField marcDataField, String excludePath) {
        List<Subfield> subfields = marcDataField.getSubfields();
        for (Iterator<Subfield> subfieldIterator = subfields.iterator(); subfieldIterator.hasNext(); ) {
            Subfield subfield = subfieldIterator.next();
            return subfield.getData();

        }
        return null;
    }

    private String resolveSubFieldValuesFromIncludePath(DataField marcDataField, List<String> subFields) {
        for (Iterator<String> stringIterator = subFields.iterator(); stringIterator.hasNext(); ) {
            String subFieldString = stringIterator.next();
            Subfield subfield = marcDataField.getSubfield(subFieldString.toCharArray()[0]);
            if (null != subfield) {
                String data = subfield.getData();
               return data;
            }
        }
        return null;
    }
}
