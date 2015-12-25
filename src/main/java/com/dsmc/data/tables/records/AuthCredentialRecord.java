/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.records;


import com.dsmc.data.tables.AuthCredential;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class AuthCredentialRecord extends UpdatableRecordImpl<AuthCredentialRecord> implements Record4<String, String, String, String> {

	private static final long serialVersionUID = -1465172866;

	/**
	 * Setter for <code>public.auth_credential.login</code>.
	 */
	public void setLogin(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.auth_credential.login</code>.
	 */
	public String getLogin() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>public.auth_credential.password</code>.
	 */
	public void setPassword(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.auth_credential.password</code>.
	 */
	public String getPassword() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>public.auth_credential.salt</code>.
	 */
	public void setSalt(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.auth_credential.salt</code>.
	 */
	public String getSalt() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>public.auth_credential.jwt_token</code>.
	 */
	public void setJwtToken(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.auth_credential.jwt_token</code>.
	 */
	public String getJwtToken() {
		return (String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return AuthCredential.AUTH_CREDENTIAL.LOGIN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return AuthCredential.AUTH_CREDENTIAL.PASSWORD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return AuthCredential.AUTH_CREDENTIAL.SALT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return AuthCredential.AUTH_CREDENTIAL.JWT_TOKEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getLogin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getPassword();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getSalt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getJwtToken();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthCredentialRecord value1(String value) {
		setLogin(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthCredentialRecord value2(String value) {
		setPassword(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthCredentialRecord value3(String value) {
		setSalt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthCredentialRecord value4(String value) {
		setJwtToken(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthCredentialRecord values(String value1, String value2, String value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached AuthCredentialRecord
	 */
	public AuthCredentialRecord() {
		super(AuthCredential.AUTH_CREDENTIAL);
	}

	/**
	 * Create a detached, initialised AuthCredentialRecord
	 */
	public AuthCredentialRecord(String login, String password, String salt, String jwtToken) {
		super(AuthCredential.AUTH_CREDENTIAL);

		setValue(0, login);
		setValue(1, password);
		setValue(2, salt);
		setValue(3, jwtToken);
	}
}
