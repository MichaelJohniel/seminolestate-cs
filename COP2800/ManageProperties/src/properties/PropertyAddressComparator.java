//Name: Michael Maldonado
//Date: 07-15-2024
package properties;

import java.util.Comparator;

public class PropertyAddressComparator implements Comparator<Property> {
	
	public int compare(Property p1, Property p2) {
		return p1.getAddress().compareToIgnoreCase(p2.getAddress());
	}

}
