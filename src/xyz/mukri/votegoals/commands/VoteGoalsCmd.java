package xyz.mukri.votegoals.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.mukri.votegoals.VoteGoals;
import xyz.mukri.votegoals.data.ConfigData;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 9:56:08 AM 
 */

public class VoteGoalsCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if(!(sender instanceof Player)) {
		
			if(cmd.getName().equalsIgnoreCase("votegoals")) {
				if(args.length < 1) {
					sender.sendMessage("Vote Goals Versions: " + VoteGoals.getInstance().versions);
					sender.sendMessage("Vote Goals by: Mukri");
				}
				
				else if(args[0].equalsIgnoreCase("add")) {
					VoteGoals.getInstance().config.getConfig().set("total", VoteGoals.getInstance().config.getVoteTotal() + 1);
					VoteGoals.getInstance().config.save();
					
					if(VoteGoals.getInstance().config.getVoteTotal() >= VoteGoals.getInstance().config.getVoteGoal()) {
						
						for(String commands : VoteGoals.getInstance().config.getConfig().getStringList("commands")) {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commands);
						}
						
						VoteGoals.getInstance().config.getConfig().set("total", 0);
						VoteGoals.getInstance().config.save();
						
					} else {
						int leftVote = VoteGoals.getInstance().config.getVoteGoal() - VoteGoals.getInstance().config.getVoteTotal();
						sender.sendMessage("[VoteGoalsM] Added 1 votes to the Vote Totals");
						sender.sendMessage("[VoteGoalsM] You need " + leftVote + " votes to go.");
					}
				}
			}
			return false;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("votegoals")) {
			if(args.length < 1) {
				for(String line : VoteGoals.getInstance().config.getConfig().getStringList("message")) {
					line = ChatColor.translateAlternateColorCodes('&', line);
					line = line.replace("%votetotal%", String.valueOf(VoteGoals.getInstance().config.getVoteTotal()));
					p.sendMessage(line);
				}
			} 
			
			else if(args[0].equalsIgnoreCase("reload")) {
				if(p.hasPermission("votegoalsm.reload")) {
					VoteGoals.getInstance().config = new ConfigData();
					p.sendMessage("¤7[VoteGoalsM] settings.yml reloaded!");
				} else {
					p.performCommand("votegoals");
				}
			}
			
			else {
				p.performCommand("votegoals");
			}
		}
		
		return false;
	}

}
