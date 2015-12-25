/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables;


import com.dsmc.data.Keys;
import com.dsmc.data.Public;
import com.dsmc.data.tables.records.StudentEnrollmentRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


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
public class StudentEnrollment extends TableImpl<StudentEnrollmentRecord> {

	private static final long serialVersionUID = 1007343197;

	/**
	 * The reference instance of <code>public.student_enrollment</code>
	 */
	public static final StudentEnrollment STUDENT_ENROLLMENT = new StudentEnrollment();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<StudentEnrollmentRecord> getRecordType() {
		return StudentEnrollmentRecord.class;
	}

	/**
	 * The column <code>public.student_enrollment.student_id</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Integer> STUDENT_ID = createField("student_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.student_enrollment.course_package_id</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Integer> COURSE_PACKAGE_ID = createField("course_package_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.student_enrollment.price_override</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Integer> PRICE_OVERRIDE = createField("price_override", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.student_enrollment.payment_received</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Integer> PAYMENT_RECEIVED = createField("payment_received", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.student_enrollment.enrollment_date</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Date> ENROLLMENT_DATE = createField("enrollment_date", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>public.student_enrollment.completion_date</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Date> COMPLETION_DATE = createField("completion_date", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>public.student_enrollment.company_id</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Integer> COMPANY_ID = createField("company_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.student_enrollment.instructor_id</code>.
	 */
	public final TableField<StudentEnrollmentRecord, Integer> INSTRUCTOR_ID = createField("instructor_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>public.student_enrollment</code> table reference
	 */
	public StudentEnrollment() {
		this("student_enrollment", null);
	}

	/**
	 * Create an aliased <code>public.student_enrollment</code> table reference
	 */
	public StudentEnrollment(String alias) {
		this(alias, STUDENT_ENROLLMENT);
	}

	private StudentEnrollment(String alias, Table<StudentEnrollmentRecord> aliased) {
		this(alias, aliased, null);
	}

	private StudentEnrollment(String alias, Table<StudentEnrollmentRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<StudentEnrollmentRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<StudentEnrollmentRecord, ?>>asList(Keys.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_2, Keys.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_1, Keys.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_3, Keys.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_4);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StudentEnrollment as(String alias) {
		return new StudentEnrollment(alias, this);
	}

	/**
	 * Rename this table
	 */
	public StudentEnrollment rename(String name) {
		return new StudentEnrollment(name, null);
	}
}
