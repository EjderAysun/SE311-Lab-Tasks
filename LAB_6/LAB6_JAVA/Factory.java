package LAB_6.LAB6_JAVA;

import java.util.ArrayList;


//=======================================================================
//Name        : AbstractFactory.cpp
// 1. AbstractFactory  ( CarFactory )
//	  Declares an interface for operations that create abstract products
// 2. ConcreteFactory  (OPELFactory,FordFactory)
//	  Implements the operations to create concrete product objects
// 3. AbstractProduct   (Part, Engine, Transmission)
//	  Declares an interface for a type of product object
// 4. Product  (OPEL_Engine, OPEL_Transmission,
//				 FORD_Engine, FORD_Transmission.)
//	  Defines a product object to be created by the corresponding 
//    concrete factory implements the AbstractProduct interface
// 5. Client  (BuildCar)
//	  Uses interfaces declared by AbstractFactory and AbstractProduct 
//    classes
//=======================================================================

// Top "Abstract Product" Part class
abstract class Furniture {
	abstract public String displayName();
	abstract double getPrice();
}

// An 'Abstract Product A' class 
// Engine base class.
abstract class Chair extends Furniture {
	protected double price;
	protected String name;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
}

// A 'ConcreteProduct A1' class
class ModernAfrica extends Chair {	
	public ModernAfrica(double p) { 
		price = p;
		name = new String("Modern Africa");
		System.out.println("Modern Africa is created..."); 
	}
}

// A 'ConcreteProduct A2' class
class Antique1 extends Chair {
	public Antique1(double p) { 
		price = p;
		name = new String("Antique1");
		System. out.println("Antique1 is created...");
	}
}

// A 'ConcreteProduct A3' class
class WaveletChair extends Chair {
	public WaveletChair(double p) { 
		price = p;
		name = new String("Wavelet Chair");
		System. out.println("Wavelet Chair is created...");
	}
}

// An 'Abstract Product B' class 
// Transmission base class
abstract class CoffeeTable extends Furniture {
	protected double price;
	protected String name;
	public double getPrice(){ return price; }
	public String displayName() { return name;	}
}

// A 'ConcreteProduct B1' class
class MarbleCloud extends CoffeeTable {
	public MarbleCloud(double p) {
		price = p;
		name = new String("Marble Cloud");
		System. out.println("Marble Cloud is created...");
	}
}

// A 'ConcreteProduct B2' class
class ValedictorianTable extends CoffeeTable {
	public ValedictorianTable(double p) {
		price = p;
		name = new String("Valedictorian Table");
		System. out.println("Valedictorian Table is created...");
	}
}

// A 'Concrete Product B3' class
class CorianTable extends CoffeeTable {
    public CorianTable(double p) {
        price = p;
        name = new String("Corian Table");
		System. out.println("Corian Table is created...");
    }
}

// An 'Abstract Factory' class
abstract class FurnitureCompany {
	abstract public Chair createChair();
	abstract public CoffeeTable createTable();
    abstract public Furniture createAll(int indicator);
}

// A 'Concrete Factory' class
class ArtModernCompanyFactory extends FurnitureCompany {
	public ModernAfrica createChair() {
		return new ModernAfrica (25000.00);
	}
	public MarbleCloud createTable() {
		return new MarbleCloud(10000.00);
	}
    public Furniture createAll(int indicator) {
        if (indicator==1) return new ModernAfrica(25000.00);
        else if (indicator==2) return new MarbleCloud(10000.00);
        return null;
    }
}

// Another 'Concrete Factory' class
class HistoryCompanyFactory extends FurnitureCompany {
	public Antique1 createChair() {
		return new Antique1 (20000.00);
	}
	public ValedictorianTable createTable() {
		return new ValedictorianTable(12000.00);
	}
    public Furniture createAll(int indicator) {
        if (indicator==1) return new Antique1 (20000.00);
        else if (indicator==2) return new ValedictorianTable(12000.00);
        return null;
    }
}

class FutureNow extends FurnitureCompany {
    public WaveletChair createChair() {
        return new WaveletChair (10000.00);
    }
    public CorianTable createTable() {
        return new CorianTable (40000.00);
    }
    public Furniture createAll(int indicator) {
        if (indicator==1) return new WaveletChair (10000.00);
        else if (indicator==2) return new CorianTable (40000.00);
        return null;
    }
}

// The 'Client'.
class BuildFurniture {
	// Object creation is delegated to factory.
	public void createFurniture(FurnitureCompany factory) {
		parts = new ArrayList<Furniture>();
		// parts.add(factory.createChair());
		// parts.add(factory.createTable());
        parts.add(factory.createAll(1));
        parts.add(factory.createAll(2));
	}
	void displayParts() {
		System.out.println("\tListing Parts\n\t-------------");
		parts.forEach(p  -> System.out.println("\t"+ p.displayName() + " " + p.getPrice()));
	}
	private ArrayList<Furniture> parts;
}

// Abstract Factory Method Design Pattern.
// Entry point into main application.
public class Factory {
	public static void main(String[] args) {
        // Create factories.
        FurnitureCompany artModernCompany = new ArtModernCompanyFactory();
        FurnitureCompany historyCompany = new HistoryCompanyFactory();
        FurnitureCompany futureNow = new FutureNow();

        BuildFurniture furniture = new BuildFurniture();

        System.out.println("Creating Art Modern Company");
        furniture.createFurniture(artModernCompany);
        furniture.displayParts();

        System.out.println("Creating History Company");
        furniture.createFurniture(historyCompany);
        furniture.displayParts();

        System.out.println("Creating Future Now Company");
        furniture.createFurniture(futureNow);
        furniture.displayParts();
	}
}