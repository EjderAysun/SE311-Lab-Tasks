package LAB_1.Part_4_5;

import java.util.HashMap;

// 4
class SubSingleton extends Singleton {

    // a
    private static HashMap<String, Singleton> instances;

    // b
    public static void registerInstances(String nameParam1, String nameParam2, String nameParam3) {
        if(instances == null) {
            instances = new HashMap<String, Singleton>();

            instances.put(nameParam1, new Singleton(nameParam1));
            instances.put(nameParam2, new Singleton(nameParam2));
            instances.put(nameParam3, new Singleton(nameParam3));
        } else {
            System.out.println("There are already 3 instances.");
        }
    }

    // c (Ignore the "Override the getInstance method" part.)
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