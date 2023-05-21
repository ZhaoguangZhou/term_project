package Code2;

//-----------第三次作业问题4------------
public abstract class Employee {
    private String name;
    private String number;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, String number, MyDate birthday) {
        this.name = name;
        this.number = number;
        this.birthday = birthday;
    }

    abstract void earnings();
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
class MyDate{
    int year;
    int mouth;
    int day;

    public MyDate() {
    }

    public MyDate(int year, int mouth, int day) {
        this.year = year;
        this.mouth = mouth;
        this.day = day;
    }

    @Override
    public String toString() {
        return year+"/"+mouth+"/"+day;
    }
}