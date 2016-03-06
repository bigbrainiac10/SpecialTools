package com.bigbrainiac10.specialtools;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class STConfigManager {

	private static FileConfiguration config;
	
	public STConfigManager(FileConfiguration con){
		config = con;
	}
	
	
	public static boolean devEnabled(){
		return config.getBoolean("dev.enabled");
	}
	
	/*
	public static List<String> getSTPickaxeLore(){
		return config.getStringList("drill.lore");
	}
	*/
	
	public static List<String> getToolLore(String tool){
		return config.getStringList(tool+".lore");
	}
	
	/*
	public static String getSTPickaxeTitle(){
		return config.getString("drill.title");
	}
	*/
	
	public static String getToolTitle(String tool){
		return config.getString(tool+".title");
	}
}
