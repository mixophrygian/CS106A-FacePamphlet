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
		
		//Displays name
		profileName = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN);
		profileName.setFont(PROFILE_NAME_FONT);
		profileName.setColor(Color.blue);
		profileName.move(0, profileName.getAscent() / 2);
		add(profileName);
		
		//Displays picture.  Default picture is the text "No Image" in a rectangle
		if(profile.getImage() == null){
			GRect rect = new GRect (LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			GLabel noImage = new GLabel("No Image", LEFT_MARGIN + rect.getWidth()/2, TOP_MARGIN+IMAGE_MARGIN + rect.getHeight()/2);
			noImage.setFont(PROFILE_IMAGE_FONT);
			noImage.move(-noImage.getWidth()/2, noImage.getAscent()/2);
			add(rect);
			add(noImage);
		}else{
			GImage profilePicture = profile.getImage();
			profilePicture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			profilePicture.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
			add(profilePicture);
		}
		
		//Displays status
		if(profile.getStatus().equals(null)){
			
		}
		
		//Displays friends
	}
	
	public void removeProfile(){
		removeAll();
		message.setLabel("");
		add(message);
	}

GLabel message;
GLabel profileName;
GLabel status;
GLabel friends;

}
