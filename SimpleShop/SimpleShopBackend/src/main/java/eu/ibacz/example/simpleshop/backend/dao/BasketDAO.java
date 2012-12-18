/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.dao;

import eu.ibacz.example.simpleshop.backend.entity.Basket;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rastislav Papp
 */
@Repository
public class BasketDAO extends GenericDAO<Basket> {

    public Basket findBasketByUserId(String userId) {
        TypedQuery<Basket> q = entityManager.createQuery("SELECT b FROM Basket b WHERE userId = :userId", Basket.class)
                .setParameter("userId", userId);
        List<Basket> results = q.getResultList();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
    
}
