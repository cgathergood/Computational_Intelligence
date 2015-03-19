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
		
		tournament(population, myJobs);
		
		int[] chromo = createChromo(myJobs);
		int bestFit = fitness(myJobs, chromo);

		while (tries < attempt) {

			int[] newChromo = chromo;

			newChromo = mutate(newChromo);

			// newChromo = crossover(newChromo);

			int newFit = fitness(myJobs, newChromo);

			if (newFit > bestFit) {
				chromo = newChromo;
				bestFit = newFit;

				System.out.println("Improved fitness is now \t " + bestFit
						+ "\t on try \t " + tries);
			}
			tries++;
		}

		for (int i : chromo) {
			System.out.print(i + ",");
		}
		System.out.println();
		System.out.println("Final Fitness= " + bestFit);
	}
	
	// Tournament selects best performing chromosone from the population
	private static int[] tournament(List<int[]> population, Job[] myJobs) {
		
		int bestFit =0;
		int currentFit = 0;
		int[] winner = null;
		
		for(int[] chromo : population){
			currentFit = fitness(myJobs, chromo);
			
			if(currentFit > bestFit){
				bestFit = currentFit;
				winner = chromo;
			}
		}
		System.out.println("The best fitness is " + bestFit);
		
		for(int i : winner){
			System.out.print(i);
		}
		System.out.println();
		return winner;
		
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
		System.out.println("Population Created!");
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

	// Single point crossover
	private static int[] crossover(int[] newChromo) {
		// TODO Auto-generated method stub
		return null;
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