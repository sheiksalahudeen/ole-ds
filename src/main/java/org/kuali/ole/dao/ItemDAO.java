package org.kuali.ole.dao;

import org.kuali.ole.model.OleDsItemT;

import java.util.List;

/**
 * Created by senthilkumars on 4/21/16.
 */
public interface ItemDAO {
    void save(OleDsItemT oleDsItemT);
    void update(OleDsItemT oleDsItemT);
    void delete(OleDsItemT oleDsItemT);
    OleDsItemT findByPrimaryKey(Integer id);
    List findAll();
    List findAllByLimit(int startIndex, int maxResult,boolean orderBy,String columnName, String orderType);
    Long totalCount();
}
