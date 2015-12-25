/**
 * This class is generated by jOOQ
 */
package com.dsmc.data.tables.records;


import com.dsmc.data.tables.InstructionSession;

import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
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
public class InstructionSessionRecord extends UpdatableRecordImpl<InstructionSessionRecord> implements Record10<Integer, Integer, Integer, Integer, Integer, Timestamp, Integer, Integer, Date, Integer> {

	private static final long serialVersionUID = 1089998085;

	/**
	 * Setter for <code>public.instruction_session.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.instruction_session.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.instruction_session.student_id</code>.
	 */
	public void setStudentId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.instruction_session.student_id</code>.
	 */
	public Integer getStudentId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>public.instruction_session.course_package_id</code>.
	 */
	public void setCoursePackageId(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.instruction_session.course_package_id</code>.
	 */
	public Integer getCoursePackageId() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>public.instruction_session.price_override</code>.
	 */
	public void setPriceOverride(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.instruction_session.price_override</code>.
	 */
	public Integer getPriceOverride() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>public.instruction_session.payment_received</code>.
	 */
	public void setPaymentReceived(Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.instruction_session.payment_received</code>.
	 */
	public Integer getPaymentReceived() {
		return (Integer) getValue(4);
	}

	/**
	 * Setter for <code>public.instruction_session.session_datetime</code>.
	 */
	public void setSessionDatetime(Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.instruction_session.session_datetime</code>.
	 */
	public Timestamp getSessionDatetime() {
		return (Timestamp) getValue(5);
	}

	/**
	 * Setter for <code>public.instruction_session.company_id</code>.
	 */
	public void setCompanyId(Integer value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.instruction_session.company_id</code>.
	 */
	public Integer getCompanyId() {
		return (Integer) getValue(6);
	}

	/**
	 * Setter for <code>public.instruction_session.instructor_id</code>.
	 */
	public void setInstructorId(Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.instruction_session.instructor_id</code>.
	 */
	public Integer getInstructorId() {
		return (Integer) getValue(7);
	}

	/**
	 * Setter for <code>public.instruction_session.created_on</code>.
	 */
	public void setCreatedOn(Date value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.instruction_session.created_on</code>.
	 */
	public Date getCreatedOn() {
		return (Date) getValue(8);
	}

	/**
	 * Setter for <code>public.instruction_session.created_by</code>.
	 */
	public void setCreatedBy(Integer value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>public.instruction_session.created_by</code>.
	 */
	public Integer getCreatedBy() {
		return (Integer) getValue(9);
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
	// Record10 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row10<Integer, Integer, Integer, Integer, Integer, Timestamp, Integer, Integer, Date, Integer> fieldsRow() {
		return (Row10) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row10<Integer, Integer, Integer, Integer, Integer, Timestamp, Integer, Integer, Date, Integer> valuesRow() {
		return (Row10) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return InstructionSession.INSTRUCTION_SESSION.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return InstructionSession.INSTRUCTION_SESSION.STUDENT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return InstructionSession.INSTRUCTION_SESSION.COURSE_PACKAGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return InstructionSession.INSTRUCTION_SESSION.PRICE_OVERRIDE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field5() {
		return InstructionSession.INSTRUCTION_SESSION.PAYMENT_RECEIVED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field6() {
		return InstructionSession.INSTRUCTION_SESSION.SESSION_DATETIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field7() {
		return InstructionSession.INSTRUCTION_SESSION.COMPANY_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field8() {
		return InstructionSession.INSTRUCTION_SESSION.INSTRUCTOR_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Date> field9() {
		return InstructionSession.INSTRUCTION_SESSION.CREATED_ON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field10() {
		return InstructionSession.INSTRUCTION_SESSION.CREATED_BY;
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
		return getStudentId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getCoursePackageId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getPriceOverride();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value5() {
		return getPaymentReceived();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value6() {
		return getSessionDatetime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value7() {
		return getCompanyId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value8() {
		return getInstructorId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date value9() {
		return getCreatedOn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value10() {
		return getCreatedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value2(Integer value) {
		setStudentId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value3(Integer value) {
		setCoursePackageId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value4(Integer value) {
		setPriceOverride(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value5(Integer value) {
		setPaymentReceived(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value6(Timestamp value) {
		setSessionDatetime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value7(Integer value) {
		setCompanyId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value8(Integer value) {
		setInstructorId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value9(Date value) {
		setCreatedOn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord value10(Integer value) {
		setCreatedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InstructionSessionRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, Timestamp value6, Integer value7, Integer value8, Date value9, Integer value10) {
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
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached InstructionSessionRecord
	 */
	public InstructionSessionRecord() {
		super(InstructionSession.INSTRUCTION_SESSION);
	}

	/**
	 * Create a detached, initialised InstructionSessionRecord
	 */
	public InstructionSessionRecord(Integer id, Integer studentId, Integer coursePackageId, Integer priceOverride, Integer paymentReceived, Timestamp sessionDatetime, Integer companyId, Integer instructorId, Date createdOn, Integer createdBy) {
		super(InstructionSession.INSTRUCTION_SESSION);

		setValue(0, id);
		setValue(1, studentId);
		setValue(2, coursePackageId);
		setValue(3, priceOverride);
		setValue(4, paymentReceived);
		setValue(5, sessionDatetime);
		setValue(6, companyId);
		setValue(7, instructorId);
		setValue(8, createdOn);
		setValue(9, createdBy);
	}
}
