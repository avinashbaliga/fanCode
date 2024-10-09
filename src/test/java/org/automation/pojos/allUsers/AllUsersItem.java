package org.automation.pojos.allUsers;

import com.google.gson.annotations.SerializedName;

public class AllUsersItem{

	@SerializedName("website")
	public String website;

	@SerializedName("address")
	public Address address;

	@SerializedName("phone")
	public String phone;

	@SerializedName("name")
	public String name;

	@SerializedName("company")
	public Company company;

	@SerializedName("id")
	public int id;

	@SerializedName("email")
	public String email;

	@SerializedName("username")
	private String username;
}