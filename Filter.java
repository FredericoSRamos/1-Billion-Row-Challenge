import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class Filter
{
    public static void main (String[] args)
    {

        Scanner reader = null;

        try
        {
            File file = new File ("weather_stations.csv");
            reader = new Scanner (file);
        } catch (Exception e)
        {
            System.out.println ("An error occurred.");
            e.printStackTrace();
            System.exit (1);
        }

        HashMap<String, Station> map = new HashMap<>();

        while (reader.hasNextLine())
        {
            String[] data = reader.nextLine().split(",");

            String name = data[0];
            double temperature = Double.parseDouble (data[1]);

            Station station;

            if (map.containsKey (name))
            {
                station = map.get (name);
                station.amount++;

                if (temperature > station.max)
                    station.max = temperature;

                if (temperature < station.min)
                    station.min = temperature;

                station.mean = station.mean + (temperature - station.mean) / station.amount;
            }
            else
            {
                station = new Station (temperature);
                map.put (name, station);
            }
        }

        ArrayList<String> list = new ArrayList<>();

        list.ensureCapacity (10000);
        list.addAll (map.keySet());
        list.sort (new Sorter());

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext())
        {
            String key = iterator.next();
            Station station = map.get (key);
            System.out.println (String.join (";", key, String.format ("%.1f", station.min), String.format ("%.1f", station.mean), String.format ("%.1f", station.max)).replace(",", "."));
        }

        reader.close();
    }
}