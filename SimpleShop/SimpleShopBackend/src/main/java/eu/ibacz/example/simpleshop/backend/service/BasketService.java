/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.service;

import eu.ibacz.example.simpleshop.backend.dto.BasketDTO;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;

/**
 *
 * @author Rastislav Papp
 */
public interface BasketService {
    
    BasketDTO getBasket(String userId);
    
    void addProductToBasket(ProductDTO product, String userId);
        
    void removeProduct(ProductDTO product, String userId);
    
    void removeAllProducts(String userId);
    
}
