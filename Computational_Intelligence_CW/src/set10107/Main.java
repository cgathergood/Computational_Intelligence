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
		int runningTime = 0;
		int jobCost;

		Job currentJob = openList.get(0);
		Job nextJob = new Job();
		
		while(openList.isEmpty() == false){
			openList.remove(currentJob);
			closedList.add(currentJob);
			System.out.println("Current job " + currentJob.id);
			// Find closest job to currentJob
			for (Job j : openList){
				jobCost = Problem.getTime(currentJob.setdown, j.pickup);
				if(jobCost < time){
					time = jobCost;
					nextJob = j;
					System.out.println("Lowest cost from " + currentJob.id + " to " + j.id + " is " + jobCost );
				}
				runningTime += Problem.getTime(currentJob.setdown, nextJob.pickup);
				currentJob = nextJob;
			}
			time = 10000;			
		}
		
		for (Job job : closedList){
			System.out.println(job.id);
		}
		
		Job[] sol = new Job[closedList.size()];
		closedList.toArray(sol);

		// Random Shuffle
		Collections.shuffle(Arrays.asList(myJobs));

		// System.out.println("Delivery order ");
		// for (Job j : myJobs) {
		// System.out.println(j.id + ", ");
		// }
		System.out.println();
		
		System.out.println("Random Reward =" + Problem.score(myJobs));
		System.out.println("Reward =" + Problem.score(sol));

	}
}
