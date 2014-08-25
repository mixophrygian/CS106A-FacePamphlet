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
		// Adds interactors to the top and left side of the window//
		add(new JLabel("Name"), NORTH);
		textField = new JTextField(TEXT_FIELD_SIZE);
		textField.addActionListener(this);
		add(textField, NORTH);
		Add = new JButton ("Add");
		Delete = new JButton ("Delete");
		Lookup = new JButton ("Lookup");
		add(Add, NORTH);
		add(Delete, NORTH);
		add(Lookup, NORTH);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
	}

    
	private JTextField textField;
	private JButton Add;
	private JButton Delete;
	private JButton Lookup;
}
