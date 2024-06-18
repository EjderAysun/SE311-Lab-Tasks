package LAB_4.LAB4_JAVA;

import java.util.ArrayList;

// This is the "Component". (i.e tree node.)
interface HepsiTrendy11 {
	void Add(HepsiTrendy11 hd11);
	void Remove(HepsiTrendy11 hd11);
	void Display(int indent);
	public String getName();
	public boolean find(String str);
	// public int totalPrice(String categoryName);
}

//This is the "Leaf".
class Product implements HepsiTrendy11 {
	private String name;
	private String description;
	private int price;

	public String getName() {return name;}
	public String getDescription() {return description;}
	public double getPrice() {return price;}

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public void Add(HepsiTrendy11 c) {
		System.out.println("Cannot add to a Product.");
	}
	public void Remove(HepsiTrendy11 c) {
		System.out.println("Cannot remove from a Product.");
	}
	public void Display(int indent) {
		for(int i = 1;i <= 2*indent;i++) System.out.print(" ");
		System.out.println("- "  + name);
	}

	public boolean find(String str){return false;}

	// public int totalPrice(String categoryName) {
        // return price;
    // }
}

// This is the "Composite"
class Category implements HepsiTrendy11 {
	private String name;
	private	ArrayList<HepsiTrendy11> elements = new ArrayList<HepsiTrendy11>();

	public String getName() { return name;}
	public ArrayList<HepsiTrendy11> getElements() {return elements;}

	public Category(String name) {this.name = name;}

	public void Add(HepsiTrendy11 hepsiTrendy11) {elements.add(hepsiTrendy11);};

	public void Remove(HepsiTrendy11 hepsiTrendy11) {
		for (int i = 0; i< elements.size(); i++) {
			if (elements.get(i).getName() == hepsiTrendy11.getName()) {
				elements.remove(i);
				return;
			}
		}
	}

	public boolean find(String str) {
		if(this.name.equals(str)) return true;
		else {
			for (HepsiTrendy11 hepsiTrendy11 : elements) {
				if(hepsiTrendy11.getName().equals(str)) return true;
				else {
					// Category c = (Category) hepsiTrendy11;
					if(hepsiTrendy11.find(str)) return true;
				}
			}
		}
		return false;
	}

	public void Display(int indent) {
		for(int i = 1;i <= 2*indent;i++) System.out.print(" ");
		System.out.println( "+ " + getName());

		// Display each child element on this node
		for (int i= 0; i< elements.size(); i++) {
			elements.get(i).Display(indent+2);
		}
	}

	// public int totalPrice(String categoryName) {
		// I will come back here.
	// }

}

//This is the "client"
public class CompositePattern {
	public static void main(String[] args) {
	
		// Create a tree structure
		Category electronics = new Category("Electronics");

		Category tv = new Category("TV");
		electronics.Add(tv);
		Product qled_tv = new Product("QLED TV", 10);
		tv.Add(qled_tv);
		tv.Add(qled_tv);

		Category pc = new Category("PC");
		electronics.Add(pc);
		Product ram = new Product("RAM", 20);
		Product ssd = new Product("SSD", 30);
		pc.Add(ram);
		pc.Add(ssd);

		Category fashion = new Category("Fashion");
		Category men = new Category("Men");
		fashion.Add(men);
		Product suit = new Product("Suit", 40);
		men.Add(suit);

		Category women = new Category("Women");
		Product shirt = new Product("Shirt", 50);
		Product skirt = new Product("Skirt", 60);
		women.Add(shirt);
		women.Add(skirt);

		Category outdoor = new Category("Outdoor");
		Product tent = new Product("Tent", 70);
		outdoor.Add(tent);

		Category cosmetics = new Category("Cosmetics");
		Category skin_care = new Category("Skin Care");
		cosmetics.Add(skin_care);
		Product face_cream = new Product("Face Cream", 80);
		Product sun_protector = new Product("Sun Protector", 90);
		skin_care.Add(face_cream);
		skin_care.Add(sun_protector);
		Product shampoo = new Product("Shampoo", 100);
		Product parfum = new Product("Parfum", 110);
		cosmetics.Add(shampoo);
		cosmetics.Add(parfum);

		Category shop_menu = new Category("Shop Menu");

		shop_menu.Add(electronics);
		shop_menu.Add(fashion);
		shop_menu.Add(outdoor);
		shop_menu.Add(cosmetics);

		// Add and remove a Product
		Category x = new Category("X");
		shop_menu.Add(x);
		Product y = new Product("y", 3);
		x.Add(y);
		y.Add(x);
		shop_menu.Remove(x);

		// Recursively display nodes
		shop_menu.Display(1);

		// Test find method
		System.out.println("Are 'Entrees' exist in the menu? " + shop_menu.find("Entrees"));
		System.out.println("Are 'Soft Drinks' exist in the menu? " + shop_menu.find("Soft Drinks"));
		System.out.println("Are 'Fashion' exist in the menu? " + shop_menu.find("Fashion"));
		System.out.println("Are 'Face Cream' exist in the menu? " + shop_menu.find("Face Cream"));

		// Test totalPrice method
		// System.out.println("Total price of electronics: " + shop_menu.totalPrice("Electronics"));
		// System.out.println("Total price of fashion: " + shop_menu.totalPrice("Fashion"));
	}
}