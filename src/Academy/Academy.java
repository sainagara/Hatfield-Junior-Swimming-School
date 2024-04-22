package Academy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Academy {
	private static Academy academyObj = null;
	private Student student;
	private Management management;
	private Trainer trainer;
	private LessonTimetable lessonTimetable;
	private BookingCoordinator bookingCoordinator;
	private ReviewCoordinator reviewCoordinator;
	private ReportCoordinator reportCoordinator;

	public Student getStudent() {
		return student;
	}

	public Management getManagement() {
		return management;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public LessonTimetable getLessonTimetable() {
		return lessonTimetable;
	}

	public BookingCoordinator getBookingCoordinator() {
		return bookingCoordinator;
	}

	public ReviewCoordinator getReviewCoordinator() {
		return reviewCoordinator;
	}

	public ReportCoordinator getReportCoordinator() {
		return reportCoordinator;
	}

	public static Academy getAcademyObj() {
		if (academyObj == null) {
			academyObj = new Academy();
		}
		return academyObj;
	}

	// create constructor to initialized object
	public Academy() {
		this.student = new Student();
		this.trainer = new Trainer();
		this.lessonTimetable = new LessonTimetable();
		this.reportCoordinator = new ReportCoordinator();
		this.reviewCoordinator = new ReviewCoordinator();
		this.bookingCoordinator = new BookingCoordinator();
		this.management = new Management();
	}

	// save student booking details
	public void saveStudentBoookingInfo(String studentId, String lessonId) {
		String bookingId;
		String bookingStatus = "Booked";
		// set booking id
		int id = bookingCoordinator.studentBookings.size();
		if (id >= 0 && id <= 9) {
			bookingId = "BI0" + (id + 1);
		} else {
			bookingId = "BI" + (id + 1);
		}

		String bookingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String trainerId = lessonTimetable.getLessonInfoById(lessonId).getTrainer_id();
		// create booking object
		BookingCoordinator booking = new BookingCoordinator(bookingId, bookingDate, bookingStatus, lessonId, studentId,
				trainerId);
		bookingCoordinator.studentBookings.add(booking);
		// get current lesson seat
		int lessonSeat = lessonTimetable.getLessonInfoById(lessonId).getLesson_seat();
		// update lesson seat after booking
		lessonTimetable.getLessonInfoById(lessonId).setLesson_seat(lessonSeat - 1);
	}

	// update booking details
	public void updateBookingDetails(String bookingId, String latestLessonId) {
		// get last lesson id
		String lastLessonId = bookingCoordinator.getBookingById(bookingId).getLessonId();
		String trainerId = lessonTimetable.getLessonInfoById(latestLessonId).getTrainer_id();
		// get bookingCoordinator by booking id
		BookingCoordinator bookingObj = bookingCoordinator.getBookingById(bookingId);
		// update trainer id
		bookingObj.setTrainerId(trainerId);
		// update latest lesson id
		bookingObj.setLessonId(latestLessonId);
		// update last lesson seat
		// get last lesson seat
		int lastLessonSeat = lessonTimetable.getLessonInfoById(lastLessonId).getLesson_seat();
		lessonTimetable.getLessonInfoById(lastLessonId).setLesson_seat(lastLessonSeat + 1);
		// get latest lesson seat
		int latestLessonSeat = lessonTimetable.getLessonInfoById(latestLessonId).getLesson_seat();
		// update latest lesson seat
		lessonTimetable.getLessonInfoById(latestLessonId).setLesson_seat(latestLessonSeat - 1);
		// update booking status
		bookingCoordinator.getBookingById(bookingId).setStatus("Changed");
	}

	// cancel student booking
	public void studentBookingCancel(String bookingId) {
		// get lesson id from booking
		String lessonId = bookingCoordinator.getBookingById(bookingId).getLessonId();
		// get lessonTimetable object by booking id
		LessonTimetable lessonObj = lessonTimetable.getLessonInfoById(lessonId);
		// get lesson seat
		int lessonSeat = lessonTimetable.getLessonInfoById(lessonId).getLesson_seat();
		// update lesson seat
		lessonObj.setLesson_seat(lessonSeat + 1);
		// update booking status
		bookingCoordinator.getBookingById(bookingId).setStatus("Cancelled");
	}

	// update attend class
	public void updateAttendClass(String studentId, String bookingId) {
		// get lesson id from booking
		String lessonId = bookingCoordinator.getBookingById(bookingId).getLessonId();
		// get lessonTimetable object by booking id
		LessonTimetable lessonObj = lessonTimetable.getLessonInfoById(lessonId);
		// get lesson seat
		int lessonSeat = lessonTimetable.getLessonInfoById(lessonId).getLesson_seat();
		// update lesson seat
		lessonObj.setLesson_seat(lessonSeat + 1);
		// get lesson grade
		int lessonGrade = lessonObj.getLesson_grade();
		// update booking status
		bookingCoordinator.setStatus("Attended");
		// get student object
		Student studentObj = student.getStudentInfoById(studentId);
		// get student grade
		int studentGrade = studentObj.getStudentGrade();
		// validation of student grade
		if (lessonGrade > studentGrade) {
			studentObj.setStudentGrade(lessonGrade);
		}
		// update booking status
		bookingCoordinator.getBookingById(bookingId).setStatus("Attended");

	}

}
