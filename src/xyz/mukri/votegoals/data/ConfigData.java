package xyz.mukri.votegoals.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import xyz.mukri.votegoals.VoteGoals;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 9:48:27 AM 
 * 
 * total 		= Vote goals total
 * goals 		= How much goal will the VoteGoal have
 * commands 	= Command lists if the goals was meet
 * message 		= Message of how many vote left
 * 
 */

public class ConfigData {
	
	File file;
	FileConfiguration config;
	
	public ConfigData() {
		file = new File(VoteGoals.getInstance().getDataFolder(), "settings.yml");
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public boolean isExists() {
		return file.exists();
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createConfig() {
		try {
			
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			
			file.createNewFile();
			
			//total
			config.set("total", 0);
			
			//goals
			config.set("goals", 100);
			
			//commands
			List<String> commands = new ArrayList<>();
			commands.add("broadcast We reached our Vote Goals! Everyone gets $100");
			commands.add("eco give * 100");
			config.set("commands", commands);
			
			//message
			List<String> message = new ArrayList<>();
			message.add("----------------------------->");
			message.add("&c&lVote Goals: &b&l%votetotal%/100");
			message.add("----------------------------->");
			config.set("message", message);
			
			save();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getVoteTotal() {
		return config.getInt("total");
	}
	
	public int getVoteGoal() {
		return config.getInt("goals");
	}

}
