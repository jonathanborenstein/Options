package options;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class PutOptions {

	private String expiration;
	private Double oi;
	private Double strike;
	private int price;

	private ArrayList<Double> oiList;
	private ArrayList<Double> strikeList;
	private ArrayList<Double> putsTotal;
	private ArrayList<String> expirationList;


	public PutOptions()
	{
		oiList =  new ArrayList<Double>();
		strikeList = new ArrayList<Double>();
		expirationList = new ArrayList<String>();
		putsTotal = new ArrayList<Double>();
		price = 0;
	}

	public void setOi(PutData data)
	{
		for (int i =0; i < data.getPutsData().size(); i++)
		{
			JsonObject dataset = data.getPutsData().get(i).getAsJsonObject();
			oi = dataset.get("oi").getAsDouble();
			oiList.add(oi);
		}

	}

	public void setStrike(PutData data)
	{
		for (int i =0; i < data.getPutsData().size(); i++)
		{
			JsonObject dataset = data.getPutsData().get(i).getAsJsonObject();
			strike = dataset.get("strike").getAsDouble();
			strikeList.add(strike);
		}

	}

	public void setExpiration(PutData data)
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

	public ArrayList<Double> computePutTotal(PutData data)
	{
		double putTotal = 0.0;
		double total = 0.0;
		double priceA = price;

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

	public int getPrice(OptionData data)
	{

		JsonObject dataset = data.getOptionData().getAsJsonObject();
		price = dataset.get("underlying_price").getAsInt();

		return price;
	}

}