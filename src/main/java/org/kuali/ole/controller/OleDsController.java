package org.kuali.ole.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.kuali.ole.executors.SolrIndexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by sheiksalahudeenm on 10/25/15.
 */
@Controller
@RequestMapping("/index")
public class OleDsController {

    @Autowired
    private SolrIndexer solrIndexer;

    /* Rest POST Methods Start*/

    @RequestMapping(method = RequestMethod.POST, value = "/fullReIndex", produces = {MediaType.APPLICATION_JSON})
    @ResponseBody
    public String fullReIndex(@RequestBody String body) throws Exception {
        JSONObject responseJsonObject = new JSONObject();
        JSONObject jsonObject = new JSONObject(body);
        int numberOfThread = getIntegerValueFromJsonObject(jsonObject, "numberOfThread");
        int batchSize = getIntegerValueFromJsonObject(jsonObject, "batchSize");
        if(numberOfThread > 0 && batchSize > 0){
            boolean fullIndex = solrIndexer.fullIndex(numberOfThread, batchSize);
        } else{
            responseJsonObject.put("response","Invalid batch size or number of thread");
        }

        return responseJsonObject.toString();
    }

    public int getIntegerValueFromJsonObject(JSONObject jsonObject, String key) throws JSONException {
        return jsonObject.getInt(key);

    }
}
