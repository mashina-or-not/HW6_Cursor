import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class StringSort {
    static String string = "1Test, 2String, 3Test - 4String";
    static String file = "Tolstoi_Lev.txt";

    static String text;
    static Map<String, Integer> wordsEntry;

    static {
        text = readerURL();
        wordsEntry = wordsEntry();
    }

    public static Map<Character, Integer> numLetter() {
        return string.toLowerCase().chars()
                .filter(Character::isLetter)
                .collect(HashMap::new, (x, y) -> x.put((char) y, x.containsKey((char) y) ? (x.get((char) y) + 1) : 1), HashMap::putAll);
    }

    public static long numAllLetter() {
        return string.chars().filter(Character::isLetter).count();
    }

    public static long numSpace() {
        return string.chars().filter(Character::isSpaceChar).count();
    }

    public static long numDigits() {
        return string.chars().filter(Character::isDigit).count();
    }

    public static long numPunctuation() {
        return string.chars().filter(x -> !(Character.isLetter(x) || Character.isDigit(x) || Character.isSpaceChar(x))).count();
    }

    private static Map<String, Integer> wordsEntry() {
        String strFilter = text.toLowerCase().chars()
                .filter(x -> Character.isLetter(x) || Character.isWhitespace(x))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return Arrays.stream(strFilter.split("[ \n]"))
                .map(s -> s = s.trim())
                .filter(s -> s.length() > 2)
                .collect(HashMap::new, (k, v) -> k.put(v, k.containsKey(v) ? (k.get(v) + 1) : 1), HashMap::putAll);
    }

    public static String shortestWord() {
        Map.Entry<String, Integer> word = wordsEntry.entrySet().stream()
                .min(Comparator.comparingInt(x -> x.getKey().length()))
                .orElseThrow(NoSuchElementException::new);
        return word.getKey() + ":" + word.getValue();
    }

    public static String longestWord() {
        Map.Entry<String, Integer> word = wordsEntry.entrySet().stream()
                .max(Comparator.comparingInt(x -> x.getKey().length()))
                .orElseThrow(NoSuchElementException::new);
        return word.getKey() + ":" + word.getValue();
    }

    private static String readerURL() {
        try (InputStream inputStream = new FileInputStream(file)) {
            if (inputStream.available() > 0) {
                byte[] bytes = new byte[inputStream.available()];
                int readBytesCount = inputStream.read(bytes);
                return new String(bytes, 0, readBytesCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}