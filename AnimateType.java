import java.io.*;
import java.util.*;

// Defines an Object of AnimateType
// All Objects which are animate (i.e., goblins, orcs, wizards, etc.) with which player can interact
// all methods are final and not designed for inheritance

public class AnimateType{

	private int index; // unique index (>=0) for each animate object
	private float probability; // the probability of encountering when coming to new area (0 - 100%)
	private String name;	//name of the animate object, i.e., "goblin"
	private String description; // short description, i.e., "the goblin is an ugly creature"
	private boolean weapon; //does object carry a weapon
	private String weaponname; //if so, what is weapon name
	private boolean ranged; // can the weapon be used at range (i.e., "bow," "spear", or only close contact (i.e., "sword")?
	private boolean elvish; // is this an elvish animate object?
	private boolean enemy; // is it an enemy?
	private float lethality; // its lethality during combat (0 - 100%)
	private float injury; // injury incurred during combat
	
	private final float LETHALITY_CONSTANT = 1.3F; // decrease to make harder
	
	// Constructor, instantiates an animate object during location build
	public AnimateType(int newindex, String newname){
		index = newindex;
		probability = 0.0F;
		name = newname;
		injury = 0.0F;
	}

	//Given a name of an animate object, and the List of all Animate Objects, return
	//the complete Object, which has all fields
	//can return null object (client's responsibility to check)

	public static final AnimateType returnAnimateObject(String s, List<AnimateType> AnimateList){

		AnimateType returnobj = null;
		for (int i = 0; i<AnimateList.size(); i++)
			if (AnimateList.get(i).getName().matches(s))
					returnobj = AnimateList.get(i);			
		return returnobj;
	}

	//Given an index of an animate object, and the List of all Animate Objects, return
	//the complete Object, which has all fields
	//can return null object (client's responsibility to check)

	public static final AnimateType returnAnimateObject(int index, List<AnimateType> AnimateList){

		AnimateType returnobj = null;
		for (int i = 0; i<AnimateList.size(); i++)
			if (AnimateList.get(i).getIndex()==index)
					returnobj = AnimateList.get(i);			
		return returnobj;
	}

	// parses "true" or "false" into boolean true or boolean false
	public static final boolean parseBool(String s){
		return (s.matches("true")) ? true : false;
	}

	// Parser routine for building the AnimateList
	// Called only at initiation when reading the text file
	// The text field must be aligned exactly to this parser, otherwise
	// will throw IOException

	public static final void parseAnimate(List<AnimateType> AnimateList, String s){
	

		//System.out.println("Called parseAnimate");
		int endindex = 0;
		
		//s = s.substring(s.indexOf("+")+1);
		
		s = s.trim();
		String t = s.substring(0,s.indexOf("/"));
		int newindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex);

		s = s.trim();
		String newname = s.substring(0,s.indexOf("/"));
    	        AnimateType newobj = new AnimateType(newindex, newname);
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
		
		boolean newweapon = parseBool(t);
		
		newobj.setWeapon(newweapon);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("weapon " + newobj.isWeapon());
		
		s = s.trim();
		String newweaponname = s.substring(0,s.indexOf("/"));
		
		newobj.setWeaponName(newweaponname);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("description= " + newobj.getWeaponName());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newranged = parseBool(t);
		
		newobj.setRanged(newranged);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Ranged= " + newobj.isRanged());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newelvish = parseBool(t);
		
		newobj.setElvish(newelvish);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Elvish= " + newobj.isElvish());

		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newenemy = parseBool(t);
		
		newobj.setEnemy(newenemy);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Enemy= " + newobj.isEnemy());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		Float newlethality = Float.parseFloat(t);
		newobj.setLethality(newlethality);
		//System.out.println("Lethality= " + newobj.getLethality());
		AnimateList.add(newobj);
		//System.out.println(newobj.toString());

	}

	// Determines whether String should have "a" or "an"
	public final String getLookArticle(){
		String s = "";
		if (name.startsWith("a")||
		    name.startsWith("e")||
		    name.startsWith("i")||
		    name.startsWith("o")||
		    name.startsWith("u"))
			s = "an";
		else 
			s = "a";
		return s;
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

	public final String getWeaponName(){
		return weaponname;
	}

	public final float getLethality(){
		return lethality;
	}

	public final boolean isWeapon(){
		return weapon;
	}
	
	public final boolean isRanged(){
		return ranged;
	}
	
	public final boolean isElvish(){
		return elvish;
	}
		
	public final boolean isEnemy(){
		return enemy;
	}

	public final float getInjury(){
		return injury;
	}

	public final float getProbability(){
		return probability;
	}
	
	// injury is computed as a function of current injury, player's weapon's lethality
	// and enemy's lethality

	public final void computeInjury(MovableType obj){
		
		if (obj.isWeapon())
			injury = injury + (obj.getLethality()*LETHALITY_CONSTANT - this.lethality);
	}

	public final void setIndex(int newindex){
		index = newindex;
	}

	public final void setProbability(float newprobability){
		probability = newprobability;
	}

	public final void setName(String newname){
		name = newname;
	}


	public final void setDescription(String newdescription){
		description = newdescription;
	}


	public final void setLethality(float newlethality){
		lethality = newlethality;
	}

	public final void setWeapon(boolean newweapon){
		weapon = newweapon;
	}

	public final void setWeaponName(String newweaponname){
		weaponname = newweaponname;
	}
	
	public final void setRanged(boolean newranged){
		ranged = newranged;
	}
	
	public final void setElvish(boolean newelvish){
		elvish = newelvish;
	}

	public final void setEnemy(boolean newenemy){
		enemy= newenemy;
	}


	public String toString() {
		String s = "Animate: Index= " + index + " name= " + name +  " Description: " +
			   description + " Weapon: " + weapon + " Weapon Name: " + weaponname + " Ranged: " +
			   ranged + " Elvish: " + elvish +  " Enemy: " + enemy + " Lethality: " + lethality;

		return s;
	}

}
	
