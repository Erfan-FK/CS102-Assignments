import java.util.Random;

public class Methods {
    
    static Random random = new Random();
    
    
    // Method 1
    public static String findPrime(int min, int max){
        StringBuilder output = new StringBuilder();

        output.append("\nResult: Prime numbers in range [" + min + ","  + max +"] are ");

        while (min <= max){
            if (isPrime(min)){
                output.append(min + ", ");
            }
            min++;
        }

        output.delete(output.length() - 2, output.length());
        output.append(".\n");
        return output.toString();
    }

    // Helper method for method 1
    private static boolean isPrime(int number){
        if (number == 1 || number == 0)
            return false;

        for (int i = 2; i <= number / 2; i++){
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Method 2
    public static String fillVolume(int x, int y, int z){
        StringBuilder output = new StringBuilder();

        int gcdXY = findGCD(x , y);
        int gcdXYZ = findGCD(gcdXY, z);
        int countOfCubes = (x * y * z) / (gcdXYZ * gcdXYZ * gcdXYZ); 

        if (gcdXYZ == 1)
            output.append("\nNumbers " + x + ", " + y + ", " + z + " are coprime!\n");
            
        output.append("\nResult: Using cubes of edge length " + gcdXYZ  + " you need " + countOfCubes + " blocks minimum.\n");
        return output.toString();
    }

    // Helper Method for method 2
    private static int findGCD(int a, int b){
        // repeat proccess of dividing dividend by divisor, replacing dividend by divisor and divisor by remainder until remainder becomes 0. 
        // recursive implementation
        if(b == 0)
            return a;
        else
            return findGCD(b, a % b);

        // loop implementation
        /*
         * while(b != 0){
         * int temp = b;
         * b = a % b;
         * a = temp;
         * }
         */
    }

    // Method 3
    public static String findUnionArea(int x1Left, int y1Left, int w1, int h1, int x2Left, int y2Left, int w2, int h2 ){
        StringBuilder output = new StringBuilder();

        int area1 = w1 * h1;
        int area2 = w2 * h2;

        // Supposing top left corner is the origin (x-y plain is upside down), find coardinates of bottom right corner
        int x1Right = x1Left + w1;
        int y1Right = y1Left + h1;

        int x2Right = x2Left + w2;
        int y2Right = y2Left + h2;


        //finding width and height of the intersection rectangle
        int intersectionW = findMin(x1Right, x2Right) - findMax(x1Left, x2Left);
        int intersectionH = findMin(y1Right, y2Right) - findMax(y1Left, y2Left);
        int areaIntersection;

        if (intersectionH <= 0 || intersectionW <= 0)
            areaIntersection = 0;
        else
            areaIntersection = intersectionH * intersectionW;

        output.append("\nResult: Intersection area is " + areaIntersection + " thus the total area of the union is " + (area1 + area2 - areaIntersection) + ".\n");
        return output.toString();
    }

    // helper methods for method 3
    private static int findMax(int a, int b){
        int max = a > b ? a : b;

        return max; 
    }

    private static int findMin(int a, int b){
        int min = a < b ? a : b;

        return min;
    }

    // Method 4
    public static void randomGenerator(int count){
        for (int i = 1; i <= count; i++){
            int question = random.nextInt(3);

            if (question == 0) {
                int min = randomParametr();
                int max = randomParametr();

                // regenrate both to decrease the chance closeness of two numbers
                while (min >= max){
                    min = randomParametr();
                    max = randomParametr();
                }

                System.out.println("\n" + i + ") Find the prime numbers in the range between " + min + " and " + max + ":");
                System.out.println(Methods.findPrime(min, max));

            } else if (question == 1) {
                int x = randomParametr();
                int y = randomParametr();
                int z = randomParametr();

                while (x == 0 || y == 0 || z == 0 || isCoPrime(x, y, z)){
                    x = randomParametr();
                    y = randomParametr();
                    z = randomParametr();
                }

                System.out.println("\n" + i + ") A rectangular prism volume of dimensions " + x + "," + y + " and " + z +" is to be filled using cube blocks."
                    + "\nWhat is the minimum number of cubes required?");

                System.out.println(Methods.fillVolume(x, y, z));

            } else {
                int x1Left = randomParametr();
                int y1Left = randomParametr();
                int w1 = randomParametr();
                int h1 = randomParametr();
                int x2Left = randomParametr();
                int y2Left = randomParametr();
                int w2 = randomParametr();                
                int h2 = randomParametr();

                System.out.println("\n" + i + ") What is the area of the union of two rectangles R1 and R2," 
                    + "\nwhere top left corner of R1 is (" + x1Left + "," + y1Left + ") and its size is (" + w1 + "," + h1 + "),"
                    + "\nand top left corner of R2 is (" + x2Left + "," + y2Left + ") and its size is (" + w2 + "," + h2 + ")?");

                System.out.println(Methods.findUnionArea(x1Left, y1Left, w1, h1, x2Left, y2Left, w2, h2));
            }
        }
    }

    // helper methods for Method 4
    private static int randomParametr(){
        return random.nextInt(51);
    }

    private static boolean isCoPrime(int a, int b, int c) {
        return findGCD(a, findGCD(b, c)) == 1;
    }


}
