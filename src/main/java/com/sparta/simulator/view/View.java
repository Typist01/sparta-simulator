package com.sparta.simulator.view;

import java.io.IOException;
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
        return UserChoiceModule.getUserMonths();
    }

    // this function will return true if the user wishes to iterate over every month
    public boolean iterateEveryMonthCheck(){
        return switch (UserChoiceModule.getOperationType()) {
            case 1 -> true;
            case 2 -> false;
            default -> throw new RuntimeException();
        };
    }
    public void waitForUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter anything to continue");
        scanner.nextLine();
    }
    //_____________________OUTPUTS__________________________
    public void introduction(){
        System.out.println("Welcome to the sparta simulator!!\n\nthis program is designed to simulate operations at Sparta Global");
    }
    // requires a list containing all trainees tha have not yet been assigned to a placement
    public void allWaitingCount(int waitingTrainees){
        System.out.println("there are currently " + waitingTrainees + " trainees waiting for a placement");
    }
    public void waitingType(int waitingTrainees, String type){
        System.out.println("there are currently " + waitingTrainees + " " + type + "trainees waiting for a placement");
    }

    //________________**READOUTS FOR EACH CENTRE**_________________

    //___________GENERIC___________________
    public void AllOutputCentres(int totalCentres, int fullCentres){
        if (totalCentres==1){
            System.out.print("There is 1 training centre, ");
            if (fullCentres==1){
                System.out.println("but it is closed..");
            }
            else System.out.println(" it is not closed");

        }
        else System.out.println("There is a total of " + totalCentres + " training centres of which " + fullCentres + " have full capacity");
    }
    public void AllCurrentlyTraining(int traineeCount){
        System.out.println("There  are currently " + traineeCount + " people training at Sparta Global");
    }
    public void AllClosed (int closedCount){
        if (closedCount == 1){
            System.out.println(closedCount + " centre has been closed");
        }
        else
            System.out.println(closedCount + " centres have been closed");
    }

    //_________TRAINING HUB_________
    public void trainingHubsDisplay(int totalCentres, int fullCentres){
        System.out.println("Training hubs: " + totalCentres + "\n" +
                "Full training hubs: " + fullCentres);
    }
    public void trainingHubTrainees(int traineeCount){
        System.out.println("Training hub total trainee count: " + traineeCount );
    }
    public void trainingHubsClosed (int closedCount){
            System.out.println("Training hubs closed: " + closedCount);
    }

    // _______BOOTCAMP___________
    public void bootCampsDisplay(int totalCentres, int fullCentres) {
        System.out.println("Boot camps: " + totalCentres + "\n" +
                "Full boot camps: " + fullCentres);
    }
    public void bootCampTrainees(int traineeCount){
        System.out.println("Boot camp total trainee count: " + traineeCount );
    }
    public void bootCampsClosed (int closedCount){
        System.out.println("Boot camps closed: " + closedCount);
    }

    //________TECH CENTRE_________
    public void techCentresDisplay(int totalCentres, int fullCentres , String type){
        System.out.println(type +" tech centres: " + totalCentres + "\n" +
                "Full "+type+" tech centres: " + fullCentres);
    }
    public void techCentreTrainees(int traineeCount , String type){
        System.out.println(type+ " tech centre total trainee count: " + traineeCount );
    }
    public void techCentresClosed (int closedCount, String type){
        System.out.println(type + " tech centres closed: " + closedCount);
    }

    //__________________________INPUT FUNCTIONS_______________________________ called by functions above
     static class UserChoiceModule{
        public static int getOperationType(){
            // boolean to hold while loop
            boolean userChoosing=true;
            Scanner scanner = new Scanner(System.in);
            int userValue=1;
            while (userChoosing) {
                System.out.println("""
                        Would you like to
                        1. iterate per month and receive a full readout each month
                        2. complete the simulation and receive readouts only on the final month?""");
                userValue= StringConverter.stringToInt(scanner.next()); // uses converter to only return a positive int, and loop refuses a null array.
                switch (userValue) {
                    case 1 -> userChoosing = false;
                    case 2 -> userChoosing = false;
                    default -> System.out.println(" please check that you input a correct number");
                }
            }
            return  userValue;
        }
        public static int getUserMonths(){
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
            leave: while (userChoosing){
                System.out.println("Would you like to simulate again? (y or n)");
                input=scanner.next().toLowerCase(Locale.ROOT);
                switch (input){
                    case "y" : break leave;
                    case "n" : simulateAgain=false; break leave;
                    default:
                        System.out.println("Invalid input, please enter 'y' or 'n'");
                }
            }
            return simulateAgain;
        }

    }
    static class StringConverter {
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
