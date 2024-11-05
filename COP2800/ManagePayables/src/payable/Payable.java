//Name: Michael Maldonado
//Date: 07-01-2024

package payable;
import java.text.NumberFormat;
import java.util.Locale;

public interface Payable {
	
	static final NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
	
	double computeAmountToPay();
}
