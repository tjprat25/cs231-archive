import java.util.Comparator;

public class WordTrendsFinderTests
{
    public static void WordTrendsFinderTests()
    {
        // case 1: testing WordTrendsFinderTests()
        {
            // set up
            WordTrendsFinder wt = new WordTrendsFinder();

            // test
            assert wt != null : "Error in WordTrendsFinder::WordTrendsFinder()";
        }

        // case 2: testing readWordCount()
        {
            // set up
            WordTrendsFinder wt1 = new WordTrendsFinder();
            WordTrendsFinder wt2 = new WordTrendsFinder();
            wt1.readWordCount("wcf_counttest1.txt");
            wt2.readWordCount("wcf_counttest2.txt");

            //verify
            System.out.println(wt1.wc.totalWordCount() + " == 24");
            System.out.println(wt2.wc.totalWordCount() + " == 55");

            // test
            assert wt1.wc.totalWordCount() == 10 : "Error in WordTrendsFinder::readWordCount()";
            assert wt2.wc.totalWordCount() == 10 : "Error in WordTrendsFinder::readWordCount()";
        }

        // case 3: testing frequency()
        {
            // set up
            WordTrendsFinder wt1 = new WordTrendsFinder();
            WordTrendsFinder wt2 = new WordTrendsFinder();
            wt1.readWordCount("wcf_counttest1.txt");
            wt2.readWordCount("wcf_counttest2.txt");

            //verify
            System.out.println(wt1.frequency("the") + " == .17");
            System.out.println(wt1.frequency("foolishness") + " == .04");
            System.out.println(wt1.frequency("wordnotinfile") + " == 0");
            System.out.println(wt2.frequency("one") + " == .02");
            System.out.println(wt2.frequency("two") + " == .04");
            System.out.println(wt2.frequency("wordnotinfile") + " == 0");

            // test
            assert wt1.frequency("the") == .17 : "Error in WordTrendsFinder::frequency()";
            assert wt1.frequency("foolishness") == .04 : "Error in WordTrendsFinder::frequency()";
            assert wt1.frequency("wordnotinfile") == 0 : "Error in WordTrendsFinder::frequency()";
            assert wt2.frequency("one") == .02 : "Error in WordTrendsFinder::frequency()";
            assert wt2.frequency("two") == .04 : "Error in WordTrendsFinder::frequency()";
            assert wt2.frequency("wordnotinfile") == 0 : "Error in WordTrendsFinder::frequency()";
        }

        // case 4: testing clear()
        {
            // set up
            WordTrendsFinder wt1 = new WordTrendsFinder();
            WordTrendsFinder wt2 = new WordTrendsFinder();
            wt1.readWordCount("wcf_counttest1.txt");
            wt2.readWordCount("wcf_counttest2.txt");
            wt1.clear();
            wt2.clear();

            //verify
            System.out.println(wt1.wc.totalWordCount() + " == 0");
            System.out.println(wt2.wc.totalWordCount() + " == 0");

            // test
            assert wt1.wc.totalWordCount() == 0 : "Error in WordTrendsFinder::clear()";
            assert wt2.wc.totalWordCount() == 0 : "Error in WordTrendsFinder::clear()";
        }
       
        System.out.println("Done testing WordTrendsFinder");
    }

    public static void main(String[] args)
    {
        WordTrendsFinderTests();
    }
}