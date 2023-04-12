public class Work1 {
    public static void main(String[] args) {
        System.out.println("1: B, 2: B, 3: D, 4: B, 5: D");

        showTriangle(6);//打印三角

        reverseString("Hello World");//颠倒字符串

        isPalindrome(12321);//回文数判断

        ShuiXianHua();//水仙花数

        int[] arr=new int[]{1,2,3,4,9,6,7,8,5,0};
        arraysDemo(arr);//求最大值和最小值
    }
    public static void showTriangle(int n){
        for (int i = 0; i <=n; i++) {
            String str="";
            for (int j = 0; j < n-i; j++) {
                str+=" ";
            }
            for (int j = 0; j < 2*i-1; j++) {
                str+="*";
            }
            for (int j = 0; j < n-i; j++) {
                str+=" ";
            }
            System.out.println(str);
        }
    }
    public static void reverseString(String s){
        char[] chars=s.toCharArray();
        for (int i = 0; i < chars.length/2; i++) {
            char t=chars[i];
            chars[i]=chars[chars.length-i-1];
            chars[chars.length-i-1]=t;
        }
        String s1=String.valueOf(chars);
        System.out.println(s1);
    }
    public static void isPalindrome(int num){
        boolean flag=false;
        int m=0,n=num;
        while (n!=0){
            int r=n%10;
            n/=10;
            m+=r;
            if (n!=0){
                m*=10;
            }
        }
        if (m==num){
            flag=true;
        }
        if (flag){
            System.out.println("是的");
        }else {
            System.out.println("不是");
        }
    }
    public static void ShuiXianHua(){
        String str="";
        for (int i = 100; i <=999; i++) {
            int sum=0;
            int a=i/100;
            int b=i/10%10;
            int c=i%10;
            sum=a*a*a+b*b*b+c*c*c;
            if (sum==i){
                str+=i+" ";
            }
        }
        System.out.println(str);
    }
    public static void arraysDemo(int[] arr){
        int min=arr[0],max=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min>arr[i]){
                min=arr[i];
            }
            if (max<arr[i]){
                max=arr[i];
            }
        }
        System.out.println("min: "+min+"  max: "+max);
    }
}
