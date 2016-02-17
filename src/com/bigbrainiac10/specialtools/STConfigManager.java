package com.bigbrainiac10.specialtools;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class STConfigManager {

	private static FileConfiguration config;
	
	public STConfigManager(FileConfiguration con){
		config = con;
	}
	
	
	public static boolean getSTPickaxeCommandEnabled(){
		return config.getBoolean("drill.debug_command_enabled");
	}
	
	public static List<String> getSTPickaxeLore(){
		return config.getStringList("drill.lore");
	}
	
	public static String getSTPickaxeTitle(){
		return config.getString("drill.title");
	}
}
