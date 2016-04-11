package org.kuali.ole.dao.impl;

import org.kuali.ole.dao.SearchConfigurationDocFieldDao;
import org.kuali.ole.datasource.AbstractJpaDAO;
import org.kuali.ole.model.OleDsDocFieldT;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pvsubrah on 10/22/15.
 */


@Repository("searchConfigurationDocFieldDao")
@Scope("prototype")
public class SearchConfigurationDocFieldDaoImpl extends AbstractJpaDAO implements SearchConfigurationDocFieldDao {

    public SearchConfigurationDocFieldDaoImpl() {
        setClazz(OleDsDocFieldT.class);
    }

    public void save(OleDsDocFieldT oleDsDocFieldT) {

    }

    public void update(OleDsDocFieldT oleDsDocFieldT) {

    }

    public void delete(OleDsDocFieldT oleDsDocFieldT) {

    }

    public OleDsDocFieldT findByPrimaryKey(String key) {

        return null;
    }

    public List findAll() {
      return super.findAll();
    }
}
