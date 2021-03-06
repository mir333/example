/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.example.simpleshop.backend.service;

import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;
import java.util.List;

/**
 *
 * @author petr
 */
public interface CatalogService {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> findProducts(String query);

    ProductDTO getProductById(long id);

}
