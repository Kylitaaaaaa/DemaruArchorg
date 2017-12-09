import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("First word: ");
//		String first = sc.next();
		
		System.out.print("Second word: ");
//		String second = sc.next();
		
		String first = "email";
		String second = "mail";
		
		//serial damerau
		Damerau d2 = new Damerau(first, second);
		d2.OASDamerau();
		
		//parallel damerau
		Damerau d1 = new Damerau(first, second);
		d1.tryThread();
	}

}
