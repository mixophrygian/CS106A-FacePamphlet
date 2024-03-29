/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;
import java.awt.Image;
import java.awt.image.*;

public class FacePamphletProfile implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile. Creates a new face pamphlet profile with a name, 
	/* an empty picture, an empty status and no friends.
	 */
	public FacePamphletProfile(String name) {
		profileName = name;
		status = null;
		friendsList = new ArrayList<String>();
		profilePicture = null;
		shortProfilePictureName = "No Image";
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		return profileName;
	}

	/** 
	 * This method returns the image associated with the profile.  
	 * The default image assigned for all profiles is denoted in FacePamphlet Constands
	 */ 
	public GImage getImage() {
		if (profilePicture == null) {
			return null;
		}else{
		return profilePicture;
	}
	}
	
	/**This method gets the name of the profile picture being used.  The default is "No Image"
	 *
	 */
	public String getShortFileName(){
		return shortProfilePictureName;
	}
	
	/**This method sets the name of the profile picture being used.  The default is "No Image"
	 * @param string
	 * @return
	 */
	public String setShortFileName(String string){
		shortProfilePictureName = string;
		return shortProfilePictureName;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		//seems too easy...could be buggy?//
		profilePicture = image;
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		if (status == null){
			return "";
		}else{
		return status;
	}
	}
	/** This method sets the status associated with the profile. */ 
	public void setStatus(String newStatus) {
		status = newStatus;
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		if(friendsList.contains(friend)) {
			return false;
		}else{
			friendsList.add(friend);
			return true;
			
		}
		
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		if(friendsList.contains(friend)){
			friendsList.remove(friend);
			return true;
			
		}else{
			//Add a message that the given friend was not in the list of friends to begin with//
		return false;
		}
	}

	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 */ 
	public Iterator<String> getFriends() {
		//seems too easy, may also be buggy//
		return friendsList.iterator();
	}
	
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name (status): list of friends", 
	 * where name and status are set accordingly and the list of 
	 * friends is a comma separated list of the names of all of the 
	 * friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is 
	 * "coding" and who has friends Don, Chelsea, and Bob, this method 
	 * would return the string: "Alice (coding): Don, Chelsea, Bob"
	 */ 
	public String toString() {
		//Might be a neater way to display friendsList, perhaps a for Loop or iterator//
		String neatFriendsList = " ";
		boolean firstFriend = true;
		for(int i = 0; i < friendsList.size(); i++){
			if(firstFriend) {
				neatFriendsList = neatFriendsList + " " + friendsList.get(i);
				firstFriend = false;
			}else{
				neatFriendsList = neatFriendsList + ", " + friendsList.get(i);
			}
		}
		
		return ""+profileName+ " "+status+":" + neatFriendsList + " ";
	}

	
	private String profileName;
	private String status;
	private GImage profilePicture;
	private ArrayList<String> friendsList;
	private String shortProfilePictureName;
}
