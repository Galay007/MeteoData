import java.util.Arrays;
import java.util.List;

enum Types {

    BYTE {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            byte key = 50;
            var array = byteArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    BYTE_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            byte key = 30;
            var array = byteArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    CHAR {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            char key = 'd';
            var array = charArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    CHAR_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            char key = 'k';
            var array = charArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    DOUBLE {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            double key = 697;
            var array = doubleArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    DOUBLE_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            double key = 1200;
            var array = doubleArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    FLOAT {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            float key = 534;
            var array = floatArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    FLOAT_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            float key = 7234;
            var array = floatArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    INT {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            int key = 19;
            var array = intArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    INT_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            int key = 13;
            var array = intArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    LONG {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            long key = 348;
            var array = longArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    LONG_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            long key = 920;
            var array = longArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    SHORT {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            short key = 17;
            var array = shortArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array));
        }
    },
    SHORT_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            short key = 16;
            var array = shortArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key),
                    Arrays.toString(array));
        }
    },
    ARRAY_GENERIC {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            char key = 'a';
            var array = charGenericArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key, (a, b) -> a - b),
                    Arrays.toString(array));
        }
    },
    ARRAY_GENERIC_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            char key = 'k';
            var array = charGenericArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, fromIndex, toIndex, key, (a, b) -> a - b),
                    Arrays.toString(array));
        }
    },
    COLLECTION_GENERIC {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            Integer key = 60;
            var array = intGenericArray;
            System.out.print(series + ". Binary search " + typeName + " " + key);
            Main.printResult(series, search.binarySearch(array, key),
                    Arrays.toString(array.stream().mapToInt(i -> i).toArray()));
        }
    },
    COLLECTION_GENERIC_2 {
        @Override
        public void getArray(int series, BinarySearch search, String typeName) {
            Integer key = 100;
            var array = intGenericArray;
            System.out.print(series + ". Binary search " + typeName + " " + key + " from index " + fromIndex
                    + " to index " + toIndex);
            Main.printResult(series, search.binarySearch(array, key, (a, b) -> a - b),
                    Arrays.toString(array.stream().mapToInt(i -> i).toArray()));
        }
    };

    public abstract void getArray(int series, BinarySearch search, String typeName);

    final int fromIndex = 5;
    final int toIndex = 10;
    final byte[] byteArray = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};
    final int[] intArray = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
    final char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
    final Character[] charGenericArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
    final double[] doubleArray = {348, 498, 534, 697, 789, 873, 920, 1200, 5000, 6350, 7023};
    final float[] floatArray = {348, 498, 534, 697, 789, 873, 920, 1200, 5000, 6342, 7234, 8042};
    final long[] longArray = {348, 498, 534, 697, 789, 873, 920, 1200, 5000, 6342, 7930, 8243};
    final short[] shortArray = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
    final List<Integer> intGenericArray = List.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120);

    }
