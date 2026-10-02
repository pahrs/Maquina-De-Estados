public class Mining implements State {
    private final Mineiro mineiro;

    public Mining(Mineiro mineiro) {
        this.mineiro = mineiro;
    }

    @Override
    public void enter() {
        System.out.println("Vamos minerar!");
    }

    @Override
    public void execute() {
        if (mineiro.isBedtime()) {
            mineiro.changeState(new Sleeping(mineiro));
            return;
        }

        mineiro.mine();
        mineiro.printStatus("Minerando...");

        boolean full = mineiro.getCarried() >= Mineiro.MINING_DONE;
        boolean urgent = mineiro.isOreRequested() && mineiro.getCarried() >= Mineiro.MIN_FOR_REQUEST;

        if (full || urgent) {
            mineiro.changeState(new Repairing(mineiro));
        }
    }

    @Override
    public void leave() {
        if (mineiro.storeOre() > 0) {
            System.out.println("O minério foi guardado no estoque para o ferreiro!");
        }
    }
}