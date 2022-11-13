/*
 * 
 * Author: Darcy Zeng
 * 
 */

import java.util.*;

public class Hangman{

    private String word;
    private int numguess = 6;
    private int guessedletter = 0;
    private boolean clear  = false;
    final String LowercaseAplhabet  = "abcdefghijklmnopqrstuvwxyz";

    Hangman(String word){

        this.word = word;

    }

    public void printWord(char[] c ){

        String word   = "";
        
        for(int i=0;i<c.length;i++){
            word += c[i];
            word += " ";
        }

        System.out.println("Word: "+word);
    }

    private void printWrongGuess(List<String> wrong){

        String temp = "";

        for(int i=0;i<wrong.size();i++){
            temp+=wrong.get(i);
            temp+= " ";
        }

        System.out.println("Guessed Letters: "+temp);
    }

    private boolean invalidInput(String letter, String correctGuessHolder, List<String> wrongGuess){
        return letter.length() > 1|| wrongGuess.contains(letter) || 
        correctGuessHolder.contains(letter) || !LowercaseAplhabet.contains(letter);
    }

    public void playGame (){

        char[] wordArr = new char[word.length()];
        Arrays.fill(wordArr, '_');
        List<String> wrongGuess = new ArrayList<>();
        Scanner scan  =  new Scanner(System.in);
        String correctGuessHolder = "";

        System.out.println("Enter a letter to guess! The word is " + word.length() + "characters." );

        while(numguess != 0 && !clear){

            DrawHangman.draw(numguess);
            printWord(wordArr);
            System.out.println("\nYou have " + numguess + " life left!");
            printWrongGuess(wrongGuess);
            System.out.println("Enter letter: ");
            String letter = scan.next();

            while(invalidInput(letter, correctGuessHolder, wrongGuess)){

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

                for(int i=0;i<wordArr.length;i++){
                    if(word.charAt(i) == letter.charAt(0)){
                        wordArr[i] = letter.charAt(0);
                        guessedletter++;
                    }
                }

                word.replaceAll(letter, "");
                correctGuessHolder+=letter;
                System.out.println("Correct guess!");

            }else{
                wrongGuess.add(letter);
                numguess--;
            }

            if(guessedletter == word.length()){
                clear = true;
            }

            System.out.println("---------------------------------------------------------");

        }

        scan.close();

        if(numguess == 0){
            DrawHangman.draw(numguess);
            System.out.println("\nYou Lost.");
        }else{
            printWord(wordArr);
            System.out.println(" \nYou Won!");
        }
        
    }

    public static void main(String args[]){
        
        String word = "black";

        Hangman play = new Hangman(word);
        play.playGame();

    }
}