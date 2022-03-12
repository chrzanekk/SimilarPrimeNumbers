import java.util.*;

public class PrimeNumberAnagrams {
    public static int primeNumberAnagramMaxGroupSizeMethodOne(int range) {
        long start = System.currentTimeMillis();
        List<String> primeNumbers = primeNumbersAsString(range);
        long end = System.currentTimeMillis();
        System.out.println("Finding primes:");
        System.out.println(end-start);

        start = System.currentTimeMillis();
        List<String> allAnagrams = findAnagramsAsString(primeNumbers);
        end = System.currentTimeMillis();
        System.out.println("Finding all anagrams:");
        System.out.println(end-start);

        start = System.currentTimeMillis();
        List<List<String>> groupedAnagrams = groupAnagramsAsString(allAnagrams);
        end = System.currentTimeMillis();
        System.out.println("Grouping anagrams: ");
        System.out.println(end-start);

        start = System.currentTimeMillis();
        Integer result = PrimeNumberAnagrams.findMaxSizeAsString(groupedAnagrams);
        end = System.currentTimeMillis();
        System.out.println("Finding max length group:");
        System.out.println(end-start);

        return result;
    }

    public static int primeNumberAnagramMaxGroupSizeMethodTwo(int range) {
        long start = System.currentTimeMillis();
        List<Integer> primeNumbers = primeNumbersAsInteger(range);
        long end = System.currentTimeMillis();
        System.out.println("Finding primes:");
        System.out.println(end-start);

        start = System.currentTimeMillis();
        List<Integer> allAnagrams = findAnagramsAsInteger(primeNumbers);
        end = System.currentTimeMillis();
        System.out.println("Finding all anagrams:");
        System.out.println(end-start);

        start = System.currentTimeMillis();
        List<List<Integer>> groupedAnagrams = groupAnagramsAsInteger(allAnagrams);
        end = System.currentTimeMillis();
        System.out.println("Grouping anagrams: ");
        System.out.println(end-start);

        start = System.currentTimeMillis();
        Integer result = findMaxSizeAsInteger(groupedAnagrams);
        end = System.currentTimeMillis();
        System.out.println("Finding max length group:");
        System.out.println(end-start);

        return result;
    }

    //here is place to improve/change
    public static List<String> findAnagramsAsString(List<String> primeNumbers) {
        Set<String> allAnagrams = new HashSet<>();

        for (int i = 1; i < primeNumbers.size(); i++) {
            for (int j = i + 1; j < primeNumbers.size(); j++) {
                if (isAnagramAsString(primeNumbers.get(i), primeNumbers.get(j))) {
                    allAnagrams.add(primeNumbers.get(i));
                    allAnagrams.add(primeNumbers.get(j));
                }
            }
        }
        List<String> result = new ArrayList<>(allAnagrams);
        return result;
    }

    public static List<Integer> findAnagramsAsInteger(List<Integer> primeNumbers) {
        Set<Integer> allAnagrams = new HashSet<>();

        for (int i = 1; i < primeNumbers.size(); i++) {
            for (int j = i + 1; j < primeNumbers.size(); j++) {
                if (isAnagramAsInteger(primeNumbers.get(i), primeNumbers.get(j))) {
                    allAnagrams.add(primeNumbers.get(i));
                    allAnagrams.add(primeNumbers.get(j));
                }
            }
        }
        List<Integer> result = new ArrayList<>(allAnagrams);
        return result;
    }


    public static List<String> primeNumbersAsString(int range) {
        List<String> primes = new ArrayList<>();
        int border = borderFind(range);
        int newRange = range - border;
        boolean[] referencePrimes = PrimeNumberCheck.primeTable(range);
        for (int i = border; i < range; i++) {
            if (PrimeNumberCheck.isNumberFromRangeIsPrime(i, referencePrimes)) {
                primes.add(Integer.toString(i));
            }
        }
        return primes;
    }

    public static List<Integer> primeNumbersAsInteger(int range) {
        List<Integer> primes = new ArrayList<>();
        int border = borderFind(range);
        int newRange = range - border;
        boolean[] referencePrimes = PrimeNumberCheck.primeTable(range);
        for (int i = border; i < range; i++) {
            if (PrimeNumberCheck.isNumberFromRangeIsPrime(i, referencePrimes)) {
                primes.add(i);
            }
        }
        return primes;
    }


    public static boolean isAnagramAsString(String firstNumber, String secondNumber) {
        Hashtable<Character, Integer> hashtable = new Hashtable<>();
        for (Character ch : firstNumber.toCharArray()) {
            if (hashtable.get(ch) == null) {
                hashtable.put(ch, 1);
            } else {
                hashtable.put(ch, hashtable.get(ch) + 1);
            }
        }
        for (Character ch : secondNumber.toCharArray()) {
            if (hashtable.get(ch) == null) {
                return false;
            } else if (hashtable.get(ch) == 1) {
                hashtable.remove(ch);
            } else {
                hashtable.put(ch, hashtable.get(ch) - 1);
            }
        }
        return hashtable.isEmpty();
    }

    public static boolean isAnagramAsInteger(Integer firstNumber, Integer secondNumber) {
        int[] count = new int[10];
        for (int i = 0; i < 10; ++i) {
            count[i] = 0;
        }
        while (firstNumber > 0) {
            count[firstNumber % 10]++;
            firstNumber = firstNumber / 10;
        }
        while (secondNumber > 0) {
            count[secondNumber % 10]--;
            secondNumber = secondNumber / 10;
        }
        for (int i = 0; i < 10; ++i) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public static List<List<String>> groupAnagramsAsString(List<String> anagrams) {
        HashMap<HashMap<Character, Integer>, List<String>> map = new HashMap<>();
        for (String str : anagrams) {
            HashMap<Character, Integer> tempMap = new HashMap<>();

            for (int i = 0; i < str.length(); i++) {
                if (tempMap.containsKey(str.charAt(i))) {
                    int x = tempMap.get(str.charAt(i));
                    tempMap.put(str.charAt(i), ++x);
                } else {
                    tempMap.put(str.charAt(i), 1);
                }
            }
            if (map.containsKey(tempMap)) {
                map.get(tempMap).add(str);
            } else {
                List<String> tempList = new ArrayList<>();
                tempList.add(str);
                map.put(tempMap, tempList);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (HashMap<Character, Integer> temp : map.keySet()) {
            result.add(map.get(temp));
        }
        return result;
    }

    public static List<List<Integer>> groupAnagramsAsInteger(List<Integer> anagrams) {
        HashMap<HashMap<Character, Integer>, List<Integer>> map = new HashMap<>();
        for (Integer number : anagrams) {
            String intAsString = String.valueOf(number);
            HashMap<Character, Integer> tempMap = new HashMap<>();

            for (int i = 0; i < intAsString.length(); i++) {
                if (tempMap.containsKey(intAsString.charAt(i))) {
                    int x = tempMap.get(intAsString.charAt(i));
                    tempMap.put(intAsString.charAt(i), ++x);
                } else {
                    tempMap.put(intAsString.charAt(i), 1);
                }
            }
            if (map.containsKey(tempMap)) {
                map.get(tempMap).add(number);
            } else {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(number);
                map.put(tempMap, tempList);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (HashMap<Character, Integer> temp : map.keySet()) {
            result.add(map.get(temp));
        }
        return result;
    }

    public static int findMaxSizeAsString(List<List<String>> input) {
        int size = 0;
        for (List<String> list : input) {
            if (list.size() > size) {
                size = list.size();
            }
        }
        return size;
    }

    public static int findMaxSizeAsInteger(List<List<Integer>> input) {
        int size = 0;
        for (List<Integer> list : input) {
            if (list.size() > size) {
                size = list.size();
            }
        }
        return size;
    }


    private static int borderFind(int number) {
        int border;
        if (number > 100000000) {
            border = 100000000;
        } else if (number > 10000000) {
            border = 10000000;
        } else if (number > 1000000) {
            border = 1000000;
        } else if (number > 100000) {
            border = 100000;
        } else if (number > 10000) {
            border = 10000;
        } else {
            border = 0;
        }
        return border;
    }

}
