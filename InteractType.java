import java.io.*;
import java.util.*;

// Define Objects of InteractType - objects which player can exchange or get information, such as talk, speak, listen, etc.
// All methods are final and not designed for inheritance

public class InteractType{

	private int index;  // unique index for each interact object (>= 0)
	private String friendinfo; // string description of information from friendly objects
	private String enemyinfo1; // string description of information from enemy objects (before injury)
	private String enemyinfo2; // string description of information from enemy objects (after injury or under duress)
	private String ringinfo; // string description of information from objects while wearing the ring and unseen
	
	//constructor, builds an object during parsing locations

	public InteractType(int newindex){
		index = newindex;
		
	}


	// Parses the text file to build the InteractList during initialization. All fields
	// in the text file must exactly match this parser, otherwise will throw
	// IOException

	public final static void parseInteract(List<InteractType> InteractList, String s){
	

		//System.out.println("Called parseInteract");
		int endindex = 0;
	
		s = s.substring(s.indexOf("+")+1);
		
		s = s.trim();
		String t = s.substring(0,s.indexOf("/"));
		//System.out.println(s);
		int newindex = Integer.parseInt(t);
    	        InteractType newobj = new InteractType(newindex);
		s = s.substring(s.indexOf("/")+1);
		//System.out.println("parsed index= " + newindex);

		s = s.trim();
		String newfriendinfo = s.substring(0,s.indexOf("/"));
		newobj.setFriendInfo(newfriendinfo);		
   		s = s.substring(s.indexOf("/")+1);
		
		s = s.trim();
		String newenemyinfo1 = s.substring(0,s.indexOf("/"));
		
		newobj.setEnemyInfo1(newenemyinfo1);
		s = s.substring(s.indexOf("/")+1);

		s = s.trim();
		String newenemyinfo2 = s.substring(0,s.indexOf("/"));
		newobj.setEnemyInfo2(newenemyinfo2);
		
		s = s.substring(s.indexOf("/")+1);


		s = s.trim();
		String newringinfo = s.substring(0,s.indexOf("/"));
		newobj.setRingInfo(newringinfo);
		s = s.substring(s.indexOf("/")+1);
	
		InteractList.add(newobj);
	}

	// Given an index of interact object and InteractList, return an object with all the fields
	// of an interact object.  
	// Can return null - responsibility of the client to check

	public static final InteractType returnInteractObject(int index, List<InteractType> InteractList){

		InteractType returnobj = null;
		for (int i = 0; i<InteractList.size(); i++)
			if (InteractList.get(i).getIndex() == index)
					returnobj = InteractList.get(i);			
		return returnobj;
	}


	public final int getIndex(){
		return index;
	}

	public final String getFriendInfo(){
		return friendinfo;
	}

	public final String getEnemyInfo1(){
		return enemyinfo1;
	}

	public final String getEnemyInfo2(){
		return enemyinfo2;
	}

	public final String getRingInfo(){
		return ringinfo;
	}


	public final void setIndex(int newindex){
		index = newindex;
	}

	public final void setFriendInfo(String newfriendinfo){
		friendinfo = newfriendinfo;
	}

	public final void setEnemyInfo1(String newenemyinfo1){
		enemyinfo1 = newenemyinfo1;
	}


	public final void setEnemyInfo2(String newenemyinfo2){
		enemyinfo2 = newenemyinfo2;
	}

	public final void setRingInfo(String newringinfo){
		ringinfo = newringinfo;
	}
	
}
		