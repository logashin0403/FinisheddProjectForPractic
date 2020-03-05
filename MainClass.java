package programm;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainClass implements ActionListener {

    static JFrame jFrm; JPanel mainPanel; static JSlider jsHorizontal1;
    static JSlider jsHorizontal2; static JSlider jsHorizontal3; int red;
    int green; int blue; static JButton butColor;
    static Color mainColor; BufferedImage finishPicture;
    FileOutputStream fos;


    MainClass() {

        mainPanel = new JPanel();
        obyav();
    }

    public void obyav() {
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(77, 86, 85));
//        Image name = new ImageIcon("C:\\Users\\Эдик" +
//                "\\IdeaProjects\\FinishedProjectForPractic\\name.jpg").getImage();
        JLabel name = new JLabel(new ImageIcon("C:\\Users\\Эдик" +
                "\\IdeaProjects\\FinishedProjectForPractic\\name.jpg"));

        GridBagConstraints forFirstPage = new GridBagConstraints();
        forFirstPage.ipady = 36;
        forFirstPage.insets = new Insets(0, 0, 120, 0);
        forFirstPage.gridwidth = GridBagConstraints.REMAINDER;

        JButton jBtnNew = new JButton("Новый холст");
        JButton jBtnExit = new JButton("Выход");
        List<JButton> buttons = new ArrayList<>();
        buttons.add(jBtnNew);
        buttons.add(jBtnExit);
        for (JButton but : buttons) {

            but.setBorder(new RoundedBorder(10));
            but.setForeground(new Color(255, 80, 51));
            but.setBackground(new Color(73, 82, 81));
            but.setFocusable(false);
            but.addActionListener(this);
        }

        jBtnNew.setSize(170, 30);
        jBtnNew.setLocation(25, 5);
        jBtnExit.setSize(170, 30);
        jBtnExit.setLocation(405, 5);

        mainPanel.add(name, forFirstPage);
        forFirstPage.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(jBtnNew, forFirstPage);
        mainPanel.add(jBtnExit, forFirstPage);
        mainPanel.setOpaque(true);
        jFrm.setContentPane(mainPanel);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("Выход")) {

            System.exit(0);
        }

        else if (ae.getActionCommand().equals("Сохранить")) {

            Dimension size = jFrm.getSize();
            finishPicture = new BufferedImage(size.width, size.height,
                    BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = finishPicture.getGraphics();
            jFrm.paint(g);
            g.dispose();
            Random randNum = new Random();
            String nameForPicture = "";
            for (int i = 0; i < 5; i++){
                int rand = (int) (1 + Math.random()*8);
                nameForPicture = nameForPicture + String.valueOf(rand);
            }
            int height = finishPicture.getHeight();
            int width = finishPicture.getWidth();

            int targetWidth = width - 721;
            int targetHeight = height - 352;
            int xc = 360;
            int yc = 57;

            BufferedImage croppedImage = finishPicture.getSubimage(
                    xc,
                    yc,
                    targetWidth,
                    targetHeight
            );

            String username = System.getProperty("user.name");
            String fileName = "picture" + nameForPicture + ".png";
            String filePath = "C://Users//" + username + "//Desktop";
            File myFile = new File(filePath + "//////" + fileName);
            try {
                fos = new FileOutputStream(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {


                ImageIO.write(croppedImage, "png", fos);

            }
            catch (IOException ex)
            {
                ex.printStackTrace ();
            }

        }

        else if (ae.getActionCommand().equals("Новый холст")) {

            mainPanel.removeAll();

            jsHorizontal1 = new JSlider(JSlider.HORIZONTAL, 0, 255, 1);
            jsHorizontal2 = new JSlider(JSlider.HORIZONTAL, 0, 255, 1);
            jsHorizontal3 = new JSlider(JSlider.HORIZONTAL, 0, 255, 1);

            List<JSlider> jSliders = new ArrayList<>();
            jSliders.add(jsHorizontal1);
            jSliders.add(jsHorizontal2);
            jSliders.add(jsHorizontal3);
            for (JSlider jSlider : jSliders) {
                jSlider.setBackground(new Color(73, 82, 81));
                jSlider.setForeground(new Color(255, 80, 51));
                jSlider.setMajorTickSpacing(51);
                jSlider.setMinorTickSpacing(0);
                jSlider.setPaintTicks(true);
                jSlider.setPaintLabels(true);
                jSlider.setBorder(BorderFactory.createEtchedBorder());
                jSlider.addChangeListener(new JSliderHandler());
            }

            GridBagConstraints c1 = new GridBagConstraints();

            c1.fill = GridBagConstraints.HORIZONTAL;
            c1.ipady = 0;       //reset to default
            c1.weighty = 1.0;   //request any extra vertical space
            c1.anchor = GridBagConstraints.PAGE_END; //bottom of space
            c1.insets = new Insets(0, -400, -261, 100);  //top padding
            c1.gridx = 0;       //aligned with button 2
            c1.gridwidth = 2;   //2 columns wide

            GridBagConstraints c2 = new GridBagConstraints();

            c2.fill = GridBagConstraints.HORIZONTAL;
            c2.ipady = 0;       //reset to default
            c2.weighty = 1.0;   //request any extra vertical space
            c2.anchor = GridBagConstraints.PAGE_END; //bottom of space
            c2.insets = new Insets(0, -100, -211, 500);  //top padding
            c2.gridx = 1;       //aligned with button 2
            c2.gridwidth = 2;   //2 columns wide

            GridBagConstraints c3 = new GridBagConstraints();

            c3.fill = GridBagConstraints.HORIZONTAL;
            c3.ipady = 0;       //reset to default
            c3.weighty = 1.0;   //request any extra vertical space
            c3.anchor = GridBagConstraints.PAGE_END; //bottom of space
            c3.insets = new Insets(0, 100, -161, 300);  //top padding
            c3.gridx = 2;       //aligned with button 2
            c3.gridwidth = 2;   //2 columns wide

            butColor = new JButton();
            butColor.setBorder(new RoundedBorder(5));
            butColor.setForeground(new Color(255, 80, 51));
            butColor.setEnabled(false);

            GridBagConstraints c4 = new GridBagConstraints();

            c4.fill = GridBagConstraints.HORIZONTAL;
            c4.ipady = 36;//reset to default
            c4.anchor = GridBagConstraints.PAGE_END; //bottom of space
            c4.insets = new Insets(0, 330, -112, 231);  //top padding
            c4.gridx = 2;       //aligned with button 2
            c4.gridwidth = 2;   //2 columns wide
            c4.weighty = 1;

            JButton butSave = new JButton("Сохранить");
            butSave.setBorder(new RoundedBorder(10));
            butSave.setForeground(new Color(255, 80, 51));
            butSave.setBackground(new Color(73, 82, 81));
            butSave.setFocusable(false);
            butSave.addActionListener(this);

            GridBagConstraints c5 = new GridBagConstraints();

            c5.fill = GridBagConstraints.HORIZONTAL;
            c5.ipady = 22;       //reset to default
            c5.weighty = 1.0;   //request any extra vertical space
            c5.anchor = GridBagConstraints.LAST_LINE_END; //bottom of space
            c5.insets = new Insets(0, 560, -1, -50);  //top padding
            c5.gridx = 2;       //aligned with button 2
            c5.gridwidth = 2;   //2 columns wide
            c5.weighty = 1;


            mainPanel.add(jsHorizontal1, c1);
            mainPanel.add(jsHorizontal2, c2);
            mainPanel.add(jsHorizontal3, c3);
            mainPanel.add(butColor, c4);
            mainPanel.add(butSave, c5);
            mainPanel.add(new TestPane());
            jFrm.setContentPane(mainPanel);

        }
    }

    public static void main(String[] args) {

        jFrm = new JFrame("Kirsuno");
        jFrm.setBounds(100, 10, 1200, 830);
        jFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrm.setResizable(false);
        jFrm.setVisible(true);
        new MainClass();
    }

    class JSliderHandler implements ChangeListener {

        public void stateChanged(ChangeEvent ce) {

            mainColor = new Color(jsHorizontal1.getValue(), jsHorizontal2.getValue(),
                    jsHorizontal3.getValue());
            butColor.setBackground(mainColor);

        }
    }

    public Color getMainColor() {
        return mainColor;
    }
}


