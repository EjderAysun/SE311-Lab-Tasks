package LAB_9.LAB9_JAVA_Solution_With_Two_Hook;

import java.util.Scanner;

abstract class AbstractBread {
    protected void TemplateMethod() {
        mix_Ingredients();
        hook1();
        knead();
        hook2();
        bake();
    }

    protected abstract void mix_Ingredients();
    protected abstract void knead();
    protected abstract void bake();
    protected abstract void hook1();
    protected abstract void hook2();
}

class WhiteLoaf extends AbstractBread {
    public WhiteLoaf() {}
    
    protected void mix_Ingredients(){
        System.out.println("WHITE LOAF BREAD RECIPE");
        System.out.println("Mix 500g strong white flour, 2 tsp salt and a 7g sachet of fast-action yeast in a large bowl.");
        System.out.println("Make a well in the centre, then add 3 tbsp olive oil and 300ml water, and mix well.");
    }
    protected void knead(){
        System.out.println("Tip the white loaf dough onto a lightly floured work surface and knead for around 10 mins.");
    }
    protected void bake(){
        System.out.println("Bake for 25-30 mins until golden brown and the white loaf sounds hollow when tapped underneath.");
    }
    protected void hook1() {}
    protected void hook2() {
        give_shape();
    }
    protected void give_shape(){
        System.out.println("Knock back the white loaf dough (punch the air out and pull the dough in on itself) then gently mould the dough into a ball.");
    }
}

class NutsSeeds extends AbstractBread {
    public NutsSeeds() {}

    protected void mix_Ingredients(){
        System.out.println("NUTS-SEEDS BREAD RECIPE");
        System.out.println("Mix together some almond flour, coconut flour, baking soda, and salt.");
        System.out.println("Add eggs, olive oil, apple cider vinegar, and honey.");
    }
    protected void knead(){
        System.out.println("Tip the mixed dough onto a lightly floured work surface and knead for around 10 mins.");
    }
    protected void bake(){
        System.out.println("Transfer the dough to a loaf pan, using a spatula to make sure itâ€™s evenly spread across the pan.");
        System.out.println("Bake your bread for about 38-41 minutes or until a toothpick comes out clean.");
    }

    protected void hook1() {
        add_nuts();
    }
    protected void add_nuts(){
        System.out.println("Add in the flax seeds, chia seeds and large amount of nuts of your choice.");
    }

    protected void hook2() {
        seeds_on_top();
    }
    protected void seeds_on_top(){
        System.out.println("Top your bread with a generous amount of extra seeds such as sunflower seeds. ");
    }
}

public class Bread {
    public static void main(String[] args) {
        WhiteLoaf wl = new WhiteLoaf();
        NutsSeeds ns = new NutsSeeds();
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the recipe app.\nPlease choose which recipe you wanna see(int):\n1) White Loaf Bread\n2) Nuts-Seeds Bread");
        int choice = scan.nextInt();
        scan.close();
        switch (choice){
            case 1: wl.TemplateMethod(); break;
            case 2: ns.TemplateMethod(); break;
            default: System.out.println("Wrong choice...");
        }
    }
}