import java.io.*;
import java.util.*;


// Defines an Object of ImmovableType.  Immovable objects can be seen, looked at, touched, and sometimes other interaction, but cannot be moved,
// destroyed, or taken
// Examples are "fireplace", "tree", "cliff", "palantir", etc.  Note that some objects that in real life would be classified as "Movable" are
// "Immovable" for the purpose of this game.
// All methods are final and not designed for inheritance

public class ImmovableType{

	private int index; // unique index (>=0)
	private String name; // name 
	private String description; // description
	private boolean readable; // can the object be "read" (i.e., is there some writing on object?)
	private String inscription; // if so, what does it say?
	private boolean openable; // can the object be "opened" or "closed" (like door, window, etc.)
	private boolean open; // if so, is it open?
	private boolean lockable; // can the object be "locked?"
	private boolean locked; // if so, is it locked?
	private boolean unlockswithkey; // does it unlock with a key?
	private int key;  // if so, which key? 
	private boolean unlockswithspell; // does it unlock with a spell?
	private boolean climbable; // can the object be "climbed?"
	private boolean crossable; // can object be crossed?
	private boolean boat; // is it a boat?

	// This array is used to define interactions with a palantir, Galadriel's mirror, or other fortune-telling devices.
	// It provides the user a different fortune depending on how far along in the game, according to dayselapsed.

	private String[] fortunearray = { "You see a tower with a luminous green dome.  A black gate opens beneath the tower - nine riders on black horses ride out."
  + " They ride west toward the setting sun. A lone ranger wanders the forest and hills, his face lined with care and his beard rough."
  + "  A white wizard looks out from a tall black tower onto a circular plain ringed with stone.",
	"Armies of orcs pour forth from the caves under high snow-covered peaks.  A white wizard walks under tall ancient trees." +
	"  Black ships with white sails beat the winds on the open seas.",
	"An ancient demon stands above an abyss in an underground cavern.  Goblins issue forth into the forests under the mountain and across the great river. " 
  + " The lidless eye peers out into the void - his gaze pierces all.  A plume erupts from a volcano on a barren plain - men cower"
  + " behind the old stone walls of their mountain city.",
	"You see an Elvish princess walking alone under the shadow of barren winter trees.  You see the fires of Mount Doom - " +
				      "the hosts of Mordor issue from the black gates to attack the walled city. " +
  "A white wizard rides a swift horse to a walled city with a white tower."
};
	
	// Constructor - makes new object during location parsing

	public ImmovableType(int newindex, String newname){
		index = newindex;
		name = newname;
	}

	// Given days elapsed, read the fortune (note - can be applied to all fortune-telling objects)

	public final void readPalantir(float dayselapsed){

		if (dayselapsed < 20)
			System.out.println(fortunearray[0]);
		else if (dayselapsed < 50)
			System.out.println(fortunearray[1]);
		else if (dayselapsed < 80)
			System.out.println(fortunearray[2]);
		else 
			System.out.println(fortunearray[3]);
	} 

	// Given the name of an Immovable Object and the ImmovableList, return the object which represents
	// all of the fields of the Immovable Object.
	// Can return null - client is responsible to check.

	public static final ImmovableType returnImmovableObject(String s, List<ImmovableType> ImmovableList){

		ImmovableType returnobj = null;
		for (int i = 0; i<ImmovableList.size(); i++)
			if (ImmovableList.get(i).getName().matches(s))
					returnobj = ImmovableList.get(i);			
		return returnobj;
	}

	// Given the index of an Immovable Object and the ImmovableList, return the object which represents
	// all of the fields of the Immovable Object.
	// Can return null - client is responsible to check.

	public static final ImmovableType returnImmovableObject(int index, List<ImmovableType> ImmovableList){

		ImmovableType returnobj = null;
		for (int i = 0; i<ImmovableList.size(); i++)
			if (ImmovableList.get(i).getIndex() == index)
					returnobj = ImmovableList.get(i);			
		return returnobj;
	}

	// Decides whether String should get "a" or "an"

	public final String getLookArticle(){
		String s = "";
		if (name.matches("stair")||name.matches("stairs"))
			s = "some";
		else if (name.startsWith("a")||
		    name.startsWith("e")||
		    name.startsWith("i")||
		    name.startsWith("o")||
		    name.startsWith("u"))
			s = "an";
		else 
			s = "a";
		return s;
	}	
	
	// Converts "true" or "false" to boolean true or boolean false

	public final static boolean parseBool(String s){
		return (s.matches("true")) ? true : false;
	}

	// Parses the text file and builds the ImmovableList during initiation.
	// Fields in the text file must exactly match the order in this parser,
	// otherwise will throw IOException

	public static void parseImmovable(List<ImmovableType> ImmovableList, String s){
	

		//System.out.println("Called parseImmovable");
		int endindex = 0;
	
		s = s.substring(s.indexOf("+")+1);
		
		s = s.trim();
		String t = s.substring(0,s.indexOf("/"));
		//System.out.println(s);
		int newindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex);

		s = s.trim();
		String newname = s.substring(0,s.indexOf("/"));
    	        ImmovableType newobj = new ImmovableType(newindex, newname);
		//System.out.println(newobj.getName());

		
   		s = s.substring(s.indexOf("/")+1);
		

		//System.out.println("parsed index= " + newindex1);

		s = s.trim();
		String newdescription = s.substring(0,s.indexOf("/"));
		
		newobj.setDescription(newdescription);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("description= " + newobj.getDescription());

		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newreadable = parseBool(t);
		
		newobj.setReadable(newreadable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("readable " + newobj.isReadable());
		

		s = s.trim();
		String newinscription = s.substring(0,s.indexOf("/"));
		
		newobj.setInscription(newinscription);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("Inscription= " + newobj.getInscription());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newopenable = parseBool(t);
		
		newobj.setOpenable(newopenable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Openable= " + newobj.isOpenable());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newopen = parseBool(t);
		
		newobj.setOpen(newopen);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Openable= " + newobj.isOpenable());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newlockable = parseBool(t);
		
		newobj.setLockable(newlockable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Lockable= " + newobj.isLockable());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newlocked = parseBool(t);
		
		newobj.setLocked(newlocked);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Locked= " + newobj.isLocked());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newunlockswithkey = parseBool(t);
		
		newobj.setUnlocksWithKey(newunlockswithkey);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Unlocks with key= " + newobj.isUnlocksWithKey());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		int newkey = Integer.parseInt(t);
		newobj.setKey(newkey);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("key= " + newobj.getKey());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newunlockswithspell = parseBool(t);
		
		newobj.setUnlocksWithSpell(newunlockswithspell);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Unlocks with spell= " + newobj.isUnlocksWithSpell());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newclimbable = parseBool(t);
		
		newobj.setClimbable(newclimbable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Climbable= " + newobj.isClimbable());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newboat = parseBool(t);
		
		newobj.setBoat(newboat);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Boat= " + newobj.isBoat());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newcrossable = parseBool(t);
		
		newobj.setCrossable(newcrossable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Crossable= " + newobj.isCrossable());

		ImmovableList.add(newobj);
		//System.out.println(newobj.toString());

	}


	public final int getIndex(){
		return index;
	}

	public final String getName(){
		return name;
	}

	public final String getDescription(){
		return description;
	}

	public final String getInscription(){
		return inscription;
	}


	public final boolean isReadable(){
		return readable;
	}
	
	public final boolean isOpenable(){
		return openable;
	}
	
	public final boolean isOpen(){
		return open;
	}

	public final boolean isLockable(){
		return lockable;
	}

	public final boolean isLocked(){
		return locked;
	}

	public final boolean isUnlocksWithKey(){
		return unlockswithkey;
	}

	public final boolean isClimbable(){
		return climbable;
	}

	public final boolean isBoat(){
		return boat;
	}

	public final boolean isCrossable(){
		return crossable;
	}

	public final int getKey(){
		return key;
	}
	
	public final boolean isUnlocksWithSpell(){
		return unlockswithspell;
	}

	public final void setIndex(int newindex){
		index = newindex;
	}

	public final void setName(String newname){
		name = newname;
	}


	public final void setDescription(String newdescription){
		description = newdescription;
	}

	public final void setInscription(String newinscription){
		inscription = newinscription;
	}


	public final void setReadable(boolean newreadable){
		readable = newreadable;
	}
	
	public final void setOpenable(boolean newopenable){
		openable = newopenable;
	}
	
	public final void setOpen(boolean newopen){
		open = newopen;
	}

	public final void setLockable(boolean newlockable){
		lockable = newlockable;
	}

	public final void setLocked(boolean newlocked){
		locked = newlocked;
	}

	public final void setUnlocksWithKey(boolean newunlockswithkey){
		 unlockswithkey = newunlockswithkey;
	}

	public final void setKey(int newkey){
		 key = newkey;
	}

	public final void setUnlocksWithSpell(boolean newunlockswithspell){
		 unlockswithspell = newunlockswithspell;
	}

	public final void setClimbable(boolean newclimbable){
		climbable = newclimbable;
	}

	public final void setBoat(boolean newboat){
		boat = newboat;
	}

	public final void setCrossable(boolean newcrossable){
		crossable = newcrossable;
	}

	public String toString() {
		String s = "Immovable: Index= " + index + " name= " + name +  " Description: " +
			   description + " Readable: " + readable + " Inscription: " + inscription +
			   " Openable: " + openable + " Open: " + open + " Lockable: " + lockable +
			   " Locked: " + locked + " Unlocks with key: " + unlockswithkey + 
			   " Key: " + key + " Unlocks with spell: " + unlockswithspell + " Climbable: " + climbable +
			   "Crossable: " + crossable + " Boat: " + boat;

		return s;
	}

}
		
	
