import java.util.*;

public class Week7 {
    static Random random = new Random();

    public static void test() {
        System.out.println(task1("abbc", "dog cat cat fish"));
        System.out.println(task2(new int[]{1, 2, 2, 3, 0}));
        for (int i = 0; i < 5; i++) {
            int target = random.nextInt(15) - 3;
            System.out.println("target:" + target + "\tresult" + task3(new int[]
                    {0, 4, 5, 6, 8}, target));
        }
    }

    public static void main(String[] args) {
        test();
    }

    public static boolean task1(String pattern, String str) {
//第一题代码
        boolean res = true;
        String[] array= str.split(" ");
        if (array.length != pattern.length()){
            res=false;
        }
        HashMap<Character,String> map = new HashMap<>();
        for (int i = 0;i<pattern.length();i++){
            if (!map.containsKey(pattern.charAt(i))){               
                if (!map.containsValue(array[i])){
                    map.put(pattern.charAt(i),array[i]);
                } else {
                    res=false;
                }
            }
            else {
                if (!map.get(pattern.charAt(i)).equals(array[i])) {
                    res=false;
                }
            }
        }
            return res;
    }

    public static int task2(int[] nums) {
//第二题代码
        int res = 0;
        int[] array=new int[nums.length];
        List<Integer> list=new ArrayList<>();
        for (int num: nums) {
            array[num]++;
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i]!=1){
                list.add(i);
            }
        }
        res= list.get(random.nextInt()%(list.size()-1));
        return res;
    }

    public static int task3(int[] nums, int target) {
//第三题代码
        int res = 0;
        res= Arrays.binarySearch(nums,target);
        if (res<0){
            res=-1;
        }
        return res;
    }
}
