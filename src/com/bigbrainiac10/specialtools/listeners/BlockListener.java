package com.bigbrainiac10.specialtools.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bigbrainiac10.specialtools.Utility;

import vg.civcraft.mc.citadel.Citadel;
import vg.civcraft.mc.citadel.ReinforcementManager;
import vg.civcraft.mc.citadel.reinforcement.Reinforcement;

public class BlockListener implements Listener {
	
	private ReinforcementManager rm = Citadel.getReinforcementManager();
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onHoe(PlayerInteractEvent event){
		Block clickedBlock = event.getClickedBlock();
		Player player = event.getPlayer();
		ItemStack handItem = player.getItemInHand();
		
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		
		if(!clickedBlock.getType().isSolid())
			return;
		
		Material clickedBlockMaterial = clickedBlock.getType();
		
		if(!(clickedBlockMaterial.equals(Material.DIRT) || clickedBlockMaterial.equals(Material.GRASS) || clickedBlockMaterial.equals(Material.MYCEL) || clickedBlockMaterial.equals(Material.SOIL)))
			return;
		
		if(player.getItemInHand() == null)
			return;
		
		if(!Utility.toolCheck(handItem, "hoe"))
			return;
		
		if(event.getBlockFace().equals(BlockFace.UP)){
			if(player.isSneaking()){
				if(rm.isReinforced(clickedBlock))
					return;
				
				clickedBlock.setType(Material.SOIL);
			}else{
				List<Block> blocksAOE = Utility.getAOE(clickedBlock.getLocation(), 1, true);
				
				for(Block block : blocksAOE){
					
					Material blockMat = block.getType();
					
					if(rm.isReinforced(block))
						continue;
					
					if(!(blockMat.equals(Material.DIRT) || blockMat.equals(Material.GRASS) || blockMat.equals(Material.MYCEL)))
						continue;
					
					if(block.equals(Material.AIR))
						continue;
					
					Block blockRel = block.getRelative(0, 1, 0);
					
					if(!(blockRel.getType().equals(Material.AIR)))
						continue;
					
					block.setType(Material.SOIL);
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled=false)
	public void onPickaxeInteract(PlayerInteractEvent event){
		Block clickedBlock = event.getClickedBlock();
		Player player = event.getPlayer();
		ItemStack handItem = player.getItemInHand();
		
		if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;

		Material clickedBlockMaterial = clickedBlock.getType();
		
		if(!(clickedBlockMaterial.equals(Material.DIRT) || clickedBlockMaterial.equals(Material.SAND) || clickedBlockMaterial.equals(Material.GRAVEL) || clickedBlockMaterial.equals(Material.GRASS) || clickedBlockMaterial.equals(Material.MYCEL)))
			return;
		
		if(handItem == null)
			return;
		
		if(!Utility.toolCheck(handItem, "drill"))
			return;
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10, 10, false, false));
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled=false)
	public void onPickaxeBreak(BlockBreakEvent event){
		Block brokenBlock = event.getBlock();
		Player player = event.getPlayer();
		ItemStack handItem = player.getItemInHand();
		
		if(!brokenBlock.getType().isSolid())
			return;
		
		if(player.getItemInHand() == null)
			return;
		
		if(!Utility.toolCheck(handItem, "drill"))
			return;
		
		BlockFace lookingFace = Utility.lookingDirectionVertical(player.getEyeLocation().getPitch());
		
		if(lookingFace == BlockFace.UP){
			
			if(player.isSneaking()){
				BlockBreakEvent newEvent = new BlockBreakEvent(brokenBlock, player);
				
				if(!newEvent.isCancelled()){
					brokenBlock.breakNaturally(handItem);
				}
			}else{
			
				List<Block> blocksAOE = Utility.getAOE(brokenBlock.getLocation(), 1, true);
				
				for(Block block : blocksAOE){
					
					
					BlockBreakEvent newEvent = new BlockBreakEvent(block, player);
					
					if(!newEvent.isCancelled()){
						
						if(rm.isReinforced(block)){
							Reinforcement rt = rm.getReinforcement(block);
							rt.setDurability(rt.getDurability()-1);
							
							if(rt.getDurability() <= 0)
								block.breakNaturally(handItem);
						}else{
							block.breakNaturally(handItem);
						}
					}
				}
			}
		}
		
	}

}
