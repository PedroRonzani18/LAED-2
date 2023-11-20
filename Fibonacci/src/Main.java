package src;
class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            Fibonacci fib = new Fibonacci();

            fib.executeDP(i);
            System.out.println(i + ";" + fib.chamdasDp);
        }

        System.out.println();

        for (int i = 0; i < 1000; i++) {
            Fibonacci fib = new Fibonacci();

            fib.executeRecursive(i);
            System.out.println(i + ";" + fib.chamdasR.toString());
        }
    }
}