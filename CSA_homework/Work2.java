package Code1;

import java.math.BigInteger;
import java.util.Scanner;
public class Work2 {
    public static void main(String[] args) {
        System.out.println("<-------第一题------->");

        Monkey monkey=new Monkey("mon");
        System.out.println(monkey.getName());
        monkey.speak();

        People people=new People("peo");
        System.out.println(people.getName());
        people.speak();
        people.think();

        System.out.println("<-------第二题------->");
        Vehicle v=new Vehicle(4,1000);
        System.out.println(v);

        Car c=new Car(4,1150.0,6);
        System.out.println(c);

        Truck t=new Truck(6,15000.0,1,7000);
        System.out.println(t);

        System.out.println("<-------第三题------->");
        Scanner sc=new Scanner(System.in);

        System.out.println("input a:");
        String a=sc.nextLine();

        System.out.println("input b:");
        String b=sc.nextLine();

        System.out.println("output c:");
        System.out.println(getSum(a, b));

        System.out.println("<-------第四题------->");
        System.out.println("I don't know how to deal with the problem");

        System.out.println("<-------第五题------->");
        String[] strs=new String[]{"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
    public static String getSum(String a,String b){
        return new BigInteger(a).add(new BigInteger(b)).toString();
    }
    public static String longestCommonPrefix(String [] strs) {
        String ans = "";
        if (strs.length == 0) {
            return "";
        }
        ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            ans = ans.substring(0, j);
            if (ans.equals("")) {
                return ans;
            }
        }
        return ans;
    }
}
class Monkey{
    private String name;

    public Monkey() {
    }

    public Monkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void speak(){
        System.out.println("咿咿呀呀");
    }
}
class People extends Monkey{
    public People(String name) {
        super.setName(name);
    }

    @Override
    public void speak() {
        System.out.println("小样儿，不错嘛！会说话了！");
    }
    public void think(){
        System.out.println("别说话！认真思考！");
    }
}
class Vehicle{
    private int wheels;
    private double weight;

    public Vehicle() {
    }

    public Vehicle(int wheels, double weight) {
        this.wheels = wheels;
        this.weight = weight;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "wheels=" + wheels +
                ", weight=" + weight +
                '}';
    }
}
class Car extends Vehicle{
    private int loader;

    public Car() {
    }

    public Car(int wheels, double weight, int loader) {
        super(wheels, weight);
        this.loader = loader;
    }

    public int getLoader() {
        return loader;
    }

    public void setLoader(int loader) {
        this.loader = loader;
    }

    @Override
    public String toString() {
        String s="车轮的个数是："+getWheels()+" 车重："+getWeight()+"\n"+
                "这是一辆小车，能载5人，实载"+loader+"人";
        if (loader>5){
            s+=",你超员了！！！";
        }
        return s;
    }
}
class Truck extends Vehicle{
    private int loader;
    private double payload;

    public Truck() {
    }

    public Truck(int wheels, double weight, int loader, double payload) {
        super(wheels, weight);
        this.loader = loader;
        this.payload = payload;
    }

    public int getLoader() {
        return loader;
    }

    public void setLoader(int loader) {
        this.loader = loader;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        String s="车轮的个数是："+getWheels()+" 车重："+getWeight()+"\n"+
                "这是一辆卡车，能载3人，实载"+loader+"人";
        if (loader>3){
            s+=",你超员了！！！";
        }
        s+="\n这是一辆卡车，核载5000kg，你已装载"+payload+"kg";
        if (payload>5000){
            s+=",你超载了！！！";
        }
        return s;
    }
}
