package com.storyteller.java;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class App extends JFrame {

    public String substitutionText;
    public JLabel logo;
    public JLabel motto;
    public JTextField name;
    public JTextField selected;
    private JLabel sceneLabel;
    private JLabel characterName;
    private int count = 0;
    public String[] scene = {"Scene:","Horror", "Christmas", "Easter", "New Years", "Death"};
    public String[] characters = {"# of Characters:","1", "2", "3", "4", "5", "6"};
    public String[] humor = {"Humor:", "", ""};
    //Buttons:
    private JButton btnDo;

    /**
     * Maybe add this to its own class, along with the resizable = false thingo, so then every class can use it without
     * recoding it.
     */
    public static int WIDTH = 300;
    public static int HEIGHT = 300;
    //End add to own class
    //DONT ADD THIS \/\/\/ TO THAT CLASS
    public JComboBox comboBox;


    public App() {


        /**
         * Lets try to make this resizeable, so then it can go to any screen.
         */

        getContentPane().setLayout(null);
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        setTitle("Story Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        //Set gui elements:
        logo = new JLabel("Story Teller");//new
        motto = new JLabel("How have you changed the world?");//new
        btnDo = new JButton("Do");
        characterName = new JLabel("Name: ");
        name = new JTextField(10);
        sceneLabel = new JLabel("Scene: ");
        selected = new JTextField(40);
        comboBox = new JComboBox();
        JComboBox charactersBox = new JComboBox();
        JComboBox humorBox = new JComboBox();

        //Handle resize event:
        addComponentListener(new FrameListen());

        //Layout gui elements:
        positionElements(WIDTH, HEIGHT);

        for (int i = 0; i < scene.length; i++) {
            comboBox.addItem(scene[count++]);
        }
        /*
        for (int i = 0; i < characters.length; i++) {
            charactersBox.addItem(scene[count++]);
        }
        for (int i = 0; i < humor.length;)
        */

        selected.setEditable(false);

        //Event listener
        btnDo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Yay made this work
                substitutionText = App.this.name.getText();
                String selectedItem = (String) App.this.comboBox.getSelectedItem();
                Finished finished = new Finished(substitutionText, selectedItem);
            }
        });
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setText("You Selected : " + ((JComboBox) e.getSource()).getSelectedItem());
            }
        });
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                substitutionText = App.this.name.getText();
                String selectedItem = (String) App.this.comboBox.getSelectedItem();
                Finished finished = new Finished(substitutionText, selectedItem);
            }
        });

        //Add all gui elements to content pane:
        getContentPane().add(btnDo);
        getContentPane().add(characterName);
        getContentPane().add(name);
        getContentPane().add(selected);
        getContentPane().add(sceneLabel);
        getContentPane().add(comboBox);
        getContentPane().add(logo);
        getContentPane().add(motto);
        //getContentPane().add(characters);
        //getContentPane().add(humor);
    }

    private class Line {
        public Line() {
            ;
        }
    }


    private void positionElements(int width, int height) {//TODO: percentage width and height to fill entire jframe for any size.
        WIDTH = width;
        HEIGHT = height;
        //...
        int doWidth = 80;
        int doHeight = 20;

        int stdHeight = 23;
        //Get text widths:
        int charNameWidth = (int) characterName.getPreferredSize().getWidth();
        int sceneLabelWidth = (int)sceneLabel.getPreferredSize().getWidth();
        int logoWidth = (int) logo.getPreferredSize().getWidth();
        int mottoWidth = (int) motto.getPreferredSize().getWidth();
        //Some constants:
        int inpWidth = 200;//same for chooser
        int inpSpace = 20;
        int fromTop = 20;
        int stdSpace = 10;
        //For convienience:
        int tempLineWidth;
        int tempLineStart;
        int stateTop = fromTop;//From top currently.
        //Start actual layout (putting things in place):
        //Add logo:
        logo.setBounds(WIDTH/2-logoWidth/2, stateTop, logoWidth, stdHeight);//stateTop for all...
        stateTop += stdHeight+5;//+5 for motto below logo, +stdSpace normally
        //Add motto:
        motto.setBounds(WIDTH/2-mottoWidth/2, stateTop, mottoWidth, stdHeight);
        stateTop += stdHeight+stdSpace;
        //Add character name line:
        tempLineWidth = inpWidth+charNameWidth+inpSpace;
        tempLineStart = WIDTH/2-tempLineWidth/2;
        characterName.setBounds(tempLineStart, stateTop, charNameWidth, stdHeight);
        name.setBounds(tempLineStart+charNameWidth+inpSpace, stateTop, inpWidth, stdHeight);
        stateTop += stdHeight+stdSpace;
        //Add scene input: TODO: seperate comboBox add.
        tempLineWidth = inpWidth+inpSpace+sceneLabelWidth;
        tempLineStart = WIDTH/2-tempLineWidth/2;
        sceneLabel.setBounds(tempLineStart, stateTop, sceneLabelWidth, stdHeight);
        selected.setBounds(tempLineStart+inpSpace+sceneLabelWidth, stateTop, inpWidth, stdHeight);
        stateTop += stdHeight+stdSpace;
        comboBox.setBounds(tempLineStart+inpSpace+sceneLabelWidth, stateTop, inpWidth, stdHeight);
        stateTop += stdHeight+stdSpace;//Not needed, but easier for later additions.
        //...
        //Add do button:
        btnDo.setBounds(WIDTH/2-doWidth/2, HEIGHT-doHeight-30, doWidth, doHeight);//30 from bottom.
    }

    private class FrameListen implements ComponentListener{
        public void componentHidden(ComponentEvent arg0) {
        }
        public void componentMoved(ComponentEvent arg0) {
        }
        public void componentShown(ComponentEvent arg0) {

        }

        @Override
        public void componentResized(ComponentEvent e) {
            positionElements(getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        App frame = new App();
        frame.setVisible(true);
    }
}