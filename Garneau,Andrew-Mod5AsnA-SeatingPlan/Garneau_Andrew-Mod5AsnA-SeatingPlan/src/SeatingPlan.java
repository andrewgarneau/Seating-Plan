/**
 * Mod 5 Assignment Seating Plan
 * This program is an easy to use application for a seating plan
 * Andrew Garneau
 * Ms McDougall ICS4U
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Scanner;
import javafx.scene.input.MouseEvent;

public class SeatingPlan extends Application {

	
	
	static int rows=1,columns=1 ;
	static ImageView[][] iv1;
	static Desk[][]desks;
	static Rectangle[][]clickBoxes;
	static boolean accessible = false;
	static int p=0;
	static int selectedRow=0,selectedColumn=0;
	
	
	
	//main class launches the program
	public static void main (String [] args){
		launch(args);
	}
	
	
	//start method runs entire program with the window	
	@Override
	public void start (Stage theStage){
		
		//sets up entire form
		theStage.setTitle("Seating Plan");
		Group root = new Group();
		Scene theScene = new Scene(root);
		theStage.setScene(theScene);
		theStage.setResizable(false);
		theStage.sizeToScene();
		Canvas canvas = new Canvas(310,150);
		theScene.setFill(Color.DARKSEAGREEN);
		root.getChildren().add(canvas);
		theStage.show();
		
		
//		used to display mouse location on screen. Commented out so it can be reused on demand.
//		Label mouseLocation = new Label(); 
//		root.getChildren().add(mouseLocation);
//		theScene.setOnMouseMoved(
//				new EventHandler<MouseEvent>(){
//				
//					public void handle(MouseEvent e){
//						
//						String mousePosition = "("+e.getX()+ "," + e.getY() + ")";
//						
//						mouseLocation.setLayoutX(100);
//						mouseLocation.setLayoutY(500);
//						mouseLocation.setTextFill(Color.BLACK);
//						mouseLocation.setFont(Font.font("Cambria", FontWeight.BOLD,20));
//						mouseLocation.setText(mousePosition);
//
//					}
//				});
		
		
		//all label, button, textField, and rectangle groups of code, so objects can be created.
		Label titleText = new Label("Setup");
		titleText.setFont(Font.font("Ustaah",30));
		titleText.setTextFill(Color.BLACK);
		titleText.setLayoutX(105);
		titleText.setLayoutY(25);
		root.getChildren().add(titleText);
		
		Label titleHelp = new Label("Rows and Columns must be between 1 and 10.");
		titleHelp.setFont(Font.font("Ustahh",12));
		titleHelp.setTextFill(Color.BLACK);
		titleHelp.setLayoutX(30);
		titleHelp.setLayoutY(130);
		root.getChildren().add(titleHelp);
		
		Button btnDone = new Button();
		btnDone.setText("DONE");
		btnDone.setLayoutX(245);
		btnDone.setLayoutY(88);
		btnDone.setFont(Font.font("Tahoma"));
		root.getChildren().add(btnDone);
		btnDone.setDisable(false);
		
		Label labelRows = new Label("Rows:");
		labelRows.setFont(Font.font("Utsaah",14));
		labelRows.setTextFill(Color.BLACK);
		labelRows.setLayoutX(25);
		labelRows.setLayoutY(75);
		root.getChildren().add(labelRows);
		
		final TextField rowBox = new TextField();
		rowBox.setLayoutX(90);
		rowBox.setLayoutY(75);
		rowBox.setFont(Font.font("Tahoma"));
		root.getChildren().add(rowBox);
		
		Label labelColumns = new Label("Columns:");
		labelColumns.setFont(Font.font("Utsaah",14));
		labelColumns.setTextFill(Color.BLACK);
		labelColumns.setLayoutX(25);
		labelColumns.setLayoutY(100);
		root.getChildren().add(labelColumns);
		
		final TextField columnBox = new TextField();
		columnBox.setLayoutX(90);
		columnBox.setLayoutY(100);
		columnBox.setFont(Font.font("Tahoma"));
		root.getChildren().add(columnBox);
		
		Label displayName = new Label("Name:");
		displayName.setFont(Font.font("Utsaah",20));
		displayName.setLayoutX(30);
		displayName.setVisible(false);
		root.getChildren().add(displayName);
		
		Label userName = new Label();
		userName.setFont(Font.font("Utsaah",20));
		userName.setLayoutX(100);
		userName.setVisible(false);
		root.getChildren().add(userName);
		
		Button btnAddName = new Button("Edit Name");
		btnAddName.setFont(Font.font("Utsaah"));
		root.getChildren().add(btnAddName);
		btnAddName.setVisible(false);
		
		final TextField nameBox = new TextField();
		nameBox.setFont(Font.font("Utsaah"));
		nameBox.setLayoutX(100);
		nameBox.setVisible(false);
		nameBox.setDisable(true);
		root.getChildren().add(nameBox);
		
		Button btnSave = new Button("Save");
		btnSave.setFont(Font.font("Utsaah"));
		root.getChildren().add(btnSave);
		btnSave.setDisable(true);
		btnSave.setVisible(false);
		
		Label empty = new Label();
		empty.setFont(Font.font("Utsaah",18));
		empty.setLayoutY(85);
		Rectangle emptyRect = new Rectangle(97,30);
		emptyRect.setLayoutY(85);
		emptyRect.setFill(Color.LIGHTCORAL);
		emptyRect.setVisible(false);
		root.getChildren().add(emptyRect);
		root.getChildren().add(empty);
		
		Label full = new Label();
		full.setFont(Font.font("Utsaah",18));
		full.setLayoutY(135);
		Rectangle fullRect = new Rectangle(97,30);
		fullRect.setLayoutY(135);
		fullRect.setFill(Color.MEDIUMSEAGREEN);
		fullRect.setVisible(false);
		root.getChildren().add(fullRect);
		root.getChildren().add(full);
		
		Label selection = new Label();
		selection.setFont(Font.font("Utsaah",18));
		selection.setLayoutY(185);
		Rectangle selectionRect = new Rectangle(97,30);
		selectionRect.setLayoutY(185);
		selectionRect.setFill(Color.LIGHTBLUE);
		selectionRect.setVisible(false);
		root.getChildren().add(selectionRect);
		root.getChildren().add(selection);
		
		
		
		//events handled on the done button being pressed
		btnDone.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent f){
				
				boolean passR=false, passC=false;
				Image iDesks = new Image("schoolDesk.png");
				
				//sets the text in rows and columns to the variable amount of rows and columns there are.
				String strRows = rowBox.getText();
				String strColumns = columnBox.getText();
				//prevents program from crashing with wrong data type
				try{
					rows = Integer.parseInt(strRows);
					passR=true;
				} catch (Exception h){
				}
				try{
					columns = Integer.parseInt(strColumns);
					passC=true;
				} catch (Exception g){	
				}
				
				//checks to see if numbers are within a proper range
				boolean passN = false;
				if ((columns <=0||rows<=0)||(columns>10 || rows >10)){
					passN=false;
				}else{
					passN=true;
				}
				
				
				//when correct fields are entered, all actions are triggered
				if (passR==true && passC==true && passN==true){
					//sets preferred size to the form
					if (columns<4){
						theStage.setWidth(360+75);
						titleText.setLayoutX(55);
					}else if (columns>=4){
						theStage.setWidth((60*columns)+120+75);
					}
					if (rows<4){
						theStage.setHeight(360);
						titleText.setLayoutX(55); //190 for "Classroom Seat Planner"
					}else if (rows>=4){
						theStage.setHeight(120+(60*rows));
					}
					
					//removes all objects from previous form information
					root.getChildren().remove(btnDone);
					root.getChildren().remove(labelRows);
					root.getChildren().remove(labelColumns);
					root.getChildren().remove(columnBox);
					root.getChildren().remove(rowBox);
					root.getChildren().remove(titleHelp);
					
					//resets title text
					titleText.setLayoutX((theStage.getWidth()-250)/2);
					titleText.setFont(Font.font("Ustaah",24));
					titleText.setText("Classroom Seat Planner");
					
					//displays empty seat tally
					empty.setText("Empty: "+columns*rows);
					empty.setLayoutX(theStage.getWidth()-110);
					emptyRect.setLayoutX(theStage.getWidth()-115);
					emptyRect.setVisible(true);
					
					//displays full seat tally
					full.setText("Full: 0");
					full.setLayoutX(theStage.getWidth()-110);
					fullRect.setLayoutX(theStage.getWidth()-115);
					fullRect.setVisible(true);
					
					//displays seat location layout
					selection.setText("Seat: ");
					selection.setLayoutX(theStage.getWidth()-110);
					selectionRect.setLayoutX(theStage.getWidth()-115);
					selectionRect.setVisible(true);
					
					//creates the image holder, desk, and rectangle 2d arrays.
					iv1=new ImageView[rows][columns];
					desks=new Desk[rows][columns];
					clickBoxes=new Rectangle[rows][columns];
					
					//for loop that creates and displays all visual and desk elements
					for (int i = 0;i<desks.length;i++){
						for (int j = 0;j<desks[0].length;j++){
							//initializes all arrays
							iv1[i][j]=new ImageView();
							desks[i][j]=new Desk(i+1,j+1);
							clickBoxes[i][j]=new Rectangle(60+(j*60),80+(i*50),50,40);
							
							//draw and set up imageview
							iv1[i][j].setLayoutX(60+(j*60));
							iv1[i][j].setLayoutY(80+(i*50));
							iv1[i][j].setImage(iDesks);
							iv1[i][j].setFitWidth(50);
							iv1[i][j].setPreserveRatio(true);
							iv1[i][j].setSmooth(true);
							iv1[i][j].setCache(true);
					       
							//sets up the hit boxes for clicks.
							clickBoxes[i][j].setFill(Color.LIGHTCORAL);
							clickBoxes[i][j].setVisible(true);
							//draw objects on screen
					        root.getChildren().add(clickBoxes[i][j]);
					        root.getChildren().add(iv1[i][j]);
						}
					}
				}
				//accessible variable is set to enable rest of program
				accessible=true;
			}
		});
		
		
		
		//all events causes on the click of the mouse
		theScene.setOnMouseClicked(
				new EventHandler<MouseEvent>(){
					public void handle(MouseEvent e){
						
//						System.out.println(clickBoxes[2][2].getX());
//						System.out.println(clickBoxes[2][2].getY());
//						System.out.println(e);
						//if statement for events after done button is pressed (triggers p to be 1)
						if (p == 1){
							//large nested for loop and if statements that only change selected hit box to blue, but others to their default.
							for (int i = 0;i<desks.length;i++){
								for (int j = 0;j<desks[0].length;j++){
									//if the desk is clicked upon, then a loop that colors all the desks is ran.
									if(clickBoxes[i][j].contains(e.getX() , e.getY())){
										for(int x=0;x<desks.length;x++) {
											for(int y=0;y<desks[0].length;y++) {
												//if empty, desk is red
												clickBoxes[x][y].setFill(Color.LIGHTCORAL);
												//if filled, desk is green
												if(desks[x][y].getOccupied()) {
													clickBoxes[x][y].setFill(Color.MEDIUMSEAGREEN);
												}
											}
										}
										
										//updates the selection label for the user
										selection.setText("Seat: "+desks[i][j].getLocation());
										
										//global selected variables are set, and needed to fit in scope.
										selectedRow=i;
										selectedColumn=j;
							
										//updates all of the info on the screen whent the mouse is clicked.
										//name info and buttons and elements for that function
										
										userName.setText(desks[i][j].getName());
										userName.setLayoutY(theStage.getHeight()-70);
										userName.setVisible(true);
										nameBox.setLayoutY(theStage.getHeight()-70);
										nameBox.setVisible(false);
										nameBox.setDisable(true);
										btnAddName.setLayoutX(theStage.getWidth()-100);
										btnAddName.setLayoutY(theStage.getHeight()-70);
										btnAddName.setDisable(false);
										btnAddName.setVisible(true);
										btnSave.setLayoutX(theStage.getWidth()-90);
										btnSave.setLayoutY(theStage.getHeight()-70);
										btnSave.setDisable(true);
										btnSave.setVisible(false);
										displayName.setVisible(true);
										displayName.setLayoutY(theStage.getHeight()-70);
										
										//desk hit boxes show they are selected
										clickBoxes[i][j].setFill(Color.LIGHTBLUE);
									}
								}
							}
						}
						
						//when the done button is clicked, the p variable is set to 1 to activate the events on clicks on the main screen of program
						if(accessible == true){
							p=1;
						}
					}
				});
		
		
		
		// all events on add name button
		btnAddName.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent f){
				
				//add name button is disabled and hidden, and the save and name entering field are shown.
				btnAddName.setDisable(true);
				btnAddName.setVisible(false);
				btnSave.setDisable(false);
				btnSave.setVisible(true);
				nameBox.setVisible(true);
				nameBox.setDisable(false);
				
			}
		});
		
		
		
		//all events on the save button
		btnSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent f){
				
				//sets selected desk to the name in the name field
				desks[selectedRow][selectedColumn].setName(nameBox.getText());
				//hides and disables the name field, and the save button.
				btnSave.setDisable(true);
				btnSave.setVisible(false);
				nameBox.setVisible(false);
				nameBox.setDisable(true);
				
				//displays the name of the person in the desk.
				userName.setText(desks[selectedRow][selectedColumn].getName());
				
				//if the desk has a valid name entered, it is green. If not it stays red.
				if (desks[selectedRow][selectedColumn].getOccupied()) {
					
					clickBoxes[selectedRow][selectedColumn].setFill(Color.MEDIUMSEAGREEN);
				}else {
					clickBoxes[selectedRow][selectedColumn].setFill(Color.LIGHTCORAL);
				}
				
				int countFull=0;
				int countEmpty=0;
				
				//counts the amount of desks that have someone in them, and the amount that are empty
				for(int i =0;i<desks.length;i++) {
					for(int j=0;j<desks[0].length;j++) {
						if(desks[i][j].getOccupied()) {
							countFull++;
						}else {
							countEmpty++;
						}
					}
				}
				
				//displays empty and full desk amounts.
				empty.setText("Empty: "+ countEmpty);
				full.setText("Full: "+countFull);
			}
		});
		
	}
}
