package deVilliers;

public class Main3 {
    public static void main(String[] args) throws Exception {
        Population3 model3 = new Population3(10000);
        int igen2 = 1;

        while ((model3.population.get(0).rsquared < 0.99) && (igen2 < 1500))
        {
            System.out.println("Generation: " + igen2);
            System.out.println("__________________________________________________________________________");
            model3.Evolve();
            System.out.println("__________________________________________________________________________");
            igen2 = igen2 + 1;
        }
        System.out.println("Best after " + igen2 + " generations");
        model3.population.get(0).creature.printBeta();
        model3.population.get(0).creature.printAlpha();
    }

}
