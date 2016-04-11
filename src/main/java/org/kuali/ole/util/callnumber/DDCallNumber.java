package org.kuali.ole.util.callnumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solrmarc.callnum.DeweyCallNumber;

/**
 * Created by sheiksalahudeenm on 10/29/15.
 */
public class DDCallNumber extends DeweyCallNumber {
    private static final Logger Log = LoggerFactory.getLogger(DDCallNumber.class);
    private static DDCallNumber ourInstance = null;

    public static DDCallNumber getInstance() {
        if (null == ourInstance) {
            ourInstance = new DDCallNumber();
        }
        return ourInstance;
    }

    @Override
    public void parse(String call) {
        try {
            super.parse(call);
        } catch (Exception e) {
            Log.error("DD Call Number Exception" + e);
        }
    }
}
