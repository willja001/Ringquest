import java.io.*;
import java.util.*;


// Class that defines location object and all operations that are location-centric
// All methods are final and not designed for inheritance

public class Location {
	
	private int index; // each location has a unique index >=0.  Indicies less than zero are special use.
	private int spellindex; // determines the index of the unique spell that is effective at this location
	private float temperature; // temperature degrees celsius of this location
	private boolean largearea; // true if large area, false if small area.  Large area are like fields, forests, etc.  Small areas are rooms, etc.
	private String description; // general description of area
	private String looknorth; // what player sees when looking north
	private String looksouth; 
	private String lookeast;
	private String lookwest;
	private String lookup;
	private String lookdown;
	private Movement northptr; // movement object to the north
	private Movement northeastptr; 
	private Movement eastptr;
	private Movement southeastptr;
	private Movement southptr;
	private Movement southwestptr;
	private Movement westptr;
	private Movement northwestptr;
	private Movement upptr;
	private Movement downptr;
	private Movement outptr;
	private Movement inptr;
	private List<ImmovableType> immovable; // List of al Immovable objects at this location 
	private List<FiniteType> finite; // all finite objects at this location
	private List<MovableType> movable; // all movable objects at this location
	private List<AnimateType> animate; // all animate objects at this location
	private boolean firstvisit; // true only on first visit, otherwise false
	private final int WININDEX = 607; /* the index of the cracks of doom  - change to actual game winning location */
	private float nazgprobability; // a probability assigned to the location that a nazgul will appear if the ring is put on
	private int talkindex; // a pointer to what an animate object will say if spoken to 		
	
	private final float NAZGUL_ATTRACT_PROBABILITY = 1;	

	public Location(){
	
	index = 0;
	spellindex = -1;  // -1 is default indicating no spell is effective
	temperature = 15;
	largearea = false;
	description = "";
	looknorth = "";
	looksouth = "";
	lookeast = "";
	lookwest = "";
	lookup = "";
	lookdown = "";
	northptr = new Movement();
	northeastptr = new Movement();
	eastptr = new Movement();
	southeastptr = new Movement();
	southptr = new Movement();
	southwestptr = new Movement();
	westptr = new Movement();
	northwestptr = new Movement();
	upptr = new Movement();
	downptr = new Movement();
	outptr = new Movement();
	inptr = new Movement();
	immovable = new ArrayList<ImmovableType>();
	finite = new ArrayList<FiniteType>();
	movable = new ArrayList<MovableType>();
	animate = new ArrayList<AnimateType>();
	firstvisit = true;
	nazgprobability = 0;
	talkindex = 2;

	}

	public final String getLookNorth(){
		return looknorth;
	}

	public final String getLookSouth(){
		return looksouth;
	}

	public final String getLookEast(){
		return lookeast;
	}

	public final String getLookWest(){
		return lookwest;
	}

	public final String getLookUp(){
		return lookup;
	}

	public final String getLookDown(){
		return lookdown;
	}

	public final String getDescription(){
		return description;
	}

	public final Movement getNorthPtr(){
		return northptr;
	}
	
	public final Movement getNorthEastPtr(){
		return northeastptr;
	}

	public final Movement getEastPtr(){
		return eastptr;
	}
	
	public final Movement getSouthEastPtr(){
		return southeastptr;
	}

	public final Movement getSouthPtr(){
		return southptr;
	}

	public final Movement getSouthWestPtr(){
		return southwestptr;
	}

	public final Movement getWestPtr(){
		return westptr;
	}

	public final Movement getNorthWestPtr(){
		return northwestptr;
	}
	
	public final Movement getUpPtr(){
		return upptr;
	}

	public final Movement getDownPtr(){
		return downptr;
	}

	public final Movement getInPtr(){
		return inptr;
	}

	public final Movement getOutPtr(){
		return outptr;
	}

	// converts "true" to boolean true and "false" to boolean false
	public boolean parseBool(String s){
		return (s.matches("true")) ? true : false;
	}

	// Reads the text file and parses the location list during initialization.  The 
	// order of the text file must exactly match the order of the parser, otherwise 
	// will throw IOException

	public void parseLocation(String s){

		int endindex = 0;

		s = s.substring(s.indexOf("+")+1);

		//System.out.println("First s for parseLocation is: ");
		//System.out.println(s);
	
		s = s.trim();
		String t = s.substring(0,s.indexOf("/"));
		int newindex1 = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		int newspellindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		float newtemperature = Float.parseFloat(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed temperature= " + newtemperature);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newarea = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed area= " + newarea);
				
		this.setLocIndex(newindex1, newspellindex, newtemperature, newarea);
		//System.out.println("Set Loc Index"); 

		s = s.trim();
		String newdescription = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed description= " + newdescription);
		
		s = s.trim();
		String newlooknorth = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed looknorth= " + newlooknorth);
		

		s = s.trim();
		String newlooksouth = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed looksouth= " + newlooksouth);
		

		s = s.trim();
		String newlookeast = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed lookeast " + newlookeast);
		

		s = s.trim();
		String newlookwest = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed lookwest= " + newlookwest);
		

		s = s.trim();
		String newlookup = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed lookup= " + newlookup);
				
		s = s.trim();
		String newlookdown = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed lookdown= " + newlookdown);
			
		
		this.setDescription(newdescription, newlooknorth, newlooksouth,
				    newlookeast, newlookwest, newlookup, newlookdown);

		//System.out.println("Set Description");
		
		//System.out.println("s = " + s);
		
		s = s.trim();

		while (!s.substring(0,s.indexOf("/")).matches("ZZ")){

		String newdir = s.substring(0,s.indexOf("/"));

				//System.out.println("newdir = " + newdir);
                
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed newdir= " + newdir);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		int newindex2 = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex2);
				
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		float newtime = Float.parseFloat(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed time= " + newtime);
				
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		float newenergy = Float.parseFloat(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed energy= " + newenergy);
		
	
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newspell = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed spell= " + newspell);
		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newboat = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed boat= " + newboat);
		

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newrope = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed rope= " + newrope);
			
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newring = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed ring= " + newring);
		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newviapassage = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("via passage= " + newviapassage);

        s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newridable = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("reserve1= " + newridable);

        s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newarduous = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("reserve2= " + newarduous);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newobstacle = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("obstacle= " + obstacle);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newcrossable = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("crossable= " + newcrossable);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newreserve3 = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("reserve3= " + newreserve3);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		boolean newreserve4 = parseBool(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("reserve4= " + newreserve4);
		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		int newpassageindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("passage index= " + newpassageindex);

		this.setMovementVector(newdir, newindex2, newtime, newenergy, newspell,
			         newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);
		//System.out.println("Set Movement Vector Complete");
		
		
		} /* end while */

		//System.out.println("s after while completes=" + s);
		s = s.substring(s.indexOf("/")+1); // clear out the ZZ
		
		s = s.trim();

		while (!s.substring(0,s.indexOf("/")).matches("ZZ")){

		t = s.substring(0,s.indexOf("/"));
		int newimmovableindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		
		ImmovableType obj1 = new ImmovableType(newimmovableindex, t);
		this.addImmovable(obj1);
		
		}

		s = s.substring(s.indexOf("/")+1); // clear out the ZZ



		s = s.trim();

		while (!s.substring(0,s.indexOf("/")).matches("ZZ")){

		t = s.substring(0,s.indexOf("/"));
		int newfiniteindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		
		FiniteType obj2 = new FiniteType(newfiniteindex, t);
		this.addFinite(obj2);

		}

         	s = s.substring(s.indexOf("/")+1); // clear out the ZZ



		s = s.trim();

		while (!s.substring(0,s.indexOf("/")).matches("ZZ")){

		t = s.substring(0,s.indexOf("/"));
		int newmovableindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		
		MovableType obj3 = new MovableType(newmovableindex, t);
		this.addMovable(obj3);

		}

         	s = s.substring(s.indexOf("/")+1); // clear out the ZZ


		s = s.trim();

		while (!s.substring(0,s.indexOf("/")).matches("ZZ")){

		t = s.substring(0,s.indexOf("/"));
		int newanimateindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		float newprobability = Float.parseFloat(t);
		s = s.substring(s.indexOf("/")+1);

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		s = s.substring(s.indexOf("/")+1);
		
		AnimateType obj4 = new AnimateType(newanimateindex, t);
		obj4.setProbability(newprobability);
		this.addAnimate(obj4);
		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		int newtalkindex = Integer.parseInt(t);
		this.setTalkIndex(newtalkindex);
		s = s.substring(s.indexOf("/")+1);

		}

         	s = s.substring(s.indexOf("/")+1); // clear out the ZZ

		
	}

	// Basic constructor

	public final void setLocIndex(int loc, int newspellindx, float newtemperature, boolean area){
		this.index = loc;
		this.spellindex = newspellindx;
		this.temperature = newtemperature;
		this.largearea = area;
	}

	// "Setter" which is an auxiliary constructor

	public final void setDescription(String newdescription, String newlooknorth,
				   String newlooksouth, String newlookeast,
				   String newlookwest, String newlookup,
				   String newlookdown) {

		this.description = newdescription;
		this.looknorth = newlooknorth;
		this.looksouth = newlooksouth;
		this.lookeast = newlookeast;
		this.lookwest= newlookwest;
		this.lookup = newlookup;
		this.lookdown = newlookdown;
	}

	public final void setTalkIndex(int newtalkindex){
		talkindex = newtalkindex;
	}

	// "Setter" which is an auxiliary constructor

	public final void setMovementVector(String dir, int newindex, float newtime, float newenergy,
				boolean newspell, boolean newboat, 
				boolean newrope, boolean newring,
				boolean newviapassage, boolean newridable, boolean newarduous,
				boolean newobstacle, boolean newcrossable, int newpassageindex) {

		switch (dir) {
            		case "NN":  northptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);
                     	break; 
            		case "NE":  northeastptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex); 
                     	break;
            		case "EE":  eastptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);  
                     	break;
            		case "SE":  southeastptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);  
                     	break;
            		case "SS":  southptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex); 
                     	break;
            		case "SW":  southwestptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring,  newviapassage, newridable, newarduous,newobstacle, newcrossable, newpassageindex); 
                     	break;
            		case "WW":  westptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex); 
                     	break;
            		case "NW":  northwestptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);  
                     	break;
            		case "UP":  upptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);  
                     	break;
            		case "DN":  downptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex); 
                     	break;
            		case "IN":  inptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);
                     	break;
            		case "OO":  outptr.setMovement(newindex, newtime, newenergy,
							newspell, newboat, newrope, newring, newviapassage, newridable, newarduous, newobstacle, newcrossable, newpassageindex);
                     	break;
            		default: 
                     	break;
          		}		
	}

	public final void addImmovable(ImmovableType obj){
		this.immovable.add(obj);
	}

	// Given the name of a movable object, returns the movable object containing
	// all of the fields.  Can return null - client's responsibility to check.

	public final MovableType returnMovableObject(String s){

		MovableType returnobj = null;
		for (int i = 0; i<this.movable.size(); i++)
			if (this.movable.get(i).getName().matches(s))
					returnobj = this.movable.get(i);			
		return returnobj;
	}

	// Given the name of an animate object, returns the animate object containing
	// all of the fields.  Can return null - client's responsibility to check.

	public final AnimateType returnAnimateObject(String s){

		AnimateType returnobj = null;
		for (int i = 0; i<this.animate.size(); i++)
			if (this.animate.get(i).getName().matches(s))
					returnobj = this.animate.get(i);			
		return returnobj;
	}

	// Returns true if this location has the movable object represented by the name,
	// otherwise returns false

	public final boolean hasMovable(String s){
		boolean result = false;
		for (int i = 0; i<this.movable.size(); i++)
			if (this.movable.get(i).getName().matches(s))
				result = true;
		return result;
	}

	// Returns true if this location has the immovable object represented by the name,
	// otherwise returns false

	public final boolean hasImmovable(String s){
		boolean result = false;
		for (int i = 0; i<this.immovable.size(); i++)
			if (this.immovable.get(i).getName().matches(s))
				result = true;
		return result;
	}

	// Returns true if this location has the animate object represented by the name,
	// otherwise returns false

	public final boolean hasAnimate(String s){
		boolean result = false;
		for (int i = 0; i<this.animate.size(); i++)
			if (this.animate.get(i).getName().matches(s))
				result = true;
		return result;
	}

	// Returns true if this location has the finite object represented by the name,
	// otherwise returns false

	public final boolean hasFinite(String s){
		boolean result = false;
		for (int i = 0; i<this.finite.size(); i++)
			if (this.finite.get(i).getName().matches(s))
				result = true;
		return result;
	}

	public final boolean isLargeArea(){
		return largearea;
	}

	public final float getTemperature(){
		return temperature;
	}

	public final float getNazgulProbability(){
		return nazgprobability;
	}

	public final int getTalkIndex(){
		return talkindex;
	}

	// Returns the index of an immovable object located at this location	

	public final int getIndex(String s){
		int result = -1;
		for (int i = 0; i<this.immovable.size(); i++)
			if (this.immovable.get(i).getName().matches(s))
				result = this.immovable.get(i).getIndex();
		return result;
	}


	// Returns the index of a finite object located at this location	

	public final int getFiniteIndex(String s){
		int result = -1;
		for (int i = 0; i<this.finite.size(); i++)
			if (this.finite.get(i).getName().matches(s))
				result = this.finite.get(i).getIndex();
		return result;
	}

	// Returns true if there is a boat at this location, otherwise returns false
	
	public final boolean hasBoat(){
		boolean result = false;
		for (int i = 0; i<this.immovable.size(); i++)
			if (this.immovable.get(i).getName().matches("boat"))
				result = true;
		return result;
	}

	// Returns true if there is a horse at this location, otherwise returns false
	
	public final boolean hasHorse(){
		boolean result = false;
		for (int i = 0; i<this.animate.size(); i++)
			if (this.animate.get(i).getName().matches("horse"))
				result = true;
		return result;
	}

	// Returns true if there is a web at this location, otherwise returns false
	
	public final boolean hasWeb(){
		boolean result = false;
		for (int i = 0; i<this.immovable.size(); i++)
			if (this.immovable.get(i).getName().matches("web"))
				result = true;
		return result;
	}
	
	// Returns the index of the first enemy animate object at this location.  Can
	// return -1 if no enemy at this location

	public final int returnEnemy(List<AnimateType> AnimateList){
		
		int result = -1;
		for (int i = 0; i<this.animate.size(); i++)	
			if (AnimateType.returnAnimateObject(this.animate.get(i).getIndex(), AnimateList).isEnemy())
				result = this.animate.get(i).getIndex();
		return result;
	}

	// Returns the index of the first orcish animate object at this location.  Can
	// return -1 if no orc, uruk, goblin, or hobgoblin at this location

	public final int returnOrc(List<AnimateType> AnimateList){
		
		int result = -1;
		for (int i = 0; i<this.animate.size(); i++)	
			if (AnimateType.returnAnimateObject(this.animate.get(i).getIndex(), AnimateList).getName().matches("orc")||
			    AnimateType.returnAnimateObject(this.animate.get(i).getIndex(), AnimateList).getName().matches("goblin")||
			    AnimateType.returnAnimateObject(this.animate.get(i).getIndex(), AnimateList).getName().matches("uruk")||
	                    AnimateType.returnAnimateObject(this.animate.get(i).getIndex(), AnimateList).getName().matches("hobgoblin"))
				result = this.animate.get(i).getIndex();
		return result;
	}



	// Unlocks an openable immovable object if the correct spell is invoked for this location.
	// Returns true if spell was effective, otherwise returns false

	public final boolean spellAction(String spell, String [] spells, int [] spellindices, List<ImmovableType> ImmovableList){

			boolean result  = false;
			int spellfoundindex = -2;
			for (int i = 0; i<spells.length; i++)
				if (spells[i].matches(spell)) {
					spellfoundindex = spellindices[i]; // there are multiple words which suffice for each spell
					if (spellfoundindex == this.spellindex)
						 break; } // if we found a good spell, get out of the loop
					
			if (result = (spellfoundindex == this.spellindex))
				for (int i = 0; i < this.immovable.size() ; i++){
					ImmovableType obj = ImmovableType.returnImmovableObject(this.immovable.get(i).getIndex(), ImmovableList);
					if (obj!=null)
				    		if (obj.isLockable() && obj.isLocked())
							if (obj.isUnlocksWithSpell()){
								obj.setLocked(false);
								System.out.println("The " + obj.getName()  + " is now unlocked.");
						}	
				}

		return result;
	}

	// spellAction caused by holding an object

	// Unlocks an openable immovable object if the correct spell is generated by holding an object.
	// Returns true if spell was effective, otherwise returns false

	public final boolean spellAction(PlayerStatus mystatus, List<ImmovableType> ImmovableList, List<MovableType> MovableList){

			boolean result  = false;
			switch (this.spellindex) {
			// add holding-based spells in case statements			
	                        case 7 : if (mystatus.isHolding(MovableType.returnMovableObject("sword", MovableList))){  // 11 = sword = Anduril
						result = true;
						System.out.println("The oathbreakers recognize the heir of Isildur and the flame of Anduril and permit you to pass.");
						} 
				break;

	                        case 8 : if (mystatus.isHolding(MovableType.returnMovableObject("sword", MovableList))){  
						// 11 = sword = Anduril; 18 = crown; 24 = arkenstone; 30 = shield
						result = true;
						System.out.println("The guards see that you are holding a sword made for the Last Alliance of Elves and Men, and will permit you to pass.");
						break;
						} 

					if (mystatus.isHolding(MovableType.returnMovableObject("shield", MovableList))){  
						// 11 = sword = Anduril; 18 = crown; 24 = arkenstone; 30 = shield
						result = true;
						System.out.println("The guards see your shield with the sign of their friends - the riders of Rohan - and will permit you to pass.");
						break;
						} 
					
					 if (mystatus.isHolding(MovableType.returnMovableObject("crown", MovableList))||
					     mystatus.isHolding(MovableType.returnMovableObject("arkenstone", MovableList))){  
						// 11 = sword = Anduril; 18 = crown; 24 = arkenstone; 30 = shield
						result = true;
						System.out.println("The guards recognize that you are holding an object of great value and will permit you to pass.");
						break;
						} 

					 if (mystatus.isHolding(MovableType.returnMovableObject("phial", MovableList))||
					     mystatus.isHolding(MovableType.returnMovableObject("bow", MovableList))){  
						// 26  = phial; 27 = bow
						result = false;
						System.out.println("The guards recognize that you are carrying an Elvish object - probably from that witch who lives in Lorien forest north of Rohan, and will not let you pass.");
						break;
						} 

					 if (mystatus.isHolding(MovableType.returnMovableObject("dagger", MovableList))||
                                             mystatus.isHolding(MovableType.returnMovableObject("knife", MovableList))||
                                             mystatus.isHolding(MovableType.returnMovableObject("blade", MovableList))){  
						// 2  = dagger; 9 = knife
						result = false;
						System.out.println("The guards laugh at you for carrying a childs weapon to war, and will not let you pass.");

						break;
						} 

					if (mystatus.didBlowHorn()){
						result = true;
						System.out.println("The guards recognize the horn call of Eorl the Young and will let you pass.");
						break;						
						}

				break;

				case 9 : if (mystatus.isHolding(MovableType.returnMovableObject("phial", MovableList))){  // 2 = phial
						result = true;
						System.out.println("The light of the star of Earendil permits you to pass.");
						} 
				break;
				
				default:
				break;
			}
			if (result)
				for (int i = 0; i < this.immovable.size() ; i++){
					ImmovableType obj = ImmovableType.returnImmovableObject(this.immovable.get(i).getIndex(), ImmovableList);
					if (obj!=null)
			    			if (obj.isLockable() && obj.isLocked())
							if (obj.isUnlocksWithSpell()){
								obj.setLocked(false);
								System.out.println("The " + obj.getName()  + " is now unlocked.");
						}	
				} // end for			
			
		return result;
	} // end spellAction

	
	public final void takeMovable(MovableType obj){
		this.movable.remove(obj);
	}

	public final void removeAnimate(AnimateType obj){
		for (int i = 0; i<this.animate.size(); i++)
			if (obj.getName().matches(this.animate.get(i).getName()))
				this.animate.remove(this.animate.get(i));
	}		


	public final boolean addMovable(MovableType obj){
		this.movable.add(obj);
		return (this.index == WININDEX);
	}


	public final void addFinite(FiniteType obj){
		this.finite.add(obj);
	}


	public final void addAnimate(AnimateType obj){
		this.animate.add(obj);
	}

	public static final String listToString(List obj){
		String s = "";
		
		Iterator itr = obj.iterator();
		while (itr.hasNext())
			s = s + itr.next().toString() + " ";
		return s;
	}

	// Adds a nazgul to list of the locations's animate objects in the case that a nazgul was not previously 
	// present, but the player chose to put on the ring, and the probability of encountering a nazgul was sufficiently great
	
	public final void attractNazgul(List<AnimateType> AnimateList){
		boolean nazgulfound = false;
		for (int i = 0; i<this.animate.size(); i++)
			if (this.animate.get(i).getName().matches("nazgul"))
				nazgulfound = true;
		if (!nazgulfound)
			if (this.nazgprobability >= NAZGUL_ATTRACT_PROBABILITY){
				this.addAnimate(AnimateType.returnAnimateObject("nazgul", AnimateList));
				System.out.println("Your use of the ring has drawn the nazgul to your location.");
			}
	}
			
	// Executes the action of looking at objects in a location.  If this is the first visit, determines whether
	// or not there will be animate objects based on the probability of them occuring.

	public static final void lookAtObjs(Location location, PlayerStatus mystatus){
		
		for (int i = 0; i<location.immovable.size(); i++)
			System.out.print("You see " + location.immovable.get(i).getLookArticle() + " " + location.immovable.get(i).getName() + ". ");
		
		System.out.println();

		for (int i = 0; i<location.movable.size(); i++)
			System.out.print("You see " + location.movable.get(i).getLookArticle() + " " + location.movable.get(i).getName() + ". ");

		System.out.println();

		for (int i = 0; i<location.finite.size(); i++)
			if (location.finite.get(i).getName().matches("arrow"))
				System.out.print("There are some arrows. ");
			else if (location.finite.get(i).getName().matches("mushrooms")||
				 location.finite.get(i).getName().matches("apples")||
				 location.finite.get(i).getName().matches("grapes")||
				 location.finite.get(i).getName().matches("dates")||
				 location.finite.get(i).getName().matches("berries"))	
					System.out.print("There are some " + location.finite.get(i).getName() + ". ");
				 else 
					System.out.print("There is some " + location.finite.get(i).getName() + ". ");

		System.out.println();
		
		if (location.firstvisit){	

			location.firstvisit = false;	
			int i = 0;
			while (i < location.animate.size()){
				if (location.animate.get(i).getName().matches("nazgul")) location.nazgprobability = location.animate.get(i).getProbability();
				if (location.animate.get(i).getName().matches("horse") && mystatus.getHorseFound())
					location.animate.remove(i);
				else if ((Math.random()*100)>location.animate.get(i).getProbability())
					location.animate.remove(i);
				else i++;
			} // end while
		}					

		for (int i = 0; i<location.animate.size(); i++){
			 System.out.print("You see " + location.animate.get(i).getLookArticle() + " " + location.animate.get(i).getName() + ". ");
			 if (mystatus.hasSpell()) System.out.print("The " + location.animate.get(i).getName() + " fears your magic and will not bother you."); 
			 if (location.animate.get(i).getName().matches("horse")) mystatus.setHorseFound(true);
			 }
		System.out.println();

	}

	// shows than an animate object is afraid of a particular item that the player is holding
        // the fear is specific to the animate object and the held object

        public final boolean fearsHolding(PlayerStatus mystatus, List<MovableType> MovableList){
		boolean result = false;	
		for (int i = 0; i<this.animate.size(); i++){
			switch (this.animate.get(i).getName()){
				case "goblin" :
					 if (mystatus.isHolding(MovableType.returnMovableObject("sword", MovableList))){ // sword
						result = true;
						System.out.println("The goblin is scared of your sword.");}
					 if (mystatus.isHolding(MovableType.returnMovableObject("phial", MovableList))){ // phial
						result = true;
						System.out.println("The Elvish light of Earendil scares the goblin");}
				break;
				case "spider" :
					if (mystatus.isHolding(MovableType.returnMovableObject("dagger", MovableList))){ // dagger
						result = true;
						System.out.println("The spider is scared of your dagger.");}
					if (mystatus.isHolding(MovableType.returnMovableObject("phial", MovableList))){ // phial
						result = true;
						System.out.println("The Elvish light of Earendil scares the spider");}
				break;
				case "warg" :
					if (mystatus.isHolding(MovableType.returnMovableObject("phial", MovableList))){  //phial
						result = true;
						System.out.println("The Elvish light of Earendil scares the warg");}
				break;	
				case "ghost" :
					 if (mystatus.isHolding(MovableType.returnMovableObject("sword", MovableList))){ // sword
						result = true;
						System.out.println("The ghost recognizes its allegiance to the holder of the sword of Isildur.");}
				default:
				break;
			} // end switch
		} // end for
	return result;
	} // end fearsholding						
		
	// If player is holding an Elvish weapon, checks all of the surrounding small-area locations to see if orcs are
	// present.  If orcs are in surrounding areas, the Elvish weapon will glow blue.

	// Returns the last movement vector at current location which is crossable.
	// If there are no crossable movement vectors, returns the empty string.
	
	public final String returnCrossDirection() {
        String crossdirection = "";
        if (this.northptr!=null)
            if (this.northptr.isCrossable())
                crossdirection = "north";
	
	    if (this.northeastptr!=null)
            if (this.northeastptr.isCrossable())
                crossdirection = "northeast";
	
        if (this.eastptr!=null)
            if (this.eastptr.isCrossable())
                crossdirection = "east";
	
        if (this.southeastptr!=null)
            if (this.southeastptr.isCrossable())
                crossdirection = "southeast";
	
	    if (this.southptr!=null)
            if (this.southptr.isCrossable())
                crossdirection = "south";
	
	    if (this.southwestptr!=null)
            if (this.southwestptr.isCrossable())
                crossdirection = "southwest";
		
	    if (this.westptr!=null)
            if (this.westptr.isCrossable())
                crossdirection = "west";
	
	    if (this.northwestptr!=null)
            if (this.northwestptr.isCrossable())
                crossdirection = "northwest";
	
	    if (this.upptr!=null)
            if (this.upptr.isCrossable())
                crossdirection = "up";

        if (this.downptr!=null)
            if (this.downptr.isCrossable())
                crossdirection = "down";

        if (this.inptr!=null)
            if (this.inptr.isCrossable())
                crossdirection = "in";
        
	    if (this.outptr!=null)
            if (this.outptr.isCrossable())
                crossdirection = "out";

         return crossdirection;
    }
        
    public static final void glowsBlue(List<Location> locationlist, int currentloc, PlayerStatus mystatus, List<AnimateType> AnimateList){

		boolean glowsblue = false;
		if (mystatus.hasGlowBlue()&& !locationlist.get(currentloc).isLargeArea()){
			if (locationlist.get(currentloc).northptr!=null)
				if (locationlist.get(currentloc).northptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).northptr.getIndex()).returnOrc(AnimateList)!=-1) &&
					     !locationlist.get(locationlist.get(currentloc).northptr.getIndex()).isLargeArea())
						glowsblue = true;
					
			if (locationlist.get(currentloc).northeastptr!=null)
				if (locationlist.get(currentloc).northeastptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).northeastptr.getIndex()).returnOrc(AnimateList)!=-1) &&
						!locationlist.get(locationlist.get(currentloc).northeastptr.getIndex()).isLargeArea())
						glowsblue = true;
			if (locationlist.get(currentloc).eastptr!=null)
				if (locationlist.get(currentloc).eastptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).eastptr.getIndex()).returnOrc(AnimateList)!=-1) &&
						!locationlist.get(locationlist.get(currentloc).eastptr.getIndex()).isLargeArea())
						glowsblue = true;
			
			if (locationlist.get(currentloc).southeastptr!=null)
				if (locationlist.get(currentloc).southeastptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).southeastptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).southeastptr.getIndex()).isLargeArea())
						glowsblue = true;
			
			if (locationlist.get(currentloc).southptr!=null)
				if (locationlist.get(currentloc).southptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).southptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).southptr.getIndex()).isLargeArea())
						glowsblue = true;
			
			if (locationlist.get(currentloc).southwestptr!=null)
				if (locationlist.get(currentloc).southwestptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).southwestptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).southwestptr.getIndex()).isLargeArea())
						glowsblue = true;

			if (locationlist.get(currentloc).westptr!=null)
				if (locationlist.get(currentloc).westptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).westptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).westptr.getIndex()).isLargeArea())
						glowsblue = true;

			if (locationlist.get(currentloc).northwestptr!=null)
				if (locationlist.get(currentloc).northwestptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).northwestptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).northwestptr.getIndex()).isLargeArea())
						glowsblue = true;
			if (locationlist.get(currentloc).upptr!=null)
				if (locationlist.get(currentloc).upptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).upptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).upptr.getIndex()).isLargeArea())
						glowsblue = true;
			
			if (locationlist.get(currentloc).downptr!=null)
				if (locationlist.get(currentloc).downptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).downptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).downptr.getIndex()).isLargeArea())
						glowsblue = true;

			if (locationlist.get(currentloc).inptr!=null)
				if (locationlist.get(currentloc).inptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).inptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).inptr.getIndex()).isLargeArea())
						glowsblue = true;

			if (locationlist.get(currentloc).outptr!=null)
				if (locationlist.get(currentloc).outptr.getIndex()!=-1)
					if ((locationlist.get(locationlist.get(currentloc).outptr.getIndex()).returnOrc(AnimateList)!=-1)&&
						!locationlist.get(locationlist.get(currentloc).outptr.getIndex()).isLargeArea())
						glowsblue = true;


			}

		if (mystatus.hasGlowBlue() && (locationlist.get(currentloc).returnOrc(AnimateList)!=-1))
			glowsblue = true;

		if (glowsblue)
			System.out.println("Your " + mystatus.getGlowBlue() + " is glowing blue.");
	}

	public String toString(){
		String s = "Index= " + index + "Temperature= " + temperature +  "LargeArea= " + largearea + " description= " + description +
                           " looknorth= " + looknorth +
			   " looksouth= " + looksouth + " lookeast= " + lookeast + " lookwest= " + lookwest + 
			   " lookup= " + lookup + " lookdown= " + lookdown + "North ptr= " + northptr.toString() + "Northeast ptr= " +
                           northeastptr.toString() + "East ptr= " + eastptr.toString() + "Southeast ptr= " + southeastptr.toString() +
			   "South ptr= " + southptr.toString() + "Southwest ptr= " + southwestptr.toString() +
  			   "West ptr= " + westptr.toString() + "Northwest ptr= " + northwestptr.toString() + "Up ptr= " + upptr.toString() +
                            "Down ptr= " + downptr.toString() + "In ptr= " + inptr.toString() + "Out ptr= " + outptr.toString() +
			    listToString(immovable) + listToString(movable) +
			   listToString(finite) + listToString(animate);
		return s;
	}

	
} /* end Location */
	
