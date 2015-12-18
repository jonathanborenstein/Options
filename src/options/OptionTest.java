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
		
		
		System.out.println();
		
		
		
		
		CallData callFirstData = new CallData(first);
		CallOptions firstCallOption = new CallOptions();
		firstCallOption.setOi(callFirstData);
		firstCallOption.setExpiration(callFirstData);
		firstCallOption.setStrike(callFirstData);
		;
		System.out.println(firstCallOption.returnStrikeList());
		System.out.println(firstCallOption.returnOiList());
		
		System.out.println();
		
		
		System.out.println(firstPutOption.computePutTotal(putFirstData, 90));
		System.out.println(firstCallOption.computeCallTotal(callFirstData, 90));
		
		ComputeTotal cp = new ComputeTotal();
		System.out.println(cp.computeTotal(90, firstPutOption, firstCallOption));
		
		
		
		System.out.println();
		
		

	}
	
}
