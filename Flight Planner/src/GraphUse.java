import java.text.ParseException;

public class GraphUse {
	public static void main(String args[]) throws CityNotFoundException, ParseException {
		
		Graph g = new Graph();
		g.addCity("A");
		g.addCity("D");
		g.addCity("C");
		g.addCity("Q");
		g.addCity("E");
		g.addCity("B");
		
		g.addFlight("123", "A", "B", "0200", "0300", 110);
		g.addFlight("456", "B", "D", "0100", "0400", 100);
		g.addFlight("PQR", "B", "E", "0500", "0545", 300);
		g.addFlight("YZX", "A", "C", "0200", "0230", 100);
		g.addFlight("XC3", "B", "D", "0330", "0400", 200);
		g.addFlight("66P", "E", "C", "0200", "0300", 300);
		g.addFlight("221B", "D", "E", "0430", "0500", 100);
		g.addFlight("DAN", "D", "C", "0430", "0450", 100);
		g.addFlight("ULT", "A", "E", "1400", "2359", 200);
		g.addFlight("MNP", "C", "D", "0300", "0330", 200);
		g.addFlight("AB1", "B", "D", "0300", "0400", 400);
		g.addFlight("DVE", "D", "A", "0300", "0400", 100);
		g.addFlight("COS", "C", "B", "0630", "1340", 100);
		
		System.out.println(g.cheapestTrip("A", "D", "0200", "1200"));
		System.out.println(g.cheapestTrip("A", "E", "0100", "1100"));
	}
}
