import java.io.*;

// Defines an object which represents "Movement" from one location to another, including the conditions under which 
// Movement can occur (or be inhibited)
// All methods are final and not designed for inheritance

public class Movement {

	private int index;  // the index of the location (>=0) TO which movement will occur
	private float time; // how much time will elapse in making this move, in days
	private float energy; // how much energy will be used in this move, 0 - 100
	private boolean requiresspell; // is a spell required to move to next location?
	private boolean requiresboat; // is a boat required?
	private boolean requiresrope; // is rope required?
	private boolean requiresring; // is the ring required?
	private boolean viapassage; // does movement occur "via" a passage, such as a door, passage, window, etc.
	private boolean ridable; // can be traversed on horseback?
	private boolean arduous; // does passage require sufficient strength? 
	private boolean obstacle; // does passage have obstacle?
	private boolean crossable; // is passage crossable?
	private int passageindex; // if via passage, provide index to this unique passage to determine open/closed/locked, etc.
	
	public Movement(){
		index = -1;
		time = 0;
		energy = 0;
		requiresspell = false;
		requiresboat = false;
		requiresrope = false;
		requiresring = false;
		ridable = false;
		arduous = false;
		viapassage = false;
		obstacle = false;
		crossable = false;
		passageindex = -1;
	}

	// Constructor - movement objects are instantiated during parsing of location during
	// initialization.  

	public Movement(int newindex, float newtime, float newenergy,
			boolean newspell, boolean newboat, 
			boolean newrope, boolean newring,
			boolean newviapassage, boolean newridable, boolean newarduous,
			boolean newobstacle, boolean newcrossable, int newpassageindex){

		index = newindex;
		time = newtime;
		energy = newenergy;
		requiresspell = newspell;
		requiresboat = newboat;
		requiresrope = newrope;
		requiresring = newring;
		viapassage = newviapassage;
		ridable = newridable;
		arduous = newarduous;
		obstacle = newobstacle;
		crossable = newcrossable;
		passageindex = newpassageindex;
	}

	// auxiliary "setter" which augments constructor to instantiate new object

	public final void setMovement(int newindex, float newtime, float newenergy,
			boolean newspell, boolean newboat, 
			boolean newrope, boolean newring,
			boolean newviapassage, boolean newridable, boolean newarduous,
			boolean newobstacle, boolean newcrossable, int newpassageindex){

		this.index = newindex;
		this.time = newtime;
		this.energy = newenergy;
		this.requiresspell = newspell;
		this.requiresboat = newboat;
		this.requiresrope = newrope;
		this.requiresring = newring;
		this.viapassage = newviapassage;
		this.ridable = newridable;
		this.arduous = newarduous;
		this.obstacle = newobstacle;
		this.crossable = newcrossable;
		this.passageindex = newpassageindex;
	}
		
	public final int getIndex(){
		return index;
	}

	public final float getTime(){
		return time;
	}

	public final float getEnergy(){
		return energy;
	}
	
	public final boolean getRequiresSpell(){
		return requiresspell;
	}
	
	public final boolean getRequiresBoat(){
		return requiresboat;
	}

	public final boolean getRequiresRing(){
		return requiresring;
	}

	public final boolean getRequiresRope(){
		return requiresrope;
	}

	public final boolean isArduousTask() {
		return arduous;
	}

	public final boolean getViaPassage(){
		return viapassage;
	}

	public final boolean isRidable(){
		return ridable;
	}

	public final boolean hasObstacle(){
        return obstacle;
    }

    public final boolean isCrossable(){
        return crossable;
    }
    
	public final int getPassageIndex(){
		return passageindex;
	}

	@Override public String toString(){
		String s = "Index= " + index + " Time= " + time +
			   " Energy= " + energy + " RequiresSpell= " + requiresspell +
			   " RequiresBoat= " + requiresboat + " RequiresRing= " +
			   requiresring + " RequiresRope= " + requiresrope +
			   " Via Passage= " + viapassage + " Passage Index= " + passageindex;		
		return s;
	}
	
} /* end Movement */
	
