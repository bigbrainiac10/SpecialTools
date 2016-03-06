package com.bigbrainiac10.specialtools;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.bigbrainiac10.specialtools.commands.ToolCommand;
import com.bigbrainiac10.specialtools.listeners.BlockListener;
import com.bigbrainiac10.specialtools.listeners.InventoryListener;
import com.bigbrainiac10.specialtools.tools.DiamondDrill;
import com.bigbrainiac10.specialtools.tools.IndustrialHoe;


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
		//instance.getCommand("stpickaxe").setExecutor(new STPickaxe());
		instance.getCommand("stpickaxe").setExecutor(new ToolCommand("stpickaxe", new DiamondDrill()));
		instance.getCommand("sthoe").setExecutor(new ToolCommand("sthoe", new IndustrialHoe()));
	}
	
}
