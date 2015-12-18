package options;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;


public class ComputeTotal {

	private ArrayList<String> opTotal;

	public ComputeTotal()
	{
		opTotal = new ArrayList<String>();
	}

	public ArrayList<String> computeTotal(double priceA, PutOptions puts, CallOptions calls)
	{
		double putTotal = 0;
		double callTotal = 0;
		double price = priceA;

		for (int i =0; i < puts.returnPutsTotal().size(); i++)
		{
			callTotal = calls.returnCallsTotal().get(i);
			putTotal = puts.returnPutsTotal().get(i);
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String money = formatter.format(callTotal + putTotal);
			opTotal.add(money + " at " + price);
			price = price + .5;

		}
		Collections.sort(opTotal);
		return opTotal;
	}


}