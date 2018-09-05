package deVilliers;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws Exception {
        /*PopulationB model2 = new PopulationB(100,"Rsquared > 0.99 or Generations > 1500",0.001, 0.001, 0.5, 0);
        int igen2 = 1;

       while ((model2.population.get(0).rsquared < 0.99) && (igen2 < 1500))
        {
            System.out.println("Generation: " + igen2);
            System.out.println("__________________________________________________________________________");
            model2.Evolve2();
            //model2.Evolve();
            System.out.println("__________________________________________________________________________");
            igen2 = igen2 + 1;
        }
        System.out.println("Best after " + igen2 + " generations");
        model2.population.get(0).creature.printBeta();*/
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes.add(20);
        sizes.add(50);
        sizes.add(100);
        sizes.add(500);
        sizes.add(1000);
        sizes.add(2000);
        sizes.add(5000);
        sizes.add(10000);

        ArrayList<Double> mutationmagnitudes = new ArrayList<>();
        mutationmagnitudes.add(0.0001);
        mutationmagnitudes.add(0.0005);
        mutationmagnitudes.add(0.001);
        mutationmagnitudes.add(0.005);
        mutationmagnitudes.add(0.01);
        mutationmagnitudes.add(0.05);
        mutationmagnitudes.add(0.1);
        mutationmagnitudes.add(0.5);

        ArrayList<Double> mutationrates = new ArrayList<>();
        mutationrates.add(0.0001);
        mutationrates.add(0.0005);
        mutationrates.add(0.001);
        mutationrates.add(0.005);
        mutationrates.add(0.01);
        mutationrates.add(0.05);
        mutationrates.add(0.1);
        mutationrates.add(0.5);

        ArrayList<Double> crossoverrates = new ArrayList<>();
        crossoverrates.add(0.01);
        crossoverrates.add(0.05);
        crossoverrates.add(0.1);
        crossoverrates.add(0.20);
        crossoverrates.add(0.50);
        crossoverrates.add(0.80);
        crossoverrates.add(0.90);

        ArrayList<Integer> selectionOperators = new ArrayList<>();
        selectionOperators.add(0);
        selectionOperators.add(1);
        selectionOperators.add(2);
        selectionOperators.add(3);
        selectionOperators.add(4);
        selectionOperators.add(5);

        readCSV rcv = new readCSV();

        for (int i = 0; i <= sizes.size()-1; i++) {
            for (int j =0; j <= mutationmagnitudes.size() - 1; j++) {
                for (int k = 0; k <= mutationrates.size() - 1; k++) {
                    for (int m = 0; m <= crossoverrates.size() - 1; m++) {
                        for (int n = 0; n <= selectionOperators.size() - 1; n++) {
                            PopulationB model2 = new PopulationB(sizes.get(i), "Rsquared > 0.98 or Generations == 1000", mutationrates.get(k), mutationmagnitudes.get(j), crossoverrates.get(m), selectionOperators.get(n));
                            int igen = 1;
                            while ((model2.population.get(0).rsquared < 0.98) && (igen < 1000)) {
                                System.out.println("Generation: " + igen);
                                System.out.println("__________________________________________________________________________");
                                model2.Evolve2();
                                if (igen == 1) {
                                    rcv.writeCsvFile("model2.csv", model2.toPrintString());
                                } else if ((igen % 10) == 0) {
                                    rcv.writeCsvFile("model2.csv", model2.toPrintString());
                                }

                                System.out.println("__________________________________________________________________________");
                                igen = igen + 1;
                            }
                            System.out.println("Best after " + igen + " generations");
                            model2.population.get(0).creature.printBeta();
                            rcv.writeCsvFile("model1.csv", model2.toPrintString());
                            System.out.println(model2.toString());
                        }
                    }
                }
            }
        }
    }

}
