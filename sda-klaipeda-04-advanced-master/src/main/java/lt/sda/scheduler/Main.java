package lt.sda.scheduler;

import lt.sda.scheduler.models.Group;
import lt.sda.scheduler.models.Student;
import lt.sda.scheduler.models.Trainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        List<Student> studentList = new ArrayList<>();
        for(int i = 0; i<15; i++){
            studentList.add(StudentFactory.getRandomStudent());
        }

        // Filter younger than 20
        studentList = studentList.stream().filter(s -> s.getAge() > 20).collect(Collectors.toList());

        List<Trainer> trainerList = new ArrayList<>();
        trainerList.add(TrainerFactory.getRandomTrainer());
        trainerList.add(TrainerFactory.getRandomTrainer());
        trainerList.add(TrainerFactory.getRandomTrainer());

        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group("Java 101"));
        groupList.add(new Group("Testing 101"));
        groupList.add(new Group("Java Advanced"));
        groupList.add(new Group("HR Tips"));

        for(Group g : groupList){
            g.setTrainer(Utils.getRandom(trainerList));
        }

        List<Group> availableGroups = new ArrayList(groupList);
        int current = 0;
        for(Student s : Utils.shuffle(studentList)){
            if(availableGroups.isEmpty()){
                System.err.println("No available groups left");
                break;
            }
            Group g = availableGroups.get(current);
            g.addStudent(s);
            if(g.isFull()){
                availableGroups = groupList.stream().filter(x -> !x.isFull()).collect(Collectors.toList());
                current = 0;
            }
            else {
                current++;
                if(current >= availableGroups.size()){
                    current = 0;
                }
            }
        }

        for(Group g : groupList){
            System.out.printf("\nGroup: %s\n-------------------\n", g.getName());
            Utils.printList(g.getStudentsByLastName());
        }

        // 1st method
        for(Trainer t : trainerList){
            System.out.printf("\nTrainer: %s\n-------------------\n", t.getName());
            t.printStudents();
        }


        // 2nd method
        groupList
                .stream()
                .collect(Collectors.groupingBy(g -> g.getTrainer()))
                .entrySet()
                .stream()
                .forEach(pair -> {
                    System.out.printf("\nTrainer: %s\n-------------------\n", pair.getKey().getName());
                    for(Group g : pair.getValue()){
                        Utils.printList(g.getStudents());
                    }
                });

        Group g1 = Group.groupWithMostStudents(groupList);
        System.out.printf("Group with most students: %s\n", g1.getName());

        Group g2 = Group.groupWithMostUnexperiencedStudents(groupList);
        System.out.printf("Group with most unexperienced students: %s\n", g2.getName());

        System.out.println("Students with previous Java knowledge:");

        studentList
                .stream()
                .filter(s -> s.isHasPreviousKnowledge())
                .forEach(s -> System.out.println(s) );

        groupList.stream()
                .flatMap( g -> g.getStudents().stream() )
                //.filter( s -> s.isHasPreviousKnowledge())
                .forEach( s -> System.out.printf("Student: %s Previous knowledge? %b\n", s, s.isHasPreviousKnowledge()) );
    }
}
