package lt.sda.scheduler.models;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class StudentTest {
    @Test
    public void testStudentAge(){
        LocalDate birthDate = LocalDate.of(1970, 1, 1);
        Student student = new Student("Test", "Test", birthDate, false);
        assertEquals(51, student.getAge());
    }
}
