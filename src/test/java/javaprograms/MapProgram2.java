package javaprograms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapProgram2 {

	public static void main(String[] args) {

		HashMap<Object, Object> m = new HashMap<Object, Object>();
		// Insertion order wont be preserved
		m.put(693, "Delson");//
		m.put(102, "Denya");
		m.put(103, "Delma");
		m.put(104, "Lian");
		m.put(105, "Amelia");
		m.put(106, "Bright");
		// m.put(103, "Sandra"); 103 will be replcad by Sandra
		m.put(999, "Delson");

		System.out.println(m);
		System.out.println("The value of 693 is  " + m.get(693));
		m.remove(999);
		System.out.println(m);
		boolean s = m.containsKey(105);
		System.out.println(s);

		System.out.println(m.containsKey("Apple"));
		System.out.println(m.isEmpty());

		System.out.println("=====KeySet=========");

		System.out.println(m.keySet());// returns all the set in the set format and no duplicates
		System.out.println("=====Values=========");
		System.out.println(m.values());
		System.out.println("=====EntrySet=========");
		System.out.println(m.entrySet());// All the entries as set object

		System.out.println(" I can use Entry methids to retrive individaul keys and vlues not in the set format ");

		System.out.println(m.keySet());

		for (Object keeys : m.keySet()) {
			System.out.println(keeys + "  " + m.get(keeys));
		}

		for (Object val : m.values()) {
			System.out.println(val);
		}
		// EntrySet Methods

		System.out.println("=====Using the EntrySet Methods=========");
		for (Map.Entry e : m.entrySet()) {
			System.out.println(e.getKey() + "  " + e.getValue());
		}
		System.out.println("=====Using the Iterator=========");

		Set s1 = m.entrySet();

		Iterator itr = s1.iterator();

		while (itr.hasNext()) 
		{
			Map.Entry e = (Entry) itr.next();

			System.out.println(e.getKey() + " " + e.getValue());
		}

	}

}
