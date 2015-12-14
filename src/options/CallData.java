package options;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CallData {
	
	private JsonObject calls;
	private JsonArray callsData;
	
	
	public CallData(OptionData call) throws IOException
	{
		calls = call.getOptionData().getAsJsonObject();
		callsData = calls.getAsJsonArray("calls");
	}
	
	public JsonArray getCallsData() 
	{
		return callsData;
	}

}