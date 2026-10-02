public abstract class Agent {
    private final String name;
    private State state;
    private int hour;

    protected Agent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHour() {
        return hour;
    }

    public void changeState(State next) {
        if (state != null) {
            state.leave();
        }
        state = next;
        state.enter();
    }

    public void update(int currentHour) {
        hour = currentHour;
        state.execute();
    }

    public abstract boolean shouldWakeUp();

    public abstract void wakeUp();

    public abstract void printStatus(String action);
}