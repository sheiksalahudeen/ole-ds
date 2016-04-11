package org.kuali.ole.bo;

import org.kuali.ole.dao.SearchConfigurationDocFieldDao;
import org.kuali.ole.model.OleDsDocFieldT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pvsubrah on 10/22/15.
 */

@Service("searchConfigurationDocFieldBo")
@Scope("prototype")
public class SearchConfigurationDocFieldBo {
    @Autowired
    SearchConfigurationDocFieldDao searchConfigurationDocFieldDao;
    private List cachedSearchFieldConfigurations;
    private List cachedBibSearchFieldConfigurations;
    private List cachedHoldingsSearchFieldConfigurations;
    private List cachedItemsSearchFieldConfigurations;


    public List findAll() {
        return searchConfigurationDocFieldDao.findAll();
    }

    public List getCachedSearchConfigurations() {
        if (null == cachedSearchFieldConfigurations) {
            cachedSearchFieldConfigurations = searchConfigurationDocFieldDao.findAll();

            cachedBibSearchFieldConfigurations = new ArrayList();
            cachedHoldingsSearchFieldConfigurations = new ArrayList();
            cachedItemsSearchFieldConfigurations = new ArrayList();

            for (Iterator<OleDsDocFieldT> iterator = cachedSearchFieldConfigurations.iterator(); iterator.hasNext(); ) {
                OleDsDocFieldT dsDocFieldT = iterator.next();
                if (dsDocFieldT.getOleDsDocTypeT().getName().equalsIgnoreCase("bibliographic")) {
                    cachedBibSearchFieldConfigurations.add(dsDocFieldT);
                } else if (dsDocFieldT.getOleDsDocTypeT().getName().equalsIgnoreCase("holdings")) {
                    cachedHoldingsSearchFieldConfigurations.add(dsDocFieldT);
                } else if (dsDocFieldT.getOleDsDocTypeT().getName().equalsIgnoreCase("item")) {
                    cachedItemsSearchFieldConfigurations.add(dsDocFieldT);
                }
            }
        }

        return cachedSearchFieldConfigurations;
    }

    public List getCachedBibSearchFieldConfigurations() {
        getCachedSearchConfigurations();
        return cachedBibSearchFieldConfigurations;
    }

    public List getCachedHoldingsSearchFieldConfigurations() {
        getCachedSearchConfigurations();
        return cachedHoldingsSearchFieldConfigurations;
    }

    public List getCachedItemsSearchFieldConfigurations() {
        getCachedSearchConfigurations();
        return cachedItemsSearchFieldConfigurations;
    }
}
