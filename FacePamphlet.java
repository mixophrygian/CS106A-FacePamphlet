/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// Adds interactors to the top of the window//
		add(new JLabel("Name"), NORTH);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		nameField.addActionListener(this);
		add(nameField, NORTH);
		Add = new JButton ("Add");
		Delete = new JButton ("Delete");
		Lookup = new JButton ("Lookup");
		add(Add, NORTH);
		add(Delete, NORTH);
		add(Lookup, NORTH);
		
		//Adds status field to the left of the window//
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		add(statusField, WEST);
		ChangeStatus = new JButton("Change Status");
		add(ChangeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//Adds picture field to the left of the window//
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.addActionListener(this);
		add(pictureField, WEST);
		ChangePicture = new JButton("Change Picture");
		add(ChangePicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//Adds friend field to the left of the window//
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		add(friendField, WEST);
		AddFriend = new JButton("Add Friend");
		add(AddFriend, WEST);
		
		//Adds a canvas to the center of the window//
		add(canvas);
		
		
		//Adds an empty database (hashmap of strings and FacePamphlet Profiles)//
		database = new FacePamphletDatabase();
		
		//Sets the current profile (usable for changing status, pictures and adding friends) to null//
		currentProfile = null;
		
		addActionListeners();
		
		
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		
    	Object source = e.getSource();
    	String nameText = nameField.getText();
    	
    	//Adding a friend//
    	if (source == Add && !nameText.equals("")) {
    		if(!database.containsProfile(nameText)){
    			FacePamphletProfile newProfile = new FacePamphletProfile(nameText);
    			currentProfile = newProfile;
    			database.addProfile(newProfile);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A new profile was added: " + newProfile.toString());
    			
    		
    		}else{
    			FacePamphletProfile existingProfile = database.getProfile(nameText);
    			canvas.showMessage("That profile already exists! It is: " + existingProfile.toString() +"" );
    			currentProfile = existingProfile;
    		}
    	}
    	
    	//Deleting a friend//
    	if (source == Delete && !nameText.equals("")){
    		if(database.containsProfile(nameText)){
    			database.deleteProfile(nameText);
    			canvas.showMessage("This profile was deleted: " + nameText);
    			currentProfile = null;
    		}else{
    			canvas.showMessage("The profile " + "\"" +nameText+ "\"" + " couldn't be deleted because it doesn't exist.");
    			currentProfile = null;
    		}
    	}
    	
    	//Looking up a friend//
    	if(source == Lookup && !nameText.equals("")){
    		if(database.containsProfile(nameText)){
    			FacePamphletProfile existingProfile = database.getProfile(nameText);
    			currentProfile = existingProfile;
    			canvas.showMessage("Look up: " + currentProfile.toString());
    			
    		}else{
    			canvas.showMessage("Profile with the name "+ "\"" + nameText + "\"" + " doesn't exist.");
    			currentProfile = null;
    		}
    		
    	}
    	
    	//Changing the status of a current profile, if any//
    	if(source == statusField || source == ChangeStatus){
    		String newStatus = statusField.getText();
    		if(currentProfile != null){
    			currentProfile.setStatus(newStatus);
    			canvas.showMessage(currentProfile.getName() + ": " + currentProfile.getStatus());
    		}else{
    			canvas.showMessage("Select a profile by Adding or Looking up a name before changing a user's status");
    		}
    		
    	}
    	
    	//Changing the profile picture of a current profile, if any//
    	if(source == pictureField || source == ChangePicture){
    		String pictureFileName = pictureField.getText();
    		if(currentProfile != null){
    			GImage image = null;
    			try {
    				image = new GImage(pictureFileName);
    				currentProfile.setImage(image);
    				canvas.showMessage("The picture for " +currentProfile.getName() + " has been set to " +pictureFileName);
    			}catch (ErrorException ex) {
    				canvas.showMessage("That file name won't work.  Make sure you spelled it right?");
    			}
    
    		}else{
    			canvas.showMessage("Select a profile by Adding or Looking up a name before changing a user's picture.");
    		}
    		
    	}
    	
    	//Adding a friend to a current profile, if any//
    	if(source == friendField && !friendField.getText().equals("") || source == AddFriend){
    		String friendName = friendField.getText();
    		if(currentProfile != null){
    			
    			if(currentProfile.getName().equals(friendName)){
    				canvas.showMessage("You can't list yourself as a friend. Dweeb!");
    			}else{
    			
    			
    			if(database.containsProfile(friendName)){
    				if(currentProfile.addFriend(friendName)){
    					currentProfile.addFriend(friendName);
    					FacePamphletProfile thatFriend = database.getProfile(friendName);
    					thatFriend.addFriend(currentProfile.getName());
    					canvas.showMessage(currentProfile.getName() + " and " + friendName + " are now friends!");
    					
    				}else{
    					canvas.showMessage("Those two users are already friends.");
            			
    				}
    				
    			}else{
    				canvas.showMessage("That user hasn't joined FacePamphlet yet.");
    				currentProfile = null;
    			}
    			}
    		}else{
    			canvas.showMessage("Select a profile by Adding or Looking up a name before adding a friend.");
    		}
    	}
	}

    
	private JTextField nameField;
	private JButton Add;
	private JButton Delete;
	private JButton Lookup;
	
	private JTextField statusField;
	private JTextField pictureField;
	private JTextField friendField;
	private JButton ChangeStatus;
	private JButton ChangePicture;
	private JButton AddFriend;
	
	private FacePamphletCanvas canvas = new FacePamphletCanvas();;
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile;
}
