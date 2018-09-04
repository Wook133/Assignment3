package deVilliers;

public class Experiment {
    String stoppingCondition;
    String intializedValues;
    String datasetSize;
    String populationSize;
    String mutationRate;
    String selectionMethod;
    String crossoverRate;
    String rSquared;
    String Time;
    String Generation;
    String BetaParameters;
    String model;

    public Experiment(String model, String stoppingCondition, String intializedValues, String datasetSize, String populationSize, String mutationRate, String selectionMethod, String crossoverRate, String rSquared, String time, String iterations, String betaParameters) {
        this.stoppingCondition = stoppingCondition;
        this.intializedValues = intializedValues;
        this.datasetSize = datasetSize;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.selectionMethod = selectionMethod;
        this.crossoverRate = crossoverRate;
        this.rSquared = rSquared;
        Time = time;
        Generation = iterations;
        BetaParameters = betaParameters;
        this.model=model;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "model='" + model + '\'' +
                "stoppingCondition='" + stoppingCondition + '\'' +
                ", intializedValues='" + intializedValues + '\'' +
                ", datasetSize='" + datasetSize + '\'' +
                ", populationSize='" + populationSize + '\'' +
                ", mutationRate='" + mutationRate + '\'' +
                ", selectionMethod='" + selectionMethod + '\'' +
                ", crossoverRate='" + crossoverRate + '\'' +
                ", rSquared='" + rSquared + '\'' +
                ", Time='" + Time + '\'' +
                ", Generation='" + Generation + '\'' +
                ", BetaParameters='" + BetaParameters + '\'' +
                '}';
    }


    public String print()
    {
        String sOut = model + ", " + stoppingCondition + ", " + intializedValues + ", " + datasetSize + ", " + populationSize + ", " + mutationRate + ", " + selectionMethod + ", " + crossoverRate + ", " + rSquared + ", " + Time + ", " + Generation + ", " + BetaParameters ;
        return sOut;
    }
}
