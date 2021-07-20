package services;

import entities.Offers;
import entities.Packages;
import entities.PackagesDeliveryCost;
import repositories.OffersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryCostService {
    final int weightMultipier = 10;
    final int distanceMultipier = 5;

    public List<PackagesDeliveryCost> getDeliveryCost(List<Packages> packagesList, double deliveryCost){
        if(packagesList == null || packagesList.size() == 0){
            return null;
        }
        List<PackagesDeliveryCost> packagesDiscountsList = new ArrayList<>();

        for(Packages packages:packagesList){
            double finalDeliveryCostExDiscount = getTotalDeliveryCostExcludingDiscount(deliveryCost, packages.getWeight(), packages.getDistance());
            double discountAmount = getDiscountedAmount(finalDeliveryCostExDiscount, packages.getOfferCode(), packages.getWeight(), packages.getDistance());
            packagesDiscountsList.add(new PackagesDeliveryCost(packages.getId(), discountAmount, finalDeliveryCostExDiscount - discountAmount));
        }

        return packagesDiscountsList;
    }

    public double getDiscountedAmount(double finalDeliveryCost, String offerCode, int weight, int distance){
        double discountAmount = 0;
        List<Offers> offersList = OffersRepository.getOffers();
        if(offersList != null){
            List<Offers> offersFilteredList = offersList.stream().filter(offers -> offers.offerCode.equals(offerCode)).collect(Collectors.toList());
            if(offersFilteredList.size() > 0){
                Offers currentOffer = offersFilteredList.get(0);
                if(currentOffer != null && currentOffer.offerCode.equals(offerCode)){
                    if (distance >= currentOffer.minDistance && distance <= currentOffer.maxDistance
                            && weight >= currentOffer.minWeight && weight <= currentOffer.maxWeight) {
                        discountAmount = (finalDeliveryCost * currentOffer.relativeValue)/100;
                    }
                }
            }
        }

        return discountAmount;
    }

    double getTotalDeliveryCostExcludingDiscount(double deliveryCost, int weight, int distance){
        return deliveryCost + (weight * weightMultipier) + (distance * distanceMultipier);
    }
}
