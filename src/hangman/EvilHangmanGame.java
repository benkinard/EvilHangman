package hangman;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame {

    Set<String> dictionary;
    SortedSet<Character> guessedLetters;

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
        // If the current guess has already been guessed, throw a GuessAlreadyMadeException object
        if(guessedLetters.contains(guess)) {
            throw new GuessAlreadyMadeException();
        } else {
            guessedLetters.add(guess);
        }

        // Create a Map to store partitions
        Map<String, Set<String>> partitions = new HashMap<>();

        // Loop through the words in the dictionary and create the partitions
        for(String word : dictionary) {
            // Create StringBuilder variable to store partition pattern
            StringBuilder pattern = new StringBuilder();
            // Loop through each character of current word
            for(int i = 0; i < word.length(); i++) {
                // If current character matches the guess, append guess to the StringBuilder
                if(word.charAt(i) == guess) {
                    pattern.append(guess);
                } else { // Otherwise, append an underscore to the StringBuilder
                    pattern.append('_');
                }
            }
            String key = pattern.toString();
            if(partitions.get(key) == null) {
                Set<String> patternMatches = new HashSet<>();
                patternMatches.add(word);
                partitions.put(key, patternMatches);
            } else {
                partitions.get(key).add(word);
            }
        }


    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return null;
    }
}
