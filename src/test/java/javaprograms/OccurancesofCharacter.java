package javaprograms;

import java.util.HashMap;
import java.util.Map;

public class OccurancesofCharacter {
    public static void main(String[] args) {
        String input = "Bright George";
        Map<Character, Integer> frequencyMap = getCharacterFrequency(input);

        // Displaying the result
        System.out.println("Character Frequencies:");
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<Character, Integer> getCharacterFrequency(String str) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Iterating through the string
        for (char ch : str.toCharArray()) {
            // Ignore spaces
            if (ch != ' ') {
                char lowerCaseChar = Character.toLowerCase(ch); // Optional if you want case insensitivity
                charCountMap.put(lowerCaseChar, charCountMap.getOrDefault(lowerCaseChar, 0) + 1);
            }
        }

        return charCountMap;
    }
}
