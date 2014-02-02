package com.whiteleys.zoo.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.whiteleys.zoo.domain.User;

public class UserRegistrationValidator implements Validator {

	public boolean supports(Class clazz) {
		return clazz.equals(User.class);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		validatePasswordsMatch(user.getPassword(), user.getPassword2(), errors);
		validatePostCode(user.getPostcode(), errors);
			
	}
	
	private void validatePasswordsMatch(String password1, String password2, Errors errors) {
		if (!password1.equals(password2)) {
			errors.reject("", "Both passwords must be the same.");
		}
	}
	
	private void validatePostCode(String postCode, Errors errors) {
		
	}

}
