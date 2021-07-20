package entities;

public class PackagesDeliveryCost {
    public String id;
    public double discount;
    public double totalCost;

    public PackagesDeliveryCost(String id, double discount, double totalCost) {
        this.id = id;
        this.discount = discount;
        this.totalCost = totalCost;
    }
}
