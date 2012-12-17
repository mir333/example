/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.pv230.simpleshop.backend;

import java.util.List;

/**
 * @author petr
 */
public interface Catalog {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> findProducts(String query);

    ProductDTO getProductById(long id);

}
