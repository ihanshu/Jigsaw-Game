package UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //二维数组用于管理图片随机
    int[][] data=new int[4][4];
    //记录空白方块的位置
    int x=0;
    int y=0;
    //游戏图片地址
    String path="src\\image\\animal\\animal3\\";
    //胜利格式
    int[][] win=new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    //步数
    int step=0;

    //选项创建下面的条目

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    JMenuItem girl=new JMenuItem("美女");
    JMenuItem animal=new JMenuItem("动物");
    JMenuItem sport=new JMenuItem("运动");

    //创建主界面
    //属性 行为 统计步数 移动逻辑 通关 查看最终效果
    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据（打乱）
        initData();
        //初始化图片
        initImage();
        //显示界面，一定写在最后
        this.setVisible(true);
    }

    private void initData() {
        int[] tempArr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        Random r= new Random();
        for(int i=0;i<tempArr.length;i++){
            int index=r.nextInt(tempArr.length);
            int temp=tempArr[i];
            tempArr[i]=tempArr[index];
            tempArr[index]=temp;
        }


        for(int i=0;i<tempArr.length;i++){
            if(tempArr[i]==0){
                x=i/4;
                y=i%4;
            }

            data[i/4][i%4]=tempArr[i];

        }
    }

    private void initImage() {
        //清除原本出现的图片（配合末尾的刷新）
        this.getContentPane().removeAll();

        //胜利显示图片
        if(victory()){
            JLabel winLabel=new JLabel(new ImageIcon("src\\image\\win.png"));
            winLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winLabel);
        }

        //显示步数
        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,50,100,20);
        this.getContentPane().add(stepCount);

        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                //创建JLable对象，建议用相对路径，便于不同电脑移植打开
                int number=data[i][j];
                JLabel Jlabel = new JLabel(new ImageIcon(path + number +".jpg"));
                //指定位置
                Jlabel.setBounds(105*j+83, 105*i+134, 105, 105);
                //添加边框(0让图片突起，1让图片凹下）
                Jlabel.setBorder(new BevelBorder(0));
                //添加到界面
                this.getContentPane().add(Jlabel);

            }

        }
        //添加背景
        ImageIcon background=new ImageIcon("src\\image\\background.png");
        JLabel backgroundLabel=new JLabel(background);
        backgroundLabel.setBounds(40,40,508,560);
        this.getContentPane().add(backgroundLabel);

        //刷新
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        //创建整个菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建上面的的选项
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于我们");
        JMenu changeImage=new JMenu("更换图片");


        //加入更换图片中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //条目加入选项
        functionMenu.add(changeImage);
        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);
        aboutMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        //选项加入菜单
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);

        //整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame(){
        this.setSize(603,680);
        this.setTitle("拼图单机版 v1.0");
        //设置置顶
        this.setAlwaysOnTop(true);
        //设置居中
        this.setLocationRelativeTo(null);
        //关闭的模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置，才会按照XY形式添加组件
        this.setLayout(null);
        //给界面添加键盘监听事件
        this.addKeyListener(this);
    }

    public boolean victory(){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
                if(data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        System.out.println("code:"+code);
        if(code==81){
            this.getContentPane().removeAll();
            JLabel all =new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            ImageIcon background=new ImageIcon("src\\image\\background.png");
            JLabel backgroundLabel=new JLabel(background);
            backgroundLabel.setBounds(40,40,508,560);
            this.getContentPane().add(backgroundLabel);
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        //对上下左右进行判断
        //上下左右分别为38、40、37、39
        //WSAD分别为87、83、65、68
        int code=e.getKeyCode();
        System.out.println(code);
        if(code==40||code==83){
            //System.out.println("向上移动");
            if(x==3){
                return;
            }
            //把(x,y)和(x+1,y)交换
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            step++;
            initImage();
        }

        else if(code==38||code==87){
            //System.out.println("向下移动");
            if(x==0){
                return;
            }
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            step++;
            initImage();
        }

        else if(code==39||code==68){
           // System.out.println("向左移动");
            if(y==3){
                return;
            }
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            step++;
            initImage();
        }

        else if(code==37||code==65){
            //System.out.println("向右移动");
            if(y==0){
                return;
            }
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            step++;
            initImage();
        }
        else if (code == 81) {
            initImage();
        }
        else if (code == 69){
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            x=3;
            y=3;
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();

        if(obj==replayItem){
            //System.out.println("重新游戏");
            //步数清零
            step=0;
            //打乱
            initData();
            //重新加载图片
            initImage();
        }
        else if(obj==reLoginItem){
            //System.out.println("重新登录");
            //关闭游戏界面
            this.setVisible(false);
            //打开登录
            new LoginJFrame();
        }
        else if(obj==closeItem){
           //System.out.println("关闭游戏");
            System.exit(0);
        }
        else if(obj==accountItem){
           //System.out.println("公众号");
            JDialog jDialog = new JDialog();
            jDialog.setTitle("关于我们");
            ImageIcon icon = new ImageIcon("src\\image\\about.png");
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
            jDialog.getContentPane().setLayout(null);
            jDialog.getContentPane().add(jLabel);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setSize(jLabel.getWidth() + 20, jLabel.getHeight() + 50);
            jDialog.setVisible(true);
        }
        else if(obj==girl){
            //System.out.println("美女");
            Random r= new Random();
            int a=r.nextInt(13)+1;
            path="src\\image\\girl\\girl"+a+"\\";
            step=0;
            initData();
            initImage();
        }
        else if(obj==animal){
            //System.out.println("动物");
            Random r= new Random();
            int a=r.nextInt(13)+1;
            path="src\\image\\animal\\animal"+a+"\\";
            step=0;
            initData();
            initImage();
        }
        else if(obj==sport){
            //System.out.println("运动");
            Random r= new Random();
            int a=r.nextInt(13)+1;
            path="src\\image\\sport\\sport"+a+"\\";
            step=0;
            initData();
            initImage();
        }
    }
}