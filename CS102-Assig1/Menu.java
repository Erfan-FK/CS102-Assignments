import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder menu = new StringBuilder();
        menu.append("\n1. Prime Numbers\n");
        menu.append("2. Volume Filling\n");
        menu.append("3. Union Area\n");
        menu.append("4. Random Question\n");
        menu.append("5. Exit\n");
        menu.append("\n\nPlease enter your choice: " );

        boolean enough = false;
        do {
            System.out.print(menu.toString());
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    System.out.println("\n-Find the prime numbers in the range between X and Y.");
                    System.out.print("Please enter X, Y: ");
                    int min = scanner.nextInt();
                    int max = scanner.nextInt();

                    System.out.println(Methods.findPrime(min, max));
                    break;

                case 2:
                    System.out.println("\n- A rectangular prism volume of dimensions X, Y and Z is to be filled using cube blocks."
                    + "\nWhat is the minimum number of cubes required?");
            
                    System.out.print("Please enter X, Y, Z: ");
                    
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int z = scanner.nextInt();


                    System.out.println(Methods.fillVolume(x, y, z));
                    break;

                case 3:
                    System.out.println("- What is the area of the union of two rectangles R1 and R2," 
                    + "\nwhere top left corner of R1 is (X1,Y1) and its size is (W1,H1),"
                    + "\nand top left corner of R2 is (X2,Y2) and its size is (W2,H2)?");
            
                    System.out.print("\nPlease enter X1, Y1, W1, H1, X2, Y2, W2, H2: ");
                    int x1Left = scanner.nextInt();
                    int y1Left = scanner.nextInt();
                    int w1 = scanner.nextInt();
                    int h1 = scanner.nextInt();
                    int x2Left = scanner.nextInt();
                    int y2Left = scanner.nextInt();
                    int w2 = scanner.nextInt();
                    int h2 = scanner.nextInt();

                    System.out.println(Methods.findUnionArea(x1Left, y1Left, w1, h1, x2Left, y2Left, w2, h2));
                    break;

                case 4:
                    System.out.print("\nPlease enter the number of questions you want: ");
                    int count = scanner.nextInt();
                    Methods.randomGenerator(count);
                    break;
                case 5:
                    enough = true;
                    System.out.println("Adios Amigo.");
                    break;
            }
        } while(!enough);

        scanner.close();
    }
}