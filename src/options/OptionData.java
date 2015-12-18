package options;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class OptionData {
	
	private String symbol;
	private String url;
	private String json;
	private JsonParser parser;
	private JsonElement element;
	
	public OptionData() throws MalformedURLException, IOException
	{
		symbol = JOptionPane.showInputDialog("Please Enter A Ticker Symbol");
		url = "http://www.google.com/finance/option_chain?q=" + symbol + "&output=json";
		json = IOUtils.toString(new URL(url));
		parser = new JsonParser();
		element = parser.parse(json);
	}

	
	public JsonElement getOptionData() 
	{
		return element;
	}
	
}
