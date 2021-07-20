package utils;

import entities.PackagesDeliveryCost;
import entities.PackagesDeliveryTime;

import java.util.Collections;
import java.util.List;

public class OutputHelper {
    public void printDeliveryCost(List<PackagesDeliveryCost> packagesDeliveryCostList){
        System.out.println("------------Output---------------");
        // sort by package id
        Collections.sort(packagesDeliveryCostList,(o1, o2) -> o1.id.compareTo(o2.id));
        for (PackagesDeliveryCost packagesDeliveryCost : packagesDeliveryCostList) {
            System.out.println(packagesDeliveryCost.id + " "+ packagesDeliveryCost.discount + " " + packagesDeliveryCost.totalCost);
        }
    }

    public void printDeliveryCostWithDeliveryTimeEstimated(List<PackagesDeliveryTime> packagesDeliveryTimeList, List<PackagesDeliveryCost> packagesDeliveryCostList){
        System.out.println("------------Output---------------");
        // sort by package id
        Collections.sort(packagesDeliveryTimeList,(o1, o2) -> o1.id.compareTo(o2.id));
        Collections.sort(packagesDeliveryCostList,(o1, o2) -> o1.id.compareTo(o2.id));
        for(int i = 0;i < packagesDeliveryTimeList.size();i++){
            PackagesDeliveryCost packagesDeliveryCost = packagesDeliveryCostList.get(i);
            PackagesDeliveryTime packagesDeliveryTime = packagesDeliveryTimeList.get(i);
            System.out.println(packagesDeliveryCost.id + " "+ packagesDeliveryCost.discount + " " + packagesDeliveryCost.totalCost + " " + packagesDeliveryTime.time);
        }
    }
}
