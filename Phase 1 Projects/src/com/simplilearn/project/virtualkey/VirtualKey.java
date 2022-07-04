/* **********************************************
 * End-of-Phase 1 Project: Virtual Key Repository
 * Student: Nia Kelley
 * Code Finished Date: 02 July 2022
 * Code Started Date: 26 June 2022 (draft version) * 
 * ********************************************* */
package com.simplilearn.project.virtualkey;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class VirtualKey {
	
	//class instance variables
	private static String targetDirectory = null;	
	private static String targetFileName = null;	
	private static Scanner sc;
	private static Path myFile;	
	private static List<File> fileList = new ArrayList<File>();
	private static int numFilesinDirectory = 0;	 
	
	//Private Setter Methods (overloading)
	private static void setTargetDirectory(Scanner s) {
		System.out.println("Enter the target directory for LockedMe.com to use...");		
		targetDirectory = s.next();
	}
	
	private static void setTargetDirectory() {				
		targetDirectory = "C:\\test";
	}

	public static void main(String[] args) {	
	
		openScanner();
		welcomeScreen();
		try {
			Thread.sleep(2000); // set time delay for 2 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		try {
			System.out.println("Do you want to run in TEST mode (using C:\\test) - y or n?");
			String test = sc.next();
			
			
			//Set the target directory
			if(test.equalsIgnoreCase("y"))
				setTargetDirectory();
			else if(test.equalsIgnoreCase("n"))
				setTargetDirectory(sc);
			else {
				System.out.println("Invalid directory...");
				System.out.println("Terminating Application");
				System.exit(1);
			}
		}catch(InputMismatchException e) {
			sc.nextLine(); //clearing out the buffer
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mainMenu();		
		closeScanner();
        
	}//end main()
	
	/* ************************************* * 
	 *      Private Static Class Methods     *
	 * ************************************* */	
	private static void openScanner() {
		//Open a Scanner to read input from the console
		sc = new Scanner(System.in);
	}
	
	private static void closeScanner() {
		sc.close();
	}
	
	private static void welcomeScreen() {
		
		System.out.println("\n************************************************\n");
		System.out.println("Welcome to the Virtual Keys Repository of LockedMe.com");
		System.out.println("Version: 1.0 PROTOTYPE");
		System.out.println("Client: Lockers Pvt. Ltd");
		System.out.println("Full Stack Developer Name: Nia Kelley Jester");
		System.out.println("************************************************\n");		
		
	}//end welcome()
	
	private static void mainMenu() 
	{
		int mainMenuChoice = 0;		
		String appState = "c";			
		
		System.out.println("You specified the following target directory: " + targetDirectory);
		buildFileList();
		
		while(appState.equalsIgnoreCase("c"))		
		{
			//Display the Main Menu Options
			try {
			mainMenuChoice = mainMenuOptions();
			System.out.println("Selected main menu option: " + mainMenuChoice);
							
				switch(mainMenuChoice) 
				{
					case 1:
						System.out.println("Retrieve files from " + targetDirectory);
						retrieveFiles();					
						break;
					case 2:
						businessOptionsMenu();
						break;
					case 3:
						System.out.println("Closing application...");				
						System.exit(1);
					default:
						System.out.println("Please enter a valid option..");
						break;
				}//end switch
			
			}catch(InputMismatchException e) {
				sc.nextLine();				
			}//end catch
			catch(Exception e) {
				e.printStackTrace();
			}//end catch
			
			try {
			System.out.println("Enter 'c' to continue, 'x' to quit: ");
			appState = sc.next();			
			}catch(InputMismatchException e) {
				System.out.println("you entered invalid input");
				sc.nextLine();
			}catch(Exception e) {
				e.printStackTrace();
			}
				
		}//end while
		
		if(appState.equalsIgnoreCase("x")) {
			System.out.println("Quitting the application...");
			System.exit(1);
		}
		else {
			System.out.println("Entered invalid sequence...Quitting the application...");
			System.exit(1);
		}
		
	}//end mainMenu()	
	
	private static int mainMenuOptions() {
		int r=0; //added this to handle any exceptions
			
		String[] options = {"************************************************",
							"*               MAIN MENU                      *",
							"************************************************",
							"1. Display the current file names in ASCENDENING order",
							"2. Open Business Level Operations Menu",
							"3. Close the application",
							"************************************************"
					};
		
		//Display Main Menu Options
		for(int i=0; i<options.length;i++) 
		{
			System.out.println(options[i]);
		}
				
		try {
				System.out.println("Choose your option...");
				r = sc.nextInt();
				//couldn't return r from here; have to move it outside of the try-catch block
		}catch(InputMismatchException e) {			
			sc.nextLine(); //consuming the input that was causing the exception, clearing the input stream, and allowing the user to input something again
		}catch(Exception e) {
			e.printStackTrace();
		}//end catch		
		return r;
	}//end mainMainOptions()
	
	private static void retrieveFiles() throws IOException {
		
		int count=0;
		Path dirPath = Paths.get(targetDirectory);
		
		if(Files.exists(dirPath) && Files.isDirectory(dirPath)) 
		{
			System.out.println("Directory: " + dirPath.toAbsolutePath());
			System.out.println("Files: ");
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath);
			for(Path p:dirStream) {
				if(Files.isRegularFile(p)) 
				{
					System.out.println(p.getFileName()); //already sorted in ascending order				
					count++;
				}
			}//end for
		}//end if		
		
		numFilesinDirectory = count;
		
		System.out.println("There are " + numFilesinDirectory + " files in the directory.");
			
	}//end retrieveFiles
		
	private static void businessOptionsMenu() {
	
		boolean loop = true;
	
		while(loop) 
		{
			System.out.println("................................................");
			System.out.println(".     Business Level Options Sub-Menu          .");
			System.out.println("................................................");
			
			
			String[] options = {"1. Add new file",
								"2. Delete File (case sensisitive)",
								"3. Search for File (case sensisitive)",
								"4. Go back to main menu",
								"................................................"
								};
			
			//Display the Business Level Options Menu to the Console
			for(int i=0; i<options.length;i++) 
			{
				System.out.println(options[i]);
			}
	
			try {
				//User will input their selection
				System.out.println("Choose your option...");				
				int y = sc.nextInt();			
		
				switch(y) 
				{
					case 1:							
						System.out.println("Specify the file name to ADD to " + targetDirectory+ ": ");
						targetFileName = sc.next();
						myFile = Paths.get(targetDirectory + "\\" + targetFileName);						
						addFile();
						break;
					case 2:							
						System.out.println("Specify the file name to DELETE from " + targetDirectory + ": ");
						targetFileName = sc.next();
						myFile = Paths.get(targetDirectory + "\\" + targetFileName);
						deleteFile();
						break;
					case 3:							
						System.out.println("Specify the file name to SEARCH from " + targetDirectory + ": ");
						targetFileName = sc.next();
						myFile = Paths.get(targetDirectory + "\\" + targetFileName);
						System.out.println("Searching for file: " + targetFileName);
						searchForFile();
						break;
					case 4:
						loop = false;
						mainMenu();
						break;
					default:
						System.out.println("Please enter a valid option...");
						break;
				}//end switch		
			} catch(InputMismatchException e) {
				System.out.println("You entered invalid input! Try again..");
				sc.nextLine();	//consuming the input that was causing the exception, clearing the input stream, and allowing the user to input something again			
			}//end catch()
			catch (Exception e){
				e.printStackTrace();
			}//end catch()
		}//end while
	
	}//end businessOptionsMenu()
	
	private static void addFile() throws IOException{
		/* the requirement specifically  stated "You can ignore the case sensitivity of the file names*/	
		Path filePath = Paths.get(targetDirectory, targetFileName);
		
		if(Files.notExists(filePath))
		{ 
			Files.createFile(filePath);
			System.out.println("File created successfully!"); //doesn't distinguish between NIA.html and nia.html are the same. okay per requirement
			buildFileList(); //re-building the fileList after the addition
			Collections.sort(fileList); //sort the resultant ArrayList in ascending order
		}
		else {
			System.out.println("File already exists");
		}				
	}//end addFile()
	
	private static void deleteFile() throws IOException {
		
		//The requirement was to implement case sensitive file delete functionality
		File dirTest = new File(targetDirectory);		
		
		if(Files.exists(myFile)) 
		{
			System.out.println("The file exists already...");
			System.out.println("Do you really want to delete - y or n?");
			String proceed = sc.next();
			
			if(proceed.equalsIgnoreCase("y")) 
			{
				for(File f : dirTest.listFiles()) {
					if(f.getCanonicalFile().getName().equals(myFile.getFileName().toString())) 
					{
						Files.deleteIfExists(myFile);
						System.out.println("File deleted Successfully");
						buildFileList(); //re-building the fileList after the deletion
						Collections.sort(fileList); //sort the resultant ArrayList in ascending order						
					}//end canonical check
				
				}//end for
			}
			else {
				System.out.println("Not deleting the file per your request...");				
				}
		}//end if(proceed)
		else
			System.out.println("File not found in " + dirTest + " ....");
		
	}//end deleteFile()	
	
	private static void searchForFile() {
		
		File dirTest = new File(targetDirectory);
		boolean fnf = true;		
			
			for(File f : dirTest.listFiles()) 
			{				
				try {
					if(f.getCanonicalFile().getName().equals(myFile.getFileName().toString())) {
						System.out.println(myFile.getFileName().toString() + " already exists in " + dirTest);
						fnf = false;
						break;
					}
				} catch (IOException e) {					
					e.printStackTrace();
				}				
			}//end for()				
			
		if( fnf != false )									
			System.out.println(myFile.getFileName().toString() + " DOES NOT exist in " + dirTest + "...");			
		
	}//searchForFile()
	
	private static void buildFileList() {
		int count = 0;
		
		File dirTest = new File(targetDirectory);
		
		for(File file : dirTest.listFiles()) {
			fileList.add(file);
			count++;
		}
		
		numFilesinDirectory = count;
		
	}//end buildFileList()
	
	//This (debug) method is printing the fileList Collection directly to the console
	//TODO: The method keeps double printing the file name to the console; further debug needed. Will not have time to fix before submission.
	private static void printDirectoryList() {
		File dirTest = new File(targetDirectory);
		
		Iterator<File> retrieve = fileList.iterator();
		
		while(retrieve.hasNext()) {
			for(File f : dirTest.listFiles()) {				
				try {
					System.out.println(f.getCanonicalFile().getName());					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//end catch
			}//end for loop
		}//end while		
		
		System.out.println("There are (numFilesinDirectory = ) " + numFilesinDirectory + " files in " + Paths.get(targetDirectory));
		
	}//end printDirectoryList

}//end class