package deVilliers;

public class Main2 {
    public static void main(String[] args) throws Exception {
        PopulationB model2 = new PopulationB(100);
        int igen2 = 1;

       while ((model2.population.get(0).rsquared < 0.99) && (igen2 < 1500))
        {
            System.out.println("Generation: " + igen2);
            System.out.println("__________________________________________________________________________");
            model2.Evolve();
            System.out.println("__________________________________________________________________________");
            igen2 = igen2 + 1;
        }
        System.out.println("Best after " + igen2 + " generations");
        model2.population.get(0).creature.printBeta();
    }

}
