package deVilliers;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

class SortbyR implements Comparator<OrganismsLife>
{
    @Override
    public int compare(OrganismsLife a, OrganismsLife b)
    {
        double c = (a.rsquared * 1000000) - (b.rsquared* 1000000);
        int d = Double.compare(a.rsquared, b.rsquared);
        return d;
    }
}
public class Population
{
    ArrayList<input> populationInput;
    ArrayList<OrganismsLife> population = new ArrayList<>();

    public Population() {
        readCSV rcsv = new readCSV();
        populationInput = rcsv.readfile("TrainingSet.csv");
        System.out.println(populationInput.size());
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            OrganismsLife life = new OrganismsLife(populationInput.get(0), i, populationInput.size());

            for (int j = 0; j < populationInput.size(); j++)
            {
                life.Live(populationInput.get(j));
            }
            population.add(life);
        }
        Collections.sort(population, new SortbyR());
        Collections.reverse(population);
        Long end = System.currentTimeMillis();
        System.out.println("population size = " + population.size());
        System.out.println("Time = " + (end-begin) + " ms");
       /* for (OrganismsLife cur : population)
        {
            System.out.println("R^2 = " + cur.rsquared*100.0);
            cur.printP();
        }*/
    }

    public static ArrayList<Integer> BreedingPairs()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            int cur = (normalRandomInteger(i * 3.0) + (i % 5) + (int)uniformPosRandomNumber(100.0)+ (int)uniformPosRandomNumber(50.0) +40) % 100;
            numbers.add(cur);
        }

        for (int i = 0; i <= 11; i++)
        {
            int cur = (int)uniformPosRandomNumber(100.0);
            numbers.add(cur);
        }
        numbers.add(0);
        numbers.add(0);
        numbers.add(99);
        numbers.add(99);
        System.out.println(numbers.size());
        return numbers;
    }

    public void Breed(ArrayList<Integer> pairs)
    {
        ArrayList<Double> alphas = new ArrayList<>();
        for (int i =0; i <= pairs.size() - 2; i = i +2) {
            double curAlpha = 1-((pairs.get(i) + pairs.get(i + 1)) / 400.0);

            if (curAlpha == 1)
            {
                curAlpha = 0.99999999;
            }

            System.out.println(i+ ". " + pairs.get(i) + " : " + pairs.get(i+1) +" Alpha = " + curAlpha);
            alphas.add(curAlpha);
            OrganismsLife ParentA;
            OrganismsLife ParentB;
            if (pairs.get(i) < pairs.get(i + 1))//We know that the organism at i is a better fit
            {
                ParentA = population.get(pairs.get(i));
                ParentB = population.get(pairs.get(i + 1));
            }
            else
            {
                ParentA = population.get(pairs.get(i + 1));
                ParentB = population.get(pairs.get(i));
            }

            Double[] childB1 = new Double[ParentA.creature.getBeta().length];
            Double[] childB2 = new Double[ParentA.creature.getBeta().length];
            TDistribution ttable = new TDistribution(ParentA.DoF);
            for (int j = 0; j <= ParentA.tBetaTS.length - 1; j++)
            {
                if (Math.abs(ParentA.tBetaTS[j]) > Math.abs(ParentB.tBetaTS[j]))
                {
                    childB1[j] = ParentA.creature.getBeta()[j]+ (normalRandomNumber(ttable.inverseCumulativeProbability(curAlpha))*ParentA.standardErrorBeta[j]);//Sample from Confidence Interval
                    childB2[j] = ParentB.creature.getBeta()[j]+ (normalRandomNumber(ttable.inverseCumulativeProbability(curAlpha))*ParentB.standardErrorBeta[j]);//Sample from Confidence Interval
                    //System.out.println(ttable.inverseCumulativeProbability(curAlpha));
                }
                else
                {
                    childB2[j] = ParentA.creature.getBeta()[j]+ (normalRandomNumber(ttable.inverseCumulativeProbability(curAlpha))*ParentA.standardErrorBeta[j]);//Sample from Confidence Interval
                    childB1[j] = ParentB.creature.getBeta()[j]+ (normalRandomNumber(ttable.inverseCumulativeProbability(curAlpha))*ParentB.standardErrorBeta[j]);//Sample from Confidence Interval
                }
            }
            OrganismsLife child1 = new OrganismsLife(populationInput.get(0), i, ParentA.DoF.intValue(), childB1); // Make child which is slight variant of best traits of parents
            OrganismsLife child2 = new OrganismsLife(populationInput.get(0), i, ParentA.DoF.intValue(), childB2); // Make child which is hugely diverse of worst traits of parents
        }
        ArrayList<OrganismsLife> newPopulation = new ArrayList<>();

    }

    /**
     * @param Range [0; Range]
     * @return  pseudorandom number from a Uniform Distribution within the range
     */
    public static Double normalRandomDouble(Double Range)
    {
        Random r = new Random();
        double dmax =  5.539589635447753;

        GaussianRandomGenerator rnd = new GaussianRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        return (cur/dmax) * Range;
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
    public static double uniformPosRandomNumber(Double Range)
    {
        double dmax =  1.7320508071499143;
        UniformRandomGenerator rnd = new UniformRandomGenerator(new Well19937c(8));
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
