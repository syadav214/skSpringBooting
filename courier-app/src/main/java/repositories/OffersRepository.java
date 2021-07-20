package repositories;

import entities.Offers;

import java.util.ArrayList;
import java.util.List;

public class OffersRepository {
    static List<Offers> cachedOffers;
    public static List<Offers> getOffers() {
        if(cachedOffers != null && cachedOffers.size() > 0){
            return cachedOffers;
        }

        List<Offers> offersList = new ArrayList<Offers>();
        offersList.add(new Offers("OFR001", 10, 0, 200, 70, 200));
        offersList.add(new Offers("OFR002", 7, 50, 150, 100, 250));
        offersList.add(new Offers("OFR003", 5, 50, 250, 10, 150));
        cachedOffers = new ArrayList<>();
        cachedOffers.addAll(offersList);
        return offersList;
    }
}
