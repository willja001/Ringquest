import java.io.*;
import java.util.*;

// Defines an object of MovableType.  Movable Objects are unique, they can be acquired by the player, and moved to another location.
// Examples: "ring", "sword","dagger", "helmet", etc.
// All methods are final and not designed for inheritance

public class MovableType{

	private int index; // unique index (>=0)
	private String name; // name
	private String description; // description
	private float weight; // weight 0 - 100
	private float warmth; // warmth provided by object
	private boolean weapon; // is this object a weapon?
	private boolean ranged; // if so, can it be used at "range," like "bow", "spear"?
	private boolean elvish; // is it Elvish?
	private float lethality; // lethality 0 - 100 of weapon
	private boolean holdable; // can this object be held?
	private boolean glowsblue; // does this object glow blue when orcs are near?
	private boolean sharp; // is this a sharp object that can cut?

	// Constructor - makes object during location parsing

	public MovableType(int newindex, String newname){
		index = newindex;
		name = newname;
	}

	// Given a name of movable object and MovableList, return an object with all the fields
	// of a movable object.  
	// Can return null - responsibility of the client to check

	public static final MovableType returnMovableObject(String s, List<MovableType> MovableList){

		MovableType returnobj = null;
		for (int i = 0; i<MovableList.size(); i++)
			if (MovableList.get(i).getName().matches(s))
					returnobj = MovableList.get(i);			
		return returnobj;
	}

	// Given an index of movable object and MovableList, return an object with all the fields
	// of a movable object.  
	// Can return null - responsibility of the client to check

	public static final MovableType returnMovableObject(int index, List<MovableType> MovableList){

		MovableType returnobj = null;
		for (int i = 0; i<MovableList.size(); i++)
			if (MovableList.get(i).getIndex() == index)
					returnobj = MovableList.get(i);			
		return returnobj;
	}
	// Converts "true" or "false" to boolean true or boolean false

	public static final boolean parseBool(String s){
		return (s.matches("true")) ? true : false;
	}

	// Reads the text file to build the MovableList during initialization.
	// The fields in the text file must exactly match the order of the parser,
	// otherwise will throw IOException

	public static final void parseMovable(List<MovableType> MovableList, String s){
	

		//System.out.println("Called parseMovable");
		int endindex = 0;
		
		s = s.trim();
		String t = s.substring(0,s.indexOf("/"));
		//System.out.println(s);
		int newindex = Integer.parseInt(t);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex);

		s = s.trim();
		String newname = s.substring(0,s.indexOf("/"));
    	        MovableType newobj = new MovableType(newindex, newname);
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
		
		Float newweight = Float.parseFloat(t);
		
		newobj.setWeight(newweight);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Weight= " + newobj.getWeight());
		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		Float newwarmth = Float.parseFloat(t);
		
		newobj.setWarmth(newwarmth);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Warmth= " + newobj.getWarmth());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newweapon = parseBool(t);
		
		newobj.setWeapon(newweapon);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("weapon " + newobj.isWeapon());
		
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
		
		boolean newholdable = parseBool(t);
		
		newobj.setHoldable(newholdable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Holdable= " + newobj.isHoldable());
		
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));

		boolean newglowsblue = parseBool(t);
		
		newobj.setGlowsBlue(newglowsblue);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Glows Blue= " + newobj.doesGlowBlue());
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));

		boolean newsharp = parseBool(t);
		
		newobj.setSharp(newsharp);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Sharp = " + newobj.isSharp());
		s = s.trim();
		t = s.substring(0,s.indexOf("/"));

		Float newlethality = Float.parseFloat(t);
		newobj.setLethality(newlethality);
		//System.out.println("Lethality= " + newobj.getLethality());

		MovableList.add(newobj);
		//System.out.println(newobj.toString());

	}


	public final int getIndex(){
		return index;
	}

	// Decides if String gets "a" or "an"

	public final String getLookArticle(){
		String s = "";

		if (name.matches("mail")||name.matches("armor")) return s;		

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

	public final String getName(){
		return name;
	}

	public final String getDescription(){
		return description;
	}

	public final float getWeight(){
		//System.out.println("getWeight called - weight is: " + weight + ".");
		return weight;
	}

	public final float getWarmth(){
		return warmth;
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

	
	public final boolean isHoldable(){
		return holdable;
	}

	public final boolean doesGlowBlue(){
		return glowsblue;
	}

	public final boolean isSharp(){
		return sharp;
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

	public final void setWeight(float newweight){
		weight = newweight;
	}

	public final void setWarmth(float newwarmth){
		warmth = newwarmth;
	}

	public final void setLethality(float newlethality){
		lethality = newlethality;
	}

	public final void setWeapon(boolean newweapon){
		weapon = newweapon;
	}
	
	public final void setRanged(boolean newranged){
		ranged = newranged;
	}
	
	public final void setElvish(boolean newelvish){
		elvish = newelvish;
	}

	public final void setHoldable(boolean newholdable){
		holdable = newholdable;
	}

	public final void setGlowsBlue(boolean newglowsblue){
		glowsblue = newglowsblue;
	}

	public final void setSharp(boolean newsharp){
		sharp = newsharp;
	}
	
	public final void makeNewEnemy(List<AnimateType> AnimateList){
		if (this.name.matches("egg")){
			AnimateType.returnAnimateObject("eagle", AnimateList).setEnemy(true);
			System.out.println("Taking the egg was not wise. The eagles will be very angry and will come looking for whomever took it.");
		}
		if (this.name.matches("arkenstone")){
			AnimateType.returnAnimateObject("dwarf", AnimateList).setEnemy(true);		
			System.out.println("The Dwarves curse you for taking their treasure and declare you to be their enemy forever.");
		}
	}

	public String toString() {
		String s = "Movable: Index= " + index + " name= " + name + " weight= " + weight + " warmth= " + warmth + 
			   " Description: " + description + " Weapon: " + weapon + " Ranged: " +
			    ranged + " Elvish: " + elvish + " Holdable: " + holdable + " Lethality: " + lethality ;

		return s;
	}

}
	
