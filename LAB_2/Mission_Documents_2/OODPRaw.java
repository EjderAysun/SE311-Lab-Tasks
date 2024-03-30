import java.util.Scanner;

class PotatoChips{
    public boolean evaluate(int c){
        if(c>0 && c<=20){
            System.out.println("Found Chips...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Potato Chips...");}
    public void drop(){System.out.println("You can now take your Chips...");}
}

class Soda{
    public boolean evaluate(int c){
        if(c>20 && c<=40){
            System.out.println("Found Soda...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Soda...");}
    public void drop(){System.out.println("You can now take your Soda...");}
}

class Gummy{
    public boolean evaluate(int c){
        if(c>40 && c<=60){
            System.out.println("Found Gummy...");
            return true;
        }
        else{
            System.out.println("No product...");
            return false;
        }
    }
    public void push(){System.out.println("Pushing out the Gummy...");}
    public void drop(){System.out.println("You can now take your Gummy...");}
}

class VendingMachine extends PotatoChips{
    Scanner scan = new Scanner(System.in);
    public VendingMachine(){}
    public void custChoice(int ch){
        if(this.evaluate(ch)){
            System.out.println("Last Step: Please put the money for the product you choose.\nChips: 30\nSoda: 15\nGummy: 25");
            int money = scan.nextInt();
            if(moneyCheck(money)) {
                this.push();
                this.drop();
            }
            else{System.out.println("You did not entered enough money... Cannot give product... Here, take your money back.");}
        }
    }

    public boolean moneyCheck(int p){
        if(this instanceof PotatoChips){
            return (p>=30)?true:false;
        }
        /*else if(this instanceof Soda){
            return (p>=15)?true:false;
        }
        else if(this instanceof Gummy){
            return (p>=25)?true:false;
        }*/
        else{
            return false;
        }
    }
}

public class OODPRaw{
    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        VendingMachine V1 = new VendingMachine();
        System.out.println("Please enter the slot of the product you want...");
        V1.custChoice(scan.nextInt());
        scan.close();
    }
}
