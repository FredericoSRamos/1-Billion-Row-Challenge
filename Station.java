public class Station
{
    public double min;
    public double max;
    public double mean;
    public int amount;

    public Station (double temperature)
    {
        this.min = temperature;
        this.max = temperature;
        this.mean = temperature;
        this.amount = 1;
    }
}