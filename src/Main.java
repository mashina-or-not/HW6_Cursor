public class Main extends StringSort {
    public static void main(String[] args) {
        System.out.println("Number of letter: " + numLetter());
        System.out.println("Numbers of all letters: " + numAllLetter());
        System.out.println("Number of space: " + numSpace());
        System.out.println("Number of digits: " + numDigits());
        System.out.println("Number of punctuation: " + numPunctuation());
        System.out.println("Shortest word - " + shortestWord());
        System.out.println("Longest word - " + longestWord());
    }
}
