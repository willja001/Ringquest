import java.io.*;

// Defines class used by the parser
// Object of this class contains one parsed sentence, parsed into fields according to grammar
// all methods, except for toString(), are final and not designed for inheritance

public class Action {

	private String verb; // verb (transitive or intransitive)
	private String dobj; // direct object
	private String prep; // preposition
	private String objofprep;// object of preposition
	private String direction; // north, northeast, east, southeast, south, southwest, west, northwest, up, down, in, out
	private String instrument; // noun representing "with" or "using" (i.e., "with the sword")
	private int index; // debugging feature - index used for hyperjumps
	
	public Action(){
		verb = "";
		dobj = "";
		prep = "";
		objofprep = "";
		direction = "";	
		instrument = "";
		index = -1;         	
	}


	public final void clearAction(){
		verb = "";
		dobj = "";
		prep = "";
		objofprep = "";
		direction = "";	
		instrument = "";
		index = -1;
	}
		
	public final String getVerb(){
		return verb;
	}

	public final String getPrep(){
		return prep;
	}

	public final String getDobj(){
		return dobj;
	}
	
	public final String getObjOfPrep(){
		return objofprep;
	}
	
	public final String getDirection(){
		return direction;
	}

	public final String getInstrument(){
		return instrument;
	}

	public final int getNumber(){
		return index;
	}

	public final void setVerb(String s){
		verb = s;
	}

	public final void setPrep(String s){
		prep = s;
	}

	public final void setDobj(String s){
		dobj = s;
	}

	public final void setObjOfPrep(String s){
		objofprep = s;
	}
	
	public final void setDirection(String s){
		direction = s;
	}

	public final void setInstrument(String s){
		instrument = s;
	}

	public final void setNumber(String s){
		index = Integer.parseInt(s);
	}
		
	@Override public String toString(){
		String s = "Verb= " + verb + " Direction= " + direction +
			   " Direct Obj= " + dobj + " Prep= " + prep +
			   " Obj of Prep= " + objofprep + " using= " + instrument + " Number= " + index;		
		return s;
	}
	
} /* end Action */
	