/*
 * 
 * Author: Darcy Zeng
 * 
 */

import java.io.File;
import java.util.*;

public class Hangman{

    private String word;
    private int numguess = 6;
    private int guessedletter = 0;
    private boolean clear  = false;
    final String LowercaseAplhabet  = "abcdefghijklmnopqrstuvwxyz";

    Hangman(String word){
        //constructor
        this.word = word;

    }

    public void printWord(char[] c ){
        //prints spaced out letters in word

        String word   = "";
        
        for(int i=0;i<c.length;i++){
            word += c[i];
            word += " ";
        }

        System.out.println("Word: "+word);
    }

    private void printWrongGuess(List<String> wrong){
        //prints the list of wrong gusses

        String temp = "";

        for(int i=0;i<wrong.size();i++){
            temp+=wrong.get(i);
            temp+= " ";
        }

        System.out.println("Guessed Letters: "+temp);
    }

    private boolean invalidInput(String letter, String correctGuessHolder, List<String> wrongGuess){
        //returns false if the player input is invalid. 
        return letter.length() > 1|| wrongGuess.contains(letter) || 
        correctGuessHolder.contains(letter) || !LowercaseAplhabet.contains(letter);
    }

    public void playGame (){

        char[] wordArr = new char[word.length()];
        Arrays.fill(wordArr, '_'); //filling the char array with "_" initially to visualize how many letters are in the word 
        List<String> wrongGuess = new ArrayList<>();
        Scanner scan  =  new Scanner(System.in);
        String correctGuessHolder = "";

        System.out.println("Enter a letter to guess! The word is " + word.length() + "characters." );

        while(numguess != 0 && !clear){ //loop until lose all guesses or correctly guess the word

            DrawHangman.draw(numguess); 
            printWord(wordArr);
            System.out.println("\nYou have " + numguess + " life left!");
            printWrongGuess(wrongGuess);
            System.out.println("Enter letter: ");
            String letter = scan.next();

            while(invalidInput(letter, correctGuessHolder, wrongGuess)){
                //keep asking the player to input a single letter until the input is valid
                if(letter.length()>1){
                    System.out.println("Enter a single letter!");
                }else if(!LowercaseAplhabet.contains(letter)){
                    System.out.println("Enter a lower case alphabet!");

                }else{
                    System.out.println("You have already guessed this letter! Guess again.");
                }
                letter = scan.next();

            }

            if(word.contains(letter)){
                //if the letter is contained in the word, replace the underscore in the char array with the correct letter
                for(int i=0;i<wordArr.length;i++){
                    if(word.charAt(i) == letter.charAt(0)){
                        wordArr[i] = letter.charAt(0);
                        guessedletter++; //increment the count when replacing the underscore with the letter 
                    }
                }

                word.replaceAll(letter, "");
                correctGuessHolder+=letter;
                System.out.println("Correct guess!");

            }else{
                //if the gusssed letter is incorrect, add it to the wronguess list and subtract a life
                wrongGuess.add(letter);
                numguess--;
            }

            if(guessedletter == word.length()){
                //if the guessedletter count == word length, it means the char array is fully filled with correct guesses. so return game clear is true
                clear = true;
            }

            System.out.println("---------------------------------------------------------"); //seperation line to view the console better

        }

        scan.close();

        if(numguess == 0){
            //if there is nol gussed life left, print player lost and print the complete hangman
            DrawHangman.draw(numguess);
            System.out.println("\nYou Lost.");
            System.out.print("The word was: "+ word);
        }else{
            //else print the player won
            printWord(wordArr);
            System.out.println(" \nYou Won!");
        }
        
    }

    public static String scanFile(){
        //scan the wordbank text file and randomly select one word 
        File file = new File("wordbank.txt");
        String randomWord = "fail";

        try{

            Scanner input = new Scanner(file);
            String line = input.nextLine();
            List<String> words = new ArrayList<>();
            while(input.hasNext()) {
                words.add(line);
                line = input.nextLine();
            }

            Random random = new Random();
            int upperbound = words.size();
            randomWord = words.get(random.nextInt(upperbound));
            System.out.println(randomWord);
            input.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return randomWord;
    }

    public static void main(String args[]){
        
        String word = scanFile();

        Hangman play = new Hangman(word);
        play.playGame();

    }
}