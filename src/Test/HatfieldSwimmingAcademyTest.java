package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import Academy.Academy;
import Academy.BookingCoordinator;
import Academy.LessonTimetable;
import Academy.ReviewCoordinator;
import Academy.Student;
import Academy.Trainer;

class HatfieldSwimmingAcademyTest {
	private Academy academy;
	private Student student;
	private LessonTimetable lessonTimetable;
	private BookingCoordinator bookingCoordinator;
	private ReviewCoordinator reviewCoordinator;

	public HatfieldSwimmingAcademyTest() {
		this.academy = Academy.getAcademyObj();
		this.student = academy.getStudent();
		student.setAllObject(academy);
		this.lessonTimetable = academy.getLessonTimetable();
		lessonTimetable.setAllObject(academy);
		this.bookingCoordinator = academy.getBookingCoordinator();
		bookingCoordinator.setAllObject(academy);
		this.reviewCoordinator = academy.getReviewCoordinator();
		reviewCoordinator.setAllObjects(academy);
	}

	// test filter timetable by trainer
	@Test
	public void testfilterTimetableByTrainer() {
		String trainerId = "TRN01";
		System.out.println("Test details     : Show timetable of trainer");
		System.out.println("Trainer name     : " + new Trainer().getTrainerInfo(trainerId).getTrainerName());
		lessonTimetable.userEnter = new Scanner(trainerId);
		List<LessonTimetable> trainerLessons = lessonTimetable.filterTimetableByTrainer();
		assertNotNull(trainerLessons);
		System.out.println();
		// display Wednesday lesson
		lessonTimetable.displayLessonInfo(trainerLessons);

	}

	//test save student booking info
	@Test
	void testSaveStudentBoookingInfo0() {
		String studentId = "ST01";
		String lessonId = "LT38";
		String expStatus = "Booked";
		System.out.println("\nTest details : booking lesson by student");
		System.out.println("Student name : " + student.getStudentInfoById(studentId).getStudentName());
		System.out.println("Lesson name  : " + lessonTimetable.getLessonInfoById(lessonId).getLesson_title());
		int currentSeat = lessonTimetable.getLessonInfoById(lessonId).getLesson_seat();
		academy.saveStudentBoookingInfo(studentId, lessonId);
		int expSeat = currentSeat - 1;
		int actualSeat = lessonTimetable.getLessonInfoById(lessonId).getLesson_seat();
		String bookingId = "BI01";
		String actualStatus = bookingCoordinator.getBookingById(bookingId).getStatus();
		assertEquals(expSeat, actualSeat);
		assertEquals(expStatus, actualStatus);
		System.out.println("test passed");
	}

	// test update booking lessons
	@Test
	public void testUpdateBookingDetails1() {
		String bookingId = "BI01";
		String studentId = "ST01";
		String latestLessonId = "LT40";
		String expStatus = "Changed";
		String lastLessonId = bookingCoordinator.getBookingById(bookingId).getLessonId();
		int expLastLessonSeat = lessonTimetable.getLessonInfoById(lastLessonId).getLesson_seat() + 1;
		int expLatestLessonSeat = lessonTimetable.getLessonInfoById(latestLessonId).getLesson_seat() - 1;
		System.out.println("\nTest details        : update new lesson in bookings");
		System.out.println("Booking Id          : " + bookingId);
		System.out
				.println("Last lesson name    : " + lessonTimetable.getLessonInfoById(lastLessonId).getLesson_title());
		System.out.println(
				"Latest lesson name  : " + lessonTimetable.getLessonInfoById(latestLessonId).getLesson_title());
		bookingCoordinator.userEnter = new Scanner("1\n40");
		lessonTimetable.userEnter = new Scanner("e\n");
		bookingCoordinator.updateLesson(studentId);
		String actualStatus = bookingCoordinator.getBookingById(bookingId).getStatus();
		int actualLastLessonSeat = lessonTimetable.getLessonInfoById(lastLessonId).getLesson_seat();
		int actualLatestLessonSeat = lessonTimetable.getLessonInfoById(latestLessonId).getLesson_seat();
		assertEquals(expStatus, actualStatus);
		assertEquals(expLastLessonSeat, actualLastLessonSeat);
		assertEquals(expLatestLessonSeat, actualLatestLessonSeat);
		System.out.println("\ntest passed\n");
	}

	//test student review
	@Test
	public void testStudentReview() {
		String studentId = "ST01";
		String bookingId = "BI01";
		String rating = "5";
		String review = "Very satisfied";
		System.out.println("\nTest details     : Save student reviews");
		System.out.println("Student name     : " + student.getStudentInfoById(studentId).getStudentName());
		System.out.println("Booking Id       : " + bookingId);
		System.out.println("Student review   : " + review);
		System.out.println("Student rating   : " + rating);
		reviewCoordinator.userEnter = new Scanner(review + "\n" + rating);
		reviewCoordinator.lessonReviews(studentId, bookingId);
		assertNotNull(reviewCoordinator.studentReviews);
		// show review
		reviewCoordinator.reviewInformation(studentId, "", 0, "Show");
	}

	@Test
	public void testfilterTimetableByDay() {
		String day = "Wednesday";
		System.out.println("\nTest details     : Show timetable of wednesday");
		System.out.println("Day              : " + day);
		lessonTimetable.userEnter = new Scanner(day);
		List<LessonTimetable> wednesdayLessons = lessonTimetable.filterTimetableByDay();
		assertNotNull(wednesdayLessons);
		// display Wednesday lesson
		lessonTimetable.displayLessonInfo(wednesdayLessons);

	}

}
