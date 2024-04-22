package Academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewCoordinator {
	private BookingCoordinator bookingCoordinator;
	private Student student;
	public Scanner userEnter;
	private String reviewId;
	private String review;
	private int rating;
	private String studentId;
	private String trainerId;
	private String bookingId;
	public List<ReviewCoordinator> studentReviews;
	
	public String getReviewId() {
		return reviewId;
	}
	public String getStudentId() {
		return studentId;
	}

	public String getReview() {
		return review;
	}

	public int getRating() {
		return rating;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public String getBookingId() {
		return bookingId;
	}
	
	public void setAllObjects(Academy academy) {
		this.student=academy.getStudent();
		this.bookingCoordinator=academy.getBookingCoordinator();

	}

	// create constructor to add review
	public ReviewCoordinator(String reviewId,String studentId, String review, int rating, String trainerId, String bookingId) {
		this.reviewId = reviewId;
		this.studentId=studentId;
		this.review = review;
		this.rating = rating;
		this.trainerId = trainerId;
		this.bookingId = bookingId;
	}

	// create default constructor
	public ReviewCoordinator() {
		this.userEnter = new Scanner(System.in);
		this.studentReviews = new ArrayList<>();
	}

	

	// lesson review
	public void lessonReviews(String studentId, String bookingId) {
		int id = studentReviews.size() + 1;
		boolean saveReviews = false;
		String reviews = null;
		String studentRating = null;
		System.out.println("\nLesson reviews\n");
		System.out.println("\n1: Very dissatisfied\n2: Dissatisfied\n3: Ok\n4: Satisfied\n5: Very Satisfied\n");
		do {
			if (reviews == null) {
				System.out.print("Enter review for attended lesson : ");
				reviews = userEnter.nextLine().trim();
				if (reviews.isEmpty()) {
					System.out.println("Invalid ! review field cannot be empty ");
					reviews = null;
				}
			} else if (studentRating == null) {
				System.out.print("Enter rating (1 to 5) from above list : ");
				studentRating = userEnter.nextLine().trim();
				if (studentRating.isEmpty() || !studentRating.matches("[1-5]")) {
					System.out.println("Invalid ! Please give correct rating for this lesson 1 to 5");
					studentRating = null;
				}
			} else {
				// create object
				rating = Integer.parseInt(studentRating);
				if (id >= 0 && id <= 9) {
					reviewId = "RI0" + id;
				} else {
					reviewId = "RI0" + id;
				}
				// get trainer id from booking id
				String trainerId = bookingCoordinator.getBookingById(bookingId).getTrainerId();
				// create object to save review
				ReviewCoordinator reviewObj = new ReviewCoordinator(reviewId,studentId, reviews, rating, trainerId, bookingId);
				//add reviews in studentReviews
				studentReviews.add(reviewObj);
				saveReviews = true;
			}

		} while (!saveReviews);

	}
	
	//show review information
	public List<ReviewCoordinator> reviewInformation(String studentId, String trainerId, int month, String option) {
		List<ReviewCoordinator> filteredReviews = new ArrayList<>();
		String lessonId;
		for (ReviewCoordinator reviewData : studentReviews) {
			lessonId = bookingCoordinator.getBookingById(reviewData.getBookingId()).getLessonId();
			String lessonDate = new LessonTimetable().getLessonInfoById(lessonId).getLesson_date();
			int reviewMonth = Integer.parseInt(lessonDate.substring(3, 5));
			if (!studentId.isEmpty()) {
				if (reviewData.getStudentId().equalsIgnoreCase(studentId)) {
					filteredReviews.add(reviewData);
				}
			} else if (!trainerId.isEmpty()) {
				if ((reviewData.getTrainerId().equalsIgnoreCase(trainerId) && reviewMonth == month)
						|| (reviewData.getTrainerId().equalsIgnoreCase(trainerId) && month == 0)) {
					filteredReviews.add(reviewData);
				}
			} else {
				filteredReviews.add(reviewData);
			}
		}

		if (!option.isEmpty()) {
			if (!filteredReviews.isEmpty()) {
				System.out.println(
						",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
				System.out.printf("%-15s  %-12s  %-12s  %-15s  %-15s  %-12s  %-10s  %-30s\n", "Review Id", "Booking Id",
						"Lesson Id", "Lesson Name", "Student Id", "Trainer", "Rating", "Review");
				System.out.println(
						",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
				for (ReviewCoordinator review : filteredReviews) {
					lessonId = bookingCoordinator.getBookingById(review.getBookingId()).getLessonId();
					String lessonTitle = new LessonTimetable().getLessonInfoById(lessonId).getLesson_title();
					System.out.printf("%-15s  %-12s  %-12s  %-15s  %-15s  %-12s  %-10s  %-30s\n", review.getReviewId(),
							review.getBookingId(), lessonId, lessonTitle,
							student.getStudentInfoById(review.getStudentId()).getStudentName(),
							new Trainer().getTrainerInfo(review.getTrainerId()).getTrainerName(), review.getRating(),
							review.getReview());
				}
				System.out.println(
						",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
			} else {
				System.out.println("Invalid ! no review data available");
			}
		}

		return filteredReviews;
	}
	
}



