/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables;


import com.dsmc.data.Keys;
import com.dsmc.data.Public;
import com.dsmc.data.tables.records.InstructionSessionRecord;

import java.sql.Date;
import java.sql.Timestamp;
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
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InstructionSession extends TableImpl<InstructionSessionRecord> {

	private static final long serialVersionUID = 1067204018;

	/**
	 * The reference instance of <code>public.instruction_session</code>
	 */
	public static final InstructionSession INSTRUCTION_SESSION = new InstructionSession();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<InstructionSessionRecord> getRecordType() {
		return InstructionSessionRecord.class;
	}

	/**
	 * The column <code>public.instruction_session.id</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.instruction_session.student_id</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> STUDENT_ID = createField("student_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.instruction_session.course_package_id</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> COURSE_PACKAGE_ID = createField("course_package_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.instruction_session.price_override</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> PRICE_OVERRIDE = createField("price_override", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.instruction_session.payment_received</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> PAYMENT_RECEIVED = createField("payment_received", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.instruction_session.session_datetime</code>.
	 */
	public final TableField<InstructionSessionRecord, Timestamp> SESSION_DATETIME = createField("session_datetime", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

	/**
	 * The column <code>public.instruction_session.company_id</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> COMPANY_ID = createField("company_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.instruction_session.instructor_id</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> INSTRUCTOR_ID = createField("instructor_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.instruction_session.created_on</code>.
	 */
	public final TableField<InstructionSessionRecord, Date> CREATED_ON = createField("created_on", org.jooq.impl.SQLDataType.DATE.defaulted(true), this, "");

	/**
	 * The column <code>public.instruction_session.created_by</code>.
	 */
	public final TableField<InstructionSessionRecord, Integer> CREATED_BY = createField("created_by", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>public.instruction_session</code> table reference
	 */
	public InstructionSession() {
		this("instruction_session", null);
	}

	/**
	 * Create an aliased <code>public.instruction_session</code> table reference
	 */
	public InstructionSession(String alias) {
		this(alias, INSTRUCTION_SESSION);
	}

	private InstructionSession(String alias, Table<InstructionSessionRecord> aliased) {
		this(alias, aliased, null);
	}

	private InstructionSession(String alias, Table<InstructionSessionRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<InstructionSessionRecord, Integer> getIdentity() {
		return Keys.IDENTITY_INSTRUCTION_SESSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<InstructionSessionRecord> getPrimaryKey() {
		return Keys.INSTRUCTION_SESSION_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<InstructionSessionRecord>> getKeys() {
		return Arrays.<UniqueKey<InstructionSessionRecord>>asList(Keys.INSTRUCTION_SESSION_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<InstructionSessionRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<InstructionSessionRecord, ?>>asList(Keys.INSTRUCTION_SESSION__INSTRUCTION_SESSION_STUDENT_ID_FKEY, Keys.INSTRUCTION_SESSION__INSTRUCTION_SESSION_COURSE_PACKAGE_ID_FKEY, Keys.INSTRUCTION_SESSION__INSTRUCTION_SESSION_COMPANY_ID_FKEY, Keys.INSTRUCTION_SESSION__INSTRUCTION_SESSION_INSTRUCTOR_ID_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSession as(String alias) {
		return new InstructionSession(alias, this);
	}

	/**
	 * Rename this table
	 */
	public InstructionSession rename(String name) {
		return new InstructionSession(name, null);
	}
}