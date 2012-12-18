/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2009 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */

package eu.ibacz.example.simpleshop.portlet.basket;


import eu.ibacz.example.simpleshop.backend.dto.CustomerDTO;
import eu.ibacz.example.simpleshop.backend.service.OrderingService;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static eu.ibacz.example.simpleshop.portlet.basket.BasketConstants.*;

/**
 * @author Rastislav Papp
 */
@Controller
@RequestMapping(value = "VIEW", params = PARAM_CONTROLLER + "=" + CONTROLLER_ORDER)
public class OrderViewController {

    private static final Logger LOG = Logger.getLogger(OrderViewController.class);
    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private OrderingService orderingService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        LOG.debug("Initializing bindings for Date fields");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(sdf, false));
    }

    @RenderMapping
    public String renderDefault(RenderRequest request, Model model) {
        LOG.info("Order view default rendered.");
        if (!model.containsAttribute(ATTRIBUTE_CUSTOMER)) {
            LOG.debug("Customer model attribute is not in model. Inserting empty customer.");
            String userId = request.getRemoteUser();
            CustomerDTO customer = orderingService.getCustomer(userId);
            model.addAttribute(ATTRIBUTE_CUSTOMER, customer);
        }
        return JSP_VIEW_ORDER;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PAGE_ORDER_SUCCESS)
    public String renderSuccess() {
        LogMF.info(LOG, "Order view {0} rendered.", PAGE_ORDER_SUCCESS);
        return JSP_VIEW_ORDER_SUCCESS;
    }

    @ActionMapping(ACTION_ORDER)
    public void sendOrderAction(@ModelAttribute(ATTRIBUTE_CUSTOMER) CustomerDTO customer,
                          BindingResult result,
                          Model model,
                          ActionResponse response) {
        LogMF.info(LOG, "Sending order for customer {0}.", customer.getUserId());
        customerValidator.validate(customer, result);
        if (!result.hasErrors()) {
            LogMF.debug(LOG, "Customer {0} successfully ordered products.", customer.getUserId());
            orderingService.order(customer);
            model.asMap().remove(ATTRIBUTE_CUSTOMER);
            response.setRenderParameter(PARAM_PAGE, PAGE_ORDER_SUCCESS);
        }else {
            LogMF.info(LOG, "Customer data are wrong: {0}.", customer);
        }
        response.setRenderParameter(PARAM_CONTROLLER, CONTROLLER_ORDER);
    }

}
