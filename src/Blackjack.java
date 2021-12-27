//play a few games and see if percentage thing works
//check dealer win vs player win in hold hand

import java.util.Scanner;

public class Blackjack {

    //create some pieces used across methods
    static Scanner scanner = new Scanner (System.in);
    static int playerTotal = 0;
    static P1Random randomNum = new P1Random();
    static int randCardNum;
    static int whatToDo;

    //method for what card is dealt to the player
    public static void cardDealtForPlayer ()
    {

        randCardNum = randomNum.nextInt(13) +1;

        //stating card at startup
        if ( randCardNum == 1 )
        {
            System.out.println( "\nYour card is a ACE!" );
            playerTotal += 1;
        }

        else if ( randCardNum == 11 )
        {
            System.out.println( "\nYour card is a JACK!" );
            playerTotal += 10;
        }

        else if ( randCardNum == 12 )
        {
            System.out.println( "\nYour card is a QUEEN!" );
            playerTotal += 10;
        }

        else if ( randCardNum == 13 )
        {
            System.out.println( "\nYour card is a KING!" );
            playerTotal += 10;
        }

        else
        {
            System.out.println( "\nYour card is a " + randCardNum + "!" );
            playerTotal += randCardNum;
        }

    }

    //method that prints out the menu
    public static void menu ()
    {
        System.out.println( "\n1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n" );
        System.out.print( "Choose an option: ");
        whatToDo = scanner.nextInt();
    }

    //method that's the actual card game
    public static void main (String [] args )
    {
        //create pieces
        int gameNumber = 1;
        int dealerTotal;
        int numDealerWins = 0;
        int numPlayerWins = 0;
        int numTies = 0;
        boolean gameIsGoing = true;

        //lines that appear with each game
        System.out.println("START GAME #" + gameNumber );
        cardDealtForPlayer();
        System.out.print( "Your hand is: " + playerTotal + "\n");

        while ( gameIsGoing )
        {
            menu();

            //player chooses to get another card
            if ( whatToDo == 1 )
            {
                cardDealtForPlayer();
                System.out.print( "Your hand is: " + playerTotal + "\n" );

                //player wins because hand total is 21
                if ( playerTotal == 21 )
                {
                    System.out.println( "\nBLACKJACK! You win!" );
                    numPlayerWins++;
                    gameNumber++;
                    playerTotal = 0;

                    //lines that appear with each game
                    System.out.println("\nSTART GAME #" + gameNumber );
                    cardDealtForPlayer();
                    System.out.print( "Your hand is: " + playerTotal + "\n" );
                }

                //player loses because hand total is over 21
                else if ( playerTotal > 21 )
                {
                    System.out.println( "\nYou exceeded 21! You lose." );
                    numDealerWins++;
                    gameNumber++;
                    playerTotal = 0;

                    //lines that appear with each game
                    System.out.println("\nSTART GAME #" + gameNumber );
                    cardDealtForPlayer();
                    System.out.print( "Your hand is: " + playerTotal + "\n" );
                }

                //game just keeps going
                else
                {

                }

            }

            //player chooses to hold hand
            if ( whatToDo == 2 )
            {
                dealerTotal = randomNum.nextInt( 11 ) + 16 ;
                System.out.println( "\nDealer's hand: " + dealerTotal );
                System.out.println( "Your hand is: " + playerTotal );

                //tie between player and dealer because scores are equal
                if ( playerTotal == dealerTotal )
                {
                    System.out.println( "\nIt's a tie! No one wins!" );
                    numTies++;
                    gameNumber++;
                    playerTotal = 0;
                    dealerTotal = 0;

                    //lines that appear with each game
                    System.out.println("\nSTART GAME #" + gameNumber );
                    cardDealtForPlayer();
                    System.out.print( "Your hand is: " + playerTotal + "\n" );
                }

                //player wins because dealer's hand is over value of 21
                else if ( dealerTotal > 21)
                {
                    System.out.println( "\nYou win!" );
                    numPlayerWins++;
                    gameNumber++;
                    playerTotal = 0;
                    dealerTotal = 0;

                    //lines that appear with each game
                    System.out.println("\nSTART GAME #" + gameNumber );
                    cardDealtForPlayer();
                    System.out.print( "Your hand is: " + playerTotal + "\n" );
                }

                else
                {
                    if ( (21 - dealerTotal) > (21- playerTotal) )
                    {
                        System.out.println( "\nYou win!" );
                        numPlayerWins++;
                        gameNumber++;
                        playerTotal = 0;
                        dealerTotal = 0;

                        //lines that appear with each game
                        System.out.println("\nSTART GAME #" + gameNumber );
                        cardDealtForPlayer();
                        System.out.print( "Your hand is: " + playerTotal + "\n" );
                    }

                    if ( (21 - dealerTotal) < (21 - playerTotal) )
                    {
                        System.out.println( "\nDealer wins!" );
                        numDealerWins++;
                        gameNumber++;
                        playerTotal = 0;
                        dealerTotal = 0;

                        //lines that appear with each game
                        System.out.println("\nSTART GAME #" + gameNumber );
                        cardDealtForPlayer();
                        System.out.print( "Your hand is: " + playerTotal + "\n" );
                    }

                }

            }

            //player chooses to print statistics
            if ( whatToDo == 3 )
            {
                double percentageWins = ( ( (double) numPlayerWins / (double) (gameNumber - 1) ) * 100 );

                System.out.println( "Number of Player wins: " + numPlayerWins );
                System.out.println( "Number of Dealer wins: " + numDealerWins );
                System.out.println( "Number of tie games: " + numTies );
                System.out.println( "Total # of games played is: " + (gameNumber - 1) );
                System.out.println( "Percentage of Player wins: " + String.format("%.1f",percentageWins) + "%\n" );
            }

            //player chooses to exit game
            if ( whatToDo == 4 )
            {
                gameIsGoing = false;
            }

            //player chooses input that is invalid because it's not an option for menu selection
            if ( whatToDo != 1 && whatToDo != 2 && whatToDo != 3 && whatToDo != 4 )
            {
                System.out.print( "\nInvalid input!\nPlease enter an integer value between 1 and 4.\n" );
            }

        }

    }

}