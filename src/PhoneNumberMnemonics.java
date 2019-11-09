import java.util.HashMap;
import java.util.Map;

public class PhoneNumberMnemonics {
    static Map<Character, String> phoneMap = new HashMap<>();

    /*
    So when going through all the phone numbers we need to
    extract each one, and then go through its possibilities

    So for example if it were "22"
    We would have to extract the 2 first
    and its possibilities are going to be
    a b c
    and then we would add our current output string to it maybe,
    then pass it on to the next one, by removing the first letter from the string

    So we can by calling the string and say which index we want to look at

    And by the time it reaches all the way to the end we can print the answer out
    (base case)
     */

    public static void printPhoneNumbers(String phoneNumber) {
        helper(phoneNumber, 0, "");
    }

    public static void helper(String phoneNumber, int index, String output) {
        if (index > phoneNumber.length() - 1) {
            System.out.println(output);
            return;
        }

        // so the magic would be happening here
        // first extract the character at index
        char c = phoneNumber.charAt(index);

        // great now that we have extracted it, we just have to get its values
        String values = phoneMap.get(c);

        // now let's append one by one the values to the output
        for (int i = 0; i < values.length(); i++) {
            // let's extract the value
            char value = values.charAt(i);

            // now we can go and call this recursively
            helper(phoneNumber, index + 1, output + value);
        }
    }

    // The time complexity for this function would be 4^n as it would need to go n layers deep, and branching factor is 4, at maximum
    // The space complexity would be O(n) since at every point in time the size of the output would be at most n
    public static void main(String[] args) {
        phoneMap.put('1', "");
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
        phoneMap.put('0', "");

        printPhoneNumbers("344");
    }
}
