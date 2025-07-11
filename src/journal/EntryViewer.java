package journal;

import java.io.*;
import java.util.*;

public class EntryViewer 
{
	
	public static void viewEntries(Scanner sc)
	{
		
		File folder = new File("entries");
		
		if(!folder.exists() || folder.listFiles() == null || folder.listFiles().length == 0)
		{
			
			System.out.println("📪 No journal entries found yet.");
		return;
		}
	
	File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
	if(files == null || files.length == 0)
	{
		
		System.out.println("📪 No text entries to display.");
		return;
	}
	
	// Show all available entries
	System.out.println("\n📚 Available Journall Entries: ");
	for(int i = 0;i < files.length; i++)
	{
		
		System.out.println((i + 1 ) + ". " + files[i].getName());
	}
	
	System.out.println("\nEnter the number of the entry you want to view: ");
	int choice;
	
	try
	{
		
		choice = Integer.parseInt(sc.nextLine());
		if(choice < 1 || choice > files.length) 
			throw new Exception();
	}
	
	catch(Exception e)
	{
		
		System.out.println("⚠️ Invalid input. Returning to menu");
		return;
	}
	
	// Show selected file
	File selectedFile =  files[choice - 1];
	System.out.println("\n📄 Showing Entry: " + selectedFile.getName());
	System.out.println("----------------------------------------------------");
	
	try(BufferedReader reader = new BufferedReader(new FileReader(selectedFile)))
	{
		
		String line;
		
		while ((line = reader.readLine()) != null)
		{
			
			System.out.println(line);
		}
	}
	catch(IOException e)
	{
		
		System.out.println("❌ Error reading entry: " + e.getMessage());
	}
	
	System.out.println("---------------------------------------------------------");
}
}
