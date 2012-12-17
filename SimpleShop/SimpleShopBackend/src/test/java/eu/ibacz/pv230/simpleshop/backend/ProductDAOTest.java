
package eu.ibacz.pv230.simpleshop.backend;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Miroslav Ligas
 */
public class ProductDAOTest extends AbstractDaoTest {

    @Test
    public void insertProduct() {
        Product product = new Product();
        product.setName("Test name");
        product.setDescription("Test desc");
        product.setPrice(new BigDecimal(100));
        productDAO.insertProduct(product);
        Product retrieved = productDAO.getProduct(product.getId());
        assertEquals(product.getName(), retrieved.getName());
        assertEquals(product.getDescription(), retrieved.getDescription());
        assertEquals(product.getPrice(), retrieved.getPrice());
    }

    @Test
    public void getAllProducts() {
        List<Product> all = productDAO.getAllProducts();
        assertEquals(2, all.size());
    }

    @Test
    public void findProductByNameRyzlink() {
        List<Product> all = productDAO.findProductsByName("Ryzlink");
        assertEquals(2, all.size());
    }

    @Test
    public void findProductByNameVlasky() {
        List<Product> all = productDAO.findProductsByName("Vl");
        assertEquals(1, all.size());
    }

}
