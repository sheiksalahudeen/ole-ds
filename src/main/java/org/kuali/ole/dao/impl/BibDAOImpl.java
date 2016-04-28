package org.kuali.ole.dao.impl;

import org.kuali.ole.dao.BibDAO;
import org.kuali.ole.datasource.AbstractJpaDAO;
import org.kuali.ole.model.OleDsBibT;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pvsubrah on 10/23/15.
 */
@Repository("bibDAO")
@Scope("prototype")
public class BibDAOImpl extends AbstractJpaDAO implements BibDAO {

    public BibDAOImpl() {
        setClazz(OleDsBibT.class);
    }

    public void save(OleDsBibT oleDsBibT) {
        super.create(oleDsBibT);
    }

    public void update(OleDsBibT oleDsBibT) {

    }

    public void delete(OleDsBibT oleDsBibT) {

    }

    public OleDsBibT findByPrimaryKey(Integer id) {
        return (OleDsBibT) super.findOne(id);
    }

    public List findAll() {
        return super.findAll();
    }

    public List findAllByLimit(int startIndex, int maxResult, boolean orderBy, String columnName,String orderType){
        return super.findAllByLimit(startIndex,maxResult,orderBy,columnName,orderType);
    }

    public Long totalCount() {
        return super.totalCount();
    }
}
