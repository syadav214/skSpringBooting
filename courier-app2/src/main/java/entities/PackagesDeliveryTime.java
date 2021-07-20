package entities;

public class PackagesDeliveryTime {
    public String id;
    public double time;

    public PackagesDeliveryTime(String id, double time) {
        this.id = id;
        this.time = time;
    }

    public double getTime() {
        return time;
    }
}
