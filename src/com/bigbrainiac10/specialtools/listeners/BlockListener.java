package com.bigbrainiac10.specialtools.listeners;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbrainiac10.specialtools.STConfigManager;
import com.bigbrainiac10.specialtools.SpecialTools;
import com.bigbrainiac10.specialtools.Utility;

import vg.civcraft.mc.citadel.Citadel;
import vg.civcraft.mc.citadel.ReinforcementManager;
import vg.civcraft.mc.citadel.reinforcement.Reinforcement;

public class BlockListener implements Listener {
	
	private ReinforcementManager rm = Citadel.getReinforcementManager();
	
	/*
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerInteract(PlayerInteractEvent event){
		
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		
		Block clickedBlock = event.getClickedBlock();
		
		SpecialTools.Log(event.getBlockFace().toString());
		
		if(event.getBlockFace() == BlockFace.UP){
			
			if(event.getPlayer().isSneaking()){
				
				clickedBlock.breakNaturally();
				
			}else{
			
				List<Block> blocksAOE = Utility.getAOE(clickedBlock.getLocation(), 1);
				
				for(int i=0; i<blocksAOE.size(); i++){
					blocksAOE.get(i).breakNaturally();
					//blocksAOE.get(i).setType(STConfigManager.getReplaceMaterial());
				}
			}
		}
		
	}
	*/
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled=false)
	public void onBlockBreak(BlockBreakEvent event){
		Block brokenBlock = event.getBlock();
		Player player = event.getPlayer();
		ItemStack handItem = player.getItemInHand();
		
		if(!brokenBlock.getType().isSolid())
			return;
		
		if(player.getItemInHand() == null)
			return;
		
		BlockFace lookingFace = Utility.lookingDirectionVertical(player.getEyeLocation().getPitch());
		
		ItemMeta handItemMeta = handItem.getItemMeta();
		
		if(handItemMeta == null)
			return;
		
		if(!handItemMeta.hasLore())
			return;
		
		//SpecialTools.Log("Tool title: " + Utility.colorToSafe(handItemMeta.getLore().get(0)));
		
		if(!(Utility.colorToSafe(handItemMeta.getLore().get(0)).equalsIgnoreCase(STConfigManager.getSTPickaxeLore().get(0))))
			return;
		
		if(lookingFace == BlockFace.UP){
			
			if(player.isSneaking()){
				//brokenBlock.breakNaturally(handItem);
				BlockBreakEvent newEvent = new BlockBreakEvent(brokenBlock, player);
				//SpecialTools.instance.getServer().getPluginManager().callEvent(newEvent);
				if(!newEvent.isCancelled()){
					brokenBlock.breakNaturally(handItem);
				}
					
				//SpecialTools.instance.getServer().getPluginManager().callEvent(newEvent);
			}else{
			
				List<Block> blocksAOE = Utility.getDrillAOE(brokenBlock.getLocation(), 1);
				
				for(Block block : blocksAOE){
					//block.breakNaturally(handItem);
					
					
					BlockBreakEvent newEvent = new BlockBreakEvent(block, player);
					//SpecialTools.instance.getServer().getPluginManager().callEvent(newEvent);
					if(!newEvent.isCancelled()){
						
						if(rm.isReinforced(block)){
							Reinforcement rt = rm.getReinforcement(block);
							rt.setDurability(rt.getDurability()-1);
						}else{
							block.breakNaturally(handItem);
						}
					}
					SpecialTools.Log("X: " + newEvent.getBlock().getX() + " | Y: " + newEvent.getBlock().getY() + " | Z: " +newEvent.getBlock().getZ());
						
					//SpecialTools.instance.getServer().getPluginManager().callEvent(newEvent);
				}
			}
		}
		
	}

}
