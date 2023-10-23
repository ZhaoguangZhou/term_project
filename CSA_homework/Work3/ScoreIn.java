package Code2;

import java.util.Scanner;

//-----------第三次作业问题2------------

public class ScoreIn {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ScoreOut scoreOut=new ScoreOut();

        try {
            while (sc.hasNextInt()){
                System.out.println("input score");
                int score=sc.nextInt();
                scoreOut.printScore(score);
            }
        }catch (ScoreInException e){
            System.out.println(e.warnMess());
        }
    }
}

class ScoreOut{
    public void printScore(int score) throws ScoreInException{
        if (score<0||score>100){
            throw new ScoreInException();
        }
        System.out.println(score);
    }
}
class ScoreInException extends Exception{
    String message;
    public ScoreInException(){
        message="分数必须在 0—100 之间";
    }
    public String warnMess(){
        return message;
    }
}
