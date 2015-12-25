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
import com.dsmc.data.tables.records.AdminUserRecord;
import com.dsmc.data.tables.records.AuthCredentialRecord;
import com.dsmc.data.tables.records.CompanyRecord;
import com.dsmc.data.tables.records.CoursePackageRecord;
import com.dsmc.data.tables.records.InstructionSessionRecord;
import com.dsmc.data.tables.records.InstructorRecord;
import com.dsmc.data.tables.records.StudentEnrollmentRecord;
import com.dsmc.data.tables.records.StudentRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>public</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final Identity<AdminUserRecord, Integer> IDENTITY_ADMIN_USER = Identities0.IDENTITY_ADMIN_USER;
	public static final Identity<CompanyRecord, Integer> IDENTITY_COMPANY = Identities0.IDENTITY_COMPANY;
	public static final Identity<CoursePackageRecord, Integer> IDENTITY_COURSE_PACKAGE = Identities0.IDENTITY_COURSE_PACKAGE;
	public static final Identity<InstructionSessionRecord, Integer> IDENTITY_INSTRUCTION_SESSION = Identities0.IDENTITY_INSTRUCTION_SESSION;
	public static final Identity<InstructorRecord, Integer> IDENTITY_INSTRUCTOR = Identities0.IDENTITY_INSTRUCTOR;
	public static final Identity<StudentRecord, Integer> IDENTITY_STUDENT = Identities0.IDENTITY_STUDENT;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<AdminUserRecord> ADMIN_USER_PKEY = UniqueKeys0.ADMIN_USER_PKEY;
	public static final UniqueKey<AdminUserRecord> USERNAME = UniqueKeys0.USERNAME;
	public static final UniqueKey<AuthCredentialRecord> AUTH_CREDENTIAL_PKEY = UniqueKeys0.AUTH_CREDENTIAL_PKEY;
	public static final UniqueKey<CompanyRecord> COMPANY_PKEY = UniqueKeys0.COMPANY_PKEY;
	public static final UniqueKey<CompanyRecord> COMPANY_COMPANY_NAME_KEY = UniqueKeys0.COMPANY_COMPANY_NAME_KEY;
	public static final UniqueKey<CoursePackageRecord> COURSE_PACKAGE_PKEY = UniqueKeys0.COURSE_PACKAGE_PKEY;
	public static final UniqueKey<InstructionSessionRecord> INSTRUCTION_SESSION_PKEY = UniqueKeys0.INSTRUCTION_SESSION_PKEY;
	public static final UniqueKey<InstructorRecord> INSTRUCTOR_PKEY = UniqueKeys0.INSTRUCTOR_PKEY;
	public static final UniqueKey<StudentRecord> STUDENT_PKEY = UniqueKeys0.STUDENT_PKEY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final ForeignKey<AdminUserRecord, CompanyRecord> ADMIN_USER__ADMIN_USER_IBFK_1 = ForeignKeys0.ADMIN_USER__ADMIN_USER_IBFK_1;
	public static final ForeignKey<CoursePackageRecord, CompanyRecord> COURSE_PACKAGE__COURSE_PACKAGE_IBFK_1 = ForeignKeys0.COURSE_PACKAGE__COURSE_PACKAGE_IBFK_1;
	public static final ForeignKey<InstructionSessionRecord, StudentRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_STUDENT_ID_FKEY = ForeignKeys0.INSTRUCTION_SESSION__INSTRUCTION_SESSION_STUDENT_ID_FKEY;
	public static final ForeignKey<InstructionSessionRecord, CoursePackageRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_COURSE_PACKAGE_ID_FKEY = ForeignKeys0.INSTRUCTION_SESSION__INSTRUCTION_SESSION_COURSE_PACKAGE_ID_FKEY;
	public static final ForeignKey<InstructionSessionRecord, CompanyRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_COMPANY_ID_FKEY = ForeignKeys0.INSTRUCTION_SESSION__INSTRUCTION_SESSION_COMPANY_ID_FKEY;
	public static final ForeignKey<InstructionSessionRecord, InstructorRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_INSTRUCTOR_ID_FKEY = ForeignKeys0.INSTRUCTION_SESSION__INSTRUCTION_SESSION_INSTRUCTOR_ID_FKEY;
	public static final ForeignKey<InstructorRecord, CompanyRecord> INSTRUCTOR__INSTRUCTOR_IBFK_1 = ForeignKeys0.INSTRUCTOR__INSTRUCTOR_IBFK_1;
	public static final ForeignKey<StudentRecord, CompanyRecord> STUDENT__STUDENT_IBFK_1 = ForeignKeys0.STUDENT__STUDENT_IBFK_1;
	public static final ForeignKey<StudentEnrollmentRecord, StudentRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_2 = ForeignKeys0.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_2;
	public static final ForeignKey<StudentEnrollmentRecord, CoursePackageRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_1 = ForeignKeys0.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_1;
	public static final ForeignKey<StudentEnrollmentRecord, CompanyRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_3 = ForeignKeys0.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_3;
	public static final ForeignKey<StudentEnrollmentRecord, InstructorRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_4 = ForeignKeys0.STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_4;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends AbstractKeys {
		public static Identity<AdminUserRecord, Integer> IDENTITY_ADMIN_USER = createIdentity(AdminUser.ADMIN_USER, AdminUser.ADMIN_USER.ID);
		public static Identity<CompanyRecord, Integer> IDENTITY_COMPANY = createIdentity(Company.COMPANY, Company.COMPANY.ID);
		public static Identity<CoursePackageRecord, Integer> IDENTITY_COURSE_PACKAGE = createIdentity(CoursePackage.COURSE_PACKAGE, CoursePackage.COURSE_PACKAGE.ID);
		public static Identity<InstructionSessionRecord, Integer> IDENTITY_INSTRUCTION_SESSION = createIdentity(InstructionSession.INSTRUCTION_SESSION, InstructionSession.INSTRUCTION_SESSION.ID);
		public static Identity<InstructorRecord, Integer> IDENTITY_INSTRUCTOR = createIdentity(Instructor.INSTRUCTOR, Instructor.INSTRUCTOR.ID);
		public static Identity<StudentRecord, Integer> IDENTITY_STUDENT = createIdentity(Student.STUDENT, Student.STUDENT.ID);
	}

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<AdminUserRecord> ADMIN_USER_PKEY = createUniqueKey(AdminUser.ADMIN_USER, AdminUser.ADMIN_USER.ID);
		public static final UniqueKey<AdminUserRecord> USERNAME = createUniqueKey(AdminUser.ADMIN_USER, AdminUser.ADMIN_USER.USERNAME, AdminUser.ADMIN_USER.COMPANY_ID);
		public static final UniqueKey<AuthCredentialRecord> AUTH_CREDENTIAL_PKEY = createUniqueKey(AuthCredential.AUTH_CREDENTIAL, AuthCredential.AUTH_CREDENTIAL.LOGIN);
		public static final UniqueKey<CompanyRecord> COMPANY_PKEY = createUniqueKey(Company.COMPANY, Company.COMPANY.ID);
		public static final UniqueKey<CompanyRecord> COMPANY_COMPANY_NAME_KEY = createUniqueKey(Company.COMPANY, Company.COMPANY.COMPANY_NAME);
		public static final UniqueKey<CoursePackageRecord> COURSE_PACKAGE_PKEY = createUniqueKey(CoursePackage.COURSE_PACKAGE, CoursePackage.COURSE_PACKAGE.ID);
		public static final UniqueKey<InstructionSessionRecord> INSTRUCTION_SESSION_PKEY = createUniqueKey(InstructionSession.INSTRUCTION_SESSION, InstructionSession.INSTRUCTION_SESSION.ID);
		public static final UniqueKey<InstructorRecord> INSTRUCTOR_PKEY = createUniqueKey(Instructor.INSTRUCTOR, Instructor.INSTRUCTOR.ID);
		public static final UniqueKey<StudentRecord> STUDENT_PKEY = createUniqueKey(Student.STUDENT, Student.STUDENT.ID);
	}

	private static class ForeignKeys0 extends AbstractKeys {
		public static final ForeignKey<AdminUserRecord, CompanyRecord> ADMIN_USER__ADMIN_USER_IBFK_1 = createForeignKey(com.dsmc.data.Keys.COMPANY_PKEY, AdminUser.ADMIN_USER, AdminUser.ADMIN_USER.COMPANY_ID);
		public static final ForeignKey<CoursePackageRecord, CompanyRecord> COURSE_PACKAGE__COURSE_PACKAGE_IBFK_1 = createForeignKey(com.dsmc.data.Keys.COMPANY_PKEY, CoursePackage.COURSE_PACKAGE, CoursePackage.COURSE_PACKAGE.COMPANY_ID);
		public static final ForeignKey<InstructionSessionRecord, StudentRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_STUDENT_ID_FKEY = createForeignKey(com.dsmc.data.Keys.STUDENT_PKEY, InstructionSession.INSTRUCTION_SESSION, InstructionSession.INSTRUCTION_SESSION.STUDENT_ID);
		public static final ForeignKey<InstructionSessionRecord, CoursePackageRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_COURSE_PACKAGE_ID_FKEY = createForeignKey(com.dsmc.data.Keys.COURSE_PACKAGE_PKEY, InstructionSession.INSTRUCTION_SESSION, InstructionSession.INSTRUCTION_SESSION.COURSE_PACKAGE_ID);
		public static final ForeignKey<InstructionSessionRecord, CompanyRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_COMPANY_ID_FKEY = createForeignKey(com.dsmc.data.Keys.COMPANY_PKEY, InstructionSession.INSTRUCTION_SESSION, InstructionSession.INSTRUCTION_SESSION.COMPANY_ID);
		public static final ForeignKey<InstructionSessionRecord, InstructorRecord> INSTRUCTION_SESSION__INSTRUCTION_SESSION_INSTRUCTOR_ID_FKEY = createForeignKey(com.dsmc.data.Keys.INSTRUCTOR_PKEY, InstructionSession.INSTRUCTION_SESSION, InstructionSession.INSTRUCTION_SESSION.INSTRUCTOR_ID);
		public static final ForeignKey<InstructorRecord, CompanyRecord> INSTRUCTOR__INSTRUCTOR_IBFK_1 = createForeignKey(com.dsmc.data.Keys.COMPANY_PKEY, Instructor.INSTRUCTOR, Instructor.INSTRUCTOR.COMPANY_ID);
		public static final ForeignKey<StudentRecord, CompanyRecord> STUDENT__STUDENT_IBFK_1 = createForeignKey(com.dsmc.data.Keys.COMPANY_PKEY, Student.STUDENT, Student.STUDENT.COMPANY_ID);
		public static final ForeignKey<StudentEnrollmentRecord, StudentRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_2 = createForeignKey(com.dsmc.data.Keys.STUDENT_PKEY, StudentEnrollment.STUDENT_ENROLLMENT, StudentEnrollment.STUDENT_ENROLLMENT.STUDENT_ID);
		public static final ForeignKey<StudentEnrollmentRecord, CoursePackageRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_1 = createForeignKey(com.dsmc.data.Keys.COURSE_PACKAGE_PKEY, StudentEnrollment.STUDENT_ENROLLMENT, StudentEnrollment.STUDENT_ENROLLMENT.COURSE_PACKAGE_ID);
		public static final ForeignKey<StudentEnrollmentRecord, CompanyRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_3 = createForeignKey(com.dsmc.data.Keys.COMPANY_PKEY, StudentEnrollment.STUDENT_ENROLLMENT, StudentEnrollment.STUDENT_ENROLLMENT.COMPANY_ID);
		public static final ForeignKey<StudentEnrollmentRecord, InstructorRecord> STUDENT_ENROLLMENT__STUDENT_ENROLLMENT_IBFK_4 = createForeignKey(com.dsmc.data.Keys.INSTRUCTOR_PKEY, StudentEnrollment.STUDENT_ENROLLMENT, StudentEnrollment.STUDENT_ENROLLMENT.INSTRUCTOR_ID);
	}
}
