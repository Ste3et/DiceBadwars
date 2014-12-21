package me.Ste3et_C0st.DiceBedWars.Manager;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils{
	public static String trimPlayerName(String s, int length){
		List<String> strings = new ArrayList<String>();
        int index = 0;
        
        while (index<s.length()) {
            strings.add(s.substring(index, Math.min(index+length, s.length())));
            index+=length;
        }
        return strings.get(0);
	}
	  public static List<Location> circle (Player player, Location loc, Integer r, Integer h, Boolean hollow, Boolean sphere, int plus_y) {
	      List<Location> circleblocks = new ArrayList<Location>();
	      int cx = loc.getBlockX();
	      int cy = loc.getBlockY();
	      int cz = loc.getBlockZ();
	      for (int x = cx - r; x <= cx +r; x++)
	          for (int z = cz - r; z <= cz +r; z++)
	              for (int y = (sphere ? cy - r : cy); y < (sphere ? cy + r : cy + h); y++) {
	                  double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
	                  if (dist < r*r && !(hollow && dist < (r-1)*(r-1))) {
	                      Location l = new Location(loc.getWorld(), x, y + plus_y, z);
	                      circleblocks.add(l);
	                      }
	                  }
	 
	      return circleblocks;
	  }
	public static String returnColorName(int i){
		if(i == 0){
			return "Grün";
		}else if(i == 1){
			return "Blau";
		}else if(i == 2){
			return "Rot";
		}else if(i == 3){
			return "Rosa";
		}else if(i == 4){
			return "Gelb";
		}else if(i == 5){
			return "Weiß";
		}else if(i == 6){
			return "Schwarz";
		}else if(i == 7){
			return "Blau";
		}else if(i == 8){
			return "Grün";
		}else if(i == 9){
			return "Türkis";
		}else if(i == 10){
			return "Rot";
		}else if(i == 11){
			return "Lila";
		}else if(i == 12){
			return "Orange";
		}else if(i == 13){
			return "Grau";
		}else if(i == 14){
			return "Grau";
		}else if(i == 15){
			return "Türkis";
		}else{
			return "Weiß";
		}
	}
	
	public static int returnColorName(String s){
		if(s.equalsIgnoreCase("Grün")){
			return 0;
		}
		
		if(s.equalsIgnoreCase("Blau")){
			return 1;
		}
		
		if(s.equalsIgnoreCase("Rot")){
			return 2;
		}
		
		if(s.equalsIgnoreCase("Rosa")){
			return 3;
		}
		
		if(s.equalsIgnoreCase("Gelb")){
			return 4;
		}
		
		if(s.equalsIgnoreCase("Weiß")){
			return 5;
		}
		
		if(s.equalsIgnoreCase("Schwarz")){
			return 6;
		}
		
		if(s.equalsIgnoreCase("Blau")){
			return 7;
		}
		
		if(s.equalsIgnoreCase("Grün")){
			return 8;
		}
		
		if(s.equalsIgnoreCase("Türkis")){
			return 9;
		}

		if(s.equalsIgnoreCase("Rot")){
			return 10;
		}
		
		if(s.equalsIgnoreCase("Lila")){
			return 11;
		}
		
		if(s.equalsIgnoreCase("Orange")){
			return 12;
		}
		
		if(s.equalsIgnoreCase("Grau")){
			return 13;
		}
		
		if(s.equalsIgnoreCase("Grau")){
			return 14;
		}
		
		if(s.equalsIgnoreCase("Türkis")){
			return 15;
		}
		
		return 0;
	}
	
	public static String returnColor(int i){
		if(i == 0){
			return "&a";
		}else if(i == 1){
			return "&b";
		}else if(i == 2){
			return "&c";
		}else if(i == 3){
			return "&d";
		}else if(i == 4){
			return "&e";
		}else if(i == 5){
			return "&f";
		}else if(i == 6){
			return "&0";
		}else if(i == 7){
			return "&1";
		}else if(i == 8){
			return "&2";
		}else if(i == 9){
			return "&3";
		}else if(i == 10){
			return "&4";
		}else if(i == 11){
			return "&5";
		}else if(i == 12){
			return "&6";
		}else if(i == 13){
			return "&7";
		}else if(i == 14){
			return "&8";
		}else if(i == 15){
			return "&9";
		}else{
			return "&f";
		}
	}
	
	public static short returnDurability(String s){
		if(s.equalsIgnoreCase("&a")){
			return 5;
		}else if(s.equalsIgnoreCase("&b")){
			return 3;
		}else if(s.equalsIgnoreCase("&c")){
			return 14;
		}else if(s.equalsIgnoreCase("&d")){
			return 6;
		}else if(s.equalsIgnoreCase("&e")){
			return 4;
		}else if(s.equalsIgnoreCase("&f")){
			return 0;
		}else if(s.equalsIgnoreCase("&0")){
			return 15;
		}else if(s.equalsIgnoreCase("&1")){
			return 11;
		}else if(s.equalsIgnoreCase("&2")){
			return 13;
		}else if(s.equalsIgnoreCase("&3")){
			return 9;
		}else if(s.equalsIgnoreCase("&4")){
			return 14;
		}else if(s.equalsIgnoreCase("&5")){
			return 10;
		}else if(s.equalsIgnoreCase("&6")){
			return 1;
		}else if(s.equalsIgnoreCase("&7")){
			return 8;
		}else if(s.equalsIgnoreCase("&8")){
			return 7;
		}else if(s.equalsIgnoreCase("&9")){
			return 9;
		}else{
			return 0;
		}
	}


	public static int rnd(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}
	
	  public static boolean isInt(String string) {
		  	try {
		  	Integer.parseInt(string);
		  	} catch (NumberFormatException nFE) {
		  	return false;
		  	}
		  	return true;
		  	}
		  
	 public static boolean isDouble(String string) {
			  	try {
			  	Double.parseDouble(string);
			  	} catch (NumberFormatException nFE) {
			  	return false;
			  	}
			  	return true;
			  	}

	public static void sendRound(String string, boolean b, Arena a) {
		if(a.getPlayers() == null){
			return;
		}
		
		if(a.getPlayers().isEmpty()){
			return;
		}
		
		if(a.getPlayers().size() <= 0){
			return;
		}
		
		String s = "";
		
		if(b){
			s = Main.head;
		}
		
		for(Player pl : a.getPlayers()){
				pl.sendMessage(s + string);
		}

	}
	
	public static void sendRound2(String string, boolean b, Arena a) {
		if(a.getPlayers() == null){
			return;
		}
		
		if(a.getPlayers().isEmpty()){
			return;
		}
		
		if(a.getPlayers().size() <= 0){
			return;
		}
		
		String s = "";
		
		if(b){
			s = Main.head;
		}
		
		for(Player pl : a.getLobbyPlayer()){
				pl.sendMessage(s + string);
		}
	}
	
	
	public static void sendRound3(String string, boolean b, Arena a, Player p) {
		if(a.isStartet() && !a.isLobby()){
			sendRound2(string, b, a);
		}
		
		if(a.getLobbyPlayer() == null){
			return;
		}
		
		if(a.getLobbyPlayer().isEmpty()){
			return;
		}
		
		if(a.getLobbyPlayer().size() <= 0){
			return;
		}
		
		String s = "";
		
		if(b){
			s = Main.head;
		}
		
		for(Player pl : a.getLobbyPlayer()){
			if(pl != p){
				pl.sendMessage(s + string);
			}
		}
	}
	
	
}