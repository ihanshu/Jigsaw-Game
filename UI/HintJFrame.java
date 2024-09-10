package UI;

import javax.swing.*;
import java.awt.*;

public class HintJFrame extends JFrame {
    public HintJFrame(){
        initJFrame();
    }

    private void initJFrame() {
        // 设置窗口大小、标题、关闭操作等
        this.setSize(860, 350);
        this.setTitle("声明");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个面板，并使用BoxLayout进行垂直布局
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // 添加声明文字
        JLabel background = new JLabel(new ImageIcon("src\\image\\声明.png"));
        background.setBounds(0, 0, 850, 241);
        this.getContentPane().add(background);

        // 添加一个空面板作为间隔
        contentPanel.add(Box.createVerticalStrut(260));

        // 添加确认按钮
        JButton confirmButton = new JButton("确认");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(confirmButton);

        // 为确认按钮添加事件监听器
        confirmButton.addActionListener(e -> {
            dispose();
            new LoginJFrame().setVisible(true);
        });

        this.add(contentPanel);

        this.setVisible(true);
    }

}
