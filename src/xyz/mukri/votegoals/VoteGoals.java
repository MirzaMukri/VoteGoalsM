package xyz.mukri.votegoals;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.mukri.votegoals.commands.VoteGoalsCmd;
import xyz.mukri.votegoals.data.ConfigData;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 12:22:27 PM 
 */

public class VoteGoals extends JavaPlugin {
	
	public static VoteGoals instance;
	public ConfigData config;
	
	public String versions = "V.0.1";
	
	public void onEnable() {
		instance = this;
		config = new ConfigData();
		
		if(!config.isExists()) {
			config.createConfig();
		}
		
		listen();
		commands();
	}
	
	public void onDisable() {
		
	}
	
	public void listen() {
		
	}
	
	public void commands() {
		getCommand("votegoals").setExecutor(new VoteGoalsCmd());
	}

	public static VoteGoals getInstance() {
		return instance;
	}
}
