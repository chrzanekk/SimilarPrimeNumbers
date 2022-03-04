import java.util.*;

public class PrimeNumberAnagrams {
    public static int primeNumberAnagramMaxGroupSize(int range) {
        List<String> primeNumbers = primeNumbers(range);
        List<String> allAnagrams = findAnagrams(primeNumbers);
        List<List<String>> groupedAnagrams = groupAnagramsUsingHashMaps(allAnagrams);
        return findMaxSize(groupedAnagrams);
    }

//to do zoptymalizowania
    public static List<String> findAnagrams(List<String> primeNumbers) {
        Set<String> allAnagrams = new HashSet<>();

        for (int i = 1; i < primeNumbers.size(); i++) {
            for (int j = i + 1; j < primeNumbers.size(); j++) {
                if (isAnagram(primeNumbers.get(i), primeNumbers.get(j))) {
                    allAnagrams.add(primeNumbers.get(i));
                    allAnagrams.add(primeNumbers.get(j));
                }
            }
        }
        List<String> result = new ArrayList<>(allAnagrams);
        return result;
    }


    public static List<String> primeNumbers(int range) {
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

    //    do optymalizacji
    public static boolean haveSameDigits(String firstNumber, String secondNumber) {
        boolean status;
        if (firstNumber.length() != secondNumber.length()) {
            status = false;
        } else {
            char[] arrayFirstNumber = firstNumber.toLowerCase().toCharArray();
            char[] arraySecondNumber = secondNumber.toLowerCase().toCharArray();
            Arrays.sort(arrayFirstNumber);
            Arrays.sort(arraySecondNumber);
            status = Arrays.equals(arrayFirstNumber, arraySecondNumber);
        }
        return status;
    }

    public static boolean isAnagram(String firstNumber, String secondNumber) {
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

    public static List<List<String>> groupAnagramsUsingHashMaps(List<String> primeNumbers) {
        HashMap<HashMap<Character, Integer>, List<String>> map = new HashMap<>();
        for (String str : primeNumbers) {
            HashMap<Character, Integer> tempMap = new HashMap<>();

            for (int i = 0; i < str.length(); i++) {
                if (tempMap.containsKey(str.charAt(i))) {
                    int x = tempMap.get(str.charAt(i));
                    tempMap.put(str.charAt(i), ++x);
                } else {
                    tempMap.put(str.charAt(i), 1);
                }
            }
            if (map.containsKey(tempMap)){
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

    public static int findMaxSize(List<List<String>> input) {
        int size = 0;
        for (List<String> list : input) {
            if (list.size() > size) {
                size = list.size();
            }
        }
        return size;
    }

    private static int countDigits(int n) {
        if (n < 100000) {
            // 5 or less
            if (n < 100) {
                // 1 or 2
                if (n < 10)
                    return 1;
                else
                    return 2;
            } else {
                // 3 or 4 or 5
                if (n < 1000)
                    return 3;
                else {
                    // 4 or 5
                    if (n < 10000)
                        return 4;
                    else
                        return 5;
                }
            }
        } else {

            if (n < 10000000) {

                if (n < 1000000)
                    return 6;
                else
                    return 7;
            } else {

                if (n < 100000000)
                    return 8;
                else {

                    if (n < 1000000000)
                        return 9;
                    else
                        return 10;
                }
            }
        }
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
