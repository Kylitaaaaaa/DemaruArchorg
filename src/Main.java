import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("First word: ");
		String first = sc.nextLine();
		System.out.print("Second word: ");
		String second = sc.nextLine();
		Demaru d = new Demaru();
		d.doDemaru(first, second);
	}

}
