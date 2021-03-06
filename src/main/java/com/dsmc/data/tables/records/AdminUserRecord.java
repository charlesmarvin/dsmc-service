/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.records;


import com.dsmc.data.tables.AdminUser;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class AdminUserRecord extends UpdatableRecordImpl<AdminUserRecord> implements Record7<Integer, Integer, String, String, String, String, String> {

	private static final long serialVersionUID = -651905422;

	/**
	 * Setter for <code>public.admin_user.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.admin_user.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.admin_user.company_id</code>.
	 */
	public void setCompanyId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.admin_user.company_id</code>.
	 */
	public Integer getCompanyId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.admin_user.username</code>.
	 */
	public void setUsername(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.admin_user.username</code>.
	 */
	public String getUsername() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>public.admin_user.firstname</code>.
	 */
	public void setFirstname(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.admin_user.firstname</code>.
	 */
	public String getFirstname() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>public.admin_user.lastname</code>.
	 */
	public void setLastname(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.admin_user.lastname</code>.
	 */
	public String getLastname() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>public.admin_user.email</code>.
	 */
	public void setEmail(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.admin_user.email</code>.
	 */
	public String getEmail() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>public.admin_user.phone</code>.
	 */
	public void setPhone(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.admin_user.phone</code>.
	 */
	public String getPhone() {
		return (String) getValue(6);
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
	// Record7 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<Integer, Integer, String, String, String, String, String> fieldsRow() {
		return (Row7) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row7<Integer, Integer, String, String, String, String, String> valuesRow() {
		return (Row7) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return AdminUser.ADMIN_USER.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return AdminUser.ADMIN_USER.COMPANY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return AdminUser.ADMIN_USER.USERNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return AdminUser.ADMIN_USER.FIRSTNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return AdminUser.ADMIN_USER.LASTNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return AdminUser.ADMIN_USER.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return AdminUser.ADMIN_USER.PHONE;
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
	public Integer value2() {
		return getCompanyId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getUsername();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getFirstname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getLastname();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getPhone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value2(Integer value) {
		setCompanyId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value3(String value) {
		setUsername(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value4(String value) {
		setFirstname(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value5(String value) {
		setLastname(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value6(String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord value7(String value) {
		setPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUserRecord values(Integer value1, Integer value2, String value3, String value4, String value5, String value6, String value7) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AdminUserRecord
	 */
	public AdminUserRecord() {
		super(AdminUser.ADMIN_USER);
	}

	/**
	 * Create a detached, initialised AdminUserRecord
	 */
	public AdminUserRecord(Integer id, Integer companyId, String username, String firstname, String lastname, String email, String phone) {
		super(AdminUser.ADMIN_USER);

		setValue(0, id);
		setValue(1, companyId);
		setValue(2, username);
		setValue(3, firstname);
		setValue(4, lastname);
		setValue(5, email);
		setValue(6, phone);
	}
}
