package entities;

public class Offers {
    public String offerCode;
    public double relativeValue;
    public int minDistance;
    public int maxDistance;
    public int minWeight;
    public int maxWeight;

    public Offers(String offerCode, double relativeValue, int minDistance, int maxDistance, int minWeight, int maxWeight) {
        this.offerCode = offerCode;
        this.relativeValue = relativeValue;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }
}
