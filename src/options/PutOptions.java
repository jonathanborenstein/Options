package options;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component("PutOptions")
public class PutOptions {

	private String expiration;
	private Double oi;
	private Double strike;
	private int price;

	private ArrayList<Double> oiList;
	private ArrayList<Double> strikeList;
	private ArrayList<Double> putsTotal;
	private ArrayList<String> expirationList;
	
	@Autowired
	private PutData data;
	
	@Autowired
	private OptionData dataOpt;


	public PutOptions()
	{
		oiList =  new ArrayList<Double>();
		strikeList = new ArrayList<Double>();
		expirationList = new ArrayList<String>();
		putsTotal = new ArrayList<Double>();
		price = 0;
	}

	private void setOi()
	{
		String oi2 = null;
		for (int i =0; i < data.getPutsData().size(); i++)
		{
			JsonObject dataset = data.getPutsData().get(i).getAsJsonObject();
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
		for (int i =0; i < data.getPutsData().size(); i++)
		{
			JsonObject dataset = data.getPutsData().get(i).getAsJsonObject();
			strike2 = dataset.get("strike").getAsString();
			
			if (strike2.contains(",")){
				strike2 = strike2.substring(0,1).concat(strike2.substring(2));
				System.out.println(strike2);
			}
			
			
			

			strike = Double.parseDouble(strike2);
			strikeList.add(strike);

		}

	}

	private void setExpiration()
	{
		for (int i =0; i < data.getPutsData().size(); i++)
		{
			JsonObject dataset = data.getPutsData().get(i).getAsJsonObject();
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

	public ArrayList<Double> computePutTotal()
	{
		
		this.getPrice();
		
		double putTotal = 0.0;
		double total = 0.0;
		double priceA = price;
		
		this.setOi();
		this.setStrike();

		for (double j = price; j < priceA + 30; j = j + .5)
		{
			double price = j;
			for (int i = 0; i < data.getPutsData().size(); i++)
			{
				if (this.returnOiList().get(i) != 0.0 && price < this.returnStrikeList().get(i))
				{
					putTotal = (this.returnStrikeList().get(i) - price) * 
							(this.returnOiList().get(i) * 100);
					total = putTotal + total;
				}
			}

			putsTotal.add(total);
			total = 0.0;
		}

		return putsTotal;
	}

	public ArrayList<Double> returnPutsTotal()
	{
		return putsTotal;
	}

	public int getPrice()
	{

		JsonObject dataset = dataOpt.getOptionData().getAsJsonObject();
		price = dataset.get("underlying_price").getAsInt();

		return price;
	}

}