/*
 * Program name: StockTrader.java
 * 
 * Name: Lucas Chow
 * 
 * Last Edited: 6/21/2022
 * 
 * ICS3U1 - CULMINATING !! :)
 * 
 * This program models the real world stock market, equipped with file IO, 3 dimensional arrays, methods, array sorting (bubble sort), and a fully-functioning AI
 * 
 * 
 * 
 */


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class StockTrader {

	/*
	 * boolean isNum(String input)
	 * 
	 * Returns boolean - if the input string can be parsed to an integer
	 * 
	 * String input -> represents the input string/parameter for this method
	 * 
	 * Takes in an input string and checks if it can be parsed to an int, returning true. If it can, catches the numberFormatExcetpion and true, otherwise returning false
	 * 
	 * uses try and catch (NumberFOrmatException) to check if it is an int
	 * 
	 * 
	 */
	public static boolean isNum(String input)
	{
		boolean isValid = false;
		try {
			Integer.parseInt(input);
			isValid = true;
		} catch (NumberFormatException e) {}
		return isValid;
	}
	
	/*
	 * void updateHighScoreFileAndPrint(String[][] highScoreArr, STring filename)
	 * 
	 * returns void - this updates the array using bubble sort and outputs a leader-board
	 * 
	 * String[][] highScoreArr -> representing an array that is a parameter, used as a pass by reference, to get the data from the program and bubble sort
	 * 
	 * String filename -> the name of the file to write the data on
	 * 
	 * This method takes in a 2d array and file, sorts the 2d array by the numbers in it, and then outputs the names and number from the 2d array (sorted) onto the filename input
	 * 
	 */
	public static void updateHighScoreFileAndPrint(String[][] highScoreArr, String filename)
	{
		String swapValue;
		String swapKey;
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(filename, true));
			for (int b = 0; b < highScoreArr.length-1; b++)
			{
				for (int a = 0; a < highScoreArr.length-b-1; a++)
				{
					
					if (Integer.parseInt((highScoreArr[a+1][1])) > Integer.parseInt((highScoreArr[a][1])))
					{
						swapValue = highScoreArr[a][1];
						swapKey = highScoreArr[a][0];
						highScoreArr[a][1] = highScoreArr[a+1][1];
						highScoreArr[a][0] = highScoreArr[a+1][0];
						highScoreArr[a+1][1] = swapValue;
						highScoreArr[a+1][0] = swapKey;
					}
				}
			}
			
			
			//printing out the leader-board
			System.out.println("Leaderboard");
			System.out.println("____________");
			for (int i = 0; i < highScoreArr.length; i++)
			{
				System.out.printf("Place: %d  Name: %s  Score: $%s\n",(i+1),highScoreArr[i][0],highScoreArr[i][1]);
				br.write(highScoreArr[i][0]+"\n");
				br.write(highScoreArr[i][1]+"\n");
			}
			
			br.close();
		} catch (IOException e) {}
	}

	/*
	 * void printHighScoreFromFile(String highScoreFile)
	 * 
	 * This method takes in the input string highScoreFile, returns void, as it reads a file and outputs the high scores
	 * 
	 * string highScoreFile -> representing the file which the data is extracted from
	 * 
	 * This file takes in a file and reads the data and outputs the leader-board of the sorted values (bubble-sort)
	 * 
	 * 
	 */
 	public static void printHighScoreFromFile(String highScoreFile)
	{
		String swapValue;
		String swapKey;
		int highScoreCountCounter;
		String input;
		final int HIGH_SCORE_WIDTH = 2;
		String[][] highScoreArr;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(highScoreFile));
			  highScoreCountCounter = 0;
			    do
			    {
			    	input = br.readLine();
			    	if (isNum(input))
			    	{
			    		highScoreCountCounter++;
			    	}
			    } while (input != null);
				
			    highScoreArr = new String[highScoreCountCounter][HIGH_SCORE_WIDTH];
				br.close();
				
				
				BufferedReader br_2 = new BufferedReader(new FileReader(highScoreFile));
				
				
				
				
				
				for (int i =0; i < highScoreCountCounter; i++)
				{
					input = br_2.readLine();
					highScoreArr[i][0] = input;
					input = br_2.readLine();
					highScoreArr[i][1] = input;
				}
				
				br_2.close();

				
				
				for (int b = 0; b < highScoreArr.length-1; b++)
				{
					for (int a = 0; a < highScoreArr.length-b-1; a++)
					{
						
						if (Integer.parseInt((highScoreArr[a+1][1])) > Integer.parseInt((highScoreArr[a][1])))
						{
							swapValue = highScoreArr[a][1];
							swapKey = highScoreArr[a][0];
							highScoreArr[a][1] = highScoreArr[a+1][1];
							highScoreArr[a][0] = highScoreArr[a+1][0];
							highScoreArr[a+1][1] = swapValue;
							highScoreArr[a+1][0] = swapKey;
						}
					}
				}
				if (highScoreCountCounter == 0)
				{
					System.out.println("\nLeaderboard is empty! Be the first on the leaderboard!");
				}
				else
				{

					for (int x = 0; x < highScoreArr.length; x++)
					{
						System.out.println("Player #"+(x+1)+" place on the leaderboard");
						System.out.println("______________________________________");
						System.out.printf("%-25S",highScoreArr[x][0]);
						System.out.println(highScoreArr[x][1]+"\n\n");
					}
				}
		} catch (IOException e) {}
	}
	
 	/*
 	 * void fillStockValues(int[] stockValue)
 	 * 
 	 * returns void - uses stockValue array as a pass by object
 	 * 
 	 * This method takes in an array and fills it with values, using a partial random - modern stock market realistic algorithm to generate values
 	 * 
 	 * int[] stockValue -> the array that the programmer wants the method to fill up with fictional values
 	 * 
 	 * This program takes in int[] stockValue and uses momentum-random to determine the next days values
 	 * 
 	 * 
 	 */
	public static void fillStockValues(int[] stockValue) 
	{
		//possibly add if bankrupt?
		int random_upOrDown;
		int upperLimit = 30;
		int momentum;
		int volatilityIndex;
		stockValue[0] = (int)(Math.random()*5+1);
		
		volatilityIndex = (int)(Math.random()*4);
		
		if (volatilityIndex == 0 || volatilityIndex == 1)
		{
			volatilityIndex = 2;
		}
		for (int i = 1; i < stockValue.length; i++)
		{

			momentum = (int)(Math.random()*volatilityIndex);
			if (momentum == 0 || momentum == 1)
			{
				momentum = 2;
			}
			random_upOrDown = (int) Math.round(Math.random());
			if (random_upOrDown == 1)
			{

				stockValue[i] = stockValue[i-1] + momentum;
			}
			else
			{

				stockValue[i] = stockValue[i-1] - momentum;
			}
			if (stockValue[i] <= 0)
			{
				stockValue[i] = stockValue[i-1] + momentum;
			}
			else if (stockValue[i] >= upperLimit)
			{
				stockValue[i] = stockValue[i-1] - momentum;
			}
		}
	}
	
	
	/*
	 * void printGraphPerDay(int[] stockValue, int day)
	 * 
	 * This method takes in int[] stockValue and int day and prints the graph of the stock of the day, with the days preceding it
	 * 
	 * int[] stockValue -> input values, not modulated, just used to reference data
	 * 
	 * int day -> the day is the number up to the number of days of stock data 
	 * 
	 * this method takes in an array and day and prints out the coordinating stock graph of it. it uses some array searching algorithms to determine
	 * upper "^", denoting to the top of the actual graph, filling in the rest with "*"
	 * 
	 * 
	 */
	public static void printGraphPerDay(int[] stockValue, int day)
	{
		final int CONVERTED_ARR_LENGTH = 31;
		final int CONVERTED_ARR_WIDTH = 100;
		
		
		String[][] convertedArr = new String[CONVERTED_ARR_LENGTH][CONVERTED_ARR_WIDTH];
		
		for (int i = 0; i < CONVERTED_ARR_LENGTH; ++i)
		{
			for (int a = 0; a < CONVERTED_ARR_WIDTH; a++)
			{
				convertedArr[i][a] = "   ";
			}
		}
		
		for (int i = 0; i < stockValue.length; i++)
		{
			convertedArr[CONVERTED_ARR_LENGTH - stockValue[i]][i] = "^";
		}
		
		
		
		for (int i = 0; i < stockValue.length; i++)
		{
			if (convertedArr[CONVERTED_ARR_LENGTH - stockValue[i]][i].equals("^") || convertedArr[CONVERTED_ARR_LENGTH - stockValue[i]][i].equals("*"))	
			{
			
				for (int a = 1; a < stockValue[i]; a++)
				{
					convertedArr[CONVERTED_ARR_LENGTH - stockValue[i]+a][i] = "*";
				}
				
			}
			
		}
		
		for (int i = 0; i < convertedArr.length; i++)
		{
			if (i == (int) convertedArr.length/2)
			{
				System.out.print("Value ($)");
			}
			else
			{
				System.out.printf("%9s"," ");
			}
			for (int a = 0; a < day; a++)
			{
				System.out.printf("%3s",convertedArr[i][a]);
			}
			System.out.println();
		}
		
		//keep

		System.out.printf("%-9s","Days:");
		for (int i = 0; i < CONVERTED_ARR_WIDTH; i++)
		{
			System.out.printf("%3s", (i+1));
		}
	}
	
	/*
	 * void aiBUYorSell(int[][] stockGenValues,int x, int[] cashAllocation,int currentDay, int[] stocksIn, int[][][] stockArr, int[] balance, int[] sellingChance,String[] preGenNames)
	 * 
	 * This method is part of the artificialIntelligenceMain, to determine if to buy or sell, and actually buying or selling
	 * 
	 * int[][] stockGenValues -> referring to the values generated in the game
	 * 
	 * int x -> the stock choice, to keep it consistent with the main method
	 * 
	 * int currentDay -> the current day, starts at 0
	 * 
	 * int[] cashAllocation -> the amount of money allocated per stock in an array
	 * 
	 * int[] stocksIn -> utilizing 1s and 0s to denote if the stock is currently not held or held.
	 * 
	 * int[][][] stockArr -> pass by reference, for each stock, the number of shares, the current stock value, etc
	 * 
	 * int[] sellingChance -> an array with a cell for each stock denoting to the chance that it is going to be sold, determined by the previous day's value and previous time period
	 * 
	 * String[] preGenNames -> to print the names of the stocks
	 * 
	 * int[] balance -> balance of each of the players; modulated pass-by array object
	 * 
	 * This method determines if a stock is held or not, then, uses random to 'roll the dice' and see if it would take any action, either holding, selling, or buying depending on the situation by evaluate stock performance
	 * 
	 * 
	 * 
	 */
	public static void aiBUYorSell(int[][] stockGenValues,int x, int[] cashAllocation,int currentDay, int[] stocksIn, int[][][] stockArr, int[] balance, int[] sellingChance,String[] preGenNames)
	{
		int randomNumber;
		int shareGoingToInvest;
		
		randomNumber = (int)(Math.random()*100+1);
		if (stocksIn[x] == 1)
		{
			if (randomNumber <= sellingChance[x])
			{
				//sell
				balance[2] += stockArr[x][2][0] * stockGenValues[x][currentDay];
				cashAllocation[x] += stockArr[x][2][0] * stockGenValues[x][currentDay];
				stockArr[x][2][0] = 0;
				stockArr[x][2][1] = 0;
				stocksIn[x] = 0;
			}
		}
		else if (stocksIn[x] == 0)
		{
			if (sellingChance[x] <= 0 || stockGenValues[x][currentDay] <= 4)
			{
				//buy
				System.out.println(cashAllocation[x]);
				shareGoingToInvest = cashAllocation[x]/stockGenValues[x][currentDay];
				stockArr[x][2][0] = shareGoingToInvest;
				stockArr[x][2][1] = stockGenValues[x][currentDay];
				balance[2] -= shareGoingToInvest*stockGenValues[x][currentDay];
				stocksIn[x] = 1;
				cashAllocation[x] -= shareGoingToInvest * stockGenValues[x][currentDay];

			}
		}	
		
	}
	
	/*
	 * void evaluateStockPerformance(int[][] stockGenValues, int currentDay, int[] sellingChance, int index)
	 * 
	 * built on the artificialIntelligenceMain method
	 * 
	 * int[][] stockGenValues -> the stock data referenced but not modulated from the main method
	 * 
	 * int current day -> the current day
	 * 
	 * int[] sellingChance -> the pass by object, modulated according to changes in the market
	 * 
	 * int index -> the index of the stock
	 * 
	 * 
	 * This method takes in the stock data, and changes the chance of the stock being purchased or sold, or held, determined by changes in the market. 
	 * This method only applies to one stock concurrently, and actual transcations are done on the aiBUYorSell
	 * 
	 */
	public static void evaluateStockPerformance(int[][] stockGenValues, int currentDay, int[] sellingChance, int index)
	{
		int prevValue;
		int stockDiff;
		
		
		//loops through and finds the difference from prev -> current day
		prevValue = stockGenValues[index][currentDay-1];
		stockDiff = (int) Math.round((-100.0 * (1 - (double) stockGenValues[index][currentDay] / (double) prevValue)));
	
		
		
		if (stockDiff <= -100)
		{
			//hold effectively
			sellingChance[index] = 0;
		}
		else if(stockDiff <= 0 && stockDiff >= -30)
		{
			sellingChance[index] -=20;
		}
		else if (stockDiff <-30 && stockDiff > -100)
		{
			sellingChance[index] -= 35;
		}
		else if (stockDiff >= 100)
		{
			//sell
			sellingChance[index] +=30;
		}
		else if (stockDiff <= 25 && stockDiff > 0)
		{
			sellingChance[index] +=10;
		}
		else if (stockDiff < 100 && stockDiff > 25)
		{
			sellingChance[index] +=20;
		}
		
		
		
		if (sellingChance[index] >= 120)
		{
			sellingChance[index] = 100;
		}
		else if (sellingChance[index] <= -10)
		{
			sellingChance[index] = 0;
		}
		
	}
	
	/*
	 * void artificialIntelligenceMain(int[] cashAllocation, int[] stocksIn, int[][] stockGenValues,int startingCash,int currentDay, int[][][] stockArr, int[] balance, int playerCount,String[] preGen, int[] sellingChance) 
	 * 
	 * 
	 * void aiBUYorSell(int[][] stockGenValues,int x, int[] cashAllocation,int currentDay, int[] stocksIn, int[][][] stockArr, int[] balance, int[] sellingChance,String[] preGenNames)
	 * 
	 * This method is part of the artificialIntelligenceMain, to determine if to buy or sell, and actually buying or selling
	 * 
	 * int[][] stockGenValues -> referring to the values generated in the game
	 * 
	 * int x -> the stock choice, to keep it consistent with the main method
	 * 
	 * int currentDay -> the current day, starts at 0
	 * 
	 * int[] cashAllocation -> the amount of money allocated per stock in an array
	 * 
	 * int[] stocksIn -> utilizing 1s and 0s to denote if the stock is currently not held or held.
	 * 
	 * int[][][] stockArr -> pass by reference, for each stock, the number of shares, the current stock value, etc
	 * 
	 * int[] sellingChance -> an array with a cell for each stock denoting to the chance that it is going to be sold, determined by the previous day's value and previous time period
	 * 
	 * String[] preGen -> to print the names of the stocks
	 * 
	 * int startingCash -> the starting cash awarded to each of the players, given by the main method
	 * 
	 * int[] balance -> the balance of each of the players; modulated pass-by array-object. 
	 * 
	 * 
	 * This method acts as a hub to the AI / Bot part of the program. Spanning across 3 methods, this method utilizes the other 2 (aiBUYorSell and evaluateStockPerformance methods) to make
	 * logical decision on the current situation of the market, judging off market history, etc. Although the bot has to utilize RNG (Random number generation), it is utilized
	 * to evaluate average percentages (50%, 60%) and naturally utilizing the bot several tens of times will make the RNG practically inadmissible. These sets of methods buy, sell and hold the 
	 * stocks independently and have been proven to beat popular indexes including the S&P in the real world.
	 * 
	 */
	
	public static void artificialIntelligenceMain(int[] cashAllocation, int[] stocksIn, int[][] stockGenValues,int startingCash,int currentDay, int[][][] stockArr, int[] balance, int playerCount,String[] preGen, int[] sellingChance) 
	{
		
		
		int sharesPerStock;
		
		
		for (int i = 0; i < stockGenValues.length; i++)
		{
			if (currentDay == 0)
			{
				for (int y = 0; y < stockGenValues.length; y++)
				{
					cashAllocation[y] = startingCash / stockGenValues.length;
				}
				sharesPerStock = cashAllocation[i] / stockGenValues[i][0];
				balance[2] -= sharesPerStock * stockGenValues[i][0];
				stockArr[i][2][0] = sharesPerStock;
				stockArr[i][2][1] = stockGenValues[i][0];
				stocksIn[i] = 1;
				cashAllocation[i] -= sharesPerStock * stockGenValues[i][0];
				
			}
			else
			{
				evaluateStockPerformance(stockGenValues, currentDay, sellingChance, i);
				aiBUYorSell(stockGenValues, i, cashAllocation, currentDay, stocksIn, stockArr, balance, sellingChance,preGen);
			}
		}
		
	}

	
	public static void main(String[] args)
	{		
		
		//Initializing Integers
		int playerCount;
		int marketLength;
		int violationCounter;
		int choiceNum;
		int subChoice;
		int tempValue;
		int superSubChoice;
		int userGen_stockList_Size;
		int startingCash;
		int humanPlayerCount;
		
		//Initializing constants, use to initialize arrays
		final int SCORE_AND_NAME = 2;
		final int NAMES_LENGTH = 3;
		final int HOLDINGS_AND_VALUE = 2;
		
		//Initializing Strings
		String highScoreFile = "StockTrade_highScorelist.txt";
		String input;
		
		
		//Initializing string arrays
		String[] userGeneratedStocks;
		String[] preGen_or_CustomStocks_Arr;
		String[][] highScoreArr;
		String[] possibleStockNames = {"APPL","GOOGL","TSLA","KROC","AYJ","LSC","SPY","BRK","GME","NYSE"};
		String[] names = new String[NAMES_LENGTH];
		
		//initializing integer arrays
		int[] balance;
		int[][] stockValueGeneration;
		int[] coversionArray;
		int[] stockIn;
		int[] cashAllocation;
		int[] sellingChance;
		int[] totalScore;
		int[][][] stockDataArr;
 		
		//Initializing boolean values
		boolean ifAI;
		boolean validInput;
		boolean ifCustomStocks;
		boolean playerTurnInProgress;
		boolean gameStarted;
		boolean stockFound;
		boolean seeAI_Transactions;
		
		
		
		
		//loop to check if file is made. If file is not found, creates file
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(highScoreFile));
			br.close();
		} catch (IOException e)
		{
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(highScoreFile, true));
				bw.close();
			} catch (IOException e1) {}
		}
		
 
		
		//starting the do - while loop, the program will run infinitely until unplugged
		do
		{
			try
			{	
				//creating Buffered Reader
				BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
			    
				//setting all values in the balance array to be zero, to be properly initialized later
				balance = new int[3];
				for (int i = 0; i < 3; i++)
				{
					balance[i] = 0;
				}
				
				//introduction / name
				System.out.println("STOCK MARKET SIMULATOR");
				System.out.println("______________________\n\n");
				

				userGen_stockList_Size = 10;
				System.out.println("\n(1) 1 Player");
				System.out.println("(2) 2 Players");
				System.out.println("(3) Leaderboard");
				System.out.println("(4) How to play");
				System.out.println("(5) Credits");
				
				//Initializing integer choice variables
				
				playerCount = -1;
				choiceNum = -1;
				
				
				//prompting user for a choice number
				//Ensuring the user enters a valid input
				System.out.print("\nEnter a number: ");
				input = sc.readLine();
				do
				{
					validInput = false;
					if (isNum(input))
					{
						if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 5)
						{
							validInput = true;
							choiceNum = Integer.parseInt(input);
						}
						else
						{
							System.out.println("\nNumber must be from 1 - 5");
						}
					}
					if (!validInput)
					{
						System.out.print("\nEnter A NUMBER!: ");
						input = sc.readLine();
					}
				} while (!validInput);
				
				
				// boolean values to be false
				gameStarted = false;
				ifAI = false;
				humanPlayerCount = -1;
				
				//for 1 player (1 player OR 1 player VS AI)
				if (choiceNum == 1)
				{
					System.out.println("\n(1) Solo Player");
					System.out.println("(2) Player vs AI");
					
					System.out.println("\nEnter a choice or press q/quit to return to the main menu");
					System.out.print("> ");
					input = sc.readLine();
					
					//setting choice to be -1
					subChoice = -1;
					
					//ensuring input is valid
					do
					{
						validInput = false;
						if (isNum(input))
						{
							if (Integer.parseInt(input) == 1)
							{
								System.out.println("\nYou have selected solo player: ");
								subChoice = 1;
								validInput = true;
								playerCount = 1;
								gameStarted = true;
								humanPlayerCount = 1;
							}
							else if (Integer.parseInt(input) == 2)
							{

								System.out.println("\nYou have selected solo player and AI");
								subChoice = 2;
								validInput = true;
								playerCount = 2;
								ifAI = true;
								gameStarted = true;
								humanPlayerCount = 1;
							}
							
							else
							{
								System.out.println("\nInvalid number, enter number from 1 to 4\n");
							}
							
						}
						else if (!isNum(input))
						{
							input.toLowerCase();
							if (input.equals("q") || input.equals("quit"))
							{
								validInput = true;
								System.out.println("Returning to main menu");
							}
						}
						else
						{
							System.out.println("Make sure input is a number or \"q\"");
						}
						if (!validInput)
						{

							System.out.println("\n(1) Solo Player");
							System.out.println("(2) Player vs AI");
							System.out.println("Enter a choice, or q/quit to quit: ");
							System.out.print("> ");
							
							input = sc.readLine();
						}
					} while (!validInput);
				}
				
				
				//For two players (Player vs Player OR Player vs Player vs AI)
				else if (choiceNum == 2)
				{
					System.out.println("\n(1) Player vs Player");
					System.out.println("(2) Player vs Player vs AI\n");
				
					
					System.out.println("Enter a choice or press q/quit to return to the main menu");
					System.out.print("> ");
					
					//prompting user to enter the correct valid input
					input = sc.readLine();
					do
					{
						validInput = false;
						if (isNum(input))
						{
							
							if (Integer.parseInt(input) == 1)
							{
								System.out.println("\nYou have selected: Player Vs Player");
								validInput = true;
								subChoice = 3;
								playerCount = 2;
								gameStarted = true;
								humanPlayerCount = 2;
							}
							else if (Integer.parseInt(input) == 2)
							{

								System.out.println("\nYou have selected: Player Vs Player Vs AI");
								validInput = true;
								subChoice = 4;
								playerCount = 3;
								ifAI = true;
								gameStarted = true;
								humanPlayerCount = 2;
							}
							else
							{
								System.out.println("\nInvalid number, enter number from 1 to 4\n");
							}
						}
						else
						{
							System.out.println("\nMake sure input is a number");
						}
						if (!validInput)
						{

							System.out.println("\n(1) Player vs Player");
							System.out.println("(2) Player vs Player vs AI");
							System.out.println("Enter a choice, or q/quit to quit: ");
							System.out.print("> ");

							input = sc.readLine();
						}
					} while (!validInput);
					
				}
				
				
				
				
				else if (choiceNum == 3)
				{
					printHighScoreFromFile(highScoreFile);
					//Calling the printtofile() method to print the current leader board of top scorers
				}
				else if (choiceNum == 4)
				{
					//displaying to the console how to play the game
					System.out.println("\nHow to play: ");
					System.out.println("________________\n");
					System.out.println("This game models the real life stock market (totally)\nTo start, choose from 1 to 2 human players. \nThen, you will be presented with a choice: ");
					System.out.println("Do you want to play with AI or not?");
					System.out.println("Specify if you want to play with AI.");
					System.out.println("You will then be prompted with:\n");
					System.out.println(" - Custom usernames");
					System.out.println(" - Custom Stocks");
					System.out.println(" - Duration of stock market (10 - 30 days)");
					System.out.println(" - Starting Stock Market balance from $100 - $1000");
					System.out.println("\nTo play:");
					System.out.println("Make a choice on a stock to selected");
					System.out.println("You can choose between buying or selling your holdings / stocks");
					System.out.println("Or neither if you would prefer");
					System.out.println("The prices of stocks changes throughout, so you might want to keep an eye on stocks");
					System.out.println("At the end, the winner is one the with the highest total score: ");
					System.out.println("Score is tallied by total liquidated portfolio + leftover balnace");
					System.out.println("Winner will have the opportunity to be immortalized in the forever Leaderboard");
					System.out.println("\n\n");
					System.out.println("Press ENTER to return to the start \n");
					
					//return to menu function
					sc.readLine();
					
				}
				else if (choiceNum == 5)
				{
					
					//displaying the credits
					System.out.println("This is a Stock Market Simulator, my cumulative project for ICS3U1 2022.\r\n"
							+ "\r\n"
							+ "		Created by: Lucas Chow\r\n"
							+ "\r\n"
							+ "		Staff Supervisor: Mr. Skuja\r\n"
							+ "\r\n"
							+ "		Coded in JavaSE-17 in Eclipse IDE \r\n"
							+ "\r\n"
							+ "\r\n"
							+ "		Press ENTER to return to the start \r\n"
							+ "");
					
					//return to menu function
					sc.readLine();
					
				}
				
				
				//if a valid command is given, the game can start
				if (gameStarted)
				{
					//prompting user(s) for name(s) per player(s)
					for (int i = 0; i < humanPlayerCount; i++)
					{	
						validInput = false;
						do
						{

							//prompting user(s) for name(s)
							System.out.printf("\nPlayer %d: Enter your username (MAX 15 Chars): \n",(i+1));
							input = sc.readLine();
							
							
							
							//setting restrictions to the name
							if (input.length() <= 15)
							{
								validInput = true;
								names[i] = input;
							}
							if (!validInput)
							{
								System.out.println("Make sure input is a maximum 15 characters");
								input = sc.readLine();
							}
								
						} while (!validInput);
						
						System.out.printf("Player %d you have selected the username \"%s\"\n",(i+1),names[i]);				
					}
					//if user chose an AI
					if (ifAI)
					{
						names[humanPlayerCount] = "Artifical Intelligence (BOT)";
					}
					
					
					//printing out the names of the players
					System.out.println("\n\nPlayers:");
					System.out.println("___________");
					for (int i = 0; i < humanPlayerCount; i++)
					{
						if (names[i] != null)
						{

							System.out.println("Player #"+(i+1)+": "+names[i]);	
						}
					}
					
					
					//prompting the user if they want to play with their own stocks
					System.out.println("\nDo you want to play with your own stocks?");
					System.out.println("Y/Yes if you do, N/No for pre-set stocks");
					System.out.print("> ");
					input = sc.readLine();
					
					ifCustomStocks = false;
					
					
					//checking for correct input and if they want to play with their own custom stocks or not
					do
					{
						validInput = false;
						input = input.toUpperCase();
						if (input.equals("Y") ^ input.equals("YES"))
						{
							System.out.println("You are playing with your own custom stocks");
							ifCustomStocks = true;
							validInput = true;
						}
						else if (input.equals("N") ^ input.equals("NO"))
						{
							System.out.println("You are playing with pre-set stocks");
							ifCustomStocks = false;
							validInput = true;
						}
						
						if (!validInput)
						{
							System.out.println("\nPlease make sure you enter: ");
							System.out.println("Y/Yes if you do, N/No for pre-set stocks\n");
							input = sc.readLine();
						}
					} while (!validInput);
					
					
					
					//If they are playing with custom stocks
					if (ifCustomStocks)
					{
						//prompting the number of stocks they want to play with:
						System.out.println("How many stocks do you want to play with?");
						input = sc.readLine();			
						
						//Initializing that counter that sets the # of stocks, used to create the stock array
						userGen_stockList_Size = -1;
						do
						{
							validInput = false;
							
							if (isNum(input))
							{
								//setting restrictions to maximum number of stocks they can choose from
								if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 8)
								{
									System.out.println("You are playing with "+input+" stocks");
									userGen_stockList_Size = Integer.parseInt(input);
									validInput = true;
								}
								else
								{
									//printing out error message
									System.out.println("You are only allowed up to 8 stocks\n");
								}
							}
							
							//ensuring valid input
							if (!validInput)
							{
								System.out.println("Please make sure the # of stocks you want to play with is a positive integer from 1 to 8");
								System.out.println("How many stocks do you want to play with?");
								input = sc.readLine();	
							}
						} while(!validInput);
							
						//creating the array with user-specified dimensions
						userGeneratedStocks = new String[userGen_stockList_Size];
						
						
						//for each stocks, request user to enter a name
						for (int i = 0; i < userGen_stockList_Size; i++)
						{
							System.out.print("Enter your own stock names: ");
							
							input = sc.readLine();
							do {
								//setting boolean-gates to false
								validInput = false;
								stockFound = false;
							
								//setting restrictions for stock-symbol names to 3-7 to make it more realistic
								if (input.length() >= 3 && input.length() <=7 )
								{
									//Counter used to make sure stock symbol input is valid
									violationCounter = 0;
								
									for (int a = 0; a < input.length(); a++)
									{
										//Making sure the user ONLY entered characters, by referencing the ASCII value of each individual character
										if (!(input.charAt(a) >= 97 && input.charAt(a) <= 122 || input.charAt(a) >= 65 && input.charAt(a) <= 90))
										{
											violationCounter++;
										}
									}
								
									//If the input is valid
									if (violationCounter == 0)
									{
										if (userGeneratedStocks[0] != null)
										{
											input = input.toUpperCase();
											
											//making sure the stock name isn't repeated
											for (int x = 0; x < userGeneratedStocks.length; x++)
											{
												if (input.equals(userGeneratedStocks[x]))
												{
													stockFound = true;
												}
											}
										}
										
										//if stock name is repeated
										if (stockFound)
										{
											System.out.println("That stock name already continues");
										}
										
										//if stock name is unique
										else
										{
											input = input.toUpperCase();
											validInput = true;
											userGeneratedStocks[i] = input;
											System.out.println("You have chosen the name: "+input);
										}
									}
								}
								//error message 1, for incorrect input
								if (!validInput && !stockFound)
								{
									System.out.println("Please make sure your stock is only letters, 3-7 chars long");
									
								}
								//error message 2, for duplicate stocks
								else if (!validInput && stockFound)
								{
									System.out.println("That stock already exist");
								}
								
								//if error message, then prompts user to try again
								if (!validInput)
								{
									System.out.print("Enter your own stock names: ");
									input = sc.readLine();
								}
								
							} while (!validInput);
						}
						//creating the array.
						preGen_or_CustomStocks_Arr = new String[userGen_stockList_Size];
						
						
						//If you have your own custom stocks
						System.out.println("The stocks that you have chosen are:\n");
						for (int i = 0; i < userGen_stockList_Size; i++)
						{
							System.out.println("> "+userGeneratedStocks[i]);
							
							//this line is necessary to converge if non pre-generated OR custom into one array
							preGen_or_CustomStocks_Arr[i] = userGeneratedStocks[i];
						}
						System.out.println("\n");
					}
					
					else
					{
						//if the user requested for pre-generated stocks
						System.out.println("Your pre-generated stocks are:");
						
						for (int i = 0; i < possibleStockNames.length; i++)
						{
							//printing out the pre-generated stock symbols
							System.out.println("> "+possibleStockNames[i]);
						}
						preGen_or_CustomStocks_Arr = new String[userGen_stockList_Size];
						for (int i = 0; i < userGen_stockList_Size; i++)
						{

							//this line is necessary to converge if non pre-generated OR custom into one array
							preGen_or_CustomStocks_Arr[i] = possibleStockNames[i];
						}
					}
					
					
					
					//prompting user for the duration of the stock challenge
					System.out.println("\nHow long do you want the challenge to last?");
					System.out.println("Enter a number from 10 - 30, or press 'd' for default (20 days)\n");
					validInput = false;
					marketLength = -1;
					
					//finding the length of the stock market
					//ensuring the user enters exclusively valid input
					do
					{
						System.out.print("> ");
						input = sc.readLine();
						input = input.toUpperCase();
						if (isNum(input))
						{
							//setting restrictions to the length of the stock market
							if (Integer.parseInt(input) >= 10 && Integer.parseInt(input) <= 30)
							{
								validInput = true;
								System.out.println("\nThe Stock Market Challenge length is: "+(input)+" days\n");
								marketLength = Integer.parseInt(input);
							}
						}
						
						//default case
						if (input.equals("D"))
						{
							System.out.println("Setting days to the default: 20 days");
							validInput = true;
							marketLength = 20;
						}
						
						else
						{
							//error message
							System.out.println("Make sure your input is from 10 - 30");
						}
						
					} while(!validInput);

					
					//initializing int startingCash to -1, which will be properly initialized later
					startingCash = -1;
					
					//ensuring the input is valid
					validInput = false;
					do
					{
						//prompting the user for how much money they want to start with
						System.out.println("How much money do you want to start with?");
						System.out.print("> ");
						input = sc.readLine();
						if (isNum(input))
						{
							//Setting the restrictions to the starting cash inputed
							if (Integer.parseInt(input) >= 100 && Integer.parseInt(input) <= 10000)
							{
								startingCash = Integer.parseInt(input);
								System.out.printf("The starting cash is $%,d\n",startingCash);
								validInput = true;
							}
							
						}
						
						if (!validInput)
						{
							//if error message, prints error message
							System.out.println("Make sure $ input is number from 100 to 10000\n");
						}
					} while (!validInput);
					
					
					//properly setting balance of each individual player to be the starting cash
					for (int i = 0; i < balance.length; i++)
					{
						balance[i] = startingCash;
					}
					
					//exclusive if they picked the AI, if they want to see its holdings
					seeAI_Transactions = false;
					if (ifAI)
					{
						//setting viewable AI transactions to false by default
						
						validInput = false;
						System.out.println("Do you want to see the AI's holdings throughout the challenge?");
						do {
							//prompting user if they should be able to see the AI's holdings, if they have chosen the AI
							
							System.out.println("Type Y/Yes for yes, N/No for no:");
							System.out.print("> ");
							input = sc.readLine();
							input = input.toUpperCase();
							
							//if input is yes, they will be able to see holdings of AI
							if (input.equals("YES") || input.equals("Y"))
							{
								
								//setting boolean values to give access to AI holdings
								System.out.println("You will be able to see the holdings of the AI");
								validInput = true;
								seeAI_Transactions = true;
							}
							//if input is no, they will be able to see holdings of AI
							else if (input.equals("NO") || input.equals("N"))
							{
								
								//setting boolean values to not give access to AI holdings
								System.out.println("You will be able to see the holdings of the AI");
								validInput = true;
								seeAI_Transactions = false;
							}
							
							if (!validInput)
							{
								//error message, prompted to go again
								System.out.println("Try again if you want to see the AI's holdings throughout the challange");	
							}
						} while (!validInput);
					}
					//starting the stock challenge
					System.out.println("\n\n\nWelcome to the stock market challenge!");
					System.out.println("The challenge will last for "+marketLength+" days");
					System.out.println("Each participant will be given $"+startingCash+ " each!");
					
					//initializing the arrays, now that the user has given proper input for the legal parameters
					stockValueGeneration = new int[userGen_stockList_Size][marketLength];
					coversionArray = new int[marketLength];
					for (int i = 0; i < userGen_stockList_Size; i++)
					{
						
						//calling fillstockvalues method to fill the array with values determined by a partial-random algorithm
						fillStockValues(coversionArray);
						
						for (int a = 0; a < marketLength; a++)
						{
							//Storing all stocks values in a single 2d array, by copying the values of conversionArray => stockValueGeneration
							stockValueGeneration[i][a] = coversionArray[a];
						}
					}
					
				
				
					//generating array to store user sell and buy stocks
					
					stockDataArr = new int[userGen_stockList_Size][NAMES_LENGTH][HOLDINGS_AND_VALUE];
					
					
					for (int i = 0; i < userGen_stockList_Size; i++)
					{
						for (int a = 0; a < playerCount; a++)
						{
							for (int b = 0; b < HOLDINGS_AND_VALUE; b++)
							{
								//setting the values to be 0, signifying the user has not bought any stocks yet
								stockDataArr[i][a][b] = 0;
							}
						}
					}
					
					
					
					//START of the actual game
					
					
					
					
					//creating arrays, to be pass-by referenced in methods used by the AI to determine accurate enter and exit points
					cashAllocation = new int[preGen_or_CustomStocks_Arr.length];
					stockIn = new int[preGen_or_CustomStocks_Arr.length];
					sellingChance = new int[preGen_or_CustomStocks_Arr.length];
					//setting AI name:
					if (ifAI)
					{
						names[2] = "AI";
					}
					
					
					//for sellingChance[], at the base, the stocks have a 50/50 chance of selling or holding.
					for (int u = 0; u < sellingChance.length; u++)
					{
						sellingChance[u] = 50;
					}
					
					//looping through per day
					for (int i = 0; i < marketLength; i++)
					{
						//printing out the day
						System.out.println("\nDay "+(i+1)+"\n");
						System.out.println("_________________");
						
						//per player
						for (int a = 0; a < humanPlayerCount; a++)
						{
							
							
							//printing out the player's name and tells them it is their turn
							System.out.printf("\n[Player %s's turn]\n",names[a]);
							
							//setting this boolean value to be true, when it turns false the next player's turn commences
							playerTurnInProgress = true;
							do
							{
								
								//displaying all the options for the player to choose from
								System.out.println("\n(1) Choose a stock to look");
								System.out.println("(2) Go to next turn / next player ");
								System.out.println("(3) View Balance & Holdings\n");
								
								
								//creating a do-while loop to ensure the user enters valid input
								do
								{
									choiceNum = -1;
									System.out.print("> ");
									input = sc.readLine();
									if (isNum(input))
									{
										if (Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= 3)
										{
											choiceNum = Integer.parseInt(input);
											validInput = true;
										}
									}
									if (!validInput)
									{
										//error message; prompts to try again
										System.out.println("Make sure your input is a number from 1 to 3");
										System.out.print(">");
										input = sc.readLine();
									}
								} while (!validInput);
								
								
								//For the individual choice numbers, allows them to view their options
								switch (choiceNum) {
								//utilizing a switch and case, as any contingencies are already sorted out, and a case and switch would be the easiest to use
									case 1:
										
										//prompting user to choose a stock
										System.out.println("\nChoose a stock: ");
										for (int x = 0; x < userGen_stockList_Size; x++)
										{
											
											//displays all the stocks the user can choose form
											System.out.printf("(%d) %s\n",(x+1),preGen_or_CustomStocks_Arr[x]);
										}

										//prompts them to pick a stock either using the ticker symbol OR the coordinating number
										System.out.print("Pick a stock using ticker symbol or coordinating number: \n");
										
										//subChoice, referring to if they picked 1 or 2, they will have more options to pick from
										subChoice = -1;
										do
										{
											//prompting the user to choose a stock
											validInput = false;
											System.out.print(">");
											input = sc.readLine();
											if (isNum(input))
											{
												if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < userGen_stockList_Size+1)
												{
													//confirming the choice of the stock the user selected
													//subchoice refers to an index in the stockData OR stockGenData array, and it must be =-1 as indexes start at 0
													subChoice = Integer.parseInt(input)-1;
													System.out.println("You have selected "+preGen_or_CustomStocks_Arr[subChoice]);
													validInput = true;
												}
											}
											else
											{
												//setting input to capital, as stocks in the array are capital, and so are in real life
												input = input.toUpperCase();
												for (int w = 0; w < userGen_stockList_Size; w++)
												{
													//Loops through all the stocks, checks if the stock symbol entered is an actual stocks
													if (preGen_or_CustomStocks_Arr[w].equals(input))
													{
														//if stocks exists, outputs a confirmation message
														System.out.println("You have selected "+preGen_or_CustomStocks_Arr[w]);
														subChoice = w;
														validInput = true;
													}
												}
											}
											
											//if error message
											if (!validInput)
											{
												//prompts user to try again
												System.out.println("Make sure your input is either a number in range: ");
												
												//tells the user the min and max values they can enter if they choose to enter a coordinating stock number
												System.out.println(1 +" to " + (userGen_stockList_Size+1));
												System.out.println("Or the coordinating stock symbol");
											}
										} while (!validInput);
										
		
										
										
										//printing out the stock graph of the stock they have chosen
										System.out.println("\n\nStock Graph: "+preGen_or_CustomStocks_Arr[subChoice]);
										
										//printing out the day of the stock
										System.out.println("Day "+(i+1));
										System.out.println("_____________________");
										
										//calling the printGraphPerDay to print the value of the stock, on the day
										printGraphPerDay(stockValueGeneration[subChoice], (i+1));
										
										
										//now they have seen their stock they have chosen, they can pick from buying, selling, or returning to the menu
										do
										{
											superSubChoice = -1;
											validInput = false;
											System.out.println("\n(1) [Buy shares  ]");
											System.out.println("(2) [Sell Shares ]");
											System.out.println("(3) [Menu        ]");
											System.out.print("> ");
											input = sc.readLine();
											
											
											//using a switch and case to auto evaluate the input, and decide the coordinating choice
											if (isNum(input))
											{
												
												switch (Integer.parseInt(input))
												{
												case 1:
													System.out.println("You have selected: Buy shares");
													superSubChoice = 1;
													validInput = true;
													break;
												case 2:
													System.out.println("You have selected: Sell shares");
													superSubChoice = 2;
													validInput = true;
													break;
												case 3:
													System.out.println("You have selected: Return to menu");
													superSubChoice = 3;
													validInput = true;
													break;
												}
											}
											
											//if error message
											if (!validInput)
											{
												//prompting user to try again
												System.out.println("Please enter an input from 1 to 3");
											}
										} while(!validInput);
										
										//if they choose to buy
										if (superSubChoice == 1)
										{
											
											//printing out the current statistics of the stock they choose to buy
											System.out.printf("[%s]\n","Statistics:");
											System.out.printf("Total Balance: $%d \n",balance[a]);
											System.out.printf("Current Stock Price: $%d \n",stockValueGeneration[subChoice][i]);
											System.out.printf("Current shares of %S held: %d \n",preGen_or_CustomStocks_Arr[subChoice],stockDataArr[subChoice][a][0]);
											
											//ensuring they have a valid input
											validInput = false;
											do
											{
												//prompting them to enter a # of shares to purchase
												System.out.println("Enter # of shares to buy:");
												System.out.print("> ");
												input = sc.readLine();
												if (isNum(input)) 
												{
													System.out.println();
													//If valid input is a legal number and they aren't exceeding the limit of the account
													if (Integer.parseInt(input) > 0 && Integer.parseInt(input) * stockValueGeneration[subChoice][i] <= balance[a]) 
													{
														//purchase goes through: 
														//parsing the input (string) to tempValue (integer)
														tempValue = Integer.parseInt(input);
														
														//subtracting the money they payed with from their balance
														balance[a] -= tempValue * stockValueGeneration[subChoice][i];
														
														//adding the tempValue to the shares they have in that stock
														stockDataArr[subChoice][a][0] += tempValue;
														
														//setting the value of that stock to the value in the graph
														stockDataArr[subChoice][a][1] = stockValueGeneration[subChoice][i];
														
														//Outputting a confirmation message
														System.out.println("You have purchased: ");
														System.out.println(stockDataArr[subChoice][a][0]+ " share of: "+preGen_or_CustomStocks_Arr[subChoice]);
														System.out.println("At the current market price of: "+stockValueGeneration[subChoice][i]);
														System.out.println("For a total of: "+stockValueGeneration[subChoice][i]*tempValue);
														//valid input
														validInput = true;
													}
													else
													{
														//if error; (Limit exceeded), then outputs error message
														System.out.println("The current share price * # of shares cannot exceed your balance and must be greater than 0");
													}
												}
												if (!validInput)
												{
													//prompts user to try again
													System.out.println("Please enter a number of shares to purchase");
												}
											} while(!validInput);
										}
										
										//if they choose to sell
										else if (superSubChoice == 2)
										{	
											validInput = true;
											//if they own zero shares
											if (stockDataArr[subChoice][a][0] == 0)
											{
												//outputs an error that they don't own any shares
												System.out.println("You don't know any shares of: "+preGen_or_CustomStocks_Arr[subChoice]);
												
											}
											else
											{
												//if the player at this turn owns shares from the stock, then outputs the statistics, as well as the number of shares they can purchase
												System.out.println("Of the stock: "+preGen_or_CustomStocks_Arr[subChoice]+", you own "+ stockDataArr[subChoice][a][0]+" shares ");
												
												//Tells them the current value if they sell all their shares
												System.out.println("At the current value, this amount of shares comes out to be: " + (stockValueGeneration[subChoice][i] * stockDataArr[subChoice][a][0]));
											}
											
											
											//if they have valid input
											if (validInput && stockDataArr[subChoice][a][0] != 0)
											{	validInput = false;
												do
												{
													//prompts them to enter a number of shares they want to sell
													System.out.println("How many shares do you want to sell? Enter 0 to not sell any shares");
													System.out.print(">");
													input = sc.readLine();
													
													//checking if the input is a number or not
													if (isNum(input))
													{
														
														//parsing input (string) to tempValue (integer)
														tempValue = Integer.parseInt(input);
														if (tempValue > 0)
														{
															
															if (tempValue <= stockDataArr[subChoice][a][0])
															{
																//putting out the confirmation that the sell went through
																System.out.println("Selling "+tempValue+ "shares of "+preGen_or_CustomStocks_Arr[subChoice]+":");
																
																//adding the value of the share sale to the balance of the player
																balance[a] += tempValue * stockValueGeneration[subChoice][i];
																
																//removing the shares sold from their holdings
																stockDataArr[subChoice][a][0] -= tempValue;
																
																//confirmation message
																System.out.println("You now own: "+stockDataArr[subChoice][a][0] +" shares");
																System.out.println("Your new balance is: "+balance[a]);
																
																//therefore is valid input
																validInput =true;
															}
															else
															{
																//error message; player does not own that many shares and therefore cannot sell that many shares
																System.out.println("You do not own that many shares!");
																System.out.println("You only own "+stockDataArr[subChoice][a][0]+" shares");
															}
															
														}
														
														//if player decides not to sell any shares
														else if (tempValue == 0)
														{
															//confirmation message that they are not selling any shares
															System.out.println("You are not selling any shares.");
															validInput = true;
														}
														else
														{
															//error message; if player does not enter a positive number
															System.out.println("Input must be a postive number");
														}
													}
													else
													{
														//error message; if input is not a number
														System.out.println("Make sure the input is an number");
													}
													
													//if not valid input, the player is prompted again
													
												} while (!validInput);
											}
										}
										
										
										//going to menu if player decides to leave that screen
										else
										{
											System.out.println("Going to menu: ");
										}
										break;
									case 2:
										//going to the next turn for the second case in the menu
										System.out.println("Commencing to next player / turn");
										playerTurnInProgress = false;
										break;
									case 3:
										
										//printing out the balance as well as current holdings of the player
										System.out.printf("Balance of player: %s is $%d\n",names[a],balance[a]);
										System.out.println("\nHoldings:");
										System.out.println("\n\n");
										for (int v = 0; v < stockValueGeneration.length; v++)
										{
											//will not print out the holdings if zero holdings
											if (stockDataArr[v][a][0] != 0)
											{
												System.out.print("Stock: ");
												System.out.printf("%6s",preGen_or_CustomStocks_Arr[v]);
												System.out.println("  Shares invested: "+ (stockDataArr[v][a][0])+" ($"+(stockDataArr[v][a][0])*stockValueGeneration[v][i]+")");
											}
										}
										break;
								}
							} while (playerTurnInProgress);
								
							
						}
						
						//if the user opted to play with an AI, then tells user it is the AI's turn
						if (ifAI)
						{
							artificialIntelligenceMain(cashAllocation, stockIn,stockValueGeneration,startingCash, i, stockDataArr, balance, playerCount,preGen_or_CustomStocks_Arr,sellingChance);
							if (seeAI_Transactions)
							{
								System.out.println("[AI's turn]");
								
								//calling the aritificalIntelligenceMain, acting as the main method for the AI program
								
								System.out.println("___________\n\n");
								
								//if marginal account for overdraft purposes, does not effect final score
								if (balance[2] < 0)
								{

									System.out.println("Charged Margin Account: $"+ (-balance[2]));
								}
								else
								{

									System.out.println("Account Balance : $"+ (balance[2]));
								}
								System.out.println("\nHoldings:");
								System.out.println("\n\n");
								for (int v = 0; v < stockValueGeneration.length; v++)
								{
									System.out.print("Stock: ");
									System.out.printf("%6s",preGen_or_CustomStocks_Arr[v]);
									System.out.println("  Shares invested: "+ (stockDataArr[v][2][0])+" ($"+(stockDataArr[v][2][0])*stockValueGeneration[v][i]+")");
								}
								System.out.println("\n\n");
							}
						}
					}
					
					
					//game is over, calculating total score of each player
					totalScore = new int[playerCount];
					for (int i = 0; i < playerCount; i++)
					{
						totalScore[i] = 0;
						for (int x = 0; x < preGen_or_CustomStocks_Arr.length; x++)
						{
							//Calculating total score with the balance
							totalScore[i] += stockDataArr[x][i][0] * stockValueGeneration[x][marketLength-1];
							
						}
						totalScore[i] += balance[i];
					}
					if (ifAI)
					{
						for (int x = 0; x < preGen_or_CustomStocks_Arr.length; x++)
						{
							//Calculating total score with the balance
							totalScore[playerCount-1] += stockDataArr[x][2][0] * stockValueGeneration[x][marketLength-1];
							
							
						}
						totalScore[playerCount-1] += balance[2] - balance[0];
					}
					
					//creating a 2d array with both player names and their score
					highScoreArr = new String[playerCount][SCORE_AND_NAME];
					
					
					
					//looping through and assigning the highScoreArr values to the names and scores, to be sorted
					for (int i = 0; i < playerCount; i++)
					{
						//assignment designed for human player
						highScoreArr[i][0] = names[i];
						highScoreArr[i][1] = Integer.toString(totalScore[i]);
						
						if (ifAI && i == 1 && humanPlayerCount == 1)
						{
							highScoreArr[i][0] = names[2];
							highScoreArr[i][1] = Integer.toString(totalScore[playerCount-1]);
						}
						else if (ifAI && i == 2 && humanPlayerCount == 2)
						{
							highScoreArr[i][0] = names[2];
							highScoreArr[i][1] = Integer.toString(totalScore[playerCount-1]);
						}
					}
					
					//if AI toggled on, then the highScoreArr is assigned as so
					
					
			
					
					
					
					//utilizing bubble sort to sort through the array
					updateHighScoreFileAndPrint(highScoreArr, highScoreFile);
					
					System.out.println("Press ENTER to return to start menu:");
					sc.readLine();
					//calling method to 
					
				}
				//end main code
				
				//closing the objects
				
				
			} catch (IOException e) {
				
				//Catching errors
				System.out.println("Unexpected error reading file "+highScoreFile+" "+ e);
			}	
		} while (true);
		
	}
	}
