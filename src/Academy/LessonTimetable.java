package Academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LessonTimetable {
	private BookingCoordinator booking;
	public Scanner userEnter;
	private String lesson_id;
	private String lesson_title;
	private String lesson_date;
	private String lesson_day;
	private String lesson_time;
	private int lesson_seat;
	private String trainer_id;
	private int lesson_grade;
	public List<LessonTimetable> lessonTimetables;

	public String getLesson_id() {
		return lesson_id;
	}

	public String getLesson_title() {
		return lesson_title;
	}

	public String getLesson_date() {
		return lesson_date;
	}

	public String getLesson_day() {
		return lesson_day;
	}

	public String getLesson_time() {
		return lesson_time;
	}

	public int getLesson_seat() {
		return lesson_seat;
	}

	public String getTrainer_id() {
		return trainer_id;
	}

	public int getLesson_grade() {
		return lesson_grade;
	}

	public List<LessonTimetable> getLessonTimetables() {
		return lessonTimetables;
	}

	public void setLesson_id(String lesson_id) {
		this.lesson_id = lesson_id;
	}

	public void setLesson_title(String lesson_title) {
		this.lesson_title = lesson_title;
	}

	public void setLesson_date(String lesson_date) {
		this.lesson_date = lesson_date;
	}

	public void setLesson_day(String lesson_day) {
		this.lesson_day = lesson_day;
	}

	public void setLesson_time(String lesson_time) {
		this.lesson_time = lesson_time;
	}

	public void setLesson_seat(int lesson_seat) {
		this.lesson_seat = lesson_seat;
	}

	public void setTrainer_id(String trainer_id) {
		this.trainer_id = trainer_id;
	}

	public void setLesson_grade(int lesson_grade) {
		this.lesson_grade = lesson_grade;
	}

	public void setLessonTimetables(List<LessonTimetable> lessonTimetables) {
		this.lessonTimetables = lessonTimetables;
	}

	public void setAllObject(Academy academy) {
		this.booking = academy.getBookingCoordinator();
	}

	// create lessonTimetable constructor to add lessons details
	public LessonTimetable(String lesson_id, String lesson_title, String lesson_date, String lesson_day,
			String lesson_time, int lesson_seat, String trainer_id, int lesson_grade) {
		super();
		this.lesson_id = lesson_id;
		this.lesson_title = lesson_title;
		this.lesson_date = lesson_date;
		this.lesson_day = lesson_day;
		this.lesson_time = lesson_time;
		this.lesson_seat = lesson_seat;
		this.trainer_id = trainer_id;
		this.lesson_grade = lesson_grade;
	}

	// create default constructor
	public LessonTimetable() {
		this.userEnter = new Scanner(System.in);
		this.lessonTimetables = new ArrayList<>();
		addLessonTimetable();
	}

	// add default lessonTimetables
	public void addLessonTimetable() {
		LessonTimetable lesson1 = new LessonTimetable("LT01", "Toddler Splash", "08-04-2024", "Monday",
				"4:00pm - 5:00pm", 4, "TRN01", 1);
		saveLessonInfo(lesson1);
		LessonTimetable lesson2 = new LessonTimetable("LT02", "Toddler Splash", "08-04-2024", "Monday",
				"5:00pm - 6:00pm", 4, "TRN02", 1);
		saveLessonInfo(lesson2);
		LessonTimetable lesson3 = new LessonTimetable("LT03", "Toddler Splash", "08-04-2024", "Monday",
				"6:00pm - 7:00pm", 4, "TRN03", 4);
		saveLessonInfo(lesson3);
		LessonTimetable lesson4 = new LessonTimetable("LT04", "Dive Skills", "10-04-2024", "Wednesday",
				"4:00pm - 5:00pm", 4, "TRN04", 3);
		saveLessonInfo(lesson4);
		LessonTimetable lesson5 = new LessonTimetable("LT05", "Dive Skills", "10-04-2024", "Wednesday",
				"5:00pm - 6:00pm", 4, "TRN05", 5);
		saveLessonInfo(lesson5);
		LessonTimetable lesson6 = new LessonTimetable("LT06", "Dive Skills", "10-04-2024", "Wednesday",
				"6:00pm - 7:00pm", 4, "TRN05", 2);
		saveLessonInfo(lesson6);
		LessonTimetable lesson7 = new LessonTimetable("LT07", "Swim Strong", "12-04-2024", "Friday", "4:00pm - 5:00pm",
				4, "TRN04", 4);
		saveLessonInfo(lesson7);
		LessonTimetable lesson8 = new LessonTimetable("LT08", "Swim Strong", "12-04-2024", "Friday", "5:00pm - 6:00pm",
				4, "TRN03", 2);
		saveLessonInfo(lesson8);
		LessonTimetable lesson9 = new LessonTimetable("LT09", "Swim Strong", "12-04-2024", "Friday", "6:00pm - 7:00pm",
				4, "TRN02", 5);
		saveLessonInfo(lesson9);
		LessonTimetable lesson10 = new LessonTimetable("LT10", "Water Polo", "13-04-2024", "Saturday",
				"2:00pm - 3:00pm", 4, "TRN01", 1);
		saveLessonInfo(lesson10);
		LessonTimetable lesson11 = new LessonTimetable("LT11", "Water Polo", "13-04-2024", "Saturday",
				"3:00pm - 4:00pm", 4, "TRN01", 4);
		saveLessonInfo(lesson11);
		LessonTimetable lesson12 = new LessonTimetable("LT12", "Toddler Splash", "15-04-2024", "Monday",
				"4:00pm - 5:00pm", 4, "TRN02", 3);
		saveLessonInfo(lesson12);
		LessonTimetable lesson13 = new LessonTimetable("LT13", "Toddler Splash", "15-04-2024", "Monday",
				"5:00pm - 6:00pm", 4, "TRN03", 1);
		saveLessonInfo(lesson13);
		LessonTimetable lesson14 = new LessonTimetable("LT14", "Toddler Splash", "15-04-2024", "Monday",
				"6:00pm - 7:00pm", 4, "TRN04", 4);
		saveLessonInfo(lesson14);
		LessonTimetable lesson15 = new LessonTimetable("LT15", "Dive Skills", "17-04-2024", "Wednesday",
				"4:00pm - 5:00pm", 4, "TRN05", 3);
		saveLessonInfo(lesson15);
		LessonTimetable lesson16 = new LessonTimetable("LT16", "Dive Skills", "17-04-2024", "Wednesday",
				"5:00pm - 6:00pm", 4, "TRN05", 2);
		saveLessonInfo(lesson16);
		LessonTimetable lesson17 = new LessonTimetable("LT17", "Dive Skills", "17-04-2024", "Wednesday",
				"6:00pm - 7:00pm", 4, "TRN03", 2);
		saveLessonInfo(lesson17);
		LessonTimetable lesson18 = new LessonTimetable("LT18", "Swim Strong", "19-04-2024", "Friday", "4:00pm - 5:00pm",
				4, "TRN02", 2);
		saveLessonInfo(lesson18);
		LessonTimetable lesson19 = new LessonTimetable("LT19", "Swim Strong", "19-04-2024", "Friday", "5:00pm - 6:00pm",
				4, "TRN01", 1);
		saveLessonInfo(lesson19);
		LessonTimetable lesson20 = new LessonTimetable("LT20", "Swim Strong", "19-04-2024", "Friday", "6:00pm - 7:00pm",
				4, "TRN04", 4);
		saveLessonInfo(lesson20);
		LessonTimetable lesson21 = new LessonTimetable("LT21", "Water Polo", "20-04-2024", "Saturday",
				"2:00pm - 3:00pm", 4, "TRN04", 1);
		saveLessonInfo(lesson21);
		LessonTimetable lesson22 = new LessonTimetable("LT22", "Water Polo", "20-04-2024", "Saturday",
				"3:00pm - 4:00pm", 4, "TRN02", 4);
		saveLessonInfo(lesson22);
		LessonTimetable lesson23 = new LessonTimetable("LT23", "Toddler Splash", "22-04-2024", "Monday",
				"4:00pm - 5:00pm", 4, "TRN05", 2);
		saveLessonInfo(lesson23);
		LessonTimetable lesson24 = new LessonTimetable("LT24", "Toddler Splash", "22-04-2024", "Monday",
				"5:00pm - 6:00pm", 4, "TRN01", 3);
		saveLessonInfo(lesson24);
		LessonTimetable lesson25 = new LessonTimetable("LT25", "Toddler Splash", "22-04-2024", "Monday",
				"6:00pm - 7:00pm", 4, "TRN05", 3);
		saveLessonInfo(lesson25);
		LessonTimetable lesson26 = new LessonTimetable("LT26", "Dive Skills", "24-04-2024", "Wednesday",
				"4:00pm - 5:00pm", 4, "TRN02", 5);
		saveLessonInfo(lesson26);
		LessonTimetable lesson27 = new LessonTimetable("LT27", "Dive Skills", "24-04-2024", "Wednesday",
				"5:00pm - 6:00pm", 4, "TRN03", 4);
		saveLessonInfo(lesson27);
		LessonTimetable lesson28 = new LessonTimetable("LT28", "Dive Skills", "24-04-2024", "Wednesday",
				"6:00pm - 7:00pm", 4, "TRN03", 3);
		saveLessonInfo(lesson28);
		LessonTimetable lesson29 = new LessonTimetable("LT29", "Swim Strong", "26-04-2024", "Friday", "4:00pm - 5:00pm",
				4, "TRN04", 3);
		saveLessonInfo(lesson29);
		LessonTimetable lesson30 = new LessonTimetable("LT30", "Swim Strong", "26-04-2024", "Friday", "5:00pm - 6:00pm",
				4, "TRN02", 2);
		saveLessonInfo(lesson30);
		LessonTimetable lesson31 = new LessonTimetable("LT31", "Swim Strong", "26-04-2024", "Friday", "6:00pm - 7:00pm",
				4, "TRN03", 5);
		saveLessonInfo(lesson31);
		LessonTimetable lesson32 = new LessonTimetable("LT32", "Water Polo", "27-04-2024", "Saturday",
				"2:00pm - 3:00pm", 4, "TRN01", 1);
		saveLessonInfo(lesson32);
		LessonTimetable lesson33 = new LessonTimetable("LT33", "Water Polo", "27-04-2024", "Saturday",
				"3:00pm - 4:00pm", 4, "TRN05", 5);
		saveLessonInfo(lesson33);
		LessonTimetable lesson34 = new LessonTimetable("LT34", "Toddler Splash", "29-04-2024", "Monday",
				"4:00pm - 5:00pm", 4, "TRN04", 2);
		saveLessonInfo(lesson34);
		LessonTimetable lesson35 = new LessonTimetable("LT35", "Toddler Splash", "29-04-2024", "Monday",
				"5:00pm - 6:00pm", 4, "TRN03", 2);
		saveLessonInfo(lesson35);
		LessonTimetable lesson36 = new LessonTimetable("LT36", "Toddler Splash", "29-04-2024", "Monday",
				"6:00pm - 7:00pm", 4, "TRN02", 5);
		saveLessonInfo(lesson36);
		LessonTimetable lesson37 = new LessonTimetable("LT37", "Dive Skills", "01-05-2024", "Wednesday",
				"4:00pm - 5:00pm", 4, "TRN01", 1);
		saveLessonInfo(lesson37);
		LessonTimetable lesson38 = new LessonTimetable("LT38", "Dive Skills", "01-05-2024", "Wednesday",
				"5:00pm - 6:00pm", 4, "TRN05", 3);
		saveLessonInfo(lesson38);
		LessonTimetable lesson39 = new LessonTimetable("LT39", "Dive Skills", "01-05-2024", "Wednesday",
				"6:00pm - 7:00pm", 4, "TRN03", 5);
		saveLessonInfo(lesson39);
		LessonTimetable lesson40 = new LessonTimetable("LT40", "Swim Strong", "03-05-2024", "Friday", "4:00pm - 5:00pm",
				4, "TRN02", 4);
		saveLessonInfo(lesson40);
		LessonTimetable lesson41 = new LessonTimetable("LT41", "Swim Strong", "03-05-2024", "Friday", "5:00pm - 6:00pm",
				4, "TRN01", 2);
		saveLessonInfo(lesson41);
		LessonTimetable lesson42 = new LessonTimetable("LT42", "Swim Strong", "03-05-2024", "Friday", "6:00pm - 7:00pm",
				4, "TRN05", 5);
		saveLessonInfo(lesson42);
		LessonTimetable lesson43 = new LessonTimetable("LT43", "Water Polo", "04-05-2024", "Saturday",
				"2:00pm - 3:00pm", 4, "TRN03", 2);
		saveLessonInfo(lesson43);
		LessonTimetable lesson44 = new LessonTimetable("LT44", "Water Polo", "04-05-2024", "Saturday",
				"3:00pm - 4:00pm", 4, "TRN02", 1);
		saveLessonInfo(lesson44);
	}

	// save lesson details
	public void saveLessonInfo(LessonTimetable lesson) {
		if (lesson != null) {
			lessonTimetables.add(lesson);
		}
	}

	// filter lesson
	public void filterLessonTimetable(String action) {
		String selectOption;
		do {
			// display menu to select
			System.out.println("\nA. By grade");
			System.out.println("B. By day");
			System.out.println("C. By trainer");
			System.out.println("D. All timetable");
			if (!action.isEmpty()) {
				System.out.println("E. Select lesson\n");
			} else {
				System.out.println("E. Previous menu\n");
			}
			System.out.print("Select one selectOption to show timetable : ");
			selectOption = userEnter.nextLine().trim();
			if (selectOption.equalsIgnoreCase("A")) {
				// store filter timetable by day
				List<LessonTimetable> filter = filterTimetableByGrade();
				// validation of filter timetable list is not empty
				if (!filter.isEmpty()) {
					// store filter timetable in filterTimetable list
					booking.filterLessonTimetable = filter;
					// display timetable
					displayLessonInfo(booking.filterLessonTimetable);
				}
			} else if (selectOption.equalsIgnoreCase("B")) {
				List<LessonTimetable> filter = filterTimetableByDay();
				if (!filter.isEmpty()) {
					booking.filterLessonTimetable = filter;
					displayLessonInfo(booking.filterLessonTimetable);
				}
			} else if (selectOption.equalsIgnoreCase("C")) {
				List<LessonTimetable> filter = filterTimetableByTrainer();
				if (!filter.isEmpty()) {
					booking.filterLessonTimetable = filter;
					displayLessonInfo(booking.filterLessonTimetable);
				}
			} else if (selectOption.equalsIgnoreCase("D")) {
				booking.filterLessonTimetable = lessonTimetables;
				displayLessonInfo(booking.filterLessonTimetable);
			} else if (selectOption.equalsIgnoreCase("E")) {
				return;
			} else {
				System.out.println("\n Invalid ! please select a valid option");
			}

		} while (!selectOption.equalsIgnoreCase("E"));
	}

	// filter timetable by day
	public List<LessonTimetable> filterTimetableByDay() {
		String day;
		System.out.print("Enter day(Monday,Wednesday,Friday,Saturday) to show timetable : ");
		day = userEnter.nextLine().trim();
		// create list to store filter lesson
		List<LessonTimetable> dayLessons = new ArrayList<>();
		if (!day.isEmpty()) {
			String select_day = selectDay(day);
			if (select_day != null) {
				for (LessonTimetable timetable : lessonTimetables) {
					if (timetable.getLesson_day().equalsIgnoreCase(select_day)) {
						// add filter timetable in day lesson list
						dayLessons.add(timetable);
					}
				}
			}
		} else {
			System.out.println("Invalid ! entered invalid day name");
		}
		return dayLessons;

	}

	// filter timetable by day
	public List<LessonTimetable> filterTimetableByGrade() {
		// create list to store filter lesson
		List<LessonTimetable> gradeLessons = new ArrayList<>();
		int selectGrade;
		System.out.print("Enter grade (1-5) to show timetable : ");
		String grade = userEnter.nextLine().trim();
		if (grade.isEmpty() || !grade.matches("[1-5]")) {
			System.out.println("Invalid ! entered invalid grade");
		} else {
			selectGrade = Integer.parseInt(grade);
			for (LessonTimetable timetable : lessonTimetables) {
				if (timetable.getLesson_grade() == selectGrade) {
					// add filter timetable in day lesson list
					gradeLessons.add(timetable);
				}
			}
		}
		return gradeLessons;
	}

	// filter timetable by day
	public List<LessonTimetable> filterTimetableByTrainer() {
		String trainerId;
		// create list to store filter lesson
		List<LessonTimetable> trainerLessons = new ArrayList<>();
		// show trainer list to enter their id for show timetable
		new Trainer().showTrainers();
		System.out.print("Enter trainer id to show timetable : ");
		trainerId = userEnter.nextLine().trim();
		if (trainerId.isEmpty() || !trainerExist(trainerId)) {
			System.out.println("Invalid ! entered trainer id does not exist");
		} else {
			for (LessonTimetable timetable : lessonTimetables) {
				if (timetable.getTrainer_id().equalsIgnoreCase(trainerId)) {
					// add filter timetable in trainer lesson list
					trainerLessons.add(timetable);
				}
			}
		}
		return trainerLessons;
	}

	// display lesson info
	public void displayLessonInfo(List<LessonTimetable> filterTimetable) {
		int option = 0;
		if (!lessonTimetables.isEmpty()) {
			System.out.println(
					",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
			System.out.printf(" %-8s  %-10s  %-20s  %-15s  %-13s  %-17s  %-10s  %-12s  %-8s  %-10s \n", "Option",
					"Lesson Id", "Lesson name", "Lesson day", "Lesson date", "Lesson time", "Tutor name",
					"Tutor gender", "Grade", "Seat");
			System.out.println(
					",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

			for (LessonTimetable timetable : filterTimetable) {
				option++;
				String lessonId = timetable.getLesson_id();
				String lessonDay = timetable.getLesson_day();
				String lessonName = timetable.getLesson_title();
				String lessonTime = timetable.getLesson_time();
				String trainerId = timetable.getTrainer_id();
				Trainer trainer = new Trainer().getTrainerInfo(trainerId);
				String trainerName = trainer.getTrainerName();
				String trainerGender = trainer.getTrainerGender();
				int lessonGrade = timetable.getLesson_grade();
				String lessonDate = timetable.getLesson_date();
				int lessonSeat = timetable.getLesson_seat();

				System.out.printf(" %-8s  %-10s  %-20s  %-15s  %-13s  %-17s  %-10s  %-12s  %-8s  %-10s \n", option,
						lessonId, lessonName, lessonDay, lessonDate, lessonTime, trainerName, trainerGender,
						"Grade " + lessonGrade, lessonSeat);
			}

			System.out.println(
					",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		}
	}

	// get lesson info by id
	public LessonTimetable getLessonInfoById(String lesson_id) {
		for (LessonTimetable lessonInfo : lessonTimetables) {
			if (lessonInfo.getLesson_id().equalsIgnoreCase(lesson_id)) {
				return lessonInfo;
			}
		}
		return null;
	}

	// validation of valid day
	public String selectDay(String day) {
		String dayName[] = { "Monday", "Wednesday", "Friday", "Saturday" };
		for (String day1 : dayName) {
			if (day1.equalsIgnoreCase(day) || day1.substring(0, 3).equalsIgnoreCase(day)) {
				return day1;
			}
		}
		return null;
	}

	// validation of trainer id
	public boolean trainerExist(String trainerId) {
		for (Trainer trainer : new Trainer().swimTrainers) {
			if (trainer.getTrainerId().equalsIgnoreCase(trainerId)) {
				return true;
			}
		}
		return false;
	}

}
