package lt.sda.scheduler.models;

import lt.sda.scheduler.MaximumNumberOfStudentsReached;
import lt.sda.scheduler.StudentFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GroupTest {
    @Test
    public void testGroupDistinct(){
        Student s1 = StudentFactory.getRandomStudent();
        Student s2 = new Student(s1.getFirstName(), s1.getLastName(), s1.getDateOfBirth(), s1.isHasPreviousKnowledge());
        Group g = new Group("Test");
        assertEquals(s1, s2);
        int h1 = s1.hashCode();
        int h2 = s2.hashCode();
        g.addStudent(s1);
        g.addStudent(s2);
        assertEquals(1, g.getStudents().size());
    }

    @Test
    public void testGroupLimit(){
        Group g = new Group("Test");
        assertThrows(MaximumNumberOfStudentsReached.class, () -> {
            for(int i = 0; i<10; i++){
                g.addStudent(StudentFactory.getRandomStudent());
            }
        });
    }

    @Test
    public void testGroupSorting(){
        List<Group> groups = new ArrayList<>();
        Group a = new Group("GroupA");
        Group b = new Group("GroupB");
        Group c = new Group("GroupC");

        for(int i = 0; i<3; i++){
            a.addStudent(StudentFactory.getRandomStudent());
        }

        for(int i = 0; i<5; i++){
            b.addStudent(StudentFactory.getRandomStudent());
        }

        for(int i = 0; i<4; i++){
            c.addStudent(StudentFactory.getRandomStudent());
        }


        Group top = Group.groupWithMostStudents(a, b, c);

        assertEquals("GroupB", top.getName());
    }

    @Test
    public void breakTheRules(){
        Group group = new Group("Test");
        assertThrows(java.lang.UnsupportedOperationException.class, () -> {
            for(int i = 0; i<10; i++){
                group.getStudents().add(StudentFactory.getRandomStudent());
            }
        });
    }
}
