package me.Ste3et_C0st.language;

import me.Ste3et_C0st.DiceBedWars.Main;

import org.bukkit.entity.Player;


public class Messages{
	
	public static void sendMessage(Player p, String msg, Boolean bool){
		if(bool == true){
			p.sendMessage(Main.head + msg);
		}else{
			p.sendMessage(msg);
		}
	}
}