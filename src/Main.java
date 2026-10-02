public class Main {
    public static void main(String[] args) throws InterruptedException {
        int totalHours = args.length > 0 ? Integer.parseInt(args[0]) : 49;
        long delayMs = args.length > 1 ? Long.parseLong(args[1]) : 1500;

        Mineiro mineiro = new Mineiro();
        Ferreiro ferreiro = new Ferreiro(mineiro);

        AgentManager manager = new AgentManager();
        manager.add(ferreiro);
        manager.add(mineiro);
        manager.run(totalHours, delayMs);
    }
}