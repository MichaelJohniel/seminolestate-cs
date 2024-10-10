//Name: Michael Maldonado
//Date: 09/01/2024

public class ReviewProject {

	// Find fastest car
	public static RaceCar findFastestCar( RaceCar[] cars) {
		// Initialize fastestCar
		RaceCar fastestCar = null;
		
		// Validate array
		if (cars != null && cars.length > 0) {
			// Assume first car is fastest
			fastestCar = cars[0];
			
	        // Loop and compare top speeds
			for ( RaceCar car : cars)
				fastestCar = (car.getTopSpeed() > fastestCar.getTopSpeed()) ? car : fastestCar;
		}
		return fastestCar;
	}
	
	// Generate speed stat
	public static Double genSpeedStat() {
		// Range
		int max = 210, min = 160, range = max - min;
		return Math.floor(Math.random() * range) + min;
	}
	
	// Simulate Series
	public static void simulateSeries(RaceCar[] roster) {
		for (int i = 1; i < 4; i++) {
			System.out.println(" SSC Raceway Week " + i + "\n####################");
			RaceCar fCar = findFastestCar(roster);
			System.out.println(fCar.getDriverName() + "'s " + fCar.getClassName() + " car is the fastest with a speed of " + fCar.getTopSpeed() + " mph");
			
			// Get driver's last name
			String driverName = fCar.getDriverName();
			int spaceIndex = driverName.indexOf(' ');
			String lastName = (spaceIndex != -1) ? driverName.substring(spaceIndex + 1) : driverName;
			
			System.out.println(lastName + " qualifies Team " + fCar.getTeamName() + " onto the next series!\n");
			
			// Remove fastest car from roster
			for (int j = 0; j < roster.length; j++) {
				if (roster[j].getTopSpeed() == fCar.getTopSpeed())
					roster[j].setTopSpeed(0.0);
			}
		}
	}
	
	public static void main(String[] args) {
		// Declare and Instantiate Race Cars
		RaceCar[] roster = {
				new RaceCar( "Grenadine", "Rally", "Princeton Stacy", genSpeedStat()),
				new RaceCar( "Starlight", "Stock", "Stephen Kate", genSpeedStat()),
				new RaceCar( "DevilsFood", "Drag", "Chet Marbourelli", genSpeedStat()),
				new RaceCar( "Renegade", "Rally", "Neven Carter", genSpeedStat()),
				new RaceCar( "RedOcean", "Drag", "Savannah Liu", genSpeedStat()),
				new RaceCar( "RatCats", "Drag", "Lily Vang", genSpeedStat()),
		};
		
		// Simulate Series
		simulateSeries(roster);
	}
}