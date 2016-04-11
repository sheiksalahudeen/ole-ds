package org.kuali.ole;

import org.junit.Test;
import org.kuali.ole.bo.SearchConfigurationDocFieldBo;
import org.kuali.ole.dao.SearchConfigurationDocFieldDao;
import org.kuali.ole.model.OleDsDocFieldT;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pvsubrah on 10/22/15.
 */

public class OleApplicationContextTest extends BaseTestCase {

    @Autowired
    SearchConfigurationDocFieldDao searchConfigurationDocFieldDao;


    @Autowired
    SearchConfigurationDocFieldBo searchConfigurationDocFieldBo;


    @Test
    public void searchConfigurationDocFieldDao() throws Exception {

        assertNotNull(searchConfigurationDocFieldDao);

        List all = searchConfigurationDocFieldDao.findAll();

        for (Iterator iterator = all.iterator(); iterator.hasNext(); ) {
            OleDsDocFieldT next = (OleDsDocFieldT) iterator.next();
            System.out.println(next.getName());
        }

        assertNotNull(all);
    }


    @Test
    public void findCachedBibDocFeildSearchConfiguration() throws Exception {
        List cachedBibSearchFieldConfigurations = searchConfigurationDocFieldBo.getCachedBibSearchFieldConfigurations();
        assertTrue(!cachedBibSearchFieldConfigurations.isEmpty());
    }

    @Test
    public void findCachedHoldingsDocFeildSearchConfiguration() throws Exception {
        List cachedHoldingsSearchFieldConfigurations = searchConfigurationDocFieldBo.getCachedHoldingsSearchFieldConfigurations();
        assertTrue(!cachedHoldingsSearchFieldConfigurations.isEmpty());
    }

    @Test
    public void findCachedItemsDocFeildSearchConfiguration() throws Exception {
        List cachedItemsSearchFieldConfigurations = searchConfigurationDocFieldBo.getCachedItemsSearchFieldConfigurations();
        assertTrue(!cachedItemsSearchFieldConfigurations.isEmpty());
    }
}
