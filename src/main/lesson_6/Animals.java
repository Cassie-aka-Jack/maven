public class Animals {
    private static int animalCount = 0;
    protected String name;

    public Animals(String name) {
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public void run(double distance) {
    }

    public void swim(double distance) {
    }

    public boolean isFull() {
        return true;
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog("Doggy");
        Dog dog2 = new Dog("Goddy");
        Cat cat1 = new Cat("Caty");
        Cat cat2 = new Cat("Tacy");

        dog1.run(150);
        dog2.run(600);
        dog1.swim(5);
        dog2.swim(15);
        cat1.run(100);
        cat2.run(300);
        cat1.swim(10);
        cat2.swim(20);

        System.out.println("Total animals: " + Animals.getAnimalCount());
        System.out.println("Total dogs: " + Dog.getDogCount());
        System.out.println("Total cats: " + Cat.getCatCount());

        Cat[] cats = {cat1, cat2};
        FoodBowl foodBowl = new FoodBowl();
        foodBowl.addFood(15);

        for (Cat cat : cats) {
            cat.eat(10, foodBowl);
        }

        for (Cat cat : cats) {
            System.out.println(cat.name + " is full: " + cat.isFull());
        }
    }
}

class Dog extends Animals {
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(double distance) {
        if (distance <= 500) {
            System.out.println(name + " run " + distance + "m.");
        } else {
            System.out.println(name + " can't run " + distance + "m.");
        }
    }

    @Override
    public void swim(double distance) {
        if (distance <= 10) {
            System.out.println(name + " swim " + distance + "m.");
        } else {
            System.out.println(name + " can't swim " + distance + "m.");
        }
    }
}

class Cat extends Animals {
    private static int catCount = 0;
    private boolean isFull = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public void eat(double amount, FoodBowl foodBowl) {
        if (amount > foodBowl.getFoodAmount()) {
            System.out.println(name + " can't eat, not enough food in the bowl!");
            return;
        }
        foodBowl.takeFood(amount);
        isFull = true;
        System.out.println(name + " has eaten " + amount + " units of food.");
    }

    @Override
    public void run(double distance) {
        if (distance <= 200) {
            System.out.println(name + " run " + distance + "m.");
        } else {
            System.out.println(name + " can't run " + distance + "m.");
        }
    }

    @Override
    public void swim(double distance) {
        System.out.println(name + " can't swim!");
    }

    @Override
    public boolean isFull() {
        return isFull;
    }
}

class FoodBowl {
    private double foodAmount = 0;

    public void addFood(double amount) {
        if (amount > 0) {
            foodAmount += amount;
        }
    }

    public void takeFood(double amount) {
        if (amount > 0 && foodAmount >= amount) {
            foodAmount -= amount;
        }
    }

    public double getFoodAmount() {
        return foodAmount;
    }
}