package me.sqltest;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	
	public void onEnable()
	{
		
		try 
		{
			getConnection();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws Exception
	{
		
		try
		{
			Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/minecraft", "root", "password");
			
			System.out.println("------------[SQL Database]------------");
			System.out.println("Status : Connected");
			System.out.println("-------------------------------------------");
			return con;
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	public static void  getInfo(Player player) throws Exception
	{
		
		Connection con = getConnection();
		
		PreparedStatement s = con.prepareStatement("CREATE TABLE playertest(id INTEGER PRIMARY KEY AUTOINCREMENT, steps INTEGER);");
		s.execute();
		PreparedStatement getInfo = con.prepareStatement("SELECT " + player.getName().toLowerCase() + " FROM playertest");
		getInfo.executeQuery();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		if(sender instanceof Player)
		{
			
			Player player = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("info"))
			
			
				try 
				{
					getInfo(player);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
			
		}
		
		return false;
		
	}
	
}