package Program;


import javax.swing.*;

//进制转换界面
public class BaseUI extends JFrame {
    //单例模式
    private static final BaseUI instance=new BaseUI();
    //进制转换类对象
    private final BaseConvert baseConvert;
    //组件
    JTextField inputText;  //输入数字文本框
    JTextField inBaseText; //输入进制文本框
    JTextField outBaseText; //输出进制文本框
    JTextField outputText;  //输出数字文本框
    JLabel inputLabel;     //输入数字标签
    JLabel inBaseLabel;    //输入进制标签
    JLabel outBaseLabel;   //输出进制标签
    JLabel outputLabel;    //输出数字标签
    JLabel tipsLabel1;     //提示语句1
    JLabel tipsLabel2;     //提示语句2
    JButton button;        //转换确认按钮
    private BaseUI() {
        baseConvert=BaseConvert.getInstance();
    }
    public static BaseUI getInstance() {
        return instance;
    }
    //初始化图形化界面
    public void init() {
        setComponent();
        actionListen();
        setFrame();
    }

    //图形化界面设置
    public void setFrame() {
        //设置名称
        setTitle("进制转换");
        setLocationRelativeTo(null);//位置为显示器的正中间
        //设置窗口的大小
        setSize(530,350);
        //禁用重新调整窗口大小的功能
        setResizable(false);
        //关闭窗口的右上角的叉的同时关闭程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //清除默认的布局管理器
        getContentPane().setLayout(null);
        //设置窗口显示
        setVisible(false);
    }

    //设置组件
    public void setComponent() {

        //文本输入框
        inputText = new JTextField();
        inBaseText = new JTextField();
        outBaseText = new JTextField();
        outputText = new JTextField();

        //标签
        inputLabel = new JLabel("输入数据:");
        inBaseLabel = new JLabel("输入进制:");
        outBaseLabel = new JLabel("转换进制:");
        outputLabel = new JLabel("转换结果:");
        tipsLabel1 = new JLabel("没有检测输入机制");
        tipsLabel2 = new JLabel("支持 2-36 进制无符号double类型");

        //按钮
        button = new JButton("转换");

        //设置组件位置和尺寸
        inputText.setBounds(120, 70, 120, 40);
        inBaseText.setBounds(120, 150, 120, 40);
        outBaseText.setBounds(120, 220, 120, 40);
        outputText.setBounds(300, 220, 200, 40);

        inputLabel.setBounds(20, 70, 100, 40);
        inBaseLabel.setBounds(20, 150, 100, 40);
        outBaseLabel.setBounds(20, 220, 100, 40);
        outputLabel.setBounds(300, 170, 200, 60);
        tipsLabel1.setBounds(280, 70, 220, 30);
        tipsLabel2.setBounds(20, 270, 350, 30);

        button.setBounds(300, 120, 80, 35);

        //设置输出数字文本框组件不可编辑
        outputText.setEditable(false);

        //添加组件
        getContentPane().add(inputText);
        getContentPane().add(inBaseText);
        getContentPane().add(outBaseText);
        getContentPane().add(outputText);

        getContentPane().add(inputLabel);
        getContentPane().add(inBaseLabel);
        getContentPane().add(outBaseLabel);
        getContentPane().add(outputLabel);
        getContentPane().add(tipsLabel1);
        getContentPane().add(tipsLabel2);

        getContentPane().add(button);
    }
    //添加事件监听
    public void actionListen() {
        button.addActionListener(e -> {
            //获取左边三个文本输入框内容
            String inputNum = inputText.getText();
            int inBase = Integer.parseInt(inBaseText.getText());
            int outBase = Integer.parseInt(outBaseText.getText());

            //转换结果
            outputLabel.setText("转换结果:");
            if(inBase != outBase){
                outputText.setText(baseConvert.Convert(inBase,outBase,inputNum,10));
            }else{
                outputText.setText(inputNum);  //原进制与所需进制相同无需转换
            }
        });
    }
}

