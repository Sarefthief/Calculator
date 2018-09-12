package calculator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс производящий расчеты
 */
public class Calculator
{
    private ArrayList<String> operationMarks;

    public Calculator(){
        operationMarks = new ArrayList<>();
        operationMarks.addAll(Arrays.asList("+","-","*","/"));
    }

    public double calculate(String expression) throws DivisionByZeroException
    {
        ArrayList<Operand> operands = new ArrayList<>();
        double answer = 0;
        double bracketValue = 0;
        if (expression.contains("(")){
            while(expression.contains("(")){                                    //цикл избавляющий от всех скобок в выражении
                int openBracketPosition = expression.lastIndexOf("(");
                String remainingExpression = expression.substring(openBracketPosition);
                if (remainingExpression.contains(")")){
                    int closeBracketPosition = expression.length() - remainingExpression.length() + remainingExpression.indexOf(")");
                    bracketValue = calculate(expression.substring(openBracketPosition+1, closeBracketPosition));
                    expression = expression.substring(0,openBracketPosition) + Double.toString(bracketValue) + expression.substring(closeBracketPosition+1);
                } else {
                    bracketValue = calculate(expression.substring(openBracketPosition+1));
                    expression = expression.substring(0,openBracketPosition) + Double.toString(bracketValue);

                }
            }
        }
        operands = parseExpression(expression);

        for (int i = 0; i <= operands.size()-1; i++){       //цикл выполнения операций умножения  и деления
            if(operands.size() != 1){
                if(operands.get(i).getPriority() == 2){
                    if(operands.get(i).getOperation().equals("*")){
                        operands.get(i+1).setNumber(operands.get(i).getNumber() * operands.get(i+1).getNumber());       //операция умножения
                    } else {
                        if (operands.get(i+1).getNumber() == 0){
                            throw new DivisionByZeroException();       //невозможность деления на ноль
                        }
                        operands.get(i+1).setNumber(operands.get(i).getNumber() / operands.get(i+1).getNumber());       //операция деления
                    }
                    operands.remove(i);
                    i--;
                }
            } else {
                answer = operands.get(i).getNumber();
            }
        }

        for (int i = 0; i <= operands.size()-1; i++)
        {
            if(operands.size() != 1){
                if(operands.get(i).getOperation().equals("+")){
                    operands.get(i+1).setNumber(operands.get(i).getNumber() + operands.get(i+1).getNumber());       //операция сложения
                } else {
                    operands.get(i+1).setNumber(operands.get(i).getNumber() - operands.get(i+1).getNumber());       //операция вычитания
                }
                operands.remove(i);
                i--;
            } else {
                answer = operands.get(0).getNumber();
            }
        }

        return answer;
    }

    private ArrayList<Operand> parseExpression(String expression){
        ArrayList<Operand> operands = new ArrayList<>();
        int priority;

        for (int i = 0; i <= expression.length()-1; i++){
            if (i != expression.length()-1){
                String symbolToCheck = expression.substring(i,i+1);
                if (operationMarks.contains(symbolToCheck) && (i != 0)){
                    if (symbolToCheck.equals("*") || (symbolToCheck.equals("/"))){
                        priority = 2;
                    } else {
                        priority = 1;
                    }
                    operands.add(new Operand(Double.parseDouble(expression.substring(0,i)), expression.substring(i,i+1), priority));
                    expression = expression.substring(i+1);
                    i = -1;
                }
            } else {
                operands.add(new Operand(Double.parseDouble(expression)));
            }
        }

        return operands;
    }
}
