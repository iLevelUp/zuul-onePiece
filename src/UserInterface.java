package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
//import java.awt.image.*;
public class UserInterface implements ActionListener{



	    private GameEngine engine;
	    private JFrame myFrame;
	    private JTextField entryField;
	    private JTextArea log;
	    private JLabel image,solde,bag,life,keys,strength,crew;
		private JButton north,northEast,northWest,east,west,southEast,southWest,south,look,help,back,none;
		private Parser parser;		
	    /**
	     * Construct a UserInterface. As a parameter, a Game Engine
	     * (an object processing and executing the game commands) is
	     * needed.
	     * 
	     * @param gameEngine  The GameEngine object implementing the game logic.
	     */
	    public UserInterface(GameEngine gameEngine)
	    {
	        engine = gameEngine;
	        createGUI();
	        parser = new Parser();
	    }

	    /**
	     * Print out some text into the text area.
	     */
	    public void print(String text)
	    {
	        log.append(text);
	        log.setCaretPosition(log.getDocument().getLength());
	    }

	    /**
	     * Print out some text into the text area, followed by a line break.
	     */
	    public void println(String text)
	    {
	        log.append(text + "\n");
	        log.setCaretPosition(log.getDocument().getLength());
	    }

	    /**
	     * Show an image file in the interface.
	     */
	    public void showImage(String imageName)
	    {
	        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
	        if(imageURL == null)
	            System.out.println("image not found");
	        else {
	            ImageIcon icon = new ImageIcon(imageURL);
	            image.setIcon(icon);
	            myFrame.pack();
	        }
	    }

	    /**
	     * Enable or disable input in the input field.
	     */
	    public void enable(boolean on)
	    {
	        entryField.setEditable(on);
	        if(!on) {
	            entryField.getCaret().setBlinkRate(0);
				look.removeActionListener(this);
				north.removeActionListener(this);
				northEast.removeActionListener(this);
				northWest.removeActionListener(this);
				east.removeActionListener(this);
				west.removeActionListener(this);
				southEast.removeActionListener(this);
				southWest.removeActionListener(this);
				south.removeActionListener(this);
				help.removeActionListener(this);
				back.removeActionListener(this);
				

	        }
	    }

	    /**
	     * Set up graphical user interface.
	     */
	    private void createGUI()
	    {
	        myFrame = new JFrame("One Piece Treasure Cruise");
	        entryField = new JTextField(34);

	        log = new JTextArea();
	        log.setEditable(false);
	        JScrollPane listScroller = new JScrollPane(log);
	        listScroller.setPreferredSize(new Dimension(200, 200));
	        listScroller.setMinimumSize(new Dimension(100,100));

	        JPanel panel = new JPanel();
	        image = new JLabel();
			
			panel.setLayout(new GridBagLayout());
	        
	        GridBagConstraints gbc =new GridBagConstraints();


	        gbc.gridx=0;
	        gbc.gridy=0;
	        gbc.gridheight=1;
	        //gbc.gridwidth=6;
	        gbc.fill=GridBagConstraints.HORIZONTAL;
	        gbc.gridwidth=GridBagConstraints.REMAINDER;
	        panel.add(image,gbc);
	        

	        gbc.gridx=0;
	        gbc.gridy=1;
	        gbc.gridheight=7;
	        gbc.gridwidth=1;
	       	panel.add(listScroller,gbc);
	        
	        gbc.gridx=0;
	        gbc.gridy=8;
	        gbc.gridheight=1;
	        gbc.gridwidth=1;
	        panel.add(entryField,gbc);
			
	       	
	       	JLabel label = new JLabel("Direction Button ",JLabel.CENTER);
	       	gbc.gridx=2;
			gbc.gridy=2;
			gbc.gridheight=1;
	        gbc.gridwidth=3;
			panel.add(label,gbc);

			northWest=new JButton("North West");
			northWest.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=2;
			gbc.gridy=3;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        northWest.addActionListener(this);
			panel.add(northWest,gbc);

			north=new JButton("North");
			north.setPreferredSize(new Dimension(100, 20));
			gbc.gridx=3;
			gbc.gridy=3;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        north.addActionListener(this);
			panel.add(north,gbc);


			northEast=new JButton("North East");
			northEast.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=4;
			gbc.gridy=3;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        northEast.addActionListener(this);
	        panel.add(northEast,gbc);

			west=new JButton("West");
			west.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=2;
			gbc.gridy=4;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        west.addActionListener(this);
			panel.add(west,gbc);

			none=new JButton(" ");
			none.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=3;
			gbc.gridy=4;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			panel.add(none,gbc);

			east=new JButton("East");
			east.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=4;
			gbc.gridy=4;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        east.addActionListener(this);
			panel.add(east,gbc);

			

			southWest=new JButton("South West");
			southWest.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=2;
			gbc.gridy=5;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        southWest.addActionListener(this);
			panel.add(southWest,gbc);			

			south=new JButton("South");
			south.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=3;
			gbc.gridy=5;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        south.addActionListener(this);
			panel.add(south,gbc);

			southEast=new JButton("South East");
			southEast.setPreferredSize(new Dimension(120, 20));
			gbc.gridx=4;
			gbc.gridy=5;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
	        southEast.addActionListener(this);
			panel.add(southEast,gbc);

			bag = new JLabel("Bag 0/250KG",JLabel.LEFT);
	       	gbc.gridx=2;
			gbc.gridy=6;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			panel.add(bag,gbc);

			solde = new JLabel("Solde 50 $",JLabel.LEFT);
	       	gbc.gridx=3;
			gbc.gridy=6;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			panel.add(solde,gbc);

			life = new JLabel("Life 3/3",JLabel.LEFT);
	       	gbc.gridx=4;
			gbc.gridy=6;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			panel.add(life,gbc);

			strength=new JLabel("Strength 30%",JLabel.LEFT);
			gbc.gridx=2;
			gbc.gridy=7;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			panel.add(strength,gbc);

			crew = new JLabel("Crew 50 person",JLabel.LEFT);
	       	gbc.gridx=3;
			gbc.gridy=7;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			panel.add(crew,gbc);

			keys = new JLabel("MagicKeys 0/4",JLabel.LEFT);
	       	gbc.gridx=4;
			gbc.gridy=7;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			panel.add(keys,gbc);

			help=new JButton("Help");
			help.setPreferredSize(new Dimension(80, 20));
			gbc.gridx=2;
			gbc.gridy=8;
		    gbc.gridheight=1;
	        gbc.gridwidth=1;
	        help.addActionListener(this);
			panel.add(help,gbc);


			back=new JButton("Back");
			back.setPreferredSize(new Dimension(60, 20));
			gbc.gridx=3;
			gbc.gridy=8;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			back.addActionListener(this);
			panel.add(back,gbc);

			look=new JButton("Look");
			look.setPreferredSize(new Dimension(80, 20));
			gbc.gridx=4;
			gbc.gridy=8;
			gbc.gridheight=1;
	        gbc.gridwidth=1;
			look.addActionListener(this);
			panel.add(look,gbc);

			

	        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

	        // add some event listeners to some components
	        myFrame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {System.exit(0);}
	        });

	        entryField.addActionListener(this);
	        myFrame.pack();
	        myFrame.setVisible(true);
	        entryField.requestFocus();
	    }

	    /**
	     * Actionlistener interface for entry textfield.
	     */
	    public void actionPerformed(ActionEvent e) 
	    {
	        // no need to check the type of action at the moment.
	        // there is only one possible action: text entry
	        Object  source=e.getSource();
			if  (source==north)
	        	engine.interpretCommand(new Command(CommandWord.GO,"north"));

	        else if(source == northWest)
	        	engine.interpretCommand(new Command(CommandWord.GO,"northWest"));

	        else if(source == northEast)
	        	engine.interpretCommand(new Command(CommandWord.GO,"northEast"));

	        else if(source == west)
	        	engine.interpretCommand(new Command(CommandWord.GO,"west"));

	        else if(source == east)
	        	engine.interpretCommand(new Command(CommandWord.GO,"east"));

	        else if(source == southWest)
	        	engine.interpretCommand(new Command(CommandWord.GO,"southWest"));

	        else if(source == southEast)
	        	engine.interpretCommand(new Command(CommandWord.GO,"southEast"));

	        else if(source == south)
	        	engine.interpretCommand(new Command(CommandWord.GO,"south"));

	        else if(source == help)
	        	engine.interpretCommand(new Command(CommandWord.HELP,""));

	        else if(source == look)
	        	engine.interpretCommand(new Command(CommandWord.LOOK,""));

	        else if(source == back)
	        	engine.interpretCommand(new Command(CommandWord.BACK,""));

	        else 
	        	processCommand();
		}
		
		public void setSolde(int x){
			String newSolde="Solde "+Integer.toString(x)+" $";
			solde.setText(newSolde);
		}

		public void setBagContain(int x,int y){
			String newBagContain="Bag "+Integer.toString(x) +"/"+Integer.toString(y)+"KG";
			bag.setText(newBagContain);
		}

		public void setLife(int x){
			String newSolde="Life "+Integer.toString(x)+"/3";
			solde.setText(newSolde);
		}

		public void setCrew(int x){
			String newCrew="Crew "+Integer.toString(x)+" Person";
			crew.setText(newCrew);
		}

		public void setStrength(int x){
			String newStrength="Strength "+Integer.toString(x)+ "%";
			strength.setText(newStrength);
		}

		public void setKeys(int x ){
			String newKeys="Keys "+Integer.toString(x)+"/4";
			keys.setText(newKeys);
		}	
	    /**
	     * A command has been entered. Read the command and do whatever is 
	     * necessary to process it.
	     */
	    private void processCommand()
	    {
	        //boolean finished = false;
	        String line = entryField.getText();
	        entryField.setText("");
	        println(line);
	        engine.interpretCommand(parser.getCommand(line));
	    }
}


