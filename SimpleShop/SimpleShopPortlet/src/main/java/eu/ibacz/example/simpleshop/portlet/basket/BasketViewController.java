/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */

package eu.ibacz.example.simpleshop.portlet.basket;

import eu.ibacz.example.simpleshop.backend.dto.BasketDTO;
import eu.ibacz.example.simpleshop.backend.dto.ProductDTO;
import eu.ibacz.example.simpleshop.backend.service.BasketService;
import eu.ibacz.example.simpleshop.backend.service.CatalogService;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.EventMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.*;

import static eu.ibacz.example.simpleshop.portlet.basket.BasketConstants.*;


/**
 * @author Rastislav Papp
 */
@Controller
@RequestMapping("VIEW")
public class BasketViewController {

    private static final Logger LOG = Logger.getLogger(BasketViewController.class);

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private BasketService basketService;


    @RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.info("Basket view default rendered.");
        BasketDTO basket = basketService.getBasket(request.getRemoteUser());
        LogMF.debug(LOG, "Showing basked {0}", basket);
        model.addAttribute(ATTRIBUTE_BASKET, basket);
        return JSP_VIEW;
    }

    @EventMapping(EVENT_BUY_PRODUCT)
    public void eventBuyProduct(EventRequest request) {
        LOG.info("Handling buy product event.");
        ProductDTO product = (ProductDTO) request.getEvent().getValue();
        LogMF.debug(LOG, "Event payload {0}", product);
        basketService.addProductToBasket(product, request.getRemoteUser());
    }


    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_BASKET_MAXIMIZED)
    public String renderBasketMaximized(RenderRequest request, Model model) {
        LogMF.info(LOG, "Basket view {0} rendered.", PAGE_BASKET_MAXIMIZED);
        BasketDTO basket = basketService.getBasket(request.getRemoteUser());
        LogMF.debug(LOG, "Showing basked {0}", basket);
        model.addAttribute(ATTRIBUTE_BASKET, basket);
        if (request.getWindowState().equals(WindowState.MAXIMIZED)) {
            return JSP_VIEW_MAXIMIZED;
        }
        return JSP_VIEW;
    }

    @ActionMapping(DELETE_ITEM)
    public void deleteItem(@RequestParam(PRODUCT_ID) Long productId,
                           ActionRequest request,
                           ActionResponse response) {
        LogMF.info(LOG, "Deleting product with id {0}.", productId);
        ProductDTO dto = catalogService.getProductById(productId);
        String userId = request.getRemoteUser();
        basketService.removeProduct(dto, userId);
        response.setRenderParameter(PARAM_PAGE, PAGE_BASKET_MAXIMIZED);
    }
}
