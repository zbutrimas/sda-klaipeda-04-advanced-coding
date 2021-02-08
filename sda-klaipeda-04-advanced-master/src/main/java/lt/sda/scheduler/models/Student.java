package lt.sda.scheduler.models;

import java.time.LocalDate;

public class Student extends Person{
    private boolean hasPreviousKnowledge;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, boolean hasPreviousKnowledge) {
        super(firstName, lastName, dateOfBirth);
        this.hasPreviousKnowledge = hasPreviousKnowledge;
    }

    public boolean isHasPreviousKnowledge() {
        return hasPreviousKnowledge;
    }
}
