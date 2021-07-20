import entities.Packages;
import entities.PackagesDeliveryTime;
import org.junit.Test;
import services.DeliveryTimeService;

import java.util.ArrayList;
import java.util.List;

public class DeliveryTimeServiceTest {
    @Test
    public void testDeliveryEstimatesWithNoData(){
        boolean isTestSuccess = false;
        DeliveryTimeService deliveryCostService = new DeliveryTimeService();
        try{
            List<PackagesDeliveryTime> packagesDeliveryTimeList = deliveryCostService.getDeliveryEstimates(null, 0, 10, 10);
            if(packagesDeliveryTimeList == null){
                isTestSuccess = true;
            }
        } catch (Exception e) {
            isTestSuccess = false;
        }

        assert isTestSuccess;
    }

    @Test
    public void testDeliveryEstimatesWithPackageData(){
        boolean isTestSuccess = false;
        List<Packages> packagesList = new ArrayList<>();
        packagesList.add(new Packages("1", 110, 60, "OFR002"));
        DeliveryTimeService deliveryCostService = new DeliveryTimeService();
        try{
            List<PackagesDeliveryTime> packagesDeliveryTimeList = deliveryCostService.getDeliveryEstimates(packagesList, 2, 70, 200);
            if(packagesDeliveryTimeList != null && packagesDeliveryTimeList.size() > 0 &&
                    packagesDeliveryTimeList.get(0).time == 0.8571428571428571){
                isTestSuccess = true;
            }
        } catch (Exception e) {
            isTestSuccess = false;
        }

        assert isTestSuccess;
    }
}
