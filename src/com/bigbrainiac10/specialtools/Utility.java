package com.bigbrainiac10.specialtools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utility {
	 
	public static List<Block> getAOE(Location center, int radius, boolean includeCentre) {
		List<Block> blocksAOE = new ArrayList<Block>();
		
	    for (int xMod = -radius; xMod <= radius; xMod++) {
	        for (int zMod = -radius; zMod <= radius; zMod++) {
	            Block theBlock = center.getBlock().getRelative(xMod, 0, zMod);
	            
	            if(theBlock.getType().equals(Material.BEDROCK))
	            	continue;
	            
	            if((!includeCentre) && (xMod == 0 && zMod == 0))
	            	continue;
	            
	            blocksAOE.add(theBlock);
	        }
	    }
	    
	    return blocksAOE;
	}
	
	
	public static BlockFace lookingDirectionVertical(float pitch){
		if(pitch > 45){
			return BlockFace.UP;
		}else{
			return BlockFace.DOWN;
		}
	}
	
	public static String safeToColor(String str){
		return str.replace('&', ChatColor.COLOR_CHAR);
	}
	
	public static String colorToSafe(String str){
		return str.replace(ChatColor.COLOR_CHAR, '&');
	}
	
	public static List<String> createLore(List<String> toolLoreRaw){
		List<String> toolLore = new ArrayList<String>();
		
		for(String line : toolLoreRaw){
			toolLore.add(Utility.safeToColor(line));
		}
		
		return toolLore;
	}
	
	public static boolean toolCheck(ItemStack item, String toolName){
		ItemMeta meta = item.getItemMeta();
		
		if(meta == null)
			return false;
		
		if(!meta.hasLore())
			return false;
		
		if(!(colorToSafe(meta.getLore().get(0)).equalsIgnoreCase(STConfigManager.getToolLore(toolName).get(0))))
			return false;
		
		return true;
	}
	
}
