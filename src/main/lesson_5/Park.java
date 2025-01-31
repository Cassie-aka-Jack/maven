public class Park {
    static class Attraction {
        String name;
        String opening;
        double price;

        Attraction(String name, String opening, double price) {
            this.name = name;
            this.opening = opening;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Attraction: " + "name = '" + name + '\'' + ", opening = " + opening + ", price = " + price;
        }
    }

    public void createAttractions() {
        Attraction rollerCoaster = new Attraction("Roller Coaster", "10:00 - 18:00", 5.0);
        Attraction ferrisWheel = new Attraction("Ferris Wheel", "10:00 - 20:00", 3.0);
        Attraction hauntedHouse = new Attraction("Haunted House", "11:00 - 19:00", 4.0);
        Attraction bumperCars = new Attraction("Bumper Cars", "10:00 - 21:00", 2.5);

        System.out.println(rollerCoaster);
        System.out.println(ferrisWheel);
        System.out.println(hauntedHouse);
        System.out.println(bumperCars);
    }

    public static void main(String[] args) {
        Park park = new Park();
        park.createAttractions();
    }
}