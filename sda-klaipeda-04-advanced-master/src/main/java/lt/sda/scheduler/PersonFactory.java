package lt.sda.scheduler;

import java.time.LocalDate;
import java.util.Random;

public abstract class PersonFactory {
    protected static String[] firstNames = {"Jonas", "Petras", "Gražina", "Dalia", "Juozas"};
    protected static String[] lastNames = {"Petraitis", "Jonaitis", "Zygmantas", "Kiaušinienė"};
    protected static Random rand = new Random();

    protected static LocalDate getRandomBirthDate(){
        return LocalDate.of(1970 + rand.nextInt(35), 1 + rand.nextInt(11), 1 + rand.nextInt(27));
    }
}
