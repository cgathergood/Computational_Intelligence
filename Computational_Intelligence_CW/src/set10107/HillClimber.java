package set10107;

import java.util.Random;

public class HillClimber {

	public static void main(String[] args) {

		Problem.loadProblem("Problem Files/Problem5.txt");
		Job[] myJobs = Problem.getJobs();

		int tries = 0;
		int attempt = 10000;

		int[] chromo = createChromo(myJobs);
		int bestFit = fitness(myJobs, chromo);

		while (tries < attempt) {

			int[] newChromo = chromo;

			newChromo = mutate(newChromo);

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

	// Creates random chromosome
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

	// Mutates the chromosome
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

	// Calculates the fitness of the chromosome
	private static int fitness(Job[] myJobs, int[] chromo) {
		Job[] sol = new Job[myJobs.length];
		for (int i = 0; i < myJobs.length; i++) {
			sol[i] = myJobs[chromo[i]];
		}
		return Problem.score(sol);
	}
}