import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {
    public String book;
    public MyLinkedList<String> words;
    public MyLinkedList<String> wordsAndSeparators;
    public BookReader(String filename) {
        this.words = new MyLinkedList<>();
        this.wordsAndSeparators = new MyLinkedList<>();
        readBook(filename);
        parseWords();
    }
    public MyLinkedList<String> getWords() {
        return words;
    }
    public void readBook(String filename) {
        long start = System.currentTimeMillis();
        long duration = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("WarAndPeace.txt"))) {
            StringBuilder bookBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                bookBuilder.append((char) c);
            }
            book = bookBuilder.toString();
            reader.close();
            long now = System.currentTimeMillis();
            duration = now-start;
            System.out.println(bookBuilder.length() + " Character read in " +
                    duration + " milliseconds");
            //System.out.println();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void parseWords() {
        long start = System.currentTimeMillis();

        StringBuilder wordBuffer = new StringBuilder();

        for (int i = 0; i < book.length(); i++) {
            Character c = book.charAt(i);
            if((c.compareTo('A') >= 0 && c.compareTo('Z') <= 0) || (c.compareTo('a') >= 0 && c.compareTo('z') <= 0)
                    || (c.compareTo('0') >= 0 && c.compareTo('9') <= 0) || c.equals('\'') ) {
                wordBuffer.append(c);
                continue;
            } else if (!wordBuffer.isEmpty()) {
                words.addBefore(wordBuffer.toString());
                wordsAndSeparators.addBefore(wordBuffer.toString());
                wordBuffer = new StringBuilder();
            }
            wordsAndSeparators.addBefore(String.valueOf(c));
        }

        long now = System.currentTimeMillis();
        long duration = now - start;

        System.out.println("parseWords() in " + duration + " milliseconds.");
        System.out.println();
        System.out.println(words.size() + " words read from book.");
        System.out.println();
    }
}
