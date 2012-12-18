/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.ibacz.example.simpleshop.backend.service.impl;

import eu.ibacz.example.simpleshop.backend.service.CatalogService;
import eu.ibacz.example.simpleshop.backend.dao.ProductDAO;
import eu.ibacz.example.simpleshop.backend.entity.Product;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author petr
 */
@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional(readOnly=true)
    public List<ProductDTO> getAllProducts() {
        return ConversionUtils.productDTOs(productDAO.getAll());
    }

    @Transactional(readOnly=true)
    public List<ProductDTO> findProducts(String query) {
        return ConversionUtils.productDTOs(productDAO.findProductsByName(query));
    }

    @Transactional(readOnly=true)
    public ProductDTO getProductById(long id) {
        Product p = productDAO.findById(id);
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(p, dto);
        return dto;
    }

}
