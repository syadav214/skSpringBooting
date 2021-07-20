public class autoBoxing {
    public static void main(String[] arg){
        int i = 10, k = 5;
        String binary = Integer.toBinaryString(i);
        System.out.println(binary);
        i = i ^(1 << k - 1);
        System.out.println(i);
    }
}
