/************************************************************
 * @Assignment 7 - Implement a class that has logic for Hangman game : 
 * @Program : AlreadyGuessedException.java 
 * @Author: Arif Adatia 
 * @Date: May 20, 2013
 * @Description: This is an exception intended for being thrown when a new guess has already been mades
 *  @Input: None    
 * @Output: Error message
 * 
 */

public class AlreadyGuessedException extends Exception 
{	
    public AlreadyGuessedException(String message)
    {
		super(message);
    } 
}
