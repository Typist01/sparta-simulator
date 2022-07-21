package com.sparta.simulator.controller;

import com.sparta.simulator.model.CourseType;
import com.sparta.simulator.model.Intake;
import com.sparta.simulator.model.Simulator;
import com.sparta.simulator.view.View;

public class ProjectManager {
    View view = new View();
    Simulator simulator;

    public void printSection(){}

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
            Intake intake= simulator.getIntake();
                while(simulator.getCurrentMonth()<simulator.getTotalDuration()){
                    simulator.run();
                    if (iterateMonths){
                        System.out.println("CURRENT MONTH :" + (simulator.getCurrentMonth()-1));
                        if (intake.getCentreNumByType("traininghub")!=0||intake.getFullCentreNumByType("traininghub") != 0 || intake.getClosedCentresNumByType("traininghub") !=0 ) {
                            System.out.println("______________Training hubs__________");
                            view.centreDisplay(intake.getCentreNumByType("traininghub"), intake.getFullCentreNumByType("traininghub"));
                            view.centreClosed(intake.getClosedCentresNumByType("traininghub"));
                            if (intake.getCentreNumByType("traininghub")!=0) {
                                view.centreTrainees(intake.getNumTraineesByCentreType("traininghub"));
                            }
                        }
                        if (intake.getCentreNumByType("bootcamp")!=0||intake.getFullCentreNumByType("bootcamp") != 0 || intake.getClosedCentresNumByType("bootcamp") !=0 ) {
                            System.out.println("______________Boot camps__________");
                            view.centreDisplay(intake.getCentreNumByType("bootcamp"), intake.getFullCentreNumByType("bootcamp"));
                            view.centreClosed(intake.getClosedCentresNumByType("bootcamp"));
                            if (intake.getCentreNumByType("bootcamp")!=0) {
                                view.centreTrainees(intake.getNumTraineesByCentreType("bootcamp"));
                            }
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
            System.out.println("____________Final readings_________");
            if (intake.getCentreNumByType("traininghub")!=0||intake.getFullCentreNumByType("traininghub") != 0 || intake.getClosedCentresNumByType("traininghub") !=0 ) {
                System.out.println("______________Training hubs__________");
                view.centreDisplay(intake.getCentreNumByType("traininghub"), intake.getFullCentreNumByType("traininghub"));
                view.centreClosed(intake.getClosedCentresNumByType("traininghub"));
                if (intake.getCentreNumByType("traininghub")!=0) {
                    view.centreTrainees(intake.getNumTraineesByCentreType("traininghub"));
                }
            }
            if (intake.getCentreNumByType("bootcamp")!=0||intake.getFullCentreNumByType("bootcamp") != 0 || intake.getClosedCentresNumByType("bootcamp") !=0 ) {
                System.out.println("______________Boot camps__________");
                view.centreDisplay(intake.getCentreNumByType("bootcamp"), intake.getFullCentreNumByType("bootcamp"));
                view.centreClosed(intake.getClosedCentresNumByType("bootcamp"));
                if (intake.getCentreNumByType("bootcamp")!=0) {
                    view.centreTrainees(intake.getNumTraineesByCentreType("bootcamp"));
                }
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
            executeProgram=view.continueCheck();
        }
    }

}
