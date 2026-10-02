public class Mineiro extends Agent {
    public static final int MINING_DONE = 12;
    public static final int MIN_FOR_REQUEST = 6;
    public static final int WAKE_UP_HOUR = 5;
    public static final int SLEEP_HOUR = 20;

    private int carried = 0;
    private int stock = 0;
    private int toolWear = 0;
    private boolean oreRequested = false;

    public Mineiro() {
        super("MINEIRO");
        changeState(new Sleeping(this));
    }

    @Override
    public boolean shouldWakeUp() {
        return getHour() == WAKE_UP_HOUR;
    }

    @Override
    public void wakeUp() {
        changeState(new Mining(this));
    }

    public boolean isBedtime() {
        return getHour() == SLEEP_HOUR;
    }

    public void mine() {
        carried += 3;
        toolWear += 2;
    }

    public void repair() {
        toolWear = Math.max(toolWear - 4, 0);
    }

    public int storeOre() {
        int stored = carried;
        stock += carried;
        carried = 0;
        return stored;
    }

    public int takeStock() {
        int taken = stock;
        stock = 0;
        oreRequested = false;
        return taken;
    }

    public void requestOre() {
        oreRequested = true;
    }

    public void cancelRequest() {
        oreRequested = false;
    }

    public boolean isOreRequested() {
        return oreRequested;
    }

    public boolean hasStock() {
        return stock > 0;
    }

    public int getStock() {
        return stock;
    }

    public int getCarried() {
        return carried;
    }

    public int getToolWear() {
        return toolWear;
    }

    @Override
    public void printStatus(String action) {
        System.out.println("\n===== " + getName() + " =====");
        System.out.println(action);
        System.out.println("Minério na mochila: " + carried);
        System.out.println("Minério guardado no estoque: " + stock);
        System.out.println("Desgaste da picareta: " + toolWear);
        System.out.println("Pedido do ferreiro: " + oreRequested);
    }
}