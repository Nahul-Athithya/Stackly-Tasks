import java.util.Scanner;

public class FibonacciSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter terms: ");
        int n = sc.nextInt();

        int[] fib = new int[n];
        fib[0] = 0;
        if (n > 1)
            fib[1] = 1;

        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        System.out.print("Even: ");
        for (int num : fib) {
            if (num % 2 == 0)
                System.out.print(num + " ");
        }

        System.out.print("\nOdd: ");
        for (int num : fib) {
            if (num % 2 != 0)
                System.out.print(num + " ");
        }

        sc.close();
    }
}