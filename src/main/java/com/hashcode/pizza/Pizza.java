package com.hashcode.pizza;

import static java.lang.Math.min;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pizza {

  public static void main(String[] args) throws FileNotFoundException {
    try (Scanner scanner = new Scanner(new File("c_medium.in"));
        PrintWriter writer = new PrintWriter(new File("output.txt"))) {
      int max = scanner.nextInt();
      int n = scanner.nextInt();
      solve(max, n, scanner, writer);
    }
  }

  private static void solve(int max, int n, Scanner scanner, PrintWriter writer) {
    int[] f = new int[max + 1];
    String[] orderedPizzas = new String[max + 1];
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      int newPizzaIndex = i - 1;
      int newPizzaSlices = scanner.nextInt();
      sum += newPizzaSlices;
      for (int j = min(sum, max); j >= newPizzaSlices; j--) {
        int slicesLeft = j - newPizzaSlices;
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
    }
    writer.println(f[max]);
    writer.println(orderedPizzas[max]);
  }
}
