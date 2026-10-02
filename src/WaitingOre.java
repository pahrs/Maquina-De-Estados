public class WaitingOre implements State {
    private final Ferreiro ferreiro;

    public WaitingOre(Ferreiro ferreiro) {
        this.ferreiro = ferreiro;
    }

    @Override
    public void enter() {
        System.out.println("Acabou o minério! Mineiro, preciso de mais!");
        ferreiro.askForOre();
    }

    @Override
    public void execute() {
        if (ferreiro.refill()) {
            System.out.println("O minério chegou!");
            ferreiro.changeState(new Forging(ferreiro));
        } else if (ferreiro.isNight()) {
            ferreiro.changeState(new Sleeping(ferreiro));
        } else {
            ferreiro.printStatus("Esperando o minério...");
        }
    }

    @Override
    public void leave() {
        ferreiro.cancelRequest();
    }
}