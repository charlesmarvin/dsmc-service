/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables;


import com.dsmc.data.Keys;
import com.dsmc.data.Public;
import com.dsmc.data.tables.records.AdminUserRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class AdminUser extends TableImpl<AdminUserRecord> {

	private static final long serialVersionUID = 169335421;

	/**
	 * The reference instance of <code>public.admin_user</code>
	 */
	public static final AdminUser ADMIN_USER = new AdminUser();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AdminUserRecord> getRecordType() {
		return AdminUserRecord.class;
	}

	/**
	 * The column <code>public.admin_user.id</code>.
	 */
	public final TableField<AdminUserRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.admin_user.company_id</code>.
	 */
	public final TableField<AdminUserRecord, Integer> COMPANY_ID = createField("company_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.admin_user.username</code>.
	 */
	public final TableField<AdminUserRecord, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.admin_user.firstname</code>.
	 */
	public final TableField<AdminUserRecord, String> FIRSTNAME = createField("firstname", org.jooq.impl.SQLDataType.VARCHAR.length(50).defaulted(true), this, "");

	/**
	 * The column <code>public.admin_user.lastname</code>.
	 */
	public final TableField<AdminUserRecord, String> LASTNAME = createField("lastname", org.jooq.impl.SQLDataType.VARCHAR.length(50).defaulted(true), this, "");

	/**
	 * The column <code>public.admin_user.email</code>.
	 */
	public final TableField<AdminUserRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(100).defaulted(true), this, "");

	/**
	 * The column <code>public.admin_user.phone</code>.
	 */
	public final TableField<AdminUserRecord, String> PHONE = createField("phone", org.jooq.impl.SQLDataType.CHAR.length(11).defaulted(true), this, "");

	/**
	 * Create a <code>public.admin_user</code> table reference
	 */
	public AdminUser() {
		this("admin_user", null);
	}

	/**
	 * Create an aliased <code>public.admin_user</code> table reference
	 */
	public AdminUser(String alias) {
		this(alias, ADMIN_USER);
	}

	private AdminUser(String alias, Table<AdminUserRecord> aliased) {
		this(alias, aliased, null);
	}

	private AdminUser(String alias, Table<AdminUserRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<AdminUserRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ADMIN_USER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AdminUserRecord> getPrimaryKey() {
		return Keys.ADMIN_USER_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AdminUserRecord>> getKeys() {
		return Arrays.<UniqueKey<AdminUserRecord>>asList(Keys.ADMIN_USER_PKEY, Keys.USERNAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<AdminUserRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<AdminUserRecord, ?>>asList(Keys.ADMIN_USER__ADMIN_USER_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AdminUser as(String alias) {
		return new AdminUser(alias, this);
	}

	/**
	 * Rename this table
	 */
	public AdminUser rename(String name) {
		return new AdminUser(name, null);
	}
}
