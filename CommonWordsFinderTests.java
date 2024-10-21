import java.util.Comparator;

public class CommonWordsFinderTests
{
    public static void CommonWordsFinderTests()
    {
        // case 1: testing CommonWordsFinder()
        {
            // set up
            CommonWordsFinder cwf = new CommonWordsFinder();

            // test
            assert cwf != null : "Error in CommonWordsFinder::CommonWordsFinder()";
        }

        // case 2: testing readWordCount()
        {
            // set up
            CommonWordsFinder cwf1 = new CommonWordsFinder();
            CommonWordsFinder cwf2 = new CommonWordsFinder();
            cwf1.readWordCount("wcf_counttest1.txt");
            cwf2.readWordCount("wcf_counttest2.txt");

            //verify
            System.out.println(cwf1.heap.size() + " == 10");
            System.out.println(cwf2.heap.size() + " == 10");

            // test
            assert cwf1.heap.size() == 10 : "Error in CommonWordsFinder::readWordCount()";
            assert cwf2.heap.size() == 10 : "Error in CommonWordsFinder::readWordCount()";
        }

        // case 3: testing poll()
        {
            // set up
            CommonWordsFinder cwf1 = new CommonWordsFinder();
            CommonWordsFinder cwf2 = new CommonWordsFinder();
            CommonWordsFinder cwf3 = new CommonWordsFinder();
            cwf1.readWordCount("wcf_counttest1.txt");
            cwf2.readWordCount("wcf_counttest2.txt");

            //verify
            System.out.println("Word order not always accurate since multiple words have the same count");
            System.out.println(cwf1.poll() + ", " + cwf1.poll() + ", " + cwf1.poll() + ", " + cwf1.poll());
            System.out.println("the, it, of, was");
            System.out.println(cwf1.poll() + ", " + cwf1.poll());
            System.out.println("age, times");
            System.out.println(cwf1.poll() + ", " + cwf1.poll() + ", " + cwf1.poll() + ", " + cwf1.poll());
            System.out.println("foolishness, best, worst, wisdom");
            System.out.println(cwf2.poll() + " == ten");
            System.out.println(cwf2.poll() + " == nine");
            System.out.println(cwf2.poll() + " == eight");
            System.out.println(cwf2.poll() + " == seven");
            System.out.println(cwf2.poll() + " == six");
            System.out.println(cwf2.poll() + " == five");
            System.out.println(cwf2.poll() + " == four");
            System.out.println(cwf2.poll() + " == three");
            System.out.println(cwf2.poll() + " == two");
            System.out.println(cwf2.poll() + " == one");
            System.out.println(cwf3.poll() + " == null");

            // test
            assert cwf1.poll() == "the" || cwf1.poll() == "it" || cwf1.poll() == "of" || cwf1.poll() == "was" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "the" || cwf1.poll() == "it" || cwf1.poll() == "of" || cwf1.poll() == "was" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "the" || cwf1.poll() == "it" || cwf1.poll() == "of" || cwf1.poll() == "was" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "the" || cwf1.poll() == "it" || cwf1.poll() == "of" || cwf1.poll() == "was" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "age" || cwf1.poll() == "times" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "age" || cwf1.poll() == "times" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "foolishness" || cwf1.poll() == "best" || cwf1.poll() == "worst" || cwf1.poll() == "wisdom" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "foolishness" || cwf1.poll() == "best" || cwf1.poll() == "worst" || cwf1.poll() == "wisdom" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "foolishness" || cwf1.poll() == "best" || cwf1.poll() == "worst" || cwf1.poll() == "wisdom" : "Error in CommonWordsFinder::poll()";
            assert cwf1.poll() == "foolishness" || cwf1.poll() == "best" || cwf1.poll() == "worst" || cwf1.poll() == "wisdom" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "ten" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "nine" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "eight" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "seven" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "six" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "five" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "four" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "three" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "two" : "Error in CommonWordsFinder::poll()";
            assert cwf2.poll() == "one" : "Error in CommonWordsFinder::poll()";
            assert cwf3.poll() == null : "Error in CommonWordsFinder::poll()";
        }

        // case 4: testing clear()
        {
            // set up
            CommonWordsFinder cwf1 = new CommonWordsFinder();
            CommonWordsFinder cwf2 = new CommonWordsFinder();
            cwf1.readWordCount("wcf_counttest1.txt");
            cwf2.readWordCount("wcf_counttest2.txt");
            cwf1.clear();
            cwf2.clear();

            //verify
            System.out.println(cwf1.heap.size() + " == 0");
            System.out.println(cwf2.heap.size() + " == 0");

            // test
            assert cwf1.heap.size() == 0 : "Error in CommonWordsFinder::clear()";
            assert cwf2.heap.size() == 0 : "Error in CommonWordsFinder::clear()";
        }
       
        System.out.println("Done testing CommonWordsFinder");
    }

    public static void main(String[] args)
    {
        CommonWordsFinderTests();
    }
}