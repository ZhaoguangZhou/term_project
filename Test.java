package Code2;

import java.util.Scanner;

//-----------第三次作业问题1------------
public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("input n: ");
        int n= sc.nextInt();
        System.out.println("input m: ");
        int m=sc.nextInt();

        UseCompute useCompute=new UseCompute();
        System.out.print("n+m=");
        useCompute.useCom(new Add(),n,m);
        System.out.print("n-m=");
        useCompute.useCom(new Subtract(),n,m);
        System.out.print("n*m=");
        useCompute.useCom(new Multiply(),n,m);
        System.out.print("n/m=");
        useCompute.useCom(new Divide(),n,m);
    }
}

interface Compute{
    int computer(int n, int m);
}

class Add implements Compute{

    @Override
    public int computer(int n, int m) {
        return n+m;
    }
}

class Subtract implements Compute{
    @Override
    public int computer(int n, int m) {
        return n-m;
    }
}

class Multiply implements Compute{
    @Override
    public int computer(int n, int m) {
        return n*m;
    }
}

class Divide implements Compute{
    @Override
    public int computer(int n, int m) {
        return n/m;
    }
}

class UseCompute{
    public void useCom(Compute com, int one, int two){
        System.out.println(com.computer(one, two));
    }

}

