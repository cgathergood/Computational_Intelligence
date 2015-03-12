//**********************************************************
// SET10107 Computational Intelligence Coursework
//
// Author: 40056161
//
// Date: 2015
//*********************************************************

package set10107;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		Problem.loadProblem("Problem Files/Problem1.txt"); // Load Problem File

		Job[] myJobs = Problem.getJobs();

		// Intialise Open and Closed Lists
		ArrayList<Job> openList = new ArrayList<Job>();
		ArrayList<Job> closedList = new ArrayList<Job>();

		// Populate openList
		for (Job j : myJobs) {
			openList.add(j);
		}

		int time = 10000;
		int jobCost;
		Job nextJob = null;

		for (Job current : myJobs) {
			for (Job j : myJobs) {
				jobCost = Problem.getTime(current.setdown, j.pickup);
				System.out.println("The cost from " + current.id + " to " + j.id + " is " + jobCost);
			}
			System.out.println();
		}

		// Random Shuffle
		Collections.shuffle(Arrays.asList(myJobs));

		// System.out.println("Delivery order ");
		// for (Job j : myJobs) {
		// System.out.println(j.id + ", ");
		// }
		System.out.println();

		System.out.println("Random Reward =" + Problem.score(myJobs));

	}
}
