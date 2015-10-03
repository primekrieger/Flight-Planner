import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

public class Graph {
	ArrayList<City> cities;
	
	public Graph() {
		cities = new ArrayList<>();
	}
	
	public int numberCities() {
		return cities.size();
	}
	
	private City findCity(String ct) {
		for (City c: cities) {
			if (c.name.equals(ct)) 
				return c;
		}
		return null;
	}
	
	public void addCity(String name) {
		City c = new City(name);
		cities.add(c);
	}
	
	private void addFlight(String fNum, City source, City destination, double depart, double arrive, int price) {
		Flight f = new Flight(fNum, source, destination, depart, arrive, price);
		source.addFlight(f);
	}
	
	public void addFlight(String fNum, String source, String destination, String depart, String arrive, int price) throws CityNotFoundException, ParseException {
		City c1 = findCity(source);
		City c2 = findCity(destination);
		if (c1 == null || c2 == null) {
			CityNotFoundException e = new CityNotFoundException();
			throw e;
		}
		
		DateFormat df = new SimpleDateFormat("HHmm");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));

		addFlight(fNum, c1, c2, df.parse(depart).getTime(), df.parse(arrive).getTime(), price);
	}
	
	
	int cheapest;
	private void cheapestTrip(City startCity, City endCity, double minTime, double maxTime, int currPrice, HashMap<City, Boolean> visited) {
		
		if (startCity == endCity && minTime - 30*60000 <= maxTime) {
			cheapest = Math.min(cheapest, currPrice);
			return;
		}
		
		HashMap<City, Boolean> currVisited = (HashMap<City, Boolean>) visited.clone();
		
		currVisited.put(startCity, true);
		
		for (Flight f : startCity.flights) {
			
			//System.out.println("checking: " + f.fNum);
			
			if (currVisited.containsKey(f.destination)) {
				//System.out.println("discarded" + f.fNum);
				continue;
			}
			
			if (f.depart >= minTime && f.arrive <= maxTime)
				cheapestTrip(f.destination, endCity, f.arrive + 30*60000, maxTime, currPrice + f.price, currVisited);
		}
	}
	
	public int cheapestTrip(String startCity, String endCity, String minTime, String maxTime) throws CityNotFoundException, ParseException {
		City start = findCity(startCity);
		City end = findCity(endCity);
		if (start == null || end == null) {
			CityNotFoundException e = new CityNotFoundException();
			throw e;
		}
		
		DateFormat df = new SimpleDateFormat("HHmm");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		cheapest = Integer.MAX_VALUE;
		
		cheapestTrip(start, end, df.parse(minTime).getTime(), df.parse(maxTime).getTime(), 0, new HashMap<>());
		
		if (cheapest == Integer.MAX_VALUE)
			return -1;
		else
			return cheapest;
	}
}
