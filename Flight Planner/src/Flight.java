class Flight {
	
	String fNum;
	City source;
	City destination;
	double depart;
	double arrive;
	int price;
	
	public Flight(String fNum, City source, City destination, double depart, double arrive, int price) {
		this.fNum = fNum;
		this.source = source;
		this.destination = destination;
		this.depart = depart;
		this.arrive = arrive;
		this.price = price;
	}
}
