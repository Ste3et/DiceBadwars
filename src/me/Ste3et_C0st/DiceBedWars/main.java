package me.Ste3et_C0st.DiceBedWars;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	public static String messageHead = "§7[§Bedwars§7] ";
	Plugin instance = null;
	
	public void onEnable(){
		instance = this;
	}
	
	public void onDisable(){
		instance = null;
	}
	
	public Plugin inst(){
		return this.instance;
	}
}
