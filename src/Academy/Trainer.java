package Academy;


import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String trainerId;
    private String trainerName;
    private String trainerAge;
    private String trainerGender;
    private String trainerEmail;
    private String trainerContact;
    public List<Trainer> swimTrainers;

    // Default constructor
    public Trainer() {
        this.swimTrainers = new ArrayList<>();
        addDefaultTrainers();
    }

    // Parameterized constructor
    public Trainer(String trainerId, String trainerName, String trainerAge, String trainerGender,
                   String trainerEmail, String trainerContact) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.trainerAge = trainerAge;
        this.trainerGender = trainerGender;
        this.trainerEmail = trainerEmail;
        this.trainerContact = trainerContact;
    }

    // Method to add default trainers
    private void addDefaultTrainers() {
       // CreatingTrainer objects
		Trainer trainer1 = new Trainer("TRN01", "David", "30", "Male", "john@gmail.com", "0412345678");
		Trainer trainer2 = new Trainer("TRN02", "Emma", "35", "Female", "jane@gmail.com", "0498765432");
		Trainer trainer3 = new Trainer("TRN03", "Daniel", "28", "Male", "alex@gmail.com", "0445612378");
		Trainer trainer4 = new Trainer("TRN04", "Sophia", "32", "Female", "emily@gmail.com", "0478945612");
		Trainer trainer5 = new Trainer("TRN05", "Matthew", "40", "Male", "michael@gmail.com", "0415935724");
		// Adding trainer objects to the list
		swimTrainers.add(trainer1);
		swimTrainers.add(trainer2);
		swimTrainers.add(trainer3);
		swimTrainers.add(trainer4);
		swimTrainers.add(trainer5);
    }

    // Getter methods
    public String getTrainerId() {
        return trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getTrainerAge() {
        return trainerAge;
    }

    public String getTrainerGender() {
        return trainerGender;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public String getTrainerContact() {
        return trainerContact;
    }

    public List<Trainer> getSwimTrainers() {
        return swimTrainers;
    }

    // Method to get trainer information by trainer ID
    public Trainer getTrainerInfo(String trainerId) {
        for (Trainer trainer : swimTrainers) {
            if (trainer.getTrainerId().equalsIgnoreCase(trainerId)) {
                return trainer;
            }
        }
        return null;
    }

    // Method to display all trainers
    public void showTrainers() {
        if (!swimTrainers.isEmpty()) {
            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
            System.out.printf("%-12s  %-17s  %-25s  %-12s  %-12s  %-18s\n",
                    "Trainer ID", "Full Name", "Email", "Age", "Gender", "Contact");
            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
            for (Trainer trainer : swimTrainers) {
                System.out.printf("%-12s  %-17s  %-25s  %-12s  %-12s  %-18s\n",
                        trainer.getTrainerId(), trainer.getTrainerName(), trainer.getTrainerEmail(),
                        trainer.getTrainerAge(), trainer.getTrainerGender(), trainer.getTrainerContact());
            }
            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        } else {
            System.out.println("No trainers available.");
        }
    }
}
