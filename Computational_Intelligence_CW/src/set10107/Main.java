package set10107;

import java.util.Arrays;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		Problem.loadProblem("Problem Files/Problem5.txt");
		
		Job[] myJobs = Problem.getJobs();
		
		Collections.shuffle(Arrays.asList(myJobs));
		
		System.out.println("Delivery order ");
		for(Job j : myJobs){
			System.out.println(j.id + ", ");
		}
		System.out.println();
		System.out.println("Reward =" +Problem.score(myJobs));

	}

}
