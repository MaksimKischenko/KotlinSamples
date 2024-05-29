import java.util.function.Function;



 class Main {
    static int x = 10;
    static int y = 20;
    public static void main(String[] args) {

        Operational operation = (x,y) -> x*y;
        var result = operation.calculate(10,20);

        //ИЛИ

        Operational op = new Operational(){

            public int calculate(int x, int y){

                return x + y;
            }
        };
        int z = op.calculate(20, 10);


        Printable printer = System.out::println;
        printer.print("Hello Java!");


        Operation op2 = ()->{

            x=30;
            return x+y;
        };



         int n=70;
         int m=30;

        Operation op3 = ()->{

            //n=100; - так нельзя сделать
            return m+n;
        };


        MyImplementation myImplementation = new MyImplementation();

        // Использование лямбда-выражения
        Function<String, String> lambdaFunction = (input) -> myImplementation.processString(input);
        System.out.println(lambdaFunction.apply("Hello"));

        // Использование ссылки на метод
        Function<String, String> methodReference = myImplementation::processString;
        System.out.println(methodReference.apply("World"));


    }

}







interface Printable{
    void print(String s);
}

interface Operational {
    int calculate(int x, int y);

}

interface Operation{
    int calculate();
}
// Пример обычного интерфейса
interface MyInterface {
    String processString(String input);
}

// Пример класса, реализующего интерфейс
class MyImplementation implements MyInterface {
    @Override
    public String processString(String input) {
        return "Processed: " + input;
    }
}
