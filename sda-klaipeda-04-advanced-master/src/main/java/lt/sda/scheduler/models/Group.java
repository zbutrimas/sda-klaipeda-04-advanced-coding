package lt.sda.scheduler.models;

import lt.sda.scheduler.MaximumNumberOfStudentsReached;

import java.util.*;
import java.util.stream.Collectors;

public class Group {
    private String name;
    private Trainer trainer;
    private Set<Student> studentSet = new HashSet<>();

    public Group(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.setAuthorized(true);
        trainer.addGroup(this);
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(studentSet);
    }

    public boolean isFull(){
        return this.studentSet.size() == 5;
    }

    public Integer getStudentCount() {
        return this.studentSet.size();
    }

    public Long getStudentsWithoutPreviousKnowledgeCount(){
        return this.studentSet
                .stream()
                .filter(s -> !s.isHasPreviousKnowledge())
                .count();
    }

    public void addStudent(Student student) throws MaximumNumberOfStudentsReached{
        if(isFull()){
            throw new MaximumNumberOfStudentsReached();
        }
        this.studentSet.add(student);
    }

    public List<Student> getStudentsByLastName(){
        return studentSet.stream().sorted((a, b) -> {
           return a.getLastName().compareTo(b.getLastName());
        }).collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return String.format("Trainer: %s, StudentCount: %d", trainer, getStudentCount());
    }

    public static Group groupWithMostStudents(Group... groups) {
        return groupWithMostStudents(Arrays.asList(groups));
    }

    public static Group groupWithMostStudents(Collection<Group> groups) {
        Optional<Group> groupWithMostStudents = groups.stream().max((left, right) -> {
            return left.getStudentCount().compareTo(right.getStudentCount());
        });

        return groupWithMostStudents.get();
    }

    public static Group groupWithMostUnexperiencedStudents(Collection<Group> groups){
        Optional<Group> groupWithMostStudents = groups.stream().max((left, right) -> {
            return left.getStudentsWithoutPreviousKnowledgeCount().compareTo(right.getStudentsWithoutPreviousKnowledgeCount());
        });

        return groupWithMostStudents.get();
    }
}
