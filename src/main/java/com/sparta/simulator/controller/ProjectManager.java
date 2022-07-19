package com.sparta.simulator.controller;

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

        while (executeProgram) {
            boolean iterateMonths= view.iterateEveryMonthCheck();

            // TODO create two variants, one for one month with iterator, one for all
            simulator = new Simulator(view.getUserTime());
            simulator.run(); // currently runs all months and finishes
            fullCenters = simulator.getFullCenters();
            openCenters = simulator.getOpenCenters();
            waitingList = simulator.getWaitingList();
            totalTrainees = simulator.getTotalTrainees();

            view.AllOutputCentres(openCenters,fullCenters);
            view.displayAllWaitingCount(waitingList);
            view.AllCurrentlyTraining(totalTrainees);

            executeProgram=view.continueCheck();
        }
    }
    public void

}
