import entities.Packages;
import entities.PackagesDeliveryCost;
import entities.PackagesDeliveryTime;
import services.DeliveryCostService;
import services.DeliveryTimeService;
import utils.OutputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CourierApplication {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Enter 1 to get Delivery Cost or Enter 2 to get Delivery Cost with  Delivery Time Estimation: ");
        String type = reader.readLine();
        double deliveryCost = 0;
        int vehicleCount = 0, maxSpeed = 0, maxWeight = 0, noOfPackages = 0;

        List<Packages> packagesList = new ArrayList<>();

        // adding entered data into a list
        String baseData = reader.readLine();
        String[] baseArr = baseData.split(" ");
        if(baseArr.length > 1) {
            deliveryCost = Double.parseDouble(baseArr[0]);
            noOfPackages = Integer.parseInt(baseArr[1]);
            for(int i=0; i< noOfPackages;i++){
                String enteredData = reader.readLine();
                if(Boolean.FALSE.equals(enteredData.isEmpty())){
                    String[] packageInfo = enteredData.split(" ");
                    if(packageInfo.length == 4){
                        int weight = Integer.parseInt(packageInfo[1]);
                        int distance = Integer.parseInt(packageInfo[2]);
                        packagesList.add(new Packages(packageInfo[0], weight, distance, packageInfo[3]));
                    }
                }
            }
        }

        if(packagesList.size() > 0){
            DeliveryCostService deliveryCostService = new DeliveryCostService();
            List<PackagesDeliveryCost> packagesDeliveryCostList = deliveryCostService.getDeliveryCost(packagesList, deliveryCost);

            OutputHelper outputHelper = new OutputHelper();
            if(type.equals("1")){
                outputHelper.printDeliveryCost(packagesDeliveryCostList);
            } else if(type.equals("2")){
                String enteredData = reader.readLine();
                if(Boolean.FALSE.equals(enteredData.isEmpty())){
                    String[] deliveryInfo = enteredData.split(" ");
                    if(deliveryInfo.length == 3){
                        vehicleCount = Integer.parseInt(deliveryInfo[0]);
                        maxSpeed = Integer.parseInt(deliveryInfo[1]);
                        maxWeight = Integer.parseInt(deliveryInfo[2]);
                    }
                }

                DeliveryTimeService deliveryTimeService = new DeliveryTimeService();
                List<PackagesDeliveryTime> packagesDeliveryTimeList = deliveryTimeService.getDeliveryEstimates(packagesList,vehicleCount, maxSpeed, maxWeight);

                outputHelper.printDeliveryCostWithDeliveryTimeEstimated(packagesDeliveryTimeList, packagesDeliveryCostList);
            }
        }
    }
}
