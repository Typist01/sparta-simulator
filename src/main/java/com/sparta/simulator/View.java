package com.sparta.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    public int getUserTime(){
        int userTime;
        userTime = UserChoiceModule.getUserWeeks();
        return userTime;
    }

    // requires a list of centres and those to have an isFull function that returns a boolean when no more trainees can be accepted
    public void displayOutputCentres(List<Centre> centres){
        int fullCentres=0;
        for (Centre c : centres){
            if (Centre.isfull){
            fullCentres++;
            }
        }
        System.out.println("There are " + centres.size() + " of which " + fullCentres + " have full capacity");
    }

    // requires a list containing all trainees tha have not yet been assigned to a placement
    public void displayWaitingCount(List<Trainee> waitingList){
        System.out.println(" there are currently " +waitingList.size() + " trainees waiting for a placement");
    }

    // requires a list of centres, and those centres to contain a list of trainees that are currently training there
    public void currentlyTraining( List<Centre> centres){
        int traineeCount=0;
        for (Centre c : centres){
            traineeCount+= centres.trainees.size();
        }
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
