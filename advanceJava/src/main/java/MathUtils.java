public class MathUtils {
    synchronized void getMultiples(int n){
        //synchronized (this){
            for(int i=1;i<=5;i++){
                System.out.println(n*i);;
                try{
                    Thread.sleep(400);;
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        //}
    }
}
