package entities;

public class Packages{
    public String id;
    public int weight;
    public int distance;
    public String offerCode;

    public Packages(String id, int weight, int distance, String offerCode) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
        this.offerCode = offerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}


