@FunctionalInterface
interface IMathFunction{
    int calculate(int a, int b);
    static void printResult(int a, int b, IMathFunction fobj){
        System.out.println("Result is " + fobj.calculate(a,b));
    }
}

public class MathOperationsApp {
    public static void main(String[] args) {
        IMathFunction multiply = (int x, int y) -> x*y;
        IMathFunction add = (int x, int y) -> x+y;
        IMathFunction substract = (int x, int y) -> x-y;
        IMathFunction.printResult(3,4,add);

    }

}
