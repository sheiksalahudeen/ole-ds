package org.kuali.ole.util.callnumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solrmarc.callnum.LCCallNumber;

/**
 * Created by sheiksalahudeenm on 10/29/15.
 */
public class NLMCallNumber extends LCCallNumber {
    private static final Logger Log = LoggerFactory.getLogger(NLMCallNumber.class);
    private static NLMCallNumber ourInstance = null;

    public static NLMCallNumber getInstance() {
        if (null == ourInstance) {
            ourInstance = new NLMCallNumber();
        }
        return ourInstance;
    }

    @Override
    public void parse(String call) {
        try {
            super.parse(call);
        } catch (Exception e) {
            Log.error("NLM Call Number Exception" + e);
        }
    }
}

