package api.endpoints;

public class CountNumbersInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "s23ht6og1";
		int sum =0;
		
		for(int i=0; i<str.length();i++) {
			if(Character.isDigit(str.charAt(i)))
				sum = sum+Character.getNumericValue(str.charAt(i));
		}

		System.out.println("Sum is: " +sum);
	}

}
