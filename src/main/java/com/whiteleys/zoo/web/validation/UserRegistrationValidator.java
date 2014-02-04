package com.whiteleys.zoo.web.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.whiteleys.zoo.domain.Sex;
import com.whiteleys.zoo.domain.User;
import com.whiteleys.zoo.service.UserService;
import com.whiteleys.zoo.web.Globals;

public class UserRegistrationValidator implements Validator {
	
	// http://stackoverflow.com/questions/164979/uk-postcode-regex-comprehensive
	private static String UK_POSTCODE_REGEX = "(?i)(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)" +
												"|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)" +
												"|(([A-Z-[QVX]][0-9][A-HJKSTUW])" +
												"|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) *[0-9][A-Z-[CIKMOV]]{2})";
	
	private UserService userService;

	public boolean supports(Class clazz) {
		return clazz.equals(User.class);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		validateUsername(user.getUsername(), errors);
		validatePassword(user.getPassword(), user.getPassword2(), errors);
		validateSex(user.getSex(), errors);
		validatePostCode(user.getPostcode(), errors);
		validateAge(user.getDobYear(), user.getDobMonth(), user.getDobDay(), errors);
			
	}
	
	protected void validateUsername(String username, Errors errors) {
		if (userService.exists(username)) {
			errors.reject("", "Username is not available");
		}
	}
	
	protected void validatePassword(String password1, String password2, Errors errors) {
		if (password1 == null || password1.trim().length() == 0) {
			errors.reject("", "Password cannot be blank");
		} else if (!password1.equals(password2)) {
			errors.reject("", "Both passwords must be the same.");
		}
	}
	
	protected void validateSex(Sex sex, Errors errors) {
		if (sex == null) {
			errors.reject("", "Sex must be M or F.");
		}
	}
	
	protected void validateAge(int year, int month, int day, Errors errors) {
		LocalDate now = new LocalDate();
		//Add 1 to month as Joda time is one based whereas Calendar is zero based
		validateAge(now, year, month + 1, day, errors);
	}
	
	protected void validateAge(LocalDate now, int year, int month, int day, Errors errors) {
		LocalDate dob = new LocalDate(year, month, day);
		if (Years.yearsBetween(dob, now).isGreaterThan(Years.years(Globals.USER_MAX_AGE))) {
			errors.reject("", "Maximum age is 100.");
		} else if (Days.daysBetween(dob, now).isLessThan(Days.days(0))) {
			errors.reject("", "You must have been born in order to register!");
		}
	}
	
	protected void validatePostCode(String postCode, Errors errors) {
		if (postCode == null
				|| !postCode.matches(UK_POSTCODE_REGEX)) {
			errors.reject("", "Not a valid UK post code.");
		}
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
