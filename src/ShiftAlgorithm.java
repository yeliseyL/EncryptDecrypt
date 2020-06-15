public class ShiftAlgorithm implements Algorithm {

    private final char[] ALPHABET_ENGLISH = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private final char[] ALPHABET_RUSSIAN = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л',
            'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш',
            'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    };

    @Override
    public String encrypt(String message, int key) {
        char[] newAlphabetEng = getShiftedAlphabet("enc", ALPHABET_ENGLISH, key);
        char[] newAlphabetRus = getShiftedAlphabet("enc", ALPHABET_RUSSIAN, key);
        return shiftAlgorithm(message, newAlphabetEng, newAlphabetRus);
    }

    @Override
    public String decrypt(String message, int key) {
        char[] newAlphabetEng = getShiftedAlphabet("dec", ALPHABET_ENGLISH, key);
        char[] newAlphabetRus = getShiftedAlphabet("dec", ALPHABET_RUSSIAN, key);
        return shiftAlgorithm(message, newAlphabetEng, newAlphabetRus);
    }

    private String shiftAlgorithm(String message, char[] newAlphabetEng, char[] newAlphabetRus) {
        char[] newMessage = message.toCharArray();
        for (int k = 0; k < newMessage.length - 1; k++) {
            if (Character.isLetter(newMessage[k])) {
                if (String.valueOf(ALPHABET_RUSSIAN).contains(Character.toString(Character.toLowerCase(newMessage[k])))){
                    int index = getIndex(ALPHABET_RUSSIAN, newMessage[k]);
                    if (!Character.isUpperCase(newMessage[k])) {
                        newMessage[k] = newAlphabetRus[index];
                    } else {
                        newMessage[k] = Character.toUpperCase(newAlphabetRus[index]);
                    }
                } else if (String.valueOf(ALPHABET_ENGLISH).contains(Character.toString(Character.toLowerCase(newMessage[k])))) {
                    int index = getIndex(ALPHABET_ENGLISH, newMessage[k]);
                    if (!Character.isUpperCase(newMessage[k])) {
                        newMessage[k] = newAlphabetEng[index];
                    } else {
                        newMessage[k] = Character.toUpperCase(newAlphabetEng[index]);
                    }
                }
            }
        }
        return String.valueOf(newMessage);
    }

    private int getIndex(char[] alphabet, char letter) {
        char newLetter = Character.toLowerCase(letter);
        int index = 0;
        while (alphabet[index] != newLetter) {
            index++;
        }
        return index;
    }

    private char[] getShiftedAlphabet(String action, char[] alphabet, int key) {
        char[] newAlphabet = alphabet.clone();
        for (int i = 0; i < key; i++) {
            int j;
            char temp;
            if (action.equals("enc")) {
                temp = newAlphabet[0];
                for (j = 0; j < newAlphabet.length - 1; j++) {
                    newAlphabet[j] = newAlphabet[j + 1];
                }
                newAlphabet[j] = temp;
            } else if (action.equals("dec")) {
                temp = newAlphabet[newAlphabet.length - 1];
                for (j = newAlphabet.length - 1; j > 0; j--) {
                    newAlphabet[j] = newAlphabet[j - 1];
                }
                newAlphabet[j] = temp;
            }
        }
        return newAlphabet;
    }
}
