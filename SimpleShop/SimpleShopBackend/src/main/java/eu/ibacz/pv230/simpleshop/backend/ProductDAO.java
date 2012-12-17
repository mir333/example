/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.pv230.simpleshop.backend;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * @author petr
 */
@Repository
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void insertProduct(Product product) {
        entityManager.persist(product);
    }

    public void removeProduct(Product product) {
        entityManager.remove(entityManager.merge(product));
    }

    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    public Product getProduct(long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> getAllProducts() {
        Query q = entityManager.createNamedQuery(Product.FIND_ALL);
        return q.getResultList();
    }

    public List<Product> findProductsByName(String name) {
        Query q = entityManager.createNamedQuery(Product.FIND_BY_NAME);
        q.setParameter("name", "%" + name + "%");
        return q.getResultList();
    }

}
