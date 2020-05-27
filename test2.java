package class1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import class1.flights.status;

class test2 {

	void test() {
		Natbag natbag = new Natbag();
		
		LocalDateTime d1 = LocalDateTime.of(2019, 6, 5, 3, 0);
		LocalDateTime d2 = LocalDateTime.of(2019, 9, 11, 17, 0);
		
		flights f11 = new flights("air gogo", "1365GE", d1, status.early, true, "ramat gan");
		flights f12 = new flights("air fofo", "2375SE", d2, status.late, true, "ramat gan");
		
		natbag.addFlight(f11);
		natbag.addFlight(f12);
		
		natbag.sortFlights();
		
		StringBuffer s = new StringBuffer("Departing flights: \n");
		s.append("air fofo flight: 2375SE from ramat gan arriving at: 2019-9-11T17:00\n");
		s.append("air gogo flight: 1365GE from ramat gan arriving at: 2019-6-5T03:00\n");
		s.append("Arriving flights: ");
		
		assertEquals(s,natbag.toString());
		
		
	}

}
