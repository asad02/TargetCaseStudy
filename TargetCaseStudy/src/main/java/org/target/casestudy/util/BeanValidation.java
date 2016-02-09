/**
 * 
 */
package org.target.casestudy.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import org.target.casestudy.model.Product;

/**
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 */
public class BeanValidation {
	boolean isValid = false;
	/**
	 * This method will create a validatorFactory using validation.builddefaultvalidatorfactory.
	 * and check if then provided bean class has any exceptions. If there is any exceptions present
	 * then put into set and then retun as inValid.
	 * @param product
	 * @return either true or false.
	 * @throws ConstraintViolationException
	 * @throws ValidationException
	 */
	public boolean validateBean(Product product) throws ConstraintViolationException, ValidationException {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Product>> violations = validator.validate(product);
		
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		} else {
			isValid = true;
		}
		return isValid;
	}	
}
