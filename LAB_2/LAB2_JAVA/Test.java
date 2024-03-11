package LAB2_JAVA;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Product{
    protected int lowerBound;
    protected int upperBound;
    protected int price;

    public Product(int lowerBoundParam, int upperBoundParam, int priceParam) {
        this.lowerBound = lowerBoundParam;
        this.upperBound = upperBoundParam;
        this.price = priceParam;
    }

    public int getPrice() {return price;}

    public abstract void push();
    public abstract void drop();
    public abstract boolean evaluate(int c);
}

class PotatoChips extends Product {

    // Use the constructor of the superclass with super() keyword
    public PotatoChips(int lowerBoundParam, int upperBoundParam, int priceParam) {
        super(lowerBoundParam, upperBoundParam, priceParam);
    }
    
    public boolean evaluate(int c){
        // Instead of 'if(c>0 && c<=20)'
        if(c>lowerBound && c<=upperBound){
            System.out.println("Found Chips...");
            return true;
        }
        else{
            /*
            If the 2nd product is in vending machine, it gives this warning 1 time,
            if the 3rd product is in vending machine, it gives this warning 2 times,
            if the 4th product is in vending machine, it gives this warning 3 .... times.
            This is undesirable and confusing. I have therefore removed this line
             */
            // System.out.println("No product...");
            return false;
        }
    }

    public void push(){System.out.println("Pushing out the Potato Chips...");}
    public void drop(){System.out.println("You can now take your Chips...");}
}

class Soda extends Product{

    // Use the constructor of the superclass with super() keyword
    public Soda(int lowerBoundParam, int upperBoundParam, int priceParam) {
        super(lowerBoundParam, upperBoundParam, priceParam);
    }
    public boolean evaluate(int c){
        // Instead of 'if(c>20 && c<=40)'
        if(c>lowerBound && c<=upperBound){
            System.out.println("Found Soda...");
            return true;
        }
        else {
            // Remove this for the same reason as the above:
            // System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Soda...");}
    public void drop(){System.out.println("You can now take your Soda...");}
}

class Gummy extends Product {

    // Use the constructor of the superclass with super() keyword
    public Gummy(int lowerBoundParam, int upperBoundParam, int priceParam) {
        super(lowerBoundParam, upperBoundParam, priceParam);
    }

    public boolean evaluate(int c){
        // Instead of 'if(c>40 && c<=60)'
        if(c>lowerBound && c<=upperBound){
            System.out.println("Found Gummy...");
            return true;
        }
        else{
            // Remove this for the same reason as the above:
            // System.out.println("No product...");
            return false;
        }
    }

    public void push(){System.out.println("Pushing out the Gummy...");}
    public void drop(){System.out.println("You can now take your Gummy...");}
}

class VendingMachine{
    Scanner scan = new Scanner(System.in);
    // Create ArrayList for all products
    ArrayList<Product> products = new ArrayList<Product>();
    // Initially, create a product as null
    Product product = null;

    // Fill the Vending Machine with the desired products
    public VendingMachine(ArrayList<Product> productsParam){
        products = productsParam;
    }
    // Change this method:
    // if(this.evaluate(ch))
    // The Vending Machine should return the product with the price in the desired range from the loaded products.
    public void custChoice(int ch){
        for (Product p : products) {
            if(p.evaluate(ch)==true) {
                // Assign the desired product to the product variable
                product = p;
                break;
            }
        }

        // System.out.println("Last Step: Please put the money for the product you choose.\nChips: 30\nSoda: 15\nGummy: 25");
        System.out.println("Last Step: Please put the money for the " + product.getClass().getName() + ": " + product.getPrice());
        int money = scan.nextInt();
        if(moneyCheck(money)) {
            product.push();
            product.drop();
        }
        else{System.out.println("You did not entered enough money... Cannot give product... Here, take your money back.");}
    }

    // public boolean moneyCheck(int p){
    //     if(this instanceof PotatoChips){
    //         return (p>=30)?true:false;
    //     }
    //     /*else if(this instanceof Soda){
    //         return (p>=15)?true:false;
    //     }
    //     else if(this instanceof Gummy){
    //         return (p>=25)?true:false;
    //     }*/
    //     else{
    //         return false;
    //     }
    // }

    public boolean moneyCheck(int p){
        return (p>=product.price)?true:false;
    }
}

public class Test{
    public static void main(String []args){;
        ArrayList<Product> products = new ArrayList<Product>();
        PotatoChips potatoChips = new PotatoChips(0, 20, 5);
        Soda soda = new Soda(21, 40, 15);
        Gummy gummy = new Gummy(41, 60, 25);
        products.add(potatoChips);
        products.add(soda);
        products.add(gummy);
        
        Scanner scan = new Scanner(System.in);
        VendingMachine V1 = new VendingMachine(products);
        System.out.println("Please enter the slot of the product you want...");
        V1.custChoice(scan.nextInt());
        scan.close();
    }
}

/*----------------------------------------------------------------------------------
- What happens, if they want to take another product besides Potato Chips?

    According to the code given at the beginning, they cannot buy any other product
except PotatoChips. To solve this, I left the Vending Machine as a class on its own
and collected all the products that can be sold in a class called Product. When you
want to add or remove a new product, there is no need to intervene in the code from
the outside and all addition and subtraction operations can be done in main class.
In this way, the code complies with OCP.
------------------------------------------------------------------------------------
- What happens, if they want to change the slot intervals for products?

    If you want to change the price intervals of slot products, you can do this with
the changeSlotInterval method after creating the product.
    Note: At this point I am ignoring the scenario where the intervals overlap
because it is assumed that the user logically takes this into account.
------------------------------------------------------------------------------------
- What happens, if they want to update prices of the products?

    If you want to update the prices of the products, you can do this with the
changeProductPrice() method.
------------------------------------------------------------------------------------
Footnote: I edited Test.java to ensure that these questions are answered correctly.
----------------------------------------------------------------------------------*/