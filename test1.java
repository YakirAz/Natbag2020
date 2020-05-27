package class1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import class1.flights.status;

class test1 {
	
	@Test
	public void test() {
		Natbag natbag = new Natbag();
		LocalDateTime d = LocalDateTime.of(2019, 9, 5, 17, 0);
		
		flights f1 = new flights("air gogo", "1365GE", d, status.early, true, "ramat gan");
		natbag.addFlight(f1);
		assertEquals(1,natbag.getNumOfFlights());
	}

}
