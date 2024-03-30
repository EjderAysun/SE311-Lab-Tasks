package LAB_1.Mission_Documents_1;

public class SingletonPatternRaw {
	String name;
	public SingletonPatternRaw(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}

class TEST {
	public static void main(String[] args) {
		System.out.println(new SingletonPatternRaw("I1"));
	}
}

