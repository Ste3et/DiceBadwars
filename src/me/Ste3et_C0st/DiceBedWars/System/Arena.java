package me.Ste3et_C0st.DiceBedWars.System;

import org.bukkit.Location;
 
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena { 

    Integer id;
    Location spawn;
    List<UUID> players = new ArrayList<UUID>();
    List<Team> teams = new ArrayList<Team>();
    
    public Arena(Location spawn, int id) {
        this.spawn = spawn;
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