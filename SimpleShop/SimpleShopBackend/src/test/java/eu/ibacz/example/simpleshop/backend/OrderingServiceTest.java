/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend;

import eu.ibacz.example.simpleshop.backend.dto.AddressDTO;
import eu.ibacz.example.simpleshop.backend.dto.BasketDTO;
import eu.ibacz.example.simpleshop.backend.service.BasketService;
import eu.ibacz.example.simpleshop.backend.dto.CustomerDTO;
import eu.ibacz.example.simpleshop.backend.dto.OrderDTO;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;
import eu.ibacz.example.simpleshop.backend.service.CatalogService;
import eu.ibacz.example.simpleshop.backend.service.OrderingService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
/**
 *
 * @author Rastislav Papp
 */
public class OrderingServiceTest extends AbstractTest {
    public static final String TEST_CUSTOMER_1 = "customer1";
    public static final String TEST_CUSTOMER_2 = "customer2";
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private OrderingService orderingService;
    @Autowired
    private BasketService basketService;
    
    @Test
    public void testGetCustomer() {
        try {
            orderingService.getCustomer("");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        CustomerDTO c1 = orderingService.getCustomer(TEST_CUSTOMER_1);
        assertNotNull(c1);
        assertNotNull(c1.getUserId());
        assertEquals(TEST_CUSTOMER_1, c1.getUserId());
        CustomerDTO c2 = orderingService.getCustomer(TEST_CUSTOMER_2);
        assertNotSame(c1, c2);
        assertEquals(c1, orderingService.getCustomer(TEST_CUSTOMER_1));
    }
       
    @Test
    public void testOrder() {
        try {
            orderingService.order(orderingService.getCustomer(TEST_CUSTOMER_1));
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        for (ProductDTO productDTO : catalogService.getAllProducts()) {
            basketService.addProductToBasket(productDTO, TEST_CUSTOMER_1);
        }
        
        BasketDTO basket = basketService.getBasket(TEST_CUSTOMER_1);
        assertEquals(DemoData.getStartingProductCount(), basket.getItemCount());
        
        OrderDTO order = orderingService.order(getTestCustomer());
        assertEquals(orderingService.getCustomer(TEST_CUSTOMER_1), order.getCustomer());
        assertEquals(DemoData.getStartingProductCount(), order.getItems().size());
    }
    
    public CustomerDTO getTestCustomer() {
        AddressDTO address = new AddressDTO();
        address.setCity(TEST_CUSTOMER_1);
        address.setStreet(TEST_CUSTOMER_1);
        address.setZipCode(TEST_CUSTOMER_1);
        
        CustomerDTO c = new CustomerDTO();
        c.setUserId(TEST_CUSTOMER_1);
        c.setEmail(TEST_CUSTOMER_1);
        c.setFirstName(TEST_CUSTOMER_1);
        c.setLastName(TEST_CUSTOMER_1);
        c.setAddress(address);
        
        return c;
    }

}
