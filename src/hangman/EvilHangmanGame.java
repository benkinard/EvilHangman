package hangman;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;

public class EvilHangmanGame implements IEvilHangmanGame {

    Set<String> dictionary;
    SortedSet<char> guessedLetters;

    public EvilHangmanGame() {
        dictionary = null;
    }

    @Override
    public void startGame(File dictionary, int wordLength) throws IOException, EmptyDictionaryException {
        // Clear any previous dictionary/set of words
        if(this.dictionary != null) {
            this.dictionary.clear();
        }
        // Initialize EvilHangmanGame's dictionary to be an empty set
        this.dictionary = new HashSet<>();
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
