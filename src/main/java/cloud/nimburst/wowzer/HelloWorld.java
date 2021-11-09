package cloud.nimburst.wowzer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class HelloWorld {
    private JPanel rootPanel;
    private JLabel helloLabel;
    private JButton helloButton;
    private JList analogInputList;


    public HelloWorld() {


        helloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helloLabel.setText("Hello World");
            }
        });

    }

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = false;

    public static void addComponentsToPane(Container pane) {

        pane.setLayout(new GridBagLayout());
        Gauge driftGauge = new Gauge(0.0, 1.0, 5, 5, 1.5);
        Gauge flutterGauge = new Gauge(0.0, 1.0, 5, 5, 1.5);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        pane.add(driftGauge, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;

        pane.add(flutterGauge, c);


        JRadioButton khz3button = new JRadioButton("3.00");
//        birdButton.setMnemonic(KeyEvent.VK_B);
//        birdButton.setActionCommand(birdString);
        khz3button.setSelected(true);

        JRadioButton khz315button = new JRadioButton("3.15");
//        birdButton.setMnemonic(KeyEvent.VK_B);
//        birdButton.setActionCommand(birdString);
        khz315button.setSelected(false);

        ButtonGroup frequencyGroup = new ButtonGroup();
        frequencyGroup.add(khz3button);
        frequencyGroup.add(khz315button);

        JPanel frequencyPanel = new JPanel(new GridLayout(1, 2));
        frequencyPanel.add(khz315button);
        frequencyPanel.add(khz3button);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;

        pane.add(frequencyPanel, c);

        JRadioButton driftZero = new JRadioButton("ZERO");
        driftZero.setSelected(true);

        JRadioButton driftPoint3 = new JRadioButton("0.3%");;
        driftPoint3.setSelected(false);

        JRadioButton drift1 = new JRadioButton("1%");;
        drift1.setSelected(false);

        JRadioButton drift3 = new JRadioButton("3%");;
        drift3.setSelected(false);

        ButtonGroup driftGroup = new ButtonGroup();
        driftGroup.add(driftZero);
        driftGroup.add(driftPoint3);
        driftGroup.add(drift1);
        driftGroup.add(drift3);

        JPanel driftPanel = new JPanel(new GridLayout(1, 4));
        driftPanel.add(driftZero);
        driftPanel.add(driftPoint3);
        driftPanel.add(drift1);
        driftPanel.add(drift3);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;

        pane.add(driftPanel, c);


        JRadioButton flutterPoint1 = new JRadioButton("0.1%");
        flutterPoint1.setSelected(true);

        JRadioButton flutterPoint3 = new JRadioButton("0.3%");;
        flutterPoint3.setSelected(false);

        JRadioButton flutter1 = new JRadioButton("1%");;
        flutter1.setSelected(false);

        JRadioButton flutter3 = new JRadioButton("3%");;
        flutter3.setSelected(false);

        ButtonGroup flutterGroup = new ButtonGroup();
        flutterGroup.add(flutterPoint1);
        flutterGroup.add(flutterPoint3);
        flutterGroup.add(flutter1);
        flutterGroup.add(flutter3);

        JPanel flutterPanel = new JPanel(new GridLayout(1, 4));
        flutterPanel.add(flutterPoint1);
        flutterPanel.add(flutterPoint3);
        flutterPanel.add(flutter1);
        flutterPanel.add(flutter3);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;

        pane.add(flutterPanel, c);



        java.util.List<MixerLine> inputs = AnalogLineFinder.getAnalogInputs();

        JComboBox<String> inputList = new JComboBox<>(inputs.stream().map(info -> info.getMixer().getName() + " - " + info.getLine().toString()).collect(Collectors.toList()).toArray(new String[0]));
        inputList.setSelectedIndex(1);

        JToggleButton onOff = new JToggleButton("On / Off", false);

        JPanel sourcePanel = new JPanel(new GridBagLayout());

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        sourcePanel.add(inputList, c);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        sourcePanel.add(onOff, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;

        pane.add(sourcePanel, c);


//        JButton button;
//        pane.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        if (shouldFill) {
//            //natural height, maximum width
//            c.fill = GridBagConstraints.HORIZONTAL;
//        }
//
//        button = new JButton("Button 1");
//        if (shouldWeightX) {
//            c.weightx = 0.5;
//        }
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 0;
//        pane.add(button, c);
//
//        button = new JButton("Button 2");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 1;
//        c.gridy = 0;
//        pane.add(button, c);
//
//        button = new JButton("Button 3");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 2;
//        c.gridy = 0;
//        pane.add(button, c);
//
//        button = new JButton("Long-Named Button 4");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 40;      //make this component tall
//        c.weightx = 0.0;
//        c.gridwidth = 3;
//        c.gridx = 0;
//        c.gridy = 1;
//        pane.add(button, c);
//
//        button = new JButton("5");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;       //reset to default
//        c.weighty = 1.0;   //request any extra vertical space
//        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//        c.insets = new Insets(10,0,0,0);  //top padding
//        c.gridx = 1;       //aligned with button 2
//        c.gridwidth = 2;   //2 columns wide
//        c.gridy = 2;       //third row
//        pane.add(button, c);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("WOWzer!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
//
//
//
//        Gauge gauge = new Gauge(0.0, 1.0, 5, 5, 1.5);
//        JPanel pane = new JPanel(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        pane.add(gauge, c);
//
//
//
//
//
//        java.util.List<MixerLine> inputs = AnalogLineFinder.getAnalogInputs();
//
//        JComboBox<String> inputList = new JComboBox<>(inputs.stream().map(info -> info.getMixer().getName() + " - " + info.getLine().toString()).collect(Collectors.toList()).toArray(new String[0]));
//        inputList.setSelectedIndex(1);
//
//        JFrame frame = new JFrame("HelloWorld");
//        frame.setContentPane(inputList);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
}
