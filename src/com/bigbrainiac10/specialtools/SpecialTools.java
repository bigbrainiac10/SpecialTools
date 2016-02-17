package com.bigbrainiac10.specialtools;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.bigbrainiac10.specialtools.commands.STPickaxe;
import com.bigbrainiac10.specialtools.listeners.BlockListener;
import com.bigbrainiac10.specialtools.listeners.InventoryListener;


public class SpecialTools extends JavaPlugin {

	private static Logger logger;
	public static SpecialTools instance;
	
	public void onEnable(){
		instance = this;
		logger = this.getLogger();
		
		saveDefaultConfig();
		reloadConfig();
		new STConfigManager(getConfig());
		
		registerListeners();
		registerCommands();
	}
	
	public void onDisable(){
	}
	
	public static void Log(String message){
		logger.log(Level.INFO, message);
	}
	
	private static void registerListeners(){
		instance.getServer().getPluginManager().registerEvents(new BlockListener(), instance);
		instance.getServer().getPluginManager().registerEvents(new InventoryListener(), instance);
	}
	
	private static void registerCommands(){
		if(STConfigManager.getSTPickaxeCommandEnabled()){
			instance.getCommand("stpickaxe").setExecutor(new STPickaxe());
		}
	}
	
}
