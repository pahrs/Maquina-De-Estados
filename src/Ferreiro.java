public class Ferreiro extends Agent {
    public static final int ORE_PER_CYCLE = 2;
    public static final int SWORD_DONE = 20;
    public static final int SWORDS_TO_SELL = 2;
    public static final int ORE_TO_START_WORK = 12;
    public static final int NIGHT_HOUR = 20;
    public static final int NIGHT_END_HOUR = 5;

    private final Mineiro mineiro;
    private int ore = 0;
    private int progress = 0;
    private int swords = 0;
    private int gold = 0;

    public Ferreiro(Mineiro mineiro) {
        super("FERREIRO");
        this.mineiro = mineiro;
        changeState(new Sleeping(this));
    }

    @Override
    public boolean shouldWakeUp() {
        return mineiro.getStock() >= ORE_TO_START_WORK;
    }

    @Override
    public void wakeUp() {
        refill();
        changeState(new Forging(this));
    }

    public boolean isNight() {
        int hour = getHour();
        return hour >= NIGHT_HOUR || hour < NIGHT_END_HOUR;
    }

    public void forge() {
        ore -= ORE_PER_CYCLE;
        progress += 5;
        if (progress >= SWORD_DONE) {
            progress = 0;
            swords++;
        }
    }

    public void sell() {
        swords--;
        gold += 10;
    }

    public boolean hasOre() {
        return ore >= ORE_PER_CYCLE;
    }

    public boolean refill() {
        if (mineiro.hasStock()) {
            ore += mineiro.takeStock();
            return true;
        }
        return false;
    }

    public void askForOre() {
        mineiro.requestOre();
    }

    public void cancelRequest() {
        mineiro.cancelRequest();
    }

    public void forgeOrWait() {
        if (hasOre() || refill()) {
            changeState(new Forging(this));
        } else {
            waitOrSleep();
        }
    }

    public void waitOrSleep() {
        if (isNight()) {
            changeState(new Sleeping(this));
        } else {
            changeState(new WaitingOre(this));
        }
    }

    public int getSwords() {
        return swords;
    }

    @Override
    public void printStatus(String action) {
        System.out.println("\n===== " + getName() + " =====");
        System.out.println(action);
        System.out.println("Minério disponível para forjar: " + ore);
        System.out.println("Progresso da espada: " + progress);
        System.out.println("Espadas prontas: " + swords);
        System.out.println("Ouro: " + gold);
    }
}