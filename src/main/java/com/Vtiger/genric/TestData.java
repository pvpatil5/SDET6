package com.Vtiger.genric;

import com.github.javafaker.Faker;

public class TestData 
{
	/**
	 * @author AMAR-G
	 * This method gonna create fake data for first name
	 * @return String Frist Name
	 */
	public String getFirstName() {
		Faker faker = new Faker();
		String firstname=faker.name().firstName();
		return firstname;
	}
	/**
	 * @author AMAR-G
	 * This method will return name of the city
	 * @return String city name
	 */

	public String getCityName() {
		Faker faker = new Faker();
		return	faker.address().cityName();
	}
	
	public String getLastName() {
		Faker faker = new Faker();
		String lastName=faker.name().lastName();
		return lastName;
	}

}
