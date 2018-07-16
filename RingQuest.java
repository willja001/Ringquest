import java.io.*;
import java.util.*;
		
public class RingQuest {

	
	public static String[] verbs = {"go","walk","hit","take","pick","get","put","kill","quit","exit","end",
					"fight", "run","look","speak","say", "talk", "throw", "release","drop", "climb","read",
					 "status","carrying", "holding", "energy", "health", "injury", "wear", "touch",
					"open","close","shut", "unlock", "eat", "drink", "attack", "shoot",
					"hold", "draw","raise", "lower", "return", "debug", "help", "hold", "blow", "hyper", "wearing",
					"listen", "cross"};

	public static String[] preps = {"to","from","onto","on", "under","over","into","through","with","using", "at", "around",
					"front","behind", "off", "in", "of", "away","back"};

	public static String[] directions = {"north","south","east","west","up","down","out", "in", "northeast", "southeast", "southwest",
					     "northwest", "n", "ne", "e", "se", "s", "sw", "w", "nw", "n", "u", "d", "i", "inside", "o", "outside"};


	public static String[] nouns = {
					"ale","animal","apples","arkenstone","armor","arrow","arrows","athelas","axe","baggins","balrog","bear","bed","beer",
					"belt", "berries","beth","blade","boat","bombadil","book","bow","brandy","brandybuck","bread","bridge","bruinen","butter","calm",
					"chair","cheese","cliff","cloak","club","coat","corsair","cram","cream","crickhollow","crown","dagger","dais","dates","door",
					"draught","dwarf","eagle","edro","egg","elbereth","elf","elrond","ent","fire","fireplace","food","friend",
					"frodo","fruit","galadriel","gate","ghost","goblin","grapes","grate","harp","hearth","helm","hobgoblin","honeycomb",
					"hood","horn","horse","inscription","jerkey","jerkin","key","kingsfoil","knife","ladder","lake","lammen", 
					"lasto","legolas","lembas","letter","liquid","mace","mail","man","mead","meat","mellon","merchant","merry","milk",
					"mirror","miruvor","monument","mushrooms","nazgul","note","oliphaunt","orc","palantir","phial","pipeweed",
					"ranger","ranger","ring","rivendell","river","rock","rope","sausage","scimitar","shield","sign","southron",
					"spear","spell","spider","stair","stairs","star","stone","stone","stream","sword","table","thranduil",
					"tomb","tree","troll","uruk","warg","warlock","watcher","water","web","well","wight","willow","window","wine","wizard",
					"wolf","wose","wraith","writing"};

	public static String[] movableobjs = {"arkenstone","armor","axe","belt","blade","bow","cloak", "coat","crown","dagger","egg","harp",
 						"helm","hood","horn","jerkin","key","knife","mail","phial","ring","rock","scimitar","shield","spear",
						"star","sword"};

	public static String[] immovableobjs = {"bed","boat","book","bridge","chair","cliff","dais","door","fire","fireplace","gate",
						"hearth","horn","inscription","ladder","lake","letter","mirror","monument","note","palantir",
						"river","sign","stair","stairs","stone","stream","table","table","tomb","tree","well","window",		
						"writing","grate","watcher","web"};

	public static String[] animateobjs = {"goblin","wizard","orc","troll","wraith","wight","balrog","elf", "nazgul","willow","ranger", "wolf",
                                              "dwarf","southron","warg", "wose","bear","spider","uruk","ent","hobgoblin","merchant", "ghost", "corsair",
					      "horse","eagle","oliphaunt","warlock"};

	public static String[] finiteobjs = {"apples","berries","dates","grapes", "food","water","miruvor","athelas","lembas","bread", "arrow","arrows","rope","cheese","sausage",
					     "ale","butter","mushrooms","honeycomb","beer", "kingsfoil","pipeweed","jerkey","mead","draught",
					     "cram","wine", "fruit","liquid","cream","milk","meat"};

	public static String[] spells = {"brandy", "brandybuck" , "frodo", "merry", "crickhollow", "baggins",
					 "tom","bombadil",
					 "elrond", "rivendell", "water", "bruinen", "elbereth", "calm" ,"lasto", "beth","lammen",
					 "mellon","edro","spell", "thranduil","legolas","friend","elbereth","galadriel","elrond","mellon","","",""};

	public static int[] spellindices = {0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4 , 4, 5, 5, 5, 6, 6, 6, 6, 7, 8, 9}; 
	// note - the number of indices must match the number of entries in spells, or an exception will be thrown
	
	public static void newString(List<Location> locationlist, List<MovableType> MovableList,
				     List<AnimateType> AnimateList, List<FiniteType> FiniteList,
				     List<ImmovableType> ImmovableList, List<InteractType> InteractList) throws IOException

	
   	{
      	InputStreamReader cin = null;
		Action newaction = new Action();
    try {
         	cin = new InputStreamReader(System.in);
        	int currentloc = 0;
		PlayerStatus mystatus = new PlayerStatus();
			
		char c;
		
		while (!newaction.getVerb().matches("exit") && (currentloc>=0)){
		currentLocation(locationlist.get(currentloc), currentloc, mystatus, MovableList);
		glowsBlue(locationlist, currentloc, mystatus, AnimateList);
		newaction.clearAction();
		String s = "";
		System.out.println("Please enter your command.");
         	do {
            		c = (char) cin.read();
		      	    s = s + String.valueOf(c);
            		//System.out.print(c); echo off
         	} while(c != '\n');
		parse(s, newaction);
		System.out.println("                                                                                                 ");
		//System.out.println(newaction.toString());
		currentloc = takeAction(newaction, currentloc, locationlist, mystatus, MovableList, AnimateList, FiniteList, ImmovableList, InteractList);

		} // while
		
		if (currentloc == -3)
			System.out.println("Congratulations! You have dropped the ring into the fires of Mount Doom.  The ring is destroyed and you have saved Middle Earth!");
		if (currentloc == -2) 
			System.out.println("You have died - too bad!");			
	}
	catch (IOException e) { throw new IOException("Exception thrown."); } 
      	finally {
         	if (cin != null) {
            	cin.close();
         	}
      	} // end finally
      //return s;
      }  // end getString()

//	while ((!newaction.getVerb().matches("quit"))&&(!newaction.getVerb().matches("exit"))&&(!newaction.getVerb().matches("end"))){

public static void currentLocation(Location location, int currentloc, PlayerStatus mystatus, List<MovableType> MovableList){
		if ((mystatus.firstVisit(currentloc)) || (mystatus.getMoveAttempt()) ) {
			System.out.println(location.getDescription());
			mystatus.setMoveAttempt(false);
		}	
		Location.lookAtObjs(location, mystatus);
		if (location.fearsHolding(mystatus, MovableList))
			mystatus.setSpell(true);	        
	}

public static void glowsBlue(List<Location> locationlist, int currentloc, PlayerStatus mystatus, List<AnimateType> AnimateList){
		Location.glowsBlue(locationlist, currentloc, mystatus, AnimateList);
	}

public static boolean isType(String [] t, String s){

	boolean result = false;
	for (int i = 0; i<t.length; i++)
		if (t[i].matches(s))
			result = true;
	return result;
	}

public static final boolean checkType(boolean istype, boolean hasobj, String s, String t){

		boolean result = false;
		if (result = istype)
			if (result = hasobj)
				System.out.println("You cannot " + t + " the " + s + ".");
			else 
				System.out.println("You do not see any " + s + ".");
		return result;
	}

public static void getPrepAction(Action newaction, Location location, PlayerStatus mystatus, List<AnimateType> AnimateList){
		
	if (newaction.getPrep().matches("in") || newaction.getPrep().matches("into"))
		if (newaction.getObjOfPrep().matches("boat"))
			if (location.hasBoat())
				if (mystatus.isOnHorse())
					System.out.println("You must first get off the horse.");	
				else {
					mystatus.getInBoat(true);
					System.out.println("You are now in the boat.");
				}
			
			else System.out.println("There is no boat here.");
		else System.out.println("I don't understand what you want to do.");
	else if (newaction.getPrep().matches("off")||newaction.getPrep().matches("of")){
		if (newaction.getObjOfPrep().matches("boat"))
			if (mystatus.isInBoat()){
				mystatus.getInBoat(false);
				System.out.println("You are out of the boat.");
			}
			else System.out.println("You are not in a boat.");
		if (newaction.getObjOfPrep().matches("horse"))
			if (mystatus.isOnHorse()){
				mystatus.getOntoHorse(false);
				location.addAnimate(AnimateType.returnAnimateObject("horse", AnimateList));
				System.out.println("You are now off the horse.");
			}
			else System.out.println("You are not on a horse.");
		}	
        else if (newaction.getPrep().matches("on") || newaction.getPrep().matches("onto"))
		if (newaction.getObjOfPrep().matches("horse"))
			if (location.hasHorse())
				if (mystatus.isInBoat())
					System.out.println("You must first get out of the boat.");
				else { 
					mystatus.getOntoHorse(true);
					location.removeAnimate(location.returnAnimateObject("horse"));
					System.out.println("You are now on the horse.");
				}
			else System.out.println("There is no horse here.");
	else if (newaction.getPrep().matches("down") || newaction.getPrep().matches("from"))
			if (mystatus.isOnHorse()){
				mystatus.getOntoHorse(false);
				location.addAnimate(AnimateType.returnAnimateObject("horse", AnimateList));
				System.out.println("You are now off the horse.");
			}
			else System.out.println("You are not on a horse.");

		else System.out.println("I don't understand what you want to do.");
				
	else System.out.println("I don't understand what you want to do.");
	}

public static void getAction(Action newaction, Location location, PlayerStatus mystatus, List<FiniteType> FiniteList,
					 List<MovableType> MovableList, List<AnimateType> AnimateList){

	if (newaction.getDobj().matches("arrows")) newaction.setDobj("arrow");

	if (!newaction.getPrep().matches(""))
		getPrepAction(newaction, location, mystatus, AnimateList);

	// special exception for horn, since horn can be immovable or movable

	else if (newaction.getDobj().matches("horn"))
		if (location.hasMovable("horn")) {
			MovableType locobj = location.returnMovableObject(newaction.getDobj());
			MovableType listobj = MovableType.returnMovableObject(locobj.getIndex(), MovableList);	
			if (mystatus.addCarrying(listobj)){
				location.takeMovable(locobj);
				listobj.makeNewEnemy(AnimateList);
			}
		else if (location.hasImmovable("horn")) System.out.println("You cannot take the horn."); }
		else System.out.println("You do not see any horn.");	
	// end special exception for horn
	
	else if (checkType(isType(immovableobjs, newaction.getDobj()), location.hasImmovable(newaction.getDobj()), newaction.getDobj(), "take"));	  

	else if (checkType(isType(animateobjs, newaction.getDobj()), location.hasAnimate(newaction.getDobj()), newaction.getDobj(), "take"));		
	
	else if (isType(finiteobjs, newaction.getDobj()))
		if (!location.hasFinite(newaction.getDobj()))
			System.out.println("You do not see any " + newaction.getDobj() + ".");
		else { FiniteType finiteobj = FiniteType.returnFiniteObject(newaction.getDobj(), location, FiniteList);
		       if (finiteobj != null) mystatus.addFinite(finiteobj);}

	
	else
		
		if (isType(movableobjs, newaction.getDobj()))
				if (!location.hasMovable(newaction.getDobj()))
					System.out.println("You do not see any " + newaction.getDobj() + ".");
	   			else { 
					MovableType locobj = location.returnMovableObject(newaction.getDobj());
					MovableType listobj = MovableType.returnMovableObject(locobj.getIndex(), MovableList);

					if (location.returnEnemy(AnimateList)!=-1)
					   System.out.println("The " + AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName() 
							      + " guards the " + listobj.getName() + ".");
					else
					if (mystatus.addCarrying(listobj)){
						location.takeMovable(locobj);
						listobj.makeNewEnemy(AnimateList);
					}
				}
	}

public static void talkAction(Action newaction, Location location, PlayerStatus mystatus, 
			      List<AnimateType> AnimateList, List<InteractType> InteractList){

	String talkname = "";
	String talkstring = "";

	if (newaction.getPrep().matches("to")||(!newaction.getInstrument().matches(""))){
		if (newaction.getPrep().matches("to")) 
			talkname = newaction.getObjOfPrep();
		else
			talkname = newaction.getInstrument();

		
		if (checkType(isType(immovableobjs, talkname), location.hasImmovable(talkname), talkname, "talk to"));

 		else if (checkType(isType(movableobjs, talkname), location.hasMovable(talkname), talkname, "talk to")); 
			
		else if (checkType(isType(finiteobjs, talkname), location.hasFinite(talkname), talkname, "talk to"));
			
		else if (isType(animateobjs, talkname))
			if (location.hasAnimate(talkname)){
				AnimateType talkobj = AnimateType.returnAnimateObject(talkname, AnimateList);
				int talkindex = location.getTalkIndex();
				//System.out.println("Talk index: " + talkindex);
				if (talkindex == -1)
					 System.out.println("The " + talkname + " has nothing to say.");
				else {
					 if (mystatus.isWearingRing())
						talkstring = InteractType.returnInteractObject(talkindex, InteractList).getRingInfo();
					 else if (talkobj.isEnemy()) 
						   if (talkobj.getInjury() > 0)
							talkstring = InteractType.returnInteractObject(talkindex, InteractList).getEnemyInfo2();
						   else
							talkstring = InteractType.returnInteractObject(talkindex, InteractList).getEnemyInfo1();
					 else talkstring = InteractType.returnInteractObject(talkindex, InteractList).getFriendInfo();
					 // end talkobj.isEnemy()
					
					 //System.out.println(InteractType.returnInteractObject(talkindex, InteractList).getEnemyInfo1());
					 //System.out.println(InteractType.returnInteractObject(talkindex, InteractList).getFriendInfo());
					 System.out.println(talkstring);	
				     } // end else

			}	 // end hasAnimate()

			else System.out.println("You do not see any " + talkname + ".");
		
		     else System.out.println("I dont understand what you want.");
		} // end newAction
	else System.out.println("I dont understand what you want.");
	} // end talkAction		
			

public static void holdAction(Action newaction, Location location, PlayerStatus mystatus, List<MovableType> MovableList,
				 List<ImmovableType> ImmovableList, List<AnimateType> AnimateList){

	if (!newaction.getPrep().matches(""))
		getPrepAction(newaction, location, mystatus, AnimateList);	

	else if (checkType(isType(immovableobjs, newaction.getDobj()), location.hasImmovable(newaction.getDobj()), newaction.getDobj(), "hold"));	  

	else if (checkType(isType(animateobjs, newaction.getDobj()), location.hasAnimate(newaction.getDobj()), newaction.getDobj(), "hold"));		
	
	else if (checkType(isType(finiteobjs, newaction.getDobj()), location.hasFinite(newaction.getDobj()), newaction.getDobj(), "hold"));		
	
	else
	
		if (isType(movableobjs, newaction.getDobj())) {
				MovableType holdobj = MovableType.returnMovableObject(newaction.getDobj(), MovableList);
			
				if (!mystatus.isCarrying(holdobj))
					System.out.println("You are not carrying any " + newaction.getDobj() + ".");
	   			else {
					mystatus.addHolding(holdobj);
					if (location.spellAction(mystatus, ImmovableList, MovableList)){
			 			mystatus.setSpell(true);
						System.out.println("You have cast a spell.");
					} // end if
				} // end else
						
		}
	}

public static void returnAction(Action newaction, Location location, PlayerStatus mystatus, List<MovableType> MovableList, List<AnimateType> AnimateList){

	if (!newaction.getPrep().matches(""))
		getPrepAction(newaction, location, mystatus, AnimateList);	

	else if (checkType(isType(immovableobjs, newaction.getDobj()), location.hasImmovable(newaction.getDobj()), newaction.getDobj(), "return"));	  

	else if (checkType(isType(animateobjs, newaction.getDobj()), location.hasAnimate(newaction.getDobj()), newaction.getDobj(), "return"));		
	
	else if (checkType(isType(finiteobjs, newaction.getDobj()), location.hasFinite(newaction.getDobj()), newaction.getDobj(), "return"));		
	
	else
	
		if (isType(movableobjs, newaction.getDobj())) {
				MovableType holdobj = MovableType.returnMovableObject(newaction.getDobj(), MovableList);
			
				if (!mystatus.isCarrying(holdobj)) System.out.println("You are not carrying any " + newaction.getDobj() + ".");
				else mystatus.returnHolding(holdobj);
						
		}
	}


public static void eatAction(Action newaction, Location location, PlayerStatus mystatus, List<FiniteType> FiniteList){

	  
	String dobj = newaction.getDobj();

	if (checkType(isType(immovableobjs, dobj), location.hasImmovable(dobj), dobj, "eat"));
		
	else if (checkType(isType(animateobjs, dobj), location.hasAnimate(dobj), dobj, "eat"));
	
	else if (checkType(isType(movableobjs, dobj), location.hasMovable(dobj), dobj, "eat"));

	else if (isType(finiteobjs, newaction.getDobj()))
		if (!location.hasFinite(newaction.getDobj()))
			System.out.println("You do not see any " + newaction.getDobj() + ".");
		else { FiniteType finiteobj = FiniteType.returnFiniteObject(newaction.getDobj(), location, FiniteList);
		       if (finiteobj != null) mystatus.eatFood(finiteobj);}
	}

public static void readAction(Action newaction, Location location, List<ImmovableType> ImmovableList){

	String dobj = newaction.getDobj();

	if (checkType(isType(finiteobjs, dobj), location.hasFinite(dobj), dobj, "read"));
		
	else if (checkType(isType(animateobjs, dobj), location.hasAnimate(dobj), dobj, "read"));
	
	else if (checkType(isType(movableobjs, dobj), location.hasMovable(dobj), dobj, "read"));
	
	else if (isType(immovableobjs, newaction.getDobj()))
		if (!location.hasImmovable(newaction.getDobj()))
			System.out.println("You cannot read the " + newaction.getDobj() + ".");
		else {
			ImmovableType readobj = ImmovableType.returnImmovableObject(location.getIndex(newaction.getDobj()), ImmovableList);
			if (readobj == null) System.out.println("There is nothing to read.");
				else if (!readobj.isReadable()) System.out.println("There is nothing to read.");
					else System.out.println("\"" + readobj.getInscription() + "\".");
		     }
	else System.out.println("I don't understand what you mean.");
		
	}

public static int crossAction(Action newaction, int currentloc, List<Location> locationlist, PlayerStatus mystatus,
			 List<ImmovableType> ImmovableList, List<AnimateType> AnimateList){

	String dobj = newaction.getDobj();
	Location location = locationlist.get(currentloc);
	int newloc = currentloc;

	if (checkType(isType(finiteobjs, dobj), location.hasFinite(dobj), dobj, "cross"));
		
	else if (checkType(isType(animateobjs, dobj), location.hasAnimate(dobj), dobj, "cross"));
	
	else if (checkType(isType(movableobjs, dobj), location.hasMovable(dobj), dobj, "cross"));
	
	else if (isType(immovableobjs, newaction.getDobj()))
		if (!location.hasImmovable(newaction.getDobj()))
			System.out.println("You cannot cross the " + newaction.getDobj() + ".");
		else {
			ImmovableType crossobj = ImmovableType.returnImmovableObject(location.getIndex(newaction.getDobj()), ImmovableList);
			if (crossobj == null) System.out.println("There is nothing to cross here.");
				else { //System.out.println(crossobj.toString());
				       if (!crossobj.isCrossable()) System.out.println("There is nothing to cross.");
				       else if (location.returnCrossDirection() == "")
                          System.out.println("You cannot cross the " + newaction.getDobj() + ".");
				          else {  
				            System.out.println("You are crossing the " + newaction.getDobj() + ".");
                            newaction.setDirection(location.returnCrossDirection());
                            newloc = goAction(newaction, currentloc, locationlist, mystatus, ImmovableList, AnimateList); 
                          }
                     }     
		     }
	else System.out.println("I don't understand what you mean.");
	
	return newloc;
	}
	
public static void fightAction(Action newaction, Location location, PlayerStatus mystatus,
			 List<MovableType> MovableList, List<AnimateType> AnimateList){

	  
	String dobj = newaction.getDobj();

	if (checkType(isType(immovableobjs, dobj), location.hasImmovable(dobj), dobj, "fight"));
		
	else if (checkType(isType(movableobjs, dobj), location.hasMovable(dobj), dobj, "fight"));
	
	else if (checkType(isType(finiteobjs, dobj), location.hasFinite(dobj), dobj, "fight"));

	else if (isType(animateobjs, newaction.getDobj()))
		if (!location.hasAnimate(newaction.getDobj()))
			System.out.println("You do not see any " + newaction.getDobj() + ".");

		else if (newaction.getInstrument().length()==0)
			if (mystatus.hasWeapon()){
				MovableType weaponobj = MovableType.returnMovableObject(mystatus.firstWeapon(), MovableList);
				AnimateType targetobj = AnimateType.returnAnimateObject(newaction.getDobj(), AnimateList);
						if (targetobj!=null)
						   switch (weaponobj.getName()) {
						       case "bow" : if (mystatus.getArrows()>0){		
					  				mystatus.setArrows(mystatus.getArrows() - 1);
						  		        if (mystatus.isHit(weaponobj, targetobj, location)){
									    System.out.println("You have hit the " + targetobj.getName() + "."); 	
							                    targetobj.computeInjury(weaponobj);
							                    if (targetobj.getInjury() > 10.0){
								               System.out.println("You have killed the " + targetobj.getName() + ".");
								               //System.out.println(targetobj.toString());
								               location.removeAnimate(targetobj);
								               //System.out.println(targetobj.toString());
							                  } else {System.out.println("You have injured the " + targetobj.getName() + ".");
								                mystatus.enemyAction(targetobj, weaponobj); }
						                      } else { System.out.println("You have missed the " + targetobj.getName() + ".");
									       mystatus.enemyAction(targetobj, weaponobj); }			
								   } else System.out.println("You don't have any arrows.");
							break;
							case "spear" :  mystatus.throwAt(weaponobj);
									location.addMovable(weaponobj);
									if (mystatus.isHit(weaponobj, targetobj, location)){
								   	   System.out.println("You have hit the " + targetobj.getName() + "."); 	
							                   targetobj.computeInjury(weaponobj);
								           if (targetobj.getInjury() > 10.0){
									      System.out.println("You have killed the " + targetobj.getName() + ".");
									      //System.out.println(targetobj.toString());
									      location.removeAnimate(targetobj);
									      //System.out.println(targetobj.toString());
								           } else {System.out.println("You have injured the " + targetobj.getName() + ".");
									     mystatus.enemyAction(targetobj, weaponobj); }
								         } else { System.out.println("You have missed the " + targetobj.getName() + ".");
									          mystatus.enemyAction(targetobj, weaponobj); }
							break;
							default: 
								targetobj.computeInjury(weaponobj);
								targetobj.setEnemy(true);
								if (targetobj.getInjury() > 10.0){
									System.out.println("You have killed the " + targetobj.getName() + ".");
									//System.out.println(targetobj.toString());
									location.removeAnimate(targetobj);
									//System.out.println(targetobj.toString());
								} else {System.out.println("You have injured the " + targetobj.getName() + ".");
									mystatus.enemyAction(targetobj, weaponobj); }	
							break;
							} /* end switch */
						else System.out.println("I don't understand what you mean.");				
				}

			else System.out.println("You are not carrying any weapon.");				

		else  if (newaction.getInstrument().length()!=0){
				MovableType weaponobj = MovableType.returnMovableObject(newaction.getInstrument(), MovableList);
		                if (weaponobj != null) 
				    if (mystatus.isCarrying(weaponobj))
					if (weaponobj.isWeapon()){
						AnimateType targetobj = AnimateType.returnAnimateObject(newaction.getDobj(), AnimateList);
						if (targetobj!=null)

						   switch (weaponobj.getName()) {
						       case "bow" : if (mystatus.getArrows()>0){		
					  				mystatus.setArrows(mystatus.getArrows() - 1);
						  		        if (mystatus.isHit(weaponobj, targetobj, location)){
									    System.out.println("You have hit the " + targetobj.getName() + "."); 	
							                    targetobj.computeInjury(weaponobj);
							                    if (targetobj.getInjury() > 10.0){
								               System.out.println("You have killed the " + targetobj.getName() + ".");
								               //System.out.println(targetobj.toString());
								               location.removeAnimate(targetobj);
								               //System.out.println(targetobj.toString());
							                  } else {System.out.println("You have injured the " + targetobj.getName() + ".");
								                mystatus.enemyAction(targetobj, weaponobj); }
						                      } else { System.out.println("You have missed the " + targetobj.getName() + ".");
									       mystatus.enemyAction(targetobj, weaponobj); }			
								   } else System.out.println("You don't have any arrows.");
							break;
							case "spear" :  mystatus.throwAt(weaponobj);
									location.addMovable(weaponobj);
									if (mystatus.isHit(weaponobj, targetobj, location)){
								   	   System.out.println("You have hit the " + targetobj.getName() + "."); 	
							                   targetobj.computeInjury(weaponobj);
								           if (targetobj.getInjury() > 10.0){
									      System.out.println("You have killed the " + targetobj.getName() + ".");
									      //System.out.println(targetobj.toString());
									      location.removeAnimate(targetobj);
									      //System.out.println(targetobj.toString());
								           } else {System.out.println("You have injured the " + targetobj.getName() + ".");
									     mystatus.enemyAction(targetobj, weaponobj); }
								         } else { System.out.println("You have missed the " + targetobj.getName() + ".");
									          mystatus.enemyAction(targetobj, weaponobj); }
							break;
							default: 
								targetobj.computeInjury(weaponobj);
								targetobj.setEnemy(true);
								if (targetobj.getInjury() > 10.0){
									System.out.println("You have killed the " + targetobj.getName() + ".");
									//System.out.println(targetobj.toString());
									location.removeAnimate(targetobj);
									//System.out.println(targetobj.toString());
								} else {System.out.println("You have injured the " + targetobj.getName() + ".");
									mystatus.enemyAction(targetobj, weaponobj); }	
							break;
							} /* end switch */

						else System.out.println("I don't understand what you mean.");	
					} else System.out.println("The " + weaponobj.getName() + " is not a weapon.");
				    else System.out.println("You are not carrying any " + weaponobj.getName() + ".");	
				else System.out.println("I don't understand what you mean.");
			} else System.out.println("With what do you want to fight the " + newaction.getDobj() + "?");
		
	}

public static void shootAction(Action newaction, Location location, PlayerStatus mystatus, List<MovableType> MovableList, List<AnimateType> AnimateList){

	String dobj = newaction.getDobj();

	if (checkType(isType(immovableobjs, dobj), location.hasImmovable(dobj), dobj, "shoot"));
		
	else if (checkType(isType(movableobjs, dobj), location.hasMovable(dobj), dobj, "shoot"));
	
	else if (checkType(isType(finiteobjs, dobj), location.hasFinite(dobj), dobj, "shoot"));

	else if (isType(animateobjs, newaction.getDobj()))
		if (!location.hasAnimate(newaction.getDobj()))
			System.out.println("You do not see any " + newaction.getDobj() + ".");

		else  if (newaction.getInstrument().length()==0 || newaction.getInstrument().matches("bow") || newaction.getInstrument().matches("arrow")){
				MovableType weaponobj = MovableType.returnMovableObject("bow", MovableList);
		                if (weaponobj != null) 
				    if (mystatus.isCarrying(weaponobj))
					if (mystatus.getArrows()>0){
						mystatus.setArrows(mystatus.getArrows() - 1);
						AnimateType targetobj = AnimateType.returnAnimateObject(newaction.getDobj(), AnimateList);
						if (targetobj!=null){
						     if (mystatus.isHit(weaponobj, targetobj, location)){
							System.out.println("You have hit the " + targetobj.getName() + "."); 	
							targetobj.computeInjury(weaponobj);
							if (targetobj.getInjury() > 10.0){
								System.out.println("You have killed the " + targetobj.getName() + ".");
								//System.out.println(targetobj.toString());
								location.removeAnimate(targetobj);
								//System.out.println(targetobj.toString());
							} else {System.out.println("You have injured the " + targetobj.getName() + ".");
								mystatus.enemyAction(targetobj, weaponobj); }
						     } else System.out.println("You have missed the " + targetobj.getName() + ".");		
						} else System.out.println("I don't understand what you mean.");	
					} else System.out.println("You don't have any arrows.");
				    else System.out.println("You are not carrying a bow.");	
				else System.out.println("I don't understand what you mean.");
			} else System.out.println("You cannot shoot with the " + newaction.getInstrument() + ".");
	}
	

public static void drinkAction(Action newaction, Location location, PlayerStatus mystatus, List<FiniteType> FiniteList){

	String dobj = newaction.getDobj();
	
	  
	if (checkType(isType(immovableobjs, dobj), location.hasImmovable(dobj), dobj, "drink"));	
		
	else if (checkType(isType(animateobjs, dobj), location.hasAnimate(dobj), dobj, "drink")); 

	else if (checkType(isType(movableobjs, dobj), location.hasMovable(dobj), dobj, "drink")); 	

	else if (isType(finiteobjs, dobj))
		if (!location.hasFinite(dobj))
			System.out.println("You do not see any " + dobj + ".");
		else { FiniteType finiteobj = FiniteType.returnFiniteObject(dobj, location, FiniteList);
		       if (finiteobj != null) mystatus.drinkFluid(finiteobj);}
	}


public static void openAction(boolean open, Action newaction, Location location, PlayerStatus mystatus, List<ImmovableType> ImmovableList){

	String openstring = (open ? "open" : "shut");

	if (!location.hasImmovable(newaction.getDobj())&&!location.hasMovable(newaction.getDobj())&&
	    !location.hasFinite(newaction.getDobj())&&!location.hasAnimate(newaction.getDobj()))
		System.out.println("You do not see any " + newaction.getDobj() + ".");
	else if (!isType(immovableobjs, newaction.getDobj()))
		System.out.println("You cannot " + openstring + " the " + newaction.getDobj() + ".");
	else {  ImmovableType obj =
			ImmovableType.returnImmovableObject(location.getIndex(newaction.getDobj()), ImmovableList);
		if (obj!=null)
			if (obj.isOpenable())
				if (obj.isLocked()) System.out.println("The " + obj.getName() + " is locked.");
				else	{
				obj.setOpen(open);
				System.out.println("The " + obj.getName() + " is now "+ openstring  +"."); }
			else System.out.println("You cannot " + openstring + " the " + obj.getName() + ".");
		else System.out.println("I don't understand what you mean.");
	} // end else

	} // end openAction	

public static void unlockAction(boolean unlock, Action newaction, Location location, PlayerStatus mystatus, List<ImmovableType> ImmovableList){

	String unlockstring = (unlock ? "unlock" : "lock");

	if (!location.hasImmovable(newaction.getDobj())&&!location.hasMovable(newaction.getDobj())&&
	    !location.hasFinite(newaction.getDobj())&&!location.hasAnimate(newaction.getDobj()))
		System.out.println("You do not see any " + newaction.getDobj() + ".");
	else if (!isType(immovableobjs, newaction.getDobj()))
		System.out.println("You cannot " + unlockstring + " the " + newaction.getDobj() + ".");
	else {  ImmovableType obj =
			ImmovableType.returnImmovableObject(location.getIndex(newaction.getDobj()), ImmovableList);
		if (obj!=null)
			if (obj.isLockable())
				if (obj.isUnlocksWithKey())
					if (mystatus.isCarrying(obj.getKey())){
						System.out.println("The " + obj.getName() + " is now "+ unlockstring  +"ed.");
						obj.setLocked(false); }
					else System.out.println("You don't have the correct key.");
				else System.out.println("The " + obj.getName() + " cannot be locked or unlocked with a key.");
 			else System.out.println("The " + obj.getName() + " cannot be " + unlockstring + "ed.");

		else System.out.println("You cannot " + unlockstring + " the " + obj.getName() + ".");
	
	} // end else

	} // end unlockAction	


public static void blowAction(Action newaction, Location location, PlayerStatus mystatus, List<ImmovableType> ImmovableList, List<MovableType> MovableList){

	if (newaction.getDobj().matches("horn"))
		if (location.hasImmovable("horn")) {
			mystatus.setBlewHorn(true);
			System.out.println("All of the walls of the Hornburn reverberate with the sound of the Horn of Helm Hammerhand."); }
		else if (mystatus.isCarrying(MovableType.returnMovableObject("horn",MovableList))) {
				mystatus.setBlewHorn(true);
	                        if (location.spellAction(mystatus, ImmovableList, MovableList)){
			 		mystatus.setSpell(true);
					System.out.println("You have cast a spell.");}      
				else System.out.println("The loud reverberation of the horn strikes fear into your enemies.");
		} else System.out.println("I don't understand what you mean.");		

	} // end blowAction

public static boolean releaseAction(Action newaction, Location location, PlayerStatus mystatus){

	boolean wingame = false;
	
	// special exception for horn, since horn can be immovable or movable

	if (newaction.getDobj().matches("horn")){
		MovableType obj = mystatus.returnMovableObject(newaction.getDobj());
		if ((obj!=null) && (mystatus.isCarrying(obj))){
			mystatus.removeCarrying(obj);
			location.addMovable(obj);
		}
		else System.out.println("You are not carrying any horn."); }

	// end special exception for horn

	else if (checkType(isType(animateobjs, newaction.getDobj()), location.hasAnimate(newaction.getDobj()), newaction.getDobj(), "release"));
	else if (checkType(isType(immovableobjs, newaction.getDobj()), location.hasImmovable(newaction.getDobj()), newaction.getDobj(), "release"));
	 
	else if (isType(finiteobjs,newaction.getDobj()))
		System.out.println("You cannot put this down - you can only use it up.");
		   
	else if (isType(movableobjs, newaction.getDobj())){
		
		MovableType obj = mystatus.returnMovableObject(newaction.getDobj());
		if ((obj!=null) && (mystatus.isCarrying(obj))){
			
			mystatus.removeCarrying(obj);
			wingame = location.addMovable(obj);
		}
		else System.out.println("You are not carrying any " + newaction.getDobj() + ".");}
	else 
		System.out.println("I don't understand what you mean.");

	return wingame;
	}

public static void touchAction(Action newaction, Location location, PlayerStatus mystatus, List<MovableType> MovableList, List<ImmovableType> ImmovableList){

	String dobj = newaction.getDobj();
	if (checkType(isType(animateobjs, dobj), location.hasAnimate(dobj), dobj, "touch"));
	else if (isType(finiteobjs, dobj))
		if (location.hasFinite(dobj))
			System.out.println("You have touched the " + dobj + ".");
		else System.out.println("You do not see any " + dobj + ".");
	else if (isType(movableobjs, dobj))
		if (location.hasMovable(dobj))
			System.out.println("You have touched the " + dobj + ".");
		else {
			MovableType touchobj = MovableType.returnMovableObject(dobj, MovableList);
			if (touchobj != null)
				if (mystatus.isCarrying(touchobj))
					System.out.println("You have touched the " + dobj + ".");
				else System.out.println("You do not see any " + dobj + ".");
			else System.out.println("I don't understand what you mean.");}
	else if (isType(immovableobjs, dobj))
			if (location.hasImmovable(dobj)){
				ImmovableType touchobj = ImmovableType.returnImmovableObject(location.getIndex(dobj), ImmovableList);
				if (touchobj.getName().matches("palantir")){
					System.out.println("You have touched the palantir.");
					touchobj.readPalantir(mystatus.getDaysElapsed());
				}
				else System.out.println("You have touched the " + dobj + ".");
			}
			else System.out.println("You do not see any " + dobj + ".");
	else System.out.println("I don't know what you mean.");

	}			

	

public static boolean throwAction(Action newaction, Location location, PlayerStatus mystatus, List<MovableType> MovableList, List<AnimateType> AnimateList){

	String dobj = newaction.getDobj();
	boolean wingame = false;
	if (checkType(isType(animateobjs, dobj), location.hasAnimate(dobj), dobj, "throw"));
	else if (checkType(isType(immovableobjs, dobj), location.hasImmovable(dobj), dobj, "throw"));
	else if (checkType(isType(finiteobjs, dobj), location.hasFinite(dobj), dobj, "throw"));
	else if (isType(movableobjs, dobj)){
		
		MovableType obj = mystatus.returnMovableObject(dobj);
		if ((obj!=null) && (mystatus.isCarrying(obj))){
			
			mystatus.throwAt(obj);
			wingame = location.addMovable(obj);

			if (newaction.getPrep().matches("at"))
				if (isType(animateobjs, newaction.getObjOfPrep()))
					if (location.hasAnimate(newaction.getObjOfPrep()))
						if (dobj.matches("spear") || dobj.matches("knife") || dobj.matches("axe")){
							AnimateType targetobj = AnimateType.returnAnimateObject(newaction.getObjOfPrep(), AnimateList);
							MovableType weaponobj = MovableType.returnMovableObject(dobj, MovableList);
							if (targetobj == null || weaponobj == null)
								System.out.println("I don't know what you mean.");
							else {
								if (mystatus.isHit(weaponobj, targetobj, location)){
								   System.out.println("You have hit the " + targetobj.getName() + "."); 	
							           targetobj.computeInjury(weaponobj);
								   if (targetobj.getInjury() > 10.0){
									System.out.println("You have killed the " + targetobj.getName() + ".");
									//System.out.println(targetobj.toString());
									location.removeAnimate(targetobj);
									//System.out.println(targetobj.toString());
								   } else {System.out.println("You have injured the " + targetobj.getName() + ".");
									mystatus.enemyAction(targetobj, weaponobj); }
								   }
								else System.out.println("You have missed the " + targetobj.getName() + ".");
								}	
							}
							else System.out.println("You have thrown away your " + dobj + " for nothing.");
					else System.out.println("You do not see any " + newaction.getObjOfPrep() + ".");
				else System.out.println("Throwing the " + dobj + " at the " + newaction.getObjOfPrep() + " has no effect."); 							
			else System.out.println("You have thrown the " + dobj + " in no particular direction.");
		
		}
		else System.out.println("You are not carrying any " + dobj + ".");}
	else System.out.println("I don't understand what you mean.");
	
	return wingame;

	}


public static boolean putAction(Action newaction, Location location, PlayerStatus mystatus, List <AnimateType> AnimateList) {

		boolean wingame = false;

		if (newaction.getPrep().matches("on")){
				
				MovableType obj = mystatus.returnMovableObject(newaction.getDobj());
				if (obj == null)
					System.out.println("You do not have any " + newaction.getDobj()+".");
					else {
						mystatus.putOn(obj);
						location.attractNazgul(mystatus.isWearingRing(), AnimateList);
					}
			     }
			    
                else if (newaction.getPrep().matches("away")||newaction.getPrep().matches("back")){
			     MovableType obj = mystatus.returnMovableObject(newaction.getDobj());
			     if (obj == null)
					System.out.println("You do not have any " + newaction.getDobj()+".");
					else mystatus.returnHolding(obj);
			     
			     }

			     else if (newaction.getPrep().matches("down")||newaction.getDirection().matches("down"))

				   			
					wingame = releaseAction(newaction, location, mystatus);
			     	  else System.out.println("I don't understand what you want to do.");
		return wingame;

	}

public static void takeOffAction(Action newaction, PlayerStatus mystatus) {

	MovableType obj = mystatus.returnMovableObject(newaction.getDobj());
	if (obj != null)
		if (mystatus.takeOff(newaction.getDobj())){
			System.out.println("You have taken off the " + newaction.getDobj() + ".");
			mystatus.setWarmth(mystatus.getWarmth() - (int) obj.getWarmth());   }
	
		else	System.out.println("You are not wearing any " + newaction.getDobj() + ".");
	else System.out.println("I'm not sure what you mean.");
	}		
 
public static int takeAction(Action newaction, int currentloc, List<Location> locationlist, PlayerStatus mystatus,
			     List<MovableType> MovableList, List<AnimateType> AnimateList, List<FiniteType> FiniteList,
			     List<ImmovableType> ImmovableList, List<InteractType> InteractList){

	boolean wingame = false;

	switch (newaction.getVerb()){
		case "hyper" : currentloc = newaction.getNumber(); // *** warning - debugging feature only - not designed to be used by player!
		break;

		case "look": lookAction(newaction, currentloc, locationlist, MovableList, mystatus, AnimateList, FiniteList, ImmovableList);
		break;
		case "walk" :
		case "run" :
		case "go" : currentloc = goAction(newaction, currentloc, locationlist, mystatus, ImmovableList, AnimateList);
		break;
		case "cross" : currentloc = crossAction(newaction, currentloc, locationlist, mystatus, ImmovableList, AnimateList);
		break;
		case "take" : if (newaction.getPrep().matches("off")) takeOffAction(newaction, mystatus);
			      else if (newaction.getPrep().matches("out")||newaction.getDirection().matches("out"))
				 	holdAction(newaction, locationlist.get(currentloc), mystatus, MovableList, ImmovableList, AnimateList);
			      else getAction(newaction, locationlist.get(currentloc), mystatus, FiniteList, MovableList, AnimateList);
		break;
		case "get" : 
		case "pick" : getAction(newaction, locationlist.get(currentloc), mystatus, FiniteList, MovableList, AnimateList);
		break;
		case "wear" : newaction.setPrep("on");
                              putAction(newaction, locationlist.get(currentloc), mystatus, AnimateList);
		break;		
		case "put" : wingame = putAction(newaction, locationlist.get(currentloc), mystatus, AnimateList);
		break;
		case "drop" : 
		case "release" : wingame = releaseAction(newaction, locationlist.get(currentloc), mystatus);
		break; 
		case "blow" : blowAction(newaction, locationlist.get(currentloc), mystatus, ImmovableList, MovableList);
		break;
		case "open" : openAction(true, newaction, locationlist.get(currentloc), mystatus, ImmovableList);
		break;
		case "close" : openAction(false, newaction, locationlist.get(currentloc), mystatus, ImmovableList);
		break;
		case "shut" : openAction(false, newaction, locationlist.get(currentloc), mystatus, ImmovableList);
		break;
		case "unlock" : unlockAction(true, newaction, locationlist.get(currentloc), mystatus, ImmovableList);
		break;
		case "eat" : eatAction(newaction, locationlist.get(currentloc), mystatus, FiniteList);
		break;
		case "speak" : if (newaction.getPrep().matches("to")||(!newaction.getInstrument().matches("")))
					talkAction(newaction, locationlist.get(currentloc), mystatus, AnimateList, InteractList);
			       else sayAction(newaction, locationlist.get(currentloc), mystatus, ImmovableList);
		break;						
		case "say" : sayAction(newaction, locationlist.get(currentloc), mystatus, ImmovableList);
		break;
		case "listen" :
		case "talk" : talkAction(newaction, locationlist.get(currentloc), mystatus, AnimateList, InteractList);
		break;
		case "drink" : drinkAction(newaction, locationlist.get(currentloc), mystatus, FiniteList);
		break;
		case "kill" : 
		case "hit" : 
		case "attack" : 
		case "fight" : fightAction(newaction, locationlist.get(currentloc), mystatus, MovableList, AnimateList);
		break;
		case "shoot" : shootAction(newaction, locationlist.get(currentloc), mystatus, MovableList, AnimateList);
		break;
		case "throw" : wingame = throwAction(newaction, locationlist.get(currentloc), mystatus, MovableList, AnimateList);
		break;
		case "climb" : currentloc = climbAction(newaction, currentloc, locationlist, mystatus, ImmovableList);
		break;
		case "read" : readAction(newaction, locationlist.get(currentloc), ImmovableList);
		break;
		case "touch" : touchAction(newaction, locationlist.get(currentloc), mystatus, MovableList, ImmovableList);
		break;
		case "draw" :
		case "raise" :
		case "hold" : holdAction(newaction, locationlist.get(currentloc), mystatus, MovableList, ImmovableList, AnimateList);
		break;
		case "lower" :
		case "return" : returnAction(newaction, locationlist.get(currentloc), mystatus, MovableList, AnimateList);
		break;
		case "carrying" : mystatus.listCarrying();
		break;
		case "holding" : mystatus.listHolding();
		break;
		case "wearing" : mystatus.listWearing();
		break;
		case "energy" :
		case "health" :
		case "status" : mystatus.listStatus((int)locationlist.get(currentloc).getTemperature());
		break;
		case "debug" : System.out.println(locationlist.get(currentloc).toString());
		break;
		case "help" : helpAction();
		break;
		case "exit" : System.out.println("Good bye.");
		break;

		default : if (isType(directions, newaction.getDirection())){
				newaction.setVerb("go");
				currentloc = goAction(newaction, currentloc, locationlist, mystatus, ImmovableList, AnimateList); }

			   else System.out.println("I did not understand what you said.");
		break;
		}

	if (wingame) currentloc = -3;
	return currentloc;

	}

public static void helpAction(){

		System.out.println("Enter commands in plain English beginning with a command.  Try using one of the following verbs:");
		for (int i = 0; i<verbs.length-1; i++)
			System.out.print(verbs[i] + ", ");
		System.out.println(verbs[verbs.length-1] + ".");
		System.out.println("For movement, try using one of the following directions: ");
		for (int i = 0; i<directions.length-1; i++)
			System.out.print(directions[i] + ", ");
		System.out.println(directions[directions.length-1] + ".");
	}

public static void sayAction(Action newaction, Location location, PlayerStatus mystatus, List<ImmovableType> ImmovableList){

		if (isType(spells, newaction.getDobj()) && !newaction.getDobj().matches("")){
			
			 System.out.println("\"" + newaction.getDobj()+ "\"");
			 if (location.spellAction(newaction.getDobj(), spells, spellindices, ImmovableList)){
			 	mystatus.setSpell(true);
				System.out.println("You have cast a spell.");
			}
		}					
		else System.out.println("I don't understand what you want to say.");

}

public static int canGoThere(Location location, Movement m, PlayerStatus mystatus, List<ImmovableType> ImmovableList, List<AnimateType> AnimateList){

		int temploc = -1;

		if (m.getViaPassage())
			if (!ImmovableType.returnImmovableObject(m.getPassageIndex(), ImmovableList).isOpen()) {
				System.out.println("The " + ImmovableType.returnImmovableObject(m.getPassageIndex(), ImmovableList).getName() + 
					" is shut - you cannot go that way.");
				return temploc;
		} 
				
		if (m.getIndex()==-1){
			System.out.println("You cannot go there.");
			return temploc;
		}

		boolean immunetoring = false;
		boolean canstophorse = false;

		if (location.returnEnemy(AnimateList)!=-1){
			
			immunetoring = AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("nazgul")
                                       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("wight")
                                       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("wraith")
                                       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("ghost")
				       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("wizard")
				       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("warlock")
				       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("balrog")
				       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("ent")
				       || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("willow");

			canstophorse = immunetoring || AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName().matches("warg");
			boolean escapewithring = mystatus.isWearingRing() && !immunetoring;
			boolean escapeonhorse = mystatus.isOnHorse() && !canstophorse;
		
			if (!mystatus.hasSpell() && !escapewithring && !escapeonhorse){	
				System.out.println("The " + AnimateType.returnAnimateObject(location.returnEnemy(AnimateList), AnimateList).getName() + 
					" blocks your path - you cannot escape.");
				return temploc;
			}
		}
		
		if (m.getRequiresBoat() && !mystatus.isInBoat())
			System.out.println("You cannot go that way.");
		else if (!m.getRequiresBoat() && mystatus.isInBoat())
			System.out.println("You are in a boat - you can't go that way.");
		else if (!m.isRidable() && mystatus.isOnHorse())
			System.out.println("You cannot go that way on horseback.");
		else if (m.getRequiresRope() && !mystatus.hasRope())
			System.out.println("You cannot go that way without rope.");
		else if (m.getRequiresSpell() && !mystatus.hasSpell())
			System.out.println("You cannot pass.");	
		else if (m.getRequiresRing() && !mystatus.isWearingRing())
			System.out.println("You cannot pass.");	
		else if (m.isArduousTask() && !mystatus.hasEnoughEnergy())
			System.out.println("You don't have enough energy for this task.");
        else if (m.hasObstacle())
            if (location.hasWeb() && !mystatus.hasSharp())
                System.out.println("You don't have anything that can cut the web.");
            else temploc = m.getIndex();
        else  temploc = m.getIndex();
		return temploc;
	}

public static int climbAction(Action newaction, int currentloc, List<Location> locationlist, PlayerStatus mystatus, List<ImmovableType> ImmovableList){

		int newloc = currentloc;
		int temploc = 0;

		String dobj = newaction.getDobj();

		if (checkType(isType(animateobjs, dobj), locationlist.get(currentloc).hasAnimate(dobj), dobj, "climb"));
		
		else if (checkType(isType(movableobjs, dobj), locationlist.get(currentloc).hasMovable(dobj), dobj, "climb"));
		
		else if (checkType(isType(finiteobjs, dobj), locationlist.get(currentloc).hasFinite(dobj), dobj, "climb"));

		else if (!isType(immovableobjs, newaction.getDobj()))
			System.out.println("You cannot climb the " + newaction.getDobj() + ".");
		else {  ImmovableType obj =
			ImmovableType.returnImmovableObject(locationlist.get(currentloc).getIndex(newaction.getDobj()), ImmovableList);
			if (obj!=null)
				if (obj.isClimbable())
					if (!mystatus.hasEnoughEnergy()) System.out.println("You don't have enough energy to climb.");
					//else if ((mystatus.getEnergy()<25.0) || (mystatus.getInjury()>75.0)) System.out.println("You are too weak to climb.");
					else if (locationlist.get(currentloc).getUpPtr().getIndex()!=-1)
						if (locationlist.get(currentloc).getUpPtr().getRequiresRope() && !mystatus.hasRope())
							System.out.println("You do not have any rope.");
						else {
							System.out.println("You are climbing the " + newaction.getDobj() + ".");
							newloc = locationlist.get(currentloc).getUpPtr().getIndex();
							mystatus.updateStatus(locationlist.get(currentloc).getUpPtr(),
								 (int)locationlist.get(currentloc).getTemperature());
						}
					     else System.out.println("I don't understand what you mean.");
				
						 
				else System.out.println("You cannot climb the " + newaction.getDobj() + ".");
			
			else System.out.println("I don't understand what you mean.");
			}
		
		return newloc;
	}		
		

public static int goAction(Action newaction, int currentloc, List<Location> locationlist, PlayerStatus mystatus,
			 List<ImmovableType> ImmovableList, List<AnimateType> AnimateList){
		int newloc = currentloc;
		int temploc = 0;
		switch (newaction.getDirection()){
			case "n"     :
			case "north" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getNorthPtr(),
						mystatus, ImmovableList, AnimateList);
					if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getNorthPtr(),
											(int)locationlist.get(currentloc).getTemperature());
							     mystatus.setMoveAttempt(true);  }

			break;
			case "ne" :
			case "northeast" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getNorthEastPtr(),
						mystatus, ImmovableList, AnimateList);
					   if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getNorthEastPtr(),
										(int)locationlist.get(currentloc).getTemperature());
								mystatus.setMoveAttempt(true); }  		

			break;
			case "se" :
			case "southeast" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getSouthEastPtr(),
						mystatus, ImmovableList, AnimateList);
					   if (temploc != -1)  { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getSouthEastPtr(),
										(int)locationlist.get(currentloc).getTemperature());
								 mystatus.setMoveAttempt(true);  }   
			break;
			case "s" :
			case "south" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getSouthPtr(),
						mystatus, ImmovableList, AnimateList);
					 if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getSouthPtr(),
										(int)locationlist.get(currentloc).getTemperature());
							      mystatus.setMoveAttempt(true);  }  
			break; 
			case "sw" :
			case "southwest" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getSouthWestPtr(),
						mystatus, ImmovableList, AnimateList);
					     if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getSouthWestPtr(),
										(int)locationlist.get(currentloc).getTemperature());
								  mystatus.setMoveAttempt(true);  }    	
			break;
			case "nw" :
			case "northwest" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getNorthWestPtr(),
						mystatus, ImmovableList, AnimateList);
					   if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getNorthWestPtr(),
										(int)locationlist.get(currentloc).getTemperature());
								mystatus.setMoveAttempt(true);  }  	
			break;
			case "e" :		
			case "east" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getEastPtr(),
						mystatus, ImmovableList, AnimateList);
				      if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getEastPtr(),
										(int)locationlist.get(currentloc).getTemperature());
							  mystatus.setMoveAttempt(true);  }  
			break;
			case "w" :
			case "west" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getWestPtr(),
						mystatus, ImmovableList, AnimateList);
				      if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getWestPtr(),
										(int)locationlist.get(currentloc).getTemperature()); 
							   mystatus.setMoveAttempt(true); }  	
			break;
			case "u" :
			case "up" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getUpPtr(),
						mystatus, ImmovableList, AnimateList);
				      if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getUpPtr(),
										(int)locationlist.get(currentloc).getTemperature());
							   mystatus.setMoveAttempt(true);  }   		
			break;
			case "i" :
			case "inside" :
			case "in" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getInPtr(),
						mystatus, ImmovableList, AnimateList);
				      if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getInPtr(),
										(int)locationlist.get(currentloc).getTemperature());
							   mystatus.setMoveAttempt(true);  }   	
			break;
			case "o" :
			case "outside" :
			case "out" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getOutPtr(),
						mystatus, ImmovableList, AnimateList);
				       if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getOutPtr(),
										(int)locationlist.get(currentloc).getTemperature());
							    mystatus.setMoveAttempt(true);  }   	
			break;
			case "d" :
			case "down" : temploc = canGoThere(locationlist.get(currentloc), locationlist.get(currentloc).getDownPtr(),
						mystatus, ImmovableList, AnimateList);
				        if (temploc != -1) { newloc = temploc; mystatus.updateStatus(locationlist.get(currentloc).getDownPtr(),
										(int)locationlist.get(currentloc).getTemperature());
							    mystatus.setMoveAttempt(true);  }    	
			break;
							
			default: System.out.println("I don't know what you mean.");
				   newloc = currentloc;
				 
			break;
		}
		return newloc;
	}
		
		
public static void lookAction(Action newaction, int currentloc, List<Location> locationlist, List<MovableType> MovableList,
			      PlayerStatus mystatus, List<AnimateType> AnimateList, List<FiniteType> FiniteList, 
			      List<ImmovableType> ImmovableList){

	if (newaction.getDirection()!="")	
		switch (newaction.getDirection()){
			case "north" : System.out.println(locationlist.get(currentloc).getLookNorth());
			break;
			case "south" : System.out.println(locationlist.get(currentloc).getLookSouth());
			break;
			case "east" : System.out.println(locationlist.get(currentloc).getLookEast());
			break;
			case "west" : System.out.println(locationlist.get(currentloc).getLookWest());
			break;
			case "up" : System.out.println(locationlist.get(currentloc).getLookUp());
			break;
			case "down" : System.out.println(locationlist.get(currentloc).getLookDown());
			break;
							
			default: System.out.println(locationlist.get(currentloc).getDescription());
			break;
		}
	
	else switch (newaction.getPrep()){

		case "around"  : System.out.println(locationlist.get(currentloc).getDescription());
		break;
		case "on" :
		case "in" :
		case "into":
		case "through":
		case "at" :	if (isType(movableobjs, newaction.getDobj()))
					if (!locationlist.get(currentloc).hasMovable(newaction.getDobj())&&
					    !mystatus.isCarrying(mystatus.returnMovableObject(newaction.getDobj())))	
						System.out.println("You do not see any " + newaction.getDobj() + ".");
	   				else { 
						MovableType obj = MovableType.returnMovableObject(newaction.getDobj(), MovableList);
						System.out.println("The " + obj.getName() + " is " + obj.getDescription() + ".");
					} 
				else if (isType(finiteobjs, newaction.getDobj()))
					if (!locationlist.get(currentloc).hasFinite(newaction.getDobj()))
						System.out.println("You do not see any " + newaction.getDobj() + ".");
	   				else { 
						FiniteType obj = FiniteType.returnFiniteObject(newaction.getDobj(), locationlist.get(currentloc), FiniteList);
						System.out.println("The " + obj.getName() + " is " + obj.getDescription() + ".");
					}
					
			
				else if (isType(animateobjs, newaction.getDobj()))
					if (!locationlist.get(currentloc).hasAnimate(newaction.getDobj()))
						System.out.println("You do not see any " + newaction.getDobj() + ".");
	   				else { 
						AnimateType obj = AnimateType.returnAnimateObject(newaction.getDobj(), AnimateList);
						System.out.println("The " + obj.getName() + " is " + obj.getDescription() + ".");
					}
				
				else if (isType(immovableobjs, newaction.getDobj()))
					if (!locationlist.get(currentloc).hasImmovable(newaction.getDobj()))
						System.out.println("You do not see any " + newaction.getDobj() + ".");
	   				else { 
						ImmovableType obj =
							 ImmovableType.returnImmovableObject(locationlist.get(currentloc).getIndex(newaction.getDobj()), ImmovableList);
						System.out.println("The " + obj.getName() + " is " + obj.getDescription() + ".");
						if (obj.isOpenable())
							if (obj.isOpen()) System.out.println("The " + obj.getName() + " is open.");
							else System.out.println("The " + obj.getName() + " is shut.");
						if (obj.getName().matches("mirror"))
							obj.readPalantir(mystatus.getDaysElapsed());
						
					}

		break;
		
		default : System.out.println(locationlist.get(currentloc).getDescription());
		break;
	}

		
	}

public static void parse(String s, Action newaction){
         	
	int startindex = 0;
	int endindex = 0;
	String t;
	//Action newaction = new Action();
	boolean verbflag = false;	
        boolean prepflag = false;
	boolean instrflag = false;
	boolean numberflag = false; //this is a debugging feature not designed to be accessed by player	

	while (s.length() > 0){
		s = s.trim();
		if (s.indexOf(" ") == -1) endindex = s.length();
		else endindex = s.indexOf(" ");
		t = s.substring(0,endindex);
		
		//search for number - a debugging feature not available to player
		if (t.startsWith("0")){
			newaction.setNumber(t);
			numberflag = true;
		}
		
		// search verbs
		for (int i = 0; i<verbs.length; i++)
			if (t.matches(verbs[i])) {
				newaction.setVerb(t);
				verbflag = true;
				}

		for (int i = 0; i<directions.length; i++)
			if (t.matches(directions[i])) newaction.setDirection(t);
		 	
		for (int i = 0; i<preps.length; i++)
			if (t.matches(preps[i])){
				if ((t.matches("with"))||(t.matches("using")))  
					instrflag = true;
				else {
					newaction.setPrep(t);
					prepflag = true;
				     }
			}
	
		for (int i = 0; i<nouns.length; i++)
			if (t.matches(nouns[i])) {
				if (verbflag) {
					newaction.setDobj(t);
					verbflag = false;
				}

				if (prepflag) {
					newaction.setObjOfPrep(t);
					prepflag = false;
				}

				if (instrflag) {
					newaction.setInstrument(t);
					instrflag = false;
				}
			}

		s = s.substring(endindex);
		
	} // end while
	//return newaction;

}   // end parse method

public static void main (String[] args){


	List<Location> locationlist= new ArrayList<Location>();
	List<MovableType> MovableList = new ArrayList<MovableType>();
	List<AnimateType> AnimateList = new ArrayList<AnimateType>();
	List<FiniteType> FiniteList = new ArrayList<FiniteType>();
	List<ImmovableType> ImmovableList = new ArrayList<ImmovableType>();
	List<InteractType> InteractList = new ArrayList<InteractType>();

	try	{
	InputStream is = new FileInputStream("ringquesttext.txt");
        int size = is.available();
	int i = 0;
	String s = "";
	char c = (char) is.read();
	i++;
	
	
	System.out.println("Initializing...");
	while (c!='+') {
		while (c != '#') {
			if (c>31)
				s = s + c;
			c = (char) is.read();
			i++;
		}
		c = (char) is.read();
		i++;
		MovableType.parseMovable(MovableList, s);
		//System.out.println(s);
		//System.out.println(newloc.toString());
		s = "";
		
	}
	s="";
	c = (char) is.read();
	i++;
	//System.out.println("Starting animate loop: c=" + c);
	while (c!='+') {
		while (c != '#') {
			if (c>31)
				s = s + c;
			c = (char) is.read();
			i++;
		}
		c = (char) is.read();
		i++;
		//System.out.println("Finished animate loop.");
		
		//System.out.println(s);
		AnimateType.parseAnimate(AnimateList, s);
		
		s = "";
		
	}
	s="";

	c = (char) is.read();
	i++;
	//System.out.println("Starting finite loop: c=" + c);
	while (c!='+') {
		while (c != '#') {
			if (c>31)
				s = s + c;
			c = (char) is.read();
			i++;
		}
		c = (char) is.read();
		i++;
		//System.out.println("Finished finite loop.");
		
		//System.out.println(s);
		FiniteType.parseFinite(FiniteList, s);
		
		s = "";
		
	}


	s="";

	c = (char) is.read();
	i++;
	//System.out.println("Starting Immovable loop: c=" + c);
	while (c!='+') {
		while (c != '#') {
			if (c>31)
				s = s + c;
			c = (char) is.read();
			i++;
		}
		c = (char) is.read();
		i++;
		//System.out.println("Finished immovable loop.");
		
		//System.out.println(s);
		ImmovableType.parseImmovable(ImmovableList, s);
		
		s = "";
		
	}

	s="";

	c = (char) is.read();
	i++;
	//System.out.println("Starting Interact loop: c=" + c);
	while (c!='+') {
		while (c != '#') {
			if (c>31)
				s = s + c;
			c = (char) is.read();
			i++;
		}
		c = (char) is.read();
		i++;
		//System.out.println("Finished Interact loop.");
		
		//System.out.println(s);
		InteractType.parseInteract(InteractList, s);
		
		s = "";
		
	}


	s="";
	c = (char) is.read();
	i++;
	//System.out.println("Starting location loop: c=" + c);
	while (i<(size-1)) {

	    //System.out.println("i = " + i + "size = " + size);
		while (c != '#') {
			if (c>31)
				s = s + c;
			c = (char) is.read();
			i++;
		}
		c = (char) is.read();
		i++;
		Location newloc = new Location();
		//System.out.println("new location is: " + s);
	
		newloc.parseLocation(s);
		//System.out.println(newloc.toString());
		locationlist.add(newloc);
		//System.out.println("new location added to location list.");
		s = "";

	}
	//System.out.println("location list loading complete.");
	System.out.println("Initialization complete.");

        is.close();
   	} catch(IOException e){
      	System.out.print("Exception");   }	
   	

	try {
	newString(locationlist, MovableList, AnimateList, FiniteList, ImmovableList, InteractList);
	}
	catch (IOException e) { 	System.out.println("Exception."); };
}

} // end class   
