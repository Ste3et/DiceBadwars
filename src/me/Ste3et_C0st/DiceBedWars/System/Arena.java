package me.Ste3et_C0st.DiceBedWars.System;

import org.bukkit.Bukkit;
import org.bukkit.Location;
 
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena { 

    Integer id;
    Location Lobby;
    Location Exit;
    Location Spawn1;
    Location Spawn2;
    Location Spawn3;
    Location Spawn4;
    
    List<UUID> players = new ArrayList<UUID>();
    List<Team> teams = new ArrayList<Team>();
    List<Block> blocklist = new ArrayList<Block>();
    
    
    public Arena(Location lobby, Location exit, Location s1, Location s2 ,Location s3, Location s4, List<Block> bl, int id) {
        this.Lobby = lobby;
        this.Exit = exit;
        this.Spawn1 = s1;
        this.Spawn2 = s2;
        this.Spawn3 = s3;
        this.Spawn4 = s4;
        this.blocklist = bl;

        Team t_1 = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("blau");
        Team t_2 = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("rot");
        Team t_3 = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("gelb");
        Team t_4 = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("grün");
        
        t_1.setAllowFriendlyFire(false);
        t_2.setAllowFriendlyFire(false);
        t_3.setAllowFriendlyFire(false);
        t_4.setAllowFriendlyFire(false);
        
        this.teams.add(t_1);
        this.teams.add(t_2);
        this.teams.add(t_3);
        this.teams.add(t_4);
        
        this.id = id;
    }
 
    public int getId() {
        return this.id;
    }
    
    public boolean checkTeamIsIn(Player p){
    	for(Team t : this.teams){
    		if(t.hasPlayer(p)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public Integer returnTeam(Player p){
    	if(checkTeamIsIn(p)){
    		int i = 0;
    		for(Team t : this.teams){
    			if(t.hasPlayer(p)){
    				return i;
    			}else{
    				i++;
    			}
    		}
    	}else{
    		return null;
    	}
		return null;
    }
    
    public String returnName(int i, Player P){
    	if(this.teams.get(i) != null){
    		return this.teams.get(i).getName();
    	}
		return null;
    }
    
    public void addPlayerTeam(Player p, String s){
    	if(s.equalsIgnoreCase("blau")){
    		Team t = this.teams.get(0);
    		if(t.hasPlayer(p)){
    			return;
    		}
    		t.addPlayer(p);
    	}else if(s.equalsIgnoreCase("rot")){
    		Team t = this.teams.get(1);
    		if(t.hasPlayer(p)){
    			return;
    		}
    		t.addPlayer(p);
    	}else if(s.equalsIgnoreCase("gelb")){
    		Team t = this.teams.get(2);
    		if(t.hasPlayer(p)){
    			return;
    		}
    		t.addPlayer(p);
    	}else if(s.equalsIgnoreCase("grün")){
    		Team t = this.teams.get(3);
    		if(t.hasPlayer(p)){
    			return;
    		}
    		t.addPlayer(p);
    	}else{
    		return;
    	}
    }
 
    public List<UUID> getPlayers() {
        return this.players;
    }
}