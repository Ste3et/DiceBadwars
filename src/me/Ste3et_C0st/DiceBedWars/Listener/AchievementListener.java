package me.Ste3et_C0st.DiceBedWars.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AchievementListener{
	public static List<String> Achievents = new ArrayList<String>();
	
	public static HashMap<Player, Integer> EnderPearl = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Death = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Bed = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Blocks = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Steak = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> Kill = new HashMap<Player, Integer>();
	
	public static void loadAchievements(){
		Achievents.add("Champ#Eine Runde Gewinnen");//0
		Achievents.add("Loser#Eine Runde Verlieren");//1
		Achievents.add("First_Blood#Erster Kill");//2
		Achievents.add("Badass#Baue alle Betten der Gegner ab#Mind 4er Map");//3
		Achievents.add("Teamler#Kaufe eine Rüstung für dein Team");//4
		Achievents.add("Stürmer#Ein bett abbauen");//5
		Achievents.add("Überlebens Künstler#Kille 15 Mal");//6
		Achievents.add("Ender_King#Werfe 5 ender Perlen");//7
		Achievents.add("Bauarbeiter#Baue 500 Blöcke");//8
		Achievents.add("Staff_Killer#Töte ein Server-Team Mitglied");//9 
		Achievents.add("Verfressen#Esse 20 Steaks"); //10
		Achievents.add("@Facepalm#Baue dein Eigenes Bett ab"); //11
	}
	
	public static void setAchivements(Player p){
		EnderPearl.put(p, 0);
		Death.put(p, 0);
		Bed.put(p, 0);
		Blocks.put(p, 0);
		Steak.put(p, 0);
		Kill.put(p, 0);
	}
	
	public static boolean checkAchivement(Player p, int i){
		if(Achievents.get(i) != null){
			String s = getHeader(i);
			if(Main.permission.has(p, "Bedwars." + s)){
				 if(Main.permission.playerInGroup(p, "Admin") || Main.permission.playerInGroup(p, "Owner") || Main.permission.playerInGroup(p, "Supporter")){
					 if(Main.permission.has(p, "-Bedwars." + s)){
						 return true;
					 }else{
						 return false;
					 }
				 }else{
					 return true;
				 }
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public static void giveAchivement(Player p, int i){
		if(!checkAchivement(p, i)){
			if(Main.permission.playerInGroup(p, "Admin") || Main.permission.playerInGroup(p, "Owner") || Main.permission.playerInGroup(p, "Supporter")){
				Main.permission.playerAdd(p, "-Bedwars." + getHeader(i));
			}else{
				Main.permission.playerAdd(p, "Bedwars." + getHeader(i));
			}
			
			Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
			Messages.sendMessage(p, "§3Achivement Freigeschalten: §2" + getHeader(i) , false);
			Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
		}
	}
	
	public static String getHeader(int i){
		if(Achievents.get(i) != null){
			String s = Achievents.get(i);
			String[] a = s.split("#");
			String o = a[0].replace("_", "");
			return o;
		}else{
			return "§cFehler #0145";
		}
	}
}