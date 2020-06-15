public class Main {
    public static void main(String[] args) {
        boolean guiMode = true;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                guiMode = false;
            }
        }
        if (guiMode) {
            new MainWindow();
        } else {
            ConsoleMode console = new ConsoleMode(args);
            console.runConsoleMode();
        }
    }
}