package com.whiteleys.zoo.web.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.whiteleys.zoo.domain.Animal;
import com.whiteleys.zoo.domain.User;

public class UserRegistrationValidatorTest {

	private UserRegistrationValidator v = new UserRegistrationValidator();
	
	@Test
	public void testValidatorSupportsUserClass() {
		assertEquals(true, v.supports(User.class));
	}
	
	@Test
	public void testValidatorDoesNotSupportOtherClasses() {
		assertEquals(false, v.supports(Animal.class));
	}
	
	@Test
	public void testValidPostcode() {
	// http://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom	
	}
	
	
}
