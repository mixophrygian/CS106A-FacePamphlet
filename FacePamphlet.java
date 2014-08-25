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

public class FacePamphlet extends ConsoleProgram
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
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	Object source = e.getSource();
    	if (source == Add) {
    		println("Add: " + nameField.getText() + ".");
    	}
    	if (source == Delete){
    		println("Delete: " + nameField.getText() + ".");
    	}
    	if(source == Lookup){
    		println("Look up: " + nameField.getText() + ".");
    	}
    	if(source == statusField || source == ChangeStatus){
    		println("Status: " + statusField.getText() + ".");
    	}
    	if(source == pictureField || source == ChangePicture){
    		println("Picture: " + pictureField.getText() + "");
    	}
    	if(source == friendField || source == AddFriend){
    		println("Add friend: " + friendField.getText() + "");
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
	
	private FacePamphletCanvas canvas;
}
