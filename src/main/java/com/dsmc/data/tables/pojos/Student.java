/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Student implements Serializable {

	private static final long serialVersionUID = 1488667601;

	private Integer id;
	private String  firstName;
	private String  lastName;
	private Date    createdOn;
	private String  email;
	private String  active;
	private String  gender;
	private String  primaryPhone;
	private String  secondaryPhone;
	private String  addressLine1;
	private String  addressLine2;
	private String  city;
	private String  state;
	private String  zipcode;
	private Date    dob;
	private Integer companyId;

	public Student() {}

	public Student(Student value) {
		this.id = value.id;
		this.firstName = value.firstName;
		this.lastName = value.lastName;
		this.createdOn = value.createdOn;
		this.email = value.email;
		this.active = value.active;
		this.gender = value.gender;
		this.primaryPhone = value.primaryPhone;
		this.secondaryPhone = value.secondaryPhone;
		this.addressLine1 = value.addressLine1;
		this.addressLine2 = value.addressLine2;
		this.city = value.city;
		this.state = value.state;
		this.zipcode = value.zipcode;
		this.dob = value.dob;
		this.companyId = value.companyId;
	}

	public Student(
		Integer id,
		String  firstName,
		String  lastName,
		Date    createdOn,
		String  email,
		String  active,
		String  gender,
		String  primaryPhone,
		String  secondaryPhone,
		String  addressLine1,
		String  addressLine2,
		String  city,
		String  state,
		String  zipcode,
		Date    dob,
		Integer companyId
	) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdOn = createdOn;
		this.email = email;
		this.active = active;
		this.gender = gender;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.dob = dob;
		this.companyId = companyId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrimaryPhone() {
		return this.primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return this.secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Student (");

		sb.append(id);
		sb.append(", ").append(firstName);
		sb.append(", ").append(lastName);
		sb.append(", ").append(createdOn);
		sb.append(", ").append(email);
		sb.append(", ").append(active);
		sb.append(", ").append(gender);
		sb.append(", ").append(primaryPhone);
		sb.append(", ").append(secondaryPhone);
		sb.append(", ").append(addressLine1);
		sb.append(", ").append(addressLine2);
		sb.append(", ").append(city);
		sb.append(", ").append(state);
		sb.append(", ").append(zipcode);
		sb.append(", ").append(dob);
		sb.append(", ").append(companyId);

		sb.append(")");
		return sb.toString();
	}
}
