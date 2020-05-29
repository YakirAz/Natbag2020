package class1.DevTools.src.class1;

import java.util.Comparator;

public class CompareByAirPortName implements Comparator<flights> {

	@Override
	public int compare(flights f1, flights f2) {	
		return f1.city.compareTo(f2.city);
	}
}
