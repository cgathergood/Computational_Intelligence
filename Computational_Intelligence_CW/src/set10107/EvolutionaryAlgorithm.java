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
		
		tournament(population, myJobs, tournamentSize);

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

	// Tournament selection
	private static int[] tournament(List<int[]> population, Job[] myJobs, int tournamentSize) {
		
		int totalFit = 0;
		int bestFit = 0;
		int currentFit = 0;
		int[] winner = null;
		
		List<int[]> parentSelection1 = new ArrayList<int[]>();
		List<int[]> parentSelection2 = new ArrayList<int[]>();
		
		// Create selection of potential parents
		for(int i=0; i<tournamentSize; i++){
			int random = (int) (Math.random() * population.size());
			parentSelection1.add(population.get(random));
		}
		
		for(int i=0; i<tournamentSize; i++){
			int random = (int) (Math.random() * population.size());
			parentSelection2.add(population.get(random));
		}
		
		// Determine best chromo from each parent
		int parentFit1 = 0;
		int parentFit2 = 0;
		int[] parent1 = null; 
		int[] parent2 = null; 
		
		for(int[] chromo : parentSelection1){
			if(fitness(myJobs, chromo) > parentFit1){
				parentFit1 = fitness(myJobs, chromo);
				parent1 = chromo;
			}
		}
		
		for(int[] chromo : parentSelection2){
			if(fitness(myJobs, chromo) > parentFit2){
				parentFit2 = fitness(myJobs, chromo);
				parent2 = chromo;
			}			
		}
		
		System.out.print("Parent1: ");
		for(int i : parent1){
			System.out.print(i + ", ");
		}
		System.out.println("Fitness: " + parentFit1);
		
		System.out.print("Parent2: ");
		for(int i : parent2){
			System.out.print(i + ", ");
		}
		System.out.println("Fitness: " + parentFit2);
		
		crossover(parent1, parent2);

		return winner;

	}
	
	// Single point crossover
	private static int[] crossover(int[] parent1, int[] parent2) {
		// TODO Auto-generated method stub
		return null;
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