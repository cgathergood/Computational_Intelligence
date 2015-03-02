//**********************************************************
// SET10107 Computational Intelligence Coursework
//
// Author: 40056161
//
// Date: 2015
//*********************************************************

package set10107;

import java.util.Arrays;
import java.util.Collections;

public class Main {

	public static void main(String[] args) { 
		Problem.loadProblem("Problem Files/Problem1.txt"); //Load Problem File
		
		Job[] myJobs = Problem.getJobs();
		
		//Pint out Job Data
		for(Job j : myJobs){
			System.out.println("ID: " + j.id + ", Pickup:" + j.pickup + ", Setdown:" + j.setdown + ", Available:" + j.available);
		}
		
		
		Collections.shuffle(Arrays.asList(myJobs));
		
		System.out.println("Delivery order ");
		for(Job j : myJobs){
			System.out.println(j.id + ", ");
		}
		System.out.println();
		System.out.println("Reward =" +Problem.score(myJobs));

	}

}
