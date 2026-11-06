import power.Battery;
import power.Battery;

public class Main {
    public static void main(String[] args) {
        Battery battery = new Battery(50);

        battery.recharge(20);
        battery.show();

        battery.recharge();
        battery.show();

        battery.recharge(100);
        battery.show();
    }
}
