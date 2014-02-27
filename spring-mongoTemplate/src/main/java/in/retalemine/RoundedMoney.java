package in.retalemine;

import javolution.lang.MathLib;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;

public class RoundedMoney {
	public static Double getRoundedMoney(Amount<Money> amount) {
		StringBuilder append = new StringBuilder();
		Long roundedAmount = MathLib
				.round(amount.doubleValue(amount.getUnit()) * 100);
		append.append("" + (roundedAmount / 100));
		append.append('.');
		append.append((char) ('0' + (roundedAmount % 100) / 10));
		append.append((char) ('0' + (roundedAmount % 10)));
		return Double.parseDouble(append.toString());
	}
}
