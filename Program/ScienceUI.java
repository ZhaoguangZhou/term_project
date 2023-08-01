package Program;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

//科学计算界面
public class ScienceUI extends JFrame {
    //单例模式
    private static final ScienceUI instance=new ScienceUI();
    private String expression="";
    JPanel scienceButtons;    //科学计算按钮界面组件
    JTextArea scienceText;    //科学计算输出文本域组件
    Box scienceBox;           //科学计算界面(盒子组件，垂直排布)
    private int flag = 0;   //简单错误输入判断标记
    JButton[] buttons=new JButton[25];    //用于添加监听器的按钮数组
    private ScienceUI(){
    }

    public static ScienceUI getInstance() {
        return instance;
    }
    //初始化界面
    public void init(){
        setComponent();
        setFrame();
    }
    //图形化界面设置
    public void setFrame() {
        //设置名称
        setTitle("科学计算器");
        //设置窗口位置
        setLocationRelativeTo(null);//位置为显示器的正中间
        //设置计算器窗口默认大小
        pack();
        //设置计算器窗口可见
        setVisible(true);
        //点击X号可以关闭程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //设置组件
    public void setComponent(){
        //计算器窗口设置边框布局
        setLayout(new BorderLayout());
        //科学计算界面(盒子组件，垂直排布)
        scienceBox = Box.createVerticalBox();
        //科学计算输出文本域组件
        scienceText = new JTextArea(5, 45);
        //科学计算按钮界面组件
        scienceButtons = new JPanel();

        //设置科学计算输出文本域组件不可编辑
        scienceText.setEditable(false);

        //科学计算按钮界面添加按钮
        scienceButtons.setLayout(new GridLayout(5,5));
        String[] names={
                "sqrt","^","c","back", "/",
                "!","7","8","9","*",
                "%","4","5","6", "-",
                "1/x","1","2","3","+",
                "PI","E","0", ".","="
        };
        for (int i = 0; i < names.length; i++) {
            JButton button = new JButton(names[i]);
            buttons[i]=button;
            //添加按钮
            scienceButtons.add(button);
        }
        //按钮添加事件监听器
        buttonLister();

        //向科学计算界面添加科学计算输出文本域组件
        scienceBox.add(scienceText);
        //向科学计算界面添加科学计算按钮界面组件
        scienceBox.add(scienceButtons);
        //向科学计算界面添加科学计算输出文本域组件
        add(scienceBox);
    }

    //添加事件监听器
    public void buttonLister(){
        buttons[0].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "sqrt");
            expression += "s";
        });

        buttons[1].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "^");
            expression += "^";
        });

        buttons[2].addActionListener(e -> {
            scienceText.setText("");
            expression = "";
            flag = 0;
        });

        buttons[3].addActionListener(e -> {
            expression=expression.substring(0,expression.length()-1);
            scienceText.setText(expression);
        });

        buttons[4].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "/");
            expression += "/";
        });

        buttons[5].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "!");
            expression += "!";
        });


        buttons[6].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "7");
            expression += "7";
        });
        buttons[7].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "8");
            expression += "8";
        });
        buttons[8].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "9");
            expression += "9";
        });

        buttons[9].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "*");
            expression += "*";
        });

        buttons[10].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "%");
            expression += "*0.01";
        });

        buttons[11].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "4");
            expression += "4";
        });
        buttons[12].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "5");
            expression += "5";
        });
        buttons[13].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "6");
            expression += "6";
        });

        buttons[14].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "-");
            expression += "-";
        });

        buttons[15].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "1/");
            expression += "1/";
        });

        buttons[16].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "1");
            expression += "1";
        });
        buttons[17].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "2");
            expression += "2";
        });
        buttons[18].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "3");
            expression += "3";
        });

        buttons[19].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "+");
            expression += "+";
        });

        buttons[20].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "PI");
            expression += "P";
        });

        buttons[21].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "E");
            expression += "E";
        });

        buttons[22].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + "0");
            expression += "0";
        });

        buttons[23].addActionListener(e -> {
            scienceText.setText(scienceText.getText() + ".");
            expression += ".";
        });

        buttons[24].addActionListener(e -> {
            if (!Objects.equals(expression, "")){
                setOutText();
            } else {
                expression = "";
            }
        });

    }

    //进行计算
    public void setOutText(){
        if (flag == 0){
            java.util.List<String> infixExpression = getInfixExpression(expression);
            java.util.List<String> suffixExpression = getSuffixExpression(infixExpression);
            if (flag == 0){
                Double r=computeExpression(suffixExpression);
                if (r == -999999){
                    scienceText.setText(scienceText.getText());
                } else {
                    scienceText.setText(String.valueOf(r));
                }
            }
        }
    }

    //计算后缀表达式
    public double computeExpression(java.util.List<String> suffixExpression) {
        Stack<String> stack = new Stack<>();
        for (String s : suffixExpression) {
            if (isNumber(s)) {
                if (s.charAt(0) == 'E') {
                    stack.push(String.valueOf(Math.E));
                } else if (s.charAt(0) == 'P') {
                    stack.push(String.valueOf(Math.PI));
                } else {
                    stack.push(s);
                }
            } else if (isOperator(s)) {
                double result = 0;
                switch (s) {
                    case "+" -> {   //加法
                        double num2 = Double.parseDouble(stack.pop());
                        double num1 = Double.parseDouble(stack.pop());
                        result = num1 + num2;
                    }
                    case "-" -> {   //减法
                        double num2 = Double.parseDouble(stack.pop());
                        double num1 = Double.parseDouble(stack.pop());
                        result = num1 - num2;
                    }
                    case "*" -> {   //乘法
                        double num2 = Double.parseDouble(stack.pop());
                        double num1 = Double.parseDouble(stack.pop());
                        result = num1 * num2;
                    }
                    case "/" -> {   //除法
                        double num2 = Double.parseDouble(stack.pop());
                        double num1 = Double.parseDouble(stack.pop());
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            scienceText.setText("除数不能为0");
                            flag = 1;
                        }
                    }
                    case "^" -> {    //乘方
                        double num2 = Double.parseDouble(stack.pop());
                        double num1 = Double.parseDouble(stack.pop());
                        result = Math.pow(num1, num2);
                    }
                    case "!" -> {    //阶乘
                        double num1 = Double.parseDouble(stack.pop());
                        if (num1 == 0 || num1 == 1) {
                            result = 1;
                        } else if (num1 == (int) num1 && num1 > 1) {
                            int d = 1;
                            for (int j = (int) num1; j > 0; j--) {
                                d *= j;
                            }
                            result = d;
                        } else {
                            scienceText.setText("阶乘必须为自然数");
                            flag = 1;
                        }
                    }
                    case "s" -> {     //开平方
                        double num1 = Double.parseDouble(stack.pop());
                        result = Math.sqrt(num1);
                    }

                }
                stack.push(String.valueOf(result));
            }
        }
        if (flag == 0){
            if (!stack.isEmpty()){
                return Double.parseDouble(stack.pop());
            } else {
                return 0;
            }
        } else {
            return -999999;
        }
    }
    //判断操作符的优先级
    public static int getPriority(String operator){
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case "!", "s" -> 4;
            default -> 0;
        };
    }

    //把输入的表达式转换成中缀表达式存入list中
    private java.util.List<String> getInfixExpression(String expression) {
        int index = 0;
        java.util.List<String> list = new ArrayList<>();
        do{
            char ch = expression.charAt(index);
            if("+-*/^!s".indexOf(expression.charAt(index)) >= 0){
                //操作符添加至list中
                index ++;
                list.add(String.valueOf(ch));
            }else if (expression.charAt(index) == 'E' || expression.charAt(index) == 'P'){
                index ++;
                list.add(String.valueOf(ch));
            } else if("0123456789".indexOf(expression.charAt(index)) >= 0){
                //数字判断多位数情况
                StringBuilder number = new StringBuilder();
                while (index < expression.length() && "0123456789.".indexOf(expression.charAt(index)) >= 0){
                    number.append(expression.charAt(index));
                    index ++;
                }
                list.add(number.toString());
            }
        }while (index < expression.length());
        return list;
    }

    //中缀表达式转换成后缀表达式存入list中
    public java.util.List<String> getSuffixExpression(java.util.List<String> infixExpression){
        Stack<String> operatorStack = new Stack<>();
        List<String> list = new ArrayList<>();
        if (!infixExpression.isEmpty()) {
            for (String s : infixExpression) {
                if (isNumber(s)) {
                    list.add(s);
                } else if (isOperator(s)) {
                    if (operatorStack.isEmpty()) {
                        operatorStack.push(s);
                    } else {      //符号栈不为空
                        if (getPriority(operatorStack.peek()) <= getPriority(s)) {
                            operatorStack.push(s);   //入栈
                        } else {                     //出栈
                            while (!operatorStack.isEmpty()) {
                                if (getPriority(s) <= getPriority(operatorStack.peek())) {
                                    list.add(operatorStack.pop());
                                }
                            }
                                operatorStack.push(s);
                        }
                    }
                }
            }
            while (!operatorStack.isEmpty()){
                list.add(operatorStack.pop());
            }
        } else {
            scienceText.setText("");
        }
        return list;
    }
    //判断是否为操作符
    public static boolean isOperator(String s){
        return "0123456789.EP".indexOf(s.charAt(0)) == -1;
    }
    //判断是否为操作数
    public static boolean isNumber(String s){
        return "0123456789EP".indexOf(s.charAt(0)) >= 0;
    }
}
