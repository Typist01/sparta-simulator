package com.sparta.simulator.model;

import java.util.*;

public class Intake {
	enum CentresEnum {TRAINEE_HUB, BOOTCAMP, TECH_CENTRE}

	private final Collection<Centre> trainingCentres;
	private final Collection<Centre> closedCentres;
	private final Deque<Trainee> waitingList;
	private final ArrayList<Client> clientList;
	private final HashMap<CourseType, List<Trainee>> benchList;
	private final ArrayList<Client> happyList;
	private final ArrayList<Client> unHappyList;


	public Intake() {
		trainingCentres = new ArrayList<>();
		closedCentres = new ArrayList<>();
		clientList = new ArrayList<>();
		benchList = new HashMap<>();
		waitingList = new LinkedList<>();
		happyList = new ArrayList<>();
		unHappyList = new ArrayList<>();

		for (CourseType course : CourseType.values()) {
			benchList.put(course, new LinkedList<>());
		}
	}

	public ArrayList<Client> getHappyList() {
		return happyList;
	}

	public ArrayList<Client> getUnHappyList() {
		return unHappyList;
	}

	public ArrayList<Client> getClientList() { //changing client list
		return clientList;
	}


	public void removeUnsatClients() { //this runs at the end of the year
		for (int i = 0; i < clientList.size() - 1; i++) { //check the "-1" here
			Client client = clientList.get(i);
			if (!client.checkSatisfaction()) {
				//remove unhappy clients, maybe add them to an unhappy list later on
				unHappyList.add(client);
				clientList.remove(i);
			} else {
				//clear happy client's currentTrainees, maybe add to a happy list
				happyList.add(client);
				client.clearTrainees();
			}
		}
	}

	public void addClients() { // 1 to 5 clients per month
		int numOfClients = new Random().nextInt(1, 5);

		for (int i = 0; i < numOfClients; i++) {
			int randTrainees = new Random().nextInt(15, 30); //for num of trainees
			int randCourse = new Random().nextInt(5); //for course type

			clientList.add(new Client(CourseType.values()[randCourse], randTrainees));
		}


	}

	public void incrementTimeTrained() {
		for (Centre centre : trainingCentres) {
			for (Trainee trainee : centre.getTraineeList()) {
				trainee.incrementTimeTrained();
			}
		}

	}

	public void benchTrainee() {
		for (Centre centre : trainingCentres) {
			for (Trainee trainee : centre.getTraineeList()) {
				if (trainee.getTimeTrained() == 3) {    //checks if training for 3 months
					benchList.get(trainee.getType()).add(trainee);
				}
			}
		}
	}


	// adds centre to list
	public void addCentre(Centre centre) {
		trainingCentres.add(centre);

	}

	// create new centres
	public void createCentresRandomly() {
		int randNum = new Random().nextInt(3);
		CentresEnum centreType = CentresEnum.values()[randNum];

		if (centreType.equals(CentresEnum.TRAINEE_HUB)) {
			int centreNum = new Random().nextInt(3) + 1; // randomly generates 1/2/3
			for (int i = 0; i < centreNum; i++) {
				addCentre(new TrainingHub());
			}
			addCentre(new TechCenter());
		} else if (centreType.equals(CentresEnum.TECH_CENTRE)) {
			addCentre(new TechCenter());
		} else if (centreType.equals(CentresEnum.BOOTCAMP)) {
			if (numOfBootCamps() < 2) {
				addCentre(new BootCamp());
			} else {
				int rand = new Random().nextInt(2);
				switch (rand) {
					case 0:
						int centreNum = new Random().nextInt(3) + 1; // randomly generates 1/2/3
						for (int i = 0; i < centreNum; i++) {
							addCentre(new TrainingHub());
						}
					case 1:
						addCentre(new TechCenter());
				}
			}
		}
	}

	private int numOfBootCamps() {
		int numOfBootCamp = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof BootCamp) {
				numOfBootCamp++;
			}
		}
		return numOfBootCamp;
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
/*
		for (Centre centre : trainingCentres) {
			if (!centre.isFull() && intakeList.size() > 0) {
				for (int i = 0; i < RandGenerator.randomCenter(); i++) {
					if (!centre.isFull()) {
						centre.addTrainee(intakeList.remove());
					}
				}
			}
		}
		REDUNDANT REQUIREMENT FROM PHASE 1 TO PHASE 2---DONT DELETE
		*/
		waitingList.addAll(intakeList);
	}


	public void addWaitingTraineesToCentre() {
		Random random = new Random();
		boolean allFull = false;
		Queue<Trainee> temp = new LinkedList<>();
		//DEBUG ______
		//Queue <Trainee> debugQueue = new LinkedList<>();
		while (waitingList.size() > 0 && !allFull) {
			allFull = true;
			//DEBUG___
			/*System.out.println(waitingList.peek());
			if (waitingList.peek()==null){
				System.out.println("There was a null trainess");
				debugQueue.add(waitingList.remove());
			}*/
			for (Centre centre : trainingCentres) {
				if (!centre.isFull() && waitingList.size() > 0) {
					if (centre.acceptsTrainee(waitingList.peek())){
						allFull = false;
						if (random.nextBoolean() && waitingList.size() > 0) {
							centre.addTrainee(waitingList.remove());
						}
					}
				}
			}
			if (waitingList.size() > 0 && allFull) {
				temp.add(waitingList.remove());
				allFull = false;
			}
		}
		while (temp.size() > 0) {
			waitingList.add(temp.remove());
		}
	}

	public void closingCenters() {
		ArrayList<Trainee> spareTrainees = new ArrayList<>();
		List<Centre> centresToBeClosed = new ArrayList<>();
		for (Centre centre : trainingCentres) {
			if (centre.isClosable()) {
				spareTrainees.addAll(centre.getTraineeList());
				closedCentres.add(centre);
				centresToBeClosed.add(centre);
			}
		}
		for (Trainee trainee : spareTrainees) {
			waitingList.addFirst(trainee);
		}
		trainingCentres.removeAll(centresToBeClosed);

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

	public List<Centre> getFullCentres() {
		List<Centre> fullCentres = new ArrayList();
		for (Centre centre : trainingCentres) {
			if (centre.isFull()) {
				fullCentres.add(centre);
			}
		}
		return fullCentres;
	}
	public int getTraineeNumByType(CourseType element){
		int sum=0;
		for (Trainee trainee: waitingList){
			if (trainee.getType().equals(element)){
				sum++;
			}
		}
		return sum;
	}
	public int getFullCentreNumByType(String centreName) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreName) && centre.isFull()) {
				sum++;
			}
		}
		return sum;
	}

	public int getNumTraineesByCentreType(String centreName) {
		int sum = 0;
		for (Centre centre : trainingCentres) {

			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreName)) {
				sum += centre.getNumOfTrainees();
			}
		}
		return sum;
	}

	public int getCentreNumByType(String centreName) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreName)) {
				sum++;
			}
		}
		return sum;
	}


	public int getClosedCentresNum() {
		return closedCentres.size();
	}

	public int getClosedCentresNumByType(String centreType) {
		int sum = 0;
		for (Centre centre : closedCentres) {
			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreType)) {
				sum++;
			}
		}
		return sum;
	}

	public int getClosedTechCentreNumByType(CourseType techCentreType) {
		int sum = 0;
		for (Centre centre : closedCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.getTechType().equals(techCentreType)) {
					sum++;
				}
			}
		}
		return sum;
	}

	public int getTechCentresTraineeNumByType(CourseType centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.getTechType().equals(centreType))
					sum += techCenter.getNumOfTrainees();
			}
		}
		return sum;
	}

	public int getFullTechCentresNumByType(CourseType centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.isFull() && techCenter.getTechType().equals(centreType))
					sum++;
			}
		}
		return sum;
	}

	public int getTechCentresNumByType(CourseType centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {

				if (techCenter.getTechType().equals(centreType))
					sum++;
			}
		}
		return sum;
	}

	//_______________GETTERS_______________
	public Collection<Centre> getTrainingCentres() {
		return trainingCentres;
	}
	public Queue<Trainee> getWaitingList() {
		return waitingList;
	}

	public Collection<Centre> getClosedCentres() {
		return closedCentres;
	}

	public Collection<Centre> getCenters(){return trainingCentres;}

	public void testAddCenter(Centre centre){
		trainingCentres.add(centre);
	}
	public int getClosedCentresNum() {
		return closedCentres.size();
	}

	public int getWaitingCount() {
		return waitingList.size();
	}
}
