package com.Practice;

import com.github.javafaker.Faker;

public class BrowserLAunch {

	public static void main(String[] args) {
		
		Faker faker = new  Faker();

		//		System.out.println(faker.name().fullName());
		//		
		//		System.out.println(faker.address().fullAddress());
		//		
		//		System.out.println(faker.phoneNumber().phoneNumber());
		//		
		//		System.out.println(faker.address().cityName());

		//	System.out.println(faker.company().industry());

		System.out.println(faker.name().firstName());

		System.out.println(faker.internet().emailAddress());

	}

}
