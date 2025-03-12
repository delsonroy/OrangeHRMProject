package javaprograms;

import java.util.HashMap;
import java.util.Map;

//This program prints only the characters that are presnt more than once

public class CharcterFrequency {

	public static void main(String[] args) {

		String s = "basil joseph";

		Map<Character, Integer> charmap = new HashMap<Character, Integer>();

		for (char ch : s.toCharArray()) 
		
		{

			if (charmap.containsKey(ch)) 
			{

				int num = charmap.get(ch);

				charmap.put(ch, num + 1);

			} 
			else {
				charmap.put(ch, 1);
			}
		}

		   boolean valuefound=false;
			// Till the above code my Map is ready now need to print it
			System.out.println("Repeated characters in the string:");
			
			for (Map.Entry<Character, Integer> entry : charmap.entrySet()) 
			{

				if (entry.getValue() > 1) {
					System.out.println("character  " + entry.getKey() + " occurs " + entry.getValue() + " times");
					valuefound=true;				}
				
				}
			
					if(!valuefound)
					{
						System.out.println("In this string, no repeated characters are found");
					}

		}

	}

	
