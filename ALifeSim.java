import java.util.HashMap;
import java.util.Map;

/**
 * ALifeSim Class
 * Runs the simulations using Population and Organism
 * @author Sam Larsen
 * Name(s) of all authors
 * 12/5/2022
 * Written/online sources used: none
 * Help obtained: none
 * I confirm that the above list of sources is complete AND that I have
 * not talked to anyone else about the solution to this problem.
 *
 */
public class ALifeSim {
	
	public static void main(String[] args) {
		if(args.length != 4) {
			System.out.println("Accepted arguments format: ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>");
			return;
		}
		else {
			Map<String, Integer> counts = new HashMap<>();
			Integer iterations = 0;
			try {
				iterations = Integer.parseInt(args[0]);
				Integer coops = Integer.parseInt(args[1]);
				Integer defects = Integer.parseInt(args[2]);
				Integer parts = Integer.parseInt(args[3]);
				
				counts.put("Cooperator", coops);
				counts.put("Defector", defects);
				counts.put("PartialCooperator", parts);
				
			} catch(NumberFormatException e) {
				System.err.println("Please enter integer values for arguments");
				return;
			}
			
			Population pop = new Population(counts);
			
			for(int i = 0; i < iterations; i++) {
				pop.update();
			}
			counts = pop.getPopulationCounts();
			System.out.println("After "+ iterations + " ticks:");
			System.out.println("Cooperators = "+ counts.get("Cooperator"));
			System.out.println("Defectors = "+ counts.get("Defector"));
			System.out.println("Partial = "+ counts.get("PartialCooperator"));
			System.out.println("\nMean Cooperation Probability = " + pop.calculateCooperationMean());
			
		}
	}
	
}
