package org.kuali.ole.dao;

import org.kuali.ole.model.OleDsDocFieldT;

import java.util.List;

/**
 * Created by pvsubrah on 10/22/15.
 */
public interface SearchConfigurationDocFieldDao  {
    void save(OleDsDocFieldT oleDsDocFieldT);
    void update(OleDsDocFieldT oleDsDocFieldT);
    void delete(OleDsDocFieldT oleDsDocFieldT);
    OleDsDocFieldT findByPrimaryKey(String key);
    List findAll();
}
