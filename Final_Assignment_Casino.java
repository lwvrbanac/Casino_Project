// The "Final_Assignment_Casino" class.
/*Name: Luke Vrbanac
Date: 07/04/2021
Program Purpose: To make an online casion with the option to play either Craps or Blackjack*/
import java.awt.*;
import hsa.Console;

public class Final_Assignment_Casino
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console (25,115);
	
	// Place your program here.  'c' is the output console
	//declare the variable for loop and balance
	char proceed = 'y';
	int mainBalance = 1000;
	
	//loops the program if the user wished to switch games
	while(proceed == 'y'||proceed == 'Y')
	{
	    //clears all previous entries
	    c.clear();
	    //if you run out of money 
	    if(mainBalance <= 0)
	    {
		c.println("You have lost all of your money, get good");
		//code for a delay
		try
		{
		    Thread.sleep(1000);//timed in miliseconds
		}catch (InterruptedException e)//catches the exception
		{
		}
		//closes the program
		System.exit(0);
	    }
	    //sets the text colour and the object colour
	    c.setTextColor(Color.blue);
	    c.setTextBackgroundColor(Color.green);
	
	    //prints the background and the title
	    c.setColor(Color.green);
	    c.fillRect(0,0,1000,1000);
	    c.setColor(Color.white);
	    c.setFont(new Font("Seriff",Font.PLAIN,75));
	    c.drawString("Casino",200,250);
	    
	    //prints the player balance
	    c.println("Welcome to the Casino\nYour current balance for the casino is: $" + mainBalance);
	    String choice = "";//allows the next loop to begin
	
	    while(!choice.equalsIgnoreCase("craps")&&!choice.equalsIgnoreCase("exit"))//loops when an invalid input is entered
	    {
		//clears previous entries and reprints the title
		c.clear();
		c.setColor(Color.green);
		c.fillRect(0,0,1000,1000);
		c.setColor(Color.white);
		c.setFont(new Font("Seriff",Font.PLAIN,75));
		c.drawString("Casino",200,250);
		c.println("You have the option of playing either Craps or Blackjack\nPlease enter either 'Craps', 'Blackjack', or 'exit'");//prompt for user input
		choice = c.readString();//user input
	
		if(choice.equalsIgnoreCase("craps"))//if craps is entered
		{
		    mainBalance = craps(mainBalance);//player is sent to the craps game
		}
		else if(choice.equalsIgnoreCase("blackjack"))//if blackjack is entered
		{
		    mainBalance = blackjack(mainBalance);//player is sent to the blackjack game
		}
	    }
	    //clears previous entries and reprints the title
	    c.clear();
	    c.setColor(Color.green);
	    c.fillRect(0,0,1000,1000);
	    c.setColor(Color.white);
	    c.setFont(new Font("Seriff",Font.PLAIN,75));
	    c.drawString("Casino",200,250);
	    
	    //if you run out of money 
	    if(mainBalance <= 0)
	    {
		c.println("You have lost all of your money, get good");
		//code for a delay
		try
		{
		    Thread.sleep(1000);//timed in miliseconds
		}catch (InterruptedException e)//catches the exception
		{
		}
		//closes the program
		System.exit(0);
	    }
	    
	    proceed = 'j';//changes variable to allow following code to happen
	    while(proceed != 'y'&&proceed != 'Y'&&proceed != 'n'&&proceed != 'N')//loops if input is invalid
	    {
		c.println("Your current balance is: $" + mainBalance);//prints user balance
		c.println("Would you like to play another game in the casino? (Y/N)");//prompt for user input
		proceed = c.readChar();//user input
		c.println("invalid Entry, please enter a valid value");//invisible to user if they enter a valid answer
	    }

	}
	System.exit(0);//closes the program
	
    } // main method
    
    public static int craps(int balance)//craps game in a method
    {
	char newGame = 'y';//allows game to start and loop
	while (newGame == 'y' || newGame == 'Y')//loop for the game
	{
	    //clears previous entries and prints background
	    c.clear ();
	    c.setTextBackgroundColor (Color.green);
	    c.setColor (Color.green);
	    c.fillRect (0, 0, 1000, 1000);

	    //prints the title
	    c.setFont (new Font ("Seriff", Font.BOLD, 40));
	    c.setColor (Color.white);
	    c.drawString ("Craps", 250, 200);
	    
	    //sets the text colour and prints the input prompt
	    c.setTextColor (Color.blue);
	    c.println ("Would you like to bet pass line or don't pass line:\nPlease enter either 'pass' or 'don't'");
	    String passBet = c.readString ();//user input

	    if (!passBet.equalsIgnoreCase ("pass") && !passBet.equalsIgnoreCase ("don't"))//if the user enters an invalid input
	    {
		while (!passBet.equalsIgnoreCase ("pass") && !passBet.equalsIgnoreCase ("don't"))//loops while invalid input is entered
		{
		    c.println ("Invalid choice\nPlease enter a valid choice:");//input prompt
		    passBet = c.readString ();//user input
		}
	    }
	    //prints balance, rules, and input prompt
	    c.println ("Your current balance is: $" + balance);
	    c.println ("Table minimum is $1 and max is infinite");
	    c.println ("All amounts must be integers");
	    c.println ("Please enter how much you would like to bet:");
	    int passBetAmount = c.readInt ();//user input

	    if (passBetAmount > balance || passBetAmount < 1)//if the user does not have enough money
	    {
		while (passBetAmount > balance || passBetAmount < 1)
		{
		    c.println ("Insufficient Balance or amount is below table minimum\nPlease enter a valid amount:");//error message and input prompt
		    passBetAmount = c.readInt ();//user input
		}
	    }
	    balance -= passBetAmount;//subtracts the bet from the balance

	    int rollNum = 0;//counts the number of rolls
	    int[] dice = new int [2];//stores the value of the dice for calculations
	    int point = 0;//stores the value of the point
	    boolean end = false;//stores wheter the game has ended or not
	    int placeBet = 0;//stores the initial place bet space
	    int[] placeBetAmount = new int [6];//stores the place bets
	    String nextBet = "";//stores the input for if the user wishes to make a place bet
	    boolean[] placeBetPlace = new boolean [6];//stores whether there is a place bet on a specific spot
	    
	    //loops as long as the game lasts
	    while (end == false)
	    {

		for (int rollLoop = 0 ; rollLoop < 10 ; rollLoop++) //loop for animation
		{
		    c.clear (); //clears previous entries

		    //finds the dice values
		    dice [0] = (int) (Math.random () * 6) + 1;
		    dice [1] = (int) (Math.random () * 6) + 1;

		    //prints the background
		    c.setColor (Color.green);
		    c.fillRect (0, 0, 1000, 1000);

		    //prints the dice backgrounds
		    c.setColor (Color.white);
		    c.fillRoundRect (100, 100, 100, 100, 20, 20);
		    c.fillRoundRect (400, 100, 100, 100, 20, 20);

		    //prints title and all relevant information
		    c.setFont (new Font ("Seriff", Font.BOLD, 40));
		    c.drawString ("Craps", 250, 50);
		    c.setFont (new Font ("Seriff", Font.PLAIN, 25));
		    c.drawString ("Balance: $" + balance, 0, 400);
		    c.drawString ("Pass Line: " + passBet, 0, 325);
		    c.drawString ("Pass Line Bet: $" + passBetAmount, 0, 350);
		    c.drawString ("Point: " + point, 0, 375);
		    c.drawString ("place bet on 4: $" + placeBetAmount[0], 300, 325);
		    c.drawString ("place bet on 5: $" + placeBetAmount[1], 300, 350);
		    c.drawString ("place bet on 6: $" + placeBetAmount[2], 300, 375);
		    c.drawString ("place bet on 8: $" + placeBetAmount[3], 300, 400);
		    c.drawString ("place bet on 9: $" + placeBetAmount[4], 300, 425);
		    c.drawString ("place bet on 10: $" + placeBetAmount[5], 300, 450);

		    //prints the outlines for the dice
		    c.setColor (Color.black);
		    c.drawRoundRect (100, 100, 100, 100, 20, 20);
		    c.drawRoundRect (400, 100, 100, 100, 20, 20);

		    if (dice [0] == 1) //if the die rolls 1
		    {
			//prints die with one dot
			c.fillArc (138, 138, 25, 25, 0, 360); //middle
		    }
		    else if (dice [0] == 2) //if the die rolls 2
		    {
			//prints die with two dots
			c.fillArc (110, 110, 25, 25, 0, 360); //top left
			c.fillArc (165, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [0] == 3) //if the die rolls three
		    {
			//prints die with three dots
			c.fillArc (110, 110, 25, 25, 0, 360); //top left
			c.fillArc (138, 138, 25, 25, 0, 360); //middle
			c.fillArc (165, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [0] == 4) //if the die rolls four
		    {
			//prints die with four dots
			c.fillArc (110, 110, 25, 25, 0, 360); //top left
			c.fillArc (110, 167, 25, 25, 0, 360); //bottom left
			c.fillArc (165, 110, 25, 25, 0, 360); //top right
			c.fillArc (165, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [0] == 5) //if the die rolls five
		    {
			//prints die with five dots
			c.fillArc (110, 110, 25, 25, 0, 360); //top left
			c.fillArc (110, 167, 25, 25, 0, 360); //bottom left
			c.fillArc (138, 138, 25, 25, 0, 360); //middle
			c.fillArc (165, 110, 25, 25, 0, 360); //top right
			c.fillArc (165, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [0] == 6) //if the die rolls six
		    {
			//prints die with six dots
			c.fillArc (110, 110, 25, 25, 0, 360); //top left
			c.fillArc (110, 138, 25, 25, 0, 360); //left mid
			c.fillArc (110, 167, 25, 25, 0, 360); //bottom left
			c.fillArc (165, 110, 25, 25, 0, 360); //top right
			c.fillArc (165, 138, 25, 25, 0, 360); //right mid
			c.fillArc (165, 167, 25, 25, 0, 360); //bottom right
		    }

		    if (dice [1] == 1) //if the die rolls 1
		    {
			c.fillArc (438, 138, 25, 25, 0, 360); //middle
		    }
		    else if (dice [1] == 2) //if the die rolls 2
		    {
			c.fillArc (410, 110, 25, 25, 0, 360); //top left
			c.fillArc (465, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [1] == 3) //if the die rolls 3
		    {
			c.fillArc (410, 110, 25, 25, 0, 360); //top left
			c.fillArc (438, 138, 25, 25, 0, 360); //middle
			c.fillArc (465, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [1] == 4) //if the die rolls 4
		    {
			c.fillArc (410, 110, 25, 25, 0, 360); //top left
			c.fillArc (410, 167, 25, 25, 0, 360); //bottom left
			c.fillArc (465, 110, 25, 25, 0, 360); //top right
			c.fillArc (465, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [1] == 5) //if the die rolls 5
		    {
			c.fillArc (410, 110, 25, 25, 0, 360); //top left
			c.fillArc (410, 167, 25, 25, 0, 360); //bottom left
			c.fillArc (438, 138, 25, 25, 0, 360); //middle
			c.fillArc (465, 110, 25, 25, 0, 360); //top right
			c.fillArc (465, 167, 25, 25, 0, 360); //bottom right
		    }
		    else if (dice [1] == 6) //if the die rolls 6
		    {
			c.fillArc (410, 110, 25, 25, 0, 360); //top left
			c.fillArc (410, 138, 25, 25, 0, 360); //left mid
			c.fillArc (410, 167, 25, 25, 0, 360); //bottom left
			c.fillArc (465, 110, 25, 25, 0, 360); //top right
			c.fillArc (465, 138, 25, 25, 0, 360); //right mid
			c.fillArc (465, 167, 25, 25, 0, 360); //bottom right
		    }

		    //code for a delay
		    try
		    {
			Thread.sleep (175); //timed in miliseconds
		    }
		    catch (InterruptedException e)  //catches the exception
		    {
		    }

		}
		
		rollNum++;//inrcreases the count for haw many rolls have happened
		int diceSum = dice [0] + dice [1];//calculates the sum of the dice
		int reduce;//declares a variable for reducing the bets
		int placeBetPayout;//calculates the payout for the place bet
		String placeBetReduce;//variavle for reducing place bets
		boolean dontPassBet = true;//checks if the is a don't pass bet for calculations after the first roll
		
		//prints what the dice sum was
		c.setColor (Color.black);
		c.drawString("The Roll was: " + diceSum, 200, 250);
		
		//if the roll was the first
		if (rollNum == 1)
		{
		    //if the player is playing the pass line
		    if (passBet.equalsIgnoreCase ("pass"))
		    {
			if (diceSum == 7 || diceSum == 11)//if the player wins on the initial roll
			{
			    //calculates and prints the winnings as well as adding them to the balance
			    passBetAmount *= 2;
			    c.drawString (" You win $" + passBetAmount, 200, 200);
			    balance += passBetAmount;
			    end = true;//ends the game loop
			}
			else if (diceSum == 2 || diceSum == 3 || diceSum == 12)
			{
			    //prints the money lost
			    c.drawString (" You Lose $" + passBetAmount, 200, 200);
			    end = true;//ends the game loop
			}
			else
			{
			    //prints the point and assigns it to a variable
			    point = diceSum;
			    c.drawString (" The point is: " + point, 200, 200);
			    
			    //loops if an invalid entry is enteres
			    while (!nextBet.equalsIgnoreCase ("yes") && !nextBet.equalsIgnoreCase ("no"))
			    {
				//prints rules and prompt for input
				c.println ("Would you like to make a place bet?");
				c.println ("You can bet on the numbers 4, 5, 6, 8, 9, 10");
				c.println ("4 and 10 pay 9-to-5, 5 anf 9 pay 7-to-5, and 6 and 8 pay 7-to-6");
				c.println ("Please enter either yes or no");
				nextBet = c.readString ();//user input

				if (nextBet.equalsIgnoreCase ("yes"))//if the user inputs yes
				{
				    //loops for invalid entry
				    while (placeBet != 4 && placeBet != 5 && placeBet != 6 && placeBet != 8 && placeBet != 9 && placeBet != 10)
				    {
					//prompt for input
					c.println ("Please enter the place you would like to bet on:");
					placeBet = c.readInt ();//user input
				    }

				    if (placeBet == 4)//if the user inputs 4
				    {
					placeBetPlace [0] = true;//sets the array to true for the space that concerns 4
				    }
				    else if (placeBet == 5)//if the user inputs 5
				    {
					placeBetPlace [1] = true;//sets the array to true for the space that concerns 5
				    }
				    else if (placeBet == 6)//if the user inputs 6
				    {
					placeBetPlace [2] = true;//sets the array to true for the space that concerns 6
				    }
				    else if (placeBet == 8)//if the user inputs 8
				    {
					placeBetPlace [3] = true;//sets the array to true for the space that concerns 8
				    }
				    else if (placeBet == 9)//if the user inputs 9
				    {
					placeBetPlace [4] = true;//sets the array to true for the space that concerns 9
				    }
				    else if (placeBet == 10)//if the user inputs 10
				    {
					placeBetPlace [5] = true;//sets the array to true for the space that concerns 10
				    }

				    if (placeBet < 7)//changes the place bet value for input into an array
				    {
					placeBet -= 4;//lowers the value by 4
				    }
				    else//changes the place bet value for input into an array
				    {
					placeBet -= 5;//lowers the value by 5
				    }

				    c.println ("Please enter how much you would like to bet on the selected space(table min and max apply):");//prompt for input
				    placeBetAmount [placeBet] = c.readInt ();//user input

				    if (placeBetAmount [placeBet] > balance)//if the user enters value too high
				    {
					while (placeBetAmount [placeBet] > balance)//loops for continued bad values
					{
					    //gets new value
					    c.println ("Insufficient Balance \nPlease enter how much you would like to bet:");
					    placeBetAmount [placeBet] = c.readInt ();
					}
				    }
				    else if (placeBetAmount [placeBet] < 1)//if the input is too low in value
				    {
					while (placeBetAmount [placeBet] < 1)//loops for continued bad values
					{
					    //gets new value
					    c.println ("Amount is below table minimum\nPlease enter how much you would like to bet:");
					    placeBetAmount [placeBet] = c.readInt ();
					}

				    }
				}
				else if (nextBet.equalsIgnoreCase ("no"))//if the player does not wish to make a bet
				{
				}
				else
				{
				    c.println ("Inalid entry, Please enter a valid answer");//if the entry is invalid
				}
			    }
			}
		    }
		    //if the player plays the don't pass line
		    else if (passBet.equalsIgnoreCase ("don't"))
		    {
			if (diceSum == 7 || diceSum == 11)//if the player loses instantly
			{
			    //prints loss and ends game
			    c.drawString (" You Lose $" + passBetAmount, 200, 200);
			    end = true;
			}
			else if (diceSum == 2 || diceSum == 3)//if player wins instantly
			{
			    //calculates, prints, and stores winnings
			    passBetAmount *= 2;
			    c.drawString (" You win $" + passBetAmount, 200, 200);
			    end = true;//ends game
			    balance += passBetAmount;
			}
			else if (diceSum == 12)//player ties instantly
			{
			    //returns bet to balance and prints tie
			    c.drawString (" You Tie", 200, 200);
			    end = true;//ends game
			    balance += passBetAmount;
			}
			else
			{
			    //sets the point
			    point = diceSum;
			    c.drawString (" The point is: " + point, 200, 200);
			}
			if (end != true)//if the game has not ended
			{
			    while (!nextBet.equalsIgnoreCase ("yes") && !nextBet.equalsIgnoreCase ("no"))//loops on invalid input
			    {
				//prints rules and input prompt
				c.println ("Would you like to make a place bet?");
				c.println ("You can bet on the numbers 4, 5, 6, 8, 9, 10");
				c.println ("4 and 10 pay 9-to-5, 5 anf 9 pay 7-to-5, and 6 and 8 pay 7-to-6");
				c.println ("Please enter either yes or no");
				nextBet = c.readString ();//input

				if (nextBet.equalsIgnoreCase ("yes"))//if the player makes a bet
				{
				    while (placeBet != 4 && placeBet != 5 && placeBet != 6 && placeBet != 8 && placeBet != 9 && placeBet != 10)//loops if the value is invalid
				    {
					//prompt for input and input
					c.println ("Please enter the place you would like to bet on:");
					placeBet = c.readInt ();
				    }
				    
				    //if a place is selected that space in the array is set to true
				    if (placeBet == 4)
				    {
					placeBetPlace [0] = true;
				    }
				    else if (placeBet == 5)
				    {
					placeBetPlace [1] = true;
				    }
				    else if (placeBet == 6)
				    {
					placeBetPlace [2] = true;
				    }
				    else if (placeBet == 8)
				    {
					placeBetPlace [3] = true;
				    }
				    else if (placeBet == 9)
				    {
					placeBetPlace [4] = true;
				    }
				    else if (placeBet == 10)
				    {
					placeBetPlace [5] = true;
				    }

				    if (placeBet < 7)//lowers the value by 4 for future inputs
				    {
					placeBet -= 4;
				    }
				    else//lowers the value by 5 for future inputs
				    {
					placeBet -= 5;
				    }
				    
				    //prompt for imput and input as well as subtraction of input from the balance
				    c.println ("Please enter how much you would like to bet on the selected space:");
				    placeBetAmount [placeBet] = c.readInt ();
				    balance -= placeBetAmount [placeBet];
				}
				else if (nextBet.equalsIgnoreCase ("no"))//if the user inputs no
				{
				}
				else//if an invalid input is entered
				{
				    c.println ("Inalid entry, Please enter a valid answer");
				}
			    }
			    
			    nextBet = "";//allows the next loop to begin
			    if(dontPassBet == true)//if the don't pass bet is here
			    {
				//loops during invalid input
				while (!nextBet.equalsIgnoreCase ("remove") && !nextBet.equalsIgnoreCase ("no") && !nextBet.equalsIgnoreCase ("reduce"))
				{
				    //prompt for input and input
				    c.println ("would you like to reduce or remove your don't pass bet\nPlease enter either 'no', 'remove', or 'reduce'\nIf you enter a number greater than your bet or a negative number your bet will be reduced to $1 by default");
				    nextBet = c.readString ();
				    //if the player wishes to reduce the don't pass bet
				    if (nextBet.equalsIgnoreCase ("reduce"))
				    {
					//prompt for input and input
					c.println ("Please enter how much you would like to reduce it by:\nIf you enter a number greater than your bet or a negative number your bet will be reduced to $1 by default");
					reduce = c.readInt ();
					//if the input is greater than a valid number
					if (reduce > passBetAmount)
					{
					    //bet is reduced to $1 by default
					    balance = balance + passBetAmount - 1;
					    passBetAmount = 1;
					}
					else if (reduce <= 0)//if the reductiol is less than zero
					{
					    //bet is reduced to $1 by default
					    balance = balance + passBetAmount - 1;
					    passBetAmount = 1;
					}
					else//if a valid number is entered
					{
					    //bet is reduced
					    balance += reduce;
					    passBetAmount -= reduce;
					}
				    }
				    else if (nextBet.equalsIgnoreCase ("no"))//if the user doed not wish to remove or reduce the bet
				    {
				    }
				    else if (nextBet.equalsIgnoreCase ("remove"))//if the user wishes to remove the bet
				    {
					//removes the bet and ssets the bet to false
					balance += passBetAmount;
					passBetAmount = 0;
					dontPassBet = false;
				    }
				    else//invalid input is entered
				    {
					c.println ("Inalid entry, Please enter a valid answer");
				    }
				}
			    }
			}
		    }
		}
		//for any roll after the first
		else
		{
		    //if the player is playing the pass line
		    if (passBet.equals ("pass"))
		    {
			if (diceSum == point)//if the dice roll the point, the player wins
			{
			    end = true;//ends the game
			    //calculates the winnings, displays and stores them
			    passBetAmount *= 2;
			    c.drawString (" You win $" + passBetAmount, 200, 200);
			    balance += passBetAmount;
			}
			else if (diceSum == 7)//if the dice roll a seven, player loses
			{
			    end = true;//game ends
			    c.drawString (" You Lose $" + passBetAmount, 200, 200);//amount lost is displayed
			}
			else//nothing of importance is rolled
			{
			    c.drawString (" Roll again", 200, 200);
			}
		    }
		    //if the player is playing the don't pass line
		    else if (passBet.equals ("don't"))
		    {
			if (diceSum == point)//if the dice roll the point, the player loses
			{
			    end = true;//game ends
			    c.drawString (" You Lose $" + passBetAmount, 200, 200);//displays thet the player has lost
			}
			else if (diceSum == 7)//if the dice roll a seven, the player wins
			{
			    end = true;//ends the game
			    //calculates the winnings, displays and stores them
			    passBetAmount *= 2;
			    c.drawString (" You win $" + passBetAmount, 200, 200);
			    balance += passBetAmount;
			}
			else//if nothing of importance is rolled
			{
			    //displays roll again
			    c.drawString (" Roll again", 200, 200);
			    nextBet = "";//set so the next loop can happen
			    if(dontPassBet == true)//if the don't pass bet is still active
			    {
				while (!nextBet.equalsIgnoreCase ("remove") && !nextBet.equalsIgnoreCase ("no") && !nextBet.equalsIgnoreCase ("reduce"))//loops on invalid input
				{
				    //prompr for input and input
				    c.println ("would you like to reduce or remove your don't pass bet\nPlease enter either 'no', 'remove', or 'reduce'\nIf you enter a number greater than your bet or a negative number your bet will be reduced to $1 by default");
				    nextBet = c.readString ();
				    if (nextBet.equalsIgnoreCase ("reduce"))//if the player wishes to reduce the bet
				    {
					//prompt for imput and input
					c.println ("Please enter how much you would like to reduce it by:\nIf you enter a number greater than your bet or a negative number your bet will be reduced to $1 by default");
					reduce = c.readInt ();
					if (reduce > passBetAmount)//if the input is too high
					{
					    //reduced to $1 by default
					    balance = balance + passBetAmount - 1;
					    passBetAmount = 1;
					}
					else if (reduce <= 0)//if the input is too low
					{
					    //reduced to $1 by default
					    balance = balance + passBetAmount - 1;
					    passBetAmount = 1;
					}
					else//if a valid reduction is input
					{
					    //reduces the bet
					    balance += reduce;
					    passBetAmount -= reduce;
					}
				    }
				    else if (nextBet.equalsIgnoreCase ("no"))//if the player does not wish to change the bet
				    {
				    }
				    else if (nextBet.equalsIgnoreCase ("remove"))//if the player wished to remove the bet
				    {
					//removes the bet and sets the don't to false
					balance += passBetAmount;
					passBetAmount = 0;
					dontPassBet = false;
				    }
				    else//invalid input
				    {
					c.println ("Inalid entry, Please enter a valid answer");
				    }
				}
			    }
			}
		    }

		    if (end == false)//if the game has not ended
		    {

			for (int placeBetLoop = 0 ; placeBetLoop <= 5 ; placeBetLoop++)//loops for each bet
			{
			    //reduces the bet for calculations
			    if (placeBetLoop < 3)
			    {
				placeBet = placeBetLoop + 4;
			    }
			    else
			    {
				placeBet = placeBetLoop + 5;
			    }

			    placeBetReduce = "";//set for the loop

			    if (placeBetPlace [placeBetLoop] == true)//if the place bet has been made
			    {


				if (placeBet == diceSum)//if the place bet wins
				{
				    //displays the bet that won and stores the winnings for all
				    if (placeBet == 4)
				    {
					placeBetPayout = placeBetAmount [placeBetLoop] * 9 / 5;
					balance += placeBetPayout;
					c.println ("You won your place bet on 4");
				    }
				    else if (placeBet == 10)
				    {
					placeBetPayout = placeBetAmount [placeBetLoop] * 9 / 5;
					balance += placeBetPayout;
					c.println ("You won your place bet on 10");
				    }
				    else if (placeBet == 5)
				    {
					placeBetPayout = placeBetAmount [placeBetLoop] * 7 / 5;
					balance += placeBetPayout;
					c.println ("You won your place bet on 5");
				    }
				    else if (placeBet == 9)
				    {
					placeBetPayout = placeBetAmount [placeBetLoop] * 7 / 5;
					balance += placeBetPayout;
					c.println ("You won your place bet on 9");
				    }
				    else if (placeBet == 6)
				    {
					placeBetPayout = placeBetAmount [placeBetLoop] * 7 / 6;
					balance += placeBetPayout;
					c.println ("You won your place bet on 6");
				    }
				    else if (placeBet == 8)
				    {
					placeBetPayout = placeBetAmount [placeBetLoop] * 7 / 6;
					balance += placeBetPayout;
					c.println ("You won your place bet on 8");
				    }
				}
				//loops while input is invalid
				while (!placeBetReduce.equalsIgnoreCase ("remove") && !placeBetReduce.equalsIgnoreCase ("no") && !placeBetReduce.equalsIgnoreCase ("reduce"))
				{
				    //balance displaye, prompt for input and input
				    c.println ("Would you like to remove or reduce your place bet on " + placeBet);
				    c.println ("Please enter either 'no', 'remove', or 'reduce'");
				    placeBetReduce = c.readString ();
				}

				if (placeBetReduce.equalsIgnoreCase ("remove"))//if the user wishes to remove a bet
				{
				    //bet is set to false and bet is returned to balance
				    placeBetPlace [placeBetLoop] = false;
				    balance += placeBetAmount [placeBetLoop];
				    placeBetAmount [placeBetLoop] = 0;
				}
				else if (placeBetReduce.equalsIgnoreCase ("reduce"))//if the balance is reduced
				{
				    //prompt for input and input
				    c.println ("Please enter how much you would like to reduce it by:");
				    reduce = c.readInt ();
				    
				    //if the reduction input is too high
				    if (reduce > placeBetAmount [placeBetLoop])
				    {
					//set to $1 by default
					balance = balance + placeBetAmount [placeBetLoop] - 1;
					placeBetAmount [placeBetLoop] = 1;
				    }
				    //if the reduction input is too low
				    else if (reduce <= 0)
				    {
					//set to $1 by default
					balance = balance + placeBetAmount [placeBetLoop] - 1;
					placeBetAmount [placeBetLoop] = 1;
				    }
				    else//valid input is entered
				    {
					//enters the reduction
					balance += reduce;
					placeBetAmount [placeBetLoop] -= reduce;
				    }
				}
			    }
			    //if there is no bet on a space
			    else if (placeBetPlace [placeBetLoop] == false)
			    {
				//prompt for input and input
				c.println("Your Current Balance is : $" + balance);
				c.println ("Would you like to make a place bet on the place: " + placeBet + "\nPlease enter an integer if you wish to, otherwise enter any integer below 1");
				placeBetAmount [placeBetLoop] = c.readInt ();
				if (placeBetAmount [placeBetLoop] > 0)//if the number is input than a bet is made
				{
				    balance -= placeBetAmount[placeBetLoop];
				    placeBetPlace [placeBetLoop] = true;
				}
				else//if the input is invalid there is no bet made
				{
				    placeBetAmount [placeBetLoop] = 0;
				}
			    }
			}
		    }
		}
		//code for a delay
		try
		{
		    Thread.sleep (1000); //timed in miliseconds
		}
		catch (InterruptedException e)  //catches the exception
		{
		}

	    }
	    newGame = 'j';//set for the loop
	    while (newGame != 'y' && newGame != 'Y' && newGame != 'n' && newGame != 'N')//loops for invalid input
	    {
		//reprints background
		c.setColor (Color.green);
		c.fillRect (0, 0, 1000, 1000);
		c.setFont (new Font ("Seriff", Font.BOLD, 40));
		c.setColor (Color.white);
		c.drawString ("Craps", 250, 200);
		//prints balance, input prompt and gets input
		c.println ("Your current balance is: $" + balance);
		c.println ("Would you like to play craps again? (Y/N)");
		newGame = c.readChar ();
	    }
	    if(balance == 0)//if the user is out of money
	    {
		//prints message and sends user back to title
		c.println("You are out of money");
		newGame = 'n';
		//code for a delay
		try
		{
		    Thread.sleep (2000); //timed in miliseconds
		}
		catch (InterruptedException e)  //catches the exception
		{
		}
	    }
	}
	return balance;//returns to main method
    }
    
    public static int blackjack(int balance)//blackjack method
    {
    
	c.clear();//clears all previous entries
    
	char newGame = 'y';//declates variable for the game loop
	
	while(newGame == 'y'||newGame == 'Y')//loops game for another hand
	{
	boolean end = false;//ends the hand if true
	int playerHandVal = 0;//player hand value
	int cpuHandVal = 0;//cpu hand value
	int bet = 0;//store the bet
	int playerAces = 0;//stores the aces that the user may have
	int cpuAces = 0;//stores the aces that the cpu may have
	boolean reprint = false;//for reprinting the cards
	//prints the background
	c.setTextBackgroundColor (Color.green);
	c.setTextColor (Color.blue);
	c.setColor (Color.green);
	c.fillRect (0, 0, 10000, 10000);

	//prints the title
	c.setFont (new Font ("Seriff", Font.BOLD, 40));
	c.setColor (Color.black);
	c.drawString ("Blackjack", 230, 220);
	//prints the variation rules
	c.println("Variation Rules:\nYou can have a maximum of 6 cards in your hand and if you and the dealer both bust you win");

	while (bet <= 0||bet > balance)//if the input for the bet is in valid it loops
	{
	    //prompt for imput and input
	    c.println("Your current balance is: $" + balance);
	    c.println ("Please enter how much you would like to bet:\n(Number must be an integer and you must have the required amount)");
	    bet = c.readInt ();
	}
	//subtracts bet from balance
	balance -= bet;

	//clears previous entries and prints the background
	c.clear ();
	c.setTextBackgroundColor (Color.green);
	c.setTextColor (Color.blue);
	c.setColor (Color.green);
	c.fillRect (0, 0, 10000, 10000);

	//declaration for the storge of the card values
	int[] [] playerCards = new int [6] [2];
	int[] [] cpuCards = new int [6] [2];

	boolean playerTurn = true;//boolean to check if it's the player's turn
	int playerCrd = 0;//checks which card the player is on

	while (playerCrd < 2)//prints first twi cards
	{
	    int[] methodInfo = cardPrinting (playerHandVal, playerCrd, playerTurn, playerCards, reprint);
	    //stores the info from the method in variables
	    playerHandVal += methodInfo [0];
	    playerCards [playerCrd] [0] = methodInfo [1];
	    playerCards [playerCrd] [1] = methodInfo [2];
	    playerAces = methodInfo[3];
	    //adds to the player card count
	    playerCrd++;
	}

	if (playerHandVal == 21)//if the player gets a blackjack
	{
	    //calculates winnings and displays
	    bet = bet * 3 / 2;
	    c.drawString ("You Win (BLACKJACK!!!!) $" + bet, 200, 200);
	    balance += bet;
	}
	else//if the player does not get a blackjack
	{
	    //prints relevant information
	    c.setColor (Color.white);
	    c.setFont (new Font ("Seriff", Font.PLAIN, 20));
	    c.drawString ("Player Hand: " + playerHandVal, 0, 380);
	    c.drawString ("Player Bet: $" + bet, 0, 400);
	    c.drawString ("Player Balance: $" + balance, 0, 420);
	    
	    //counts which card the cpu is on
	    int cpuCard = 0;
	    playerTurn = false;//sets the turn to the player's turn

	    //displays the first cpu card
	    int[] methodInfo = cardPrinting (playerHandVal, cpuCard, playerTurn, cpuCards, reprint);

	    //stores the cpu information in variables
	    cpuHandVal += methodInfo [0];
	    cpuCards [cpuCard] [0] = methodInfo [1];
	    cpuCards [cpuCard] [1] = methodInfo [2];
	    cpuAces = methodInfo [3];
	    //displays the cpu hand value
	    c.setColor (Color.white);
	    c.setFont (new Font ("Seriff", Font.PLAIN, 20));
	    c.drawString ("CPU Hand: " + cpuHandVal, 0, 100);

	    //code for a delay
	    try
	    {
		Thread.sleep (1000); //timed in miliseconds
	    }
	    catch (InterruptedException e)  //catches the exception
	    {
	    }
	    
	    String playerInput = "";//set so the loop can happen
	    
	    while(!playerInput.equalsIgnoreCase("stand")&&!playerInput.equalsIgnoreCase("hit"))//loops on invalid input
	    {
		//prompt for input and input
		c.println ("Would you like to hit or to stand?\n(Enter either 'stand' or 'hit')");
		playerInput = c.readString ();
	    }
	    
	    while(end == false)//if the game has not ended
	    {
	    
	    if (playerInput.equalsIgnoreCase ("stand"))//if the user wishes to stand
	    {
		//clears previous entries
		c.clear ();
		cpuCard = 1;
		
		
		while(cpuHandVal < 17&&cpuCard <= 6)//prints new cpu cards until the cpu hts a soft or hard 17 or hits six cards
		{
		    methodInfo = cardPrinting (cpuHandVal, cpuCard, playerTurn, cpuCards, reprint);

		    //stores cpu card info in variables
		    cpuHandVal += methodInfo [0];
		    cpuCards [cpuCard] [0] = methodInfo [1];
		    cpuCards [cpuCard] [1] = methodInfo [2];
		    cpuCard++;//increases the cpu card count
		}
		//reprints first cpu card
		cpuCard = 0;
		reprint = true;
		methodInfo = cardPrinting (cpuHandVal, cpuCard, playerTurn, cpuCards, reprint);
		//reprints first player card
		playerTurn = true;
		playerCrd = 0;
		methodInfo = cardPrinting (playerHandVal, playerCrd, playerTurn, playerCards, reprint);
		//reprints second player card
		playerCrd = 1;
		methodInfo = cardPrinting (playerHandVal, playerCrd, playerTurn, playerCards, reprint);
		
		//reprints important info
		c.setColor (Color.white);
		c.setFont (new Font ("Seriff", Font.PLAIN, 20));
		c.drawString ("CPU Hand: " + cpuHandVal, 0, 100);
		c.drawString ("Player Hand: " + playerHandVal, 0, 380);
		c.drawString ("Player Bet: $" + bet, 0, 400);
		c.drawString ("Player Balance: $" + balance, 0, 420);
		
		//if the game has not ended
		while(end == false)
		{
		    if(cpuHandVal > 21)//if the cpu busts, user wins
		    {
			bet = bet * 3 / 2;
			c.drawString ("You Win $" + bet, 200, 200);
			balance += bet;
			end = true;
		    }
		    else if(playerHandVal > 21)//if the player busts they lose
		    {
			if(playerAces > 0)//if there are aces they likely lose
			{
			    playerHandVal -= 10;
			}
			else//no aces the lose
			{
			    c.drawString ("You Lose $" + bet, 200, 200);
			    end = true;
			}
		    }
		    else if(playerHandVal > cpuHandVal)//if the player beats out the cpu
		    {
			bet = bet * 3 / 2;
			c.drawString ("You Win $" + bet, 200, 200);
			balance += bet;
			end = true;
		    }
		    else if(cpuHandVal > playerHandVal)//if the cpu beats out the player
		    {
			c.drawString ("You Lose $" + bet, 200, 200);
			end = true;
		    }
		    else//if there is a tie
		    {
			c.drawString ("Push, your $" + bet + " has been returned to your balance", 200, 200);
			balance += bet;
			end = true;
		    }
		}
	    }
	    else if (playerInput.equalsIgnoreCase ("hit"))//if the playe wishes to hit
	    {
		while(playerInput.equalsIgnoreCase ("hit")&&playerCrd < 6&&playerHandVal <= 21)//continues printing player cards
		{
		    //prints the cards
		    playerTurn = true;
		    methodInfo = cardPrinting (playerHandVal, playerCrd, playerTurn, playerCards, reprint);
		    //stores method info in variables
		    playerHandVal += methodInfo [0];
		    playerCards [playerCrd] [0] = methodInfo [1];
		    playerCards [playerCrd] [1] = methodInfo [2];
		    playerAces = methodInfo[3];
		    
		    playerCrd++;//increases the card count
		
		    if(playerHandVal > 21)//if the player busts the game checks for aces
		    {
			if(playerAces > 0)//if there is an ace the game continues
			{
			    playerHandVal -= 10;
			    playerAces--;
			}
		    }
		    
		    playerInput = "";//changes so the loop can happen
		    
		    while(!playerInput.equalsIgnoreCase("stand")&&!playerInput.equalsIgnoreCase("hit"))//loops if there is invalid input
		    {
			//input prompt and input
			c.println("Your hand is currently worth: " + playerHandVal);
			c.println("Would you like to hit again or stand?\n(Please enter either 'hit' or 'stand'");
			playerInput = c.readString();
		    }
		}
		//clears previous entries
		c.clear ();
		cpuCard = 1;
		playerTurn = false;
		
		while(cpuHandVal < 17&&cpuCard <= 6)//prints cpu cards and stores method info
		{
		    methodInfo = cardPrinting (cpuHandVal, cpuCard, playerTurn, cpuCards, reprint);

		    cpuHandVal += methodInfo [0];

		    cpuCards [cpuCard] [0] = methodInfo [1];
		    cpuCards [cpuCard] [1] = methodInfo [2];
		    cpuCard++;
		    if(cpuHandVal > 21)
		    {
			if(cpuAces > 0)
			{
			    cpuHandVal -= 10;
			    cpuAces--;
			}
		    }
		}
		//reprints previous cpu card
		cpuCard = 0;
		reprint = true;
		methodInfo = cardPrinting (cpuHandVal, cpuCard, playerTurn, cpuCards, reprint);
		//reprints player cards
		playerTurn = true;
		for(int reprintLoop = 0;reprintLoop <= playerCrd - 1;reprintLoop++)
		{
		    methodInfo = cardPrinting (playerHandVal, reprintLoop, playerTurn, playerCards, reprint);
		}
	    
		//reprints important info
		c.setColor (Color.white);
		c.setFont (new Font ("Seriff", Font.PLAIN, 20));
		c.drawString ("CPU Hand: " + cpuHandVal, 0, 100);
		c.drawString ("Player Hand: " + playerHandVal, 0, 380);
		c.drawString ("Player Bet: $" + bet, 0, 400);
		c.drawString ("Player Balance: $" + balance, 0, 420);
		
		
		while(end == false)//if the game has not ended
		{
		    if(cpuHandVal > 21)//if the cpu busts the player wins
		    {
			bet = bet * 3 / 2;
			c.drawString ("You Win $" + bet, 200, 200);
			balance += bet;
			end = true;
		    }
		    else if(playerHandVal > 21)//if the player busts cpu wins
		    {
			c.drawString ("You Lose $" + bet, 200, 200);
			end = true;
		    }
		    else if(playerHandVal > cpuHandVal)//if the player wins
		    {
			bet = bet * 3 / 2;
			c.drawString ("You Win $" + bet, 200, 200);
			balance += bet;
			end = true;
		    }
		    else if(cpuHandVal > playerHandVal)//if the cpu wins
		    {
			c.drawString ("You Lose $" + bet, 200, 200);
			end = true;
		    }
		    else//if there is a tie
		    {
			c.drawString ("Push, your $" + bet + " has been returned to your balance", 200, 200);
			balance += bet;
			end = true;
		    }
		} 
		
	    }
	    }
	    //code for a delay
	    try
	    {
		Thread.sleep (2000); //timed in miliseconds
	    }
	    catch (InterruptedException e)  //catches the exception
	    {
	    }
	    //reprints everything
	    c.clear();
	    c.setColor (Color.green);
	    c.fillRect (0, 0, 10000, 10000);

	    c.setFont (new Font ("Seriff", Font.BOLD, 40));
	    c.setColor (Color.black);
	    c.drawString ("Blackjack", 230, 220);
	    newGame = 'j';
	    while(newGame != 'y'&&newGame != 'Y'&&newGame != 'n'&&newGame != 'N')
	    {
		c.println("Would you like to play another hand?(Y/N)");
		newGame = c.readChar();
	    }
	    if(balance == 0)
	    {
		c.println("You are out of money");
		newGame = 'n';
		//code for a delay
		try
		{
		    Thread.sleep (2000); //timed in miliseconds
		}
		catch (InterruptedException e)  //catches the exception
		{
		}
	    }
	    }
	}
    return balance;
    }
    
    public static int[] cardPrinting (int handVal, int playerCard, boolean playTurn, int[][] playerCrds, boolean rePrint)
    {
	int crdSuit = (int) ((Math.random () * 4) + 1);
	int crdVal = (int) ((Math.random () * 13) + 1);
	int playerXPos = 0;
	int cpuYPos = 0;
	int cpuCardPos = 0;
	int aces = 0;
	if (playerCard >= 1)
	{
	    playerXPos += (150 * playerCard);
	}

	if (playTurn == false)
	{
	    cpuYPos -= 425;
	    cpuCardPos -= 450;
	}
	if (rePrint == true)
	{
	    crdVal = playerCrds [playerCard] [0];
	    crdSuit = playerCrds [playerCard] [1];
	}

	c.setColor (Color.white);
	c.fillRoundRect (25 + playerXPos, 425 + cpuCardPos, 125, 100, 20, 20);
	c.setColor (Color.black);
	c.drawRoundRect (25 + playerXPos, 425 + cpuCardPos, 125, 100, 20, 20);

	if (crdSuit == 1)
	{
	    c.setColor (Color.black);
	    c.fillArc (91 + playerXPos, 455 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (114 + playerXPos, 455 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (102 + playerXPos, 435 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (103 + playerXPos, 451 + cpuYPos, 20, 20, 0, 360);
	    c.fillArc (99 + playerXPos, 450 + cpuYPos, 30, 30, 255, 32);
	}
	else if (crdSuit == 2)
	{
	    c.setColor (Color.red);
	    c.fillArc (91 + playerXPos, 440 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (110 + playerXPos, 440 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (75 + playerXPos, 447 + cpuYPos, 75, 75, 55, 70);
	}
	else if (crdSuit == 3)
	{
	    c.setColor (Color.black);
	    c.fillArc (91 + playerXPos, 455 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (110 + playerXPos, 455 + cpuYPos, 23, 23, 0, 360);
	    c.fillArc (97 + playerXPos, 455 + cpuYPos, 30, 30, 255, 32);
	    c.fillArc (74 + playerXPos, 397 + cpuYPos, 75, 75, 237, 70);
	}
	else if (crdSuit == 4)
	{
	    c.setColor (Color.red);
	    c.fillArc (85 + playerXPos, 405 + cpuYPos, 60, 60, 235, 70);
	    c.fillArc (85 + playerXPos, 455 + cpuYPos, 60, 60, 55, 70);
	}

	c.setFont (new Font ("Seriff", Font.BOLD, 60));

	if (crdVal <= 9 && crdVal > 1)
	{
	    String cardNum = String.valueOf (crdVal);
	    c.drawString (cardNum, 35 + playerXPos, 480 + cpuYPos);
	    handVal = crdVal;
	}
	else if (crdVal == 11)
	{
	    c.drawString ("J", 35 + playerXPos, 480 + cpuYPos);
	    handVal = 10;
	}
	else if (crdVal == 12)
	{
	    c.drawString ("Q", 35 + playerXPos, 480 + cpuYPos);
	    handVal = 10;
	}
	else if (crdVal == 13)
	{
	    c.drawString ("K", 35 + playerXPos, 480 + cpuYPos);
	    handVal = 10;
	}
	else if (crdVal == 10)
	{
	    c.drawString ("10", 20 + playerXPos, 480 + cpuYPos);
	    handVal = 10;
	}
	else if(crdVal == 1)
	{
	    aces++;
	    c.drawString ("A", 35 + playerXPos, 480 + cpuYPos);
	    handVal = 11; 
	}
	int[] info = new int [4];
	info [0] = handVal;
	info [1] = crdVal;
	info [2] = crdSuit;
	info [3] = aces;
	return info;
    }

} // Final_Assignment_Casino class
