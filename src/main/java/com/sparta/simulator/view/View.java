package com.sparta.simulator.view;

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
        System.out.println("Would you like to\n" +
                "1. iterate per month and receive a full readout each month\n" +
                "2. complete the simulation and receive readouts only on the final month?");
        switch (UserChoiceModule.getOperationType() ){
            case 1 : return true;
            case 2 : return false;
            default: throw new RuntimeException();
        }

    }
    //_____________________OUTPUTS__________________________
    public void introduction(){
        System.out.println("Welcome to the sparta simulator!!\n\nthis program is designed to simulate operations at Sparta Global");
    }
    // requires a list containing all trainees tha have not yet been assigned to a placement
    public void displayAllWaitingCount(int waitingTrainees){
        System.out.println(" there are currently " + waitingTrainees + " trainees waiting for a placement");
    }
    public void displayWaitingType(int waitingTrainees, String type){
        System.out.println(" there are currently " + waitingTrainees + " " + type + "trainees waiting for a placement");
    }

    //________________**READOUTS FOR EACH CENTRE**_________________

    //___________GENERIC___________________
    public void displayAllOutputCentres(int totalCentres, int fullCentres){
        if (totalCentres==1){
            System.out.print("There is 1 training centre, ");
            if (fullCentres==1){
                System.out.println("but it is closed..");
            }
            else System.out.println(" it is not closed");

        }
        else System.out.println("There is a total of " + totalCentres + " training centres of which " + fullCentres + " have full capacity");
    }
    public void displayAllCurrentlyTraining(int traineeCount){
        System.out.println("There  are currently " + traineeCount + " people training at Sparta Global");
    }
    public void displayAllClosed (int closedCount){
        if (closedCount == 1){
            System.out.println(closedCount + " centre has been closed");
        }
        else
            System.out.println(closedCount + " centres have been closed");
    }

    //_________TRAINING HUB_________
    public void displayTrainingHubs(int totalCentres, int fullCentres){
        if (totalCentres==1){
            System.out.print("There is 1 training hub, ");
            if (fullCentres==1){
                System.out.println("it is full");
            }
            else System.out.println(" it is not full");

        }
        else System.out.println("There is a total of " + totalCentres + " training hubs of which " + fullCentres + " have full capacity");
    }
    public void displayTrainingHubTrainees(int traineeCount){
        System.out.println("There  are currently " + traineeCount + " people training at training hubs");
    }
    public void displayClosedTrainingHubs (int closedCount){
        if (closedCount == 1){
            System.out.println(closedCount + " training hub has been closed");
        }
        else
        System.out.println(closedCount + " training hubs have been closed");
    }

    // _______BOOTCAMP___________
    public void displayBootCamps(int totalCentres, int fullCentres){
        if (totalCentres==1){
            System.out.print("There is 1 boot camp, ");
            if (fullCentres==1){
                System.out.println("it is full");
            }
            else System.out.println(" it is not full");
        }
        else System.out.println("There is a total of " + totalCentres + " training hubs of which " + fullCentres + " have full capacity");
    }
    public void displayBootCampTrainees(int traineeCount){
        System.out.println("There  are currently " + traineeCount + " people training at boot camps");
    }
    public void displayClosedBootCamps (int closedCount){
        if (closedCount == 1){
            System.out.println(closedCount + " boot camp has been closed");
        }
        else
            System.out.println(closedCount + " boot camps have been closed");
    }

    //________TECH CENTRE_________
    public void displayTechCentres(int totalCentres, int fullCentres , String type){
        if (totalCentres==1){
            System.out.print("There is 1" + type + " tech centre of type ,");
            if (fullCentres==1){
                System.out.println("it is full");
            }
            else System.out.println(" it is not full");
        }
        else System.out.println("There is a total of " + totalCentres + " tech centres of type " + type + " of which " + fullCentres + " have full capacity");
    }
    public void displayTechCentreTrainees(int traineeCount , String type){
        System.out.println("There  are currently " + traineeCount + " people training at " + type + " tech centres");
    }
    public void displayClosedTechCentres (int closedCount, String type){
        if (closedCount == 1){
            System.out.println(closedCount + " "+ type + " tech centre has been closed");
        }
        else
            System.out.println(closedCount + " "+ type + " tech centres have been closed");
    }

    //__________________________INPUT FUNCTIONS_______________________________ called by functions above
    public static class UserChoiceModule{
        public static int getUserMonths(){
            // boolean to hold while loop
            boolean userChoosing=true;
            Scanner scanner = new Scanner(System.in);
            int userValue=1;
            while (userChoosing) {
                System.out.println("Would you like to\n" +
                        "1. iterate per month and receive a full readout each month\n" +
                        "2. complete the simulation and receive readouts only on the final month?");
                userValue= StringConverter.stringToInt(scanner.next()); // uses converter to only return a positive int, and loop refuses a null array.
                switch (userValue){
                    case 1 : userChoosing=false; break;
                    case 2 : userChoosing=false; break;
                    default:
                        System.out.println(" please check that you input a correct number");
                }
            }
            return  userValue;
        }
        public static int getOperationType(){
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