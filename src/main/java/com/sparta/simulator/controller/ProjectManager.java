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
            simulator = new Simulator(view.getUserTime());
            // TODO create two variants, one for one month with iterator, one for all
            Intake intake= simulator.getIntake();
                while(simulator.getCurrentMonth()<simulator.getTotalDuration()){
                    simulator.run();
                    if (iterateMonths){
                    System.out.println("______________Training hubs__________");
                    view.trainingHubsDisplay(intake.getCentreNumByType("traininghub"),intake.getFullTechCentresNumByType("traininghub"));
                    //view.trainingHubsClosed(intake.getClosedNumByType("traininghub"));
                    view.trainingHubTrainees(intake.getNumTraineesByCentreType("traininghub"));
                    System.out.println("______________Boot camps__________");
                    view.trainingHubsDisplay(intake.getCentreNumByType("bootcamp"),intake.getFullTechCentresNumByType("bootcamp"));
                    //view.trainingHubsClosed(intake.getClosedNumByType("bootcamp"));
                    view.trainingHubTrainees(intake.getNumTraineesByCentreType("bootcamp"));
                    for (String s : types) {
                        System.out.println("______________" + s + " Tech centres__________");
                        view.techCentresDisplay(intake.getTechCentresNumByType(s), intake.getFullTechCentresNumByType(s), s);
                        //   view.techCentresClosed(intak);
                        view.techCentreTrainees(intake.getTechCentresTraineeNumByType(s), s);
                    }
                    view.waitForUser();
                    }
                 }
            System.out.println("____________Final readings_________");
                System.out.println("______________Training hubs__________");
                view.trainingHubsDisplay(intake.getCentreNumByType("traininghub"),intake.getFullTechCentresNumByType("traininghub"));
                //view.trainingHubsClosed(intake.getClosedNumByType("traininghub"));
                view.trainingHubTrainees(intake.getNumTraineesByCentreType("traininghub"));
                System.out.println("______________Boot camps__________");
                view.trainingHubsDisplay(intake.getCentreNumByType("bootcamp"),intake.getFullTechCentresNumByType("bootcamp"));
                //view.trainingHubsClosed(intake.getClosedNumByType("bootcamp"));
                view.trainingHubTrainees(intake.getNumTraineesByCentreType("bootcamp"));
                for (String s : types) {
                    System.out.println("______________" + s + " Tech centres__________");
                    view.techCentresDisplay(intake.getTechCentresNumByType(s), intake.getFullTechCentresNumByType(s), s);
                    //   view.techCentresClosed(intak);
                    view.techCentreTrainees(intake.getTechCentresTraineeNumByType(s), s);
                }
            executeProgram=view.continueCheck();
        }
    }

}
