package api.endpoints;

import java.util.Hashtable;
import java.util.Map;

public class HashMapHashTable {
	
	public static void main(String[] args) {
		
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		
		ht.put(1, "AA");
		ht.put(2, "BB");
		ht.put(3, "CC");
		ht.put(4, "DD");
		
		for(Map.Entry m:ht.entrySet()) {
			System.out.println(m.getKey()+" "+m.getValue());
		}
		
	}

}
