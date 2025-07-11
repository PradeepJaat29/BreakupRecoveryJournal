package utils;

import java.io.*;
import java.util.*;

public class SupportLoader {
	
	//Base path to all asset files
	private static final String ASSET_PATH = "src/assets/";
	
	
	private static String getRandomLine(String filepath)
	{
		
		List<String> lines = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader (new FileReader(filepath)))
		{
			
			String line;
			while ((line = reader.readLine()) != null)
			{
				
				lines.add(line.trim());
			}
		}
		
		catch (IOException e)
		{
			
			return "⚠️ Error loading from " + filepath;
		}
		
		if(lines.isEmpty())
			return "⚠️ No suggestions available.";
		return lines.get(new Random().nextInt(lines.size()));
	}
	
	public static void showSupport(Scanner sc)
	{
		
		System.out.print("\nWould you like support (quote, tip, music)? (yes/no): ");
		String input = sc.nextLine().trim().toLowerCase();
		
		if(!input.equals("yes") && !input.equals("y"))
		{
			
			System.out.println("🫂 Got it. No support shown today.");
			return;
		}
		
		System.out.println("\n🧠 Quote of the Day: ");
		System.out.println("👉 " + getRandomLine(ASSET_PATH + "quotes.txt"));
		
		System.out.println("\n💊 Healing Suggestion: ");
		System.out.println("👉 "+ getRandomLine(ASSET_PATH + "suggestions.txt"));
		
		System.out.println("\n🎶 How are you feeling today?");
		System.out.println("1. 🥲 Sad");
		System.out.println("2. 😠 Angry");
		System.out.println("3. 😌 Calm");
		System.out.println("4. 💪 Motivated");
		System.out.println("5. ❤️ Nostalgic");
		System.out.println("Select your mood (1-5): ");
		
		int mood = 0;
		try
		{
			
			mood = Integer.parseInt(sc.nextLine().trim());
		}
		
		catch(Exception e)
		{
			
			System.out.println("⚠️ Invalid input. Defaulting to calm.");
			mood = 3;
		}
		
		String moodFile = switch (mood)
				{
		
		case 1 -> ASSET_PATH + "music_sad.txt";
		case 2 -> ASSET_PATH + "music_angry.txt";
		case 3 -> ASSET_PATH + "music_calm.txt";
		case 4 -> ASSET_PATH + "music_motivated.txt";
		case 5 -> ASSET_PATH + "music_nostalgic.txt";
		default -> ASSET_PATH + "music_calm.txt";
				};
				
				System.out.println("\n🎧 Mood-Based Music Suggestion:");
				System.out.println("🎵 " + getRandomLine(moodFile));
	}

}
