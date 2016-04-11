package org.kuali.ole.util.callnumber;

import org.solrmarc.callnum.CallNumber;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sheiksalahudeenm on 10/29/15.
 */
public class CallNumberFactory {
    private static CallNumberFactory callNumberFactory = new CallNumberFactory();
    private Map<String, CallNumber> callNumberMap = new HashMap<String, CallNumber>();

    public static CallNumberFactory getInstance() {
        return callNumberFactory;
    }

    private CallNumberFactory() {
        initCallNumberMap();
    }

    private void initCallNumberMap() {
        String key = CallNumberType.LCC.getCode();
        callNumberMap.put(key, LcCallNumber.getInstance());

        key = CallNumberType.DDC.getCode();
        callNumberMap.put(key, DDCallNumber.getInstance());

        key = CallNumberType.NLM.getCode();
        callNumberMap.put(key, NLMCallNumber.getInstance());

        key = CallNumberType.SuDoc.getCode();
        callNumberMap.put(key, SuDocCallNumber.getInstance());

        key = CallNumberType.FOUR.getCode();
        callNumberMap.put(key, OtherCallNumber.getInstance());

        key = CallNumberType.FIVE.getCode();
        callNumberMap.put(key, OtherCallNumber.getInstance());

        key = CallNumberType.SIX.getCode();
        callNumberMap.put(key, OtherCallNumber.getInstance());

        key = CallNumberType.SEVEN.getCode();
        callNumberMap.put(key, OtherCallNumber.getInstance());

        key = CallNumberType.EIGHT.getCode();
        callNumberMap.put(key, OtherCallNumber.getInstance());

    }


    public CallNumber getCallNumber(String callNumberType) {
        if (callNumberMap.get(callNumberType) == null) {
            return OtherCallNumber.getInstance();
        }
        return callNumberMap.get(callNumberType);
    }
}

