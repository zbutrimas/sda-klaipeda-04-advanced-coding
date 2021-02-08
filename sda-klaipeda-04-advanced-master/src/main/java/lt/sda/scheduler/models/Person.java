package lt.sda.scheduler.models;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge(){
        return dateOfBirth.until(LocalDate.now()).getYears();
    }

    @Override
    public String toString(){
        return String.format("FirstName: %s LastName: %s age: %d", firstName, lastName, getAge());
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    @Override
    public boolean equals(Object obj){
        Person other = (Person)obj;
        return this.firstName.equalsIgnoreCase(other.getFirstName()) &&
                this.lastName.equalsIgnoreCase(other.getLastName()) &&
                this.getDateOfBirth().isEqual(other.getDateOfBirth());
    }
}
