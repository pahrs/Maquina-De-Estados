public class Forging implements State {
    private final Ferreiro ferreiro;

    public Forging(Ferreiro ferreiro) {
        this.ferreiro = ferreiro;
    }

    @Override
    public void enter() {
        System.out.println("Forja acesa!");
    }

    @Override
    public void execute() {
        ferreiro.forge();
        ferreiro.printStatus("Forjando...");

        if (ferreiro.getSwords() >= Ferreiro.SWORDS_TO_SELL) {
            ferreiro.changeState(new Selling(ferreiro));
        } else if (!ferreiro.hasOre() && !ferreiro.refill()) {
            ferreiro.waitOrSleep();
        }
    }
}