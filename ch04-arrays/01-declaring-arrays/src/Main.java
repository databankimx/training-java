public class Main {
    public static void main(String[] args) {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        int[] nums = {10, 20, 30};

        System.out.println(cars[0]);
        System.out.println(nums[2]);

        cars[0] = "Opel";
        nums[2] += 5;

        System.out.println(cars[0]);
        System.out.println(nums[2]);
    }
}
