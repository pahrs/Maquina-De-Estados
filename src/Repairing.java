public class Repairing implements State {
    private final Mineiro mineiro;

    public Repairing(Mineiro mineiro) {
        this.mineiro = mineiro;
    }

    @Override
    public void enter() {
        System.out.println("A picareta está gasta, preciso reparar...");
    }

    @Override
    public void execute() {
        if (mineiro.isBedtime()) {
            mineiro.changeState(new Sleeping(mineiro));
            return;
        }

        mineiro.repair();
        mineiro.printStatus("Reparando a picareta...");

        if (mineiro.getToolWear() <= 0) {
            System.out.println("Picareta reparada!");
            mineiro.changeState(new Mining(mineiro));
        }
    }
}