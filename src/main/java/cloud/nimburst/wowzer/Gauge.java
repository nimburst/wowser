package cloud.nimburst.wowzer;

import java.awt.*;

import static java.awt.RenderingHints.*;

import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Gauge extends JPanel {

    private static final int BORDER = 6;
    private static final int GAUGE_WIDTH = 280;
    private static final int GAUGE_HEIGHT = 110;
    private static final int DIAL_LENGTH = 200;
    private static final int DIAL_ARC_OFFSET = 24;
    private static final double MAX_NORMAL_DEFLECTION_DEG = 38.0;
    private static final double LABEL_OFFSET = 3.2;
    private static final double MAX_ABSOLUTE_DEFLECTION_DEG = 44.0;

    private static final double DEGREE = Math.PI * 2.0 / 360.0;

    private static double twentyNineDeg = Math.PI * 2.0 / 360.0 * 29.0;
    private static double twoDeg = Math.PI * 2.0 / 360.0 * 2.0;
    private static double level = 0.0;
    private double min;
    private double max;
    private int numberedDivisions;
    private int divisionsPerNumberedDivision;
    private double value;

    public Gauge(double min, double max, int numberedDivisions, int divisionsPerNumberedDivision, double value) {
        setPreferredSize(new Dimension(BORDER * 2 + GAUGE_WIDTH, BORDER * 2 + GAUGE_HEIGHT));
        this.min = min;
        this.max = max;
        this.numberedDivisions = numberedDivisions;
        this.value = value;
        this.divisionsPerNumberedDivision = divisionsPerNumberedDivision;
    }

    public void paintComponent(Graphics g2D) {
        Graphics2D g = (Graphics2D) g2D;
        g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(BORDER, BORDER, GAUGE_WIDTH, GAUGE_HEIGHT);
        Rectangle2D mask = new Rectangle2D.Float();
        mask.setRect(BORDER, BORDER, GAUGE_WIDTH, GAUGE_HEIGHT);
        g.setClip(mask);

//        g.translate(BORDER + BORDER + DIAL_LENGTH,BORDER + BORDER + DIAL_LENGTH);
//        g.drawOval(GAUGE_WIDTH / 2 - DIAL_LENGTH + BORDER, BORDER + BORDER, DIAL_LENGTH * 2, DIAL_LENGTH * 2);

        final int centerX = BORDER + (GAUGE_WIDTH / 2);
        final int centerY = (BORDER * 2) + DIAL_LENGTH;

        g.drawArc(centerX - DIAL_LENGTH + DIAL_ARC_OFFSET, centerY - DIAL_LENGTH + DIAL_ARC_OFFSET, DIAL_LENGTH * 2 - DIAL_ARC_OFFSET * 2, DIAL_LENGTH * 2 - DIAL_ARC_OFFSET * 2, 90 - (int) MAX_NORMAL_DEFLECTION_DEG, (int)(2.0 * MAX_NORMAL_DEFLECTION_DEG));


        g.translate(centerX, centerY);
        AffineTransform txform = g.getTransform();
        final double degreesPerDivision = (DEGREE * MAX_NORMAL_DEFLECTION_DEG * 2.0) / (double) numberedDivisions;
        final double valuePerDivision = (max - min) / (double) numberedDivisions;
        for (int i = 0; i <= numberedDivisions; i++) {
            if(i == 0){
                g.rotate(DEGREE * -MAX_NORMAL_DEFLECTION_DEG);
            } else {
                g.rotate(degreesPerDivision);
            }

            g.drawLine(0, -DIAL_LENGTH + ( DIAL_ARC_OFFSET * 2), 0, -DIAL_LENGTH);
            if(i < numberedDivisions) {
                AffineTransform divTxform = g.getTransform();
                for (int subDiv = 1; subDiv < divisionsPerNumberedDivision; subDiv++) {
                    double toRotate = degreesPerDivision / divisionsPerNumberedDivision;
                    g.rotate(toRotate);
                    int length = DIAL_ARC_OFFSET;
                    int end = DIAL_LENGTH - length / 2;
                    g.drawLine(0, -(end - length), 0, -end);
                }
                g.setTransform(divTxform);
            }

        }

        g.setTransform(txform);
        for (int i = 0; i <= numberedDivisions; i++) {
            if(i == 0){
                g.rotate(DEGREE * -MAX_NORMAL_DEFLECTION_DEG - (LABEL_OFFSET * DEGREE));
            } else {
                g.rotate(degreesPerDivision);
            }

            double labelValue = (double) i * valuePerDivision + min;
            g.drawString(String.format("%.1f",labelValue), 0, -DIAL_LENGTH + BORDER);
        }

        g.setTransform(txform);
        double needlePercent =  2.0 * (value - min ) / (max - min);
        if(needlePercent < 1.0) {
            needlePercent = -1.0 * (1.0 - needlePercent);
        }  else {
            needlePercent = needlePercent - 1.0;
        }
        double deflection = MAX_NORMAL_DEFLECTION_DEG * needlePercent;
        if(deflection < 0) {
            deflection = Math.max(deflection, -MAX_ABSOLUTE_DEFLECTION_DEG);
        } else {
            deflection = Math.min(deflection, MAX_ABSOLUTE_DEFLECTION_DEG);
        }
        g.rotate(DEGREE * deflection);


        g.drawLine(0, 0, 0, -DIAL_LENGTH);


//        g.translate(150,150);
//        AffineTransform txform = g.getTransform();
//        g.rotate(-twoDeg);
//        for (int i=0; i<11; i++) {
//            g.rotate(twentyNineDeg);
//            g.drawString(Integer.toString(i),0,-125);
//        }
//        g.setTransform(txform);
//        g.fillOval(-3,-3,6,6);
//        g.rotate(twentyNineDeg);
//        g.rotate(twentyNineDeg * level);
//        g.drawLine(0,0,0,-120);
//        g.drawLine(0,-120,5,-110);
//        g.drawLine(0,-120,-5,-110);
    }

//    public static void main(String... args) {
//        EventQueue.invokeLater(() -> {
//            JFrame frame = new JFrame("Gauge");
//            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            Gauge gauge = new Gauge(0.0, 1.0, 5, 0.0);
//            frame.add(gauge, BorderLayout.CENTER);
////            JSlider slider = new JSlider(0,100,1);
////            Hashtable<Integer,JLabel> labelMap = new Hashtable<>();
////            for (int i=0; i<=100; i+=10)
////                labelMap.put(i,new JLabel(Integer.toString(i / 10)));
////            slider.setLabelTable(labelMap);
////            slider.setPaintLabels(true);
////            slider.addChangeListener(event -> {
////                gauge.level = slider.getValue() / 10.0;
////                gauge.repaint();
////            });
////            frame.add(slider,BorderLayout.SOUTH);
//            frame.pack();
//            frame.setVisible(true);
//
//
//            frame.setContentPane(new Gauge());
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.pack();
//            frame.setVisible(true);
//
//        });
//    }
}