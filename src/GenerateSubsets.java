public class GenerateSubsets {
    /*
    In this problem we are provided with a certain amount of values within an array
    and we need to create a subset of size k from them

    for example if the value of k is 2 and we are provided [1,2,3,4]
    then our subset would be
    [1,2]
    [1,3]
    [1,4]
    [2,3]
    [2,4]
    [3,4]
     */

    // given the values
    // let's define the necessary components we would need for the helper function
    // it might be nicer to have a pointer variable to keep track of the current location so we would be able to see
    // what are the remaining values we can view
    // also the number of entered values
    static void subset(int[] values, int k) {
        if (values.length < k) return;

        int pointer = 0;
        int enteredNumbers = 0;
        int[] ans = new int[k];
        helper(values, k, pointer, enteredNumbers, ans);
    }

    // we have reached our "goal" when the number of entered numbers is equal to the length of ans
    static void helper(int[] values, int k, int pointer, int enteredNumbers, int[] ans) {
        if (enteredNumbers > k - 1) {
            for (int num : ans) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        // so now we should enter the values to the ans array
        ans[enteredNumbers] = values[pointer];

        // once the value has been entered we want to call the helper again
        // but we have to call it based on the pointer to the end
        // we would have to loop a certain amount of time, let's figure out how much that would be
        // at the beginning the pointer would be able to loop n - 1 times
        // because it would have to exclude itself
        // then n - 2
        // n - pointer - 1 works
        for (int i = 1; i < values.length - pointer; i++) {
            helper(values, k, pointer + i, enteredNumbers + 1, ans);
            helper(values, k, pointer + i, enteredNumbers, ans);
        }
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4};

        subset(values, 2);
    }
}
