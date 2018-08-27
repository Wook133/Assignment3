package deVilliers;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class input
{
    private Double[] X ;//= new Double[7];
    private Double Y;

    public input(Double x1, Double x2, Double x3, Double x4, Double x5, Double x6, Double x7, Double y) {
        X[0] = x1;
        X[1] = x2;
        X[2] = x3;
        X[3] = x4;
        X[4] = x5;
        X[5] = x6;
        X[6] = x7;
        Y = y;
    }

    public input(String sInput)
    {
        String[] sData = sInput.split(",");
        X = new Double[sData.length-1];
       // System.out.println(sData.length);
        for (int i = 0; i < sData.length; i++)
        {
            if (StringUtils.isNumeric(sData[i]))
            {
                if (i == 0)
                {
                    Y = new Double(sData[i]);
                }
                else
                {
                    X[i-1] = new Double(sData[i]);
                }
            }
        }
    }

    public Double[] getX() {
        return X;
    }

    public Double getY() {
        return Y;
    }

    @Override
    public String toString() {
        return "input{" +
                "X=" + (X == null ? null : Arrays.asList(X)) +
                ", Y=" + Y +
                '}';
    }
}
