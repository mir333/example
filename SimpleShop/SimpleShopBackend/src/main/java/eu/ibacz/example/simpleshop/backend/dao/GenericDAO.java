/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dao;

import eu.ibacz.example.simpleshop.backend.entity.BaseEntity;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rastislav Papp
 */
public class GenericDAO<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager entityManager;
    
    private Class<T> entityClass;
    
    public GenericDAO() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }
    
    public void insert(T product) {
        entityManager.persist(product);
    }

    public void remove(T product) {
        entityManager.remove(entityManager.merge(product));
    }

    public void update(T product) {
        entityManager.merge(product);
    }

    public T findById(long id) throws EntityNotFoundException {
        T result = entityManager.find(entityClass, id);
        if (result == null) {
            throw new EntityNotFoundException();
        }
        return result;
    }
    
    public List<T> getAll() {
        TypedQuery<T> q = entityManager.createQuery("SELECT e FROM " + 
                entityClass.getSimpleName() + " e", entityClass);
        return q.getResultList();
    }
    
}
