package com.sparta.simulator.controller;

import com.sparta.simulator.model.CourseType;
import com.sparta.simulator.model.Intake;
import com.sparta.simulator.model.Simulator;
import com.sparta.simulator.view.View;
import static com.sparta.simulator.model.main.Main.logger;

public class ProjectManager {

    View view = new View();
    Simulator simulator;

    // running the simulation
    public void run(){
        view.introduction();
        boolean executeProgram=true;
        // execute program is checked at the end of a simulation
        while (executeProgram) {
            logger.info("entered loop of execution");
            boolean iterateMonths= view.iterateEveryMonthCheck();
            try {
                // delay implemented to remove issue with thread running two methods at once for some reason
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // a simulator will be created with a number of months taken from the user
            simulator = new Simulator(view.getUserTime());
            logger.info("user time fetched and simulator initiated");
            Intake intake= simulator.getIntake();
            // loop to execute the program for the desired number of months
            while(simulator.getCurrentMonth()<simulator.getTotalDuration()){
                // simulator run causes the simulation to iterate for one month
                logger.info("entering month render");
                simulator.run();
                logger.info("assumed operation of one full month");
                if (iterateMonths){
                    logger.info("entering readout for iterative months data");
                    displayMonthoutput(intake);
                    logger.info("returned from month readout");
                }
            }
            System.out.println("____________Final readings_________");
            logger.info("entering final month readout");
            displayMonthoutput(intake);
            logger.info("returned from final month readout");

            executeProgram=view.continueCheck();
            logger.debug("user has chosen " + executeProgram + " for loop again request");
        }
    }
    //
    public void printSection(Intake intake, String sectionName) {
        logger.info("readout for a centre");
        view.centreDisplay(intake.getCentreNumByType(sectionName), intake.getFullCentreNumByType(sectionName));
        logger.info("readout for closed centres");
        view.centreClosed(intake.getClosedCentresNumByType(sectionName));

        logger.debug("current trainee count, if >0 should display readout: " + intake.getCentreNumByType(sectionName));
        if (intake.getCentreNumByType(sectionName) != 0) {
            logger.info("readout for trainee count");
            view.centreTrainees(intake.getNumTraineesByCentreType(sectionName));
        }
    }
    public void writeToFileCentres(Intake intake, String centreName){
        Writer.writeToFile("[" + simulator.getCurrentMonth() + "," + centreName + ","
                + intake.getCentreNumByType(centreName) + "," + intake.getFullCentreNumByType(centreName) + ","
                + intake.getClosedCentresNumByType(centreName) + intake.getNumTraineesByCentreType(centreName) + ","
                + "]");
    }
    public void writeToFileTechCentres(Intake intake, CourseType s){
        Writer.writeToFile("["  + simulator.getCurrentMonth() + "," + s .getCourseName() + ","
                + intake.getTechCentresNumByType(s) + "," + intake.getFullTechCentresNumByType(s) + "," +
                intake.getClosedTechCentreNumByType(s) + intake.getTechCentresTraineeNumByType(s) + "," + "]");
    }
    public void writeToFileClientData(Intake intake){
        int satisfiedClientNum = intake.getHappyList().size();
        int unsatisfiedClientNum = intake.getUnHappyList().size();
        int currentClientNum = intake.getClientList().size();
        int closedCentres = intake.getClosedCentresNum();
        int openCentres = intake.numOfOpenCentres();
        int fullCentresNum = intake.numOfFullCentres();
        Writer.writeToFile("{" + "runtime_in_years:" + ((simulator.getCurrentMonth()-2)/12) + "," + "clients:" + currentClientNum + "," + "satisfied:" + satisfiedClientNum + ","
                + "unsatisfied:" + unsatisfiedClientNum + "," + "openCentres:" + openCentres + ","
                + "closed_centres:" + closedCentres + "," + "fullCentresNum:" + fullCentresNum + "}"  );
    }
    public void displayMonthoutput(Intake intake){
        System.out.println("CURRENT MONTH :" + (simulator.getCurrentMonth()-1));
        if (intake.getCentreNumByType("traininghub")!=0||intake.getFullCentreNumByType("traininghub") != 0 || intake.getClosedCentresNumByType("traininghub") !=0 ) {
            System.out.println("______________Training hubs__________");
            printSection(intake, "traininghub");
//            writeToFileCentres(intake, "traininghub");
        }
        if (intake.getCentreNumByType("bootcamp")!=0||intake.getFullCentreNumByType("bootcamp") != 0 || intake.getClosedCentresNumByType("bootcamp") !=0 ) {
            System.out.println("______________Boot camps__________");
            printSection(intake, "bootcamp");
//            writeToFileCentres(intake, "bootcamp");
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
//            writeToFileTechCentres(intake, s);
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
        writeToFileClientData(intake);

        view.waitForUser();
    }

}
