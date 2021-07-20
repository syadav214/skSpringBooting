import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

enum Day {
   SUNDAY("sunday"), MONDAY("monday"), TUESDAY("tuesday");
    private String value;
    Day(String day) {
        this.value = day;
    }
    public String getValue(){
        return value;
    }
}

public class run {


    public static void main(String[] arg){
       System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 3));
    }

    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> meanHeap = new PriorityQueue<>((a,b) -> b - a);
        for(int i : nums){
            meanHeap.add(i);
            if(meanHeap.size() > k){
                meanHeap.remove();
            }
        }
        return meanHeap.remove();
    }

    static int lengthOfLongestSubstring(String s) {
        if(s==null)
            return 0;
        int size = 0, left = 0;
        Set<Character> seen = new HashSet<>();
        for(int j=0;j<s.length();j++){
            while(seen.contains(s.charAt(j))){
                seen.remove(s.charAt(left++));
            }

            seen.add(s.charAt(j));
            size = Math.max(size, j - left + 1);

        }
        return size;
    }


    static int solution(int N) {
        int largest = 0;
        int shift = 0;
        int temp = N;
        for (int i = 1; i < 30; ++i) {
            int index = (temp & 1);
            temp = ((temp >>> 1) | (index << 29));
            if (temp > largest) {
                largest = temp;
                shift = i;
            }
        }
        return shift;
    }

    static void threadTest(){
        MathUtils mu =new MathUtils();
        for(int i = 0;i<10;i++){
            Thread1 t1 = new Thread1(mu);
            Thread t2 = new Thread(new Thread2(mu));
            t1.start();
            t2.start();
        }
    }

    static void serielizedAndDesizerid() {
        Student st = new Student("santo", 3,4);
        String fileName = "C:\\Users\\SantoshkumarYadav\\Documents\\testing\\advanceJava\\src\\main\\java\\test.txt";

        //serialize
        FileOutputStream fileOut = null;
        ObjectOutputStream objOut = null;
        try {
            fileOut = new FileOutputStream(fileName);
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(st);;

            objOut.close();
            fileOut.close();

            System.out.println("Serialized:"+ st);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        //deserilize
        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;
        try {
            fileIn = new FileInputStream(fileName);
            objIn = new ObjectInputStream(fileIn);
            Student st2 = (Student) objIn.readObject();
            System.out.println("Deserilized:" + st2);
            fileIn.close();
            objIn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    static void enumMethod() {
        Day d = Day.TUESDAY;
        System.out.println(d.name());
    }
}
