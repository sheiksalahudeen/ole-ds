package org.kuali.ole.dao;

import org.kuali.ole.model.OleDsBibT;

import java.util.List;

/**
 * Created by pvsubrah on 10/23/15.
 */
public interface BibDAO {
    void save(OleDsBibT oleDsBibT);
    void update(OleDsBibT oleDsBibT);
    void delete(OleDsBibT oleDsBibT);
    OleDsBibT findByPrimaryKey(Integer id);
    List findAll();
    List findAllByLimit(int startIndex, int maxResult,boolean orderBy,String columnName, String orderType);
    Long totalCount();
}
