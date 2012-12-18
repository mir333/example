/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */ 

package eu.ibacz.example.simpleshop.backend.service.impl;

import eu.ibacz.example.simpleshop.backend.dao.BasketDAO;
import eu.ibacz.example.simpleshop.backend.dao.ProductDAO;
import eu.ibacz.example.simpleshop.backend.dto.BasketDTO;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;
import eu.ibacz.example.simpleshop.backend.entity.Basket;
import eu.ibacz.example.simpleshop.backend.entity.BasketItem;
import eu.ibacz.example.simpleshop.backend.service.BasketService;
import java.util.ArrayList;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rastislav Papp
 */
@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    @Autowired 
    private BasketDAO dao;
    @Autowired
    private ProductDAO productDao;
    
    public BasketDTO getBasket(String userId) {
        Validate.notEmpty(userId);
        
        Basket entity = getBasketEntity(userId);
        return new BasketDTO(userId, ConversionUtils.basketItemDTOs(entity.getItems()));
    }

    public void addProductToBasket(ProductDTO product, String userId) {
        Validate.notNull(product);
        Validate.notEmpty(userId);
        
        Basket b = getBasketEntity(userId);
        BasketItem item = findBasketItem(b, product.getId());
        if (item != null) {
            item.setAmount(item.getAmount() + 1);
        } else {
            item = new BasketItem();
            item.setAmount(1);
            item.setProduct(productDao.findById(product.getId()));
            b.getItems().add(item);
        }
        dao.update(b);
    }

    public void removeAllProducts(String userId) {
        Validate.notEmpty(userId);
        
        Basket b = getBasketEntity(userId);
        b.setItems(new ArrayList<BasketItem>());
        dao.update(b);
    }

    public void removeProduct(ProductDTO product, String userId) {
        Validate.notNull(product);
        Validate.notEmpty(userId);
        
        Basket b = getBasketEntity(userId);
        BasketItem item = findBasketItem(b, product.getId());
        Validate.notNull(item);
        
        b.getItems().remove(item);
        dao.update(b);
    }
    
    private Basket getBasketEntity(String userId) throws IllegalArgumentException {
        if (userId == null || userId.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        Basket entity = dao.findBasketByUserId(userId);
        if (entity == null) {
            entity = new Basket();
            entity.setUserId(userId);
            entity.setItems(new ArrayList<BasketItem>());
            dao.insert(entity);
        }
        return entity;
    }

    private BasketItem findBasketItem(Basket b, long itemId) {
        for (BasketItem item : b.getItems()) {
            if (item.getProduct().getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

}
