public class IPAddressRestoration {
    /*
    The IP address can be expressed as follows
    XXX.XXX.XXX.XXX

    Where XXX can be any number between 0 and 255
    Our task is that given a certain string of numbers with no . in between the numbers
    We would have to convert them to the proper IP Address format

    Here we can notice that there would be possibilities when trying to convert the number

    1) Choice: Our choice would be to take the first 3 characters of the available string

    2) Constraint: Our constraints are that the selected number must be between 0 to 255, and that the number cannot contain any leading 0s

    3) Goal: Once we have created 3 dots, and made it to the end of the string, we have made all the way to the end of the string

    Our constraint are 0 to 255
     */

    static String output = "";

    // Let's create all the necessary steps before calling the helper function
    static void IPAddress(String original) {
        // we should keep track of where we are in the string
        int pointer = 0;

        // we should have a counter of how many '.' we have made
        int dotCount = 0;

        // that should be it, so let's try to call our function

        if (helper(original, pointer, dotCount)) {
            output = output.substring(0, output.length() - 1);
            System.out.println(output);
        }
    }

    static boolean helper(String original, int pointer, int dotCount) {
        // let's try to extract the first second and third values
        // we need to somehow keep track of whether we have seen a good case

        // if we have made it all the way to the end of the loop and the number of dots are equal to 4 then we have found the answer
        if (pointer == original.length() && dotCount == 4) return true;

        // if the pointer passes the original position's length return false
        if (pointer >= original.length()) return false;

        // if the number of dots is 4 return false
        if (dotCount == 4) return false;

        // now let's loop around the possible cases and go to the next case
        for (int i = 1; i < 4; i++) {
            // let's try to extract the numbers
            String numbers = original.substring(pointer, pointer + i);

            int num = Integer.parseInt(numbers);

            // if the number contains a leading zero then don't count it
            if ((numbers.charAt(0) == '0' && numbers.length() > 1) || num > 255) break;

            String originalString = output;

            // otherwise let's add this to the output
            output += numbers + '.';

            if (helper(original, pointer + i, dotCount + 1)) {
                return true;
            } else {
                output = originalString;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String ip = "25525511135";

        IPAddress(ip);
    }
}
