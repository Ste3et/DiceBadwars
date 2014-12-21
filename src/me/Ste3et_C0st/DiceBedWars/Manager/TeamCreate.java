package me.Ste3et_C0st.DiceBedWars.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class TeamCreate {
	

	public static HashMap<Player, String> player = new HashMap<Player, String>();
	public static HashMap<Player, List<Team>> teams = new HashMap<Player, List<Team>>();
	
	@SuppressWarnings("deprecation")
	public static void enterTeam(Player p, String a){
			if(player.get(p) == null){
				p.updateInventory();
				player.put(p, a);
				p.getInventory().setItem(1, Editor.is(Material.BED,Main.head + "Team Editor", null, 0, 1));
				p.updateInventory();
				
				List<Team> te = new ArrayList<Team>();
				for(int i = 0; i <= Editor.getSize(Editor.getEditorName(p)); i++){
					Team t = new Team("Team " + i, "&f", null, null, null);
					te.add(t);
				}
				
				teams.put(p, te);
			}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static void exitTeam(Player p, String a){
		if(ArenaManager.getManager().ArenaExist(a)){
			if(player.get(p) != null){
				teams.remove(p);
				player.remove(p);
				p.updateInventory();
				p.getInventory().setItem(2, null);
				p.updateInventory();
			}
		}
	}
	
	public static void setSpawn(Location l, Player p, int i){
		if(getTeam(i, p) != null){
			Team t = getTeam(i, p);
			t.setSpawn(l);
		}
	}
	
	public static void setName(String name, Player p, int i){
		if(getTeam(i, p) != null){
			Team t = getTeam(i, p);
			t.setName(name);
		}
	}
	
	public static void setColor(String color, Player p, int i){
		if(getTeam(i, p) != null){
			Team t = getTeam(i, p);
			t.setColor(color);
		}
	}
	
	public static Block getBed(int i, Player p){
		if(teams.get(p) == null){
			return null;
		}
		
		if(teams.get(p).get(i) == null){
		  return null;
		}
		
		if(teams.get(p).get(i).getBed() == null){
			return null;
		}
		
		
		return teams.get(p).get(i).getBed();
	}
	
	public static BlockFace getFace(int i, Player p){
		if(teams.get(p).get(i) == null){
		  return null;
		}
		
		if(teams.get(p).get(i).getFace() == null){
			return null;
		}
		
		return teams.get(p).get(i).getFace();
	}
	
	public static void addBed(Block b,int i, Player p){
		if(teams.get(p) != null){
			Team t = teams.get(p).get(i);
			t.addBed(b);
			t.addFace(Editor.getRotation(p, b));
		}else{
			Messages.sendMessage(p, "Es ist ein fehler aufgtreten", true);
		}
	}



	public static Team getTeam(int i, Player p) {
		if(teams.get(p) == null){
			return null;
		}
		
		List<Team> list = teams.get(p);
		
		if(i >= list.size() || list.get(i) == null){
			return null;
		}
		
		return teams.get(p).get(i);
	}



	public static void remove(int i, Player p) {
		if(teams.get(p) == null){
			return;
		}
		
		List<Team> list = teams.get(p);
		
		if(i >= list.size() || list.get(i) == null){
			return;
		}
		
		list.remove(i);
		teams.put(p, list);
		
	}
	
}
