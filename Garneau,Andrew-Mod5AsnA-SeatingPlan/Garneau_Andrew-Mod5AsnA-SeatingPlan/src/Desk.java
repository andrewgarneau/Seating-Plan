
public class Desk {

	private int row = 0,column=0;
	private String name;
	private boolean occupied=false;
	
	/**
	 * constructor
	 * pre: none
	 * post: a desk object is created, initialized to a blank name and a location of 1,1 and unoccupied
	 */
	public Desk(){
		row=1;
		column=1;
		name="";
		occupied =false;
	}
	
	/**
	 * constructor
	 * pre: int r, c
	 * post: a desk object is created, initialized to a blank name and a location of r,c and unoccupied
	 */
	public Desk(int r, int c){
		row=r;
		column=c;
		name="";
		occupied =false;
	}
	
	/**
	 * constructor
	 * pre: int r,c and string n
	 * post: a desk object is created, set to name n and location r,c and occupied based off name
	 */
	public Desk(String n,int r, int c){
		name = n;
		row=r;
		column=c;
		if (name.equals("")){
			occupied=false;
		}else {
			occupied=true;
		}
	}
	
	/**
	 * sets location
	 * pre: int r , c
	 * post: location has been changed to r,c
	 */
	public void setLocation(int r,int c){
		row = r;
		column = c;
	}
	
	/**
	 * sets name
	 * pre: string n
	 * post: name has been changed to n
	 */
	public void setName(String n){
		name = n;
		if (name.equals("")){
			occupied=false;
		}else {
			occupied=true;
		}
	}
	
	/**
	 * gets location
	 * pre: 
	 * post: a string of the row and column is returned
	 */
	public String getLocation(){
		return "["+row+","+column+"]";
	}
	
	/**
	 * returns name
	 * pre:
	 * post: returns name string
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * sets boolean to tell if seat is occupied
	 * pre:
	 * post: true or false based off of presence of name in seat
	 */
	public boolean getOccupied(){
		if (name.equals("")) {
			occupied=false;
		}else {
			occupied=true;
		}
		
		return occupied;
		
	}
	
	/**
	 * returns the string of the desk
	 * pre:
	 * post: name and location is returned
	 */
	public String toString() {
		String tempRet = name+" ["+row+","+column+"]";
		return tempRet;
	}
	
}