package class1;

import java.time.LocalDateTime;
import java.util.Scanner;

import class1.flights.status;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		LocalDateTime d1 = LocalDateTime.of(2019, 9, 5, 17, 0);
		LocalDateTime d2 = LocalDateTime.of(2019, 2, 7, 19, 0);
		LocalDateTime d3 = LocalDateTime.of(2019, 6, 3, 9, 0);
		LocalDateTime d4 = LocalDateTime.of(2019, 6, 1, 17, 0);
		LocalDateTime d5 = LocalDateTime.of(2019, 6, 3, 5, 0);
		
		Natbag n = new Natbag();

		n.addFlight("Elal", "OK540",d1 , status.onTime, true, "Petah Tikva");
		n.addFlight("Air Fly", "YA123", d2, status.onTime, true, "Paris");
		n.addFlight("Fly Fly", "KOK543", d3, status.onTime, false, "New York");
		n.addFlight("Air Kaka", "BO122", d4, status.onTime, true, "Montana");
		n.addFlight("Air Kaka", "BO122", d5, status.onTime, false, "Montana");
		n.addFlight(s);
		
		n.sortFlights(new CompareByDate());

		System.out.println(n.toString());
		
		System.out.println("----------------------------------------------------------------------");
		
		n.sortFlights(new CompareByAirPortName());

		System.out.println(n.toString());

	}

}
