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
		ArrayList<Job> sol = new ArrayList<Job>();

		// Populate openList
		for (Job j : myJobs) {
			openList.add(j);
		}

		int time = 100;

		for (Job currentJob : myJobs) {
			closedList.add(currentJob);
			//openList.remove(currentJob);
			//while (openList.isEmpty() == false) {

				Job nextJob = new Job();
				// Order open list
				for (Job j : openList) {
					if (Problem.getTime(currentJob.setdown, j.pickup) < time) {
						time = Problem.getTime(currentJob.setdown, j.pickup);
						nextJob = j;
					}
				}
				sol.add(nextJob);
				openList.remove(nextJob);
				time = 100;
//			//}
//			System.out.println("Openlist size: " + openList.size());
//			System.out.println("ClosedList size: " + closedList.size());
//			System.out.println("Solutionlist size: " + sol.size());
		}

		// Random Shuffle
		Collections.shuffle(Arrays.asList(myJobs));

		System.out.println("Delivery order ");
		for (Job j : sol) {
			System.out.println(j.id + ", ");
		}
		System.out.println();
		Job[] results = new Job[sol.size()];
		sol.toArray(results);
		
		System.out.println("Reward =" + Problem.score(results));
		

	}
}
