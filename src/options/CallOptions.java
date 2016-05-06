package options;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component("CallOptions")
public class CallOptions {

	private String expiration;
	private Double oi;
	private Double strike;
	private int price;

	private ArrayList<Double> oiList;
	private ArrayList<Double> strikeList;
	private ArrayList<Double> callsTotal;
	private ArrayList<String> expirationList;

	@Autowired
	private CallData data;
	
	@Autowired
	private OptionData dataOpt;

	
	public CallOptions()
	{
		oiList =  new ArrayList<Double>();
		strikeList = new ArrayList<Double>();
		callsTotal = new ArrayList<Double>();
		expirationList = new ArrayList<String>();
		price = 0;
	}

	private void setOi()
	{
		String oi2 = null;
		for (int i =0; i < data.getCallsData().size(); i++)
		{
			JsonObject dataset = data.getCallsData().get(i).getAsJsonObject();
			oi2 = dataset.get("oi").getAsString();
			if (!oi2.equals("-")){
				oi = Double.parseDouble(oi2);
				oiList.add(oi);
			}
			else
			{
				oi = 0.0;
				oiList.add(oi);
			}
		}

	}

	private void setStrike()
	{
		String strike2 = null;
		
		for (int i =0; i < data.getCallsData().size(); i++)
		{
			JsonObject dataset = data.getCallsData().get(i).getAsJsonObject();
			strike2 = dataset.get("strike").getAsString();
			
			if (strike2.contains(",")){
				strike2 = strike2.substring(0,1).concat(strike2.substring(2));
				System.out.println(strike2);
			}

			strike = Double.parseDouble(strike2);
			strikeList.add(strike);;
		}

	}

	private void setExpiration()
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
		this.setOi();
		return oiList;
	}

	public ArrayList<Double> returnStrikeList()
	{
		this.setStrike();
		return strikeList;
	}

	public ArrayList<String> returnExpList()
	{
		this.setExpiration();
		return expirationList;
	}

	public ArrayList<Double> computeCallTotal()
	{
		
		this.getPrice();
		
		double callTotal = 0.0;
		double total = 0.0;
		double priceA = price;
		
		this.setOi();
		this.setStrike();

		for (double j = price; j < priceA + 30; j = j + .5)
		{
			double price = j;
			for (int i = 0; i < data.getCallsData().size(); i++)
			{
				if (this.returnOiList().get(i) != 0.0 && price > this.returnStrikeList().get(i))
				{
					callTotal = (price - this.returnStrikeList().get(i)) * 
							(this.returnOiList().get(i) * 100);
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

	public int getPrice()
	{

		JsonObject dataset = dataOpt.getOptionData().getAsJsonObject();
		price = dataset.get("underlying_price").getAsInt();

		return price;
	}

}