package options;

import java.util.ArrayList;
import java.util.Collections;


public class ComputeTotal {

	private ArrayList<String> opTotal;
	private ArrayList<Double> doubleTotal;

	public ComputeTotal() 
	{
		opTotal = new ArrayList<String>();
		doubleTotal = new ArrayList<Double>();
	}

	public ArrayList<Double> computeTotal(OptionData data, PutOptions puts, CallOptions calls) 
	{
		double putTotal = 0;
		double callTotal = 0;
		double total = 0.0;
		double price = puts.getPrice(data);

		for (int i =0; i < puts.returnPutsTotal().size(); i++)
		{
			callTotal = calls.returnCallsTotal().get(i);
			putTotal = puts.returnPutsTotal().get(i);
			total = callTotal + putTotal;
			doubleTotal.add(total);
			opTotal.add(total + " at " + price);
			price = price + .5;

		}

		Collections.sort(doubleTotal);
		return doubleTotal;
	}

	public String getMaxPain()
	{
		String money = null;
		String number = null;
		for (int i = 0; i < doubleTotal.size(); i++)
		{
			number = Double.toString(doubleTotal.get(0));
			if (number.contains(opTotal.get(i).substring(0,5)))
			{
				money = opTotal.get(i);
			}
		}
		return money;
	}







}