public class Permutations {

    /*
    The problem we are given is that given an input of different possible inputs, we want to print out all the possible
    permutations for the given inputs of a provided length
    For example: if input is "abc" and the length is set to 3
    The permutation would be
    aaa
    aab
    aba
    baa
    aac
    aca
    caa
    .
    .
    .
     */

    /*
    From this problem we can realize that it can easily be done using recursion
    We would have to keep the state of how many times we have gone through the algorithm
    We would have to keep the state of the current letters
    And when we have reached the provided length we would have to print out that answer
     */

    // Let's make a driver function that needs the proper variables
    // Which are the amount of times it has been called (the depth)
    static void findPermutations(String input) {
        int currentLength = 0;
        helper(input, input.length(), currentLength, "");
    }

    // Now let's iterate over all the inputs provided
    static void helper(String input, int length, int currentLength, String output) {
        // we need to reach a goal which is when the currentLength is equal to the length
        if (length == currentLength) {
            System.out.println(output);
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            // let's extract the character at index i
            char c = input.charAt(i);

            // once we found the character we can add the character and send that to itself
            helper(input, length, currentLength + 1, output + c);
        }
    }

    public static void main(String[] args) {
        String test = "abc";

        findPermutations(test);
    }
}
