import java.util.ArrayList;

class City {
	String name;
	ArrayList<Flight> flights;
	
	public City(String name) {
		this.name = name;
		flights = new ArrayList<>();
	}

	public int numberFlights() {
		return flights.size();
	}

	public void addFlight(Flight f) {
		flights.add(f);
	}
}
