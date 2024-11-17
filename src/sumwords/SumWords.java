package sumwords;

import java.util.*;
import java.util.stream.Stream;

public class SumWords {
    static Character[] x = {'o', 'd', 'i', 'n'};
    static Character[] y = {'o', 'd', 'i', 'n'};
    static Character[] z = {'m', 'n', 'o', 'g', 'o'};

    static Map<Character, Integer> letterWithNum = new HashMap<>();
    static Map<Integer, List<Integer>> sliceBeforeChanges = new HashMap<>();
    static Deque<Integer> availableNum = new ArrayDeque<>();
    static int carrier = 0;

    public static void main(String[] args) {

        prepaireUniqueLetters();
        updateAvailableNum();
        printTask();

        if (validateSize() && start(1)) {
            System.out.println("Решение найдено");
            printMathResult();
        } else {
            System.out.println("Решение не найдено");
        }
    }

    private static void printMathResult() {
        System.out.println(Math.round(getNumber(x)));
        System.out.println(Math.round(getNumber(y)));
        System.out.println(Math.round(getNumber(z)));
    }

    private static void printTask() {
        System.out.println(Arrays.toString(Arrays.stream(x).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(y).toArray()));
        System.out.println(Arrays.toString(Arrays.stream(z).toArray()));
    }

    private static boolean validateSize() {
        if (x.length == y.length && z.length < x.length) {
            return false;
        }
        return z.length - x.length <= 1;
    }

    private static void prepaireUniqueLetters() {
        List<Character> letters = Stream.of(Arrays.asList(x), Arrays.asList(y), Arrays.asList(z))
                .flatMap(Collection::stream)
                .toList();
        for (var letter : letters) {
            letterWithNum.put(letter, null);
        }
    }

    private static void updateAvailableNum() {
        for (int i = 1; i <= 9; i++) {
            if (i == 1 && z.length - x.length == 1) {
                letterWithNum.put(z[z.length - x.length - 1], 1);
                continue;
            }
            if (!letterWithNum.values().stream().toList().contains(i)) {
                availableNum.add(i);
            }
        }
    }

    public static boolean start(int slice) {

        if (checkAllLettersHasNum()) {
            if (checkResult()) {
                return true;
            }
        }
        int carrierFromPrevStep = carrier;

        if (slice > z.length) return false;

        getMemorizeForReturn(slice);

        List<Integer> currentAvailableNum = availableNum.stream().toList();

        for (int i = 0; i < currentAvailableNum.size(); i++) {
            if (Arrays.equals(x, y)) {

                Integer scanX = letterWithNum.get(x[x.length - slice]);
                Integer scanZ = letterWithNum.get(z[z.length - slice]);

                if (scanX != null && scanZ != null && scanX * 2 + carrierFromPrevStep == scanZ + carrier * 10) {
                    boolean check = start(++slice);
                    if (check) {
                        return true;
                    } else {
                        slice--;
                        restoreInitialInputs(slice);
                    }
                }

                if (scanX != null && scanZ == null) {
                    scanZ = scanX * 2 + carrierFromPrevStep;
                    if (scanZ >= 10) {
                        carrier = scanZ / 10;
                        scanZ = scanZ % 10;
                    } else {
                        carrier = 0;
                    }
                }

                if (scanX == null && scanZ != null) {
                    if (currentAvailableNum.get(i) == scanZ / 2 && carrierFromPrevStep == 0) {
                        scanX = scanZ / 2;
                        letterWithNum.put(x[x.length - slice], scanX);
                    } else if (currentAvailableNum.get(i) == (scanZ + 10) / 2 && carrierFromPrevStep == 0) {
                        scanX = (scanZ + 10) / 2;
                        carrier = (scanZ + 10) / 10;
                        letterWithNum.put(x[x.length - slice], scanX);
                    }
                }

                if (scanX == null && scanZ == null) {
                    scanX = currentAvailableNum.get(i);
                    letterWithNum.put(x[x.length - slice], scanX);

                    scanZ = scanX * 2 + carrierFromPrevStep;
                    if (scanZ >= 10) {
                        carrier = scanZ / 10;
                        scanZ = scanZ % 10;
                    } else {
                        carrier = 0;
                    }
                    letterWithNum.put(z[z.length - slice], scanZ);
                }

                if (scanX != null && scanX * 2 + carrierFromPrevStep == scanZ + carrier * 10) {
                    Deque<Integer> tempQueue = new ArrayDeque<>(availableNum);
                    availableNum.clear();
                    while (tempQueue.peek() != null) {
                        int element = tempQueue.remove();
                        if (element != scanZ && element != scanX) {
                            availableNum.add(element);
                        }
                    }

                    boolean check = start(++slice);
                    if (check) {
                        return true;
                    } else {
                        slice--;
                        restoreInitialInputs(slice);
                    }
                }
            }
        }
        return false;
    }

    private static void restoreInitialInputs(int slice) {
        letterWithNum.put(x[x.length - slice], sliceBeforeChanges.get(slice).get(0));
        letterWithNum.put(y[y.length - slice], sliceBeforeChanges.get(slice).get(1));
        letterWithNum.put(z[z.length - slice], sliceBeforeChanges.get(slice).get(2));

        availableNum.clear();
        updateAvailableNum();

        carrier = 0;
    }

    private static void getMemorizeForReturn(int slice) {
        List<Integer> beforeChange = new ArrayList<>();

        beforeChange.add(letterWithNum.get(x[x.length - slice]));
        beforeChange.add(letterWithNum.get(y[y.length - slice]));
        beforeChange.add(letterWithNum.get(z[z.length - slice]));
        sliceBeforeChanges.put(slice, beforeChange);
    }

    private static boolean checkResult() {
        double fistAmount = getNumber(x);
        double secondAmount = getNumber(y);
        double resultAmount = getNumber(z);

        return fistAmount + secondAmount == resultAmount;
    }

    private static double getNumber(Character[] arr) {
        double result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += Math.pow(10, arr.length - 1 - i) * letterWithNum.get(arr[i]);
        }
        return result;
    }

    private static boolean checkAllLettersHasNum() {
        return letterWithNum.entrySet().stream().filter(x -> x.getValue() == null).toList().isEmpty();
    }
}
