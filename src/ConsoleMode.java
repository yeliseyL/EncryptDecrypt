public class ConsoleMode {
    String action = "enc";
    String message = "";
    int key = 0;
    String inFile = "";
    String outFile = "";
    String alg = "shift";

    public ConsoleMode(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-mode")) {
                this.action = args[i + 1];
            } else if (args[i].equals("-key")) {
                this.key = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-data")) {
                this.message = args[i + 1];
            } else if (args[i].equals("-in")) {
                this.inFile = args[i + 1];
            } else if (args[i].equals("-out")) {
                this.outFile = args[i + 1];
            } else if (args[i].equals("-alg")) {
                this.alg = args[i + 1];
            }
        }
    }

    public void runConsoleMode() {

        if (!inFile.isEmpty() && message.isEmpty()) {
            message = ReadWrite.readFile(inFile);
        }

        if (outFile.isEmpty()) {
            System.out.println(applyAlgorithm(alg, action, message, key));
        } else {
            ReadWrite.writeFile(outFile, applyAlgorithm(alg, action, message, key));
        }
    }

    private String applyAlgorithm(String alg, String action, String message, int key) {
        Encrypter encrypter = new Encrypter();

        if (alg.equals("unicode")) {
            encrypter.setAlgorithm(new UnicodeAlgorithm());
        } else {
            encrypter.setAlgorithm(new ShiftAlgorithm());
        }

        if (action.equals("enc")) {
            return encrypter.encodeText(message, key);
        } else {
            return encrypter.decodeText(message, key);
        }
    }
}
