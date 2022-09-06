import java.util.Arrays;

class Solution {
	public long solutuon(long n) {	
		// long ->  String -> char[] 배열 -> 
		// Arrays.sort -> StringBuilder 에 담음 reverse
		// ->toString -> long 
		String s = String.valueOf(n);
		StringBuilder sb = new StringBuilder();
		char[] c = new char[s.length()];
		for(int i = 0; i < s.length(); i++) {
			c[i] = s.charAt(i);
		}
		Arrays.sort(c);
		sb.append(c).reverse(); 
		System.out.println(sb.toString());
		return Long.parseLong(sb.toString());
	}
}
public class Test {
	public static void main(String[] args) {
		Solution solu =  new Solution();
		solu.solutuon(118372);
	}
}
