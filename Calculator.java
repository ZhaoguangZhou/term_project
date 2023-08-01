package Program;


import javax.swing.*;
import java.awt.*;

public class Calculator {
    public static void main(String[] args) {
        ScienceUI scienceUI =ScienceUI.getInstance(); //科学计算界面
        BaseUI baseUI =BaseUI.getInstance();          //进制转换界面

        //初始化图形化界面
        scienceUI.init();
        baseUI.init();

        //科学计算按钮
        JButton scienceButton = new JButton("science");
        scienceButton.setBounds(0,0,100,30);
        //添加界面切换事件监听
        scienceButton.addActionListener(e -> {
            scienceUI.setVisible(!scienceUI.isShowing());
            baseUI.setVisible(!baseUI.isShowing());
        });
        //进制转换界面添加科学计算按钮
        baseUI.getContentPane().add(scienceButton);

        //进制转换按钮
        JButton baseButton = new JButton("base");
        //添加界面切换事件监听
        baseButton.addActionListener(e -> {
            scienceUI.setVisible(!scienceUI.isShowing());
            baseUI.setVisible(!baseUI.isShowing());
        });
        scienceUI.add(baseButton, BorderLayout.NORTH);
    }
}
