package com.company;

import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is a calculator in two mood (simple/advanced).
 * @author Sayed Mohammad Ali Mirkazemi
 * @version 1.0.0
 * @since April.17.2020
 */
public class Calculator {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JLabel labelSimple,labelAdvanced,labelGraph;
    private JButton[] buttonsSimple,buttonsAdvanced,buttonsGraph;
    private int shiftStateAdvanced, shiftStateGraph;

    /**
     * This is constructor of this class and implement our calculator.
     * @throws ClassNotFoundException ???????????????????
     * @throws UnsupportedLookAndFeelException ?????????????
     * @throws InstantiationException ?????????????
     * @throws IllegalAccessException ?????????????
     */
    public Calculator() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        frame=new JFrame("Calculator");
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar=new JMenuBar();
        JMenu application=new JMenu("Application");
        application.setMnemonic('p');
        menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JMenuItem exit=new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        exit.addActionListener(e ->{
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
        JMenuItem copy=new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copy.addActionListener(event ->{
            if(tabbedPane.getSelectedIndex()==0) {
                StringSelection stringSelection=new StringSelection(labelSimple.getText());
                Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection,null);
            }
            else if(tabbedPane.getSelectedIndex()==1) {
                StringSelection stringSelection=new StringSelection(labelAdvanced.getText());
                Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection,null);
            }
            else {
                StringSelection stringSelection=new StringSelection(labelGraph.getText());
                Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection,null);
            }
        });
        application.add(copy); application.add(exit);
        menuBar.add(application);
        frame.setJMenuBar(menuBar);

        JPanel mainPanelSimple = new JPanel();
        mainPanelSimple.setBounds(50,25,350,390);
        mainPanelSimple.setLayout(new GridLayout(2,1));

        JPanel mainPanelAdvanced = new JPanel();
        mainPanelAdvanced.setBounds(50,25,410,390);
        mainPanelAdvanced.setLayout(new GridLayout(2,1));

        JPanel mainPanelGraph = new JPanel();
        mainPanelGraph.setBounds(50,25,410,390);
        mainPanelGraph.setLayout(new GridLayout(2,1));

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Simple", mainPanelSimple);
        tabbedPane.add("Advanced", mainPanelAdvanced);
        tabbedPane.add("Graph", mainPanelGraph);
        frame.add(tabbedPane);

        // simple[
        JPanel panelSimple=new JPanel();
        panelSimple.setLayout(new CardLayout(15,50));
        mainPanelSimple.add(panelSimple);

        labelSimple=new JLabel();
        labelSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        labelSimple.setToolTipText("Label of calculator");

        labelSimple.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panelSimple.add(labelSimple);

        JPanel firstPanelSimple=new JPanel();
        firstPanelSimple.setLayout(new GridLayout(5,4));
        mainPanelSimple.add(firstPanelSimple);

        // button[
        buttonsSimple =new JButton[20];
        for (int i = 0; i<10 ;i++) {
            buttonsSimple[i] = new JButton (""+ i);
            buttonsSimple[i].setFont(new Font("Arial", Font.PLAIN, 30));
            buttonsSimple[i].addActionListener(new buttonActionListener());
            buttonsSimple[i].setToolTipText("Number "+i+" button");
            buttonsSimple[i].addKeyListener(new keyActionListener());
        }

        JButton buttonClearSimple=new JButton("C");
        buttonClearSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonClearSimple.setBackground(Color.BLACK);
        buttonClearSimple.setForeground(Color.GRAY);
        buttonsSimple[10]=buttonClearSimple;
        buttonsSimple[10].addActionListener(new buttonActionListener());
        buttonsSimple[10].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonClearSimple);
        buttonClearSimple.setToolTipText("Clear button and use to clear label");

        JButton buttonParenthesisSimple=new JButton("()");
        buttonParenthesisSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonParenthesisSimple.setBackground(Color.BLACK);
        buttonParenthesisSimple.setForeground(Color.GRAY);
        buttonsSimple[11]=buttonParenthesisSimple;
        buttonsSimple[11].addActionListener(new buttonActionListener());
        buttonsSimple[11].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonParenthesisSimple);
        buttonParenthesisSimple.setToolTipText("set parenthesis");

        JButton buttonRemainingSimple=new JButton("%");
        buttonRemainingSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonRemainingSimple.setBackground(Color.BLACK);
        buttonRemainingSimple.setForeground(Color.GRAY);
        buttonsSimple[12]=buttonRemainingSimple;
        buttonsSimple[12].addActionListener(new buttonActionListener());
        buttonsSimple[12].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonRemainingSimple);
        buttonRemainingSimple.setToolTipText("Remaining button");

        JButton buttonDivisionSimple=new JButton("/");
        buttonDivisionSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonDivisionSimple.setBackground(Color.BLACK);
        buttonDivisionSimple.setForeground(Color.GRAY);
        buttonsSimple[13]=buttonDivisionSimple;
        buttonsSimple[13].addActionListener(new buttonActionListener());
        buttonsSimple[13].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonDivisionSimple);
        buttonDivisionSimple.setToolTipText("Division button");

        firstPanelSimple.add(buttonsSimple[7]);

        firstPanelSimple.add(buttonsSimple[8]);

        firstPanelSimple.add(buttonsSimple[9]);

        JButton buttonProductSimple=new JButton("x");
        buttonProductSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonProductSimple.setBackground(Color.BLACK);
        buttonProductSimple.setForeground(Color.GRAY);
        buttonsSimple[14]=buttonProductSimple;
        buttonsSimple[14].addActionListener(new buttonActionListener());
        buttonsSimple[14].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonProductSimple);
        buttonProductSimple.setToolTipText("Product button");

        firstPanelSimple.add(buttonsSimple[6]);

        firstPanelSimple.add(buttonsSimple[5]);

        firstPanelSimple.add(buttonsSimple[4]);

        JButton buttonSubtractionSimple=new JButton("-");
        buttonSubtractionSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonSubtractionSimple.setBackground(Color.BLACK);
        buttonSubtractionSimple.setForeground(Color.GRAY);
        buttonsSimple[15]=buttonSubtractionSimple;
        buttonsSimple[15].addActionListener(new buttonActionListener());
        buttonsSimple[15].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonSubtractionSimple);
        buttonSubtractionSimple.setToolTipText("Subtraction button");

        firstPanelSimple.add(buttonsSimple[3]);

        firstPanelSimple.add(buttonsSimple[2]);

        firstPanelSimple.add(buttonsSimple[1]);

        JButton buttonSummationSimple=new JButton("+");
        buttonSummationSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonSummationSimple.setBackground(Color.BLACK);
        buttonSummationSimple.setForeground(Color.GRAY);
        buttonsSimple[16]=buttonSummationSimple;
        buttonsSimple[16].addActionListener(new buttonActionListener());
        buttonsSimple[16].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonSummationSimple);
        buttonSummationSimple.setToolTipText("Summation button");

        JButton buttonNegativeSimple=new JButton("+/-");
        buttonNegativeSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonNegativeSimple.setBackground(Color.BLACK);
        buttonNegativeSimple.setForeground(Color.GRAY);
        buttonsSimple[17]=buttonNegativeSimple;
        buttonsSimple[17].addActionListener(new buttonActionListener());
        buttonsSimple[17].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonNegativeSimple);
        buttonNegativeSimple.setToolTipText("This button change sign of number");

        firstPanelSimple.add(buttonsSimple[0]);

        JButton buttonDotSimple=new JButton(".");
        buttonDotSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonDotSimple.setBackground(Color.BLACK);
        buttonDotSimple.setForeground(Color.GRAY);
        buttonsSimple[18]=buttonDotSimple;
        buttonsSimple[18].addActionListener(new buttonActionListener());
        buttonsSimple[18].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonDotSimple);
        buttonDotSimple.setToolTipText("Dot button and use to make float number");

        JButton buttonEqualSimple=new JButton("=");
        buttonEqualSimple.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonEqualSimple.setBackground(Color.BLUE);
        buttonEqualSimple.setForeground(Color.WHITE);
        buttonsSimple[19]=buttonEqualSimple;
        buttonsSimple[19].addActionListener(new buttonActionListener());
        buttonsSimple[19].addKeyListener(new keyActionListener());
        firstPanelSimple.add(buttonEqualSimple);
        buttonEqualSimple.setToolTipText("Equal button show answer");
        // button]
        // simple]

        // advanced[
        JPanel panelAdvanced=new JPanel();
        panelAdvanced.setLayout(new CardLayout(15,50));
        mainPanelAdvanced.add(panelAdvanced);

        labelAdvanced=new JLabel();
        labelAdvanced.setFont(new Font("Arial", Font.PLAIN, 30));
        labelSimple.setToolTipText("Label of calculator");

        labelAdvanced.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panelAdvanced.add(labelAdvanced);

        JPanel firstPanelAdvanced=new JPanel();
        firstPanelAdvanced.setLayout(new GridLayout(5,5));
        mainPanelAdvanced.add(firstPanelAdvanced);
        // button[

        buttonsAdvanced=new JButton[25];
        for (int i = 0;i<10;i++) {
            buttonsAdvanced[i] = new JButton (""+ i);
            buttonsAdvanced[i].setFont(new Font("Arial", Font.PLAIN, 20));
            buttonsAdvanced[i].addActionListener(new buttonActionListener());
            buttonsAdvanced[i].setToolTipText("Number "+i+" button");
            buttonsAdvanced[i].addKeyListener(new keyActionListener());
        }
        shiftStateAdvanced=0;

        JButton buttonShiftAdvanced=new JButton("Shift");
        buttonShiftAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonShiftAdvanced.setBackground(Color.RED);
        buttonShiftAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[20]=buttonShiftAdvanced;
        buttonsAdvanced[20].addActionListener(new buttonActionListener());
        buttonsAdvanced[20].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonShiftAdvanced);
        buttonShiftAdvanced.setToolTipText("Change all button in it's column");

        JButton buttonClearAdvanced=new JButton("C");
        buttonClearAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonClearAdvanced.setBackground(Color.BLACK);
        buttonClearAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[10]=buttonClearAdvanced;
        buttonsAdvanced[10].addActionListener(new buttonActionListener());
        buttonsAdvanced[10].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonClearAdvanced);
        buttonClearAdvanced.setToolTipText("Clear button and use to clear label");

        JButton buttonParenthesisAdvanced=new JButton("()");
        buttonParenthesisAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonParenthesisAdvanced.setBackground(Color.BLACK);
        buttonParenthesisAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[11]=buttonParenthesisAdvanced;
        buttonsAdvanced[11].addActionListener(new buttonActionListener());
        buttonsAdvanced[11].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonParenthesisAdvanced);
        buttonParenthesisAdvanced.setToolTipText("set parenthesis");

        JButton buttonRemainingAdvanced=new JButton("%");
        buttonRemainingAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonRemainingAdvanced.setBackground(Color.BLACK);
        buttonRemainingAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[12]=buttonRemainingAdvanced;
        buttonsAdvanced[12].addActionListener(new buttonActionListener());
        buttonsAdvanced[12].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonRemainingAdvanced);
        buttonRemainingAdvanced.setToolTipText("Remaining button");

        JButton buttonDivisionAdvanced=new JButton("/");
        buttonDivisionAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonDivisionAdvanced.setBackground(Color.BLACK);
        buttonDivisionAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[13]=buttonDivisionAdvanced;
        buttonsAdvanced[13].addActionListener(new buttonActionListener());
        buttonsAdvanced[13].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonDivisionAdvanced);
        buttonDivisionAdvanced.setToolTipText("Division button");

        JButton buttonConstantAdvanced=new JButton("\u03C0");
        buttonConstantAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonConstantAdvanced.setBackground(Color.CYAN);
        buttonConstantAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[21]=buttonConstantAdvanced;
        buttonsAdvanced[21].addActionListener(new buttonActionListener());
        firstPanelAdvanced.add(buttonConstantAdvanced);
        buttonConstantAdvanced.setToolTipText("Constant \u03C0=3.141592.../e=2.718281...");

        firstPanelAdvanced.add(buttonsAdvanced[7]);

        firstPanelAdvanced.add(buttonsAdvanced[8]);

        firstPanelAdvanced.add(buttonsAdvanced[9]);

        JButton buttonProductAdvanced=new JButton("x");
        buttonProductAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonProductAdvanced.setBackground(Color.BLACK);
        buttonProductAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[14]=buttonProductAdvanced;
        buttonsAdvanced[14].addActionListener(new buttonActionListener());
        buttonsAdvanced[14].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonProductAdvanced);
        buttonProductAdvanced.setToolTipText("Product button");

        JButton buttonSinCosAdvanced=new JButton("sin");
        buttonSinCosAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonSinCosAdvanced.setBackground(Color.CYAN);
        buttonSinCosAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[22]=buttonSinCosAdvanced;
        buttonsAdvanced[22].addActionListener(new buttonActionListener());
        buttonsAdvanced[22].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonSinCosAdvanced);
        buttonSinCosAdvanced.setToolTipText("Sin/Cos function");

        firstPanelAdvanced.add(buttonsAdvanced[6]);

        firstPanelAdvanced.add(buttonsAdvanced[5]);

        firstPanelAdvanced.add(buttonsAdvanced[4]);

        JButton buttonSubtractionAdvanced=new JButton("-");
        buttonSubtractionAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonSubtractionAdvanced.setBackground(Color.BLACK);
        buttonSubtractionAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[15]=buttonSubtractionAdvanced;
        buttonsAdvanced[15].addActionListener(new buttonActionListener());
        buttonsAdvanced[15].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonSubtractionAdvanced);
        buttonSubtractionAdvanced.setToolTipText("Subtraction button");

        JButton buttonTanCotAdvanced=new JButton("tan");
        buttonTanCotAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonTanCotAdvanced.setBackground(Color.CYAN);
        buttonTanCotAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[23]=buttonTanCotAdvanced;
        buttonsAdvanced[23].addActionListener(new buttonActionListener());
        firstPanelAdvanced.add(buttonTanCotAdvanced);
        buttonTanCotAdvanced.setToolTipText("Tan/Cot function");

        firstPanelAdvanced.add(buttonsAdvanced[3]);

        firstPanelAdvanced.add(buttonsAdvanced[2]);

        firstPanelAdvanced.add(buttonsAdvanced[1]);

        JButton buttonSummationAdvanced=new JButton("+");
        buttonSummationAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonSummationAdvanced.setBackground(Color.BLACK);
        buttonSummationAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[16]=buttonSummationAdvanced;
        buttonsAdvanced[16].addActionListener(new buttonActionListener());
        buttonsAdvanced[16].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonSummationAdvanced);
        buttonSummationAdvanced.setToolTipText("Summation button");

        JButton buttonExponentialLogarithmAdvanced=new JButton("e^");
        buttonExponentialLogarithmAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonExponentialLogarithmAdvanced.setBackground(Color.CYAN);
        buttonExponentialLogarithmAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[24]=buttonExponentialLogarithmAdvanced;
        buttonsAdvanced[24].addActionListener(new buttonActionListener());
        buttonsAdvanced[24].addActionListener(new buttonActionListener());
        firstPanelAdvanced.add(buttonExponentialLogarithmAdvanced);
        buttonExponentialLogarithmAdvanced.setToolTipText("Exponential/Logarithm function");

        JButton buttonNegativeAdvanced=new JButton("+/-");
        buttonNegativeAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonNegativeAdvanced.setBackground(Color.BLACK);
        buttonNegativeAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[17]=buttonNegativeAdvanced;
        buttonsAdvanced[17].addActionListener(new buttonActionListener());
        buttonsAdvanced[17].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonNegativeAdvanced);
        buttonNegativeAdvanced.setToolTipText("This button change sign of number");

        firstPanelAdvanced.add(buttonsAdvanced[0]);

        JButton buttonDotAdvanced=new JButton(".");
        buttonDotAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonDotAdvanced.setBackground(Color.BLACK);
        buttonDotAdvanced.setForeground(Color.GRAY);
        buttonsAdvanced[18]=buttonDotAdvanced;
        buttonsAdvanced[18].addActionListener(new buttonActionListener());
        buttonsAdvanced[18].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonDotAdvanced);
        buttonDotAdvanced.setToolTipText("Dot button and use to make float number");

        JButton buttonEqualAdvanced=new JButton("=");
        buttonEqualAdvanced.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonEqualAdvanced.setBackground(Color.BLUE);
        buttonEqualAdvanced.setForeground(Color.WHITE);
        buttonsAdvanced[19]=buttonEqualAdvanced;
        buttonsAdvanced[19].addActionListener(new buttonActionListener());
        buttonsAdvanced[19].addKeyListener(new keyActionListener());
        firstPanelAdvanced.add(buttonEqualAdvanced);
        buttonEqualAdvanced.setToolTipText("Equal button show answer");
        // button]
        // advanced]

        //Graph[
        JPanel panelGraph=new JPanel();
        panelGraph.setLayout(new CardLayout(15,50));
        mainPanelGraph.add(panelGraph);

        labelGraph=new JLabel();
        labelGraph.setFont(new Font("Arial", Font.PLAIN, 30));
        labelGraph.setPreferredSize(new Dimension(10,50));

        labelGraph.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panelGraph.add(labelGraph);

        JPanel firstPanelGraph=new JPanel();
        firstPanelGraph.setLayout(new GridLayout(5,5));
        mainPanelGraph.add(firstPanelGraph);
        // button[

        buttonsGraph=new JButton[25];
        for (int i = 0;i<10;i++) {
            buttonsGraph[i] = new JButton (""+ i);
            buttonsGraph[i].setFont(new Font("Arial", Font.PLAIN, 20));
            buttonsGraph[i].addActionListener(new buttonActionListener());
        }
        shiftStateGraph=0;

        JButton buttonShiftGraph=new JButton("Shift");
        buttonShiftGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonShiftGraph.setBackground(Color.RED);
        buttonShiftGraph.setForeground(Color.GRAY);
        buttonsGraph[20]=buttonShiftGraph;
        buttonsGraph[20].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonShiftGraph);


        JButton buttonClearGraph=new JButton("C");
        buttonClearGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonClearGraph.setBackground(Color.BLACK);
        buttonClearGraph.setForeground(Color.GRAY);
        buttonsGraph[10]=buttonClearGraph;
        buttonsGraph[10].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonClearGraph);

        JButton buttonParenthesisGraph=new JButton("()");
        buttonParenthesisGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonParenthesisGraph.setBackground(Color.BLACK);
        buttonParenthesisGraph.setForeground(Color.GRAY);
        buttonsGraph[11]=buttonParenthesisGraph;
        buttonsGraph[11].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonParenthesisGraph);

        JButton buttonEqualGraph=new JButton("=");
        buttonEqualGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonEqualGraph.setBackground(Color.BLACK);
        buttonEqualGraph.setForeground(Color.GRAY);
        buttonsGraph[12]=buttonEqualGraph;
        buttonsGraph[12].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonEqualGraph);

        JButton buttonDivisionGraph=new JButton("/");
        buttonDivisionGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonDivisionGraph.setBackground(Color.BLACK);
        buttonDivisionGraph.setForeground(Color.GRAY);
        buttonsGraph[13]=buttonDivisionGraph;
        buttonsGraph[13].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonDivisionGraph);

        JButton buttonConstantGraph=new JButton("\u03C0");
        buttonConstantGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonConstantGraph.setBackground(Color.CYAN);
        buttonConstantGraph.setForeground(Color.GRAY);
        buttonsGraph[21]=buttonConstantGraph;
        buttonsGraph[21].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonConstantGraph);

        firstPanelGraph.add(buttonsGraph[7]);

        firstPanelGraph.add(buttonsGraph[8]);

        firstPanelGraph.add(buttonsGraph[9]);

        JButton buttonProductGraph=new JButton("x");
        buttonProductGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonProductGraph.setBackground(Color.BLACK);
        buttonProductGraph.setForeground(Color.GRAY);
        buttonsGraph[14]=buttonProductGraph;
        buttonsGraph[14].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonProductGraph);

        JButton buttonSinCosGraph=new JButton("sin");
        buttonSinCosGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonSinCosGraph.setBackground(Color.CYAN);
        buttonSinCosGraph.setForeground(Color.GRAY);
        buttonsGraph[22]=buttonSinCosGraph;
        buttonsGraph[22].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonSinCosGraph);

        firstPanelGraph.add(buttonsGraph[6]);

        firstPanelGraph.add(buttonsGraph[5]);

        firstPanelGraph.add(buttonsGraph[4]);

        JButton buttonSubtractionGraph=new JButton("-");
        buttonSubtractionGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonSubtractionGraph.setBackground(Color.BLACK);
        buttonSubtractionGraph.setForeground(Color.GRAY);
        buttonsGraph[15]=buttonSubtractionGraph;
        buttonsGraph[15].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonSubtractionGraph);

        JButton buttonTanCotGraph=new JButton("tan");
        buttonTanCotGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonTanCotGraph.setBackground(Color.CYAN);
        buttonTanCotGraph.setForeground(Color.GRAY);
        buttonsGraph[23]=buttonTanCotGraph;
        buttonsGraph[23].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonTanCotGraph);

        firstPanelGraph.add(buttonsGraph[3]);

        firstPanelGraph.add(buttonsGraph[2]);

        firstPanelGraph.add(buttonsGraph[1]);

        JButton buttonSummationGraph=new JButton("+");
        buttonSummationGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonSummationGraph.setBackground(Color.BLACK);
        buttonSummationGraph.setForeground(Color.GRAY);
        buttonsGraph[16]=buttonSummationGraph;
        buttonsGraph[16].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonSummationGraph);

        JButton buttonExponentialLogarithmGraph=new JButton("e^");
        buttonExponentialLogarithmGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonExponentialLogarithmGraph.setBackground(Color.CYAN);
        buttonExponentialLogarithmGraph.setForeground(Color.GRAY);
        buttonsGraph[24]=buttonExponentialLogarithmGraph;
        buttonsGraph[24].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonExponentialLogarithmGraph);

        JButton buttonVariableGraph=new JButton("X");
        buttonVariableGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonVariableGraph.setBackground(Color.BLACK);
        buttonVariableGraph.setForeground(Color.GRAY);
        buttonsGraph[17]=buttonVariableGraph;
        buttonsGraph[17].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonVariableGraph);

        firstPanelGraph.add(buttonsGraph[0]);

        JButton buttonDotGraph=new JButton(".");
        buttonDotGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonDotGraph.setBackground(Color.BLACK);
        buttonDotGraph.setForeground(Color.GRAY);
        buttonsGraph[18]=buttonDotGraph;
        buttonsGraph[18].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonDotGraph);

        JButton buttonEnterGraph=new JButton("Enter");
        buttonEnterGraph.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonEnterGraph.setBackground(Color.BLUE);
        buttonEnterGraph.setForeground(Color.WHITE);
        buttonsGraph[19]=buttonEnterGraph;
        buttonsGraph[19].addActionListener(new buttonActionListener());
        firstPanelGraph.add(buttonEnterGraph);
        // button]
        //Graph]

    }

    /**
     * This method show our calculator.
     */
    public void show(){
        frame.setVisible(true);
    }

    /**
     * This method show a character is a numeric character or not.
     * @param character is a input character to check
     * @return answer as boolean
     */
    private boolean isNumericCharacter(char character){
        return (int)character-48<10 && (int)character-48>=0;
    }

    /**
     * This method return indexes of open/close parentheses .
     * @param text is text of our label
     * @return indexes as a array of integer with two element
     */
    private int[] indexOfParentheses(ArrayList<Character> text){
        int[] index=new int[2];
        for(int i=0; i<text.size(); i++){
            if(text.get(i)=='(') index[0]=i;
            if(text.get(i)==')') {
                index[1]=i;
                return index;
            }
        }
        return index;
    }

    /**
     * This method check some condition with "And" logic symbol for set character to label.
     * @param text is text of label as array list of character
     * @return a boolean as answer
     */
    private boolean setProductOrNotAndVersion(ArrayList<Character> text){
        return !isNumericCharacter(text.get(text.size()-1)) && text.get(text.size()-1)!=')' && text.get(text.size()-1)!='e' && text.get(text.size()-1)!='\u03C0' && text.get(text.size()-1)!='X' && text.get(text.size()-1)!='Y';
    }

    /**
     * This method check some condition with "Or" logic symbol for set character to label.
     * @param text is text of label as array list of character
     * @return a boolean as answer
     */
    private boolean setProductOrNotOrVersion(ArrayList<Character> text){
        return isNumericCharacter(text.get(text.size()-1)) || text.get(text.size()-1)==')' || text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y';
    }

    /**
     * Get a number from label digit by digit.
     * @param text is text of label as array list of character
     * @param numbers is a arrayList of double number that store the number
     * @param i is i'th character of text of label
     * @return i parameter
     */
    private int getNumber(ArrayList<Character> text,ArrayList<Double> numbers, int i){
        double valueBeforeDot=0, valueAfterDot=0;
        int counter=0;
        boolean isDot=false;
        do{
            if(!isDot)
                valueBeforeDot=10*valueBeforeDot+(int)text.get(i)-48;
            else{
                valueAfterDot=10*valueAfterDot+(int)text.get(i)-48;
                counter++;
            }
            if(++i==text.size()) break;
            if(text.get(i)=='.'){
                i++;
                isDot=true;
            }
        }while (isNumericCharacter(text.get(i)));
        i--;
        valueBeforeDot+=valueAfterDot/Math.pow(10,counter);
        numbers.add(valueBeforeDot);
        return i;
    }

    /**
     * This method return final answer as double.
     * @param text is text of label as array list of character
     * @param numbersInTheParentheses is values of parentheses in the text label
     * @return final integer as double
     */
    private double lastEqualAction(ArrayList<Character> text, ArrayList<Double> numbersInTheParentheses, int start, int end, boolean inParentheses){
        ArrayList<Double> numbers=new ArrayList<>();
        ArrayList<Character> operators=new ArrayList<>();
        int from, upto;
        if(inParentheses){
            from=start+1;
            upto=end;
        }
        else {
            from=0;
            upto=text.size();
        }
        for(int i=from; i<upto; i++){
            if(text.get(i)=='a')
                numbers.add(numbersInTheParentheses.get((int) text.get(++i) - 48));
            else if(isNumericCharacter(text.get(i)))
                i=getNumber(text,numbers,i);
            else if(text.get(i)=='e'){
                if (text.get(i+1) == '^') {
                    i+=2;
                    if(text.get(i)=='a')
                        numbers.add(Math.exp(numbersInTheParentheses.get((int) text.get(++i) - 48)));
                    else if(isNumericCharacter(text.get(i)))
                        i=getNumber(text,numbers,i);
                }
                else
                    numbers.add(Math.E);
            }
            else if(text.get(i)=='\u03C0'){
                numbers.add(Math.PI);
            }
            else if(text.get(i)=='s'){
                i+=3;
                if(text.get(i)=='a')
                    numbers.add(Math.sin(numbersInTheParentheses.get((int) text.get(++i) - 48)));
                else if(isNumericCharacter(text.get(i)))
                    i=getNumber(text,numbers,i);
            }
            else if(text.get(i)=='c'){
                i+=3;
                if(text.get(i)=='a')
                    numbers.add(Math.cos(numbersInTheParentheses.get((int) text.get(++i) - 48)));
                else if(isNumericCharacter(text.get(i)))
                    i=getNumber(text,numbers,i);
            }
            else if(text.get(i)=='t'){
                i+=3;
                if(text.get(i)=='a')
                    numbers.add(Math.tan(numbersInTheParentheses.get((int) text.get(++i) - 48)));
                else if(isNumericCharacter(text.get(i)))
                    i=getNumber(text,numbers,i);
            }
            else if(text.get(i)=='o'){
                i+=3;
                if(text.get(i)=='a')
                    numbers.add(1/Math.tan(numbersInTheParentheses.get((int) text.get(++i) - 48)));
                else if(isNumericCharacter(text.get(i)))
                    i=getNumber(text,numbers,i);
            }
            else
                operators.add(text.get(i));
        }
        for(int i=0; i<operators.size(); i++){
            if(operators.get(i)=='%'){
                numbers.set(i,numbers.get(i)%numbers.get(i+1));
                numbers.remove(i+1);
                operators.remove(i--);
            }
        }
        for(int i=0; i<operators.size(); i++){
            if(operators.get(i)=='x'){
                numbers.set(i,numbers.get(i)*numbers.get(i+1));
                numbers.remove(i+1);
                operators.remove(i--);
            }
            else if(operators.get(i)=='/'){
                numbers.set(i,numbers.get(i)/numbers.get(i+1));
                numbers.remove(i+1);
                operators.remove(i--);
            }

        }
        double value=numbers.get(0);
        for(int i=0; i<operators.size(); i++){
            if(operators.get(i)=='+')
                value+=numbers.get(i+1);
            else if(operators.get(i)=='-')
                value-=numbers.get(i+1);
        }
        return value;
    }


    /**
     * This method do action of parenthesis.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void parenthesis(JLabel label, ArrayList<Character> text){
        if(label.getText().length()==0 || text.get(text.size()-1)=='('){
            label.setText(label.getText() + '(');
            return;
        }
        if(text.get(text.size()-1)=='.'){
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int numberOfOpenParenthesis=0, numberOfCloseParenthesis=0;
        for(char i:text){
            if(i=='(') numberOfOpenParenthesis++;
            else if(i==')') numberOfCloseParenthesis++;
        }
        if(setProductOrNotAndVersion(text))
            label.setText(label.getText()+"(");
        else if(numberOfCloseParenthesis!=numberOfOpenParenthesis)
            label.setText(label.getText()+")");
        else
            label.setText(label.getText()+"x(");
    }

    /**
     * This method do action of remaining.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void remaining(JLabel label, ArrayList<Character> text){
        if(label.getText().length()==0)
            JOptionPane.showMessageDialog(frame,"Invalid input.","Error",JOptionPane.ERROR_MESSAGE);
        else if(label.getText().toCharArray()[label.getText().length()-1]=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(setProductOrNotAndVersion(text)){
            text.set(text.size()-1,'%');
            StringBuilder s=new StringBuilder();
            for(char i: text){
                s.append(i);
            }
            label.setText(s.toString());
        }
        else
            label.setText(label.getText()+'%');
    }

    /**
     * This method do action of division.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void division(JLabel label, ArrayList<Character> text){
        if(label.getText().length()==0)
            JOptionPane.showMessageDialog(frame, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(label.getText().toCharArray()[label.getText().length()-1]=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(setProductOrNotAndVersion(text)){
            text.set(text.size()-1,'/');
            StringBuilder s=new StringBuilder();
            for(char i: text){
                s.append(i);
            }
            label.setText(s.toString());
        }
        else
            label.setText(label.getText()+'/');
    }

    /**
     * This method do action of product.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void product(JLabel label, ArrayList<Character> text){
        if(label.getText().length()==0)
            JOptionPane.showMessageDialog(frame,"Invalid input.","Error",JOptionPane.ERROR_MESSAGE);
        else if(label.getText().toCharArray()[label.getText().length()-1]=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(setProductOrNotAndVersion(text)){
            text.set(text.size()-1,'x');
            StringBuilder s=new StringBuilder();
            for(char i: text){
                s.append(i);
            }
            label.setText(s.toString());
        }
        else
            label.setText(label.getText()+'x');
    }

    /**
     * This method do action of subtraction.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void subtraction(JLabel label, ArrayList<Character> text){
        if(label.getText().length()==0)
            JOptionPane.showMessageDialog(frame,"Invalid input.","Error",JOptionPane.ERROR_MESSAGE);
        else if(label.getText().toCharArray()[label.getText().length()-1]=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(setProductOrNotAndVersion(text)){
            text.set(text.size()-1,'-');
            StringBuilder s=new StringBuilder();
            for(char i: text){
                s.append(i);
            }
            label.setText(s.toString());
        }
        else
            label.setText(label.getText()+'-');
    }

    /**
     * This method do action of summation.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void summation(JLabel label, ArrayList<Character> text){
        if(text.size()==0)
            JOptionPane.showMessageDialog(frame,"Invalid input.","Error",JOptionPane.ERROR_MESSAGE);
        else if(label.getText().toCharArray()[label.getText().length()-1]=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(setProductOrNotAndVersion(text)){
            text.set(text.size()-1,'+');
            StringBuilder s=new StringBuilder();
            for(char i: text){
                s.append(i);
            }
            label.setText(s.toString());
        }
        else
            label.setText(label.getText()+'+');
    }

    /**
     * This method do action of negative.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void negative(JLabel label, ArrayList<Character> text){
        if(text.size()==0)
            label.setText(label.getText()+"(-");
        else if(text.get(text.size()-1)==')'){
            label.setText(label.getText()+"x(-");
        }
        else if(isNumericCharacter(text.get(text.size()-1)) || text.get(text.size()-1)=='.'){
            int counter=0;
            do{
                counter++;
                if(text.size()-1-counter<0){
                    counter--;
                    break;
                }
            }while (isNumericCharacter(text.get(text.size()-1-counter)) || text.get(text.size()-1-counter)=='.');
            text.add(text.size()-1-counter,'(');
            text.add(text.size()-1-counter,'-');
            StringBuilder s=new StringBuilder();
            for(char i: text){
                s.append(i);
            }
            label.setText(s.toString());
        }
        else
            label.setText(label.getText()+"(-");
    }

    /**
     * This method do action of dot.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void dot(JLabel label, ArrayList<Character> text){
        if(text.size()==0)
            label.setText(label.getText() + "0.");
        else if(text.get(label.getText().length()-1)=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(text.get(text.size()-1)==')' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='e' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y')
            label.setText(label.getText() + "x0.");
        else {
            int counter=1;
            while (isNumericCharacter(text.get(label.getText().length()-counter))){
                if(label.getText().length()-counter==0) break;
                counter++;
                if(text.get(label.getText().length()-counter)=='.'){
                    JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            label.setText(label.getText() + ".");
        }
    }

    /**
     * This method do initial equals process such as fix close, open parenthesis.
     * @param text is text of label as array list of character
     */
    private void initialEqualsProcess(ArrayList<Character> text) {
        if(text.size()==0)
            JOptionPane.showMessageDialog(frame,"Empty label.","Error",JOptionPane.ERROR_MESSAGE);
        if(text.get(0)=='-')
            text.add(0,'0');
        int openParentheses=0, closeParentheses=0;
        for (int i=0; i<text.size() ;i++) {
            if (text.get(i) == '('){
                if(text.get(i+1)=='-')
                    text.add(i+++1,'0');
                openParentheses++;
            }
            if (text.get(i) == ')') closeParentheses++;
        }
        while (openParentheses!=closeParentheses){
            text.add(')');
            closeParentheses++;
        }
    }

    /**
     * This method calculate value of a parenthesis and finally value.
     * @param text is text of label as array list of character
     * @return a double as value
     */
    private double calculateValue(@NotNull ArrayList<Character> text){
        ArrayList<Double> numbersInTheParenthesis=new ArrayList<>();
        int counter=47;
        while(text.contains('(')) {
            counter++;
            int[] indexes=indexOfParentheses(text);
            numbersInTheParenthesis.add(lastEqualAction(text,numbersInTheParenthesis,indexes[0],indexes[1],true));
            text.set(indexes[0],'a');
            text.set(indexes[1],(char)counter);
            if (indexes[1] > indexes[0] + 1) {
                text.subList(indexes[0] + 1, indexes[1]).clear();
            }
        }
        return lastEqualAction(text,numbersInTheParenthesis,0,0,false);
    }

    /**
     * This method do action of equal.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void equal(JLabel label, ArrayList<Character> text){
        initialEqualsProcess(text);
        double value=calculateValue(text);
        if((int)value==value)
            label.setText(""+(int)value);
        else
            label.setText(""+value);
    }

    /**
     * This method do action of constant number include Pi and exponential.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void constantNumber(JLabel label, ArrayList<Character> text){
        if(label.getText().length()==0)
            label.setText(buttonsAdvanced[21].getText());
        else if(text.get(label.getText().length()-1)=='.')
            JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
        else if(setProductOrNotOrVersion(text))
            label.setText(label.getText()+"x"+buttonsAdvanced[21].getText());
        else
            label.setText(label.getText()+buttonsAdvanced[21].getText());
    }

    /**
     * This method do action of sin / cos function.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void sinCos(JLabel label, ArrayList<Character> text){
        if(text.size()==0)
            label.setText(buttonsAdvanced[22].getText()+"(");
        else if(setProductOrNotOrVersion(text))
            label.setText(label.getText()+"x"+buttonsAdvanced[22].getText()+"(");
        else
            label.setText(label.getText()+buttonsAdvanced[22].getText()+"(");
    }

    /**
     * This method do action of tan / cot function.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void tanCot(JLabel label, ArrayList<Character> text){
        if(text.size()==0)
            label.setText(buttonsAdvanced[23].getText()+"(");
        else if(setProductOrNotOrVersion(text))
            label.setText(label.getText()+"x"+buttonsAdvanced[23].getText()+"(");
        else
            label.setText(label.getText()+buttonsAdvanced[23].getText()+"(");
    }

    /**
     * This method do action of exponential / log function.
     * @param label is label of calculator
     * @param text is text of label as array list of character
     */
    private void exponentialLog(JLabel label, ArrayList<Character> text){
        if(text.size()==0)
            label.setText(buttonsAdvanced[24].getText() + "(");
        else if(setProductOrNotOrVersion(text))
            label.setText(label.getText() + "x" + buttonsAdvanced[24].getText() + "(");
        else
            label.setText(label.getText() + buttonsAdvanced[24].getText() + "(");
    }

    /**
     * This method change state of buttons.
     * @param shiftState is state of shift button
     * @param buttons is buttons of this panel
     * @param isGraph is a boolean that show
     * @return an integer as state of shift
     */
    private int shiftChanging(int shiftState, JButton[] buttons, boolean isGraph){
        if(shiftState==0){
            buttons[21].setText("e");
            buttons[22].setText("cos");
            buttons[23].setText("cot");
            buttons[24].setText("log");
            if(isGraph) buttons[17].setText("Y");
            return 1;
        }
        else{
            buttons[21].setText("\u03C0");
            buttons[22].setText("sin");
            buttons[23].setText("tan");
            buttons[24].setText("e^");
            if(isGraph) buttons[17].setText("X");
            return 0;
        }
    }

    /**
     * This class implement KeyListener class for button of calculator.
     */
    private class keyActionListener implements KeyListener {
        @Override
        public void keyReleased(KeyEvent keyEvent) {}
        @Override
        public void keyTyped(KeyEvent keyEvent) {}

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            JLabel label;
            ArrayList<Character> text=new ArrayList<>();
            if(tabbedPane.getSelectedIndex()==0)
                label=labelSimple;
            else if(tabbedPane.getSelectedIndex()==1)
                label=labelAdvanced;
            else
                label=labelGraph;
            if(label.getText().equals("Infinity")) label.setText("");
            for(char i:label.getText().toCharArray())
                text.add(i);
            if(keyEvent.isShiftDown() && keyEvent.getKeyCode() == KeyEvent.VK_8){
                product(label, text);
            }
            else if(keyEvent.isShiftDown() && (keyEvent.getKeyCode() == KeyEvent.VK_9 || keyEvent.getKeyCode() == KeyEvent.VK_0)){
                parenthesis(label, text);
            }
            else if(keyEvent.isShiftDown() && keyEvent.getKeyCode()==KeyEvent.VK_5){
                remaining(label, text);
            }
            else if(keyEvent.isShiftDown() && keyEvent.getKeyCode()==KeyEvent.VK_5){
                remaining(label, text);
            }
            else if(keyEvent.isShiftDown() && keyEvent.getKeyCode() == KeyEvent.VK_EQUALS){
                summation(label, text);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_0){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 0);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_1){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 1);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_2){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 2);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_3){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 3);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_4){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 4);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_5){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 5);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_6){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 6);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_7){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 7);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_8){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 8);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_9){
                if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                    label.setText(label.getText() + 'x');
                label.setText(label.getText() + 9);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_C){
                if(tabbedPane.getSelectedIndex()==0)
                    labelSimple.setText("");
                else if(tabbedPane.getSelectedIndex()==1)
                    labelAdvanced.setText("");
                else
                    labelGraph.setText("");
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_SLASH) {
                division(label, text);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_MINUS){
                subtraction(label, text);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_N){
                negative(label, text);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_PERIOD){
                dot(label, text);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_EQUALS){
                equal(label, text);
            }
            else if(keyEvent.getKeyCode() == KeyEvent.VK_SHIFT){
                shiftChanging(shiftStateAdvanced,buttonsAdvanced,false);
            }

        }


    }

    /**
     * This class implement ActionListener class for button of calculator.
     */
    private class buttonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JLabel label;
            ArrayList<Character> text=new ArrayList<>();
            if(tabbedPane.getSelectedIndex()==0)
                label=labelSimple;
            else if(tabbedPane.getSelectedIndex()==1)
                label=labelAdvanced;
            else
                label=labelGraph;
            if(label.getText().equals("Infinity")) label.setText("");
            for(char i:label.getText().toCharArray())
                text.add(i);
            for(int i=0; i<10; i++) // numbers
                if(actionEvent.getSource().equals(buttonsSimple[i]) || actionEvent.getSource().equals(buttonsAdvanced[i]) || actionEvent.getSource().equals(buttonsGraph[i])) {
                    if(text.size()!=0 && (text.get(text.size()-1)==')'|| text.get(text.size()-1)=='e' || text.get(text.size()-1)=='\u03C0' || text.get(text.size()-1)=='X' || text.get(text.size()-1)=='Y'))
                        label.setText(label.getText() + 'x');
                    label.setText(label.getText() + i);
                    return;
                }
            if(actionEvent.getSource().equals(buttonsSimple[10]) || actionEvent.getSource().equals(buttonsAdvanced[10]) || actionEvent.getSource().equals(buttonsGraph[10])) { // Clear button
                label.setText("");
            }
            else if(actionEvent.getSource().equals(buttonsSimple[11]) || actionEvent.getSource().equals(buttonsAdvanced[11]) || actionEvent.getSource().equals(buttonsGraph[11])) { // Parenthesis button
                parenthesis(label,text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[12]) || actionEvent.getSource().equals(buttonsAdvanced[12])) { // Remaining button
                remaining(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[13]) || actionEvent.getSource().equals(buttonsAdvanced[13]) || actionEvent.getSource().equals(buttonsGraph[13])) { // Division button
                division(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[14]) || actionEvent.getSource().equals(buttonsAdvanced[14]) || actionEvent.getSource().equals(buttonsGraph[14])) { // Product button
                product(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[15]) || actionEvent.getSource().equals(buttonsAdvanced[15]) || actionEvent.getSource().equals(buttonsGraph[15])) { // Subtraction button
                subtraction(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[16]) || actionEvent.getSource().equals(buttonsAdvanced[16]) || actionEvent.getSource().equals(buttonsGraph[16])) { // Summation button
                summation(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[17]) || actionEvent.getSource().equals(buttonsAdvanced[17])) { // Negative button
                negative(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[18]) || actionEvent.getSource().equals(buttonsAdvanced[18]) || actionEvent.getSource().equals(buttonsGraph[18])) { // Dot button
                dot(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsSimple[19]) || actionEvent.getSource().equals(buttonsAdvanced[19])) { // Equal button
                equal(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsAdvanced[20])){ // Shift button
                shiftStateAdvanced=shiftChanging(shiftStateAdvanced,buttonsAdvanced,false);
            }
            else if(actionEvent.getSource().equals(buttonsGraph[20])){
                shiftStateGraph=shiftChanging(shiftStateGraph,buttonsGraph,true);
            }
            else if(actionEvent.getSource().equals(buttonsAdvanced[21]) || actionEvent.getSource().equals(buttonsGraph[21])){ // Pi & neperian number button
                constantNumber(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsAdvanced[22]) || actionEvent.getSource().equals(buttonsGraph[22])){ // sin & cos button
                sinCos(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsAdvanced[23]) || actionEvent.getSource().equals(buttonsGraph[23])){ // tan & cot button
                tanCot(label, text);
            }
            else if(actionEvent.getSource().equals(buttonsAdvanced[24]) || actionEvent.getSource().equals(buttonsGraph[24])){ // e^ & log  button
                exponentialLog(label, text);
            }
            /*
            else if(actionEvent.getSource().equals(buttonsGraph[12])){
                if(label.getText().length()==0)
                    JOptionPane.showMessageDialog(frame,"Invalid input.","Error",JOptionPane.ERROR_MESSAGE);
                else {
                    if (label.getText().toCharArray()[label.getText().length() - 1] == '.')
                        JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
                    else if (setProductOrNotAndVersion(text)) {
                        text.set(text.size() - 1, '=');
                        StringBuilder s = new StringBuilder();
                        for (char i : text) {
                            s.append(i);
                        }
                        label.setText(s.toString());
                    }
                    else if(text.contains('='))
                        JOptionPane.showMessageDialog(frame,"Invalid input.","Error",JOptionPane.ERROR_MESSAGE);
                    else
                        label.setText(label.getText() + '=');
                }

            }



            else if(actionEvent.getSource().equals(buttonsGraph[17])){
                if(label.getText().length()==0)
                    label.setText(buttonsGraph[17].getText());
                else if(text.get(label.getText().length()-1)=='.')
                    JOptionPane.showMessageDialog(frame, "Invalid input please enter an integer number.", "Error", JOptionPane.ERROR_MESSAGE);
                else if(setProductOrNotOrVersion(text))
                    label.setText(label.getText()+"x"+buttonsGraph[17].getText());
                else
                    label.setText(label.getText()+buttonsGraph[17].getText());
            }

             */

            /*
            else if(actionEvent.getSource().equals(buttonsGraph[19])){ //enter
                initialEqualsProcess(text);
                if(text.contains('=')){
                    int index=text.indexOf('=');
                    text.remove(index);
                    text.add(index,'(');
                    text.add(index,'-');
                    text.add(')');
                }
                else {
                    text.add('-');
                    text.add('y');
                }
                for(char c: text){
                    System.out.print(c);
                }
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                JFrame frame=new JFrame("Graph");
                frame.setSize(400, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel panel=new JPanel();
                panel.setBounds(50,25,350,390);
                panel.setLayout(null);
                frame.add(panel);
                double min=-30, max=30, step=0.5;
                double XValue=min;
                double f_X, f_XPlusH, f_XMinusH;
                while (XValue<=max){
                    double YValue=0;
                    int counter=0;
                    while (counter++<13) {
                        // f[x] {
                        f_X = fixXValueAndCalculate(label, XValue, YValue);
                        // f[x] }

                        // f[x+h] {
                        initialEqualsProcess(text);
                        f_XPlusH=fixXValueAndCalculate(label, XValue, YValue+1e-8);
                        // f[x+h] }

                        // f[x-h] {
                        initialEqualsProcess(text);
                        f_XMinusH=fixXValueAndCalculate(label, XValue, YValue-1e-8);
                        // f[x-h] }
                        YValue=YValue-f_X/((f_XPlusH-f_XMinusH)/(2*1e-8));
                    }
                    Graphics g=panel.getGraphics();
                    g.drawLine((int)XValue+30,(int)YValue+30,(int)XValue+30,(int)YValue+30);
                    XValue+=step;
                }
                frame.setVisible(true);
            }
            */
        }



        /*
        private double fixXValueAndCalculate(@NotNull JLabel label, double XValue, double YValue) {
            ArrayList<Character> text=new ArrayList<>();
            for(Character c:label.getText().toCharArray())
                text.add(c);
            if(!text.contains('=')){
                text.add('=');
                text.add('y');
            }
            for(int i=0; i<text.size(); i++){
                if(text.get(i)=='X'){
                    for(char c: text)
                        System.out.print(c);
                    System.out.println();
                    text.remove(i++);
                    for(Character c: String.valueOf(XValue).toCharArray())
                        text.set(i,c);
                }
            }
            for(int i=0; i<text.size(); i++){
                if(text.get(i)=='X'){
                    text.remove(i++);
                    for(Character c: String.valueOf(YValue).toCharArray())
                        text.set(i,c);
                }
            }
            return calculateValue(text);
        }

         */




    }


}
