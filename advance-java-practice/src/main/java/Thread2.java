
public class Thread2 implements Runnable {
    MathUtils mu;
    public Thread2(MathUtils mu){
        this.mu = mu;
    }
    @Override
    public void run() {
        try {
            this.mu.getMultiples(3);
            System.out.println("Thread2 is running");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
