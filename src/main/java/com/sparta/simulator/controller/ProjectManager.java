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
            simulator = new Simulator(view.getUserTime());
            simulator.run();
            fullCenters = simulator.getFullCenters();
            openCenters = simulator.getOpenCenters();
            waitingList = simulator.getWaitingList();
            totalTrainees = simulator.getTotalTrainees();

            view.AllOutputCentres(openCenters,fullCenters);
//                view.displayAllWaitingCount(waitingList);
            view.AllCurrentlyTraining(totalTrainees);

            executeProgram=view.continueCheck();
        }
    }

}
