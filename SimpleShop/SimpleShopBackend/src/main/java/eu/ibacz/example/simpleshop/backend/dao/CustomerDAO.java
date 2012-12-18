/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dao;

import eu.ibacz.example.simpleshop.backend.entity.Customer;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rastislav Papp
 */
@Repository
public class CustomerDAO extends GenericDAO<Customer> {

    public Customer getCustomerByUserId(String userId) {
        TypedQuery<Customer> q = entityManager.createQuery("SELECT c FROM Customer c WHERE c.userId = :userId", 
                Customer.class).setParameter("userId", userId);
        List<Customer> results = q.getResultList();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
    
}
