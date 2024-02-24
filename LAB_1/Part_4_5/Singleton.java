package LAB_1.Part_4_5;

// 4
class Singleton {
	private String name;
	private static Singleton instance;

    public static Singleton getInstance(String nameParam) {
        if(instance == null) {
            instance = new Singleton(nameParam);
        }
        return instance;
    }

    protected Singleton(String nameParam) {
		this.name = nameParam;
	}

	public String toString() {
		return name;
	}
}