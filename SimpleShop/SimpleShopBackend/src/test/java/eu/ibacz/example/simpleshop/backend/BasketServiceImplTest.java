/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend;

import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;
import eu.ibacz.example.simpleshop.backend.service.CatalogService;
import eu.ibacz.example.simpleshop.backend.entity.Product;
import eu.ibacz.example.simpleshop.backend.dto.BasketDTO;
import eu.ibacz.example.simpleshop.backend.dto.BasketItemDTO;
import eu.ibacz.example.simpleshop.backend.service.BasketService;
import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 *
 * @author Rastislav Papp
 */
public class BasketServiceImplTest extends AbstractTest {

    @Autowired
    private BasketService basketService;
    @Autowired
    private CatalogService catalogService;
    
    public static final String TEST_USER_ID = "testUserId";
    public static final String TEST_PRODUCT_NAME = "testProductName";
    
    @Test
    public void testGetBasket() {
        BasketDTO b = basketService.getBasket(TEST_USER_ID);
        assertNotNull(b);
        assertEquals(TEST_USER_ID, b.getUserId());
        assertNotNull(b.getItems());
        assertEquals(0, b.getItems().size());
    }
    
    @Test
    public void testAddProduct() {
        Product pe = insertTestProduct();
        
        ProductDTO p = catalogService.getProductById(pe.getId());
        assertNotNull(pe.getId());
        assertEquals(pe.getId(), p.getId());
        
        basketService.addProductToBasket(p, TEST_USER_ID);
        BasketDTO b = basketService.getBasket(TEST_USER_ID);
        assertNotNull(b);
        assertEquals(1, b.getItems().size());
        BasketItemDTO item = b.getItems().get(0);
        assertEquals(1, item.getAmount());
        assertNotNull(item.getProduct());
        assertEquals(pe.getId(), item.getProduct().getId());
        assertEquals(pe.getName(), item.getProduct().getName());
        
        basketService.addProductToBasket(p, TEST_USER_ID);
        b = basketService.getBasket(TEST_USER_ID);
        assertEquals(1, b.getItems().size());
        item = b.getItems().get(0);
        assertEquals(2, item.getAmount());
    }

    @Test   
    public void testRemoveProduct() {
        Product pe = insertTestProduct();
        ProductDTO p = catalogService.getProductById(pe.getId());
        
        try {
            basketService.removeProduct(p, TEST_USER_ID);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
        }
        
        basketService.addProductToBasket(p, TEST_USER_ID);
        basketService.removeProduct(p, TEST_USER_ID);
        BasketDTO b = basketService.getBasket(TEST_USER_ID);
        assertEquals(0, b.getItems().size());
        
        try {
            basketService.removeProduct(p, TEST_USER_ID);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
        }
    }
    
    @Test
    public void testRemoveAllProducts() {
        Product pe1 = insertTestProduct();
        Product pe2 = insertTestProduct();
        
        ProductDTO p1 = catalogService.getProductById(pe1.getId());
        ProductDTO p2 = catalogService.getProductById(pe2.getId());
        
        basketService.addProductToBasket(p1, TEST_USER_ID);
        basketService.addProductToBasket(p2, TEST_USER_ID);
        BasketDTO b = basketService.getBasket(TEST_USER_ID);
        assertEquals(2, b.getItems().size());
        
        basketService.removeAllProducts(TEST_USER_ID);
        b = basketService.getBasket(TEST_USER_ID);
        assertEquals(0, b.getItems().size());
        
        basketService.removeAllProducts(TEST_USER_ID);
    }

    private Product insertTestProduct() {
        Product pe = new Product();
        pe.setName(TEST_PRODUCT_NAME);
        pe.setPrice(BigDecimal.TEN);
        productDAO.insert(pe);
        return pe;
    }
    
}
