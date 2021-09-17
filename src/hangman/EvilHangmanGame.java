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

        // Create a Map to store partitions and variables to track the largest set and minimum occurrences of guess
        Map<String, Set<String>> partitions = new HashMap<>();
        int maxSize = 0;
        int minOccurrences = 100;

        // Loop through the words in the dictionary and create the partitions
        for(String word : dictionary) {
            int curOccurrences = 0;
            // Create StringBuilder variable to store partition pattern
            StringBuilder pattern = new StringBuilder();
            // Loop through each character of current word
            for(int i = 0; i < word.length(); i++) {
                // If current character matches the guess, append guess to the StringBuilder and increment occurrences
                if(word.charAt(i) == guess) {
                    pattern.append(guess);
                    curOccurrences++;
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
            // Update maxSize and minOccurrences if necessary
            if(partitions.get(key).size() > maxSize) {
                maxSize = partitions.get(key).size();
            }
            if(curOccurrences < minOccurrences) {
                minOccurrences = curOccurrences;
            }
        }
        // Keep only the largest partition(s)
        for(Iterator<Map.Entry<String, Set<String>>> itr = partitions.entrySet().iterator(); itr.hasNext();) {
            if(itr.next().getValue().size() < maxSize) {
                itr.remove();
            }
        }
        // If there are multiple partitions of the largest size, pare it down to one partition
        if(partitions.size() > 1) {
            // If there is a partition that does not include the guessed letter, choose that one
            for(String pattern : partitions.keySet()) {
                if(!pattern.contains(Character.toString(guess))) {
                    return partitions.get(pattern);
                }
            }
            // If each partition has the guessed letter, choose the one with the fewest occurrences of the letter
            for(Iterator<Map.Entry<String, Set<String>>> itr = partitions.entrySet().iterator(); itr.hasNext();) {
                int curOccurrences = 0;
                String pattern = itr.next().getKey();
                for(int i = 0; i < pattern.length(); i++) {
                    if(pattern.charAt(i) == guess) {
                        curOccurrences++;
                    }
                }
                if(curOccurrences > minOccurrences) {
                    itr.remove();
                }
            }
            // If there are multiple partitions with the minimum # of occurrences, pare it down to one partition
            if(partitions.size() > 1) {

            }

        }

    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return null;
    }
}
