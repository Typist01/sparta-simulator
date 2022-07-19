package com.sparta.simulator.controller;

import com.sparta.simulator.model.Intake;
import com.sparta.simulator.model.Simulator;
import com.sparta.simulator.view.View;

public class ProjectManager {
    View view = new View();
    Simulator simulator;

    public void run(){
        view.introduction();
        boolean executeProgram=true;
        int fullCenters;
        int openCenters;
        int waitingList;
        int totalTrainees;
        String[] types= {"Java","C#","Data","DevOps","Business"};
        while (executeProgram) {
            boolean iterateMonths= view.iterateEveryMonthCheck();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simulator = new Simulator(view.getUserTime());
            // TODO create two variants, one for one month with iterator, one for all
            Intake intake= simulator.getIntake();
                while(simulator.getCurrentMonth()<simulator.getTotalDuration()){
                    simulator.run();
                    if (iterateMonths){
                        System.out.println("CURRENT MONTH :" + (simulator.getCurrentMonth()-1));
                    System.out.println("______________Training hubs__________");
                    view.trainingHubsDisplay(intake.getCentreNumByType("traininghub"),intake.getFullTechCentresNumByType("traininghub"));
                    view.trainingHubsClosed(intake.getClosedCentresNumByType("traininghub"));
                    view.trainingHubTrainees(intake.getNumTraineesByCentreType("traininghub"));
                    System.out.println("______________Boot camps__________");
                    view.bootCampsDisplay(intake.getCentreNumByType("bootcamp"),intake.getFullTechCentresNumByType("bootcamp"));
                    view.bootCampsClosed(intake.getClosedCentresNumByType("bootcamp"));
                    view.bootCampTrainees(intake.getNumTraineesByCentreType("bootcamp"));
                    for (String s : types) {
                        System.out.println("______________" + s + " Tech centres__________");
                        view.techCentresDisplay(intake.getTechCentresNumByType(s), intake.getFullTechCentresNumByType(s), s);
                        view.techCentresClosed(intake.getClosedTechCentreNumByType(s),s);
                        view.techCentreTrainees(intake.getTechCentresTraineeNumByType(s), s);
                    }
                        System.out.println("_____________WAITING LIST_________________");
                    view.allWaitingCount(intake.getWaitingCount());
                    view.waitForUser();
                    }
                 }
            System.out.println("____________Final readings_________");
                System.out.println("______________Training hubs__________");
                view.trainingHubsDisplay(intake.getCentreNumByType("traininghub"),intake.getFullTechCentresNumByType("traininghub"));
                view.trainingHubsClosed(intake.getClosedCentresNumByType("traininghub"));
                view.trainingHubTrainees(intake.getNumTraineesByCentreType("traininghub"));
                System.out.println("______________Boot camps__________");
                view.bootCampsDisplay(intake.getCentreNumByType("bootcamp"),intake.getFullTechCentresNumByType("bootcamp"));
                view.bootCampsClosed(intake.getClosedCentresNumByType("bootcamp"));
                view.bootCampTrainees(intake.getNumTraineesByCentreType("bootcamp"));
                for (String s : types) {
                    System.out.println("______________" + s + " Tech centres__________");
                    view.techCentresDisplay(intake.getTechCentresNumByType(s), intake.getFullTechCentresNumByType(s), s);
                    view.techCentresClosed(intake.getClosedTechCentreNumByType(s),s);
                    view.techCentreTrainees(intake.getTechCentresTraineeNumByType(s), s);
                }
            System.out.println("_____________WAITING LIST_________________");
            view.allWaitingCount(intake.getWaitingCount());
            view.waitForUser();
            executeProgram=view.continueCheck();
        }
    }

}
