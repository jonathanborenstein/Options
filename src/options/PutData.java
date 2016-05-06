package options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component("PutData")
public class PutData {
	
	private JsonObject puts;
	private JsonArray putsData;
	
	@Autowired
	private OptionData put;
	
	public JsonArray getPutsData() 
	{
		puts = put.getOptionData().getAsJsonObject();
		putsData = puts.getAsJsonArray("puts");
		return putsData;
	}

}