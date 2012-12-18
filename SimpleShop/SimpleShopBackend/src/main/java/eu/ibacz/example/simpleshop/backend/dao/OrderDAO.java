/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dao;

import eu.ibacz.example.simpleshop.backend.entity.Order;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rastislav Papp
 */
@Repository
public class OrderDAO extends GenericDAO<Order> {

    public List<Order> findByUserId(String userId) {
        TypedQuery<Order> q = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.userId = :userId", 
                Order.class).setParameter("userId", userId);
        return q.getResultList();
    }

}
