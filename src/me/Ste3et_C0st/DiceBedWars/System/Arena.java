package me.Ste3et_C0st.DiceBedWars.System;

import org.bukkit.Location;
 
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena { 

    Integer id;
    Location spawn;
    List<UUID> players = new ArrayList<UUID>();
    
    public Arena(Location spawn, int id) {
        this.spawn = spawn;
        this.id = id;
    }
 
    public int getId() {
        return this.id;
    }
 
    public List<UUID> getPlayers() {
        return this.players;
    }
}