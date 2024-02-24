package LAB_1.Part_4_5;

// 5
class TEST {
	public static void main(String[] args) {
        // add 3 instances and test
		SubSingleton.registerInstances("a", "b", "c");
        System.out.println("-------------------------------");
        System.out.println(SubSingleton.getInstance("a"));
        System.out.println("-------------------------------");
        System.out.println(SubSingleton.getInstance("b"));
        System.out.println("-------------------------------");
        System.out.println(SubSingleton.getInstance("f"));
        System.out.println("-------------------------------");
        System.out.println(SubSingleton.getInstance("c"));
        System.out.println("-------------------------------");
        System.out.println(SubSingleton.getInstance("k"));
        System.out.println("-------------------------------");
	}
}