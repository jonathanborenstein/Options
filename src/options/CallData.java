package options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@Component("CallData")
public class CallData {
	
	private JsonObject calls;
	private JsonArray callsData;
	
	@Autowired
	private OptionData call;
	
	
	public JsonArray getCallsData() 
	{
		calls = call.getOptionData().getAsJsonObject();
		callsData = calls.getAsJsonArray("calls");
		return callsData;
	}

}