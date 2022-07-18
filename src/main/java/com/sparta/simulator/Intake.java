package com.sparta.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Intake {
    Collection<Centre> trainingCentres = new ArrayList();
    RandGenerator randGenerator = new RandGenerator();

    private ArrayList<Trainee> waitingList = new ArrayList<>();

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
        for (int i = 0; i < randGenerator.randomTrainee(); i++)
            waitingList.add(new Trainee());
    }

    public int addTraineesToCentre(Centre centre, int numOfTrainees) {
        int intakeAmount;
        int randomCentreNum = randGenerator.randomCenter();
        if (randomCentreNum > numOfTrainees) {
			intakeAmount = numOfTrainees;
        } else
            intakeAmount = numOfTrainees;
        for (int i = 0; i < intakeAmount; i++) {
            if (!centre.isFull()) {    //add trainees if centre not full
                centre.addTrainee(new Trainee());
            } else {
                return (numOfTrainees - i);
                //extraTrainees = (numOfTrainees - i); // maybe create this variable in this class
            }
        }
        return 0;
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
