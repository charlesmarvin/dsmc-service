/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AuthCredential implements Serializable {

	private static final long serialVersionUID = -823841107;

	private final String login;
	private final String password;
	private final String salt;
	private final String jwtToken;

	public AuthCredential(AuthCredential value) {
		this.login = value.login;
		this.password = value.password;
		this.salt = value.salt;
		this.jwtToken = value.jwtToken;
	}

	public AuthCredential(
		String login,
		String password,
		String salt,
		String jwtToken
	) {
		this.login = login;
		this.password = password;
		this.salt = salt;
		this.jwtToken = jwtToken;
	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public String getSalt() {
		return this.salt;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("AuthCredential (");

		sb.append(login);
		sb.append(", ").append(password);
		sb.append(", ").append(salt);
		sb.append(", ").append(jwtToken);

		sb.append(")");
		return sb.toString();
	}
}
