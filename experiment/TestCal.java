package com.experiment;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCal {
	public int input;
	public Boolean expected;
	private Cal cal=null;	
	
	public TestCal(int input,Boolean expected) {
		super();
		this.input=input;
		this.expected=expected;
	}	
	
	@Before
	public void setUp() {
		cal =new Cal();
	}
	
	@Parameters
	public static Collection<Object[]> getData() {
		Object [][] obj= {{1,true},{90,true},{300,false}};
		return Arrays.asList(obj);
	}
	
	
	@Test
	public void test() {
		assertEquals(expected,cal.Judge(input));
	}
}
