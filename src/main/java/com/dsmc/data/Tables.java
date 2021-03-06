/**
 * This class is generated by jOOQ
 */
package com.dsmc.data;


import com.dsmc.data.tables.AdminUser;
import com.dsmc.data.tables.AuthCredential;
import com.dsmc.data.tables.Company;
import com.dsmc.data.tables.CoursePackage;
import com.dsmc.data.tables.InstructionSession;
import com.dsmc.data.tables.Instructor;
import com.dsmc.data.tables.Student;
import com.dsmc.data.tables.StudentEnrollment;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table public.admin_user
	 */
	public static final AdminUser ADMIN_USER = com.dsmc.data.tables.AdminUser.ADMIN_USER;

	/**
	 * The table public.auth_credential
	 */
	public static final AuthCredential AUTH_CREDENTIAL = com.dsmc.data.tables.AuthCredential.AUTH_CREDENTIAL;

	/**
	 * The table public.company
	 */
	public static final Company COMPANY = com.dsmc.data.tables.Company.COMPANY;

	/**
	 * The table public.course_package
	 */
	public static final CoursePackage COURSE_PACKAGE = com.dsmc.data.tables.CoursePackage.COURSE_PACKAGE;

	/**
	 * The table public.instruction_session
	 */
	public static final InstructionSession INSTRUCTION_SESSION = com.dsmc.data.tables.InstructionSession.INSTRUCTION_SESSION;

	/**
	 * The table public.instructor
	 */
	public static final Instructor INSTRUCTOR = com.dsmc.data.tables.Instructor.INSTRUCTOR;

	/**
	 * The table public.student
	 */
	public static final Student STUDENT = com.dsmc.data.tables.Student.STUDENT;

	/**
	 * The table public.student_enrollment
	 */
	public static final StudentEnrollment STUDENT_ENROLLMENT = com.dsmc.data.tables.StudentEnrollment.STUDENT_ENROLLMENT;
}
