package com.sparta.simulator.model;

import java.util.*;

public class Intake {
    Collection<Centre> trainingCentres = new ArrayList();

    private Queue<Trainee> waitingList = new LinkedList<>();

    public int getWaitingList() {
        return waitingList.size();
    }

    enum centres {TRAINEE_HUB, BOOTCAMP, TECH_CENTRE};

    //returns centre object
     Centre generateCentre(centres centreType) throws GenerateCentreException {
        switch(centreType){
            case TRAINEE_HUB:
                return new TrainingHub();
            case BOOTCAMP:
                return new BootCamp();
            case TECH_CENTRE:
                return new TechCenter();
            default:
                throw new GenerateCentreException("Could not determine centre type");
        }
    }
    // adds centre to list
    public void addCentre(centres name) {
        try{
            trainingCentres.add(generateCentre(name));
        } catch (GenerateCentreException e){
            e.printStackTrace();
        }
    }
    // create new centres
    public void createCentresRandomly(){
        int randNum = new Random().nextInt(3);
        centres name;
        switch(randNum){
            case(0):
                name = centres.TRAINEE_HUB;
                int centreNum = new Random().nextInt(3) + 1; // randomly generates 1/2/3
                for(int i=0; i<centreNum; i++){
                    addCentre(name);
                }
            case(1):
                name = centres.BOOTCAMP;
                addCentre(name);
            case(2):
                name = centres.TECH_CENTRE;
                addCentre(name);
        }
        return;

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
            if (centre.isFull()) {
                openCentres.add(centre);
            }
        }
        return openCentres;
    }

    private List<Centre> getFullCentres() {
        List<Centre> fullCentres = new ArrayList();
        for (Centre centre : trainingCentres) {
            if (!centre.isFull()) {
                fullCentres.add(centre);
            }
        }
        return fullCentres;
    }
}
