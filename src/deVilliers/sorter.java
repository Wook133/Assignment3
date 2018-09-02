package deVilliers;

import java.util.Comparator;

public class sorter implements Comparator<OrganismsLifeB> {
    @Override
    public int compare(OrganismsLifeB a, OrganismsLifeB b)
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
