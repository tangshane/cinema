import java.util.Random;


public class Test {

	public static void main(String args[]) {
		int number = 0;
		Random random = new Random();
		for(int i = 0; i<8; i++) {
			number = number * 10 + (random.nextInt(4)+1);
		}
		System.out.println(number);
	}
	
}
