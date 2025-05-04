public class Bee extends Thread {
    private final HoneyPot pot;
    private final int id;

    public Bee(HoneyPot pot, int id) {
        this.pot = pot;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // імітація збору меду
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pot.addHoney(id);
        }
    }
}
