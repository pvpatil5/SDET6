package com.TESTNG.pac;


import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class Test1 extends Annotations {


	@Test(groups = {"smoke"},enabled = false)
	public void testAndroidapp() {
		System.out.println("testAndroidapp");

	}
	@Test(groups = {"sanity"},invocationCount = 0)
	public void testWebapp() {
		System.out.println("testWebapp");
	}

	@Test(groups = {"regression"})
	public void testWebapp1() {
		System.out.println("testWebapp1");
	}
	@Test(groups = {"regression"})
	public void testWebapp2() {
		
		System.out.println("testWebapp2");
		throw new SkipException("MSg");
		
	}
}
