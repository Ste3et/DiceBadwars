package me.Ste3et_C0st.DiceBedWars.System;

import me.Ste3et_C0st.DiceBedWars.GUI.createItemStack;
import me.Ste3et_C0st.DiceBedWars.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager{
    private static ArenaManager am;

    private final List<Arena> arenas = new ArrayList<Arena>();
    private int arenaSize = 0;
 
    private ArenaManager() {}

    public static ArenaManager getManager() {
        if (am == null)
            am = new ArenaManager();
 
        return am;
    }

    public Arena getArena(int i){ 
        for (Arena a : this.arenas) {
            if (a.getId() == i) {
                return a;
            }
        }

        return null;
    }

    @SuppressWarnings("deprecation")
	public void addPlayer(Player p, int i) {
        Arena a = this.getArena(i);
        if (a == null) {
            p.sendMessage("Invalid arena!");
            return;
        }

        if (this.isInGame(p)) {
            p.sendMessage("Cannot join more than 1 game!");
            return;
        }
 
        a.getPlayers().add(p.getUniqueId());
        p.getInventory().clear();
        p.updateInventory();
        p.teleport(a.Lobby);
        p.getInventory().setItem(0, createItemStack.returnIS(main.messageHead + "Team wählen", Material.BED, null, 1));
        p.updateInventory();
    }
    
    public void removePlayer(Player p) {
        Arena a = null;


        for (Arena arena : this.arenas) {
            if (arena.getPlayers().contains(p.getUniqueId()))
                a = arena;
        }

        if (a == null) {
            p.sendMessage("Invalid operation!");
            return;
        }
 
        a.getPlayers().remove(p.getName());

        p.setFireTicks(0);
    }

    public Arena createArena(Location l) {        
        this.arenaSize++;
		return null;
 
       // Arena a = new Arena(l, this.arenaSize);
       // this.arenas.add(a);
 
       // return a;
    }
 
    public boolean isInGame(Player p) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(p.getUniqueId()))
                return true;
        }
        return false;
    }
}