/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables;


import com.dsmc.data.Keys;
import com.dsmc.data.Public;
import com.dsmc.data.tables.records.StudentRecord;

import java.sql.Date;
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
public class Student extends TableImpl<StudentRecord> {

	private static final long serialVersionUID = -92332711;

	/**
	 * The reference instance of <code>public.student</code>
	 */
	public static final Student STUDENT = new Student();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<StudentRecord> getRecordType() {
		return StudentRecord.class;
	}

	/**
	 * The column <code>public.student.id</code>.
	 */
	public final TableField<StudentRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.student.first_name</code>.
	 */
	public final TableField<StudentRecord, String> FIRST_NAME = createField("first_name", org.jooq.impl.SQLDataType.VARCHAR.length(30).defaulted(true), this, "");

	/**
	 * The column <code>public.student.last_name</code>.
	 */
	public final TableField<StudentRecord, String> LAST_NAME = createField("last_name", org.jooq.impl.SQLDataType.VARCHAR.length(30).defaulted(true), this, "");

	/**
	 * The column <code>public.student.created_on</code>.
	 */
	public final TableField<StudentRecord, Date> CREATED_ON = createField("created_on", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>public.student.email</code>.
	 */
	public final TableField<StudentRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(150).defaulted(true), this, "");

	/**
	 * The column <code>public.student.active</code>.
	 */
	public final TableField<StudentRecord, String> ACTIVE = createField("active", org.jooq.impl.SQLDataType.CHAR.length(1).defaulted(true), this, "");

	/**
	 * The column <code>public.student.gender</code>.
	 */
	public final TableField<StudentRecord, String> GENDER = createField("gender", org.jooq.impl.SQLDataType.CHAR.length(1).defaulted(true), this, "");

	/**
	 * The column <code>public.student.primary_phone</code>.
	 */
	public final TableField<StudentRecord, String> PRIMARY_PHONE = createField("primary_phone", org.jooq.impl.SQLDataType.VARCHAR.length(12).defaulted(true), this, "");

	/**
	 * The column <code>public.student.secondary_phone</code>.
	 */
	public final TableField<StudentRecord, String> SECONDARY_PHONE = createField("secondary_phone", org.jooq.impl.SQLDataType.VARCHAR.length(12).defaulted(true), this, "");

	/**
	 * The column <code>public.student.address_line1</code>.
	 */
	public final TableField<StudentRecord, String> ADDRESS_LINE1 = createField("address_line1", org.jooq.impl.SQLDataType.VARCHAR.length(150).defaulted(true), this, "");

	/**
	 * The column <code>public.student.address_line2</code>.
	 */
	public final TableField<StudentRecord, String> ADDRESS_LINE2 = createField("address_line2", org.jooq.impl.SQLDataType.VARCHAR.length(150).defaulted(true), this, "");

	/**
	 * The column <code>public.student.city</code>.
	 */
	public final TableField<StudentRecord, String> CITY = createField("city", org.jooq.impl.SQLDataType.VARCHAR.length(50).defaulted(true), this, "");

	/**
	 * The column <code>public.student.state</code>.
	 */
	public final TableField<StudentRecord, String> STATE = createField("state", org.jooq.impl.SQLDataType.CHAR.length(2).defaulted(true), this, "");

	/**
	 * The column <code>public.student.zipcode</code>.
	 */
	public final TableField<StudentRecord, String> ZIPCODE = createField("zipcode", org.jooq.impl.SQLDataType.CHAR.length(10).defaulted(true), this, "");

	/**
	 * The column <code>public.student.dob</code>.
	 */
	public final TableField<StudentRecord, Date> DOB = createField("dob", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>public.student.company_id</code>.
	 */
	public final TableField<StudentRecord, Integer> COMPANY_ID = createField("company_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>public.student</code> table reference
	 */
	public Student() {
		this("student", null);
	}

	/**
	 * Create an aliased <code>public.student</code> table reference
	 */
	public Student(String alias) {
		this(alias, STUDENT);
	}

	private Student(String alias, Table<StudentRecord> aliased) {
		this(alias, aliased, null);
	}

	private Student(String alias, Table<StudentRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<StudentRecord, Integer> getIdentity() {
		return Keys.IDENTITY_STUDENT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<StudentRecord> getPrimaryKey() {
		return Keys.STUDENT_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<StudentRecord>> getKeys() {
		return Arrays.<UniqueKey<StudentRecord>>asList(Keys.STUDENT_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<StudentRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<StudentRecord, ?>>asList(Keys.STUDENT__STUDENT_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Student as(String alias) {
		return new Student(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Student rename(String name) {
		return new Student(name, null);
	}
}