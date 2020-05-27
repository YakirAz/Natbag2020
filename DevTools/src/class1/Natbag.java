package class1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Natbag {

	protected ArrayList<flights> allFlights;

	public Natbag() {
		this.allFlights = new ArrayList<flights>();
	}

	public Natbag(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		this.allFlights = new ArrayList<flights>();
		while (s.hasNext()) {
			allFlights.add(new flights(s));
		}
		s.close();
	}

	public void save(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		for (int i = 0; i < allFlights.size(); i++) {
			allFlights.get(i).save(pw);
		}
		pw.close();
	}

	public boolean addFlight(flights flight) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).equals(flight)) {
				return false;
			}
		}
		allFlights.add(flight);
		return true;
	}

	public void sortFlights(Comparator<?> c) {
		BubbleSort.bubbleSort(allFlights, c);
	}

	public ArrayList<flights> getFlights() {
		return allFlights;
	}
	
	public ArrayList<flights> searchArrivalFlightByCity(String city) throws Exception {
		ArrayList<flights> tempArr = new ArrayList<flights>();
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).getCity().equalsIgnoreCase(city) && allFlights.get(i).arriving) {
				tempArr.add(allFlights.get(i));
			}
		}
		return tempArr;
	}
	
	public ArrayList<flights> searchDepartureFlightByCity(String city) throws Exception {
		ArrayList<flights> tempArr = new ArrayList<flights>();
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).getCity().equalsIgnoreCase(city) && !allFlights.get(i).arriving) {
				tempArr.add(allFlights.get(i));
			}
		}
		return tempArr;
	}
	
	public flights searchArrivalFlightByFlightNumber(String flightNum) throws Exception {
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).getFlightNum().equalsIgnoreCase(flightNum) && allFlights.get(i).arriving) {
				return allFlights.get(i);
			}
		}
		System.out.println("Can't find flight: "+ flightNum);
		return null;
	}
	
	public flights searchDepartureFlightByFlightNumber(String flightNum) throws Exception {
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).getFlightNum().equalsIgnoreCase(flightNum) && !allFlights.get(i).arriving) {
				return allFlights.get(i);
			}
		}
		System.out.println("Can't find flight: "+ flightNum);
		return null;
	}

	public flights searchFlightByFlightNumber(String flightNum) throws Exception {
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).getFlightNum().equalsIgnoreCase(flightNum)) {
				return allFlights.get(i);
			}
		}
		return null;
	}

	public String toString() {
		String arr = "";
		String dep = "";
		for (int i = 0; i < allFlights.size(); i++) {
			if (allFlights.get(i).arriving) {
				arr += allFlights.get(i).toString();
			}
		}
		for (int i = 0; i < allFlights.size(); i++) {
			if (!allFlights.get(i).arriving) {
				dep += allFlights.get(i).toString();
			}
		}
		return "Departing flights: \n" + dep + "\n" + "Arriving flights: \n" + arr;
	}
}
