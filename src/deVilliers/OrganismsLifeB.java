package deVilliers;


import org.apache.commons.math3.distribution.TDistribution;

import java.util.ArrayList;

public class OrganismsLifeB
{
    //public final double SSE = 607090.261855908;
    Double SSyy = 1266528222270.0;
    Double[] sqSSxx = {8526.0, 2159956.0, 54284.0, 5978404.0, 9366.0, 3273248.0, 410176.0};

    OrganismB   creature;
    Double[]    standardErrorBeta;
    Double[]    tBetaTS;
    Double[]    pBeta;
    Double      SSE;
    Double      MSE;
    Double      DoF;
    ArrayList<input> food;
    Double rsquared;
    Integer icount;
    Integer N;

    public OrganismsLifeB(String sFileName, Integer lbl)
    {
        readCSV rcsv = new readCSV();
        food = rcsv.readfile("TrainingSet.csv");
        creature = new OrganismB(lbl, food.get(0));
        DoF = (double)food.size() - creature.getBeta().length;
        //System.out.println("Degrees of Freedom = " + DoF);
        standardErrorBeta = new Double[sqSSxx.length];
        tBetaTS = new Double[sqSSxx.length];
        pBeta = new Double[sqSSxx.length];
        icount = 0;
    }

    public OrganismsLifeB(input initial, Integer lbl, Integer N)
    {
        creature = new OrganismB(lbl, initial);
        this.N = N;
        DoF = (N*1.0) - creature.getBeta().length*1.0;
        //System.out.println("Degrees of Freedom = " + DoF);
        standardErrorBeta = new Double[sqSSxx.length];
        tBetaTS = new Double[sqSSxx.length];
        pBeta = new Double[sqSSxx.length];
        icount = 0;
        SSE = 0.0;
    }

    public OrganismsLifeB(input initial, Integer lbl, Integer N, Double[] beta)
    {
        creature = new OrganismB(lbl, initial, beta);
        this.N = N;
        DoF = (N*1.0) - creature.getBeta().length*1.0;
        //System.out.println("Degrees of Freedom = " + DoF);
        standardErrorBeta = new Double[sqSSxx.length];
        tBetaTS = new Double[sqSSxx.length];
        pBeta = new Double[sqSSxx.length];
        icount = 0;
        SSE = 0.00;
    }

    public void Live(input i)
    {
        creature.feed(i);
        creature.live();
        SSE = SSE + creature.getErrorSquared();
        icount = icount + 1;
        //System.out.println("Counter = " + icount + " != " + N);
        if (icount.compareTo(N) == 0)
        {
            //System.out.println("Hello");
            MSE = SSE/DoF;
            rsquared = 1 - (SSE/SSyy);
            //System.out.println("SSE = " + SSE);
            //System.out.println("R^2 = " + rsquared);
            popStandardError();
            popT();
            popCalcP();
            icount = 0;
        }


    }

    public void Live()
    {
        SSE = 0.0;
        for (int i = 0; i <= food.size() - 1; i++)
        {
            creature.feed(food.get(i));
            creature.live();
            SSE = SSE + creature.getErrorSquared();
        }
        MSE = SSE/DoF;
        rsquared = 1 - (SSE/SSyy);
        //System.out.println("SSE = " + SSE);
        System.out.println("R^2 = " + rsquared);
        popStandardError();
        popT();
        popCalcP();
        /*for (int j = 0; j <= pBeta.length - 1; j++)
        {
            System.out.println("B" + j + " = " + pBeta[j]*100);
        }*/
    }

    public void printP()
    {
        for (int j = 0; j <= pBeta.length - 1; j++)
        {
            System.out.println("P" + j + " = " + pBeta[j]*100);
        }
    }
    public void printT()
    {
        for (int j = 0; j <= tBetaTS.length - 1; j++)
        {
            System.out.println("T" + j + " = " + tBetaTS[j]);
        }
    }


    public void popStandardError(Double[] sqrtSSxx)
    {
        Double se = Math.sqrt(MSE);
        for (int i = 0; i <= sqrtSSxx.length - 1; i++)
        {
            standardErrorBeta[i] = se/sqrtSSxx[i];
        }
    }

    public void popStandardError()
    {
        Double se = Math.sqrt(MSE);
        for (int i = 0; i <= sqSSxx.length - 1; i++)
        {
            standardErrorBeta[i] = se/sqSSxx[i];
        }
    }

    public void popT()
    {
        for (int i = 0; i <= creature.getBeta().length - 2; i++)
        {
            tBetaTS[i] = creature.getBeta()[i]/standardErrorBeta[i];
        }
    }

    public void popCalcP()
    {

        if (DoF > 0) {
            TDistribution tTable = new TDistribution(DoF);
            for (int i = 0; i <= tBetaTS.length - 1; i++) {
                pBeta[i] = 1.0 - tTable.cumulativeProbability(Math.abs(tBetaTS[i]));
            }
        }
        else
        {
            TDistribution tTable = new TDistribution(1792);
            for (int i = 0; i <= tBetaTS.length - 1; i++) {
                pBeta[i] = 1.0 - tTable.cumulativeProbability(Math.abs(tBetaTS[i]));
            }
        }
    }

    public void popCalcP(double dof)
    {
        TDistribution tTable = new TDistribution(dof);
        for (int i = 0; i <= tBetaTS.length - 1; i++)
        {
            pBeta[i] = 1.0 - tTable.cumulativeProbability(Math.abs(tBetaTS[i]));
        }
    }


}
