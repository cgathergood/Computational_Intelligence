package set10107;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EvolutionaryAlgorithm {

	public static void main(String[] args) {

		Problem.loadProblem("Problem Files/Problem1.txt");
		Job[] myJobs = Problem.getJobs();

		int tries = 0;
		int attempt = 1000;

		// Creates a population of chromosones
		int populationSize = 100;
		List<int[]> population = createPopulation(populationSize, myJobs);

		int tournamentSize = 5;

		int[] bestChromo = new int[myJobs.length];

		while (tries < attempt) {

			int[] newChild = selection(population, myJobs, tournamentSize);
			population = replacement(newChild, population, myJobs);

			bestChromo = bestFitness(population, myJobs);

			tries++;
		}

		for (int i : bestChromo) {
			System.out.print(i + ",");
		}
		System.out.println();
		System.out.println("Final Fitness= " + fitness(myJobs, bestChromo));
	}

	private static int[] bestFitness(List<int[]> population, Job[] myJobs) {

		int bestFit = 0;
		int[] bestChromo = null;

		for (int i = 0; i < population.size(); i++) {
			if (fitness(myJobs, population.get(i)) > bestFit) {
				bestFit = fitness(myJobs, population.get(i));
				bestChromo = population.get(i);
			}
		}

		return bestChromo;
	}

	// Creates random chromosone
	public static int[] createChromo(Job[] jobs) {

		int[] chromo = new int[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			chromo[i] = i;
		}

		chromo = shuffle(chromo);

		return chromo;
	}

	// Shuffle method
	public static int[] shuffle(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// between i and n-1
			int r = i + (int) (Math.random() * (n - i));
			int tmp = a[i]; // swap
			a[i] = a[r];
			a[r] = tmp;
		}
		return a;
	}

	// Creates a population of chromosones
	public static List<int[]> createPopulation(int size, Job[] jobs) {

		List<int[]> population = new ArrayList<int[]>();

		for (int i = 0; i < size; i++) {
			int[] chromo = createChromo(jobs);
			population.add(chromo);
		}

		return population;
	}

	// Selection
	private static int[] selection(List<int[]> population, Job[] myJobs,
			int tournamentSize) {

		List<int[]> parentSelection1 = new ArrayList<int[]>();
		List<int[]> parentSelection2 = new ArrayList<int[]>();

		// Create selection of potential parents
		for (int i = 0; i < tournamentSize; i++) {
			int random = (int) (Math.random() * population.size());
			parentSelection1.add(population.get(random));
		}

		for (int i = 0; i < tournamentSize; i++) {
			int random = (int) (Math.random() * population.size());
			parentSelection2.add(population.get(random));
		}

		// Determine best chromo from each parent
		int parentFit1 = 0;
		int parentFit2 = 0;
		int[] parent1 = new int[myJobs.length];
		int[] parent2 = new int[myJobs.length];

		for (int[] chromo : parentSelection1) {
			if (fitness(myJobs, chromo) > parentFit1) {
				parentFit1 = fitness(myJobs, chromo);
				parent1 = chromo;
			}
		}

		for (int[] chromo : parentSelection2) {
			if (fitness(myJobs, chromo) > parentFit2) {
				parentFit2 = fitness(myJobs, chromo);
				parent2 = chromo;
			}
		}

		// Cross Over
		int[] child = crossover(parent1, parent2);

		// Mutate new child
		int[] mutantChild = mutate(child);

		return mutantChild;
	}

	// Single point crossover
	private static int[] crossover(int[] parent1, int[] parent2) {

		ArrayList<Integer> newChild = new ArrayList<Integer>();
		int crossoverPoint = (int) (Math.random() * parent1.length);

		// Copies parent1 up to crossover point
		for (int i = 0; i < parent1.length; i++) {
			if (i < crossoverPoint) {
				newChild.add(parent1[i]);
			}
		}

		// Add remaining values from parent2
		ArrayList<Integer> remaining = new ArrayList<Integer>();

		for (int i : parent2) {
			if (!newChild.contains(i)) {
				remaining.add(i);
			}
		}

		for (int i : remaining) {
			newChild.add(i);
		}

		// Change from arraylist to array
		int[] child = new int[parent1.length];

		for (int i = 0; i < newChild.size(); i++) {
			child[i] = newChild.get(i);
		}

		return child;
	}

	// Adds the newly created child to the exisiting population
	private static List<int[]> replacement(int[] newChild,
			List<int[]> population, Job[] myJobs) {

		// Tournament to decide the worst performing chromosone
		int worstFit = 10000;
		int worstPos = 0;

		for (int i = 0; i < population.size(); i++) {
			if (fitness(myJobs, population.get(i)) < worstFit) {
				worstFit = fitness(myJobs, population.get(i));
				worstPos = i;
			}
		}

		// Check if new child is better than the worst chromo
		if (fitness(myJobs, newChild) > worstFit) {
			population.add(newChild);
			population.remove(worstPos);
		}

		return population;
	}

	// Mutates the chromosone
	public static int[] mutate(int[] chromo) {
		Random randomGenerator = new Random();

		int random1 = randomGenerator.nextInt(chromo.length);
		int random2 = randomGenerator.nextInt(chromo.length);

		int temp1 = chromo[random1];
		int temp2 = chromo[random2];

		chromo[random1] = temp2;
		chromo[random2] = temp1;

		return chromo;
	}

	// Calculates the fitness of the chromosone
	private static int fitness(Job[] myJobs, int[] chromo) {
		Job[] sol = new Job[myJobs.length];
		for (int i = 0; i < myJobs.length; i++) {
			sol[i] = myJobs[chromo[i]];
		}
		return Problem.score(sol);
	}
}