package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.SortedSet;

public class EvilHangman {

    public static void main(String[] args) {
        EvilHangmanGame ehGame = new EvilHangmanGame();

        try {
            // Read the dictionary file into the instance of EvilHangmanGame
            File dictionary = new File(args[0]);
            int wordLength = Integer.parseInt(args[1]);
            ehGame.startGame(dictionary, wordLength);
        } catch (IOException ex) {
            // Add code here
        } catch (EmptyDictionaryException ex) {
            // Add code here
        }

        Scanner input = new Scanner(System.in);
        int numGuesses = Integer.parseInt(args[2]);
        while (numGuesses > 0) {
            // Print # of guesses remaining
            System.out.println("You have " + numGuesses + " guesses left");

            // Print alphabetized list of used guesses
            System.out.print("Used letters:");
            for(Character letter : ehGame.getGuessedLetters()) {
                System.out.print(" " + letter);
            }
            System.out.println("");

            // Show the current word pattern
            System.out.println("Word: " + ehGame.getPattern());

            // Prompt for guess
            System.out.print("Enter guess: ");
            String guess = input.next();

            // Run makeGuess
            guess = guess.toLowerCase();
            try {
                ehGame.setDictionary(ehGame.makeGuess(guess.charAt(0)));
            } catch (GuessAlreadyMadeException ex) {
                // Add Code Here
            }

            // Print whether the guess is in the word
            int guessOccurrences = ehGame.getNumOccurrences();
            if(guessOccurrences == 0) {
                System.out.print("Sorry, there are no " + guess.charAt(0) + "'s");
                numGuesses--;
            } else {
                System.out.print("Yes, there is " + guessOccurrences + " " + guess.charAt(0));
            }
            System.out.println("\n");
        }

    }

}
