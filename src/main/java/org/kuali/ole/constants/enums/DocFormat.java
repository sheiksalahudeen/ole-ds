package org.kuali.ole.constants.enums;

/**
 * Created by sheiksalahudeenm on 10/29/15.
 */
public enum DocFormat {
    MARC("marc", "marc"),
    DUBLIN_CORE("dublin", "dublin_core"),
    DUBLIN_UNQUALIFIED("dublinunq", "dublin_unqualified"),
    OLEML("oleml", "oleml"),
    PDF("pdf", "pdf"),
    DOC("doc", "doc"),
    XSLT("xslt", "xslt"),
    ONIXPL("onixpl", "onixpl");

    private final String code;
    private final String description;

    private DocFormat(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEqualTo(String input) {
        boolean result = false;
        if (null == input) {
            result = false;
        } else if (input.equalsIgnoreCase(getCode())) {
            result = true;
        } else if (input.equalsIgnoreCase(getDescription())) {
            result = true;
        }
        return result;
    }

}
