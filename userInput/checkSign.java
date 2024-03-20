package userInput;

/**
 * This class is designed to house methods that will check the sign of numbers
 * For these purposes, sign is positive/negative
 * Each method also includes a boolean to decide whether to include zero or exclude zero in its calculations
 * @author Gabriel Foster
 * @version 1.0
 */
public class checkSign {

    /**
     * Determines if a double is positive
     * @param number The double being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is positive, false if negative
     */
    public static boolean isPositive(double number, boolean includeZero){

        if (includeZero){
            return number >= 0;
        }
        else {
            return number > 0;
        }

    }

    /**
     * Determines if a float is positive
     * @param number The float being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is positive, false if negative
     */
    public static boolean isPositive(float number, boolean includeZero){

        if (includeZero){
            return number >= 0;
        }
        else {
            return number > 0;
        }

    }

    /**
     * Determines if an integer is positive
     * @param number The integer being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is positive, false if negative
     */
    public static boolean isPositive(int number, boolean includeZero){

        if (includeZero){
            return number >= 0;
        }
        else {
            return number > 0;
        }

    }

    /**
     * Determines if a long is positive
     * @param number The long being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is positive, false if negative
     */
    public static boolean isPositive(long number, boolean includeZero){

        if (includeZero){
            return number >= 0;
        }
        else {
            return number > 0;
        }

    }

    /**
     * Determines if a short is positive
     * @param number The short being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is positive, false if negative
     */
    public static boolean isPositive(short number, boolean includeZero){

        if (includeZero){
            return number >= 0;
        }
        else {
            return number > 0;
        }

    }

    /**
     * Determines if a double is negative
     * @param number The double being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is negative, false if positive
     */
    public static boolean isNegative(double number, boolean includeZero){

        if (includeZero){
            return number <= 0;
        }
        else {
            return number < 0;
        }

    }

    /**
     * Determines if a float is negative
     * @param number The float being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is negative, false if positive
     */
    public static boolean isNegative(float number, boolean includeZero){

        if (includeZero){
            return number <= 0;
        }
        else {
            return number < 0;
        }

    }

    /**
     * Determines if an integer is negative
     * @param number The integer being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is negative, false if positive
     */
    public static boolean isNegative(int number, boolean includeZero){

        if (includeZero){
            return number <= 0;
        }
        else {
            return number < 0;
        }

    }

    /**
     * Determines if a long is negative
     * @param number The long being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is negative, false if positive
     */
    public static boolean isNegative(long number, boolean includeZero){

        if (includeZero){
            return number <= 0;
        }
        else {
            return number < 0;
        }

    }

    /**
     * Determines if a short is negative
     * @param number The short being checked for its sign
     * @param includeZero A boolean which decides whether to include or exclude zero in the method's calculations
     * @return True if number is negative, false if positive
     */
    public static boolean isNegative(short number, boolean includeZero){

        if (includeZero){
            return number <= 0;
        }
        else {
            return number < 0;
        }

    }


}
