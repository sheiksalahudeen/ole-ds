package org.kuali.ole.dao;

import org.kuali.ole.model.OleDsHoldingsT;

import java.util.List;

/**
 * Created by senthilkumars on 4/21/16.
 */
public interface HoldingsDAO {
    void save(OleDsHoldingsT oleDsHoldingsT);
    void update(OleDsHoldingsT oleDsHoldingsT);
    void delete(OleDsHoldingsT oleDsHoldingsT);
    OleDsHoldingsT findByPrimaryKey(Integer id);
    List findAll();
    List findAllByLimit(int startIndex, int maxResult,boolean orderBy,String columnName, String orderType);
    Long totalCount();
}
