package org.kuali.ole.datasource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pvsubrah on 10/22/15.
 */
public class AbstractJpaDAO <T extends Serializable> {

    private Class< T > clazz;

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    public final void setClazz( Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public T findOne( Integer id ){
        return entityManager.find( clazz, id );
    }

    public List< T > findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public List< T > findAllByLimit(int startIndex, int maxResult,boolean orderBy,String columnName, String orderType){
        List resultList = entityManager.createQuery("from " + clazz.getName() + (orderBy ? " ORDER BY " + columnName + " " + orderType + " " : ""))
                .setFirstResult(startIndex)
                .setMaxResults(maxResult)
                .getResultList();
        return resultList;
    }

    public void create( T entity ){
        entityManager.persist( entity );
    }

    public T update( T entity ){
        return entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }

    public void deleteById( Integer entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }

    public Long totalCount() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery(clazz);
        cq.select(qb.count(cq.from(clazz)));
//        cq.where(/*your stuff*/);
        Object singleResult = entityManager.createQuery(cq).getSingleResult();
        return (Long) singleResult;
    }
}
