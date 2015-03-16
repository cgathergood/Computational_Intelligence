package set10107;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class EvolutionaryAlgorithm {

	public static void main(String[] args) {

		Problem.loadProblem("Problem Files/Problem1.txt");
		Job[] myJobs = Problem.getJobs();

		int tries = 0;

		int[] chromo = createChromo(myJobs);

		int myFit = fitness(myJobs, chromo);
		
		while(tries < 10000){
			
			int[] newChromo = chromo;
			
			newChromo = mutate(newChromo);
			
			int newFit = fitness(myJobs, newChromo);
			
			if(newFit > myFit){
				chromo = newChromo;
				myFit = newFit;
				
				System.out.println("Improved fitness is now " + myFit);
			}
			
			tries ++;
				
		}

		// Build solution

		// Job[] sol = new Job[myJobs.length];
		//
		// for (int i : chromo) {
		// sol[i] = myJobs[i];
		// }
		//
		// System.out.println("Delivery order ");
		// for (Job j : sol) {
		// System.out.println(j.id + ", ");
		// }
		// System.out.println();
		//
		// System.out.println("Reward =" + Problem.score(sol));

	}

	// Creates basic chromosone
	public static int[] createChromo(Job[] jobs) {

		int[] chromo = new int[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			chromo[i] = i;
		}
		return chromo;

	}

	// Mutates the chromosone
	public static int[] mutate(int[] chromo) {
		Random randomGenerator = new Random();

		int random1 = randomGenerator.nextInt(chromo.length);
		int random2 = randomGenerator.nextInt(chromo.length);

		for (int i : chromo) {
			if (i == random1) {
				chromo[i] = random2;
			}
			if (i == random2) {
				chromo[i] = random1;
			}
		}

		return chromo;

	}

	// Calculates the fitness of the chromosone
	private static int fitness(Job[] myJobs, int[] chromo) {
		Job[] sol = new Job[myJobs.length];
		
		for(int i=0; i < myJobs.length; i++){
			sol[i] = myJobs[chromo[i]];
		}
		
		return Problem.score(sol);
	}
}
