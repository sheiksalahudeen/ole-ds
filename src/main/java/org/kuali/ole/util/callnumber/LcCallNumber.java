package org.kuali.ole.util.callnumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solrmarc.callnum.LCCallNumber;

/**
 * Created by sheiksalahudeenm on 10/29/15.
 */
public class LcCallNumber extends LCCallNumber {
    private static final Logger Log = LoggerFactory.getLogger(LcCallNumber.class);
    private static LcCallNumber ourInstance = null;

    public static LcCallNumber getInstance() {
        if (null == ourInstance) {
            ourInstance = new LcCallNumber();
        }
        return ourInstance;
    }


    @Override
    public void parse(String call) {
        try {
            super.parse(call);
        } catch (Exception e) {
            Log.error("LC Call Number Exception" + e);
        }
    }
}

