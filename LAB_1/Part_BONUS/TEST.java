package LAB_1.Part_BONUS;

import java.util.ArrayList;

// BONUS
class TEST {
	public static void main(String[] args) {
		// Create names array list
        ArrayList<String> names = new ArrayList<String>(); 

		// Add n(I chose 27 at random) names(I + number format) to names array list
        for (int i = 1; i <= 27777777; i++) {
            String x = Integer.toString(i);
            names.add("I"+x);
        }

		// add n(I chose 27 at random) instances and test
        SubSingleton.registerInstances(names);
        System.out.println("-----------");
        System.out.println(SubSingleton.getInstance("I3"));
        System.out.println("-----------");
        System.out.println(SubSingleton.getInstance("I5"));
        System.out.println("-----------");
        System.out.println(SubSingleton.getInstance("I56"));
        System.out.println("-----------");
        System.out.println(SubSingleton.getInstance("I4"));
        System.out.println("-----------");
        System.out.println(SubSingleton.getInstance("I0"));
        System.out.println("-----------");
        System.out.println(SubSingleton.getInstance("I12"));
        System.out.println("-----------");
	}
}