/* **********************************************
 * End-of-Phase 1 Project: Virtual Key Repository
 * Student: Nia Kelley
 * Date: 26 June 2022
 * ********************************************* */
package com.simplilearn.project.virtualkey;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VirtualKey {
	
	//class instance variables
	/* Since I'm not creating a VirtualKey object (i.e.  no constructor to assign the default values to)
	 * in my main program, I'm declaring and initializing them in one place.	
	 */	
	private static String targetDirectory;	
	private static String targetFileName;
	private static String appState;
	private static Scanner sc;	
	private static List<File> list;
	/*
	private static String targetDirectory = null;	
	private static String targetFileName = null;
	private static String appState = "c";
	private static Scanner sc;	
	private static List<File> list = Collections.emptyList();
	*/
		
	//static initialization block
	static {
		targetDirectory = null;
		targetFileName = null;
		appState = "c";
		//sc = new Scanner(System.in);
		list = Collections.emptyList();				
	}	

	//setters
	private static void setTargetDirectory(Scanner s) {
		System.out.println("Enter the target directory for LockedMe.com to use...");		
		targetDirectory = s.next();
	}
	
	private static void setTargetDirectory() {				
		targetDirectory = "C:\\test";
	}

	//main method	
	public static void main(String[] args) {	
	
		openScanner();
		welcomeScreen();
		try {
			Thread.sleep(2000); // set time delay for 2 seconds
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		mainMenu();		
		closeScanner();
        
	}//end main()
	
	/* ****** static methods *******/	
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
		boolean debug = true;		
		
		//Set the target directory
		if(debug)
			setTargetDirectory();
		else{			
			setTargetDirectory(sc);
		}
		
		System.out.println("You specified the following target directory: " + targetDirectory);
		//list =  Collections.emptyList();
		
		while(appState.equalsIgnoreCase("c"))		
		{
			//Display the Main Menu Options
			mainMenuChoice = mainMenuOptions();
			System.out.println("Selected main menu option: " + mainMenuChoice);			
				
			switch(mainMenuChoice) 
			{
				case 1:
					System.out.println("Retrieve files from " + targetDirectory);	
						retrieveFiles();
					/*
					try {
						retrieveFilesUsingNio();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					break;
				case 2:
					businessOptionsMenu();
					break;
				case 3:
					System.out.println("Closing application...");				
					System.exit(1);
			}//end switch
			
			System.out.println("Enter 'c' to continue, 'x' to quit: ");
			appState = sc.next();
			System.out.println("You entered " + appState);
				
		}//end while		
		
	}//end mainMenu()	
	
	private static int mainMenuOptions() {
		int r = 0;
			
		String[] options = {"1. Display the current file names in ASCENDENING order",
							"2. Open Business Level Operations Menu",
							"3. Close the application"
					};
		
		//Display Main Menu Options
		for(int i=0; i<options.length;i++) 
		{
			System.out.println(options[i]);
		}
		
			
		try {
			System.out.println("Choose your option...");
			r = sc.nextInt(); //return sc.nextInt();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//close catch block
		
		/* 
		 * I had to explicitly define an integer variable to return.
		 * Once I add the try/catch, I could no longer have the return statement inside the try block.
		 * I guess because I had to handle the exception, I had to have a explicit return outside of the try/catch 
		 * */
		return r; 		
	}//end mainMainOptions()
	
	private static void retrieveFilesUsingNio() throws IOException {
		
		Path dirPath = Paths.get(targetDirectory);
		
		if(Files.exists(dirPath) && Files.isDirectory(dirPath)) {
			System.out.println("Directory: " + dirPath.toAbsolutePath());
			System.out.println("Files: ");
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath);
			for(Path p:dirStream) {
				if(Files.isRegularFile(p))
					System.out.println(p.getFileName()); //already sorted in ascending order				
			}
		}				
	}//end retrieveFiles()
	
	private static void retrieveFiles() {
		/*
		 * Update: the File class has many limitations. It is considered best practice to use the java.nio.file package
		 * 
		 * source: https://www.tutorialspoint.com/how-to-get-list-of-all-files-folders-from-a-folder-in-java
		 * The class named File of the java.io package represents a file or directory (path names) in the system. 
		 * This class provides various methods to perform various operations on files/directories.
		 * To get the list of all the existing files in a directory this class provides the files class provides list() (returns names) 
		 * and ListFiles (returns File objects) with different variants.
		 * 
		 * The List() method
		 * This method returns a String array which contains the names of all the files and directories in the path represented 
		 * by the current (File) object. Using this method, you can just print the names of the files and directories.
		 * 
		 * The ListFiles() method
		 * This method returns an array holding the objects (abstract paths) of all the files (and directories) in the path represented 
		 * by the current (File) object.
		 * Since this method returns the objects of each file/directory in a folder. 
		 * Using it you can access the properties of the files/directories such as size, path etc.
		 */
		
		//Creating a File object for directory
		File dir = new File(targetDirectory);
		System.out.println("dir: " + dir); // + "Length: " + dir.listFiles());
		
		//enhanced for loop to process each element in the collection
		for(File file : dir.listFiles()) {
		    System.out.println(file.getName());
		    
		}
	}//end retrieveFilesOld
	
	private static void sortFilesAscending() {
		
	}//end sortFilesAscending()
	
	private static void fileSearch() {
		
	}//end fileSearch()
	
	private static void businessOptionsMenu() {
	
		boolean loop = true;
	
		while(loop) 
		{
			System.out.println("Business Level Options Menu");
			
			String[] options = {"1. Add new file",
								"2. Delete File",
								"3. Search for File",
								"4. Go back to main menu"
								};
			
			//Display the Business Level Options Menu to the Console
			for(int i=0; i<options.length;i++) 
			{
				System.out.println(options[i]);
			}
	
			//User will input their selection
			System.out.println("Choose your option...");
			int y;
			
			try {
					y = sc.nextInt();
			
					switch(y) 
					{
						case 1:							
							System.out.println("Specify the file name to ADD to " + targetDirectory+ ": ");
							targetFileName = sc.next();
							
							try{								
								createFileUsingNIO();
							}catch(IOException e) {
								e.printStackTrace();
							}
							break;
						case 2:							
							System.out.println("Specify the file name to DELETE from " + targetDirectory + ": ");
							targetFileName = sc.next();							
							deleteFile();
							break;
						case 3:							
							System.out.println("Specify the file name to SEARCH from " + targetDirectory + ": ");
							targetFileName = sc.next();
							System.out.println("Searching for file: " + targetFileName);
							searchForFile();
							break;
						case 4:
							loop = false;
							mainMenu();
							break;			
					}//end switch
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//close catch
		
		}//end while
	
	}//end businessOptionsMenu()
	
	private static void createFileUsingNIO() throws IOException{
				
		Path filePath = Paths.get(targetDirectory, targetFileName);
		System.out.println("filePath.toAbsolutePath().toString() = " + filePath.toAbsolutePath().toString());
		System.out.println("filePath.getFileName() = " + filePath.getFileName());
		System.out.println("filePath.getFileSystem() = " + filePath.getFileSystem());
		System.out.println("filePath.getName(0) = " + filePath.getName(0));
		
		if(Files.notExists(filePath)) {
			Files.createFile(filePath);
			System.out.println("File created successfully!"); //doesn't distinguish between NIA.html and nia.html are the same. okay per requirement
		} else {
			System.out.println("File already exists");
		}
		
		Path myFile = Paths.get(targetDirectory + "\\" + targetFileName);
		System.out.println("myFile.toAbsolutePath().toString() = " + myFile.toAbsolutePath().toString());
		System.out.println("myFile.getFileName() = " + myFile.getFileName());
		System.out.println("myFile.getFileSystem() = " + myFile.getFileSystem());
		System.out.println("myFile.getName(0) = " + myFile.getName(0));
		
	}//end createFileUsingNIO()
	
	//this method works, but the File class is not best practice
	private static void createFileUsingFileClass() throws IOException{
		
		File myFile = new File(targetDirectory+ "\\" + targetFileName);
		
		if(myFile.createNewFile())
			System.out.println("File created Successfully");
		else
			System.out.println("File already exists");
		
		//lets open the file and write the data in that file
		FileWriter writer= new FileWriter(myFile,true);//append true
		writer.write(" Hello everyone welcome to file Handling");
		System.out.println("Data written successfully");
		writer.close();
		
	}//end createFileUsingFileClass()	
	
	private static void deleteFile() {
		
		try {
			Path myFile = Paths.get(targetDirectory + "\\" + targetFileName);			
			boolean fileExists = Files.exists(myFile);
			
			if(fileExists) 
			{
				System.out.println("The file exists already..");
				System.out.println("Do you really want to delete - y or n?");
				String proceed = sc.next();
				
				if(proceed.equalsIgnoreCase("y")) 
				{
					Files.deleteIfExists(myFile);
					System.out.println("File deleted Successfully");
				}
				else {
					System.out.println("Not deleting the file per your request...");
				}
			}//if(exists)
			else {
				System.out.println("File does not exist");	
			}			
		}catch (NoSuchFileException e) {
			e.printStackTrace();
		}
		catch (DirectoryNotEmptyException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end deleteFile()
	
	private static void searchForFile() {
		Path myFile = Paths.get(targetDirectory + "\\" + targetFileName);
		boolean fileExists = Files.exists(myFile);
		
		if(fileExists) {
			System.out.println("The file exists...");
		}
		else {
			System.out.println("The file DOES NOT exist...");
			System.out.println("Do you really want to CREATE this file? - y or n?");
			String proceed = sc.next();
			
			if(proceed.equalsIgnoreCase("y")) 
			{
				try {
					createFileUsingNIO();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("File Created Successfully");
			}
			else {
				System.out.println("Not Creating the file per your request...");
			}
		}
	}//end searchForFile()

}//end class