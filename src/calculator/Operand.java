package calculator;

/**
 * Класс операндов который содержит число, операцию над следующим числом и приоритет этой операции
 */
public class Operand
{
    private double number;
    private String operation;
    private int priority;

    public Operand(double number, String operation, int priority)
    {
        this.number = number;
        this.operation = operation;
        this.priority = priority;
    }

    public Operand(double number)
    {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
