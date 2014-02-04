package com.whiteleys.zoo.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.whiteleys.zoo.domain.Sex;
import com.whiteleys.zoo.domain.User;

public class UserRegistrationValidator implements Validator {
	
	private static String UK_POSTCODE_REGEX = "(?i)(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)" +
												"|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)" +
												"|(([A-Z-[QVX]][0-9][A-HJKSTUW])" +
												"|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) *[0-9][A-Z-[CIKMOV]]{2})";

	public boolean supports(Class clazz) {
		return clazz.equals(User.class);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		validatePasswordsMatch(user.getPassword(), user.getPassword2(), errors);
		validatePostCode(user.getPostcode(), errors);
			
	}
	
	protected void validateSex(Sex sex, Errors errors) {
		if (sex == null) {
			errors.reject("", "Sex must be M or F.");
		}
	}
	
	protected void validatePasswordsMatch(String password1, String password2, Errors errors) {
		if (!password1.equals(password2)) {
			errors.reject("", "Both passwords must be the same.");
		}
	}
	
	protected void validatePostCode(String postCode, Errors errors) {
		if (postCode == null
			|| !postCode.matches(UK_POSTCODE_REGEX)) {
			errors.reject("", "Not a valid UK post code.");
		}
		
	}

}
