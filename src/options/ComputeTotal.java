package options;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;


public class ComputeTotal {


	private ArrayList<String> opTotal;
	private ArrayList<Double> putsTotal;
	private ArrayList<Double> callsTotal;
	
	public ComputeTotal()
	{
		opTotal = new ArrayList<String>();
		putsTotal = new ArrayList<Double>();
		callsTotal = new ArrayList<Double>();
	}

	public ArrayList<String> computeTotal(double priceA)
	{
		double putTotal = 0;
		double callTotal = 0;
		double price = priceA;

		for (int i =0; i < putsTotal.size(); i++)
		{
			callTotal = callsTotal.get((i));
			putTotal = putsTotal.get((i));
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String money = formatter.format(callTotal + putTotal);
			opTotal.add(money + " at " + price);
			price = price + .5;

		}
		Collections.sort(opTotal);
		return opTotal;
	}

	public ArrayList<Double> computePutTotal(PutOptions put, PutData data, double priceA)
	{
		double putTotal = 0.0;
		double total = 0.0;
		double price = priceA;

		for (double j = price; j < priceA + 30; j = j + .5)
		{
			price = j;
			for (int i = 0; i < data.getPutsData().size(); i++)
			{
				if ((put.returnOiList().get(i) != 0.0 && price < put.returnStrikeList().get(i)))
				{
					putTotal = (put.returnStrikeList().get(i) - price) * ((put.returnOiList().get(i) * 100));
					total = putTotal + total;
				}
			}

			putsTotal.add(total);
			total = 0.0;
		}

		return putsTotal;
	}

	public ArrayList<Double> computeCallTotal(CallOptions call, CallData data, double priceA)
	{
		double callTotal = 0.0;
		double total = 0.0;
		double price = priceA;

		for (double j = price; j < priceA + 30; j = j + .5)
		{
			price = j;
			for (int i = 0; i < data.getCallsData().size(); i++)
			{
				if ((call.returnOiList().get(i) != 0.0 && price > call.returnStrikeList().get(i)))
				{
					callTotal = (price - call.returnStrikeList().get(i)) * ((call.returnOiList().get(i) * 100));
					total = callTotal + total;
				}
			}

			callsTotal.add(total);
			total = 0.0;
		}
		return callsTotal;
	}

}