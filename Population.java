import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *	Population Class
 * 	@author Sam
 *
 */
public class Population {
	
	public ArrayList<Organism> dish; 
	
	/**
	 * 1 argument constructor
	 * @param counts Map of Strings to Integers representing the types of each Organism and their counts
	 * @throws IllegalArgumentException if the types of Organism are invalid
	 */
	public Population(Map<String, Integer> counts) throws IllegalArgumentException {
		dish = new ArrayList<>();
		Set<String> keys = counts.keySet();
		for(String s : keys) {
			if(s.equals("Cooperator")) {
				int total = counts.get(s);
				for(int i = 0; i < total; i++) {
					dish.add(new Organism.Cooperator());
				}
			}
			else if(s.equals("Defector")) {
				int total = counts.get(s);
				for(int i = 0; i < total; i++) {
					dish.add(new Organism.Defector());
				}
			}
			else if(s.equals("PartialCooperator")) {
				int total = counts.get(s);
				for(int i = 0; i < total; i++) {
					dish.add(new Organism.PartialCooperator());
				}
			}
			else {
				throw new IllegalArgumentException(s + " is not a valid Organism type.");
			}
		}
	}
	
	/**
	 * Updates each Organims in the dish and each takes an action
	 */
	public void update() {
		ArrayList<Organism> born = new ArrayList<>(); 
		for(Organism o : dish) {
			if(o.getEnergy() >= 10) {
				born.add(o.reproduce());
			}
			else if(o.cooperates()) {
				o.decrementEnergy();
				Random r = new Random();
				for(int i = 0; i < 8; i++) {
					dish.get(r.nextInt(dish.size())).incrementEnergy();
				}
			}
			else {
				o.update();
			}
		}
		for(Organism o : born) {
			Random r = new Random();
			dish.set(r.nextInt(dish.size()), o);
		}
	}
	
	/**
	 * @return double value for the mean rate of Cooperation of each Organism in dish
	 */
	public double calculateCooperationMean() {
		double d = 0;
		for(Organism o : dish) {
			d += o.getCooperatorProbability();
		}
		return d/dish.size();
	}
	
	/**
	 * @return Map of Strings to Integers for each type of Organims and their Counts
	 */
	public Map<String, Integer> getPopulationCounts(){
		Map<String, Integer> m = new HashMap<>();
		m.put("Cooperator", 0);
		m.put("Defector", 0);
		m.put("PartialCooperator", 0);
		String temp;
		Integer num;
		for(Organism o: dish) {
			temp = o.getType();
			num = m.get(temp);
			num++;
			m.put(temp, num);
		}
		return m;
	}
	
	
}
