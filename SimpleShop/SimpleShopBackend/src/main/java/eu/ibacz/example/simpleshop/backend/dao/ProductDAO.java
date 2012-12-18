package eu.ibacz.example.simpleshop.backend.dao;

import eu.ibacz.example.simpleshop.backend.entity.Product;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author petr
 */
@Repository
public class ProductDAO extends GenericDAO<Product> {

    public List<Product> findProductsByName(String name) {
        Query q = entityManager.createNamedQuery(Product.FIND_BY_NAME);
        q.setParameter("name", "%"+name+"%");
        return q.getResultList();
    }

}
