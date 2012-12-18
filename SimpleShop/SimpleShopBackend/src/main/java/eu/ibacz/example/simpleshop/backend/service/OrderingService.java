/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.service;

import eu.ibacz.example.simpleshop.backend.dto.CustomerDTO;
import eu.ibacz.example.simpleshop.backend.dto.OrderDTO;
import java.util.List;

/**
 *
 * @author Rastislav Papp
 */
public interface OrderingService {

    CustomerDTO getCustomer(String userId);
    OrderDTO order(CustomerDTO customer);
    List<OrderDTO> getOrders(String userId);
    List<OrderDTO> getAllOrders();
    
}
