package lt.sda.scheduler;

import lt.sda.scheduler.models.Trainer;

public class TrainerFactory extends PersonFactory {
    public static Trainer getRandomTrainer(){
        return new Trainer(
                Utils.getRandom(firstNames),
                Utils.getRandom(lastNames),
                getRandomBirthDate()
        );
    }
}
