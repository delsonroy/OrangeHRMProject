package javaprograms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class characterFrequency {

	public static void main(String[] args) {

		String s = "delsonroy";

		String sam = s.toLowerCase();

		Map<Character, Integer> charmap = new HashMap<Character, Integer>();

		for (char schars : sam.toCharArray()) {

			if (charmap.containsKey(schars)) {

				int charnum = charmap.get(schars);
				charmap.put(schars, charnum + 1);
				
			}else 
			{
				charmap.put(schars, 1);
			}

		}

		System.out.println("The repeated character is: ");

		for (Entry<Character, Integer> entry : charmap.entrySet()) {

			if (entry.getValue() > 1) {

				System.out.println("Repeates charcer is  "+entry.getKey() + " and frequ is  " + entry.getValue());

			}

		}
	}

}
