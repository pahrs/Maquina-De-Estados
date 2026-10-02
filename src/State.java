public interface State {
    default void enter() {
    }

    void execute();

    default void leave() {
    }
}