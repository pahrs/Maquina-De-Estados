public class Sleeping implements State {
    private final Agent agent;

    public Sleeping(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void enter() {
        System.out.println(agent.getName() + " foi dormir...");
    }

    @Override
    public void execute() {
        if (agent.shouldWakeUp()) {
            agent.wakeUp();
        } else {
            agent.printStatus("Dormindo...");
        }
    }

    @Override
    public void leave() {
        System.out.println(agent.getName() + " acordou!");
    }
}