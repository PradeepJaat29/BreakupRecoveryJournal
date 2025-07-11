package journal;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.*;


public class EntryWriter 
{
	
	public static void writeEntry(Scanner sc)
	{
		
		System.out.println("\n📝 Write your journal below. Press Enter when you're done: ");
		String entryText = sc.nextLine().trim();
		
		if(entryText.isEmpty())
		{
			
			System.out.println("⚠️ Empty entry skipped.");
			return;
		}
		
		try
		{
			
			// Create entry folder if it doesn't exist.
			File folder = new File("entries");
			if(!folder.exists())
			{
				
				folder.mkdir();
			}
			
			// File name with timestamp to avoid conflicts
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			String fileName = "entries/entry_"+ timestamp + ".txt";
			FileWriter writer = new FileWriter(fileName);
			
			// Write entry with header timestamp
			writer.write("📅 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy - hh : mm a")) + "\n");
			writer.write("----------------------------------------------------\n");
			writer.write(entryText + "\n");
			
			writer.close();
			System.out.println("✅ Entry saved successfully to " + fileName);
		}
		
		catch(IOException e)
		{
			
			
			System.out.println("❌ Error whiile saving: " + e.getMessage());
		}
	}
}
