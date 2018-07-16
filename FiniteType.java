import java.io.*;
import java.util.*;

// Define Objects of FiniteType - objects which player can "take" or "use," but are not unique, and do not
// disappear when taken.  Examples: food, water, rope, drink, etc.
// All methods are final and not designed for inheritance

public class FiniteType{

	private int index;  // unique index for each finite object (>= 0)
	private String name; // name of finite "food", "water," "beer", etc.
	private String description; // description
	private float weight; //weight of object 0 - 100
	private boolean edible; // is it edible?
	private float foodpctperday; // how much of this food is consumed per day, i.e., some foods are more efficient than others
	private boolean drinkable; // is it drinkable?
	private float fluidpctperday; // how much of the drink is consumed per day?
	private boolean healable; // does the object have a healing quality?
	private float medicinepctperday; // how much of the medicine is consumed per day?
	private boolean elvish; // is it elvish?
	private boolean poison; // is this poisonous?
	private float energyimprovement; // what is its immediate energy improvement from using it?
	private int foodtype; // what is food group? 0=NA, 1=grains, 2=fruit/vegetable, 3=meats, 4=dairy
	
	//constructor, builds an object during parsing locations

	public FiniteType(int newindex, String newname){
		index = newindex;
		name = newname;
		
	}

	// Given name of FiniteType Object and the FiniteList, returns an object which matches
	// the name.  
	// Can return null - client's responsibility to check

	public static final FiniteType returnFiniteObject(String s, List<FiniteType> FiniteList){

		FiniteType returnobj = null;
		for (int i = 0; i<FiniteList.size(); i++)
			if (FiniteList.get(i).getName().matches(s))
					returnobj = FiniteList.get(i);			
		return returnobj;
	}

	// Given name of FiniteType Object and the FiniteList, returns an object which matches
	// the name of an object that is present in that location.  
	// Can return null - client's responsibility to check

	public static final FiniteType returnFiniteObject(String s, Location location, List<FiniteType> FiniteList){

		FiniteType returnobj = null;
		int newindex = location.getFiniteIndex(s);
		if (newindex != -1)
			for (int i = 0; i<FiniteList.size(); i++)
				if (FiniteList.get(i).getIndex() == newindex)
					returnobj = FiniteList.get(i);			
		return returnobj;
	}


	// converts "true" or "false" to boolean true or boolean false

	public final static boolean parseBool(String s){
		return (s.matches("true")) ? true : false;
	}

	// Parses the text file to build the FiniteList during initialization. All fields
	// in the text file must exactly match this parser, otherwise will throw
	// IOException

	public final static void parseFinite(List<FiniteType> FiniteList, String s){
	

		//System.out.println("Called parseFinite");
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
    	        FiniteType newobj = new FiniteType(newindex, newname);
		//System.out.println(newobj.getName());

		
   		s = s.substring(s.indexOf("/")+1);
		

		//System.out.println("parsed index= " + newindex1);

		s = s.trim();
		String newdescription = s.substring(0,s.indexOf("/"));
		
		newobj.setDescription(newdescription);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("description= " + newobj.getDescription());

		s = s.trim();
		String newweight = s.substring(0,s.indexOf("/"));
		
		Float f = Float.parseFloat(newweight);
		newobj.setWeight(f);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("Weight= " + newobj.getWeight());
		


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newedible = parseBool(t);
		
		newobj.setEdible(newedible);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("edible " + newobj.isEdible());
		
		s = s.trim();
		String newfoodpctperday = s.substring(0,s.indexOf("/"));
		
		f = Float.parseFloat(newfoodpctperday);
		newobj.setFoodPctPerDay(f);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("Food pct per day= " + newobj.getFoodPctPerDay());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newdrinkable = parseBool(t);
		
		newobj.setDrinkable(newdrinkable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Drinkable= " + newobj.isDrinkable());

		s = s.trim();
		String newfluidpctperday = s.substring(0,s.indexOf("/"));
		
		f = Float.parseFloat(newfluidpctperday);
		newobj.setFluidPctPerDay(f);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("Fluid pct per day= " + newobj.getFluidPctPerDay());

		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newhealable = parseBool(t);
		
		newobj.setHealable(newhealable);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Healable= " + newobj.isHealable());

		s = s.trim();
		String newmedpctperday = s.substring(0,s.indexOf("/"));
		
		f = Float.parseFloat(newmedpctperday);
		newobj.setMedicinePctPerDay(f);
		s = s.substring(s.indexOf("/")+1);

		//System.out.println("Medicine pct per day= " + newobj.getMedicinePctPerDay());		


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newelvish = parseBool(t);
		
		newobj.setElvish(newelvish);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Elvish= " + newobj.isElvish());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		boolean newpoison = parseBool(t);
		
		newobj.setPoison(newpoison);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("Poison= " + newobj.isPoison());


		s = s.trim();
		t = s.substring(0,s.indexOf("/"));
		
		Float newenergyimprovement = Float.parseFloat(t);
		newobj.setEnergyImprovement(newenergyimprovement);
		//System.out.println("Energy Improvement= " + newobj.getEnergyImprovement());

		s = s.trim();
		t = s.substring(s.indexOf("/")+1,s.indexOf("/")+2);
	
		int newfoodtype = Integer.parseInt(t);
		newobj.setFoodType(newfoodtype);
		//System.out.println("food type = " + newobj.getFoodType());
	
		FiniteList.add(newobj);
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

	public final float getWeight(){
		return weight;
	}

	public final boolean isEdible(){
		return edible;
	}

	public final float getFoodPctPerDay(){
		return foodpctperday;
	}
	
	public final boolean isDrinkable(){
		return drinkable;
	}
	
	public final float getFluidPctPerDay(){
		return fluidpctperday;
	}


	public final boolean isHealable(){
		return healable;
	}

	public final float getMedicinePctPerDay(){
		return medicinepctperday;
	}

		
	public final boolean isElvish(){
		return elvish;
	}

	public final boolean isPoison(){
		return poison;
	}
	public final float getEnergyImprovement(){
		return energyimprovement;
	}

	public final int getFoodType(){
		return foodtype;
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

	public final void setEdible(boolean newedible){
		edible = newedible;
	}
	
	public final void setFoodPctPerDay(float newfoodpctperday){
		foodpctperday = newfoodpctperday;
	}

	public final void setDrinkable(boolean newdrinkable){
		drinkable = newdrinkable;
	}

	public final void setFluidPctPerDay(float newfluidpctperday){
		fluidpctperday = newfluidpctperday;
	}

	
	public final void setHealable(boolean newhealable){
		healable = newhealable;
	}

	public final void setMedicinePctPerDay(float newmedpctperday){
		medicinepctperday = newmedpctperday;
	}


	public final void setElvish(boolean newelvish){
		elvish = newelvish;
	}

	public final void setPoison(boolean newpoison){
		poison = newpoison;
	}

	public final void setEnergyImprovement(float newenergyimprovement){
		energyimprovement = newenergyimprovement;
	}

	public final void setFoodType(int newfoodtype){
		foodtype = newfoodtype;
	}

	//Determines whether String should have "a" or "an"
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

	public String toString() {
		String s = "Finite: Index= " + index + " name= " + name +  " Description: " +
			   description + " Weight: " + weight + " Edible: " + edible + 
			   " Food % per day: " + foodpctperday + " Drinkable: "  + drinkable +  " Fluid % per day: " +
			   fluidpctperday + " Healable: " + healable + " Medicine % per day: " + medicinepctperday + 
                           " Elvish: " + elvish + " Poison: " + poison +  " Energy Improvement: " + energyimprovement;

		return s;
	}

}
		