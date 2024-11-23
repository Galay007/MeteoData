
public class Main {
    public static void main(String[] args) {

        int series = 0;

        BinarySearch search = new BinarySearch();
        for (Types type : Types.values()) {
            type.getArray(++series,search, type.name());
            System.out.println();
        }
    }

    public static void printResult(int series,int indexFound,String arrayStr) {

        System.out.print(check(indexFound) + arrayStr);
    }

    public static String check(int index) {
        if (index >= 0) {
            return " has index " + index + " in array ";
        } else {
            return " is out of array ";
        }
    }
}