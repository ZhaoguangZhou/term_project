package Code4;

//数据库作业见后面注释
import java.util.ArrayList;
import java.util.Scanner;

public class Work4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("-----First problem-----");
        System.out.println("x=");
        int number=sc.nextInt();
        ReserveNum(number);

        System.out.println("-----Second problem-----");
        System.out.println("n=");
        int n= sc.nextInt();
        WayNum(n);

        System.out.println("-----Third problem-----");
        int[] arr= {1,2,3};
        ArrayList<ArrayList<Integer>> list = getSubList(arr);
        System.out.print("[");
        for (int i=0;i<list.size();i++) {
            ArrayList<Integer> mList=list.get(i);
            System.out.print(mList);
            if (i!= list.size()-1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
    public static void ReserveNum(int n){
        String num;
        if (n>=0){
            num=Integer.toString(n);
            num=new StringBuffer(num).reverse().toString();
            try {
                Integer.valueOf(num);
            }catch (Exception e){
                num="0";
            }finally {
                System.out.println(num);
            }
        }else {
            num=Integer.toString(n);
            num=num.substring(1);
            num=new StringBuffer(num).reverse().toString();
            num="-"+num;
            try {
                Integer.valueOf(num);
            }catch (Exception e){
                num="0";
            }finally {
                System.out.println(num);
            }
        }
    }
    public static void WayNum(int n){
        int a=0;
        int b=0;
        int c=1;
        for (int i = 0; i < n; i++) {
            a=b;
            b=c;
            c=a+b;
        }
        System.out.println(c);
    }

    public static ArrayList<ArrayList<Integer>> getSubList(int[] arr) {
        ArrayList<ArrayList<Integer>> allList = new ArrayList<>();
        int size = 1 << arr.length;
        for (int mark = 0; mark < size; mark++) {
            ArrayList<Integer> aList = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if ((mark & (1 << i)) != 0) {
                    aList.add(arr[i]);
                }
            }
            allList.add(aList);
        }
        return allList;
    }
}

/*
1.
CREATE TABLE student (
studentid Varchar(10),
name Varchar(20),
sex Varchar(2),
age Integer,
Fee DECIMAL(10,2),
Address Varchar(50),
Memo Varchar(300)
);

2.
CREATE TABLE CourseAa (
Aa1 Varchar(20),
Aa2 Integer,
Aa3 DECIMAL(10)
);

3.
CREATE TABLE ChooseBb (
Bb1 Varchar(30),
Bb2 Integer,
Bb3 DECIMAL(6)
);

4.
ALTER TABLE ChooseBb
ADD Bb4 VARCHAR(20) DEFAULT ‘系统测试值’;

5.
ALTER TABLE ChooseBb
ADD Bb5 VARCHAR(10);

ALTER TABLE ChooseBb ADD
PRIMARY KEY(Bb5);

6.
CREATE VIEW View_Choosebb
AS SELECT
Bb1 AS View_bb1,
Bb4 AS View_bb2,
Bb5 AS View_bb3
FROM ChooseBb;

7.
DROP VIEW View_Choosebb;

8.
CREATE INDEX Index_bb2 on ChooseBb (Bb2 ASC);
CREATE INDEX Index_bb2 on ChooseBb (Bb4 DESC);

9.
DROP INDEX Index_bb2 on ChooseBb;

10.
    CREATE TABLE test(
Name Varchar(20),
Age Integer,
Score Numeric(10,2),
Address Varchar(60)
);

11.
INSERT INTO test VALUES
('赵一',20,580.00,'重邮宿舍 12-3-5'),
('钱二',19,540.00,'南福苑 5-2-9'),
('孙三',21,555.50,'学生新区 21-5-15'),
('李四',22,505.00,'重邮宿舍 8-6-22'),
('周五',20,495.00,'学生新区 23-4-8'),
('吴六',19,435.00,'南福苑 2-5-12');

12.
CREATE TABLE test_temp(
Name Varchar(20),
Age Integer,
Score Numeric(10,2),
Address Varchar(60)
);

13.
INSERT INTO test_temp VALUES
('郑七',21,490.50,'重邮宿舍 11-2-1'),
('钱二',20,560.00,'南福苑 3-3-3'),
('王九',19,515.00,'学生新区 19-7-1');

14.
INSERT INTO test SELECT * FROM test_temp;

15.
UPDATE test set Score=Score+5 WHERE Age<=20;

16.
UPDATE test set Age=Age-1 WHERE Address LIKE '南福苑%';

17.
DELETE FROM test WHERE Age>=21 AND Score>=500;

18.
DELETE FROM test WHERE Score<=550 AND Address LIKE '重邮宿舍%';

19.
CREATE TABLE Student(
SNO Varchar(20),
Name Varchar(10),
Age Integer,
College Varchar(30)
);

20.
CREATE TABLE Course(
CourseID Varchar(15),
CourseName Varchar(30),
CourseBeforeID Varchar(15)
);

21.
CREATE TABLE Choose(
SNO Varchar(20),
CourseID Varchar(30),
Score DECIMAL(5,2)
);

22.
INSERT INTO Student VALUES('S00001','张三',20,'计算机学院');
INSERT INTO Student VALUES('S00002','李四',19,'通信学院');
INSERT INTO Student VALUES('S00003','王五',21,'计算机学院');

23.
INSERT INTO Course VALUES('C1','计算机引论',NULL);
INSERT INTO Course VALUES('C2','C 语言','C1');
INSERT INTO Course VALUES('C3','数据结构','C2');

24.
INSERT INTO Choose VALUES('S00001',' C1',95);
INSERT INTO Choose VALUES('S00001',' C2',80);
INSERT INTO Choose VALUES('S00001',' C3',84);
INSERT INTO Choose VALUES('S00002',' C1',80);
INSERT INTO Choose VALUES('S00002',' C2',85);
INSERT INTO Choose VALUES('S00003',' C1',78);
INSERT INTO Choose VALUES('S00003',' C3',70);

25.
SELECT SNO,Name FROM Student
WHERE College='计算机学院';

26.
SELECT * FROM Student
WHERE Age BETWEEN 20 AND 23;

27.
SELECT COUNT(*) FROM Student;

28.
SELECT MAX(Score),MIN(Score),SUM(Score),AVG(Score)
FROM Choose GROUP BY CourseID;

29.
SELECT CourseID,CourseName FROM Course
WHERE CourseBeforeID IS NULL;

30.
SELECT Student.SNO,Student.Name,Course.CourseName,Choose.Score
FROM Student,Choose,Course
WHERE Student.SNO=Choose.SNO
AND Course.CourseID=Choose.CourseID;

31.
SELECT * FROM Student as a WHERE EXISTS
(SELECT * FROM Student as b WHERE b.Name='张三' AND a.College=b.College);

32.
SELECT Choose.SNO,Choose.Score
FROM Choose
WHERE CourseID='C1' AND Score<(
SELECT Score FROM Choose,Student WHERE
CourseID='C1' AND
Choose.SNO=Student.SNO AND
Student.Name='张三'
);

33.
SELECT SNO FROM Choose
WHERE CourseID='C1' OR CourseID='C3'
UNION
SELECT SNO FROM Student;

34.
SELECT DISTINCT SNO FROM Choose
WHERE CourseID='C1' OR CourseID='C3'
UNION
SELECT SNO FROM Student;

 */
