public class CaesarCipherDecoder {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final int ALPHABET_LENGTH = ALPHABET.length();

    public String decode(String encryptedText, int shift) {
        StringBuilder decodedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            if (c == ' ') {
                decodedText.append(' ');
            } else {
                int originalIndex = ALPHABET.indexOf(c);
                int newIndex = (originalIndex - shift + ALPHABET_LENGTH) % ALPHABET_LENGTH;
                decodedText.append(ALPHABET.charAt(newIndex));
            }
        }

        return decodedText.toString();
    }
}
