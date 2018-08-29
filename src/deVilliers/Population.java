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
        int d =0;
        try {
            d = Double.compare(a.rsquared, b.rsquared);
        }
        catch (Exception e)
        {
            return 0;
        }
        return d;
    }
}
public class Population
{
    ArrayList<input> populationInput;
    ArrayList<OrganismsLife> population = new ArrayList<>();

    public Population(int populationSize) {
        readCSV rcsv = new readCSV();
        populationInput = rcsv.readfile("TrainingSet.csv");
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < populationSize; i++)
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
        System.out.println("Size = " + population.size());
        System.out.println("Best: " + "R^2 = " + population.get(0).rsquared*100.0 + "\t" + "\t" + "\t" + "SSE BEST = " + population.get(0).SSE);
        System.out.println("Middle: " + "R^2 = " + population.get((population.size()/2)).rsquared*100.0 + "\t" + "\t"  + "SSE Middle = " + population.get((population.size()/2)).SSE);
        System.out.println("Worst: " + "R^2 = " + population.get(population.size()-1).rsquared*100.0 + "\t" + "\t" +  "SSE Worst = " + population.get(population.size()-1).SSE);
        System.out.println("time = " + (end-begin) + " ms");
        //System.out.println("Size = " + population.size() + "\t" + "Best: " + "R^2 = " + population.get(0).rsquared*100.0 + "\t" + "SSE BEST= " + population.get(0).SSE + "\t" + "Worst: " + "R^2 = " + population.get(population.size()-1).rsquared*100.0 + "\t" +  "time = " + (end-begin) + " ms");
    }

    public void Evolve()
    {
        Long begin = System.currentTimeMillis();
        Breed(BreedingPairs());
        for (int i = 0; i < population.size(); i++) {
            for (int j = 0; j < populationInput.size(); j++) {
                OrganismsLife cur = population.get(i);
                cur.Live(populationInput.get(j));
            }
        }
        Collections.sort(population, new SortbyR());
        Collections.reverse(population);
        Long end = System.currentTimeMillis();
        System.out.println("Size = " + population.size());
        System.out.println("Best: " + "R^2 = " + population.get(0).rsquared*100.0 + "\t" + "\t" + "\t" + "SSE BEST = " + population.get(0).SSE);
        System.out.println("Middle: " + "R^2 = " + population.get((population.size()/2)).rsquared*100.0 + "\t" + "\t"  + "SSE Middle = " + population.get((population.size()/2)).SSE);
        System.out.println("Worst: " + "R^2 = " + population.get(population.size()-1).rsquared*100.0 + "\t" + "\t" +  "SSE Worst = " + population.get(population.size()-1).SSE);
        System.out.println("time = " + (end-begin) + " ms");

    }

    /**
     * Select
     * @return
     */
    public ArrayList<Integer> BreedingPairs()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= (population.size()*0.8); i++) {
            int cur = (normalRandomInteger(i * 3.0) + (i % 5) + uniformPosRandomNumber(100.0).intValue()+ uniformPosRandomNumber(50.0).intValue() +40) % 100;
            numbers.add(cur);
        }

        for (int i = 0; i <= (population.size()*0.11); i++)
        {
            int cur = uniformPosRandomNumber(100.0).intValue();
            numbers.add(cur);
        }
        return numbers;
    }

    public void Breed(ArrayList<Integer> pairs)
    {
        ArrayList<Double> alphas = new ArrayList<>();
        ArrayList<OrganismsLife> newPopulation = new ArrayList<>();
        int n = populationInput.size();
        for (int i = 1; i < pairs.size() - 1; i += 2) {
            int ipos1 = i-1;
            int ipos2 = i;
            double denominator = 4.0 * population.size();
            double curAlpha = 1-((pairs.get(ipos1) + pairs.get(ipos2)) / denominator);

            if (curAlpha == 1)
            {
                curAlpha = 0.99999999;
            }
            alphas.add(curAlpha);
            OrganismsLife ParentA;
            OrganismsLife ParentB;
            if (pairs.get(ipos1) < pairs.get(ipos2))//We know that the organism at i is a better fit
            {
                ParentA = population.get(pairs.get(ipos1));
                ParentB = population.get(pairs.get(ipos2));
            }
            else
            {
                ParentB = population.get(pairs.get(ipos1));
                ParentA = population.get(pairs.get(ipos2));
            }
            OrganismsLife childA = goodBreed(ParentA, ParentB, curAlpha, populationInput.get(0), ipos1, n);
            OrganismsLife childB = badBreed(ParentA, ParentB, curAlpha, populationInput.get(0), ipos1, n);
            newPopulation.add(childA);
            newPopulation.add(childB);
        }
        OrganismsLife childClone = BreedNoMutation(population.get(0), population.get(0), populationInput.get(0), newPopulation.size(), n);
        newPopulation.add(childClone);
        childClone = BreedNoMutation(population.get(0), population.get(0), populationInput.get(0), newPopulation.size(), n);
        newPopulation.add(childClone);

        childClone = BreedNoMutation(population.get(population.size()-1), population.get(population.size()-1), populationInput.get(0), newPopulation.size(), n);
        newPopulation.add(childClone);
        childClone = BreedNoMutation(population.get(population.size()-1), population.get(population.size()-1), populationInput.get(0), newPopulation.size(), n);
        newPopulation.add(childClone);
        while (newPopulation.size() < population.size())
        {
            childClone = BreedNoMutation(population.get(0), new OrganismsLife(populationInput.get(0), newPopulation.size(), population.get(0).DoF.intValue()), populationInput.get(0), newPopulation.size(), n);
            newPopulation.add(childClone);
        }
        population.clear();
        for (OrganismsLife cur : newPopulation)
        {
            population.add(cur);
        }
    }

    /**
     * @param ParentA
     * @param ParentB
     * @param alpha alpha-value of t to sample from
     * @param first
     * @param lbl
     * @param n
     * @return Child of Parent's A and B, this child has the best gene combination of both parents with mutation occuring on the Confidence Interval of the parent with the better Allel
     */
    public static OrganismsLife goodBreed(OrganismsLife ParentA, OrganismsLife ParentB, double alpha, input first, int lbl, int n)
    {
        OrganismsLife child1;
        Double[] childB1 = new Double[ParentA.creature.getBeta().length];
        Double dof = ParentA.DoF;
        if (dof <= 0) {
            dof = 1792.0;
        }
        TDistribution ttable = new TDistribution(dof);
        for (int j = 0; j <= ParentA.tBetaTS.length - 1; j++) {
            if (Math.abs(ParentA.tBetaTS[j]) > Math.abs(ParentB.tBetaTS[j])) {
                childB1[j] = ParentA.creature.getBeta()[j] + (normalRandomNumber(ttable.inverseCumulativeProbability(alpha)) * ParentA.standardErrorBeta[j]);//Sample from Confidence Interval
            }
            else{
                childB1[j] = ParentB.creature.getBeta()[j] + (normalRandomNumber(ttable.inverseCumulativeProbability(alpha)) * ParentB.standardErrorBeta[j]);//Sample from Confidence Interval
            }
        }
            child1 = new OrganismsLife(first, lbl, n, childB1); // Make child which is slight variant of best traits of parents


        return child1;
    }
    public static OrganismsLife badBreed(OrganismsLife ParentA, OrganismsLife ParentB, double alpha, input first, int lbl, int n)
    {
        OrganismsLife child2;
        Double dof = ParentA.DoF;
        Double[] childB2 = new Double[ParentA.creature.getBeta().length];
        if (dof <= 0) {
            dof = 1792.0;
        }
        TDistribution ttable = new TDistribution(dof);
        for (int j = 0; j <= ParentA.tBetaTS.length - 1; j++) {
            if (Math.abs(ParentA.tBetaTS[j]) > Math.abs(ParentB.tBetaTS[j])) {
                childB2[j] = ParentB.creature.getBeta()[j] + (normalRandomNumber(ttable.inverseCumulativeProbability(alpha)) * ParentB.standardErrorBeta[j]);//Sample from Confidence Interval
            } else {
                childB2[j] = ParentA.creature.getBeta()[j] + (normalRandomNumber(ttable.inverseCumulativeProbability(alpha)) * ParentA.standardErrorBeta[j]);//Sample from Confidence Interval
            }
        }
        child2 = new OrganismsLife(first, lbl, n, childB2); // Make child which is slight variant of best traits of parents
        return child2;
    }

    /**
     * When Breeding, crossover occurs but no mutation
     * @param ParentA
     * @param ParentB
     * @param first
     * @param lbl
     * @param n
     * @return
     */
    public static OrganismsLife BreedNoMutation(OrganismsLife ParentA, OrganismsLife ParentB, input first, int lbl, int n)
    {
        OrganismsLife child2;
        Double[] childB2 = new Double[ParentA.creature.getBeta().length];
        for (int j = 0; j <= ParentA.tBetaTS.length - 1; j++)
        {
            int iSwitch = uniformPosRandomNumber(1.0).intValue();
            if (iSwitch <= 0.5)
            {
                childB2[j] = ParentB.creature.getBeta()[j];
            }
            else
            {
                childB2[j] = ParentA.creature.getBeta()[j];
            }
        }
        child2 = new OrganismsLife(first, lbl, n, childB2); // Make child which is slight variant of best traits of parents
        return child2;
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



    /** Double[] childB1 = new Double[ParentA.creature.getBeta().length];
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
     newPopulation.add(child1);
     newPopulation.add(child2);**/
}
