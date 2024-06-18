package LAB_4.Mission_Documents_4;

import java.util.ArrayList;
//
// The classes and/or objects participating in this pattern are:
// 1. Component   (DrawingElement)
//		Declares the interface for objects in the composition. Implements
//      default behavior for the interface common to all classes, as
//      appropriate. declares an interface for accessing and managing its
//		child components.
// 2. Leaf   (PrimitiveElement)
//		represents leaf objects in the composition. A leaf has no children.
//	    Defines behavior for primitive objects in the composition.
// 3. Composite   (CompositeElement)
//		defines behavior for components having children. Stores child
//		components. Implements child-related operations in the Component interface.
// 4. Client  (CompositeApp)
//		Manipulates objects in the composition through the Component interface.

// This is the "Component". (i.e tree node.)
interface DrawingElement {
	void Add(DrawingElement d);
	void Remove(DrawingElement d);
	void Display(int indent);
	public String getName();
}
//This is the "Leaf".
class PrimitiveElement implements DrawingElement {
	private String name;
	public String getName() { return name;}
	public PrimitiveElement(String name) {this.name = name;}
	public void Add(DrawingElement c) {
		System.out.println("Cannot add to a PrimitiveElement.");
	}
	public  void Remove(DrawingElement c) {
		System.out.println("Cannot remove from a PrimitiveElement.");
	}
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) 	System.out.print("-");
		System.out.println(" "  + name);
	}
}
// This is the "Composite"
class CompositeElement implements DrawingElement {
	private String name;
	public String getName() { return name;}
	public CompositeElement(String name) {this.name = name;}
	public void Add(DrawingElement d) {elements.add(d);};
	public void Remove(DrawingElement d) {
		for (int i = 0; i< elements.size(); i++) {
			if (elements.get(i).getName() == d.getName()) {
				elements.remove(i);
				return;
			}
		}
	}
	public	void Display(int indent) {
		for(int i = 1;i <= indent;i++) System.out.print("-");
		System.out.println( "+ " + getName());

		// Display each child element on this node
		for (int i= 0; i< elements.size(); i++) {
			elements.get(i).Display(indent+2);
		}
	}
	private	ArrayList<DrawingElement> elements = new ArrayList<DrawingElement>();
}
//This is the "client"
public class CompositePatternRaw {
	public static void main(String[] args) {
	
		// Create a tree structure
		DrawingElement root = new CompositeElement("Picture");
		root.Add(new PrimitiveElement("Red Line"));
		root.Add(new PrimitiveElement("Blue Circle"));
		root.Add(new PrimitiveElement("Green Box"));

		DrawingElement comp = new CompositeElement("Two Circles");
		comp.Add(new PrimitiveElement("Black Circle"));
		comp.Add(new PrimitiveElement("White Circle"));
		root.Add(comp);

		// Add and remove a PrimitiveElement
		DrawingElement pe = new PrimitiveElement("Yellow Line");
		pe.Add(new PrimitiveElement("Red Line"));
		root.Add(pe);
		root.Remove(pe);

		// Recursively display nodes
		root.Display(1);
	}
}