package com.sparta.simulator;

import java.util.*;

public class Intake {
    Collection<Centre> trainingCentres = new ArrayList();

    private Queue<Trainee> waitingList = new LinkedList<>();

    public int getWaitingList() {
        return waitingList.size();
    }

    public void addCentre() {
        trainingCentres.add(new Centre());
    }


    public int numOfTotalTrainees() {
        int sum = 0;
        for (Centre centre : trainingCentres) {
            sum += centre.getNumOfTrainees();
        }
        return sum;
    }

    public int numOfFullCentres() {
        return getFullCentres().size();
    }

    public int numOfOpenCentres() {
        return getOpenCentres().size();
    }

	//Add new random trainees to the waiting list.
    public void addTraineeGroup() {

        Queue<Trainee> intakeList = new LinkedList<>();
        for (int i = 0; i < RandGenerator.randomTrainee(); i++) {
            intakeList.add(new Trainee());
        }
        for (Centre centre : trainingCentres) {
            if (!centre.isFull() && intakeList.size() > 0) {
                for (int i=0; i<RandGenerator.randomCenter(); i++){
                    if (!centre.isFull()){
                        centre.addTrainee(intakeList.remove());
                    }
                }
            }
        }
        for (Trainee t : intakeList){
            waitingList.add(t);
        }
    }



    public void addWaitingTraineesToCentre() {
        Random random = new Random();
        boolean allFull=false;
        while (waitingList.size() > 0 && !allFull) {
            allFull=true;
            for (Centre centre : trainingCentres) {
                if (!centre.isFull()){
                    allFull=false;
                    if (random.nextBoolean() && waitingList.size() > 0) {
                        centre.addTrainee(waitingList.remove());
                    }
                }
            }
        }
    }

    private List<Centre> getOpenCentres() {
        List<Centre> openCentres = new ArrayList();
        for (Centre centre : trainingCentres) {
            if (!centre.isFull()) {

                openCentres.add(centre);
            }
        }
        return openCentres;
    }

    private List<Centre> getFullCentres() {
        List<Centre> fullCentres = new ArrayList();
        for (Centre centre : trainingCentres) {
            if (centre.isFull()) {
                fullCentres.add(centre);
            }
        }
        return fullCentres;
    }
}
