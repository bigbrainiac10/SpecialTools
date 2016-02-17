package com.bigbrainiac10.specialtools.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbrainiac10.specialtools.STConfigManager;
import com.bigbrainiac10.specialtools.Utility;

public class STPickaxe implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			return false;
		
		Player player = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("stpickaxe")){
			player.getInventory().addItem(makePickaxe());
			return true;
		}
		
		return false; 
	}
	
	private ItemStack makePickaxe(){
		ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta pickaxeMeta = pickaxe.getItemMeta();
		
		//pickaxeMeta.setDisplayName(ChatColor.AQUA + "Diamond Drill");
		pickaxeMeta.setDisplayName(Utility.safeToColor(STConfigManager.getSTPickaxeTitle()));
		
		/*
		List<String> pickaxeLore = Arrays.asList(
			ChatColor.BOLD + "" + ChatColor.WHITE +"Dig down to dig a large area"
		);
		*/
		
		List<String> pickaxeLoreRaw = STConfigManager.getSTPickaxeLore();
		
		List<String> pickaxeLore = new ArrayList<String>();
		
		for(String line : pickaxeLoreRaw){
			pickaxeLore.add(Utility.safeToColor(line));
		}
		
		pickaxeMeta.setLore(pickaxeLore);
		pickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		pickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 8, true);
		
		pickaxe.setItemMeta(pickaxeMeta);
		
		return pickaxe;
	}
	
}
