/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.records;


import com.dsmc.data.tables.Instructor;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row16;
import org.jooq.impl.UpdatableRecordImpl;


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
public class InstructorRecord extends UpdatableRecordImpl<InstructorRecord> implements Record16<Integer, String, String, Date, String, String, String, String, String, String, String, String, String, String, Date, Integer> {

	private static final long serialVersionUID = -956629825;

	/**
	 * Setter for <code>public.instructor.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.instructor.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.instructor.first_name</code>.
	 */
	public void setFirstName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.instructor.first_name</code>.
	 */
	public String getFirstName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>public.instructor.last_name</code>.
	 */
	public void setLastName(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.instructor.last_name</code>.
	 */
	public String getLastName() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>public.instructor.created_on</code>.
	 */
	public void setCreatedOn(Date value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.instructor.created_on</code>.
	 */
	public Date getCreatedOn() {
		return (Date) getValue(3);
	}

	/**
	 * Setter for <code>public.instructor.email</code>.
	 */
	public void setEmail(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.instructor.email</code>.
	 */
	public String getEmail() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>public.instructor.active</code>.
	 */
	public void setActive(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.instructor.active</code>.
	 */
	public String getActive() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>public.instructor.gender</code>.
	 */
	public void setGender(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.instructor.gender</code>.
	 */
	public String getGender() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>public.instructor.primary_phone</code>.
	 */
	public void setPrimaryPhone(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.instructor.primary_phone</code>.
	 */
	public String getPrimaryPhone() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>public.instructor.secondary_phone</code>.
	 */
	public void setSecondaryPhone(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.instructor.secondary_phone</code>.
	 */
	public String getSecondaryPhone() {
		return (String) getValue(8);
	}

	/**
	 * Setter for <code>public.instructor.address_line1</code>.
	 */
	public void setAddressLine1(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>public.instructor.address_line1</code>.
	 */
	public String getAddressLine1() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>public.instructor.address_line2</code>.
	 */
	public void setAddressLine2(String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>public.instructor.address_line2</code>.
	 */
	public String getAddressLine2() {
		return (String) getValue(10);
	}

	/**
	 * Setter for <code>public.instructor.city</code>.
	 */
	public void setCity(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>public.instructor.city</code>.
	 */
	public String getCity() {
		return (String) getValue(11);
	}

	/**
	 * Setter for <code>public.instructor.state</code>.
	 */
	public void setState(String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>public.instructor.state</code>.
	 */
	public String getState() {
		return (String) getValue(12);
	}

	/**
	 * Setter for <code>public.instructor.zipcode</code>.
	 */
	public void setZipcode(String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>public.instructor.zipcode</code>.
	 */
	public String getZipcode() {
		return (String) getValue(13);
	}

	/**
	 * Setter for <code>public.instructor.certification_date</code>.
	 */
	public void setCertificationDate(Date value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>public.instructor.certification_date</code>.
	 */
	public Date getCertificationDate() {
		return (Date) getValue(14);
	}

	/**
	 * Setter for <code>public.instructor.company_id</code>.
	 */
	public void setCompanyId(Integer value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>public.instructor.company_id</code>.
	 */
	public Integer getCompanyId() {
		return (Integer) getValue(15);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record16 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row16<Integer, String, String, Date, String, String, String, String, String, String, String, String, String, String, Date, Integer> fieldsRow() {
		return (Row16) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row16<Integer, String, String, Date, String, String, String, String, String, String, String, String, String, String, Date, Integer> valuesRow() {
		return (Row16) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Instructor.INSTRUCTOR.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Instructor.INSTRUCTOR.FIRST_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Instructor.INSTRUCTOR.LAST_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field4() {
		return Instructor.INSTRUCTOR.CREATED_ON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Instructor.INSTRUCTOR.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Instructor.INSTRUCTOR.ACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Instructor.INSTRUCTOR.GENDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return Instructor.INSTRUCTOR.PRIMARY_PHONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field9() {
		return Instructor.INSTRUCTOR.SECONDARY_PHONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field10() {
		return Instructor.INSTRUCTOR.ADDRESS_LINE1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field11() {
		return Instructor.INSTRUCTOR.ADDRESS_LINE2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field12() {
		return Instructor.INSTRUCTOR.CITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field13() {
		return Instructor.INSTRUCTOR.STATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field14() {
		return Instructor.INSTRUCTOR.ZIPCODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field15() {
		return Instructor.INSTRUCTOR.CERTIFICATION_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field16() {
		return Instructor.INSTRUCTOR.COMPANY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getFirstName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getLastName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value4() {
		return getCreatedOn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getActive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getGender();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getPrimaryPhone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value9() {
		return getSecondaryPhone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value10() {
		return getAddressLine1();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value11() {
		return getAddressLine2();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value12() {
		return getCity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value13() {
		return getState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value14() {
		return getZipcode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value15() {
		return getCertificationDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value16() {
		return getCompanyId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value2(String value) {
		setFirstName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value3(String value) {
		setLastName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value4(Date value) {
		setCreatedOn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value5(String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value6(String value) {
		setActive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value7(String value) {
		setGender(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value8(String value) {
		setPrimaryPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value9(String value) {
		setSecondaryPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value10(String value) {
		setAddressLine1(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value11(String value) {
		setAddressLine2(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value12(String value) {
		setCity(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value13(String value) {
		setState(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value14(String value) {
		setZipcode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value15(Date value) {
		setCertificationDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord value16(Integer value) {
		setCompanyId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructorRecord values(Integer value1, String value2, String value3, Date value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14, Date value15, Integer value16) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		value13(value13);
		value14(value14);
		value15(value15);
		value16(value16);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached InstructorRecord
	 */
	public InstructorRecord() {
		super(Instructor.INSTRUCTOR);
	}

	/**
	 * Create a detached, initialised InstructorRecord
	 */
	public InstructorRecord(Integer id, String firstName, String lastName, Date createdOn, String email, String active, String gender, String primaryPhone, String secondaryPhone, String addressLine1, String addressLine2, String city, String state, String zipcode, Date certificationDate, Integer companyId) {
		super(Instructor.INSTRUCTOR);

		setValue(0, id);
		setValue(1, firstName);
		setValue(2, lastName);
		setValue(3, createdOn);
		setValue(4, email);
		setValue(5, active);
		setValue(6, gender);
		setValue(7, primaryPhone);
		setValue(8, secondaryPhone);
		setValue(9, addressLine1);
		setValue(10, addressLine2);
		setValue(11, city);
		setValue(12, state);
		setValue(13, zipcode);
		setValue(14, certificationDate);
		setValue(15, companyId);
	}
}
