package auth;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PasswordManager 
{
	public static final String Password_File ="credentials.txt";
	
	public static boolean verifyPassword(Scanner sc)
	{
		
		File file = new File(Password_File);
		
		try
		{
			
			if(!file.exists()) 
			{
				
				//	First Time Setup
				
			System.out.print("🔐 Set your new journal password: ");
			String newPassword = sc.nextLine();
			String hashedPassword = hashPassword(newPassword);
			
			try(FileWriter writer = new FileWriter(file))
			{
				
				writer.write(hashedPassword);
			}
			
			System.out.println("✅ Password set successfully.\n");
			return true;
			}
			
			else
			{
				
				//	File exists then check password
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String savedHashedPassword = reader.readLine();
				reader.close();
				
				int attempts = 0;
				
				while (attempts < 3)
				{
					
					System.out.println("🔐 Enter your journay password: ");
					String enterPassword = sc.nextLine();
					String hashed = hashPassword(enterPassword);
					
					if(hashed.equals(savedHashedPassword))
					{
						
						System.out.println("✅ Access Granted.\n");
						return true;
					}
					
					else
					{
						System.out.println("❌ Incorrect password. Try again.");
						attempts++;
					}
				}
			}
		}
		
		catch(IOException e)
		{
			
			System.out.println("❌ Error accessing password file.");
			e.printStackTrace();
		}
		
		return false;   // 	ACCESS DENIED AFTER 3 ATTEMPTS
	}
	
	// Convert password to hash
	
	private static String hashPassword(String password)
	{
		
		try
		{
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			StringBuilder sb =new StringBuilder();
			
			for(byte b : hash)
			{
				
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();
		}
		
		catch(NoSuchAlgorithmException e)
		{
			
			throw new RuntimeException("SHA-256 not available", e);
		}
	}

}
