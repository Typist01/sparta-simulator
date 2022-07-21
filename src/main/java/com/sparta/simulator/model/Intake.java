package com.sparta.simulator.model;

import com.sparta.simulator.model.centres.BootCamp;
import com.sparta.simulator.model.centres.Centre;
import com.sparta.simulator.model.centres.TechCenter;
import com.sparta.simulator.model.centres.TrainingHub;

import java.util.*;
// Manages trainee movement to and from training centres
public class Intake {
	enum CentresEnum {TRAINING_HUB, BOOTCAMP, TECH_CENTRE}

	private final List<Centre> trainingCentres;
	private final List<Centre> closedCentres;
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

	public void addTraineesToClient(){ //check if they have free spaces and give them some from benchList
		List<Trainee> tempList;
		Collections.sort(clientList,(a,b)-> a.getMonthCount()<b.getMonthCount()?1:a.getMonthCount()==b.getMonthCount()?0:-1);
		for (CourseType course : CourseType.values()){
			for (Client client : clientList) {
				if (client.getCourseType() == course) { //checks if client's course is the course course iteration

					tempList = benchList.get(course); //list of benched trainees relevant course
					int traineesWanted = new Random().nextInt(1, 15); //takes between 1 and 15 trainees

					traineesWanted = client.numToTake(traineesWanted); //doesn't allow more than they can handle
					int index = 0;
					while (tempList.size() > 0 && index < traineesWanted && index < tempList.size() ){ // adds trainees here and removes from benchlist
						client.addTrainee(tempList.remove(0));
						index++;
					}

				}
			}
		}
	}
	public void incrementClientMonth(){
		for (Client c : clientList){
		c.increaseMonth();
		}
	}

	public void removeUnsatClients() { //this runs at the end of the year
		Iterator<Client> clientIterator = clientList.listIterator();
		while (clientIterator.hasNext()){
			Client client = clientIterator.next();
			if (client.getMonthCount()>=12) {
				if (!client.checkSatisfaction()) {
					//remove unhappy clients, maybe add them to an unhappy list later on
					unHappyList.add(client);
					clientIterator.remove();
				} else {
					//clear happy client's currentTrainees, maybe add to a happy list
					happyList.add(client);
					client.clearTrainees();
				}
			}
		}
	}

	public void addClients() { // 1 to 5 clients per month
		int numOfClients = new Random().nextInt(1, 5);

		for (int i = 0; i < numOfClients; i++) {
			int randTrainees = RandGenerator.generateClientRequest(); //for num of trainees
			int randCourse = new Random().nextInt(5); //for course type

			//Add clients to list with rand course and random trainee req
			clientList.add(new Client(CourseType.values()[randCourse], randTrainees));
		}
	}

	public void incrementTimeTrained() {
		for (Centre centre : trainingCentres) {
			for (Trainee trainee : centre.getTraineeList()) {
				//Increment time trained for all Trainees in centres.
				trainee.incrementTimeTrained();
			}
		}

	}

	public void benchTrainee() {
		//Loop through all training centres
		for (Centre centre : trainingCentres) {
			Iterator<Trainee> traineeIterator = centre.getTraineeList().iterator();
			//Loop through trainees in the centre
			while (traineeIterator.hasNext()) {
				Trainee trainee = traineeIterator.next();
				//checks if training for 3 months
				if (trainee.getTimeTrained() == 3) {
					//Remove them from the bench and from centre.
					benchList.get(trainee.getType()).add(trainee);
					traineeIterator.remove();
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

		if (centreType.equals(CentresEnum.TRAINING_HUB)) {
			//Create random amount of training hubs.
			int centreNum = new Random().nextInt(3) + 1; // randomly generates 1/2/3
			for (int i = 0; i < centreNum; i++) {
				addCentre(new TrainingHub());
			}
		} else if (centreType.equals(CentresEnum.TECH_CENTRE)) {
			//Create new Tech Centre.
			addCentre(new TechCenter());
		} else if (centreType.equals(CentresEnum.BOOTCAMP)) {
			if (numOfBootCamps() < 2) {
				addCentre(new BootCamp());
			} else {
				//If 2 bootcamps already exist then make a  random hub or tech centre
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
// return the number of bootcamps
	private int numOfBootCamps() {
		int numOfBootCamp = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof BootCamp) {
				numOfBootCamp++;
			}
		}
		return numOfBootCamp;
	}
// returns the total number of trainees in centres
	public int numOfTotalTrainees() {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			sum += centre.getNumOfTrainees();
		}
		return sum;
	}

	// returns the number of centres that are full
	public int numOfFullCentres() {
		return getFullCentres().size();
	}

	//returns the number of centres that are open
	public int numOfOpenCentres() {
		return getOpenCentres().size();
	}

	// Generates a new trainee group and adds it to the waiting list.
	public void addTraineeGroup() {
		Queue<Trainee> intakeList = new LinkedList<>();
		for (int i = 0; i < RandGenerator.randomTrainee(); i++) {
			intakeList.add(new Trainee());
		}
		waitingList.addAll(intakeList);
	}


	public void addWaitingTraineesToCentre() {
		Random random = new Random();
		boolean allFull = false;
		Queue<Trainee> temp = new LinkedList<>();
		Collections.shuffle(trainingCentres);
		while (waitingList.size() > 0 && !allFull) {
			allFull = true;
			for (Centre centre : trainingCentres) {
				if (!centre.isFull() && waitingList.size() > 0) {
					if (centre.acceptsTrainee(waitingList.peek())){
						allFull = false;
						if (random.nextInt(4)==0 && waitingList.size() > 0) {
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
		Iterator<Centre> centreIterator = trainingCentres.listIterator();
		//Loop through all current training centres
		while (centreIterator.hasNext()){
			Centre centre = centreIterator.next();
			//If centre fulfills the reqs to be closed then move it to closed list
			if (centre.isClosable()) {
				spareTrainees.addAll(centre.getTraineeList());
				closedCentres.add(centre);
				centreIterator.remove();
			}
		}
		for (Trainee trainee : spareTrainees) {
			waitingList.addFirst(trainee);
		}

	}

	private List<Centre> getOpenCentres() {
		List<Centre> openCentres = new ArrayList<>();
		for (Centre centre : trainingCentres) {
			if (centre.isFull()) {
				openCentres.add(centre);
			}
		}
		return openCentres;
	}

	public List<Centre> getFullCentres() {
		List<Centre> fullCentres = new ArrayList<>();
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
	public ArrayList<Client> getHappyList() {
		return happyList;
	}

	public ArrayList<Client> getUnHappyList() {
		return unHappyList;
	}

	public ArrayList<Client> getClientList() { //changing client list
		return clientList;
	}
	public List<Centre> getTrainingCentres() {
		return trainingCentres;
	}
	public Queue<Trainee> getWaitingList() {
		return waitingList;
	}

	public Collection<Centre> getClosedCentres() {
		return closedCentres;
	}

	public Collection<Centre> getCenters(){return trainingCentres;}

	public void testAddClosedCenter(Centre center){
		closedCentres.add(center);
	}

	public int getClosedCentresNum() {
		return closedCentres.size();
	}

	public int getWaitingCount() {
		return waitingList.size();
	}

	//_____________TEST CODE______________
	public void testAddCenter(Centre centre){
		trainingCentres.add(centre);
	}

	public void clearWaiting(){
		waitingList.clear();
	}
}
