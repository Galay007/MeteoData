import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {
    public int binarySearch(byte[] a, byte key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(byte[] a, int fromIndex, int toIndex, byte key) {

        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public int binarySearch(char[] a, char key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(char[] a, int fromIndex, int toIndex, char key) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public int binarySearch(double[] a, double key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(double[] a, int fromIndex, int toIndex, double key) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public int binarySearch(float[] a, float key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(float[] a, int fromIndex, int toIndex, float key) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public int binarySearch(int[] a, int key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public int binarySearch(long[] a, long key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(long[] a, int fromIndex, int toIndex, long key) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public int binarySearch(short[] a, short key) {
        return Arrays.binarySearch(a, key);
    }

    public int binarySearch(short[] a, int fromIndex, int toIndex, short key) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key);
    }

    public <T> int binarySearch(T[] a, T key, Comparator<? super T> c) {
        return Arrays.binarySearch(a, key, c);
    }

    public <T> int binarySearch(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c) {
        return Arrays.binarySearch(a, fromIndex, toIndex, key, c);
    }

    public <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        return Collections.binarySearch(list, key);
    }

    public <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        return Collections.binarySearch(list, key, c);
    }
}
