package com.sparta.simulator.controller;

import com.sparta.simulator.model.CourseType;
import com.sparta.simulator.model.Intake;
import com.sparta.simulator.model.Simulator;
import com.sparta.simulator.view.View;

public class ProjectManager {
    View view = new View();
    Simulator simulator;

    // running the simulation
    public void run(){
        view.introduction();
        boolean executeProgram=true;
        // execute program is checked at the end of a simulation
        while (executeProgram) {

            boolean iterateMonths= view.iterateEveryMonthCheck();
            try {
                // delay implemented to remove issue with thread running two methods at once for some reason
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // a simulator will be created with a number of months taken from the user
            simulator = new Simulator(view.getUserTime());
            Intake intake= simulator.getIntake();

            // loop to execute the program for the desired number of months
            while(simulator.getCurrentMonth()<simulator.getTotalDuration()){
                // simulator run causes the simulation to iterate for one month
                simulator.run();
                if (iterateMonths){

                    displayMonthoutput(intake);
                }
            }
            System.out.println("____________Final readings_________");
            displayMonthoutput(intake);
            executeProgram=view.continueCheck();
        }
    }
    //
    public void printSection(Intake intake, String sectionName) {
        view.centreDisplay(intake.getCentreNumByType(sectionName), intake.getFullCentreNumByType(sectionName));
        view.centreClosed(intake.getClosedCentresNumByType(sectionName));
        if (intake.getCentreNumByType(sectionName) != 0) {
            view.centreTrainees(intake.getNumTraineesByCentreType(sectionName));
        }
    }
//    public void writeToFile(){
//
//    }
    public void displayMonthoutput(Intake intake){
        System.out.println("CURRENT MONTH :" + (simulator.getCurrentMonth()-1));
        if (intake.getCentreNumByType("traininghub")!=0||intake.getFullCentreNumByType("traininghub") != 0 || intake.getClosedCentresNumByType("traininghub") !=0 ) {
            System.out.println("______________Training hubs__________");
            printSection(intake, "traininghub");
//                            view.centreDisplay(intake.getCentreNumByType("traininghub"), intake.getFullCentreNumByType("traininghub"));
//                            view.centreClosed(intake.getClosedCentresNumByType("traininghub"));
//                            if (intake.getCentreNumByType("traininghub")!=0) {
//                                view.centreTrainees(intake.getNumTraineesByCentreType("traininghub"));
//                            }
        }
        if (intake.getCentreNumByType("bootcamp")!=0||intake.getFullCentreNumByType("bootcamp") != 0 || intake.getClosedCentresNumByType("bootcamp") !=0 ) {
            System.out.println("______________Boot camps__________");
            printSection(intake, "bootcamp");
//                            view.centreDisplay(intake.getCentreNumByType("bootcamp"), intake.getFullCentreNumByType("bootcamp"));
//                            view.centreClosed(intake.getClosedCentresNumByType("bootcamp"));
//                            if (intake.getCentreNumByType("bootcamp")!=0) {
//                                view.centreTrainees(intake.getNumTraineesByCentreType("bootcamp"));
//                            }
        }
        for (CourseType s : CourseType.values()) {
            if (intake.getTechCentresNumByType(s) !=0 || intake.getFullTechCentresNumByType(s) !=0 || intake.getClosedTechCentreNumByType(s) !=0) {
                System.out.println("______________" + s + " Tech centres__________");
                view.techCentresDisplay(intake.getTechCentresNumByType(s), intake.getFullTechCentresNumByType(s), s.getCourseName());
                view.techCentresClosed(intake.getClosedTechCentreNumByType(s), s.getCourseName());
                if (intake.getTechCentresTraineeNumByType(s) !=0) {
                    view.techCentreTrainees(intake.getTechCentresTraineeNumByType(s), s.getCourseName());
                }
            }
        }
        if (intake.getHappyList().size() !=0 || intake.getUnHappyList().size() != 0 || intake.getClientList().size() !=0){
            System.out.println("_______________CLIENTS_______________");
            view.allClients(intake.getClientList().size());
            view.happyClients(intake.getHappyList().size());
            view.sadClients(intake.getUnHappyList().size());
        }
        if (intake.getWaitingCount() != 0) {
            System.out.println("_____________WAITING LIST_________________");
            view.allWaitingCount(intake.getWaitingCount());
            for ( CourseType s : CourseType.values()){
                view.waitingType(intake.getTraineeNumByType(s),s.getCourseName());
            }
        }
        view.waitForUser();
    }


}
