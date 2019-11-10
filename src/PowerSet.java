public class PowerSet {
    /*
    The problem that is defined here is that if we are given a string of numbers, we must be able to create a superset
    of them
    For example: if the numbers provided were "abc"
    We would have to return the set
    ""
    "a"
    "b"
    "c"
    a,b
    a,b,c
    b
    b,c
     */

    // so let's make our driver have the necessary stuff which would be what??
    // it should contain which index it is at currently
    static void powerSet(String test) {
        helper(test, 0, "");
    }

    static void helper(String input, int currentIndex, String currentSet) {
        // Our goal, is when we have reached through the entire string
        // then we would print it
        if (currentIndex == input.length()) {
            System.out.println(currentSet);
            return;
        }

        helper(input, currentIndex + 1, currentSet + input.charAt(currentIndex));
        helper(input, currentIndex + 1, currentSet);
    }

    public static void main(String[] args) {
        String test = "abc";
        powerSet(test);
    }
}
