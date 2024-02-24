package LAB_1.Part_BONUS;

import java.util.ArrayList;
import java.util.HashMap;

// BONUS
class SubSingleton extends Singleton {

    private static HashMap<String, Singleton> instances;
    private static int numOfInstances;

    public static void registerInstances(ArrayList<String> names) {
        if(instances == null) {
            instances = new HashMap<String, Singleton>();
            for (String name : names) {
                instances.put(name, new Singleton(name));
            }
        } else {
            System.out.println("There are already " + numOfInstances + " instances.");
        }
    }

    // Ignore the "Override the getInstance method" part.
    public static Singleton getInstance(String nameParam) {
        if (instances != null) {
            for (String instance : instances.keySet()) {
                if(instance.equals(nameParam)) {
                    return instances.get(nameParam);
                }
            }
        }
        System.out.println("There is no instance with this name!");
        return null;
    }

    private SubSingleton(String nameParam) {
        super(nameParam);
    }
}