package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class Watch360 extends JFrame {


    private BasicStroke stroke = new BasicStroke(3);
    private Image backgroundImage;
    private Font font = new Font("Serif", Font.BOLD, 20);

    private Image icon;
    public Watch360() {
        // Set the title and size of the frame
        setTitle("Watch360");
        setSize(300, 300);

        icon= new ImageIcon("logo-top.png").getImage();

        setIconImage(icon);
        // Add the dial panel to the frame
        DialPanel dial = new DialPanel();
        add(dial);
        javax.swing.Timer timer = new javax.swing.Timer(1000, (e) -> {
            dial.repaint();
        });
        timer.start();

        // Set the default close operation and display the frame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }

    class DialPanel extends JPanel {
        private int hour, minute, second;
        Timer timer;




        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            backgroundImage = new ImageIcon("back.gif").getImage();
            g2.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            // Get the current time
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            g2.setStroke(stroke);
            // Draw the dial
            int x = getWidth() / 2;
            int y = getHeight() / 2;
            int r = Math.min(x, y);

            g.setColor(Color.white);
            g2.drawOval(x - r, y - r, 2 * r, 2 * r);



            // Draw the hour hand
            int hx = (int) (x + 0.5 * r * Math.sin((hour + minute / 60.0) / 12 * 2 * Math.PI));
            int hy = (int) (y - 0.5 * r * Math.cos((hour + minute / 60.0) / 12 * 2 * Math.PI));
            g.drawLine(x, y, hx, hy);

            // Draw the minute hand
            int mx = (int) (x + 0.8 * r * Math.sin(minute / 60.0 * 2 * Math.PI));
            int my = (int) (y - 0.8 * r * Math.cos(minute / 60.0 * 2 * Math.PI));
            g.drawLine(x, y, mx, my);

            // Draw the second hand
            int sx = (int) (x + 0.9 * r * Math.sin(second / 60.0 * 2 * Math.PI));
            int sy = (int) (y - 0.9 * r * Math.cos(second / 60.0 * 2 * Math.PI));
            g.drawLine(x, y, sx, sy);



            for (int i = 1; i <=12 ; i++) {
                g2.setStroke(stroke);
                g2.setFont(font);
                int tx=(int)(x + 0.8*r*Math.sin((i)/12.0*2*Math.PI));
                int ty=(int)(y-0.8*r*Math.cos((i)/12.0*2*Math.PI));

                g2.drawString(Integer.toString(i),tx,ty);



            }

            g2.setStroke(stroke);
            // code to draw the clock dial, hands, and handle as before
            // code to draw the hour hand
            g2.drawLine(x, y, hx, hy);
            // code to draw the minute hand
            g2.drawLine(x, y, mx, my);
            // code to draw the second hand
            g2.drawLine(x, y, sx, sy);


            int centerCircleX = x - 10;  // top left corner x-coordinate of the bounding rectangle
            int centerCircleY = y - 10;  // top left corner y-coordinate of the bounding rectangle
            int centerCircleWidth = 20;  // width of the bounding rectangle
            int centerCircleHeight = 20;  // height of the bounding rectangle
            g2.setColor(Color.red);
            g2.fillOval(centerCircleX, centerCircleY, centerCircleWidth, centerCircleHeight);



        }



    }



    public static void main(String[] args) {
        Watch360 watch = new Watch360();
    }
}
