package userInput;
import java.util.Scanner;

/**
 * This class is designed to help get input from the user. Method titles with "safe" in
 * then include type checking that will re-listen (or re-prompt if wished) to the command
 * line for the next input. These methods will never crash the program due to user input error.
 * Method titles without safe will simply listen for that type in the command line.
 * Method parameters will always be in the order: promptText, lowerBound, upperBound, sign
 * Should you want to get a sign-checked variable without a prompt, enter null for promptText
 * and it will skip printing a line
 * @author Gabriel Foster
 * @version 1.0
 */
public class getVariable {

    public final static String POSITIVE = "positive";
    public final static String NEGATIVE = "negative";

    /**
     * Reads the next line from console
     * @return The input as a double
     */
    public static double getDouble(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a double
     */
    public static double getDouble(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);

        return scanner.nextDouble();
    }

    /**
     * Reads the next line from the console, will only accept doubles
     * @return The input as a double
     */
    public static double getSafeDouble(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!checkType.isDouble(input)){
            System.out.println("Please only enter a number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Double.parseDouble(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * Will only accept doubles
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a double
     */
    public static double getSafeDouble(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        String input = scanner.nextLine();
        while (!checkType.isDouble(input)){
            System.out.println("Please only enter a number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Double.parseDouble(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param sign Accepts "positive" or "negative", will only return doubles
     * @param includeZero True will include 0 in the checking, false will not
     * @return The input as a double
     */
    public static double getSafeDouble(String promptText, String sign, boolean includeZero){
        if (!sign.equals(POSITIVE) && !sign.equals(NEGATIVE)){
            throw new IllegalArgumentException("Unexpected token: '" + sign + "'" +
                    "\n\nAccepted arguments are \"" + POSITIVE + "\" and \"" + NEGATIVE + "\"\n");
        }

        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (promptText != null) {
                System.out.println(promptText);
            }
            String input = scanner.nextLine();

            while (!checkType.isDouble(input)) {
                System.out.println("Please only enter a " + sign + " number.");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            double number = Double.parseDouble(input);

            if (sign.equals(POSITIVE)) {
                if (checkSign.isPositive(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a positive number.");
                    System.out.println("Try again.");
                }
            } else if (sign.equals(NEGATIVE)) {
                if (checkSign.isNegative(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a negative number.");
                    System.out.println("Try again.");
                }
            }
        }

    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a double
     */
    public static double getSafeDouble(double lowerBound, double upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            while (!checkType.isDouble(input)) {
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            double number = Double.parseDouble(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a double
     */
    public static double getSafeDouble(String promptText, double lowerBound, double upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptText);
            String input = scanner.nextLine();
            while (!checkType.isDouble(input)) {
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            double number = Double.parseDouble(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Reads the next line from console
     * @return The input as a float
     */
    public static float getFloat(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextFloat();
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a float
     */
    public static float getFloat(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);

        return scanner.nextFloat();
    }

    /**
     * Reads the next line from the console, will only accept floats
     * @return The input as a float
     */
    public static float getSafeFloat(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!checkType.isFloat(input)){
            System.out.println("Please only enter a number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Float.parseFloat(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * Will only accept floats
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a float
     */
    public static float getSafeFloat(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        String input = scanner.nextLine();
        while (!checkType.isFloat(input)){
            System.out.println("Please only enter a number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Float.parseFloat(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param sign Accepts "positive" or "negative", will only return floats
     * @param includeZero True will include 0 in the checking, false will not
     * @return The input as a float
     */
    public static float getSafeFloat(String promptText, String sign, boolean includeZero){
        if (!sign.equals(POSITIVE) && !sign.equals(NEGATIVE)){
            throw new IllegalArgumentException("Unexpected token: '" + sign + "'" +
                    "\n\nAccepted arguments are \"" + POSITIVE + "\" and \"" + NEGATIVE + "\"\n");
        }

        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (promptText != null) {
                System.out.println(promptText);
            }
            String input = scanner.nextLine();

            while (!checkType.isFloat(input)) {
                System.out.println("Please only enter a " + sign + " number.");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            float number = Float.parseFloat(input);

            if (sign.equals(POSITIVE)) {
                if (checkSign.isPositive(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a positive number.");
                    System.out.println("Try again.");
                }
            } else if (sign.equals(NEGATIVE)) {
                if (checkSign.isNegative(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a negative number.");
                    System.out.println("Try again.");
                }
            }
        }

    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a float
     */
    public static float getSafeFloat(float lowerBound, float upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            while (!checkType.isFloat(input)) {
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            float number = Float.parseFloat(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a float
     */
    public static float getSafeFloat(String promptText, float lowerBound, float upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptText);
            String input = scanner.nextLine();
            while (!checkType.isFloat(input)) {
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            float number = Float.parseFloat(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Reads the next line from console
     * @return The input as an integer
     */
    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as an integer
     */
    public static int getInt(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);

        return scanner.nextInt();
    }

    /**
     * Reads the next line from the console, will only accept integers
     * @return The input as an integer
     */
    public static int getSafeInt(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!checkType.isInt(input)){
            System.out.println("Please only enter a whole number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * Will only accept integers
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as an integer
     */
    public static int getSafeInt(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        String input = scanner.nextLine();
        while (!checkType.isInt(input)){
            System.out.println("Please only enter a whole number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param sign Accepts "positive" or "negative", will only return integers
     * @param includeZero True will include 0 in the checking, false will not
     * @return The input as an integer
     */
    public static int getSafeInt(String promptText, String sign, boolean includeZero){
        if (!sign.equals(POSITIVE) && !sign.equals(NEGATIVE)){
            throw new IllegalArgumentException("Unexpected token: '" + sign + "'" +
                    "\n\nAccepted arguments are \"" + POSITIVE + "\" and \"" + NEGATIVE + "\"\n");
        }

        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (promptText != null) {
                System.out.println(promptText);
            }
            String input = scanner.nextLine();

            while (!checkType.isInt(input)) {
                System.out.println("Please only enter a " + sign + " whole number.");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            int number = Integer.parseInt(input);

            if (sign.equals(POSITIVE)) {
                if (checkSign.isPositive(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a positive number.");
                    System.out.println("Try again.");
                }
            } else if (sign.equals(NEGATIVE)) {
                if (checkSign.isNegative(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a negative number.");
                    System.out.println("Try again.");
                }
            }
        }

    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as an integer
     */
    public static int getSafeInt(int lowerBound, int upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            while (!checkType.isInt(input)) {
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            int number = Integer.parseInt(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as an integer
     */
    public static int getSafeInt(String promptText, int lowerBound, int upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptText);
            String input = scanner.nextLine();
            while (!checkType.isInt(input)) {
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            int number = Integer.parseInt(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Reads the next line from console
     * @return The input as a long
     */
    public static long getLong(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLong();
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a long
     */
    public static long getLong(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);

        return scanner.nextLong();
    }

    /**
     * Reads the next line from the console, will only accept longs
     * @return The input as a long
     */
    public static long getSafeLong(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!checkType.isLong(input)){
            System.out.println("Please only enter a whole number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Long.parseLong(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * Will only accept longs
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a long
     */
    public static long getSafeLong(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        String input = scanner.nextLine();
        while (!checkType.isLong(input)){
            System.out.println("Please only enter a whole number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Long.parseLong(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param sign Accepts "positive" or "negative", will only return longs
     * @param includeZero True will include 0 in the checking, false will not
     * @return The input as a long
     */
    public static long getSafeLong(String promptText, String sign, boolean includeZero){
        if (!sign.equals(POSITIVE) && !sign.equals(NEGATIVE)){
            throw new IllegalArgumentException("Unexpected token: '" + sign + "'" +
                    "\n\nAccepted arguments are \"" + POSITIVE + "\" and \"" + NEGATIVE + "\"\n");
        }

        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (promptText != null) {
                System.out.println(promptText);
            }
            String input = scanner.nextLine();

            while (!checkType.isLong(input)) {
                System.out.println("Please only enter a " + sign + " whole number.");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            long number = Long.parseLong(input);

            if (sign.equals(POSITIVE)) {
                if (checkSign.isPositive(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a positive number.");
                    System.out.println("Try again.");
                }
            } else if (sign.equals(NEGATIVE)) {
                if (checkSign.isNegative(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a negative number.");
                    System.out.println("Try again.");
                }
            }
        }

    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a long
     */
    public static long getSafeLong(long lowerBound, long upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            while (!checkType.isLong(input)) {
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            long number = Long.parseLong(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a long
     */
    public static long getSafeLong(String promptText, long lowerBound, long upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptText);
            String input = scanner.nextLine();
            while (!checkType.isLong(input)) {
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            long number = Long.parseLong(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Reads the next line from console
     * @return The input as a short
     */
    public static long getShort(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextShort();
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a short
     */
    public static long getShort(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);

        return scanner.nextShort();
    }

    /**
     * Reads the next line from the console, will only accept shorts
     * @return The input as a short
     */
    public static long getSafeShort(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!checkType.isShort(input)){
            System.out.println("Please only enter a whole number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Short.parseShort(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * Will only accept shorts
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a short
     */
    public static long getSafeShort(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        String input = scanner.nextLine();
        while (!checkType.isShort(input)){
            System.out.println("Please only enter a whole number.");
            System.out.println("Try again.");
            input = scanner.nextLine();
        }

        return Short.parseShort(input);
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param sign Accepts "positive" or "negative", will only return longs
     * @param includeZero True will include 0 in the checking, false will not
     * @return The input as a short
     */
    public static long getSafeShort(String promptText, String sign, boolean includeZero){
        if (!sign.equals(POSITIVE) && !sign.equals(NEGATIVE)){
            throw new IllegalArgumentException("Unexpected token: '" + sign + "'" +
                    "\n\nAccepted arguments are \"" + POSITIVE + "\" and \"" + NEGATIVE + "\"\n");
        }

        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (promptText != null) {
                System.out.println(promptText);
            }
            String input = scanner.nextLine();

            while (!checkType.isShort(input)) {
                System.out.println("Please only enter a " + sign + " whole number.");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            short number = Short.parseShort(input);

            if (sign.equals(POSITIVE)) {
                if (checkSign.isPositive(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a positive number.");
                    System.out.println("Try again.");
                }
            } else if (sign.equals(NEGATIVE)) {
                if (checkSign.isNegative(number, includeZero)){
                    return number;
                } else {
                    System.out.println("Please only enter a negative number.");
                    System.out.println("Try again.");
                }
            }
        }

    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a short
     */
    public static long getSafeShort(short lowerBound, short upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            while (!checkType.isShort(input)) {
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            short number = Short.parseShort(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @param lowerBound Inclusive lower bound of accepted input
     * @param upperBound Inclusive upper bound of accepted input
     * @return The input as a short
     */
    public static long getSafeShort(String promptText, short lowerBound, short upperBound){
        if (lowerBound > upperBound){
            throw new IllegalArgumentException("Bounds mismatch. Lower bound '" +
                    lowerBound + "' is greater than higher bound '" + upperBound +
                    "'.");
        }
        if (lowerBound == upperBound){
            throw new IllegalArgumentException("Bounds collision. Lower bound '" +
                    lowerBound + "' is equal to higher bound '" + upperBound +
                    "'.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptText);
            String input = scanner.nextLine();
            while (!checkType.isShort(input)) {
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
                input = scanner.nextLine();
            }

            short number = Short.parseShort(input);

            if(number > upperBound || number < lowerBound){
                System.out.println("Please only enter a whole number between " +
                        lowerBound +" and " + upperBound + ".");
                System.out.println("Try again.");
            }
            else {
                return number;
            }
        }


    }

    /**
     * Reads the next line from console
     * @return The input as a string
     */
    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints a prompt to the console and reads the next line from the console
     * @param promptText Will print this prompt to let the user know what to do
     * @return The input as a string
     */
    public static String getString(String promptText){
        Scanner scanner = new Scanner(System.in);
        System.out.println(promptText);
        return scanner.nextLine();
    }


}
