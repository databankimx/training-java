public class Main {
    static void swapNums(int[] nums) {
        int z = nums[0];
        nums[0] = nums[1];
        nums[1] = z;
    }

    public static void main(String[] args) {
        int firstNum = 10;
        int secondNum = 20;
        System.out.println(firstNum + " " + secondNum);

        int[] pair = {firstNum, secondNum};
        swapNums(pair);
        System.out.println(pair[0] + " " + pair[1]);
    }
}
