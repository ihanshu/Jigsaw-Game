package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class LoginJFrame extends JFrame implements ActionListener{

    // 模拟的用户数据
    static ArrayList<User> list = new ArrayList<>();

    JButton login = new JButton();
    JButton register = new JButton();


    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JTextField codeText = new JTextField();

    // 静态初始化用户列表
    static {
        list.add(new User("player", "123456"));
        list.add(new User("root", "1234"));
    }

    String codeStr = CodeUtil.getCode();

    public LoginJFrame() {
        // 初始化登录界面
        initJFrame();

        // 添加视图组件
        initView();

        // 显示界面
        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图单机版 v1.0 登录");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
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

    private void initView() {
        // 用户名标签
        JLabel usernameLabel = new JLabel(new ImageIcon("src\\image\\login\\用户名.png"));
        usernameLabel.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameLabel);

        // 用户名输入框
        usernameText.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameText);

        // 密码标签
        JLabel passwordLabel = new JLabel(new ImageIcon("src\\image\\login\\密码.png"));
        passwordLabel.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordLabel);

        // 密码输入框
        passwordText.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordText);

        // 验证码标签
        JLabel codeLabel = new JLabel(new ImageIcon("src\\image\\login\\验证码.png"));
        codeLabel.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeLabel);

        // 验证码输入框
        codeText.setBounds(195, 256, 100, 30);
        this.getContentPane().add(codeText);

        // 显示随机生成的验证码
        JLabel rightCode = new JLabel();
        rightCode.setText(codeStr);  // 随机验证码
        rightCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(rightCode);

        // 登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("src\\image\\login\\登录按钮.png"));
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        // 注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("src\\image\\login\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        // 背景
        JLabel background = new JLabel(new ImageIcon("src\\image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

        login.addActionListener(this);
        register.addActionListener(this);

        rightCode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 刷新验证码
                codeStr = CodeUtil.getCode();
                rightCode.setText(codeStr);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==login){
            String username = usernameText.getText();
            String password = new String(passwordText.getPassword());
            String inputCode = codeText.getText();

            // 验证验证码
            if (!inputCode.equalsIgnoreCase(codeStr)) {
                showJDialog("验证码错误！");
                return;
            }

            // 验证用户名和密码
            boolean isUserValid = false;
            for (User user : list) {
                if (user.ID.equals(username) && user.password.equals(password)) {
                    isUserValid = true;
                    break;
                }
            }

            if (isUserValid) {
                showJDialog("登录成功！");
                GameJFrame gameJFrame = new GameJFrame();
                LoginJFrame.super.setVisible(false);
            } else {
                showJDialog("用户名或密码错误！");
            }
        }

        else if(source==register){
            RegisterJFrame registerJFrame = new RegisterJFrame();
            registerJFrame.setAlwaysOnTop(true);
        }
    }
}

