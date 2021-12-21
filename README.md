# EvilHangman
This Java program allows a user to play a game of Hangman. However, rather than select a word for the user to guess, the program maintains a set of possible words that could be the word
for the user to guess. The program is initiated by the user specifiying the length of the word to guess and the number of incorrect guesses they are allowed before the game ends. The
length specified by the user initializes the set of possible words the program can use. As each guess is made, the program returns the maximum-sized set that does not meet the criteria
guessed by the user. This allows the program to identify more guesses as failed guesses while maintaining the appearance that it actually has just chosen one word to be guessed. Hence,
the "evil" algorithm that distinguishes this game from typical hangman.
