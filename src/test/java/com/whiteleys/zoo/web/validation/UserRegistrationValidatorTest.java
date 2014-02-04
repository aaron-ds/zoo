package com.whiteleys.zoo.web.validation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import com.whiteleys.zoo.domain.Animal;
import com.whiteleys.zoo.domain.Sex;
import com.whiteleys.zoo.domain.User;
import com.whiteleys.zoo.service.UserService;

public class UserRegistrationValidatorTest {

	private UserRegistrationValidator v = new UserRegistrationValidator();
	@Mock Errors errors;
	@Mock UserService userService;
	

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidatorSupportsUserClass() {
	//	assertTrue(v.supports(User.class));
	}
	
	@Test
	public void testValidatorDoesNotSupportOtherClasses() {
	//	assertFalse(v.supports(Animal.class));
	}
	
	@Test
	public void testValidationPassesIfUserNameDoesNotExist() {
		
	}
	
	@Test
	public void testValidPostcode() {
	//	v.validatePostCode("N11EP", errors);
	//	verifyZeroInteractions(errors);
	}
	
	@Test
	public void testInvalidPostcodeFailsValidation() {
	//	v.validatePostCode("ABCD123", errors);
	//	verify(errors, times(1)).reject(anyString(), anyString());
	}
	
	@Test
	public void testValidationPassesWhenBothPasswordsMatch() {
	//	v.validatePasswordsMatch("password", "password", errors);
	//	verifyZeroInteractions(errors);
	}
	
	@Test
	public void testValidationFailsWhenPasswordsDoNotMatch() {
	//	v.validatePasswordsMatch("password", "pa55word", errors);
	//	verify(errors, times(1)).reject(anyString(), anyString());
	}
	
	@Test
	public void testValidationPassesWithCorrectSex() {
	//	v.validateSex(Sex.M, errors);
	//	v.validateSex(Sex.F, errors);
	//	verifyZeroInteractions(errors);
	}
	
	@Test
	public void testValidationFailsIfNoValueIsPassed() {
	//	v.validateSex(null, errors);
	//	verify(errors, times(1)).reject(anyString(), anyString());
	}
	
	
	
	
	
}
