public class Bear extends Thread {
    private final HoneyPot pot;

    public Bear(HoneyPot pot) {
        this.pot = pot;
    }

    @Override
    public void run() {
        while (true) {
            pot.eatHoney();
            try {
                // ведмідь спить
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
