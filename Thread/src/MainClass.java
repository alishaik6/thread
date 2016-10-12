
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Singleton instance = Singleton.getInstance();
		
		instance.test = "testing";
		System.out.println(instance.test);
		
		Singleton ins2 = Singleton.getInstance();
		
		ins2.test = "2testing";
		System.out.println(instance.test);
	}

}
