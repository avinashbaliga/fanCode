package org.automation.pojos.allUsers;

import com.google.gson.annotations.SerializedName;

public class Address{

	@SerializedName("zipcode")
	public String zipcode;

	@SerializedName("geo")
	public Geo geo;

	@SerializedName("suite")
	public String suite;

	@SerializedName("city")
	public String city;

	@SerializedName("street")
	public String street;
}