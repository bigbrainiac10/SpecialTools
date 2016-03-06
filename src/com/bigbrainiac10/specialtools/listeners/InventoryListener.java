package com.bigbrainiac10.specialtools.listeners;

import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bigbrainiac10.specialtools.STConfigManager;
import com.bigbrainiac10.specialtools.Utility;

public class InventoryListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event){
		Inventory inv = event.getInventory();
		
		if(!(inv instanceof AnvilInventory))
			return;
		
		ItemStack item = event.getCurrentItem();
		
		if(item == null)
			return;
		
		ItemMeta meta = item.getItemMeta();
		
		if(meta == null)
			return;
		
		if(!meta.hasLore())
			return;
		
		if((Utility.colorToSafe(meta.getLore().get(0)).equalsIgnoreCase(STConfigManager.getToolLore("drill").get(0)))){
			event.setResult(Result.DENY);
			event.setCancelled(true);
		}
		
		if((Utility.colorToSafe(meta.getLore().get(0)).equalsIgnoreCase(STConfigManager.getToolLore("hoe").get(0)))){
			event.setResult(Result.DENY);
			event.setCancelled(true);
		}
	}
	
}
