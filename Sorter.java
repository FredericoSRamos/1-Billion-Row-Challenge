public class Sorter implements java.util.Comparator<String>
{
    @Override
    public int compare (String a, String b)
    {
        return a.compareTo(b);
    }
}