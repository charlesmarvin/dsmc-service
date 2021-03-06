/**
 * This class is generated by jOOQ
 */
package com.dsmc.data;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

	/**
	 * The sequence <code>public.admin_user_id_seq</code>
	 */
	public static final Sequence<Long> ADMIN_USER_ID_SEQ = new SequenceImpl<Long>("admin_user_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

	/**
	 * The sequence <code>public.company_id_seq</code>
	 */
	public static final Sequence<Long> COMPANY_ID_SEQ = new SequenceImpl<Long>("company_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

	/**
	 * The sequence <code>public.course_package_id_seq</code>
	 */
	public static final Sequence<Long> COURSE_PACKAGE_ID_SEQ = new SequenceImpl<Long>("course_package_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

	/**
	 * The sequence <code>public.instruction_session_id_seq</code>
	 */
	public static final Sequence<Long> INSTRUCTION_SESSION_ID_SEQ = new SequenceImpl<Long>("instruction_session_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

	/**
	 * The sequence <code>public.instructor_id_seq</code>
	 */
	public static final Sequence<Long> INSTRUCTOR_ID_SEQ = new SequenceImpl<Long>("instructor_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

	/**
	 * The sequence <code>public.student_id_seq</code>
	 */
	public static final Sequence<Long> STUDENT_ID_SEQ = new SequenceImpl<Long>("student_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));
}
