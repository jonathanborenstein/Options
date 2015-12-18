package options;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class CallOptions {

	private String expiration;
	private Double oi;
	private Double strike;
	private ArrayList<Double> oiList;
	private ArrayList<Double> strikeList;
	private ArrayList<Double> callsTotal;
	private ArrayList<String> expirationList;



	public CallOptions()
	{
		oiList =  new ArrayList<Double>();
		strikeList = new ArrayList<Double>();
		callsTotal = new ArrayList<Double>();
		expirationList = new ArrayList<String>();
	}

	public void setOi(CallData data)
	{
		for (int i =0; i < data.getCallsData().size(); i++)
		{
			JsonObject dataset = data.getCallsData().get(i).getAsJsonObject();
			oi = dataset.get("oi").getAsDouble();
			oiList.add(oi);
		}

	}

	public void setStrike(CallData data)
	{
		for (int i =0; i < data.getCallsData().size(); i++)
		{
			JsonObject dataset = data.getCallsData().get(i).getAsJsonObject();
			strike = dataset.get("strike").getAsDouble();
			strikeList.add(strike);
		}

	}

	public void setExpiration(CallData data)
	{
		for (int i =0; i < data.getCallsData().size(); i++)
		{
			JsonObject dataset = data.getCallsData().get(i).getAsJsonObject();
			expiration = dataset.get("expiry").getAsString();
			expirationList.add(expiration);
		}

	}


	public ArrayList<Double> returnOiList()
	{
		return oiList;
	}

	public ArrayList<Double> returnStrikeList()
	{
		return strikeList;
	}

	public ArrayList<String> returnExpList()
	{
		return expirationList;
	}

	public ArrayList<Double> computeCallTotal(CallData data, double priceA)
	{
		double callTotal = 0.0;
		double total = 0.0;
		double price = priceA;

		for (double j = price; j < priceA + 30; j = j + .5)
		{
			price = j;
			for (int i = 0; i < data.getCallsData().size(); i++)
			{
				if ((this.returnOiList().get(i) != 0.0 && price > this.returnStrikeList().get(i)))
				{
					callTotal = (price - this.returnStrikeList().get(i)) * 
							((this.returnOiList().get(i) * 100));
					total = callTotal + total;
				}
			}

			callsTotal.add(total);
			total = 0.0;
		}
		return callsTotal;
	}

	public ArrayList<Double> returnCallsTotal()
	{
		return callsTotal;
	}

}