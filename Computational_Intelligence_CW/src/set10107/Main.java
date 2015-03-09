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
		Problem.loadProblem("Problem Files/Problem1.txt"); //Load Problem File
		
		Job[] myJobs = Problem.getJobs();
		
		//Pint out Job Data
/*		for(Job j : myJobs){
			System.out.println("ID: " + j.id + ", Pickup:" + j.pickup + ", Setdown:" + j.setdown + ", Available:" + j.available);
			
			for(int[] i : j.payments){
				System.out.println("Payments: " +i[0] + "," +i[1]);
			}
		}*/
		
		// A Star
		
		int time;
		int shortest = 100;
		
		ArrayList<Job> openList = new ArrayList<Job>();
		ArrayList<Job> closedList = new ArrayList<Job>();
		
		// Populate openList
		for(Job j : myJobs){
			openList.add(j);
		}
		
		Job currentJob;
		
		while(openList.isEmpty() == false){
			currentJob = openList.get(0);
			if(currentJob == myJobs[0]){
				return;
			} else {
				closedList.add(currentJob);
				openList.remove(currentJob);
			}
			System.out.println(openList.size());
		}
		
/*		for(Job j : myJobs){
			for(Job neighbour : myJobs){
				time = Problem.getTime(j.setdown, neighbour.setdown);
				//System.out.println("From "+ j.id + " to " + neighbour.id + " = " + time);
				if(time < shortest && time != 0){
					shortest = time;
				}
			}
			System.out.println("Shortest time: " + shortest);
			shortest = 100;
		}
		System.out.println("Reward =" +Problem.score(myJobs));*/
		
		// Random Shuffle
		//Collections.shuffle(Arrays.asList(myJobs));
		
		System.out.println("Delivery order ");
		for(Job j : myJobs){
			System.out.println(j.id + ", ");
		}
		System.out.println();
		System.out.println("Reward =" +Problem.score(myJobs));

	}

}
