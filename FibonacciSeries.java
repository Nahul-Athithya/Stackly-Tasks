public class FibonacciSeries {

    /**
     * 1. Program for Even Numbers
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void printEvenNumbers(int limit) {
        System.out.print("Even Numbers up to " + limit + ": ");
        for (int i = 2; i <= limit; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 2. Program for Odd Numbers
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void printOddNumbers(int limit) {
        System.out.print("Odd Numbers up to " + limit + ": ");
        for (int i = 1; i <= limit; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    /**
     * 3. Program for standard Fibonacci series
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void printStandardFibonacci(int numTerms) {
        System.out.print("Fibonacci Series (" + numTerms + " terms): ");
        int a = 0, b = 1;
        for (int i = 1; i <= numTerms; i++) {
            System.out.print(a + " ");
            int nextTerm = a + b;
            a = b;
            b = nextTerm;
        }
        System.out.println();
    }

    /**
     * 4. Bonus: Fibonacci series separated into even and odd without using extra arrays
     * Time Complexity: O(N) (Iterates twice, which is still O(N) linear time)
     * Space Complexity: O(1) (No lists or arrays used, constant space)
     */
    public static void printFibonacciSeparated(int numTerms) {
        System.out.println("\n--- Fibonacci Series Separated (Up to " + numTerms + " terms) ---");
        
        System.out.print("Even Fibonacci Numbers: ");
        int a = 0, b = 1;
        for (int i = 1; i <= numTerms; i++) {
            if (a % 2 == 0) {
                System.out.print(a + " ");
            }
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println();

        System.out.print("Odd Fibonacci Numbers:  ");
        a = 0; b = 1;
        for (int i = 1; i <= numTerms; i++) {
            if (a % 2 != 0) {
                System.out.print(a + " ");
            }
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Values for demonstration
        int maxLimit = 20;
        int numTerms = 15;

        System.out.println("==== Task Programs Set ====\n");
        
        // Task 1: Even Numbers
        printEvenNumbers(maxLimit);
        
        // Task 2: Odd Numbers
        printOddNumbers(maxLimit);
        
        // Task 3: Fibonacci Series
        printStandardFibonacci(numTerms);

        // Orignal Request: Fibonacci Separated 
        printFibonacciSeparated(numTerms);
    }
}
