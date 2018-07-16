import java.io.*;
import java.util.*;

// Object which describes all player status.  Designed to instantiate only one object, however, does not
// prohibit instantiating multiple objects
// All methods are final - not designed for inheritance

public class PlayerStatus {

	private List<MovableType> carrying; // List of Movable objects that player is carrying
	private List<MovableType> holding; // List of Movable objects that player is holding (max of two, since two hands!)
	private float fluid; // fluid percentage 0 - 100
	private float fluidmaxweight; // not used
	private float fluidpctperday; // how much fluid is consumed per day
	private float food; // food percentage 0 - 100
	private float foodmaxweight; // not used
	private float foodpctperday; // how much food is consumed per day
	private float medicine; // medicine percentage
	private float medicinemaxweight; // not used
	private float medicinepctperday; // how much medicine is consumed per day
	private boolean wearingring; // is player wearing ring?
	private boolean wearingcoat; // is wearing coat?
	private boolean wearingcloak; // is wearing cloak?
	private boolean wearingmail; // is wearing mail?
	private boolean wearingbelt; // is wearing belt?
	private boolean wearinghelm; // is wearing helmet?
	private boolean wearingjerkin; // is wearing jerkin?
	private boolean wearinghood; // is wearing hood?
        private boolean wearingarmor; // is wearing armor?
	private int warmth; // cumulative warmth from clothing and drink
	private boolean inaboat; // is player in a boat?
	private boolean onahorse; // is player on horseback?
	private boolean horsefound; // has player ever seen a horse on his travels?
	private boolean hasrope; // does player have rope?
	private float energy; // energy 0 - 100
	private float injury; // injury 0 - 100
	private float fightexperience; // player gains fight experience at every fight
	private int numberofshots; // records the number of times the player has shot an arrow
 	private int numberofthrows; // records the number of times the player has thrown a spear
	private float dayselapsed; // days elapsed in journey
	private float weight; // total weight 
	private boolean alreadyate; // player can only eat once per visit to location (but can take food for later)
	private boolean alreadydrank; // player can only drink once per visit to location (but can take drink for later)
	private int arrows; // number of arrows (max is 4)
	private boolean knowsaboutarrows; // player cannot know about existence of arrows until first sees arrows
	private float dayswearingring; // cumulative days player has worn the ring (i.e. bad things happen if this gets too high)
	private boolean spell; // has the player invoked a valid spell for this location?
	private int lastloc; // last location that a player was at
	private boolean blewhorn; // blew a horn at this location
	private float carbs; // player's carbohydrates from grain, starch
	private float vitamins; // player's vitamins and fibers from fruits or vegetables
	private float proteins; // player's proteins from meats
	private float fats; // player's fats from dairy products
	private int foodcarryingtype; // type of food player is carrying
	private int drinkcarryingtype; // type of drink player is carrying
	private boolean carbwarn, vitaminwarn, proteinwarn, fatswarn; // I/O flags for food type warnings 
	private boolean littlehungrywarn, veryhungrywarn, nofoodwarn; // I/O flags for food type warnings
	private boolean littlethirstywarn, verythirstywarn, nowaterwarn; // I/O flags for food type warnings
	private boolean fatiguewarn, lowenergywarn, veryweakwarn; // I/O flags for food type warnings
	private boolean moveattempt; // set to true is valid move attempt has been made, otherwise false
	
	// the below constants should be adjusted if game is too hard or too easy

	private final float WEIGHT_CONSTANT = 0.05F;        //increase to make harder
	private final float ENERGY_CONSTANT = 1.0F;         //increase to make harder
    private final float START_ENERGY = 90.0F;           //decrease to make harder
	private final float COLD_CONSTANT = 0.1F;           //increase to make harder
	private final float HOT_CONSTANT = 0.1F;            //increase to make harder
	private final float FOOD_CONSTANT = 1.0F;           //increase to make harder
    private final float FOODPCTPERDAY_CONSTANT = 0.008F; //increase to make harder
	private final float START_FOOD = 55.0F;             //decrease to make harder
	private final float FLUID_CONSTANT = 1.0F;          //increase to make harder
    private final float FLUIDPCTPERDAY_CONSTANT = 0.008F; //increase to make harder
	private final float START_FLUID = 55.0F;            //decrease to make harder
	private final float INJURY_CONSTANT = 1.0F;         //decrease to make harder
	private final float POISON_CONSTANT = 0.1F;         //increase to make harder
	private final float PHIT_CONSTANT = 1.0F;           //increase to make harder
	private final float PINJ_HOOD_CONSTANT = 30.0F;     //decrease to make harder
	private final float PINJ_MAIL_CONSTANT = 30.0F;     //decrease to make harder
	private final float PINJ_ARMOR_CONSTANT = 50.0F;    //decrease to make harder
    private final float PINJ_HELM_CONSTANT = 15.0F;     //decrease to make harder
	private final float PINJ_JERKIN_CONSTANT = 10.0F;   //decrease to make harder
	private final float PINJ_CLOAK_CONSTANT = 10.0F;    //decrease to make harder
	private final float PINJ_COAT_CONSTANT = 5.0F;      //decrease to make harder
	private final float PINJ_SHIELD_CONSTANT = 50.0F;   //decrease to make harder
	private final float MAX_INJURY = 100.0F;            //decrease to make harder	
	private final float HORSE_CONSTANT = 0.25F;         //increase to make harder 
	private final float NUTRITION_CONSTANT = 30.0F;     //decrease to make harder
	private final float MALNUTRITION_CONSTANT = 0.3F;   //increase to make harder
	private final float HORN_CONSTANT = 0.25F;          //increase to make harder
	private final float CLIMB_CONSTANT = 100.0F;        //decrease to make harder

	PlayerStatus() {

	carrying = new ArrayList<MovableType>();
	holding = new ArrayList<MovableType>();
	wearingring = false;
	wearinghood = false;
	wearingcoat = false;
	wearingcloak = false;
	wearingmail = false;
	wearingbelt = false;
	wearinghelm = false;
	wearingjerkin = false;
        wearingarmor = false;
	fluid = START_FLUID;
	fluidmaxweight = 0;
	fluidpctperday = 0;
	food = START_FOOD;
	foodmaxweight = 0;
	foodpctperday = 0;
	medicine = 0;
	medicinemaxweight = 0;
	medicinepctperday = 0;
	inaboat = false;
	onahorse = false;
	horsefound = false;
	warmth = 0;
	energy = START_ENERGY;
	injury = 0;
	fightexperience = 0;
	numberofshots = 0;
	numberofthrows = 0;
	dayselapsed = 0;
	weight = 0;
	alreadyate = false;
	alreadydrank = false;
	arrows = 0;
	knowsaboutarrows = false;
	dayswearingring = 0;
	spell = false;
	lastloc = -1;
	blewhorn = false;
	foodcarryingtype  = 0;
	drinkcarryingtype = 0;
	carbs = NUTRITION_CONSTANT;
	vitamins = NUTRITION_CONSTANT;
	proteins = NUTRITION_CONSTANT;
	fats = NUTRITION_CONSTANT;
	carbwarn = true;
	vitaminwarn = true;
	proteinwarn = true;
	fatswarn = true;
	littlehungrywarn = true;
	veryhungrywarn = true;
	nofoodwarn = true;
	littlethirstywarn = true;
	verythirstywarn = true;
	nowaterwarn = true;
	fatiguewarn = true;
	lowenergywarn = true;
	veryweakwarn = true;
	moveattempt = false;
	}

	// Basic status update - this is called upon each move to a new location, and gets input from the 
	// Movement object.

	public final void updateStatus(Movement movePtr, int temperature){
			
		float horsefactor = 1.0F;
		if (this.isOnHorse())
			 horsefactor = HORSE_CONSTANT;

		float timeconstant = movePtr.getTime() * horsefactor;

		dayselapsed = dayselapsed + timeconstant;
		if (wearingring) dayswearingring = dayswearingring + timeconstant;

		if (food == 0 ) {
			energy = energy - (movePtr.getEnergy() * timeconstant + dayswearingring);
			if (nofoodwarn) {
				System.out.println("You have no food - you are starving.");
				nofoodwarn = false; }
			}

		if ((food < 25.0) && (food > 0)) {
			energy = energy - (movePtr.getEnergy() * timeconstant/2 + dayswearingring/2) ;
			if (veryhungrywarn) {
				System.out.println("You are very hungry.");
				veryhungrywarn = false; }
			}

		if ((food < 50.0) && (food >= 25)) {
			energy = energy - (movePtr.getEnergy() * timeconstant/4  + dayswearingring/4);
			if (littlehungrywarn) {
				System.out.println("You are starting to get hungry.");
				littlehungrywarn = false; }
			}

		if (food > 75) { energy = energy + timeconstant - dayswearingring/4 ;
				 littlehungrywarn = true;
				 veryhungrywarn = true;
				 nofoodwarn = true;
			       }

		if (fluid == 0 ) {
			energy = energy - (movePtr.getEnergy() * timeconstant + dayswearingring);
			if (nowaterwarn) {
				System.out.println("You have no water and are dehydrated."); 
				nowaterwarn = false; }
			}	

		if ((fluid < 25.0) && (fluid > 0)) {
			energy = energy - (movePtr.getEnergy() * timeconstant/2 + dayswearingring/2) ;
			if (verythirstywarn) {
				System.out.println("You are very thirsty.");
				verythirstywarn = false;  }
			}

		if ((fluid < 50.0) && (fluid >= 25)) {
			energy = energy - (movePtr.getEnergy() * timeconstant/4 + dayswearingring/4) ;
			if (littlethirstywarn) {
				System.out.println("You are a little thirsty.");
				littlethirstywarn = false; }
			}

		if (fluid > 75){ energy = energy + timeconstant - dayswearingring/4;
				 littlethirstywarn = true;
				 verythirstywarn = true;
				 nowaterwarn = true;
			       }
		
		if (temperature + warmth < 15 ) {
			 energy = energy - energy * COLD_CONSTANT;
			 System.out.println("You are feeling cold."); }

		if (warmth + temperature > 30) {
			energy = energy - energy * HOT_CONSTANT;
			System.out.println("You are feeling hot."); }

		if (energy > 100) { energy = 100.0F;
				    fatiguewarn = true;
				    lowenergywarn = true;
				    veryweakwarn = true;
				  }

		if ((energy < 50) && (energy >= 25)) 
			if (fatiguewarn) {
				System.out.println("You are starting to feel fatigued.");
				fatiguewarn = false;
			}

		if ((energy < 25) && (energy > 0)) 
			if (lowenergywarn) {
				System.out.println("You are almost out of energy.");
				lowenergywarn = false;
			}
	
		if (energy < 0) {
			energy  = 0.0F;
			if (veryweakwarn) {
				System.out.println("You out of energy and feeling very weak.");
				veryweakwarn = false; }
			}

		fluid = fluid - (fluidpctperday*FLUIDPCTPERDAY_CONSTANT * timeconstant + (weight + foodmaxweight * food * FLUIDPCTPERDAY_CONSTANT + fluidmaxweight * fluid * FLUIDPCTPERDAY_CONSTANT) * WEIGHT_CONSTANT
			 + injury * INJURY_CONSTANT);
 		food = food - (foodpctperday*FOODPCTPERDAY_CONSTANT * timeconstant + (weight + foodmaxweight * food * FOODPCTPERDAY_CONSTANT + fluidmaxweight * fluid * FLUIDPCTPERDAY_CONSTANT ) * WEIGHT_CONSTANT
			 + injury * INJURY_CONSTANT);
		if (food < 0) food = 0.0F;
		if (fluid < 0) fluid = 0.0F;
		
		if (injury > 0) {

			listInjury(injury);
			if (medicine > 0){
				injury = injury - medicinepctperday * timeconstant;
				medicine = medicine - medicinepctperday * timeconstant; }

   			else injury = injury - INJURY_CONSTANT * timeconstant;
		}
	
		if (injury < 0) injury = 0.0F;
		if (medicine < 0) medicine = 0.0F;
		
		if (food > 0)
			switch(foodcarryingtype) {
				case (1) : carbs = NUTRITION_CONSTANT;
				break;
				case (2) : vitamins = NUTRITION_CONSTANT;
				break;
				case (3) : proteins = NUTRITION_CONSTANT;
				break;
				case (4) : fats = NUTRITION_CONSTANT;
				break;
				default:
				break;
			}

		if (fluid > 0)
			switch(drinkcarryingtype) {
				case (1) : carbs = NUTRITION_CONSTANT;
				break;
				case (2) : vitamins = NUTRITION_CONSTANT;
				break;
				default:
				break;
			}
				
		carbs = carbs - timeconstant;
		vitamins = vitamins - timeconstant;
		proteins = proteins - timeconstant;
		fats = fats - timeconstant;
		
		if (carbs <= 0)
			 carbs = 0.0F;
		else
			 carbwarn = true;

		if (vitamins <= 0)
			 vitamins = 0.0F;
		else
			 vitaminwarn = true;
	
		if (proteins <= 0)
			 proteins = 0.0F;
		else
			 proteinwarn = true;
	
		if (fats <= 0)
			 fats = 0.0F;
		else
			 fatswarn = true;

		if (carbs == 0) {
			if (carbwarn) {
				System.out.println("You are not eating enough grains or starchy foods.");
				carbwarn = false; }
			energy = energy - timeconstant * MALNUTRITION_CONSTANT; }

		if (vitamins == 0) {
			if (vitaminwarn) {
				System.out.println("You are not eating enough fruits and vegetables.");
				vitaminwarn = false; }
			energy = energy - timeconstant * MALNUTRITION_CONSTANT; }

		if (proteins == 0) {
			if (proteinwarn) {	
				System.out.println("You are not eating meats and proteins.");
				proteinwarn = false; }
			energy = energy - timeconstant * MALNUTRITION_CONSTANT; }

		if (fats == 0) {
			if (fatswarn) {	
				System.out.println("You are not eating enough dairy products.");
				fatswarn = false; }
			energy = energy - timeconstant * MALNUTRITION_CONSTANT; }

		alreadyate = false;
		alreadydrank = false;
		spell = false;
		blewhorn = false;

	}

	// Returns true if the player has arrived at a new location; otherwise returns false	

	public final boolean firstVisit(int currentloc) {
		boolean result = (currentloc != lastloc);
		this.lastloc = currentloc;
		return result;
	}

	// Returns true if player has enough energy to complete the arduous task, given energy, weight, and injury

	public final boolean hasEnoughEnergy() {
		return (weight + (100.0 - energy) + injury) > CLIMB_CONSTANT ? false : true ;
	}
		
	// Given an enemy "target," and player's "weapon," compute the results of combat between player
	// and enemy.  Updates the status of player and enemy.
	// If player dies during combat, returns "true", otherwise returns "false."

	public final boolean enemyAction(AnimateType targetobj, MovableType weaponobj){
		
		boolean dead = false;
		float Phit = 0.0F;
		float Pinj = 0.0F;
		float ringfactor = (wearingring && !targetobj.isElvish()) ? 50.F : 0.0F;
		
		if (weaponobj.isRanged())
			if (!targetobj.isRanged())
				System.out.println("The " + targetobj.getName() + " cannot reach you with his " + targetobj.getWeaponName() + ".");
			else if (targetobj.getName().matches("nazgul") && wearingring) Phit = 100.0F;
			       else Phit  = (targetobj.getLethality() - targetobj.getInjury())*PHIT_CONSTANT + weight + injury
					 - fightexperience - energy - ringfactor;
		else if (targetobj.getName().matches("nazgul") && wearingring) Phit = 100.0F;
			       else Phit  = (targetobj.getLethality() - targetobj.getInjury())*PHIT_CONSTANT + weight/4 + injury/4
					 - fightexperience - energy - ringfactor;

		if (this.didBlowHorn())	Phit = Phit - Phit * HORN_CONSTANT;

		if (Math.random()<Phit) {System.out.println("The " + targetobj.getName() + " has hit you with his " + targetobj.getWeaponName() + ".");
				 Pinj = targetobj.getLethality() - targetobj.getInjury() - fightexperience - energy;
				 if (wearinghelm) Pinj = Pinj - PINJ_HELM_CONSTANT;
				 if (wearinghelm) Pinj = Pinj - PINJ_HELM_CONSTANT;
				 if (wearinghood) Pinj = Pinj - PINJ_HOOD_CONSTANT;
				 if (wearingcloak) Pinj = Pinj - PINJ_CLOAK_CONSTANT;
				 if (wearingcoat) Pinj = Pinj - PINJ_COAT_CONSTANT;
				 if (wearingjerkin) Pinj = Pinj - PINJ_JERKIN_CONSTANT;
				 if (wearingarmor) Pinj = Pinj - PINJ_ARMOR_CONSTANT;	
				 if (Pinj < 0) Pinj = 0;
				 injury = injury + Pinj;
				 listInjury(injury); }
		else System.out.println("The " + targetobj.getName() + " was not able to hurt you.");
		return (injury > MAX_INJURY);
	}
				                             
	// Executes action to "put on" a movable object.  Player must be carrying a 
	// movable object which is capable of being "worn".
	// Returns true if object was "put on," otherwise returns false.
  
	public final boolean putOn(MovableType newobj){

		boolean success = false;
		if (isCarrying(newobj)){
			switch(newobj.getName()){
				case "ring" : wearingring = true;
					      success = true;
				break;
			
				case "coat" : wearingcoat = true;
					      success = true;
				break;

				case "cloak" : wearingcloak = true;
					       success = true;
				break;

				case "mail" : wearingmail = true;
					      success = true;
				break;

				case "belt" : wearingbelt = true;
					      success = true;
				break;

				case "helm" : wearinghelm = true;
					      success = true;
				break;

				case "hood" : wearinghood = true;
					      success = true;
				break;

				case "jerkin" : wearingjerkin = true;
					      success = true;
				break;

				case "armor" : wearingarmor = true;
					      success = true;
				break;

				default : System.out.println("I don't understand what you mean.");
				break;
			}
			if (success) {  warmth = warmth + (int)newobj.getWarmth();
					System.out.println("You are now wearing the " + newobj.getName() + ".");}

		} else System.out.println("You are not carrying any " + newobj.getName() + ".");
	return success;
	}
		

	public final void setArrows(int newarrows){
		arrows = newarrows;
	}

	public final void setSpell(boolean newspell){
		spell = newspell;
	}

	public final void setWarmth(int newwarmth){
		warmth = newwarmth;
	}

	public final void setHorseFound(boolean newhorsefound){
		horsefound = newhorsefound;
	}

	public final void setBlewHorn(boolean newblowhorn){
		blewhorn = newblowhorn;
	}

	public final void setMoveAttempt(boolean newmoveattempt){
		moveattempt = newmoveattempt;
	}
	
	// Adds a movable object to the List of movable objects that the player is carrying
	// Returns "true" is object is added, otherwise returns "false"

	public final boolean addCarrying(MovableType newobj){

		boolean success = false;
		if ((newobj.getWeight() + weight) > 100){
			System.out.println("You cannot carry the " +
		        newobj.getName() + " until you drop something you are already carrying.");}
		else {
			carrying.add(newobj);
			weight = weight + newobj.getWeight();
			//System.out.println("Obj weight is: " + newobj.getWeight() + ".");
			System.out.println("You are now carrying the " + newobj.getName() + ".");
			success = true; }
		return success;
		     
	}


	// Adds a movable object to the List of movable objects that the player is holding
	// Returns "true" is object is added, otherwise returns "false"

	public final boolean addHolding(MovableType newobj){

		//System.out.println(newobj.toString());
		boolean success = false;
		if (!newobj.isHoldable())
			System.out.println("You cannot hold the " + newobj.getName() + ".");
		
			else if (this.isHolding(newobj)) System.out.println("You are already holding the " + newobj.getName() + ".");			
			else if (holding.size() == 2) System.out.println("You are already holding two objects.");
			else {
				holding.add(newobj);
				System.out.println("You are now holding the " + newobj.getName() + ".");
				success = true; }
		return success;
		     
	}

	// Executes the eating of food, if FiniteType is edible

	public final void eatFood(FiniteType obj){
		
		if (obj.isEdible())
			if (obj.isPoison()) {
				System.out.println("The " + obj.getName() + " is poisonous.  It has made you sick and weak.");
				energy = energy - energy * POISON_CONSTANT;
				//injury = injury + 20;
				} 
					
				else if (!alreadyate){
					energy = energy + obj.getEnergyImprovement();
					if (obj.isHealable()) injury = injury / 2;
					alreadyate = true;

					switch (obj.getFoodType()) {
						case (1): carbs = NUTRITION_CONSTANT;
						break;
						case (2): vitamins = NUTRITION_CONSTANT;
						break;
						case (3): proteins = NUTRITION_CONSTANT;
						break;
						case (4): fats = NUTRITION_CONSTANT;
						break;
						default:
						break;
					}

					System.out.println("You have eaten the " + obj.getName() + ".");
					littlehungrywarn = true;
					veryhungrywarn = true;
					nofoodwarn = true; }

				else System.out.println("You cannot eat anymore.");
		else System.out.println("You cannot eat the " + obj.getName() + ".");
	}

	// Executes the drinking of fluid, if FiniteType is drinkable

	public final void drinkFluid(FiniteType obj){
		
		if (obj.isDrinkable())
			if (obj.isPoison()) {
				System.out.println("The " + obj.getName() + " is poisonous.  It has made you sick and weak.");
				energy = energy - energy * POISON_CONSTANT;
				//injury = injury + 20;
				} 
			
				else if (!alreadydrank){
					energy = energy + obj.getEnergyImprovement();
					if (obj.isHealable()) injury = injury / 2;
					alreadydrank = true;
					switch (obj.getFoodType()) {
						case (1): carbs = NUTRITION_CONSTANT;
						break;
						case (2): vitamins = NUTRITION_CONSTANT;
						break;
						case (3): proteins = NUTRITION_CONSTANT;
						break;
						case (4): fats = NUTRITION_CONSTANT;
						break;
						default:
						break;
					}

					System.out.println("You have drunk the " + obj.getName() + ".");
					littlethirstywarn = true;
				        verythirstywarn = true;
					nowaterwarn = true; }

				else System.out.println("You cannot drink anymore.");
		else System.out.println("You cannot drink the " + obj.getName() + ".");
	}


	// Adds objects of FiniteType to the player's status.  Since only movable objects can be
	// "carried" in the List "carrying", Objects of FiniteType which are addable are input
	// into other fields of the player's status, such as "food","arrows", etc.

	public final void addFinite(FiniteType obj){

		if (obj.isEdible()){

			if (obj.isPoison()) {
				System.out.println("The " + obj.getName() + " is poisonous.  It has made you sick and weak.");
				energy = energy - energy * POISON_CONSTANT;
				//injury = injury + 20;
				} 
			else {
				foodmaxweight = obj.getWeight();
				food = 100.0F;
				foodpctperday = obj.getFoodPctPerDay();
				energy = energy + obj.getEnergyImprovement();
				if (energy > 100) energy = 100.0F;
				if (obj.isHealable()) injury = injury / 2;
				foodcarryingtype = obj.getFoodType();
				switch (obj.getFoodType()) {
					case (1): carbs = NUTRITION_CONSTANT;
					break;
					case (2): vitamins = NUTRITION_CONSTANT;
					break;
					case (3): proteins = NUTRITION_CONSTANT;
					break;
					case (4): fats = NUTRITION_CONSTANT;
					break;
					default:
					break;
				}

				System.out.println("You have replenished your food supply with " + obj.getName() + "."); }
		}
		else if (obj.isDrinkable()){
			if (obj.isPoison()) {
				System.out.println("The " + obj.getName() + " is poisonous.  It has made you sick and weak.");
				energy = energy - energy * POISON_CONSTANT;
				//injury = injury + 20;
				} 		
			else {
				fluidmaxweight = obj.getWeight();
				fluid = 100.0F;
				fluidpctperday = obj.getFluidPctPerDay();
				energy = energy + obj.getEnergyImprovement();
				if (energy > 100) energy = 100.0F;
				if (obj.isHealable()) injury = injury / 2;
				drinkcarryingtype = obj.getFoodType();
				switch (obj.getFoodType()) {
					case (1): carbs = NUTRITION_CONSTANT;
					break;
					case (2): vitamins = NUTRITION_CONSTANT;
					break;
					case (3): proteins = NUTRITION_CONSTANT;
					break;
					case (4): fats = NUTRITION_CONSTANT;
					break;
					default:
					break;
				}

				System.out.println("You have replenished your food supply with " + obj.getName() + "."); }
		}

		else if (obj.isHealable()){
			medicinemaxweight = obj.getWeight();
			medicine = 100.0F;
			medicinepctperday = obj.getMedicinePctPerDay();
			energy = energy + obj.getEnergyImprovement();
			if (energy > 100) energy = 100.0F;
			System.out.println("The " + obj.getName() + " has healing properties.");
		}

		else if (obj.getName().matches("arrow") || obj.getName().matches("arrows")){
			 knowsaboutarrows = true;
			 arrows = 4;
			 System.out.println("Your quiver is now full of arrows.");
		}

		else if (obj.getName().matches("rope")){
			hasrope = true;
			System.out.println("You now have rope.");
		}
	}
		

	public final void getInBoat(boolean newinaboat){
		inaboat = newinaboat;
	}

	public final void getOntoHorse(boolean newonahorse){
		onahorse = newonahorse;
	}

	public final int getWarmth(){
		return warmth;
	}

	public final boolean didBlowHorn(){
		return blewhorn;
	}

	public final boolean getMoveAttempt(){
		return moveattempt;
	}

	public final boolean isInBoat(){
		return inaboat;
	}
	
	public final boolean isOnHorse(){
		return onahorse;
	}

	public final boolean isWearingRing(){
		return wearingring;
	}

	// Executes "taking off" an object
	// Returns "true" if Object is "taken off," otherwise returns "false" 

	public final boolean takeOff(String s){

		boolean removed = false;
		switch (s){

			case "ring" : if (wearingring){
					removed = true;
					wearingring = false;}
			break;

			case "coat" : if (wearingcoat){
					removed = true;
					wearingcoat = false;}
			break;


			case "cloak" : if (wearingcloak){
					removed = true;
					wearingcloak = false;}
			break;


			case "belt" : if (wearingbelt){
					removed = true;
					wearingbelt = false;}
			break;


			case "helm" : if (wearinghelm){
					removed = true;
					wearinghelm = false;}
			break;

			case "mail" : if (wearingmail){
					removed = true;
					wearingmail = false;}
			break;

			case "jerkin" : if (wearingjerkin){
					removed = true;
					wearingjerkin = false;}
			break;

			case "armor" : if (wearingarmor){
					removed = true;
					wearingarmor = false;}
			break;

			default:
			break;
		}
		return removed;
	}
		
	// Executes removing a movable object from what player is carrying.
	// If object removed, returns "true" otherwise returns "false"

	public final boolean removeCarrying(MovableType newobj){
		if (this.isCarrying(newobj)){
			carrying.remove(newobj);
			holding.remove(newobj);
			this.takeOff(newobj.getName());
			weight = weight - newobj.getWeight();
			System.out.println("You have just dropped the " + newobj.getName() + ".");
			return true;}
		else {
		   System.out.println("You are not carrying the " + newobj.getName() + ".");
		   return false;}
	}


	// Executes returning a movable object from what player is holding.
	// If object returned, returns "true" otherwise returns "false"

	public final boolean returnHolding(MovableType newobj){
		if (this.isHolding(newobj)){
			holding.remove(newobj);
			System.out.println("You have just returned the " + newobj.getName() + ".");
			return true;}
		else {
		   System.out.println("You are not holding the " + newobj.getName() + ".");
		   return false;}
	}

	
	// Executes "throwing" an object.  Note that any movable object can be "thrown," although effect
	// is only calculated for throwable weapons.

	public final boolean throwAt(MovableType weaponobj){
		if (this.isCarrying(weaponobj)){
			carrying.remove(weaponobj);
			holding.remove(weaponobj);
			this.takeOff(weaponobj.getName());
			weight = weight - weaponobj.getWeight();
			System.out.println("You have just thrown the " + weaponobj.getName() + ".");
			return true;}
		else {
		   System.out.println("You are not carrying the " + weaponobj.getName() + ".");
		   return false;}
	}


	// Given name of object s, returns Movable Object with all fields of the object.
	// Can return null - responsibility of client to check

	public final MovableType returnMovableObject(String s){

		MovableType returnobj = null;
		for (int i = 0; i<this.carrying.size(); i++)
			if (this.carrying.get(i).getName().matches(s))
					returnobj = this.carrying.get(i);			
		return returnobj;
	}


	// returns true if any of the items being carried are weapons; otherwise returns false

	public final boolean hasWeapon(){
		boolean result = false;
		for (int i = 0; i<this.carrying.size(); i++)
			if (returnMovableObject(this.carrying.get(i).getName()).isWeapon())
				result = true;
		return result;
	}

	// returns the name of the first weapon being carried.
	// Warning: should only be called after verifying that a weapon is carried by calling 
	// hasWeapon; otherwise no exception will be thrown but null string will be returned
			
	public final String firstWeapon(){
		for (int i = 0; i<this.carrying.size(); i++)
			if (returnMovableObject(this.carrying.get(i).getName()).isWeapon())
				return this.carrying.get(i).getName();
		return "";  // should never get here
	}

	public final boolean isHit(MovableType weaponobj, AnimateType targetobj, Location location){
		
		float Phit = 0.0F;
		float hitarea = location.isLargeArea()? 0.0F : 20.0F;
		if (weaponobj.getName().matches("bow")){
			Phit = targetobj.getInjury()-targetobj.getLethality()/5+hitarea+numberofshots*3+fightexperience+(this.energy/2)-(this.injury/10)-(this.weight/10);
			numberofshots = numberofshots + 1; }
		if (weaponobj.getName().matches("spear")|| weaponobj.getName().matches("knife") || weaponobj.getName().matches("axe")) {
			Phit = targetobj.getInjury()-targetobj.getLethality()/5+hitarea*2+numberofthrows*3+fightexperience+(this.energy/2)-(this.injury/10)-(this.weight/10);
			numberofthrows = numberofthrows + 1;}
		double hitprob = Math.random()*100;
		//System.out.println("Random prob = " + hitprob);
		//System.out.println("Phit = " + Phit);
		return (Phit > hitprob); 
	}	

	public final int getArrows(){
		return arrows;
	}

	public final float getWeight(){
		return weight;
	}

	public final float getInjury(){
		return injury;
	}

	public final float getEnergy(){
		return energy;
	}

	public final float getDaysElapsed(){
		return dayselapsed;
	}

	public final boolean hasRope(){
		return hasrope;
	}

	public final boolean hasSpell(){
		return spell;
	}

	// Returns true if the player is carrying the object referenced by index,
	// otherwise returns false

	public final boolean isCarrying(int index){
		boolean result = false;
		for (int i = 0; i<carrying.size(); i++){
			//System.out.println(carrying.get(i).getIndex());
			if (index == carrying.get(i).getIndex())
				result = true;
			}
		return result;
	}
	

	// Returns true if the player is holding the object referenced by index,
	// otherwise returns false

	public final boolean isHolding(int index){
		boolean result = false;
		for (int i = 0; i<holding.size(); i++){
			//System.out.println(holding.get(i).getIndex());
			if (index == holding.get(i).getIndex())
				result = true;
			}
		return result;
	}

	// Returns true if player has ever seen a horse on his travels; otherwise returns false
	
	public final boolean getHorseFound(){
		return horsefound;
	}

	// Returns true if the player is carrying the object referenced by newobj,
	// otherwise returns false

	public final boolean isCarrying(MovableType newobj){
		boolean result = false;
		for (int i = 0; i<carrying.size(); i++)
			if (newobj.getName().matches(carrying.get(i).getName()))
				result = true;
		return result;
	}

	// Returns true if the player is holding the object referenced by newobj,
	// otherwise returns false

	public final boolean isHolding(MovableType newobj){
		boolean result = false;
		for (int i = 0; i<holding.size(); i++)
			if (newobj.getName().matches(holding.get(i).getName()))
				result = true;
		return result;
	}

	// Returns true if player is carrying any object classified as "Elvish",
	// otherwise returns false

        public final boolean hasElvish(){
		boolean result = false;
		for (int i = 0; i<carrying.size(); i++)
			if (carrying.get(i).isElvish())
				result = true;
		return result;
	}


	// Returns the name of the first Object carried by the player that
	// is classified as Elvish.
	// Can return "", responsibility of client to check (hint: call "hasElvish()")

	public final String getElvish(){
		String s = "";
		for (int i = 0; i<carrying.size(); i++)
			if (carrying.get(i).isElvish())
				s = carrying.get(i).getName();
		return s;
	}

	// Returns true if player is carrying any object that glows blue in the presence of orcs,
	// otherwise returns false

        public final boolean hasGlowBlue(){
		boolean result = false;
		for (int i = 0; i<carrying.size(); i++)
			if (carrying.get(i).doesGlowBlue())
				result = true;
		return result;
	}

	// Returns true if player is carrying any object that is sharp (and can cut),
	// otherwise returns false

        public final boolean hasSharp(){
		boolean result = false;
		for (int i = 0; i<carrying.size(); i++)
			if (carrying.get(i).isSharp())
				result = true;
		return result;
	}

	// Returns the name of the first Object carried by the player that
	// glows blue in the presence of orcs.
	// Can return "", responsibility of client to check (hint: call "doesGlowBlue()")

	public final String getGlowBlue(){
		String s = "";
		for (int i = 0; i<carrying.size(); i++)
			if (carrying.get(i).doesGlowBlue())
				s = carrying.get(i).getName();
		return s;
	}

	// Lists the objects that the player is carrying

	public final void listCarrying(){

		System.out.print("You are carrying: ");
		for (int i = 0; i<carrying.size(); i++)
		
			System.out.print(carrying.get(i).getName() + ( i == carrying.size() - 1 ? "." : ", "));
                
		System.out.println();
		if (knowsaboutarrows)
			System.out.println("You have " + arrows + " arrow" + (arrows != 1 ? "s." : ".") );
	}

	// Lists the objects that the player is holding

	public final void listHolding(){

		System.out.print("You are holding: ");
		for (int i = 0; i<holding.size(); i++)
		
			System.out.print(holding.get(i).getName() + ( i == holding.size() - 1 ? "." : ", "));
                
		System.out.println();
		
	}

	public final void listWearing(){

		if (wearingring) System.out.println("You are wearing the ring.");
		if (wearinghood) System.out.println("You are wearing a hood.");
		if (wearingcoat) System.out.println("You are wearing a coat.");
		if (wearingcloak) System.out.println("You are wearing a cloak.");
		if (wearingmail) System.out.println("You are wearing mail.");
		if (wearingbelt) System.out.println("You are wearing a belt.");
		if (wearinghelm) System.out.println("You are wearing a helm.");
		if (wearingjerkin) System.out.println("You are wearing a jerkin.");
                if (wearingarmor) System.out.println("You are wearing armor.");
	}

	// Prints a master player status, consisting of objects carrying, what wearing, food, energy,
	// injury, days elapsed, etc.

	public final void listStatus(int temperature){
		listCarrying();
		listHolding();
		listWearing();
		if (hasrope) System.out.println("You have rope.");

		if (inaboat) System.out.println("You are currently in a boat.");
		if (onahorse) System.out.println("You are currently on a horse.");
		System.out.print("Your energy level is: "); System.out.format("%1$ 1.1f",energy); System.out.println("%."); 
		System.out.print("Your food reserve is: "); System.out.format("%1$ 1.1f",food); System.out.println("%.");
		System.out.print("Your fluid reserve is: "); System.out.format("%1$ 1.1f",fluid); System.out.println("%.");

		if (temperature + warmth < 15) System.out.println("You are feeling cold");
		if (warmth + temperature > 30) System.out.println("You are feeling hot");

		if (medicine > 0){
			System.out.print("Your medicine reserve is: "); System.out.format("%1$ 1.1f",medicine); System.out.println("%.");}

		System.out.format("%1$ 1.1f",dayselapsed); System.out.println(" days have elapsed on your quest.");

		String s;
		float totalweight = weight + foodmaxweight * food * 0.01F + fluidmaxweight * fluid * 0.01F;
		if (totalweight >80) s = "very heavy.";
		else if (totalweight > 60) s = "heavy.";
		else if (totalweight > 30) s = "moderate.";
		else s = "light.";
		System.out.println("Your load is " + s);
		System.out.print("Your weight is: "); System.out.format("%1$ 1.1f",totalweight); System.out.println("."); 

		listInjury(injury);		
	}

	public static final void listInjury(float myinjury){
		String s = "";
		if (myinjury > 80) s = "severly";
		else if (myinjury > 40) s = "moderately";
		else if (myinjury > 0) s = "slightly";
		else s = "not";
		System.out.println("You are " + s + " injured.");
	}		

}	
