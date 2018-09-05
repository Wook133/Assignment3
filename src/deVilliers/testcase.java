package deVilliers;

public class testcase {
    public static void main(String[] args) throws Exception {
        Population3 model2 = new Population3(1000, "Rsquared > 0.99 or Generations > 1500", 0.01, 0.01, 0.5, 2);
        int igen2 = 1;

        while ((model2.population.get(0).rsquared < 0.99) && (igen2 < 1000)) {
            System.out.println("Generation: " + igen2);
            System.out.println("__________________________________________________________________________");

            model2.Evolve();
            //model2.Evolve2();
            System.out.println("__________________________________________________________________________");
            igen2 = igen2 + 1;
        }
        System.out.println("Best after " + igen2 + " generations");
        model2.population.get(0).creature.printBeta();
        model2.population.get(0).creature.printAlpha();
    }
}
