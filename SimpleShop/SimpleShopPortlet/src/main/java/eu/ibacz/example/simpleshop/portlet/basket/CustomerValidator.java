package eu.ibacz.example.simpleshop.portlet.basket;

import eu.ibacz.example.simpleshop.backend.dto.CustomerDTO;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Rastislav Papp
 */
@Component
public class CustomerValidator implements Validator {

    private static final Logger LOG = Logger.getLogger(CustomerValidator.class);
    
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CustomerDTO.class);
    }

    public void validate(Object target, Errors errors) {
        LogMF.debug(LOG,"Validating customer dto {0}",target);
        CustomerDTO customer = (CustomerDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "msg-err-field-required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "msg-err-field-required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "msg-err-field-required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.street", "msg-err-field-required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "msg-err-field-required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipCode", "msg-err-field-required");
        
        String email = customer.getEmail();
        if (!email.matches(".+@.+\\.(.*){2,3}")) {
            errors.rejectValue("email", "msg-err-email-invalid");
        }
    }

}
