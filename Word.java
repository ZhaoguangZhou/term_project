package Code2;

import java.util.Arrays;

//-----------第三次作业问题5------------
public class Word {
    public static void main(String[] args) {
        String s="abcde";
        String[] words=new String[]{"a","bb","acd","ace"};
        int count=0;
        for (String word: words) {
            if (isContain(s,word)){
                count++;
            }
        }
        System.out.println(count);
    }
    public static boolean isContain(String s,String word){
        char[] chars=word.toCharArray();
        Arrays.sort(chars);
        boolean flag=false;
        for (char c: chars) {
            int i=s.indexOf(c+"");
            if (i!=-1){
                flag=true;
                s=s.substring(i+1);
            }else {
                flag=false;
                break;
            }
        }
        return flag;
    }
}
