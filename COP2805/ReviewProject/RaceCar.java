//Name: Michael Maldonado
//Date: 09/01/2024

public class RaceCar {
	// Variables
	private String teamName;
	private String className;
	private String driverName;
	private Double topSpeed;
	
	// Constant
	private static final double MIN_SPEED = 0.0;
	
	// Constructor
	public RaceCar(String newTeamName, String newClassName, String newDriverName, double newTopSpeed) {
		// Validate stats
		if (newTeamName == null || newTeamName.isEmpty())
	        throw new IllegalArgumentException("Team name cannot be empty");
	    if (newClassName == null || newClassName.isEmpty())
	        throw new IllegalArgumentException("Class name cannot be empty");
	    if (newDriverName == null || newDriverName.isEmpty())
	        throw new IllegalArgumentException("Driver name cannot be empty");
	    if (newTopSpeed < MIN_SPEED)
	        throw new IllegalArgumentException("Top speed cannot be negative");
	    
	    // Set race car stats
		setTeamName(newTeamName);
		setClassName(newClassName);
		setDriverName(newDriverName);
		setTopSpeed(newTopSpeed);
	}
	
	// Stat getters & Setters
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String newTeamName) {
		if (newTeamName != null && newTeamName.length() > 0)
			this.teamName = newTeamName;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String newClassName) {
		if (newClassName != null && newClassName.length() > 0)
			this.className = newClassName;
	}
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String newDriverName) {
		if (newDriverName != null && newDriverName.length() > 0)
			this.driverName = newDriverName;
	}
	
	public double getTopSpeed() {
		return topSpeed;
	}
	public void setTopSpeed(double newTopSpeed) {
		if (newTopSpeed >= MIN_SPEED)
			this.topSpeed = newTopSpeed;
	}
	
	@Override
	public String toString() {
		return "[teamName=" + getTeamName() +
				", className=" + getClassName() +
				", driverName=" + getDriverName() + 
				", topSpeed=" + getTopSpeed() + "]\n";
	}
}
