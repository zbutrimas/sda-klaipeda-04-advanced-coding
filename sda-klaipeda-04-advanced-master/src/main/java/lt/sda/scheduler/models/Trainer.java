package lt.sda.scheduler.models;

import lt.sda.scheduler.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trainer extends Person {
    private boolean isAuthorized = false;
    private List<Group> teachedByMe = new ArrayList();

    public Trainer(String firstName, String lastName, LocalDate dateOfBirth) {
        super(firstName, lastName, dateOfBirth);
    }

    public void addGroup(Group g){
        teachedByMe.add(g);
    }

    public void printStudents(){
        for(Group g : teachedByMe){
            Utils.printList(g.getStudents());
        }
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
