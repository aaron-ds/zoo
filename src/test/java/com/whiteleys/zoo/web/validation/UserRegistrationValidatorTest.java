package com.whiteleys.zoo.web.validation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.joda.time.LocalDate;
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

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationValidatorTest {

	private UserRegistrationValidator v = new UserRegistrationValidator();
	

	@Before
	public void setup() {
		// Coudn't get surefire to initialize mocks with either @RunWith or initMocks
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testValidatorSupportsUserClass() {
		assertTrue(v.supports(User.class));
	}
	
	@Test
	public void testValidatorDoesNotSupportOtherClasses() {
		assertFalse(v.supports(Animal.class));
	}
	
	@Test
	public void testValidationPassesIfUsernameNotRegistered() {
		Errors errors = Mockito.mock(Errors.class);
		UserService userService = Mockito.mock(UserService.class);
		when(userService.exists(anyString())).thenReturn(false);
		v.setUserService(userService);
		v.validateUsername("newUser", errors);
		verifyZeroInteractions(errors);
	}
	
	@Test
	public void testValidationFailsIfUsernameAlreadyRegistered() {
		Errors errors = Mockito.mock(Errors.class);
		UserService userService = Mockito.mock(UserService.class);
		when(userService.exists(anyString())).thenReturn(true);
		v.setUserService(userService);
		v.validateUsername("existing", errors);
		verify(errors, times(1)).reject(anyString(), eq("Username is not available"));
	}
	
	@Test
	public void testValidPostcode() {
		Errors errors = Mockito.mock(Errors.class);
		v.validatePostCode("N11EP", errors);
		verifyZeroInteractions(errors);
	}
	
	@Test
	public void testInvalidPostCode() {
		Errors errors = Mockito.mock(Errors.class);
		v.validatePostCode("123 ABC", errors);
		verify(errors, times(1)).reject(anyString(), eq("Not a valid UK post code."));
	}
	
	@Test
	public void testValidationPassesWhenBothPasswordsMatch() {
		Errors errors = Mockito.mock(Errors.class);
		v.validatePassword("password", "password", errors);
		verifyZeroInteractions(errors);
	}
	
	@Test
	public void testValidationFailsWhenPasswordsDoNotMatch() {
		Errors errors = Mockito.mock(Errors.class);
		v.validatePassword("password", "pa55word", errors);
		verify(errors, times(1)).reject(anyString(), eq("Both passwords must be the same."));
	}
	
	@Test
	public void testValidationFailsWhenPasswordsAreBlank() {
		Errors errors = Mockito.mock(Errors.class);
		v.validatePassword("    ", "    ", errors);
		verify(errors, times(1)).reject(anyString(), eq("Password cannot be blank"));
	}
	
	@Test
	public void testValidationPassesWithCorrectSex() {
		Errors errors = Mockito.mock(Errors.class);
		v.validateSex(Sex.M, errors);
		v.validateSex(Sex.F, errors);
		verifyZeroInteractions(errors);
	}
	
	@Test
	public void testValidationFailsIfNoValueIsPassedForSex() {
		Errors errors = Mockito.mock(Errors.class);
		v.validateSex(null, errors);
		verify(errors, times(1)).reject(anyString(), eq("Sex must be M or F."));
	}
	
	@Test
	public void testValidationPassesForValidAge() {
		Errors errors = Mockito.mock(Errors.class);
		LocalDate now = new LocalDate(2014, 1, 1);
		v.validateAge(now, 2000, 5, 23, errors);
		verifyZeroInteractions(errors);
	}
	
	@Test
	public void testValidationFailsIfAgeIsGreaterThan100() {
		Errors errors = Mockito.mock(Errors.class);
		LocalDate now = new LocalDate(2014, 1, 1);
		v.validateAge(now, 1913, 1, 1, errors);
		verify(errors, times(1)).reject(anyString(), eq("Maximum age is 100."));
	}
	
	@Test
	public void testValidationFailsIfAgeIsLessThan0() {
		Errors errors = Mockito.mock(Errors.class);
		LocalDate now = new LocalDate(2014, 1, 1);
		v.validateAge(now, 2014, 1, 2, errors);
		verify(errors, times(1)).reject(anyString(), eq("You must have been born in order to register!"));
	}
	
	
}
