/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;


public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		message = new GLabel("Welcome to Face Pamphlet");
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth() /2), (getHeight()-(BOTTOM_MESSAGE_MARGIN*2)));
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
		message.setLocation( (getWidth()-message.getWidth()) / 2, (getHeight()-(BOTTOM_MESSAGE_MARGIN*2))); 
		
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// Clears all of the exisitng items (picture, name, status, system messages), re adds the message label as blank.
		removeAll();
		message.setLabel("");
		add(message);
		
		//Displays a given profile (name, picture or picture place holder, status and user's friends
		profileName = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN);
		profileName.setFont(PROFILE_NAME_FONT);
		profileName.setColor(Color.blue);
		profileName.move(0, profileName.getAscent() / 2);
		add(profileName);
	}

GLabel message;
GLabel profileName;
GImage profilePicture;
GLabel status;
GLabel friends;

}
