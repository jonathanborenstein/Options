package options;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PutData {
	
	private JsonObject puts;
	private JsonArray putsData;
	
	
	public PutData(OptionData put) throws IOException
	{
		puts = put.getOptionData().getAsJsonObject();
		putsData = puts.getAsJsonArray("puts");
	}
	
	public JsonArray getPutsData() 
	{
		return putsData;
	}

}
