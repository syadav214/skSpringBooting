import entities.Packages;
import entities.PackagesDeliveryCost;
import org.junit.Test;
import services.DeliveryCostService;

import java.util.ArrayList;
import java.util.List;

public class DeliveryCostServiceTest {
    @Test
    public void testDiscountedAmountWithIncorrectOfferCode(){
        boolean isTestSuccess = false;
        DeliveryCostService deliveryCostService = new DeliveryCostService();
        try{
            double discountAmount = deliveryCostService.getDiscountedAmount(100, "NA", 10, 10);
            if(discountAmount == 0){
                isTestSuccess = true;
            }
        } catch (Exception e) {
            isTestSuccess = false;
        }

        assert isTestSuccess;
    }

    @Test
    public void testDiscountedAmountWithCorrectOfferCode(){
        boolean isTestSuccess = false;
        DeliveryCostService deliveryCostService = new DeliveryCostService();
        try{
            double discountAmount = deliveryCostService.getDiscountedAmount(100, "OFR002", 200, 100);
            if(discountAmount == 7){
                isTestSuccess = true;
            }
        } catch (Exception e) {
            isTestSuccess = false;
        }

        assert isTestSuccess;
    }

    @Test
    public void testDeliveryCostWithNoData(){
        boolean isTestSuccess = false;
        DeliveryCostService deliveryCostService = new DeliveryCostService();
        try{
            List<PackagesDeliveryCost> packagesDeliveryCostList = deliveryCostService.getDeliveryCost(null, 100);
            if(packagesDeliveryCostList == null){
                isTestSuccess = true;
            }
        } catch (Exception e) {
            isTestSuccess = false;
        }
        assert isTestSuccess;
    }

    @Test
    public void testDeliveryCostWithPackageData(){
        boolean isTestSuccess = false;
        List<Packages> packagesList = new ArrayList<>();
        packagesList.add(new Packages("1", 10, 100, "OFR003"));
        DeliveryCostService deliveryCostService = new DeliveryCostService();
        try{
            List<PackagesDeliveryCost> packagesDeliveryCostList = deliveryCostService.getDeliveryCost(packagesList, 100);
            if(packagesDeliveryCostList != null && packagesDeliveryCostList.size() > 0 &&
               packagesDeliveryCostList.get(0).discount == 35 && packagesDeliveryCostList.get(0).totalCost == 665 ){
                isTestSuccess = true;
            }
        } catch (Exception e) {
            isTestSuccess = false;
        }
        assert isTestSuccess;
    }
}
