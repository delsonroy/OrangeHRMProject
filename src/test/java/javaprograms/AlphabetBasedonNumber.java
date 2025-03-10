package javaprograms;

public class AlphabetBasedonNumber {
    public static void main(String[] args) {
        String s = "d9b8c9";
        printPattern(s);
    }

    public static void printPattern(String s) {
        // Regular expression to match characters followed by digits
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            
            // Check if the current character is a letter
            if (Character.isLetter(currentChar)) {
                // Check the next character to see if it's a digit
                if (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    int repeatCount = Character.getNumericValue(s.charAt(i + 1));
                    // Print the character 'repeatCount' times
                    for (int j = 0; j < repeatCount; j++) {
                        System.out.print(currentChar);
                    }
                }
            }
        }
    }
}