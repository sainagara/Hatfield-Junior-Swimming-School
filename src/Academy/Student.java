package Academy;
   

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private BookingCoordinator bookingCoordinator;
    private ReviewCoordinator reviewCoordinator;
    private Scanner userEnter;
    private String studentId;
    private String studentName;
    private String studentEmail;
    private String studentContact;
    private int studentGrade;
    private String studentGender;
    private String studentPassword;
    private int studentAge;
    public List<Student> academyStudents;

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public int getStudentAge() {
        return studentAge;
    }
     public void setAllObject(Academy academy) {
        this.bookingCoordinator=academy.getBookingCoordinator();
        this.reviewCoordinator=academy.getReviewCoordinator();
    }

    public List<Student> getAcademyStudents() {
        return academyStudents;
    }

   
    // create student constructor to add student info
    public Student(String studentId, String studentName, String studentEmail, String studentContact, int studentGrade,
            String studentGender, String studentPassword, int studentAge) {
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentContact = studentContact;
        this.studentGrade = studentGrade;
        this.studentGender = studentGender;
        this.studentPassword = studentPassword;
        this.studentAge = studentAge;
    }
    
     // Constructor
    public Student() {
    	this.userEnter=new Scanner(System.in);
        this.academyStudents = new ArrayList<>();
        addDefatultAcademystudents();
    }
    
    //manage student 
	public void manageStudent(String studentId) {
		String option;
		//get student name and grade to show
		String name = getStudentInfoById(studentId).getStudentName();
		int grade = getStudentInfoById(studentId).getStudentGrade();
		System.out.println("\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		System.out.println("Student name  : " + name + "\nstudent Grade : " + grade);
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
		do {
			System.out.println("\nA. My reviews");
			System.out.println("B. Booking managers");
			System.out.println("C. Logout");
			System.out.print("Select one option : ");
			option = userEnter.nextLine().trim();
			if (option.equalsIgnoreCase("A")) {
				reviewCoordinator.reviewInformation(studentId, "",0,"Show");
			} else if (option.equalsIgnoreCase("B")) {
				bookingCoordinator.manageBooking(studentId);
			} else if (option.equalsIgnoreCase("C")) {
				System.out.println("Logged out successfully");
				return;
			} else {
				System.out.println("\n Invalid ! please select a valid option");
			}
		} while (!option.equalsIgnoreCase("c"));

	}

    //student register
    public void studentRegister() {
        String name = null;
        String email = null;
        String contact = null;
        String age = null;
        String grade = null;
        String password = null;
        String gender = null;
        boolean register = false;
        System.out.println("\n,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println("\tstudent register form");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        do {
            // get name from user
            if (name == null) {
                System.out.print("Enter your name : ");
                name = userEnter.nextLine().trim();
                if (name.isEmpty() || name.matches("\\d+")) {
                    System.out.println("Invalid : Please enter a valid name.");
                    name = null;
                } else {
                    if (studentNameExist(name)) {
                        System.out.println("Invalid :  This name has been already registered.");
                        name = null;
                    }
                }
                // get email from user
            } else if (email == null) {
                System.out.print("Enter your email id : ");
                email = userEnter.nextLine().trim();
                if (email.isEmpty() || !email.matches("^(.+)@(\\S+)$")) {
                    System.out.println("Invalid : please enter valid email id");
                    email = null;
                } else {
                    if (studentEmailExist(email)) {
                        System.out.println("Invalid :  This email has been already registered.");
                        email = null;
                    }
                }
                // get contact from user
            } else if (contact == null) {
                System.out.print("Enter your emergency contact : ");
                contact = userEnter.nextLine().trim();
                if (contact.isEmpty() || !contact.matches("\\d+")) {
                    System.out.println("Invalid : please enter valid emergency contact");
                    contact = null;
                }
                // get age from user
            } else if (age == null) {
                System.out.print("Entered your age (4 to 11) : ");
                age = userEnter.nextLine().trim();
                if (age.isEmpty() || !age.matches("\\d+")) {
                    System.out.println("Invalid : please enter valid  age");
                    age = null;
                } else {
                    studentAge = Integer.parseInt(age);
                    // validation of age
                    if (!(studentAge >= 4 && studentAge <= 11)) {
                        System.out.println("Invalid : age should be 4 to 11 years");
                        age = null;
                    }
                }
                // get grade from user
            } else if (grade == null) {
                System.out.print("Enter your grade (1 to 5) : ");
                grade = userEnter.nextLine().trim();
                if (grade.isEmpty() || !grade.matches("[1-5]")) {
                    System.out.println("Invalid : please enter valid  grade");
                    grade = null;
                } else {
                    studentGrade = Integer.parseInt(grade);
                }
                // get password from user
            } else if (password == null) {
                System.out.print("Enter your password : ");
                password = userEnter.nextLine().trim();
                if (password.isEmpty()) {
                    System.out.println("Invalid : password field cannot be empty");
                    password = null;
                }
                // get gender from user
            } else if (gender == null) {
                System.out.print("Enter your gender : ");
                gender = userEnter.nextLine().trim();
                if (gender.isEmpty()) {
                    System.out.println("Invalid : gender field cannot be empty");
                    gender = null;
                } else {
                    if (!(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")
                            || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female"))) {
                        System.out.println("Invalid : please enter valid gender");
                        gender = null;
                    } else {
                        if (gender.equalsIgnoreCase("f")) {
                            gender = "Female";
                        } else if (gender.equalsIgnoreCase("m")) {
                            gender = "Male";
                        }
                    }
                }
            } else {
                // generate student id
                studentId = "ST" + (academyStudents.size() + 1);
                // create student object
                Student studentInfo = new Student(studentId, name, email, contact, studentGrade, gender, password,
                        studentAge);
                // save student info
                savestudentInfo(studentInfo);
                System.out.println("\nNew student added successfully");
                register = true;
            }

        } while (!register);
    }

    //get student info by id
    public Student getStudentInfoById(String studentId) {
        for (Student studentInfo : academyStudents) {
            if (studentInfo.getStudentId().equalsIgnoreCase(studentId)) {
                return studentInfo;
            }
        }

        return null;
    }

    // add default student info
    public void addDefatultAcademystudents() {
        // Create Student objects 
        Student student1 = new Student("ST01", "John Doe", "john@gmail.com", "041234567890", 3, "Male", "JD12345", 10);
        Student student2 = new Student("ST02", "Alice Smith", "alice@gmail.com", "049876543210", 2, "Female", "AS12345", 9);
        Student student3 = new Student("ST03", "Bob Johnson", "bob@gmail.com", "041112223333", 4, "Male", "BJ12345", 11);
        Student student4 = new Student("ST04", "Emma Davis", "emma@gmail.com", "044445556666", 1, "Female", "ED12345", 8);
        Student student5 = new Student("ST05", "James Wilson", "james@gmail.com", "047778889999", 5, "Male", "JW12345", 11);
        Student student6 = new Student("ST06", "Sophia Brown", "sophia@gmail.com", "042223334444", 1, "Female", "SB12345", 7);
        Student student7 = new Student("ST07", "Michael Taylor", "michael@gmail.com", "045556667777", 3, "Male", "MT12345", 10);
        Student student8 = new Student("ST08", "Olivia Martinez", "olivia@gmail.com", "049990001111", 2, "Female", "OM12345", 9);
        Student student9 = new Student("ST09", "William Anderson", "william@gmail.com", "043334445555", 4, "Male", "WA12345", 11);
        Student student10 = new Student("ST10", "Emily Thomas", "emily@gmail.com", "046667778888", 1, "Female", "ET12345", 8);
        Student student11 = new Student("ST11", "Daniel Jackson", "daniel@gmail.com", "041112223333", 5, "Male", "DJ12345", 11);
        Student student12 = new Student("ST12", "Mia White", "mia@gmail.com", "044445556666", 1, "Female", "MW12345", 7);
        Student student13 = new Student("ST13", "Alexander Harris", "alexander@gmail.com", "047778889999", 3, "Male", "AH12345", 10);
        Student student14 = new Student("ST14", "Chloe Martin", "chloe@gmail.com", "042223334444", 2, "Female", "CM12345", 9);
        Student student15 = new Student("ST15", "Isabella Thompson", "isabella@gmail.com", "045556667777", 4, "Female", "IT12345", 11);

// Add student information
        savestudentInfo(student1);
        savestudentInfo(student2);
        savestudentInfo(student3);
        savestudentInfo(student4);
        savestudentInfo(student5);
        savestudentInfo(student6);
        savestudentInfo(student7);
        savestudentInfo(student8);
        savestudentInfo(student9);
        savestudentInfo(student10);
        savestudentInfo(student11);
        savestudentInfo(student12);
        savestudentInfo(student13);
        savestudentInfo(student14);
        savestudentInfo(student15);
    }

    // save student details
    public void savestudentInfo(Student student) {
        // validation of leaner object not null
        if (student != null) {
            // save student info in list
            academyStudents.add(student);
        }
    }

    //show student
    public void showStudents() {
    System.out.println("\nStudent Details:");

    // Check if academyStudents is not empty
    if (!academyStudents.isEmpty()) {
        // Print header
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.printf(" %-10s   %-20s   %-25s   %-15s   %-10s   %-10s   %-10s\n",
                "Student Id", "Full Name", "Email", "Contact", "Gender", "Age", "Grade");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

        // Print student details
        for (Student studentInfo : academyStudents) {
            System.out.printf(" %-10s   %-20s   %-25s   %-15s   %-10s   %-10s   %-10s\n",
                    studentInfo.getStudentId(), studentInfo.getStudentName(), studentInfo.getStudentEmail(),
                    studentInfo.getStudentContact(), studentInfo.getStudentGender(), studentInfo.getStudentAge(),
                    " Grade " + studentInfo.getStudentGrade());
        }

        // Print footer
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
    } else {
        System.out.println("No students found.");
    }
}

    // validation of unique name
    public boolean studentNameExist(String name) {
        for (Student studentInfo : academyStudents) {
            if (studentInfo.getStudentName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // validation of unique email
    public boolean studentEmailExist(String email) {
        for (Student studentInfo : academyStudents) {
            if (studentInfo.getStudentEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
