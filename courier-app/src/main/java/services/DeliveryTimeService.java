package services;

import entities.Packages;
import entities.PackagesDeliveryTime;
import entities.Vehicles;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveryTimeService {

    public List<PackagesDeliveryTime> getDeliveryEstimates(List<Packages> packagesList,  int vehicleCount, int maxSpeed, int maxWeight) {
        if(packagesList == null || packagesList.size() == 0){
            return null;
        }
        List<PackagesDeliveryTime> packagesDeliveryTimeList = new ArrayList<>();

        // sort by weights
        Collections.sort(packagesList, (o1, o2) -> o1.getWeight() <  o2.getWeight() ? 1 : o1.getWeight() == o2.getWeight() ? 0 : -1);

        HashMap<String,List<Packages>> combinePackageMap = new HashMap<>();
        HashMap<String,Integer> weightMap = new HashMap<>();
        HashMap<String,Boolean> additionalPackageInDeliveryMap = new HashMap<>();

        int currentTotalWeight = 0;
        // combining packages to carry maximum weights
        for(int i=0;i<packagesList.size();i++){
            Packages current = packagesList.get(i);
            currentTotalWeight = current.getWeight();

            if(currentTotalWeight > maxWeight){
                continue;
            }

            if(combinePackageMap.containsKey(current.getId()) || additionalPackageInDeliveryMap.containsKey(current.getId())){
                continue;
            }

            List<Packages> tmp = new ArrayList<>();
            tmp.add(current);
            combinePackageMap.put(current.getId(), tmp);
            weightMap.put(current.getId(), current.getWeight());

            for(int j=i+1;j<packagesList.size();j++){
                Packages next = packagesList.get(j);
                if(combinePackageMap.containsKey(next.getId()) || additionalPackageInDeliveryMap.containsKey(next.getId())){
                    continue;
                }

                int weightTillNow = currentTotalWeight;
                currentTotalWeight += next.getWeight();
                if(currentTotalWeight > maxWeight){
                    tmp = new ArrayList<>();
                    tmp.add(current);
                    combinePackageMap.put(current.getId(),tmp);
                    weightMap.put(current.getId(), weightTillNow);
                    currentTotalWeight = weightTillNow;
                }
                else {
                    tmp = new ArrayList<>();
                    if(combinePackageMap.containsKey(current.getId())){
                        tmp.addAll(combinePackageMap.get(current.getId()));
                    }
                    tmp.add(next);
                    combinePackageMap.put(current.getId(),tmp);

                    int tmpWeight = tmp.stream().mapToInt(Packages::getWeight).sum();
                    weightMap.put(current.getId(), tmpWeight);
                    additionalPackageInDeliveryMap.put(next.getId(), true);
                    break;
                }
            }
        }

        // sort by weights on combined packages
        List<Map.Entry<String,Integer>> packageListbyWeight = new LinkedList<>(weightMap.entrySet());
        Collections.sort(packageListbyWeight, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // initial state of vehicles
        List<Vehicles> vehiclesList = new ArrayList<>();
        for(int i=0;i< vehicleCount;i++){
            vehiclesList.add(new Vehicles(i,0));
        }

        int start = 0, end = 0, count = 0;
        while(count < packageListbyWeight.size()){
            List<Vehicles> availableVehicles = getAvailableVehicles(vehiclesList);
            if(availableVehicles == null || availableVehicles.size() == 0){
                continue;
            }

            end = end + availableVehicles.size();
            // if combine package list is of 1 then
            if(packageListbyWeight.size() == 1){
                end = 1;
            }
            // getting sub list by availableVehicle
            List<Map.Entry<String,Integer>> packageListbyWeightByAvailableVehicle = packageListbyWeight.subList(start,end);

            int index = 0;
            for(Map.Entry<String,Integer> currentEntry: packageListbyWeightByAvailableVehicle){
                String packageId = currentEntry.getKey();
                if(combinePackageMap.containsKey(packageId)){
                    List<Packages> currentCombinePackage = combinePackageMap.get(packageId);
                    if(currentCombinePackage == null || currentCombinePackage.size() == 0){
                        continue;
                    }

                    Vehicles vehicles = availableVehicles.get(index);
                    int startIndex = packagesDeliveryTimeList.size(), currentCount = 0;

                    for(Packages p: currentCombinePackage){{
                        double calculatedDeliveryTime = (double) p.getDistance()/maxSpeed +  vehicles.time;
                        packagesDeliveryTimeList.add(new PackagesDeliveryTime(p.getId(), calculatedDeliveryTime));
                        currentCount++;
                    }}

                    double maxVal = packagesDeliveryTimeList.get(startIndex).getTime();
                    if(currentCount > 1){
                        List<PackagesDeliveryTime> packagesTimesSubList = packagesDeliveryTimeList.subList(startIndex, currentCount);
                        if(packagesTimesSubList != null && packagesTimesSubList.size() > 0){
                            maxVal = packagesTimesSubList.stream().mapToDouble(PackagesDeliveryTime::getTime).max().getAsDouble();
                        }
                    }

                    //removing old info of a vehicle and updating its time
                    vehiclesList.remove(vehicles);
                    vehicles.time +=  2 * maxVal;
                    vehiclesList.add(vehicles);
                }
                index++;
            }

            start = end;
            count += packageListbyWeightByAvailableVehicle.size();
        }
        return packagesDeliveryTimeList;
    }


    // getting available Vehicles - taking min time of a vehicle and return all vehicles who has minimum time
    List<Vehicles> getAvailableVehicles(List<Vehicles> vehiclesList){
        double minTime = vehiclesList.stream().mapToDouble(Vehicles::getTime).min().getAsDouble();
        List<Vehicles> newList = vehiclesList.stream().filter(vehicles -> vehicles.time == minTime).collect(Collectors.toList());
        if(newList != null){
            return newList;
        }
        return null;
    }
}
