package options;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("options.xml");
		
		
		PutOptions putOpt = (PutOptions)context.getBean("PutOptions");
		System.out.println(putOpt.returnExpList());
		System.out.println(putOpt.returnStrikeList());
		System.out.println(putOpt.returnOiList());
		
		System.out.println();
		
		CallOptions callOpt = (CallOptions)context.getBean("CallOptions");
		System.out.println(callOpt.returnStrikeList());
		System.out.println(callOpt.returnOiList());
		
		System.out.println();
		
		System.out.println(putOpt.computePutTotal());
		System.out.println(callOpt.computeCallTotal());
		
		System.out.println();
		
		ComputeTotal cpt = (ComputeTotal)context.getBean("ComputeTotal");
		
		String mp = cpt.getMaxPain();
		System.out.println("Max Pain is " + mp);
		
		((ClassPathXmlApplicationContext)context).close();

	}

}
