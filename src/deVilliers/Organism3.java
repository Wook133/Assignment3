package deVilliers;



import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.Random;

public class Organism3
{
    private Integer     Label;
    private Double[]    X;
    private Double[]    Alpha;
    private Double[]    Beta;
    private Double      Y;
    private Double      Yhat;
    private Double      errorSquared;

    /**
     * Initialize
     * @param lbl
     */
    public Organism3(Integer lbl, input row)
    {
        Double temp = 2500.0;
        Double temp2 = 1.5;
        this.Label          = lbl;
        int ilength         = row.getX().length;
        X                   = new Double[ilength];
        Alpha                = new Double[ilength];
        Beta                = new Double[ilength+1];
        for (int i = 0; i <= Beta.length - 1; i++)
        {
            Beta[i] = uniformRandomNumber(temp);
            if (i <= Beta.length - 2)
            {
                Alpha[i] = 1.0;//uniformRandomNumber(temp2);
            }
        }
        /*for (int i = 0; i <= Alpha.length - 1; i++)
        {
            Alpha[i] = 1.0;//uniformRandomNumber(temp2);
        }*/
    }

    /**
     * Create New Organism
     * @param lbl
     * @param row
     * @param bs
     */
    public Organism3(Integer lbl, input row, Double[] bs, Double[] as)
    {
        this.Label          = lbl;
        int ilength         = row.getX().length;
        X                   = new Double[ilength];
        Double[] temp = new Double[bs.length];
        for (int i = 0; i <= bs.length - 1; i++) {
            if (Double.isFinite(bs[i]))
            {
                temp[i] = bs[i];
            }
            else
            {
                temp[i] = uniformRandomNumber(2500.00);
            }
        }
        Beta = temp;
        Double[] temp2 = new Double[as.length];
        for (int i = 0; i <= as.length - 1; i++) {
            if (Double.isFinite(as[i]))
            {
                temp2[i] = as[i];
            }
            else
            {
                temp2[i] = uniformRandomPositiveNumber(1.5);
            }
        }
        Alpha = temp2;

        //Beta                = bs;
    }


    public void feed(input row)
    {
        X = row.getX();
        Y = row.getY();
    }

    public boolean live()
    {
        Yhat = 0.0;
        for (int i = 0; i <= Beta.length - 2; i++)
        {
            Yhat = Yhat + Beta[i]*Math.pow(X[i], Alpha[i]);
        }
        Yhat = Yhat + (Beta[Beta.length-1]);
        Double derror = Yhat - Y;
        errorSquared = Math.pow(derror, 2);
        //System.out.println(errorSquared);

        return true;
    }

    public Double getErrorSquared() {
        return errorSquared;
    }

    public void setLabel(Integer label) {
        Label = label;
    }

    public void setX(Double[] x) {
        X = x;
    }

    public void setBeta(Double[] beta) {
        Beta = beta;
    }

    public void setY(Double y) {
        Y = y;
    }

    public void setYhat(Double yhat) {
        Yhat = yhat;
    }

    public Integer getLabel() {
        return Label;
    }

    public Double[] getX() {
        return X;
    }

    public Double[] getBeta() {
        return Beta;
    }

    public Double[] getAlpha() {
        return Alpha;
    }

    public Double getY() {
        return Y;
    }

    public Double getYhat() {
        return Yhat;
    }

    /**
     * @param Range [-Range; Range]
     * @return  pseudorandom number from a Uniform Distribution within the range
     */
    public static Double uniformRandomNumber(Double Range)
    {
        double dmax =  1.7320508071499143;
        Random r = new Random();
        UniformRandomGenerator rnd = new UniformRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        return (cur/dmax) * Range;
    }
    public static Double uniformRandomPositiveNumber(Double Range)
    {
        double dmax =  1.7320508071499143;
        Random r = new Random();
        UniformRandomGenerator rnd = new UniformRandomGenerator(new Well19937c(r.nextInt()));
        double cur = rnd.nextNormalizedDouble();
        if (cur >= 0)
        {
            return (cur/dmax) * Range;
        }
        else {
            return (cur / dmax) * Range *-1.0;
        }
    }
    public void printBeta()
    {
        for (int j = 0; j <= Beta.length - 1; j++)
        {
            System.out.println("Beta" + j + " = " + Beta[j]);
        }
    }
    public void printAlpha()
    {
        for (int j = 0; j <= Alpha.length - 1; j++)
        {
            System.out.println("Alpha" + j + " = " + Alpha[j]);
        }
    }
}

