package deVilliers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class readCSV {

    private static final String NEW_LINE_SEPARATOR = "\n";

    public static ArrayList<input> readfile(String sfile)
    {
        ArrayList<input> Marks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sfile));
            String line = null;
            Scanner scanner = null;
            int index = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                scanner = new Scanner(line);
                while (scanner.hasNext())
                {
                    String data = scanner.next();
                    input curDS = new input(data);
                   // System.out.println(curDS.toString());
                    Marks.add(curDS);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Marks;
    }

    public static void writeCsvFile(String fileName, Experiment e1) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(e1.print());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void writeCsvFile(String fileName, int i) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(String.valueOf(i));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static void writeCsvFile(String fileName, String s) throws IOException {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(s);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
