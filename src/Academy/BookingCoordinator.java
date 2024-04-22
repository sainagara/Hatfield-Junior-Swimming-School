package Academy;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class BookingCoordinator {
	private Academy academy;
	private Student student;
	private ReviewCoordinator reviewCoordinator;
	private LessonTimetable lessonTimetable;
	public Scanner userEnter;
	private String bookingId;
	private String bookingDate;
	private String status;
	private String lessonId;
	private String studentId;
	private String trainerId;
	public List<BookingCoordinator> studentBookings;
	public List<LessonTimetable> filterLessonTimetable;

	public String getBookingId() {
		return bookingId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public String getLessonId() {
		return lessonId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public void setAllObject(Academy academy) {
		this.academy = academy;
		this.lessonTimetable = academy.getLessonTimetable();
		this.reviewCoordinator = academy.getReviewCoordinator();
		this.student = academy.getStudent();
	}

	// create booking constructor to add booking details
	public BookingCoordinator(String bookingId, String bookingDate, String status, String lessonId, String studentId,
			String trainerId) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.status = status;
		this.lessonId = lessonId;
		this.studentId = studentId;
		this.trainerId = trainerId;
	}

	// create default constructor
	public BookingCoordinator() {
		this.userEnter = new Scanner(System.in);
		this.studentBookings = new ArrayList<>();
		this.filterLessonTimetable = new ArrayList<>();
	}

	// manage booking
	public void manageBooking(String studentId) {
		String option;
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		System.out.println("\nManage Bookings");
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		do {
			System.out.println("\nA. Lesson booking");
			System.out.println("B. Cancel lesson");
			System.out.println("C. Change lesson");
			System.out.println("D. Attend lesson");
			System.out.println("E. My bookings");
			System.out.println("f. Previous menu");
			System.out.print("Select one option : ");
			option = userEnter.nextLine();
			if (option.equalsIgnoreCase("A")) {
				if (lesssonBooking(studentId)) {
					System.out.println("\nLesson bookings retrieved successfully.");
				}
			} else if (option.equalsIgnoreCase("B")) {
				if (cancelLesson(studentId)) {
					System.out.println("\nLesson successfully cancelled.");
				}
			} else if (option.equalsIgnoreCase("C")) {
				if (updateLesson(studentId)) {
					System.out.println("\nLesson successfully updated.");
				}
			} else if (option.equalsIgnoreCase("D")) {
				if (attendClass(studentId)) {
					System.out.println("\nLesson successfully attended.");
				}
			} else if (option.equalsIgnoreCase("E")) {
				myBookings(fetchLessonBookingsByStudent(studentId, 0));
			}else if (option.equalsIgnoreCase("F")) {
				return;
			} else {
				System.out.println("\n Invalid ! please select a valid option");
			}

		} while (!option.equalsIgnoreCase("f"));
	}

	// lesson booking
	public boolean lesssonBooking(String studentId) {
		String option;
		String lessonId;
		int lessonSeat = 0;
		// show timetable to select lesson for booking
		filterLessonTimetable = lessonTimetable.lessonTimetables;
		lessonTimetable.displayLessonInfo(filterLessonTimetable);
		// filter lesson
		lessonTimetable.filterLessonTimetable("Book");
		System.out.print("Select one lesson option to book lesson : ");
		option = userEnter.nextLine().trim();
		if (option.isEmpty() || !option.matches("\\d+")) {
			System.out.println("Invalid ! selected invalid lesson option");
		} else {
			if (Integer.parseInt(option) > filterLessonTimetable.size()) {
				System.out.println("Invalid ! selected invalid lesson option");
			} else {
				lessonId = filterLessonTimetable.get(Integer.parseInt(option) - 1).getLesson_id();
				if (!lessonExist(lessonId)) {
					System.out.println("Invalid ! selected lesson id does not exist");
				} else {
					// check not duplicate booking
					if (checkDuplicateBooking(lessonId, studentId)) {
						System.out.println("Invalid ! this lesson id " + lessonId + " already booked by you");
					} else {
						// get current lesson seat
						lessonSeat = lessonTimetable.getLessonInfoById(lessonId).getLesson_seat();
						if (!(lessonSeat > 0 && lessonSeat < 5)) {
							System.out.println("Invalid ! this lesson id " + lessonId + " is fully booked.");
						} else {
							// get lesson level
							int lessonGrade = lessonTimetable.getLessonInfoById(lessonId).getLesson_grade();
							// get student level
							int studentGrade = student.getStudentInfoById(studentId).getStudentGrade();
							if (!(studentGrade == lessonGrade || (studentGrade + 1) == lessonGrade)) {
								System.out.println("You can book only grade " + studentGrade + " or "
										+ (studentGrade + 1) + " lessons");
							} else {
								academy.saveStudentBoookingInfo(studentId, lessonId);
								return true;
							}
						}
					}
				}
			}
		}
		return false;

	}

	// change change booking
	public boolean updateLesson(String studentId) {
		String option;
		int lessonSeat;
		String latestLessonId;
		String bookingId = null;
		// show student booking details to select for change
		myBookings(fetchLessonBookingsByStudent(studentId, 0));
		if (!fetchLessonBookingsByStudent(studentId, 0).isEmpty()) {
			System.out.print("Enter one option from booking to change lesson : ");
			option = userEnter.nextLine().trim();
			if (option.isEmpty() || !option.matches("\\d+")) {
				System.out.println("Invalid ! Please enter option to change lesson");
			} else {
				// set booking id from option
				if (option.matches("[0-9]")) {
					bookingId = "BI0" + option;
				} else {
					bookingId = "BI" + option;
				}
				// Validation of student select option
				if (!validBookingId(bookingId, studentId)) {
					System.out.println("Invalid ! This booking number does not exist");
				} else {
					// check selected booking number not attended or cancel
					String booking_status = getBookingById(bookingId).getStatus();
					if (booking_status.equalsIgnoreCase("Attended")) {
						System.out.println("Invalid ! You have already attended this booking id ");
					} else if (booking_status.equalsIgnoreCase("Cancelled")) {
						System.out.println("Invalid ! You have already cancelled this booking id");
					} else {
						// show selected booking details
						System.out.println("\nSelect Booking details to change");
						List<BookingCoordinator> selectBooking = new ArrayList<>();
						selectBooking.add(getBookingById(bookingId));
						myBookings(selectBooking);
						System.out.println();
						// show timetable to select lesson for booking
						filterLessonTimetable = lessonTimetable.lessonTimetables;
						lessonTimetable.displayLessonInfo(filterLessonTimetable);
						// filter lesson
						lessonTimetable.filterLessonTimetable("Change");
						System.out.print("Select one lesson option to change lesson : ");
						option = userEnter.nextLine().trim();
						if (option.isEmpty() || !option.matches("\\d+")) {
							System.out.println("Invalid ! selected invalid lesson option");
						} else {
							if (Integer.parseInt(option) > filterLessonTimetable.size()) {
								System.out.println("Invalid ! selected invalid lesson option");
							} else {
								latestLessonId = filterLessonTimetable.get(Integer.parseInt(option) - 1).getLesson_id();
								if (!lessonExist(latestLessonId)) {
									System.out.println("Invalid ! selected lesson id does not exist");
								} else {
									// check not duplicate booking
									if (checkDuplicateBooking(latestLessonId, studentId)) {
										System.out.println("Invalid ! this lesson id already booked by you");
									} else {
										// get current lesson seat
										lessonSeat = lessonTimetable.getLessonInfoById(latestLessonId).getLesson_seat();
										if (!(lessonSeat > 0 && lessonSeat < 5)) {
											System.out.println("Invalid ! this lesson id is fully booked.");
										} else {
											// get lesson level
											int lessonGrade = lessonTimetable.getLessonInfoById(latestLessonId)
													.getLesson_grade();
											// get student level
											int studentGrade = student.getStudentInfoById(studentId).getStudentGrade();
											if (!(studentGrade == lessonGrade || (studentGrade + 1) == lessonGrade)) {
												System.out.println("You can change only grade " + studentGrade + " or "
														+ (studentGrade + 1) + " lessons");
											} else {
												academy.updateBookingDetails(bookingId, latestLessonId);
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}else {
			System.out.println("Invalid ! no booking available to update");
		}
		return false;
	}

	// cancel lesson
	public boolean cancelLesson(String studentId) {
		String option;
		String bookingId;
		String action;
		boolean correct_action = false;
		// show student booking details to select for cancel
		myBookings(fetchLessonBookingsByStudent(studentId, 0));
		if (!fetchLessonBookingsByStudent(studentId, 0).isEmpty()) {
			System.out.print("Enter one option from booking to cancel lesson : ");
			option = userEnter.nextLine().trim();
			if (option.isEmpty() || !option.matches("\\d+")) {
				System.out.println("Invalid ! Please enter valid booking option to cancel lesson");
			} else {
				// set booking id from option
				if (option.matches("[0-9]")) {
					bookingId = "BI0" + option;
				} else {
					bookingId = "BI" + option;
				}
				// Validation of student select option
				if (!validBookingId(bookingId, studentId)) {
					System.out.println("Invalid ! This booking number does not exist");
				} else {
					// check selected booking number not attended or cancel
					String booking_status = getBookingById(bookingId).getStatus();
					if (booking_status.equalsIgnoreCase("Attended")) {
						System.out.println("Invalid ! You have already attended this booking id ");
					} else if (booking_status.equalsIgnoreCase("Cancelled")) {
						System.out.println("Invalid ! You have already cancelled this booking id");
					} else {
						// show selected booking details
						System.out.println("\nSelect Booking details to change");
						List<BookingCoordinator> selectBooking = new ArrayList<>();
						selectBooking.add(getBookingById(bookingId));
						myBookings(selectBooking);
						System.out.println();
						// get reason for cancel class by student
						do {
							System.out.print("Do you want to cancel this booking (Yes/no) : ");
							action = userEnter.nextLine().trim();
							if (action.isEmpty() || action.matches("\\d+")) {
								System.out.println("Invalid ! entered an invalid option ");
							} else {
								if (action.equalsIgnoreCase("Y") || action.equalsIgnoreCase("Yes")) {
									// cancel this lesson
									academy.studentBookingCancel(bookingId);
									correct_action = true;
									return correct_action;
								} else {
									correct_action = true;
								}
							}
						} while (!correct_action);
					}
				}
			}

		}else {
			System.out.println("Invalid ! no booking available to cancel");
		}

		return false;
	}

	// attend lesson
	public boolean attendClass(String studentId) {
        String bookingId;
        String option;
     // show student booking details to select for attend
     		myBookings(fetchLessonBookingsByStudent(studentId, 0));
     		if(fetchLessonBookingsByStudent(studentId, 0).isEmpty()) {
     			System.out.println("Invalid ! no booking available to attend");
     		}else {
     			System.out.print("Enter one option from booking to attend lesson : ");
    			option = userEnter.nextLine().trim();
    			if (option.isEmpty() || !option.matches("\\d+")) {
    				System.out.println("Invalid ! Please enter valid booking option to attend lesson");
    			} else {
    				// set booking id from option
    				if (option.matches("[0-9]")) {
    					bookingId = "BI0" + option;
    				} else {
    					bookingId = "BI" + option;
    				}
    				// Validation of student select option
    				if (!validBookingId(bookingId, studentId)) {
    					System.out.println("Invalid ! This booking number does not exist");
    				} else {
    					// check selected booking number not attended or cancel
    					String booking_status = getBookingById(bookingId).getStatus();
    					if (booking_status.equalsIgnoreCase("Attended")) {
    						System.out.println("Invalid ! You have already attended this booking id ");
    					} else if (booking_status.equalsIgnoreCase("Cancelled")) {
    						System.out.println("Invalid ! You have already cancelled this booking id");
    					} else {
    						// show selected booking details
    						System.out.println("\nSelect Booking details to change");
    						List<BookingCoordinator> selectBooking = new ArrayList<>();
    						selectBooking.add(getBookingById(bookingId));
    						myBookings(selectBooking);
    						System.out.println();
                            //attend this lesson
                            //get student feedback after attended class
                            reviewCoordinator.lessonReviews(studentId, bookingId);
                           academy.updateAttendClass(studentId, bookingId);
                            
                                return true;
                            
                        }
                    }
                }
     		}
     		
			return false;
        }
		
	

	// show my bookings
	public void myBookings(List<BookingCoordinator> studentBookings) {
		int option = 0;
		if (!studentBookings.isEmpty()) {
			System.out.println(
					"\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"
							+ ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
			System.out.printf(
					"%-10s %-12s %-15s %-12s  %-17s  %-12s  %-15s  %-15s  %-20s  %-15s  %-15s  %-15s  %-15s \n",
					"Option", "Booking Id", "Booking Date", "Lesson Id", "Lesson Name", "Lesson Level", "Lesson Day",
					"Lesson Time", "Lesson Date", "Status", "Student Name", "Student Grade", "Trainer");

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
						Integer.parseInt(bookingInfo.getBookingId().substring(2)), bookingInfo.getBookingId(), bookingInfo.getBookingDate(), bookingInfo.getLessonId(),
						timetable.getLesson_title(), "Grade " + timetable.getLesson_grade(), timetable.getLesson_day(),
						timetable.getLesson_time(), timetable.getLesson_date(), bookingInfo.getStatus(),
						student.getStudentInfoById(bookingInfo.getStudentId()).getStudentName(),
						"Grade " + student.getStudentInfoById(bookingInfo.getStudentId()).getStudentGrade(),
						new Trainer().getTrainerInfo(bookingInfo.trainerId).getTrainerName());
			}
			System.out.println(
					",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"
							+ ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		}else {
			System.out.println("Invalid ! no booking data available.");
		}
	}

	// get booking by bookingId
	public BookingCoordinator getBookingById(String bookingId) {
		for (BookingCoordinator bookingInfo : studentBookings) {
			if (bookingInfo.getBookingId().equalsIgnoreCase(bookingId)) {
				return bookingInfo;
			}
		}
		return null;
	}

	// get student booking
	public List<BookingCoordinator> fetchLessonBookingsByStudent(String studentId, int month) {
		// create list to store student booking by month
		List<BookingCoordinator> studentBooking = new ArrayList<>();
		for (BookingCoordinator bookingInfo : studentBookings) {
			int attendMonth = Integer.parseInt(lessonTimetable.getLessonInfoById(bookingInfo.getLessonId()).getLesson_date()
					.substring(3, 5));
			if (bookingInfo.getStudentId().equalsIgnoreCase(studentId) && attendMonth==month
					|| bookingInfo.getStudentId().equalsIgnoreCase(studentId) && month==0) {
				studentBooking.add(bookingInfo);
			}
		}
		return studentBooking;
	}

	// validation of lesson exist
	public boolean lessonExist(String lessonId) {
		for (LessonTimetable timetable : lessonTimetable.lessonTimetables) {
			if (timetable.getLesson_id().equalsIgnoreCase(lessonId)) {
				return true;
			}
		}
		return false;
	}

	// validation of duplicate booking
	public boolean checkDuplicateBooking(String lessonId, String studentId) {
		for (BookingCoordinator bookingInfo : studentBookings) {
			if (bookingInfo.getStudentId().equalsIgnoreCase(lessonId)
					&& bookingInfo.getStudentId().equalsIgnoreCase(studentId)) {
				return true;
			}
		}
		return false;
	}

	// validation of correct booking Id
	public boolean validBookingId(String bookingId, String studentId) {
		for (BookingCoordinator bookingInfo : studentBookings) {
			if (bookingInfo.getBookingId().equalsIgnoreCase(bookingId)
					&& bookingInfo.getStudentId().equalsIgnoreCase(studentId)) {
				return true;
			}
		}
		return false;
	}

}
