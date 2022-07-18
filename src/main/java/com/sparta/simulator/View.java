package com.sparta.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class View {
    //_____________________INPUTS_________________________
    // will return true if the user wishes to continue the simulation and false if they wish not to
    public boolean continueCheck(){
        return UserChoiceModule.simulateAgain();
    }

    // function will call to userChoice class, which will only let the user return a value of 1 or greater
    public int getUserTime(){
        int userTime;
        userTime = UserChoiceModule.getUserWeeks();
        return userTime;
    }
    //_____________________OUTPUTS__________________________
    public void introduction(){
        System.out.println("Welcome to the sparta simulator!!\n\n this program is designed to simulate operations at Sparta Global");
    }


    // requires a list of centres and those to have an isFull function that returns a boolean when no more trainees can be accepted
    public void displayOutputCentres(int totalCentres, int fullCentres){
        System.out.println("There are " + totalCentres + " of which " + fullCentres + " have full capacity");
    }

    // requires a list containing all trainees tha have not yet been assigned to a placement
    public void displayWaitingCount(int waitingTrainees){
        System.out.println(" there are currently " + waitingTrainees + " trainees waiting for a placement");
    }

    // requires a list of centres, and those centres to contain a list of trainees that are currently training there
    public void currentlyTraining( int traineeCount){
        System.out.println(" there  are currently " + traineeCount + " training at a centre");
    }

    public static class UserChoiceModule{
        public static int getUserWeeks(){
            // boolean to hold while loop
            boolean userChoosing=true;
            Scanner scanner = new Scanner(System.in);
            int userValue=1;
            while (userChoosing) {
                System.out.println("Please input an amount of months to use in the simulation");
                userValue= StringConverter.stringToInt(scanner.next()); // uses converter to only return a positive int, and loop refuses a null array.
                if (userValue <= 0){
                    System.out.println("cannot have 0 or negative weeks");
                    userChoosing=true;
                }
                else userChoosing=false;
            }
            return  userValue;
        }

        public static boolean simulateAgain(){
            boolean userChoosing=true;
            Scanner scanner = new Scanner(System.in);
            String input;
            boolean simulateAgain=true;
            while (userChoosing){
                System.out.println("Would you like to simulate again? (y or n)");
                input=scanner.next().toLowerCase(Locale.ROOT);
                switch (input){
                    case "y" : break;
                    case "n" : simulateAgain=false; break;
                    default:
                        System.out.println("Invalid input, please enter 'y' or 'n'");
                }
            }
            return simulateAgain;
        }

    }
    public static class StringConverter {
        public static int stringToInt(String inputString){
            // create a stringBuilder to be delivered
            StringBuilder stringBuilder= new StringBuilder(inputString.length());
            // set a default value to 0
            stringBuilder.append(0);
            char [] inputAsCharArray = inputString.toCharArray();
            // char value 48-57 are numerics , by using this it will not create a negative number
            for (char c : inputAsCharArray){
                if (c>= 48 && c<=57){
                    stringBuilder.append(c);
                }
            }
            return Integer.parseInt(stringBuilder.toString());
        }
    }
}
