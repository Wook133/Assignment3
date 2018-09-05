package deVilliers;

import java.util.ArrayList;

public class Main3 {
    public static void main(String[] args) throws Exception {
        int icount = 0;
        /*Population3 model3 = new Population3(1000, "Rsquared > 0.99 or Generations > 1500",0.001, 0.001, 0.5, 0);
        int igen2 = 1;

        while ((model3.population.get(0).rsquared < 0.99) && (igen2 < 1500))
        {
            System.out.println("Generation: " + igen2);
            System.out.println("__________________________________________________________________________");

            model3.Evolve();
            //System.out.println("__________evo 2___________");
            //model3.Evolve2();
            System.out.println("__________________________________________________________________________");
            igen2 = igen2 + 1;

        }
        System.out.println("Best after " + igen2 + " generations");
        model3.population.get(0).creature.printBeta();
        model3.population.get(0).creature.printAlpha();**/

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
        selectionOperators.add(5);

        readCSV rcv = new readCSV();

        for (int i = 0; i <= sizes.size() - 1; i++) {
            Population3 model1 = new Population3(sizes.get(i), "Rsquared > 0.98 or Generations == 500", 0.0, 0.0, 0.0, -1);
            int igen = 1;
            while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                System.out.println("Generation: " + igen);
                System.out.println("__________________________________________________________________________");
                model1.Evolve();
                if (igen == 1) {
                    rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                } else if ((igen % 30) == 0) {
                    rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                }

                System.out.println("__________________________________________________________________________");
                igen = igen + 1;
            }
            System.out.println("Best after " + igen + " generations");
            model1.population.get(0).creature.printBeta();
            rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
            System.out.println(model1.toString());
            icount = icount+1;
            System.out.println("Counter =" + icount);
        }
        for (int i = 0; i <= sizes.size() - 1; i++) {
            for (int j = 0; j <= mutationmagnitudes.size() - 1; j++) {
                Population3 model1 = new Population3(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(1), mutationmagnitudes.get(j), crossoverrates.get(1), -1);
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    model1.Evolve();
                    if (igen == 1) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                icount = icount+1;
                System.out.println("Counter =" + icount);
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                System.out.println(model1.toString());
            }
        }
        for (int i = 0; i <= sizes.size() - 1; i++) {
            for (int j = 0; j <= mutationrates.size() - 1; j++) {
                Population3 model1 = new Population3(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(j), mutationmagnitudes.get(1), crossoverrates.get(1), -1);
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    model1.Evolve();
                    if (igen == 1) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                icount = icount+1;
                System.out.println("Counter =" + icount);
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                System.out.println(model1.toString());
            }
        }
        for (int i = 0; i <= sizes.size() - 1; i++) {
            for (int j = 0; j <= crossoverrates.size() - 1; j++) {
                Population3 model1 = new Population3(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(1), mutationmagnitudes.get(1), crossoverrates.get(j), -1);
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    model1.Evolve();
                    if (igen == 1) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                icount = icount+1;
                System.out.println("Counter =" + icount);
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                System.out.println(model1.toString());
            }
        }
        for (int i = 0; i <= sizes.size() - 1; i++) {
            for (int j = 0; j <= selectionOperators.size() - 1; j++) {
                Population3 model1 = new Population3(sizes.get(i), "Rsquared > 0.98 or Generations == 500", mutationrates.get(1), mutationmagnitudes.get(1), crossoverrates.get(1), selectionOperators.get(j));
                int igen = 1;
                while ((model1.population.get(0).rsquared < 0.98) && (igen < 500)) {
                    System.out.println("Generation: " + igen);
                    System.out.println("__________________________________________________________________________");
                    model1.Evolve2();
                    model1.Evolve();
                    if (igen == 1) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    } else if ((igen % 30) == 0) {
                        rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                    }

                    System.out.println("__________________________________________________________________________");
                    igen = igen + 1;
                }
                icount = icount+1;
                System.out.println("Counter =" + icount);
                System.out.println("Best after " + igen + " generations");
                model1.population.get(0).creature.printBeta();
                rcv.writeCsvFile("newmodel3.csv", model1.toPrintString());
                System.out.println(model1.toString());
            }
        }
    }


}
