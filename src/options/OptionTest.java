package options;

import java.io.IOException;
import java.net.MalformedURLException;

public class OptionTest {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		OptionData first = new OptionData();
		PutData putFirstData = new PutData(first);
		PutOptions firstPutOption = new PutOptions();
		firstPutOption.setOi(putFirstData);
		firstPutOption.setExpiration(putFirstData);
		firstPutOption.setStrike(putFirstData);
		System.out.println(firstPutOption.returnExpList());
		System.out.println(firstPutOption.returnStrikeList());
		System.out.println(firstPutOption.returnOiList());

	}
	
}
