package Academy;

import java.util.Scanner;

public class Management {
	private Scanner userEnter;
	private Student student;
	private ReviewCoordinator reviewCoordinator;
	private BookingCoordinator bookingCoordinator;
	private ReportCoordinator reportCoordinator;
	
	//create constructor
	public Management() {
		this.userEnter = new Scanner(System.in);
	}

	// set all object
	public void setAllObject(Academy academy) {
		this.student = academy.getStudent();
		this.reviewCoordinator = academy.getReviewCoordinator();
		this.bookingCoordinator = academy.getBookingCoordinator();
		this.reportCoordinator = academy.getReportCoordinator();
		academy.getTrainer();
		academy.getLessonTimetable();
	}

	// management option
	public void managementOption() {
		String option;
		System.out.println("\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		System.out.println("Management Menu");
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		do {
			System.out.println("\nA. Student management");
			System.out.println("B. Booking management");
			System.out.println("C. Review management");
			System.out.println("D. Student report management");
			System.out.println("E. Trainer report management");
			System.out.println("F. Logout\n");
			System.out.print("Select one option : ");
			option = userEnter.nextLine().trim();
			if (option.equalsIgnoreCase("A")) {
				student.showStudents();
			} else if (option.equalsIgnoreCase("B")) {
				bookingCoordinator.myBookings(bookingCoordinator.studentBookings);
			} else if (option.equalsIgnoreCase("C")) {
				reviewCoordinator.reviewInformation("", "", 0, "Show");
			} else if (option.equalsIgnoreCase("D")) {
				reportCoordinator.studentReport();
			} else if (option.equalsIgnoreCase("E")) {
				reportCoordinator.trainerReport();
			} else if (option.equalsIgnoreCase("F")) {
				System.out.println("\nManagement logged out successfully");
				return;
			} else {
				System.out.println("\n Invalid ! please select a valid option");
			}

		} while (!option.equals("f"));

	}

}
