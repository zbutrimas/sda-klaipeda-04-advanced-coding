package lt.sda.scheduler;

import lt.sda.scheduler.models.Student;

public class StudentFactory extends PersonFactory {
    public static Student getRandomStudent(){
        return new Student(
                Utils.getRandom(firstNames),
                Utils.getRandom(lastNames),
                getRandomBirthDate(),
                rand.nextBoolean()
        );
    }
}
