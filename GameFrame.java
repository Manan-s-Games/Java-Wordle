import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Arrays;
import java.awt.geom.RoundRectangle2D;
class GameFrame extends JFrame implements KeyListener {
    ImageIcon img1 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Menu.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Settings.png");
    ImageIcon img3 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Title.png");
    ImageIcon img4 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Logo.png");
    ImageIcon img6 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Error.png");
    ImageIcon img7 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\EndButtons.png");
    ImageIcon img8 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Letters.png");
    ImageIcon img9 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\GrayKey.png");
    ImageIcon img10 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\BackSpace.png");
    ImageIcon img11 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Enter.png");
    ImageIcon img12 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\YellowKey.png");
    ImageIcon img13 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\GreenKey.png");
    ImageIcon img14 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\DarkKey.png");
    ImageIcon img15 = new ImageIcon("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Great.png");
    JLabel stats = new JLabel("STATISTICS");
    JLabel played = new JLabel("Played");
    JLabel playedNum = new JLabel();
    JLabel winPercent = new JLabel("Win %");
    JLabel winPerNum = new JLabel();
    JLabel winStr = new JLabel("<html>Current<br/> Streak</html>", SwingConstants.CENTER);
    JLabel winStrNum = new JLabel();
    JLabel MaxStr = new JLabel("<html>  Max<br/>Streak</html>", SwingConstants.CENTER);
    JLabel MaxStrNum = new JLabel();
    JLabel Error = new JLabel (img6);
    JLabel Title = new JLabel(img3);
    JLabel Line = new JLabel();
    JLabel Menu = new JLabel(img1);
    JLabel Settings = new JLabel(img2);
    JLabel EndScreen = new JLabel("", SwingConstants.CENTER);
    JLabel Back = new JLabel("Back123");
    JButton Keys[] = new JButton[28];
    JLabel KeyBack[] = new JLabel[28];
    JButton Replay = new JButton("Replay");
    public String sAns = "";
    public String sAns1 = "Test";
    JLabel Play[][] = new JLabel[6][5];
    public String sWords[] = new String[12972];
    public int RowNum = 0;
    public int ColNum = 0;
    public int nWord;
    Timer timer = new Timer();
    Timer timer2 = new Timer();
    public int counter = 0;
    public int Played = 0;
    public int win = 0;
    public int lose = 0;
    public int[] WinStreak = new int[999999];

    GameFrame() throws IOException{
        setIconImage(img4.getImage());
    for (int j = 0; j < 28; j++) {
        Keys[j] = new JButton();
        Keys[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
        Keys[j].setOpaque(false);
        Keys[j].setContentAreaFilled(false);
        Keys[j].setBorderPainted(false);
        Keys[j].setFocusable(false);
    } 
    for (int j = 0; j < 28; j++) {
        KeyBack[j] = new JLabel(img9);
        if (j<26)
        KeyBack[j].setText(String.valueOf((char)(j+65)));
        KeyBack[j].setVerticalTextPosition(JLabel.CENTER);
        KeyBack[j].setHorizontalTextPosition(JLabel.CENTER);
        KeyBack[j].setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        KeyBack[j].setForeground(Color.WHITE);
    }
    KeyBack[26] = new JLabel(img11);
    KeyBack[27] = new JLabel(img10);
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 5; j++) {
            Play[i][j] = new JLabel("", SwingConstants.CENTER);
        }
    }
    Keys[0].addActionListener(d -> PrintLetter(81));
    Keys[1].addActionListener(d -> PrintLetter(87));
    Keys[2].addActionListener(d -> PrintLetter(69));
    Keys[3].addActionListener(d -> PrintLetter(82));
    Keys[4].addActionListener(d -> PrintLetter(84));
    Keys[5].addActionListener(d -> PrintLetter(89));
    Keys[6].addActionListener(d -> PrintLetter(85));
    Keys[7].addActionListener(d -> PrintLetter(73));
    Keys[8].addActionListener(d -> PrintLetter(79));
    Keys[9].addActionListener(d -> PrintLetter(80));

    Keys[10].addActionListener(d -> PrintLetter(65));
    Keys[11].addActionListener(d -> PrintLetter(83));
    Keys[12].addActionListener(d -> PrintLetter(68));
    Keys[13].addActionListener(d -> PrintLetter(70));
    Keys[14].addActionListener(d -> PrintLetter(71));
    Keys[15].addActionListener(d -> PrintLetter(72));
    Keys[16].addActionListener(d -> PrintLetter(74));
    Keys[17].addActionListener(d -> PrintLetter(75));
    Keys[18].addActionListener(d -> PrintLetter(76));
    
    Keys[19].addActionListener(d -> PrintLetter(90));
    Keys[20].addActionListener(d -> PrintLetter(88));
    Keys[21].addActionListener(d -> PrintLetter(67));
    Keys[22].addActionListener(d -> PrintLetter(86));
    Keys[23].addActionListener(d -> PrintLetter(66));
    Keys[24].addActionListener(d -> PrintLetter(78));
    Keys[25].addActionListener(d -> PrintLetter(77));

    Keys[26].addActionListener(d -> Enter());
    Keys[27].addActionListener(d -> Remove());
    Scanner sin = new Scanner(new FileReader("C:\\Users\\duama\\Documents\\VS Code\\VS Java\\Wordle\\Words.txt"));
    for (int i = 0;; i++) {
        try {
            sWords[i] = sin.nextLine();
        }
        catch (NoSuchElementException err) {
            break;
        }
    }
    stats.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
    stats.setForeground(Color.WHITE);
    playedNum.setFont(new Font("Helvetica Neue", Font.PLAIN, 45));
    playedNum.setForeground(Color.WHITE);
    winPerNum.setFont(new Font("Helvetica Neue", Font.PLAIN, 45));
    winPerNum.setForeground(Color.WHITE);
    winStrNum.setFont(new Font("Helvetica Neue", Font.PLAIN, 45));
    winStrNum.setForeground(Color.WHITE);
    played.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
    played.setForeground(Color.WHITE);
    winPercent.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
    winPercent.setForeground(Color.WHITE);
    winStr.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
    winStr.setForeground(Color.WHITE);
    MaxStr.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
    MaxStr.setForeground(Color.WHITE);
    MaxStrNum.setFont(new Font("Helvetica Neue", Font.PLAIN, 45));
    MaxStrNum.setForeground(Color.WHITE);
    setLocationRelativeTo(null);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setMinimumSize(new Dimension(550, 900));
    //setUndecorated(true);
    setTitle("Wordle");
    setResizable(true);
    setVisible(true);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(18, 18, 19));
    Line.setBackground(new Color(58, 58, 60, 200));
    Line.setOpaque(true);
    EndScreen.setBackground(new Color(18, 18, 19));
    EndScreen.setForeground(Color.WHITE);
    EndScreen.setOpaque(true);
    setLocation();
    StartGame();
    EndScreen.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    EndScreen.setIcon(img7);
    EndScreen.setVerticalTextPosition(JLabel.TOP);
    EndScreen.setHorizontalTextPosition(JLabel.CENTER);
    EndScreen.setIconTextGap(350);
    Replay.setBorderPainted(false);
    Replay.setFocusable(false);
    Replay.setBackground(new Color(18, 18, 19));
    Replay.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
    Replay.setForeground(Color.WHITE);
    Replay.addActionListener(d -> StartGame());
    Replay.setCursor(new Cursor(Cursor.HAND_CURSOR));
    add(MaxStrNum);
    add(winStrNum);
    add(winPerNum);
    add(playedNum);
    add(MaxStr);
    add(winStr);
    add(winPercent);
    add(played);
    add(stats);
    add(Replay);
    add(EndScreen);
    add(Back);
    add(Error);
    add(Line);
    add(Menu);
    add(Settings);
    add(Title);
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 5; j++) {
            add(Play[i][j]);
        }
    }
    for (int i = 0 ;i < 28 ; i++)
        add(Keys[i]);
    for (int i = 0 ;i < 28 ; i++)
        add(KeyBack[i]);
    setVisible(true);
    addKeyListener(this);
    getRootPane().addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            setLocation();
        }
    });
    }
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e) {
        if (Keys[0].isEnabled()) {
            for (int i = 65; i <=90 ; i++) {
                if (e.getKeyCode() == i)
                PrintLetter(i);
            }
            if (e.getKeyCode() == 8) // Backspace
            Remove();
            if (e.getKeyCode() == 10) //Enter
            Enter();
        }
    }
    public void setLocation() {
        Back.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 150), getWidth() * 3));
        Back.setBounds(0, 0, getWidth(), getHeight());
        Menu.setBounds(0, 0, 50, 50);
        Title.setBounds(getWidth()/2 - 70, 0, 120, 50);
        Line.setBounds(0, 55, getWidth(), 1);
        Settings.setBounds(getWidth()-150, 0, 150, 50);
        Keys[0].setBounds(getWidth()/2 - 247, getHeight() - 244, 40, 55);
        Keys[1].setBounds(getWidth()/2 - 198, getHeight() - 244, 40, 55);
        Keys[2].setBounds(getWidth()/2 - 198+49, getHeight() - 244, 40, 55);
        Keys[3].setBounds(getWidth()/2 - 198+49+49, getHeight() - 244, 40, 55);
        Keys[4].setBounds(getWidth()/2 - 198+49+49+49, getHeight() - 244, 40, 55);
        Keys[5].setBounds(getWidth()/2 - 198+49+49+49+49, getHeight() - 244, 40, 55);
        Keys[6].setBounds(getWidth()/2 - 198+49+49+49+49+49, getHeight() - 244, 40, 55);
        Keys[7].setBounds(getWidth()/2 - 198+49+49+49+49+49+49, getHeight() - 244, 40, 55);
        Keys[8].setBounds(getWidth()/2 - 198+49+49+49+49+49+49+49, getHeight() - 244, 40, 55);
        Keys[9].setBounds(getWidth()/2 - 198+49+49+49+49+49+49+49+49, getHeight() - 244, 40, 55);
        Keys[10].setBounds(getWidth()/2 - 247+21, getHeight() - 178, 40, 55);
        Keys[11].setBounds(getWidth()/2 - 247+21+50, getHeight() - 178, 40, 55);
        Keys[12].setBounds(getWidth()/2 - 247+21+50+50, getHeight() - 178, 40, 55);
        Keys[13].setBounds(getWidth()/2 - 247+21+50+50+50, getHeight() - 178, 40, 55);
        Keys[14].setBounds(getWidth()/2 - 247+21+50+50+50+49, getHeight() - 178, 40, 55);
        Keys[15].setBounds(getWidth()/2 - 247+21+50+50+50+50+49, getHeight() - 178, 40, 55);
        Keys[16].setBounds(getWidth()/2 - 247+21+50+50+50+50+49+49, getHeight() - 178, 40, 55);
        Keys[17].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+49+49, getHeight() - 178, 40, 55);
        Keys[18].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+50+49+49, getHeight() - 178, 40, 55);
        Keys[19].setBounds(getWidth()/2 - 247+21+50, getHeight() - 112, 40, 55);
        Keys[20].setBounds(getWidth()/2 - 247+21+50+50, getHeight() - 112, 40, 55);
        Keys[21].setBounds(getWidth()/2 - 247+21+50+50+50, getHeight() - 112, 40, 55);
        Keys[22].setBounds(getWidth()/2 - 247+21+50+50+50+49, getHeight() - 112, 40, 55);
        Keys[23].setBounds(getWidth()/2 - 247+21+50+50+50+50+49, getHeight() - 112, 40, 55);
        Keys[24].setBounds(getWidth()/2 - 247+21+50+50+50+50+49+49, getHeight() - 112, 40, 55);
        Keys[25].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+49+49, getHeight() - 112, 40, 55);
        Keys[26].setBounds(getWidth()/2 - 247, getHeight() - 112, 60, 55);
        Keys[27].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+50+49+49, getHeight() - 112, 60, 60);

        KeyBack[16].setBounds(getWidth()/2 - 247, getHeight() - 244, 46, 59);
        KeyBack[22].setBounds(getWidth()/2 - 198, getHeight() - 244, 46, 59);
        KeyBack[4].setBounds(getWidth()/2 - 198+49, getHeight() - 244, 46, 59);
        KeyBack[17].setBounds(getWidth()/2 - 198+49+49, getHeight() - 244, 46, 59);
        KeyBack[19].setBounds(getWidth()/2 - 198+49+49+49, getHeight() - 244, 46, 59);
        KeyBack[24].setBounds(getWidth()/2 - 198+49+49+49+49, getHeight() - 244, 46, 59);
        KeyBack[20].setBounds(getWidth()/2 - 198+49+49+49+49+49, getHeight() - 244, 46, 59);
        KeyBack[8].setBounds(getWidth()/2 - 198+49+49+49+49+49+49, getHeight() - 244, 46, 59);
        KeyBack[14].setBounds(getWidth()/2 - 198+49+49+49+49+49+49+49, getHeight() - 244, 46, 59);
        KeyBack[15].setBounds(getWidth()/2 - 198+49+49+49+49+49+49+49+49, getHeight() - 244, 46, 59);
        KeyBack[0].setBounds(getWidth()/2 - 247+21, getHeight() - 178, 46, 59);
        KeyBack[18].setBounds(getWidth()/2 - 247+21+50, getHeight() - 178, 46, 59);
        KeyBack[3].setBounds(getWidth()/2 - 247+21+50+50, getHeight() - 178, 46, 59);
        KeyBack[5].setBounds(getWidth()/2 - 247+21+50+50+50, getHeight() - 178, 46, 59);
        KeyBack[6].setBounds(getWidth()/2 - 247+21+50+50+50+49, getHeight() - 178, 46, 59);
        KeyBack[7].setBounds(getWidth()/2 - 247+21+50+50+50+50+49, getHeight() - 178, 46, 59);
        KeyBack[9].setBounds(getWidth()/2 - 247+21+50+50+50+50+49+49, getHeight() - 178, 46, 59);
        KeyBack[10].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+49+49, getHeight() - 178, 46, 59);
        KeyBack[11].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+50+49+49, getHeight() - 178, 46, 59);
        KeyBack[25].setBounds(getWidth()/2 - 247+21+50, getHeight() - 112, 46, 59);
        KeyBack[23].setBounds(getWidth()/2 - 247+21+50+50, getHeight() - 112, 46, 59);
        KeyBack[2].setBounds(getWidth()/2 - 247+21+50+50+50, getHeight() - 112, 46, 59);
        KeyBack[21].setBounds(getWidth()/2 - 247+21+50+50+50+49, getHeight() - 112, 46, 59);
        KeyBack[1].setBounds(getWidth()/2 - 247+21+50+50+50+50+49, getHeight() - 112, 46, 59);
        KeyBack[13].setBounds(getWidth()/2 - 247+21+50+50+50+50+49+49, getHeight() - 112, 46, 59);
        KeyBack[12].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+49+49, getHeight() - 112, 46, 59);
        KeyBack[26].setBounds(getWidth()/2 - 249, getHeight() - 112, 69, 59);
        KeyBack[27].setBounds(getWidth()/2 - 247+21+50+50+50+50+50+50+49+49, getHeight() - 112, 69, 59);

        Play[0][0].setBounds(getWidth()/2 - 172, getHeight()/2 - 219,60, 60);
        Play[0][1].setBounds(getWidth()/2 - 105, getHeight()/2 - 219,60, 60);
        Play[0][2].setBounds(getWidth()/2 - 105+67, getHeight()/2 - 219,60, 60);
        Play[0][3].setBounds(getWidth()/2 - 105+67+67, getHeight()/2 - 219,60, 60);
        Play[0][4].setBounds(getWidth()/2 - 105+67+67+67, getHeight()/2 - 219,60, 60);

        Play[1][0].setBounds(getWidth()/2 - 172, getHeight()/2 - 219+67,60, 60);
        Play[1][1].setBounds(getWidth()/2 - 105, getHeight()/2 - 219+67,60, 60);
        Play[1][2].setBounds(getWidth()/2 - 105+67, getHeight()/2 - 219+67,60, 60);
        Play[1][3].setBounds(getWidth()/2 - 105+67+67, getHeight()/2 - 219+67,60, 60);
        Play[1][4].setBounds(getWidth()/2 - 105+67+67+67, getHeight()/2 - 219+67,60, 60);

        Play[2][0].setBounds(getWidth()/2 - 172, getHeight()/2 - 219+67+67,60, 60);
        Play[2][1].setBounds(getWidth()/2 - 105, getHeight()/2 - 219+67+67,60, 60);
        Play[2][2].setBounds(getWidth()/2 - 105+67, getHeight()/2 - 219+67+67,60, 60);
        Play[2][3].setBounds(getWidth()/2 - 105+67+67, getHeight()/2 - 219+67+67,60, 60);
        Play[2][4].setBounds(getWidth()/2 - 105+67+67+67, getHeight()/2 - 219+67+67,60, 60);

        Play[3][0].setBounds(getWidth()/2 - 172, getHeight()/2 - 219+67+67+67,60, 60);
        Play[3][1].setBounds(getWidth()/2 - 105, getHeight()/2 - 219+67+67+67,60, 60);
        Play[3][2].setBounds(getWidth()/2 - 105+67, getHeight()/2 - 219+67+67+67,60, 60);
        Play[3][3].setBounds(getWidth()/2 - 105+67+67, getHeight()/2 - 219+67+67+67,60, 60);
        Play[3][4].setBounds(getWidth()/2 - 105+67+67+67, getHeight()/2 - 219+67+67+67,60, 60);

        Play[4][0].setBounds(getWidth()/2 - 172, getHeight()/2 - 219+67+67+67+67,60, 60);
        Play[4][1].setBounds(getWidth()/2 - 105, getHeight()/2 - 219+67+67+67+67,60, 60);
        Play[4][2].setBounds(getWidth()/2 - 105+67, getHeight()/2 - 219+67+67+67+67,60, 60);
        Play[4][3].setBounds(getWidth()/2 - 105+67+67, getHeight()/2 - 219+67+67+67+67,60, 60);
        Play[4][4].setBounds(getWidth()/2 - 105+67+67+67, getHeight()/2 - 219+67+67+67+67,60, 60);

        Play[5][0].setBounds(getWidth()/2 - 172, getHeight()/2 - 219+67+67+67+67+67,60, 60);
        Play[5][1].setBounds(getWidth()/2 - 105, getHeight()/2 - 219+67+67+67+67+67,60, 60);
        Play[5][2].setBounds(getWidth()/2 - 105+67, getHeight()/2 - 219+67+67+67+67+67,60, 60);
        Play[5][3].setBounds(getWidth()/2 - 105+67+67, getHeight()/2 - 219+67+67+67+67+67,60, 60);
        Play[5][4].setBounds(getWidth()/2 - 105+67+67+67, getHeight()/2 - 219+67+67+67+67+67,60, 60);

        //EndScreen.setUndecorated(true);
        //EndScreen.setShape(new RoundRectangle2D.Double(getWidth()/2 - 185, getHeight()/2 - 350,360, 550, 20,20));
        Error.setBounds(getWidth()/2 - 90, 60, 150, 100);
        EndScreen.setBounds(getWidth()/2 - 185, getHeight()/2 - 350,360, 550);
        stats.setBounds(getWidth()/2 - 50, getHeight()/2 - 250,100, 50);
        Replay.setBounds(getWidth()/2 - 163, getHeight()/2 + 110,140, 25);

        played.setBounds(getWidth()/2 - 150, getHeight()/2 - 160,100, 50);
        playedNum.setBounds(getWidth()/2 - 140, getHeight()/2 - 230,100, 100);
        winPercent.setBounds(getWidth()/2 - 65, getHeight()/2 - 160,100, 50);
        if (String.valueOf(Math.round((double)win/(double)Played*100)).length() == 3)
        winPerNum.setBounds(getWidth()/2-85, getHeight()/2 - 230,100, 100);
        if (String.valueOf(Math.round((double)win/(double)Played*100)).length() == 2)
        winPerNum.setBounds(getWidth()/2-70, getHeight()/2 - 230,100, 100);
        if (String.valueOf(Math.round((double)win/(double)Played*100)).length() == 1)
        winPerNum.setBounds(getWidth()/2-55, getHeight()/2 - 230,100, 100);
        MaxStrNum.setBounds(getWidth()/2+110, getHeight()/2 - 230,100, 100);
        winStrNum.setBounds(getWidth()/2+25, getHeight()/2 - 230,100, 100);
        winStr.setBounds(getWidth()/2 -10, getHeight()/2 - 160,100, 50);
        MaxStr.setBounds(getWidth()/2 + 80, getHeight()/2 - 160,100, 50);
    }
    public void PrintLetter(int i) {
        if (sAns != sWords[nWord]) {
            if(sAns.length() < 5) {
                Play[RowNum][ColNum].setText(String.valueOf((char)i));
                sAns += Character.toLowerCase((char)i);
                if (ColNum < 4)
                ColNum++;
            }
        }
    }
    public void Enter() {
        if (sAns.length() == 5) {
        if (new ArrayList<>(Arrays.asList(sWords)).contains(sAns)) {
        counter = 0;
        sAns1 = sAns;
        timer2.cancel();
            timer2 = new Timer();
                TimerTask task2 = new TimerTask() {
                   @Override
                    public void run() {
                        if (sWords[nWord].contains(String.valueOf(sAns1.charAt(counter)))) {
                            Play[RowNum][counter].setBackground(new Color(181, 159, 59));
                            Play[RowNum][counter].setBorder(BorderFactory.createLineBorder(new Color(58,58,60), 0));
                        }
                        if (sWords[nWord].charAt(counter) == sAns1.charAt(counter)) {
                            Play[RowNum][counter].setBackground(new Color(83, 141, 78));
                            Play[RowNum][counter].setBorder(BorderFactory.createLineBorder(new Color(58,58,60), 0));
                        }
                        if (!sWords[nWord].contains(String.valueOf(sAns1.charAt(counter)))) {
                            Play[RowNum][counter].setBackground(new Color(58, 58, 60));
                            Play[RowNum][counter].setBorder(BorderFactory.createLineBorder(new Color(58,58,60), 0));
                        }
                        counter++;
                        if (counter == 5) {
                            for (int k = 0 ; k < 5 ; k++) {
                                if (sWords[nWord].contains(String.valueOf(sAns1.charAt(k))) && KeyBack[(int)sAns1.charAt(k) - 97].getIcon() != img13) {
                                    KeyBack[(int)sAns1.charAt(k) - 97].setIcon(img12);
                                }
                                if (sWords[nWord].charAt(k) == sAns1.charAt(k)) {
                                    KeyBack[(int)sAns1.charAt(k) - 97].setIcon(img13);
                                }
                                if (!sWords[nWord].contains(String.valueOf(sAns1.charAt(k)))) {
                                    KeyBack[(int)sAns1.charAt(k) - 97].setIcon(img14);
                                }
                            }
                            if (sAns1.equals(sWords[nWord])) {
                                win++;
                                WinStreak[lose]++;
                                Error.setIcon(img15);
                                ErrorMessage();
                                End("Nice Job!");
                            }
                            else {
                            sAns = "";
                            if (RowNum < 6)
                            RowNum++;
                            ColNum = 0;
                            if (RowNum == 6) {
                                lose++;
                                End("The word was " + sWords[nWord]);
                            }
                            }
                            timer2.cancel();
                        }
                    
                    }
                };
        timer2.scheduleAtFixedRate(task2, 50, 400);
        }
        else {
            Error.setIcon(img6);
            ErrorMessage();
        }
        }
        else {
            Error.setIcon(img8);
            ErrorMessage();
        }
    }
    public void Remove() {
        if (sAns.length() != 0) {
        sAns = sAns.trim().substring(0, sAns.trim().length() - 1).trim();
            if (Play[RowNum][ColNum].getText() == "") {
                if (ColNum > 0)
                ColNum--;
                Play[RowNum][ColNum].setText("");
            }
            else {
                Play[RowNum][ColNum].setText("");
            }
        }
    }
    public void StartGame() {
        Played++;
        for (int i = 0; i < 26; i++) {
            KeyBack[i].setIcon(img9);
        }
        for (int j = 0; j < 28; j++) {
            Keys[j].setEnabled(true);
            Keys[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
        } 
        Replay.setVisible(false);
        stats.setVisible(false);
        Back.setVisible(false);
        EndScreen.setVisible(false);
        Error.setVisible(false);
        played.setVisible(false);
        playedNum.setVisible(false);
        winPercent.setVisible(false);
        MaxStrNum.setVisible(false);
        winStrNum.setVisible(false);
        winPerNum.setVisible(false);
        winStr.setVisible(false);
        MaxStr.setVisible(false);
        RowNum = 0;
        ColNum = 0;
        sAns = "";
        nWord = (int)Math.floor(Math.random() * 12973);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Play[i][j].setText("");
                Play[i][j].setBorder(BorderFactory.createLineBorder(new Color(58,58,60), 2));
                Play[i][j].setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                Play[i][j].setForeground(Color.WHITE);
                Play[i][j].setHorizontalTextPosition(JLabel.CENTER);
                Play[i][j].setVerticalTextPosition(JLabel.CENTER);
                Play[i][j].setBackground(new Color(18, 18, 19));
                Play[i][j].setOpaque(true);
            }
        }
    }
    public void End(String s) {
        for (int j = 0; j < 28; j++) {
            Keys[j].setEnabled(false);
            Keys[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
        } 
        playedNum.setText(String.valueOf(Played));
        winPerNum.setText(String.valueOf(Math.round((double)win/(double)Played*100)));
        winStrNum.setText(String.valueOf(WinStreak[lose]));
        MaxStrNum.setText(String.valueOf(Arrays.stream(WinStreak).max().getAsInt()));
        setLocation();
        MaxStrNum.setVisible(true);
        winStrNum.setVisible(true);
        winPerNum.setVisible(true);
        Replay.setVisible(true);
        Back.setVisible(true);
        EndScreen.setVisible(true);
        EndScreen.setText(s);
        stats.setVisible(true);
        played.setVisible(true);
        playedNum.setVisible(true);
        winPercent.setVisible(true);
        winStr.setVisible(true);
        MaxStr.setVisible(true);
    }

    public void ErrorMessage() {
        timer.cancel();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Error.setVisible(false);
            timer.cancel();
            
            }
        };
        Error.setVisible(true);
        timer.scheduleAtFixedRate(task, 1500, 1500);
    }
}