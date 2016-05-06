package options;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ComputeTotal")
public class ComputeTotal {

	private ArrayList<String> opTotal;
	private ArrayList<Double> doubleTotal;

	@Autowired
	private PutOptions puts;

	@Autowired
	private CallOptions calls;

	public ComputeTotal() 
	{
		opTotal = new ArrayList<String>();
		doubleTotal = new ArrayList<Double>();
	}

	public ArrayList<Double> computeTotal() 
	{
		double putTotal = 0;
		double callTotal = 0;
		double total = 0.0;
		double price = puts.getPrice();

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
		System.out.println(opTotal);
		return doubleTotal;
	}

	public String getMaxPain()
	{
		this.computeTotal();
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
