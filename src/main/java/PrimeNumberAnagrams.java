import java.util.*;

public class PrimeNumberAnagrams {
    public static int primeNumberAnagramMaxGroupSize(int range) {
        List<Integer> primeNumbers = primeNumbers(range);
        List<Integer> allAnagrams = findAnagrams(primeNumbers);
        List<String> allStringAnagrams = convertAnagramsToStrings(allAnagrams);
        List<List<String>> groupedAnagrams = groupAnagrams(allStringAnagrams);
        return findMaxSize(groupedAnagrams);
    }

    public static List<Integer> findAnagrams(List<Integer> primeNumbers) {
        Set<Integer> allAnagrams = new HashSet<>();
        for (int i = 1; i < primeNumbers.size(); i++) {
            for (int j = i + 1; j < primeNumbers.size(); j++) {
                if (haveSameDigits(primeNumbers.get(i), primeNumbers.get(j)) && !allAnagrams.contains(primeNumbers.get(j))) {
                    allAnagrams.add(primeNumbers.get(i));
                    allAnagrams.add(primeNumbers.get(j));
                }
            }
        }
        List<Integer> result = new ArrayList<>(allAnagrams);
        return result;
    }


    public static List<Integer> primeNumbers(int range) {
        List<Integer> primes = new ArrayList<>();
        boolean[] referencePrimes = PrimeNumberCheck.primeTable(range);
        for (int i = 2; i < range; i++) {
            if (PrimeNumberCheck.isNumberFromRangeIsPrime(i, referencePrimes)) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static boolean haveSameDigits(int firstNumber, int secondNumber) {
        int[] freqFirstNumber = new int[10];
        int[] freqSecondNumber = new int[10];

        updateFrequency(firstNumber, freqFirstNumber);
        updateFrequency(secondNumber, freqSecondNumber);
        for (int i = 0; i < 10; i++) {
            if (freqFirstNumber[i] != freqSecondNumber[i])
                return false;
        }
        return true;
    }

    private static void updateFrequency(int n, int[] freq) {
        while (n > 0) {
            int digit = n % 10;
            freq[digit]++;
            n /= 10;
        }
    }

    public static List<String> convertAnagramsToStrings(List<Integer> anagrams) {
        List<String> result = new ArrayList<>();
        for (Integer number : anagrams) {
            result.add(number.toString());
        }
        return result;
    }

    public static List<List<String>> groupAnagrams(List<String> anagrams) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : anagrams) {
            char[] chArr = str.toCharArray();
            Arrays.sort(chArr);
            String key = new String(chArr);

            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> stringList = new ArrayList<>();
                stringList.add(str);
                map.put(key, stringList);
            }
        }
        result.addAll(map.values());
        return result;
    }

    public static int findMaxSize(List<List<String>> input) {
        int size = input.get(0).size();
        for (List<String> list : input) {
            if (list.size() > size) {
                size = list.size();
            }
        }
        return size;
    }

}
