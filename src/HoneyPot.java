public class HoneyPot {
    private final int capacity;
    private int current = 0;

    public HoneyPot(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addHoney(int beeId) {
        while (current == capacity) {
            try {
                // чекаємо, поки ведмідь з'їсть мед
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        current++;
        System.out.println("Бджола " + beeId + " принесла порцію меду. У горщику: " + current + "/" + capacity);

        if (current == capacity) {
            System.out.println("Бджола " + beeId + " заповнила горщик та будить ведмедя!");
            notifyAll(); // будимо ведмедя
        }
    }

    public synchronized void eatHoney() {
        while (current < capacity) {
            try {
                // чекаємо, поки горщик не буде заповнений
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ведмідь прокинувся і з'їв увесь мед!");
        current = 0;
        // будимо бджіл
        notifyAll();
        System.out.println("Ведмідь заснув.\n");
    }
}
