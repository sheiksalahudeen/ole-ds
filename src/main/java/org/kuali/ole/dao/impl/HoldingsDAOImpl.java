package org.kuali.ole.dao.impl;

import org.kuali.ole.dao.HoldingsDAO;
import org.kuali.ole.datasource.AbstractJpaDAO;
import org.kuali.ole.model.OleDsHoldingsT;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by senthilkumars on 4/21/16.
 */
@Repository("holdingsDAO")
@Scope("prototype")
public class HoldingsDAOImpl extends AbstractJpaDAO implements HoldingsDAO {

    public HoldingsDAOImpl() {
        setClazz(OleDsHoldingsT.class);
    }

    public void save(OleDsHoldingsT oleDsHoldingsT) {

    }

    public void update(OleDsHoldingsT oleDsHoldingsT) {

    }

    public void delete(OleDsHoldingsT oleDsHoldingsT) {

    }

    public OleDsHoldingsT findByPrimaryKey(Integer id) {
        return (OleDsHoldingsT) super.findOne(id);
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
