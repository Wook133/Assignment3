package deVilliers;

import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.Random;

public class SelectionOperator {

    public ArrayList<Integer> select(int iSize, int iSelector)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        switch (iSelector)
        {
            case 0:
            {
                numbers = mySelection1(iSize);
            }
            break;
            case 1:
            {
                numbers = mySelection2(iSize);
            }
            break;
           /* case 2:
            {
                numbers = NormalSelectionHalf(iSize);
            }
            break;*/
            case 2:
            {
                numbers = NormalSelection0(iSize);
            }
            break;
            case 3:
            {
                numbers = UniformPairsWeightedFront(iSize);
            }
            break;
            case 4:
            {
                numbers = UniformPairs(iSize);
            }
            break;
            default:
            {
                numbers = NormalSelection0(iSize);
            }
            break;
        }
        return numbers;
    }


    public ArrayList<Integer> mySelection1(int iSize)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= (iSize*0.8); i++) {
            int cur = (normalRandomInteger(i * 3.0) + (i % 5) + uniformPosRandomNumber(iSize*1.0).intValue()+ uniformPosRandomNumber(iSize/2.0).intValue() +40) % iSize;
            numbers.add(cur);
        }

        for (int i = 0; i <= (iSize*0.2); i++)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }
        while (numbers.size() > iSize)
        {
            numbers.remove(numbers.size()-1);
        }
        while (numbers.size() < iSize)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }
        return numbers;
    }

    public ArrayList<Integer> mySelection2(int iSize)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= (iSize*0.8); i++) {
            int cur = (normalRandomInteger(i * 3.0) + (i % 5) + uniformPosRandomNumber(iSize*1.0).intValue()+ uniformPosRandomNumber(iSize/2.0).intValue() +40) % iSize;
            numbers.add(cur);
        }

        for (int i = 0; i <= (iSize*0.10); i++)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }
        for (int i = 0; i <= (iSize - numbers.size())/2; i++)
        {
            int cur = uniformPosRandomNumber(iSize*0.1).intValue();
            numbers.add(cur);
        }
        for (int i = 0; i <= (iSize - numbers.size())/2; i++)
        {
            int cur = numbers.size() - uniformPosRandomNumber(iSize*0.1).intValue();
            numbers.add(cur);
        }
        while (numbers.size() > iSize)
        {
            numbers.remove(numbers.size()-1);
        }
        while (numbers.size() < iSize)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }

        return numbers;
    }

    public ArrayList<Integer> NormalSelectionHalf(int iSize)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        while (numbers.size() <= iSize)
        {
            int cur = normalRandomNumber(iSize*1.0).intValue() + iSize;
            if ((cur >= 0) && (cur < iSize)) {
                numbers.add(cur);
            }
        }
        while (numbers.size() > iSize)
        {
            numbers.remove(numbers.size()-1);
        }
        while (numbers.size() < iSize)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }

        return numbers;
    }

    public ArrayList<Integer> NormalSelection0(int iSize)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        while (numbers.size() <= iSize)
        {
            numbers.add(normalRandomInteger(iSize*1.0));
        }
        while (numbers.size() > iSize)
        {
            numbers.remove(numbers.size()-1);
        }
        while (numbers.size() < iSize)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }

        return numbers;
    }


    public ArrayList<Integer> UniformPairsWeightedFront(int iSize)
    {
        ArrayList<Integer> numbers = new ArrayList<>();


        for (int i = 0; i <= iSize; i++)
        {
            int cur = uniformPosRandomNumber(i*1.0).intValue();
            numbers.add(cur);
        }
        while (numbers.size() > iSize)
        {
            numbers.remove(numbers.size()-1);
        }
        while (numbers.size() < iSize)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }
        return numbers;
    }

    public ArrayList<Integer> UniformPairs(int iSize)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= iSize; i++)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }
        while (numbers.size() > iSize)
        {
            numbers.remove(numbers.size()-1);
        }
        while (numbers.size() < iSize)
        {
            int cur = uniformPosRandomNumber(iSize*1.0).intValue();
            numbers.add(cur);
        }
        return numbers;
    }




    /**
     * @param Range [0; Range]
     * @return  pseudorandom number from a Uniform Distribution within the range
     */
    public static Integer normalRandomInteger(Double Range)
    {
        Random r = new Random();
        double dmax =  5.539589635447753;

        GaussianRandomGenerator rnd = new GaussianRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        if (cur < 0)
            cur = cur *-1.0;

        cur = (cur/dmax) * Range;
        cur = cur - 1;

        Integer i = (int)cur;
        return i;
    }

    /**
     * @param Range [0; Range]
     * @return  pseudorandom number from a Uniform Distribution within the range
     * */
    public static Double uniformPosRandomNumber(Double Range)
    {
        double dmax =  1.7320508071499143;
        Random r = new Random();
        UniformRandomGenerator rnd = new UniformRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        cur = (cur/dmax) * Range;
        if (cur < 0)
            cur = cur *-1.0;
        return cur;
    }

    /**
     * @param Range [-Range; Range]
     * @return  pseudorandom number from a Normal Distribution within the range
     */
    public static Double normalRandomNumber(Double Range)
    {
        Random r = new Random();
        double dmax =  5.539589635447753;

        GaussianRandomGenerator rnd = new GaussianRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        cur = (cur/dmax) * Range;
        return cur;
    }

}
