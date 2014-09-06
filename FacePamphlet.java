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
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

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
		
		//Adds picture button and label to the left of the window
		pictureField = new JLabel("No Image", SwingConstants.RIGHT);
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
    	
    	//Adding a new profile//
    	if (source == Add && !nameText.equals("")) {
    		if(!database.containsProfile(nameText)){
    			FacePamphletProfile newProfile = new FacePamphletProfile(nameText);
    			currentProfile = newProfile;
    			database.addProfile(newProfile);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A new profile was added: " + newProfile.getName());
    			pictureField.setText(currentProfile.getShortFileName());
    			
    			
    		
    		}else{
    			FacePamphletProfile existingProfile = database.getProfile(nameText);
    			currentProfile = existingProfile;
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("That profile already exists! It is currently displayed." );
    			pictureField.setText(currentProfile.getShortFileName());
    		}
    	}
    	
    	//Deleting a friend//
    	if (source == Delete && !nameText.equals("")){
    		if(database.containsProfile(nameText)){
    			database.deleteProfile(nameText);
    			canvas.removeProfile();
    			canvas.showMessage("This profile was deleted: " + nameText);
    			currentProfile = null;
    		}else{
    			
    			canvas.showMessage("The profile " + "\"" +nameText+ "\"" + " couldn't be deleted because it doesn't exist.");
    			currentProfile = null;
    			pictureField.setText("No Image");
    		}
    	}
    	
    	//Looking up a friend//
    	if(source == Lookup && !nameText.equals("")){
    		if(database.containsProfile(nameText)){
    			FacePamphletProfile existingProfile = database.getProfile(nameText);
    			currentProfile = existingProfile;
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Displaying " + currentProfile.getName());
    			if(currentProfile.getImage() == null){
    				pictureField.setText("No Image");
    			}else{
    			pictureField.setText(currentProfile.getShortFileName());
    			}
    		}else{
    			canvas.removeProfile();
    			canvas.showMessage("Profile with the name "+ "\"" + nameText + "\"" + " doesn't exist.");
    			currentProfile = null;
    		}
    		
    	}
    	
    	//Changing the status of a current profile, if any//
    	if(source == statusField || source == ChangeStatus){
    		String newStatus = statusField.getText();
    		if(currentProfile != null){
    			currentProfile.setStatus(newStatus);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage(currentProfile.getName() + ": " + currentProfile.getStatus());
    		}else{
    			canvas.showMessage("Select a profile by Adding or Looking up a name before changing a user's status");
    		}
    		
    	}
    	
    	//Changing the profile picture of a current profile, if any//
    	if(source == ChangePicture){
    		if(currentProfile != null){
    			GImage image = null;
    			BufferedReader rd = null;
    			JFileChooser chooser = new JFileChooser();
    			 FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
    			 chooser.setFileFilter(filter); 
    			 
    			int result = chooser.showOpenDialog(this);
    			if(result == JFileChooser.APPROVE_OPTION){
    				try{
    					file = chooser.getSelectedFile();
    					rd = new BufferedReader(new FileReader(file));
    					image = new GImage(""+file+"");     
    				              
    				}catch (IOException ex){
    					canvas.showMessage("That file name won't work.  Make sure you spelled it right?");
    				}
    				currentProfile.setImage(image);
    				canvas.displayProfile(currentProfile);
    				shortFileName = file.toString();
    				shortFileName = shortFileName.substring(file.toString().lastIndexOf("/") + 1);
    				pictureField.setText(shortFileName);
    				currentProfile.setShortFileName(shortFileName);
    				canvas.showMessage("The picture for " +currentProfile.getName() + " has been set to " + shortFileName);
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
    					canvas.displayProfile(currentProfile);
    					
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
	private JLabel pictureField;
	private JTextField friendField;
	private JButton ChangeStatus;
	private JButton ChangePicture;
	private JButton AddFriend;
	
	private FacePamphletCanvas canvas = new FacePamphletCanvas();;
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile;
	
	private String shortFileName;
	private File file;
}
