package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс графического интерфейса
 */
public class CalculatorGUI {
    private JPanel calculatorPanel;
    private JButton num0Button;

    private JButton num1Button;
    private JButton num2Button;
    private JButton num3Button;
    private JButton num4Button;
    private JButton num5Button;
    private JButton num6Button;
    private JButton num7Button;
    private JButton num8Button;
    private JButton num9Button;
    private JButton additionButton;
    private JButton subtractionButton;
    private JButton multiplicationButton;
    private JButton divisionButton;
    private JButton equalityButton;
    private JButton decimalPointButton;
    private JButton openBracketButton;
    private JButton closeBracketButton;
    private JButton eraseButton;
    private JButton clearButton;
    private JTextField expressionField;
    private JTextField check;
    private ArrayList<String> operationMarks;
    private int notClosedBracketCount = 0;
    private Calculator calculator;

    public CalculatorGUI() {
        operationMarks = new ArrayList<>();
        operationMarks.addAll(Arrays.asList("+","-","*","/"));  //знаки операций
        calculator = new Calculator();
    }

    public void createFrame()
    {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(calculatorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setMinimumSize(new Dimension(500,500));
        frame.pack();
        frame.setVisible(true);
        frame.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                frame.requestFocusInWindow();
            }
        });
        ArrayList<Integer> numPadKeyCodesOfOperations = new ArrayList<>(Arrays.asList(106, 107, 109, 111)); //знаки операций на цифровой клавиатуре

        frame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                check.setText(Integer.toString(keyCode));
                if(e.isShiftDown()){
                    if(keyCode == 57){      //открывающаяся скобка
                        addOpenBracket();
                    } else if(keyCode == 48){       //закрывающаяся скобка
                        addCloseBracket();
                    } else if(keyCode == 61){       //плюс на основной клавиатуре
                        addOperation("+");
                    }
                } else if(keyCode >=48 && keyCode <=57){        //цифры на основной клавиатуре
                    String keyText = KeyEvent.getKeyText(keyCode);
                    addNum(keyText);
                } else if(keyCode >=96 && keyCode <= 105){       //цифры на цифровой клавиатуре
                    int length = KeyEvent.getKeyText(keyCode).length();
                    String keyText = KeyEvent.getKeyText(keyCode).substring(length-1);
                    addNum(keyText);
                } else if(keyCode == 8){  //Backspace
                    erase();
                } else if(keyCode == 10 || keyCode == 61){ //Enter и знак равенства
                    equality();
                } else if(numPadKeyCodesOfOperations.contains(keyCode)){    //знаки операций на цифровой клавиатуре
                    String keyText = KeyEvent.getKeyText(keyCode).substring(KeyEvent.getKeyText(keyCode).length() - 1);
                    addOperation(keyText);
                } else if(keyCode == 45){       //минус на основной клавиатуре
                    addOperation("-");
                } else if(keyCode == 47){       //деление на основной клавиатуре
                    addOperation("/");
                } else if(keyCode == 46 || keyCode == 110){     //точка на основной и цифровой клавиатуре
                    addDecimalPoint();
                } else if(keyCode == 127){
                    expressionField.setText(""); // кнопка Delete
                }
            }
        });

        num0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("0");
            }
        });
        num1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("1");
            }
        });
        num2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("2");
            }
        });
        num3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("3");
            }
        });
        num4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("4");
            }
        });
        num5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("5");
            }
        });
        num6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("6");
            }
        });
        num7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("7");
            }
        });
        num8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("8");
            }
        });
        num9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNum("9");
            }
        });
        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation("+");
            }
        });
        subtractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation("-");
            }
        });
        multiplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation("*");
            }
        });
        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperation("/");
            }
        });
        decimalPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDecimalPoint();
            }
        });
        openBracketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOpenBracket();
            }
        });
        closeBracketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCloseBracket();
            }
        });
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erase();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expressionField.setText("");
            }
        });
        equalityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equality();
            }
        });
    }

    private void addNum(String num)
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        Integer stringLength = expressionField.getText().length();
        if(stringLength == 1){
            String lastSymbol = expressionField.getText().substring(stringLength-1);
            if(!lastSymbol.equals("0")){
                expressionField.setText(expressionField.getText() + num);
            }
        } else if (stringLength >= 2) {
            String lastSymbol = expressionField.getText().substring(stringLength-1);
            String penultimateSymbol = expressionField.getText().substring(stringLength-2,stringLength-1);
            if(!lastSymbol.equals("0") || !operationMarks.contains(penultimateSymbol)){
                expressionField.setText(expressionField.getText() + num);
            }
        } else {
            expressionField.setText(expressionField.getText() + num);
        }
    }

    private void addOperation(String operationMark)
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        Integer stringLength = expressionField.getText().length();
        if(stringLength != 0){
            String lastSymbol = expressionField.getText().substring(stringLength-1);
            if (!operationMarks.contains(lastSymbol) && !lastSymbol.equals(".")){
                if(operationMark.equals("-")){
                    expressionField.setText(expressionField.getText() + operationMark);
                } else if (!lastSymbol.equals("(")){
                    expressionField.setText(expressionField.getText() + operationMark);
                }
            } else if(stringLength > 1) {
                String penultimateSymbol = expressionField.getText().substring(stringLength-2,stringLength-1);
                if(!penultimateSymbol.equals("(")){
                    String expression = expressionField.getText();
                    expressionField.setText(expression.substring(0,expression.length()-1) + operationMark);
                }
            }
        } else if(operationMark.equals("-")){
            expressionField.setText(expressionField.getText() + "0-");
        }
    }

    private void addDecimalPoint()
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        Integer stringLength = expressionField.getText().length();
        boolean check = true;
        if (stringLength != 0){
            for (int i = 1; i < stringLength ; i++){
                String symbolToCheck = expressionField.getText().substring(stringLength-i,stringLength-i+1);
                if((i==1) && operationMarks.contains(symbolToCheck)){
                    check = false;
                    break;
                }
                if(operationMarks.contains(symbolToCheck)){
                    break;
                }
                if(symbolToCheck.equals(".")){
                    check = false;
                    break;
                }
            }
            if (check){
                expressionField.setText(expressionField.getText() + ".");
            }
        }
    }

    private void addOpenBracket()
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        Integer stringLength = expressionField.getText().length();
        if (stringLength !=0){
            String lastSymbol = expressionField.getText().substring(stringLength-1);
            if(operationMarks.contains(lastSymbol) || lastSymbol.equals("(")){
                expressionField.setText(expressionField.getText() + "(");
                notClosedBracketCount ++;
            }
        } else {
            expressionField.setText("(");
            notClosedBracketCount ++;
        }
    }

    private void addCloseBracket()
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        Integer stringLength = expressionField.getText().length();
        if((stringLength > 1) && (notClosedBracketCount > 0)){
            String lastSymbol = expressionField.getText().substring(stringLength-1);
            if (!operationMarks.contains(lastSymbol)){
                expressionField.setText(expressionField.getText() + ")");
                notClosedBracketCount --;
            }
        }
    }

    private void erase()
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        String expression = expressionField.getText();
        if(expression.length() != 0){
            expressionField.setText(expression.substring(0,expression.length()-1));
        }
    }

    private void equality()
    {
        if(expressionField.getText().equals("Division by zero is impossible!")){
            expressionField.setText("");
        }
        Integer stringLength = expressionField.getText().length();
        if (stringLength != 0){
            String lastSymbol = expressionField.getText().substring(stringLength-1);
            if (lastSymbol.equals("(")){
                while(lastSymbol.equals("(")){
                    String expression = expressionField.getText();
                    expressionField.setText(expression.substring(0,expression.length()-1));
                    stringLength --;
                    lastSymbol = expressionField.getText().substring(stringLength-1);
                }
                if (operationMarks.contains(lastSymbol)){
                    String expression = expressionField.getText();
                    expressionField.setText(expression.substring(0,expression.length()-1));
                }
            } else if (operationMarks.contains(lastSymbol)){
                String expression = expressionField.getText();
                expressionField.setText(expression.substring(0,expression.length()-1));
            }
        }
        try{
            expressionField.setText(Double.toString(calculator.calculate(expressionField.getText())));
        } catch (DivisionByZeroException ex){
            expressionField.setText("Division by zero is impossible!");
        }
    }
}
