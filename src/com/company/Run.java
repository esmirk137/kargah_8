package com.company;

import javax.swing.*;

/**
 * This class just run our code.
 * @author Sayed Mohammad Ali Mirkazemi
 * @version 1.0.0
 * @since April.17.2020
 */
public class Run {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Calculator calculator=new Calculator();
        calculator.show();
    }
}
