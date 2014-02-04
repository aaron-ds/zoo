package com.whiteleys.zoo.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnimalTest {

	
	private Animal horse1;
	private Animal horse2;
	private Animal donkey;
	
	
	@Test
	public void testTwoOfTheSameAnimalsAreEqual() {
		horse1 = new Animal();
		horse2 = new Animal();
		horse1.setId(1L);
		horse1.setName("Horse");
		horse2.setId(1L);
		horse2.setName("Horse");
		
		assertTrue(horse1.equals(horse2));
		assertTrue(horse2.equals(horse1));
	}
	
	@Test
	public void testDifferentAnimalsAreNotEqual() {
		donkey = new Animal();
		horse1 = new Animal();
		donkey.setId(2L);
		donkey.setName("Donkey");
		horse1.setId(1L);
		horse1.setName("Horse");
		
		assertFalse(horse1.equals(donkey));
		assertFalse(donkey.equals(horse1));
	}
}
