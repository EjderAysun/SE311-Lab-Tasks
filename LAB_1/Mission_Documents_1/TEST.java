package LAB_1.Mission_Documents_1;

class Singleton {
	String name;
	public Singleton(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}

public class TEST {
	public static void main(String[] args) {
		System.out.println(new Singleton("I1"));
	}
}

