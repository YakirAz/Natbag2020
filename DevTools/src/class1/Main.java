package class1;

import java.time.LocalDateTime;
import java.util.Scanner;
import natbad2020.flights.status;

public class Main {

	static Scanner s = new Scanner(System.in);
	static Natbag n = new Natbag();

	public static void main(String[] args) throws Exception {
		n = new Natbag("Natbag2020.txt");

		n.addFlight(new flights("Turkish-AirLines", "TL989", LocalDateTime.of(2020, 9, 5, 17, 0), status.onTime, true, "Honolulu"));
		n.addFlight(new flights("Hawaiian-Airlines", "HU731", LocalDateTime.of(2020, 11, 7, 20, 0), status.onTime, true, "Maui"));
		n.addFlight(new flights("Air-China", "LOL98", LocalDateTime.of(2020, 6, 30, 9, 0), status.onTime, false, "Beijing"));
		n.addFlight(new flights("Air-India", "IN121", LocalDateTime.of(2020, 6, 1, 17, 0), status.onTime, true, "Mumbai"));
		n.addFlight(new flights("Delta", "DE900", LocalDateTime.of(2020, 7, 12, 5, 0), status.onTime, false, "Phoenix"));
		n.addFlight(new flights("easyJet", "KI878", LocalDateTime.of(2020, 8, 13, 6, 0), status.onTime, false, "Serbia"));
		try {
			int res = 0;
			while (res != -1) {
				System.out.println(
						"To add flight --> 1\n" + "To sort by date --> 2\n" + "To sort by air ports name --> 3\n"
								+ "To buy ticket --> 4\n" + "For list of all free seats --> 5\n"
								+ "To search specific flight --> 6\n" +"Exit --> -1\n");
				res = s.nextInt();
				switch (res) {
				case 1: // add flight
					System.out.println("Please enter airline");
					String tempAirline = s.next();
					System.out.println("Please enter flight number");
					String flightNum = s.next();
					System.out.println("Flight status? ( landing, unconclusive, early, late, onTime )");
					status eStatus = status.valueOf(s.next());
					System.out.println("arrivaing-> true, else -> false");
					boolean arriving = Boolean.valueOf(s.next());
					System.out.println("Please enter city");
					String city = s.next();
					System.out.println("Pleasr enter the local date time: (Y, M, D, H, Min)");
					LocalDateTime dateTime = LocalDateTime.of(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(),
							s.nextInt());
					n.addFlight(new flights(tempAirline, flightNum, dateTime, eStatus, arriving, city));
					n.save("Natbag2020.txt");
					break;

				case 2: // Sort by date & print
					n.sortFlights(new CompareByDate());
					System.out.println(n.toString());
					break;

				case 3: // Sort by air ports name & print
					n.sortFlights(new CompareByAirPortName());
					System.out.println(n.toString());
					break;

				case 4: // PaymentApp
					paymentApp();
					break;

				case 5: // Print all free seats
					for (int i = 0; i < n.allFlights.size(); i++) {
						if (n.allFlights.get(i) != null) {
							System.out.println(n.allFlights.get(i));
							n.allFlights.get(i).getAllFreeSeats();
							System.out.println("---------------------");
						}
					}
					break;
				case 6: // Search flight
					s.nextLine(); // Clean buffer
					System.out.println("For departure -> D, arrival -> A");
					String res1 = s.nextLine();
					int ans = 0;
					if (res1.equalsIgnoreCase("D")) {
						System.out.println("To search with flight number -> 1\nTo search with City -> 2");
						ans = s.nextInt();
						s.nextLine(); // Clean buffer
						switch (ans) {
						case 1:
							System.out.println("Please enter the flight number:\n");
							res1 = s.nextLine();
							System.out.println(n.searchDepartureFlightByFlightNumber(res1));
							break;

						case 2:
							System.out.println("Please enter the city:\n");
							res1 = s.nextLine();
							System.out.println(n.searchDepartureFlightByCity(res1));
							break;

						default:
							throw new IllegalArgumentException("Unexpected value: " + ans);
						}
					} else { // Arrival flights
						System.out.println("To search with flight number -> 1\nTo search with City -> 2");
						ans = s.nextInt();
						s.nextLine(); // Clean buffer
						switch (ans) {
						case 1:
							System.out.println("Please enter the flight number:\n");
							res1 = s.nextLine();
							System.out.println(n.searchArrivalFlightByFlightNumber(res1));
							break;

						case 2:
							System.out.println("Please enter the city:\n");
							res1 = s.nextLine();
							System.out.println(n.searchArrivalFlightByCity(res1));
							break;

						default:
							throw new IllegalArgumentException("Unexpected value: " + ans);
						}
					}
					break;

				case -1: // Exit
					System.out.println("Bay-bay");
					s.close();
					break;

				default:
					throw new IllegalArgumentException("Unexpected value: " + res);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void paymentApp() throws Exception {
		System.out.println("You arrived to The Payment App.\nFor EXIT  please enter -1, to continue enter 2");
		int res = s.nextInt();
		if (res != -1) {
			System.out.println("Please enter the flight number:");
			String flightNum = s.next();
			flights tempFlight = n.searchFlightByFlightNumber(flightNum);
			System.out.println("Is that the flight you mean to:\n" + tempFlight + "\nY to continue.\n");
			String res2 = s.next();
			if (res2.equalsIgnoreCase("Y")) { // if NOT wrong flight!
				System.out.println("Please enter yor credit catd:\n"); // FOR now without exceptions
				Long creditCard = s.nextLong();
				System.out.println("\nPlease enter youer passport number:\n");
				String passportNumber = s.next();
				Person tempPerson = new Person(creditCard, passportNumber, null); // create temp person
				s.nextLine(); // Clean Buffer
				System.out.println("Please pick one seat:\n");
				n.searchFlightByFlightNumber(flightNum).getAllFreeSeats(); // pick a seat
				System.out.println("\nPlease enter 'Line: <numLine> Row: <numRow>'");
				String tempSeatID = s.nextLine();
				Seat tempSeat = tempFlight.getSeatBySeadID(tempSeatID);
				tempPerson.setMySeat(tempSeat); // initial seat for user
				tempSeat.setMyPerson(tempPerson); // initial user into seat
				n.searchFlightByFlightNumber(flightNum).setSeatToPerson(tempSeat, tempPerson);
				System.out.println("\nYour ticket has been sent to your Email.\n\n" + tempPerson.getMySeat() + "\n"
						+ tempSeat.getMyPerson() + "\n");
				n.save("Natbag2020.txt");
			}
		}
	}
}
