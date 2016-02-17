package com.bigbrainiac10.specialtools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Utility {

	//Location loc = player.getLocation().clone().add(0.0, -1.0, 0.0);
	 
	public static List<Block> getDrillAOE(Location center, int radius) {
		List<Block> blocksAOE = new ArrayList<Block>();
		
	    for (int xMod = -radius; xMod <= radius; xMod++) {
	        for (int zMod = -radius; zMod <= radius; zMod++) {
	            Block theBlock = center.getBlock().getRelative(xMod, 0, zMod);
	            
	            if(theBlock.getType().equals(Material.BEDROCK))
	            	continue;
	            
	            /*
	            if(theBlock.getLocation() == center)
	            	continue;
	            */
	            
	            if(xMod == 0 && zMod == 0)
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
	
	/*
	public static List<Block> getDrillAOE(Location center, int radius, ItemStack item) {
		List<Block> blocksAOE = new ArrayList<Block>();
		
	    for (int xMod = -radius; xMod <= radius; xMod++) {
	        for (int zMod = -radius; zMod <= radius; zMod++) {
	            Block theBlock = center.getBlock().getRelative(xMod, 0, zMod);
	            
	            if(theBlock.getType().equals(Material.BEDROCK))
	            	continue;
	            
	            if((!item.getType().equals(Material.DIAMOND_PICKAXE)) && (theBlock.getType().equals(Material.OBSIDIAN)))
	            	continue;
	            
	            blocksAOE.add(theBlock);
	        }
	    }
	    
	    return blocksAOE;
	}
	*/
	
}
