package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame() {
        // 初始化注册界面
        initJFrame();
        // 添加组件
        initView();
        // 显示界面
        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("注册");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);
    }

    private void initView() {
        // 用户名标签
        JLabel usernameLabel = new JLabel(new ImageIcon("src\\image\\login\\用户名.png"));
        usernameLabel.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameLabel);

        // 用户名输入框
        JTextField usernameText = new JTextField();
        usernameText.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameText);

        // 密码标签
        JLabel passwordLabel = new JLabel(new ImageIcon("src\\image\\login\\密码.png"));
        passwordLabel.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordLabel);

        // 密码输入框
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordText);

        // 确认密码标签
        JLabel confirmPasswordLabel = new JLabel(new ImageIcon("src\\image\\login\\密码.png"));
        confirmPasswordLabel.setBounds(130, 255, 32, 16);
        this.getContentPane().add(confirmPasswordLabel);

        // 确认密码输入框
        JPasswordField confirmPasswordText = new JPasswordField();
        confirmPasswordText.setBounds(195, 255, 200, 30);
        this.getContentPane().add(confirmPasswordText);

        // 注册按钮
        JButton register = new JButton();
        register.setBounds(195, 315, 128, 47);
        register.setIcon(new ImageIcon("src\\image\\login\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        JLabel registerLabel = new JLabel("注册账户");
        Font font=new Font("隶书",Font.BOLD,50);
        registerLabel.setFont(font);
        registerLabel.setBounds(150, 10, 250, 150);
        this.getContentPane().add(registerLabel);

        // 添加注册按钮的事件监听
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());

                // 检查用户名是否已存在
                for (User user : LoginJFrame.list) {
                    if (user.ID.equals(username)) {
                        showJDialog("用户名已存在！");
                        return;
                    }
                }

                // 检查两次密码是否一致
                if (!password.equals(confirmPassword)) {
                    showJDialog("两次输入的密码不一致！");
                    return;
                }

                // 注册成功，添加新用户到用户列表
                LoginJFrame.list.add(new User(username, password));
                showJDialog("注册成功！");
                dispose();  // 关闭注册窗口
            }
        });
    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel(content, SwingConstants.CENTER);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }
}
