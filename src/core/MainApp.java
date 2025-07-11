package core;

import java.util.Scanner;

import auth.PasswordManager;
import journal.EntryViewer;
import journal.EntryWriter;
import utils.SupportLoader;

public class MainApp 
{
	
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("💔 Welcome to the Breakup Recovery Journal 💔");
		
		boolean verified = PasswordManager.verifyPassword(sc);
		
		if(!verified)
		{
			
			System.out.println("❌ Too many failed attempts. Exiting...");
			sc.close();
			return;
		}
		
		while (true)
		{
			System.out.println("\n📋 Main Menu: ");
			System.out.println("1. Write a new journay entry 📝");
			System.out.println("2. View past entries 📖");
			System.out.println("3. Get emotional support 💡");
			System.out.println("4. Exit 🚪");
			
			System.out.println("Enter your choice (1-4): ");
			String choice = sc.nextLine().trim();
			
			switch (choice)
			{
			case "1" -> EntryWriter.writeEntry(sc);
			case "2" -> EntryViewer.viewEntries(sc);
			case "3" -> SupportLoader.showSupport(sc);
			case "4" ->
			{
				
				System.out.println("👋 Goodbye. Stay strong. Keep healing.");
				sc.close();
				return;
			}
			
			default -> System.out.println("⚠️ Invalid input. Please choose between 1-4.");
			}
		}
	}
}
