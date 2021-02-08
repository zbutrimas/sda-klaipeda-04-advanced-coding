package lt.sda.scheduler;

import java.util.*;

public class Utils {
    private static Random rnd = new Random();
    public static<T> T getRandom(List<T> collection){
        return collection.get(rnd.nextInt(collection.size()));
    }

    public static<T> T getRandom(T[] array){
        return getRandom(Arrays.asList(array));
    }

    public static<T> List<T> shuffle(List<T> lst) {
        List<T> newList = new ArrayList<>(lst);
        Collections.shuffle(newList);
        return newList;
    }

    public static<T> void printList(Collection<T> col){
        for(T item : col){
            System.out.println(item);
        }
    }
}
