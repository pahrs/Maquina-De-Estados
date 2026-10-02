import java.util.ArrayList;
import java.util.List;

public class AgentManager {
    private final List<Agent> agents = new ArrayList<>();

    public void add(Agent agent) {
        agents.add(agent);
    }

    public void run(int totalHours, long delayMs) throws InterruptedException {
        int currentHour = 0;

        for (int step = 1; step <= totalHours; step++) {
            String timeFormatted = String.format("%02d:00", currentHour);
            System.out.println("\n---------- HORA " + timeFormatted + " ----------");

            for (Agent agent : agents) {
                agent.update(currentHour);
            }

            currentHour = (currentHour + 1) % 24;
            Thread.sleep(delayMs);
        }
    }
}