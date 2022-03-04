import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<String> primes = PrimeNumberAnagrams.primeNumbers(100000);
////        System.out.println(primes);
//        System.out.println(primes.size());
//        System.out.println(primes.get(primes.size()-1));
//
//        List<String> anagrams = PrimeNumberAnagrams.findAnagrams(primes);
////        System.out.println(anagrams);
//        System.out.println(anagrams.size());
////
//        List<List<String>> groupedAnagrams = PrimeNumberAnagrams.groupAnagrams(anagrams);
////        System.out.println(groupedAnagrams);
//        System.out.println(PrimeNumberAnagrams.findMaxSize(groupedAnagrams));

        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(10));
//
        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(100));
//        2
        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(1000));
//        4
        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(10000));
//        11
        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(100000));
//        39
        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(1000000));
//        148
        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(10000000));
//        148

    }
}

