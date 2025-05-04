public class HoneyPotSimulation {

    public static void main(String[] args) {
        int beeCount = 5;
        int potCapacity = 10;

        HoneyPot pot = new HoneyPot(potCapacity);

        // Створення ведмедя
        Bear bear = new Bear(pot);
        bear.start();

        // Створення бджіл
        for (int i = 1; i <= beeCount; i++) {
            new Bee(pot, i).start();
        }
    }

}