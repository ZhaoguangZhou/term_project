package Code2;

import java.util.Scanner;

//-----------第三次作业问题3------------

public class AverageOut {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("输入整数个数： ");
        int n=sc.nextInt();
        System.out.println("请输入所有" + n + "个数");
        int sum=0;
        int count=n;
        for (int i = 0; i < count; i++) {
            try {
                int number= sc.nextInt();
                if (number<0){
                    count++;
                    throw new NumberInException();
                }
                sum+=number;
            }catch (NumberInException e){
                System.out.println(e.warnMess());
                System.out.println("再次输入该数");
            }
        }
        System.out.println("average: "+(float) sum / n);
    }
}


class NumberInException extends Exception{
    String message;
    public NumberInException(){
        message="必须是正数或者0";
    }
    public String warnMess(){
        return message;
    }
}