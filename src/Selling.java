public class Selling implements State {
    private final Ferreiro ferreiro;

    public Selling(Ferreiro ferreiro) {
        this.ferreiro = ferreiro;
    }

    @Override
    public void enter() {
        System.out.println("Espadas prontas, hora de vender na feira!");
    }

    @Override
    public void execute() {
        ferreiro.sell();
        ferreiro.printStatus("Vendendo...");

        if (ferreiro.getSwords() <= 0) {
            ferreiro.forgeOrWait();
        }
    }
}