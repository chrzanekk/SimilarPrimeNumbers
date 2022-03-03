import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> primes = PrimeNumberAnagrams.primeNumbers(1000);
        System.out.println(primes);
        System.out.println(primes.size());

        List<Integer> anagrams = PrimeNumberAnagrams.findAnagrams(primes);
        System.out.println(anagrams);
        System.out.println(anagrams.size());

        List<List<String>> groupedAnagrams =
                PrimeNumberAnagrams.groupAnagrams(PrimeNumberAnagrams.convertAnagramsToStrings(anagrams));
        System.out.println(groupedAnagrams);
        System.out.println(PrimeNumberAnagrams.findMaxSize(groupedAnagrams));

//        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(100));
//        2
//        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(1000));
//        4
//        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(10000));
//        11
//        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(100000));
//        39
//        System.out.println(PrimeNumberAnagrams.primeNumberAnagramMaxGroupSize(1000000));
//        148

    }
}

