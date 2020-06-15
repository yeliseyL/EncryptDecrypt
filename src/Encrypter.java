public class Encrypter {
    private Algorithm algorithm;

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String encodeText(String message, int key) {
        return this.algorithm.encrypt(message, key);
    }

    public String decodeText(String message, int key) {
        return this.algorithm.decrypt(message, key);
    }
}
