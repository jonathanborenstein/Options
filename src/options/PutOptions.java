package options;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class PutOptions {
	
	private String expiration;
	private Double oi;
	private Double strike;
	private ArrayList<Double> oiList;
	private ArrayList<Double> strikeList;
	private ArrayList<String> expirationList;
	
	public PutOptions()
	{
		oiList =  new ArrayList<Double>();
		strikeList = new ArrayList<Double>();
		expirationList = new ArrayList<String>();
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
	
}