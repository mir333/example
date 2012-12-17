/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.pv230.simpleshop.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author petr
 */
@Service
@Transactional
public class CatalogImpl implements Catalog {

    @Autowired
    private ProductDAO productDAO;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productListToDTOList(productDAO.getAllProducts());
    }

    private List<ProductDTO> productListToDTOList(List<Product> entityList) throws BeansException {
        List<ProductDTO> result = new ArrayList();
        for (Product p : entityList) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p, productDTO);
            result.add(productDTO);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findProducts(String query) {
        return productListToDTOList(productDAO.findProductsByName(query));
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(long id) {
        Product p = productDAO.getProduct(id);
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(p, dto);
        return dto;
    }


}
