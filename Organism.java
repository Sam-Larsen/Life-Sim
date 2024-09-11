import java.util.Random;

/**
 * Organism Class
 * @author Sam Larsen
 *
 */

public abstract class Organism {
	
	private int energy;
	
	/**
	 * 0 argument constructor
	 */
	public Organism() {
		energy = 0;
	}
	
	/**
	 * by default increments own energy
	 */
	public void update() {
		incrementEnergy();
	}
	
	/**
	 * 
	 * @return energy
	 */
	public int getEnergy() {
		return energy;
	}
	
	/**
	 * Increases energy by one
	 */
	public void incrementEnergy() {
		energy++;
	}
	
	/**
	 * decreases energy by one
	 */
	public void decrementEnergy() {
		if(energy == 0) {
			return;
		}
		energy--;
	}
	
	/**
	 * @return The type of Organism
	 */
	public abstract String getType();
	
	/**
	 * @return a new Organism to be added to population
	 */
	public abstract Organism reproduce();
	
	/**
	 * @return the double value of the Organisms chance to cooperate
	 */
	public abstract double getCooperatorProbability();
	
	/**
	 * @return returns true if the Orginism cooperates
	 */
	public abstract boolean cooperates();
	
	
	public static class Cooperator extends Organism{
	
		/**
		 * @return the String "Cooperator" since that is the type
		 */
		public String getType() {
			return "Cooperator";
		}
		
		/**
		 * @return new Cooperator object
		 */
		public Cooperator reproduce() {
			Cooperator c = new Cooperator();
			return c;
		}
		
		/**
		 * @return 1 since the Cooperator always cooperates
		 */
		public double getCooperatorProbability() {
			return 1;
		}
		
		/**
		 * @return true since the Cooperator always cooperates
		 */
		public boolean cooperates() {
			return true;
		}
	}
	
	public static class Defector extends Organism{
		
		/**
		 * @return the String "Defector" since that is the type
		 */
		public String getType() {
			return "Defector";
		}
		
		/**
		 * @return new Defector Object
		 */
		public Defector reproduce() {
			Defector d = new Defector();
			return d;
		}
		
		/**
		 * @return 0 since a Defector never cooperates
		 */
		public double getCooperatorProbability() {
			return 0;
		}
		
		/**
		 * @return false since a Defector never cooperates
		 */
		public boolean cooperates() {
			return false;
		}
	}

	public static class PartialCooperator extends Organism{
		
		/**
		 * @return the String "PartialCooperator" since that is the type
		 */
		public String getType() {
			return "PartialCooperator";
		}
		
		/**
		 * @return a new PartialCooperator Object
		 */
		public PartialCooperator reproduce() {
			PartialCooperator d = new PartialCooperator();
			return d;
		}
		
		/**
		 * @return 0.5 since there is always a 50% chance a PartialCooperator will cooperate
		 */
		public double getCooperatorProbability() {
			return 0.5;
		}
		
		/**
		 * @return boolean true if it cooperates, false if not
		 */
		public boolean cooperates() {
			Random r = new Random();
			if(r.nextDouble() > getCooperatorProbability()) {
				return true;
			}
			return false;
			
		}
	}
	
}
