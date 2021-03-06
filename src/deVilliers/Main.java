package deVilliers;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {
        int icount = 0;
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes.add(20);
        //sizes.add(50);
        sizes.add(100);
        //sizes.add(500);
        sizes.add(1000);
       // sizes.add(10000);

        ArrayList<Double> mutationmagnitudes = new ArrayList<>();
        //mutationmagnitudes.add(0.0001);
        mutationmagnitudes.add(0.0005);
        mutationmagnitudes.add(0.001);
        mutationmagnitudes.add(0.005);
        /*mutationmagnitudes.add(0.01);
        mutationmagnitudes.add(0.05);
        mutationmagnitudes.add(0.1);
        mutationmagnitudes.add(0.5);*/

        ArrayList<Double> mutationrates = new ArrayList<>();
        //  mutationrates.add(0.0001);
        mutationrates.add(0.0005);
        mutationrates.add(0.001);
        mutationrates.add(0.005);
      /*  mutationrates.add(0.01);
        mutationrates.add(0.05);
        mutationrates.add(0.1);
        mutationrates.add(0.5);*/

        ArrayList<Double> crossoverrates = new ArrayList<>();
        /*crossoverrates.add(0.01);
        crossoverrates.add(0.05);
        crossoverrates.add(0.1);*/
        crossoverrates.add(0.20);
        crossoverrates.add(0.50);
        crossoverrates.add(0.80);
        /* crossoverrates.add(0.90);*/

        ArrayList<Integer> selectionOperators = new ArrayList<>();
        selectionOperators.add(0);
        selectionOperators.add(1);
        selectionOperators.add(2);
        selectionOperators.add(3);
        selectionOperators.add(4);
        //selectionOperators.add(5);

        readCSV rcv = new readCSV();

        for (int i = 0; i <= sizes.size()-1; i++) {
            Population model1 = new Population(sizes.get(i), "Rsquared > 0.98 or Generations == 500", 0.0, 0.0, 0.0, -1);
            int igen = 1;
            while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                System.out.println("Generation: " + igen);
                System.out.println("__________________________________________________________________________");
                model1.Evolve();
                if (igen == 1) {
                    rcv.writeCsvFile("model1.csv", model1.toPrintString());
                } else if ((igen % 30) == 0) {
                    rcv.writeCsvFile("model1.csv", model1.toPrintString());
                }

                System.out.println("__________________________________________________________________________");
                igen = igen + 1;
            }
            System.out.println("Best after " + igen + " generations");
            model1.population.get(0).creature.printBeta();
            rcv.writeCsvFile("model1.csv", model1.toPrintString());
            System.out.println(model1.toString());
            icount = icount+1;
            System.out.println("Counter =" + icount);
        }
        for (int i = 0; i <= sizes.size()-1; i++) {
            for (int j =0; j <= mutationmagnitudes.size() - 1; j++) {
                Population model1 = new Population(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(1), mutationmagnitudes.get(j), crossoverrates.get(1), -1);
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    if (igen == 1) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("model1.csv", model1.toPrintString());
                System.out.println(model1.toString());
                icount = icount+1;
                System.out.println("Counter =" + icount);
            }
        }
        for (int i = 0; i <= sizes.size()-1; i++) {
            for (int j =0; j <= mutationrates.size() - 1; j++) {
                Population model1 = new Population(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(j), mutationmagnitudes.get(1), crossoverrates.get(1), -1);
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    if (igen == 1) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                icount = icount+1;
                System.out.println("Counter =" + icount);
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("model1.csv", model1.toPrintString());
                System.out.println(model1.toString());
            }
        }
        for (int i = 0; i <= sizes.size()-1; i++) {
            for (int j =0; j <= crossoverrates.size() - 1; j++) {
                Population model1 = new Population(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(1), mutationmagnitudes.get(1), crossoverrates.get(j), -1);
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    if (igen == 1) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("model1.csv", model1.toPrintString());
                System.out.println(model1.toString());
                icount = icount+1;
                System.out.println("Counter =" + icount);
            }
        }
        for (int i = 0; i <= sizes.size()-1; i++) {
            for (int j =0; j <= selectionOperators.size() - 1; j++) {
                Population model1 = new Population(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(1), mutationmagnitudes.get(1), crossoverrates.get(1), selectionOperators.get(i));
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    if (igen == 1) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("model1.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                icount = icount+1;
                System.out.println("Counter =" + icount);
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("model1.csv", model1.toPrintString());
                System.out.println(model1.toString());
            }
        }

    }

    public static double calcP(double Tts, double dof)
    {
        TDistribution tTable = new TDistribution(dof);
        return 1.0 - tTable.cumulativeProbability(Tts);
    }

    public static double tTestStatistic(double BetaI, double standardErrorBetaI)
    {
        return BetaI/standardErrorBetaI;
    }

    public static double StandardErrorB(double MSE, double SSXXi)
    {
        return ((Math.sqrt(MSE))/(SSXXi));
    }

    /**
     * @param Range [-Range; Range]
     * @return  pseudorandom number from a Uniform Distribution within the range
     */
    public static double uniformRandomNumber(Double Range)
    {
        double dmax =  1.7320508071499143;
        UniformRandomGenerator rnd = new UniformRandomGenerator(new Well19937c(8));
        double cur = rnd.nextNormalizedDouble();
        return (cur/dmax) * Range;
    }
    /**
     * @param Range [0; Range]
     * @return  pseudorandom number from a Uniform Distribution within the range
     * */
    public static double uniformPosRandomNumber(Double Range)
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
     * @param Range [0; Range]
     * @return  pseudorandom number from a Normal Distribution within the range
     */
    public static Double normalRandomNumber(Double Range)
    {
        Random r = new Random();
        double dmax =  5.539589635447753;

        GaussianRandomGenerator rnd = new GaussianRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        if (cur < 0)
            cur = cur *-1.0;
        cur = cur;
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

    public static void Comments()
    {
                /*for (int i = 1; i < 100; i++) {
            System.out.println(uniformPosRandomNumber(1.0));
        }*/
       /*TDistribution ttable = new TDistribution(1792);
       System.out.println(ttable.inverseCumulativeProbability(0.505));
        System.out.println(ttable.inverseCumulativeProbability(0.99));*/
        /*double dmax =  5.495228495191316;
        double dmin = -5.539589635447753;
        readCSV r = new readCSV();
        for (int i = 1; i < 100; i++) {
            int cur = (normalRandomInteger(i * 3.0) + (i % 5) + (int)uniformPosRandomNumber(100.0)+ (int)uniformPosRandomNumber(50.0) +40) % 100;
            r.writeCsvFile("hello7.csv", cur);
            System.out.println(cur);
        }*/

       /* readCSV rcsv = new readCSV();
        ArrayList<input> populationInput = rcsv.readfile("TrainingSet.csv");

        /*OrganismsLife Model1 = new OrganismsLife("TrainingSet.csv", 1);
        Model1.Live();
        for (int i = 0; i < 100; i++)
        {
            OrganismsLife model = new OrganismsLife("TrainingSet.csv", i);
            model.Live();

        }*/
      /*  ArrayList<OrganismsLife> population = new ArrayList<>();
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
        Collections.reverse(population);*/



       /* ArrayList<OrganismsLife> Breedablepopulation = new ArrayList<>();
        for (OrganismsLife cur : population)
        {
            if (cur.rsquared > 0)
            {
                Breedablepopulation.add(cur);
            }
        }
        for (OrganismsLife cur : Breedablepopulation)
        {
            System.out.println("R^2 = " + cur.rsquared*100.0);
            cur.printP();
        }

        System.out.println("Survivors : " + Breedablepopulation.size());
        Breedablepopulation.get(Breedablepopulation.size() - 1).creature.printBeta();*/

       /* Long end = System.currentTimeMillis();
        for (OrganismsLife cur : population)
        {
            System.out.println("R^2 = " + cur.rsquared*100.0);
            cur.printP();
            //cur.printT();
        }

        System.out.println("Time = " + (end-begin) + " ms");*/


       /* for (input cur : populationInput)
        {
            //System.out.println(cur.toString());
        }*/




       /* UniformRandomGenerator rnd = new UniformRandomGenerator(new Well19937c(71));
        double dmax =  1.7320508071499143;
        double dmin = -1.732050806946065;
        for (int i = 0; i < 50; i++)
        {
            double cur = rnd.nextNormalizedDouble();
            double dnum = (cur/dmax) *1000.0;
            if (cur > dmax)
                dmax = cur;
            else if (cur < dmin)
                dmin = cur;
            System.out.println(i + " : " + cur + " : " + dnum);
        }
        System.out.println("Max =  " + dmax);
        System.out.println("Min = " + dmin);

        double t = 0.7;
        TDistribution tTable = new TDistribution(1000);
        //  System.out.println(tTable.density(0.025));
        // System.out.println(tTable.logDensity(0.05));
        System.out.println(tTable.cumulativeProbability(t));
        System.out.println(tTable.cumulativeProbability(t)+tTable.cumulativeProbability(t));
        System.out.println(1.0 - tTable.cumulativeProbability(t));*/
        // write your code here
    }

}
