package Academy;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Scanner;

public class ReportCoordinator {
	private Scanner userEnter;
	private Student student;
	private Trainer trainer;
	private BookingCoordinator bookingCoordinator;
	private ReviewCoordinator reviewCoordinator;
	private LessonTimetable lessonTimetable;
	private String monthName;
	private String[] months;
	
	
	public ReportCoordinator() {
		months = new DateFormatSymbols().getMonths();
		this.userEnter = new Scanner(System.in);
	}
	
	//set all object
	public void setAllObject(Academy academy) {
		this.student = academy.getStudent();
		this.bookingCoordinator = academy.getBookingCoordinator();
		this.reviewCoordinator = academy.getReviewCoordinator();
		this.trainer = academy.getTrainer();
		this.lessonTimetable = academy.getLessonTimetable();
	}
	

	public void studentReport() {
		String selectMonth;
		boolean reportAvailable = false;
		int totalBooking = 0;
		int totalAttended = 0;
		int totalCancelled = 0;
		int bookingMonth;
		// get month from user to generate report
		System.out.print("Select one month to generate student report : ");
		selectMonth = userEnter.nextLine().trim();
		if (selectMonth.isEmpty() || selectMonth.matches("\\d+")) {
			System.out.println("Invalid ! incorrect month name.");
		} else {
			this.monthName = selectMonth;
			bookingMonth = getMonthNumber(selectMonth);
			if (bookingMonth == 0) {
				System.out.println("Invalid ! entered incorrect month name.");
			} else {
				for (Student studentData : student.academyStudents) {
					String studentId = studentData.getStudentId();
					List<BookingCoordinator> studentBookings = bookingCoordinator
							.fetchLessonBookingsByStudent(studentId, 0);
					if (!studentBookings.isEmpty()) {
						for (BookingCoordinator bookingData : studentBookings) {
							if (bookingData.getStatus().equalsIgnoreCase("Booked")) {
								totalBooking++;
							} else if (bookingData.getStatus().equalsIgnoreCase("Attended")) {
								totalAttended++;
							} else if (bookingData.getStatus().equalsIgnoreCase("Cancelled")) {
								totalCancelled++;
							}
						}
						// validation of student booking not 0
						if (!(totalBooking == 0 && totalAttended == 0 && totalCancelled == 0)) {
							System.out.println("Report month  : " + monthName + "\n");
							// display student booking details
							System.out.println(
									"\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"
											+ ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
							System.out.printf(
									"%-10s %-12s %-15s %-12s  %-17s  %-12s  %-15s  %-15s  %-20s  %-15s  %-15s  %-15s  %-15s \n",
									"Booking Id", "Student id", "Student Name", "Lesson Id", "Lesson Name",
									"Lesson Day", "Lesson Time", "Lesson Date", "Status", "Total booked",
									"Total Attend", "Total cancel", "Trainer");
							System.out.println(
									",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"
											+ ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
							// Print booking details
							for (BookingCoordinator bookingInfo : studentBookings) {
								String lessonId = bookingInfo.getLessonId();
								// Get LessonTimetable object by lesson id
								LessonTimetable timetable = lessonTimetable.getLessonInfoById(lessonId);
								System.out.printf(
										"%-10s %-12s  %-15s %-12s  %-17s  %-12s  %-15s  %-15s  %-20s  %-15s  %-15s  %-15s  %-15s \n",
										bookingInfo.getBookingId(), bookingInfo.getStudentId(),
										student.getStudentInfoById(bookingInfo.getStudentId()).getStudentName(),
										bookingInfo.getLessonId(), timetable.getLesson_title(),
										timetable.getLesson_day(), timetable.getLesson_time(),
										timetable.getLesson_date(), bookingInfo.getStatus(), totalBooking,
										totalAttended, totalCancelled,
										student.getStudentInfoById(bookingInfo.getStudentId()).getStudentName(),
										new Trainer().getTrainerInfo(bookingInfo.getTrainerId()).getTrainerName());
							}
							System.out.println(
									",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"
											+ ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

							reportAvailable = true;
						}
					}
				}
				if (!reportAvailable) {
					System.out.println("No report available");
				}
			}
		}
	}

	public void trainerReport() {
		int reviewMonth;
		boolean reportAvailable = false;
		String selectMonth;
		String trainerId;
		double trainerRatings = 0;
		int totalRatings = 0;
		int totalLessons = 0;
		// get month from user to generate report
		System.out.print("Select one month to generate student report : ");
		selectMonth = userEnter.nextLine().trim();
		if (selectMonth.isEmpty() || selectMonth.matches("\\d+")) {
			System.out.println("Invalid ! incorrect month name.");
		} else {
			this.monthName = selectMonth;
			reviewMonth = getMonthNumber(selectMonth);
			if (reviewMonth == 0) {
				System.out.println("Invalid ! entered incorrect month name.");
			} else {
				for (Trainer trainerData : trainer.swimTrainers) {
					trainerId = trainerData.getTrainerId();
					// get trainer review data
					List<ReviewCoordinator> trainerReviews = reviewCoordinator.reviewInformation("", trainerId,
							reviewMonth, "");
					for (ReviewCoordinator reviewData : trainerReviews) {
						totalRatings += reviewData.getRating();
						totalLessons++;
					}
					if (!(totalRatings == 0 && totalLessons == 0)) {
						trainerRatings = totalRatings / totalLessons;
					}
					if (trainerRatings != 0) {
						// print trainer review data
						System.out.println("Report month    : " + monthName + "\n");
						// display trainer rating details
						System.out.println(
								",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
						System.out.printf("%-10s %-12s  %-15s  %-12s  %-15s  %-12s  %-10s %-10s  %-10s  %-30s\n",
								"Review Id", "Lesson Id", "Lesson Name", "Trainer Id", "Trainer Name", "Student Id",
								"Student Name", "Average Rating", "Rating", "Review");
						System.out.println(
								",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
						for (ReviewCoordinator reviewData : trainerReviews) {
							String lessonId = bookingCoordinator.getBookingById(reviewData.getBookingId())
									.getLessonId();
							String trainerName = trainer.getTrainerInfo(trainerId).getTrainerName();
							System.out.printf("%-10s %-12s  %-15s  %-12s  %-15s  %-12s  %-17s %-10s  %-10s  %-30s\n",
									reviewData.getReviewId(), lessonId,
									lessonTimetable.getLessonInfoById(lessonId).getLesson_title(), trainerId,
									trainerName, reviewData.getStudentId(),
									student.getStudentInfoById(reviewData.getStudentId()).getStudentName(),
									trainerRatings, reviewData.getRating(), reviewData.getReview());
						}
						System.out.println(
								",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
						reportAvailable = true;
					}
				}
			}
		}
		if (!reportAvailable) {
			System.out.println("No report available");
		}
	}

	public int getMonthNumber(String selectMonth) {
		for (int i = 0; i < months.length - 1; i++) {
			if (months[i].equalsIgnoreCase(selectMonth) || months[i].substring(0, 3).equalsIgnoreCase(selectMonth)) {
				return i + 1;
			}
		}
		return 0;
	}

}
