public class UnicodeAlgorithm implements Algorithm {

    @Override
    public String encrypt(String message, int key) {
        char[] newMessage = message.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            int letterNum = newMessage[i] + key;
            newMessage[i] = (char) letterNum;
        }
        return String.valueOf(newMessage);
    }

    @Override
    public String decrypt(String message, int key) {
        char[] newMessage = message.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            int letterNum = newMessage[i] - key;
            newMessage[i] = (char) letterNum;
        }
        return String.valueOf(newMessage);
    }
}
