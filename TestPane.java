package programm;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestPane extends JPanel {

    MainClass mainClass = new MainClass();


    public TestPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 40; col++) {
                gbc.gridx = col;
                gbc.gridy = row;


                CellPane cellPane = new CellPane();

                Border border = null;
                if (row < 39) {
                    if (col < 39) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < 39) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                add(cellPane, gbc);
            }
        }

    }

    public class CellPane extends JPanel {

        public CellPane() {

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        setBackground(mainClass.getMainColor());
                    }
                    else if (e.getButton() == MouseEvent.BUTTON3) {

                        mainClass.jsHorizontal1.setValue(getBackground().getRed());
                        mainClass.jsHorizontal2.setValue(getBackground().getGreen());
                        mainClass.jsHorizontal3.setValue(getBackground().getBlue());
                        mainClass.butColor.setBackground(getBackground());
                    }
                }
            });

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(12, 12);
        }
    }
}
