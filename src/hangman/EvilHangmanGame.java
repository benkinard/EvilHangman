package hangman;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame {

    Set<String> dictionary;
    SortedSet<String> guessedLetters;

    public EvilHangmanGame() {
        dictionary = null;
        guessedLetters = null;
    }

    @Override
    public void startGame(File dictionary, int wordLength) throws IOException, EmptyDictionaryException {
        // Clear any previous dictionary and guessed letters
        if(this.dictionary != null) {
            this.dictionary.clear();
        }
        if(guessedLetters != null) {
            guessedLetters.clear();
        }
        // Initialize EvilHangmanGame's dictionary to be an empty HashSet
        this.dictionary = new HashSet<>();
        // Initialize guessedLetters to be an empty TreeSet
        guessedLetters = new TreeSet<>();

        // Read the words from the dictionary file into the EvilHangmanGame's dictionary (set of words)
        Scanner scanner = new Scanner(dictionary);
        while(scanner.hasNext()) {
            this.dictionary.add(scanner.next());
        }
        // Remove any words from EvilHangmanGame's dictionary that are longer than wordLength
        this.dictionary.removeIf(s -> s.length() > wordLength);
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        return null;
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return null;
    }
}
