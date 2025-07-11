package utils;

import java.io.*;
import java.util.*;

public class SupportLoader {
	
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
		System.out.println("👉 " + getRandomLine("assets/quotes.txt"));
		
		System.out.println("\n💊 Healing Suggestion: ");
		System.out.println("👉 "+ getRandomLine("assets/suggestions.txt"));
		
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
		
		case 1 -> "assets/music_sad.txt";
		case 2 -> "assets/music_angry.txt";
		case 3 -> "assets/music_calm.txt";
		case 4 -> "assets/music_motivated.txt";
		case 5 -> "assets/music_nostalgic.txt";
		default -> "assets/music_calm.txt";
				};
				
				System.out.println("\n🎧 Mood-Based Muisc Suggestion:");
				System.out.println("🎵 " + getRandomLine(moodFile));
	}

}
