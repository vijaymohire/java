
/************************************************************
 * @Assignment 7 - Implement a class that has logic for Hangman game : 
 * @Program : AlreadyGuessedException.java 
 * @Author: Arif Adatia 
 * @Date: May 20, 2013
 * @Description:  This is an exception intended for being thrown when invalid input
 *  @Input: None    
 * @Output: Error message
 * 
 */

public class InvalidInputException extends Exception 
{	
    public InvalidInputException(String message)
    {
		super(message);
    } 
}

