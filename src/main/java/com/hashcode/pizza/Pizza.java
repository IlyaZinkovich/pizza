package com.hashcode.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pizza {

  public static void main(String[] args) throws FileNotFoundException {
    try (Scanner scanner = new Scanner(new File("c_medium.in"))) {
      int max = scanner.nextInt();
      int n = scanner.nextInt();
      System.out.println(solveOpt(max, n, scanner));
    }
  }

  private static String solveOpt(int max, int n, Scanner scanner) {
    int[] f = new int[max + 1];
    String[] orderedPizzas = new String[max + 1];
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      int newPizzaIndex = i - 1;
      int newPizzaSlices = scanner.nextInt();
      sum += newPizzaSlices;
//      System.out.println(Instant.now() + " " + i);
      for (int j = newPizzaSlices; j <= Math.min(sum, max); j++) {
        if (j > sum) {
          f[j] = f[j - 1];
          continue;
        }
        int slicesLeft = j - newPizzaSlices;
        if (newPizzaSlices <= j) {
          if (orderedPizzas[slicesLeft] != null
              && orderedPizzas[slicesLeft].endsWith(Integer.toString(newPizzaIndex))) {
            continue;
          }
          int slicesWithNewPizza = f[slicesLeft] + newPizzaSlices;
          int slicesWithoutNewPizza = f[j];
          if (slicesWithNewPizza > slicesWithoutNewPizza) {
            f[j] = slicesWithNewPizza;
            String pizzas = orderedPizzas[slicesLeft];
            if (pizzas == null || pizzas.isEmpty()) {
              pizzas = Integer.toString(newPizzaIndex);
            } else {
              pizzas += " " + newPizzaIndex;
            }
            orderedPizzas[j] = pizzas;
          } else {
            f[j] = slicesWithoutNewPizza;
          }
        }
//        System.out.println(Arrays.toString(f));
      }
    }
    return orderedPizzas[max];
  }
}
