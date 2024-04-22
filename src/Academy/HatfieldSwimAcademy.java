package Academy;

import java.util.Scanner;

public class HatfieldSwimAcademy {
	private Scanner userEnter;
	private Student student;
	private Management management;
	private String managementEmail = "management@gmail.com";
	private final String managementPassword = "manage123";
	private BookingCoordinator bookingCoordinator;
	private ReviewCoordinator reviewCoordinator;
	private ReportCoordinator reportCoordinator;
	private LessonTimetable lessonTimetable;

	public HatfieldSwimAcademy(Academy academyObj) {
		userEnter = new Scanner(System.in);
		this.student = academyObj.getStudent();
		student.setAllObject(academyObj);
		this.reportCoordinator = academyObj.getReportCoordinator();
		reportCoordinator.setAllObject(academyObj);
		this.reviewCoordinator = academyObj.getReviewCoordinator();
		reviewCoordinator.setAllObjects(academyObj);
		this.bookingCoordinator = academyObj.getBookingCoordinator();
		bookingCoordinator.setAllObject(academyObj);
		this.lessonTimetable = academyObj.getLessonTimetable();
		lessonTimetable.setAllObject(academyObj);
		this.management = academyObj.getManagement();
		management.setAllObject(academyObj);
	}

	public void openApp() {
		String userOption;
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		System.out.println("\tHatfield Swim Academy");
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		do {
			System.out.println("\nA. sign up as new student");
			System.out.println("B. View timetable");
			System.out.println("C. view trainer details");
			System.out.println("D. Login");
			System.out.println("E. Close App");
			System.out.print("Select one option : ");
			userOption = userEnter.nextLine().trim();
			if (userOption.equalsIgnoreCase("A")) {
				student.studentRegister();
			} else if (userOption.equalsIgnoreCase("B")) {
				System.out.println("\nLesson Timetable");
				// show timetable to select lesson for booking
				bookingCoordinator.filterLessonTimetable = lessonTimetable.lessonTimetables;
				lessonTimetable.displayLessonInfo(bookingCoordinator.filterLessonTimetable);
				// filter lesson
				lessonTimetable.filterLessonTimetable("");
			} else if (userOption.equalsIgnoreCase("C")) {
				System.out.println("\nTrainer information\n");
				new Trainer().showTrainers();
			} else if (userOption.equalsIgnoreCase("D")) {
				loginForm();
			} else if (userOption.equalsIgnoreCase("E")) {
				System.exit(0);
			} else {
				System.out.println("\n Invalid ! please select a valid option");
			}
		} while (!userOption.equalsIgnoreCase("E"));
	}

	// login form
	public void loginForm() {
		String emailId;
		String password;
		boolean login = false;
		System.out.println("\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		System.out.println("\tLogin form");
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,\n");
		// Get email for validation
		System.out.print("Enter your emailId : ");
		emailId = userEnter.nextLine().trim();
		// Check entered email is not empty
		if (!emailId.isEmpty()) {
			// Get password from student
			System.out.print("Enter your Password : ");
			password = userEnter.nextLine().trim();
			// Check password is not empty
			if (!password.isEmpty()) {
				for (Student studentInfo : student.academyStudents) {
					// Validate email and password of student
					if (studentInfo.getStudentEmail().equalsIgnoreCase(emailId)
							&& studentInfo.getStudentPassword().equals(password)) {
						String studentId = studentInfo.getStudentId();
						System.out.println("\nStudent logged in successfully");
						// call student option
						student.manageStudent(studentId);
						login = true;
						break;

					} else if (managementEmail.equalsIgnoreCase(emailId)
							&& managementPassword.equalsIgnoreCase(password)) {
						System.out.println("\nManagement logged in successfully");
						// management login success
						management.managementOption();
						login = true;
						break;
					}
				}
				if (!login) {
					// Display error message
					System.out.println("\nError: Incorrect username or password");
				}
			} else {
				// Display error message
				System.out.println("\nError: You entered a wrong password");
			}
		} else {
			// Display error message
			System.out.println("\nError: You entered a wrong username");
		}
	}

	public static void main(String[] args) {
		Academy academyObj = Academy.getAcademyObj();
		HatfieldSwimAcademy main = new HatfieldSwimAcademy(academyObj);
		 main.openApp();

	}

}
