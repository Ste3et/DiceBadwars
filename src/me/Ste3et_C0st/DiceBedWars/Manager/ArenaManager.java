package me.Ste3et_C0st.DiceBedWars.Manager;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Listener.GameListener;
import me.Ste3et_C0st.language.Messages;
import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.kitteh.tag.TagAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaManager{
	
    private static ArenaManager am;

    private final List<Arena> arenas = new ArrayList<Arena>();
    private HashMap<Player,Arena> spec = new HashMap<Player ,Arena>();
    
    private int arenaSize = 0;
 
    private ArenaManager() {}

    public static ArenaManager getManager() {
        if (am == null)
            am = new ArenaManager();
 
        return am;
    }
    
    public HashMap<Player, Arena> returnSpec(){
    	return this.spec;
    }
    
    public Arena getSpec(Player p){
    	if(spec.get(p) != null){
    		return spec.get(p); 
    	}else{
    		return null;
    	}
    }
    
    public List<Arena> getArenaList(){ 
        return this.arenas;
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
        
        if(a.getPlayers().size() >= a.getTeamSize()*4){
        	if(p.hasPermission("bedwars.vip")){
        		for(Player pl : a.getPlayers()){
        			if(!pl.hasPermission("bedwars.vip")){
        				Messages.sendMessage(pl, "Du wurdest wegen einen Reservierten Slot gekickt.", true);
        				removePlayer(pl);
        				break;
        			}
        		}
        		
        		Messages.sendMessage(p, "Es konnte kein Spieler gekickt werden", true);
        		return;
        	}else{
            	Messages.sendMessage(p, "Es sind zu viele Spieler im Spiel", true);
            	Messages.sendMessage(p, "Du kannst aber joinen wenn du VIP oder höher bist", true);
            	return;
        	}
        }
        
        Utils.sendRound("Der Spieler §2" + p.getName() + " §7ist beigetreten.", true, a);
        
        a.getPlayers().add(p);
        p.sendMessage("§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=-§6=§e-§6=§e-§6=§e-§6=§e-§6=");
        String s = a.getName().replaceAll("[0-9]","");
        p.sendMessage("§7Arena: §6" + s );
        String builder = a.getBuilder();
        builder = builder.replace(", ", "");
        p.sendMessage("§7Erbauer: §c" + builder );
        p.sendMessage("§7Gewinn: §c" + a.getMoney() + " Tokens");
        p.sendMessage("§7Teams: §c" + a.getTeamSize());
        p.sendMessage("§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=-§6=§e-§6=§e-§6=§e-§6=§e-§6=");
        
        p.getInventory().setArmorContents(null);
        p.getInventory().clear();
        p.teleport(a.lobby);
        ItemStack is = new ItemStack(Material.WOOL);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Main.head + "Team Selector");
        is.setItemMeta(im);
        p.getInventory().setItem(0, is);
        is = new ItemStack(Material.NETHER_STAR);
        im = is.getItemMeta();
        im.setDisplayName(Main.head + "Back to the lobby");
        is.setItemMeta(im);
        p.getInventory().setItem(8, is);
        p.updateInventory();
        if(a.team_1_player.size() > 1 && a.team_2_player.size() > 1 && a.team_3_player.size() > 1 && a.team_4_player.size() > 1 && a.team_5_player.size() > 1 && a.team_6_player.size() > 1 && a.team_7_player.size() > 1 && a.team_8_player.size() > 1){
        	if(a.timer_lobby == null){
        		a.lobbytimer();
        	}
        }
        a.getTabList().put(p, p.getPlayerListName());
        p.setPlayerListName(Utils.trimPlayerName(p.getName(), 16));
        hideP();
        if(!a.signList.isEmpty()){
        	for(Location loc : a.signList){
                a.updateSign(loc);
        	}
        }   
    }
    
    public void hideP(){
    	List<Player> list1 = new ArrayList<>();
    	List<Player> list2 = new ArrayList<>();
    	
    	for(Player player : Bukkit.getOnlinePlayers()){
    		if(isInGame(player)){
    			list1.add(player);
    		}else{
    			list2.add(player);
    		}
    	}
    	
        for(Player pl : list1){
            for(Player player : list1){
            	if(!pl.canSee(player)){
            		Arena a1 = ArenaManager.getManager().returnArena(pl);
            		Arena a2 = ArenaManager.getManager().returnArena(player);
            		if(a1 != a2){
            			list2.add(player);
            		}else{
            			pl.showPlayer(player);
            		}
            	}	
            }
        	
        	for(Player player : list2){
        		pl.hidePlayer(player);
        	}
        }
    }

    @SuppressWarnings("deprecation")
	public void removePlayer(Player p) {
        Arena a = null;

        for (Arena arena : this.arenas) {
            if (arena.getPlayers().contains(p))
                a = arena;
        }

        if (a == null) {
            p.sendMessage("Invalid operation!");
            return;
        }
        
        if(BarAPI.hasBar(p)){
        	BarAPI.removeBar(p);
        }
        p.getEnderChest().clear();
		p.setFireTicks(0);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		if(a.isLobby() && a.isStartet() == false){
			 if(!(a.teamSize(1) > 1 && a.teamSize(2) > 1 && a.teamSize(3) > 1 && a.teamSize(4) > 1 && a.teamSize(5) > 1 && a.teamSize(6) > 1 && a.teamSize(7) > 1 && a.teamSize(8) > 1)){
				 a.CancelLobby();
				if(a.getPlayers().size() >= 1){
					Utils.sendRound("Map Start wurde beendet zu wenig Spieler :(", true, a);
				}
			}
		}
		a.getLobbyPlayer().remove(p);
        a.teamRemovePlayer(p, a.teamGetPlayer(p));
        TagAPI.refreshPlayer(p);
        p.updateInventory();
        hideP();
    }
    
    public Arena returnArena(Player p){
        for(Arena a : arenas){
            if(a.getPlayers().contains(p)){
            	 return a;
            }  
        }
        return null;
    }
    
    public boolean checkTeam(Arena a, Player p){
    	if(p != null){
    		ArenaManager.getManager().removePlayer(p);
    		a.getPlayers().remove(p);
    	    a.LobbyPlayer.add(p);
    	}
    	
    	Boolean t1 = a.getBedState(1);
    	Boolean t2 = a.getBedState(2);
    	Boolean t3 = a.getBedState(3);
    	Boolean t4 = a.getBedState(4);
    	Boolean t5 = a.getBedState(5);
    	Boolean t6 = a.getBedState(6);
    	Boolean t7 = a.getBedState(7);
    	Boolean t8 = a.getBedState(8);
    	Integer i = 0;
    	
    	if(a.teamSize(1) <= 0){
    		t1 = false;
    		a.team_1_player.clear();
    	}
    	
    	if(a.teamSize(2) <= 0){
    		t2 = false;
    		a.team_2_player.clear();
    	}
    	
    	if(a.teamSize(3) <= 0){
    		t3 = false;
    		a.team_3_player.clear();
    	}
    	
    	if(a.teamSize(4) <= 0){
    		t4 = false;
    		a.team_4_player.clear();
    	}
    	
    	if(a.teamSize(5) <= 0){
    		t5 = false;
    		a.team_5_player.clear();
    	}
    	
    	if(a.teamSize(6) <= 0){
    		t6 = false;
    		a.team_6_player.clear();
    	}
    	
    	if(a.teamSize(7) <= 0){
    		t7 = false;
    		a.team_7_player.clear();
    	}
    	
    	if(a.teamSize(8) <= 0){
    		t8 = false;
    		a.team_8_player.clear();
    	}
    	
    	
    	if(t1 == true || a.teamSize(1) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t2 == true || a.teamSize(2) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t3 == true || a.teamSize(3) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t4 == true || a.teamSize(4) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t5 == true || a.teamSize(5) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t6 == true || a.teamSize(6) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t7 == true || a.teamSize(7) >= 1){
    		i = i+ 1;
    	}
    	
    	if(t8 == true || a.teamSize(8) >= 1){
    		i = i+ 1;
    	}
    	
    	if(i == 1){
    		if(a.teamSize(1) >= 1){
    			a.Finish(1);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(2) >= 1){
    			a.Finish(2);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(3) >= 1){
    			a.Finish(3);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(4) >= 1){
    			a.Finish(4);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(5) >= 1){
    			a.Finish(5);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(6) >= 1){
    			a.Finish(6);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(7) >= 1){
    			a.Finish(7);
    			clearArena(a);
        		return true;
    		}
    		
    		if(a.teamSize(8) >= 1){
    			a.Finish(8);
    			clearArena(a);
        		return true;
    		}
    	}
		return false;
    }

    public void Finish(Arena a) {

    	clearArena(a);
        
		for(Player p : a.getPlayers()){
			removePlayer(p);
		}
		
		
    	if(!a.LobbyPlayer.isEmpty()){
        	for(Player player : a.LobbyPlayer){
        		player.teleport(a.getExit());
        		if(a.tabList.get(player) != null){
        			player.setPlayerListName(a.tabList.get(player));
        		}
        	}	
    	}

    	
		if(a.getPlayers() != null){
			if(!a.getPlayers().isEmpty()){
				for(Player p : a.getPlayers()){
					p.teleport(a.getExit());
			        removePlayer(p);
				}
			}
		}
		
		a.cancelSpawner();

    	a.setEnderchest(1, null);
    	a.setEnderchest(2, null);
    	a.setEnderchest(3, null);
    	a.setEnderchest(4, null);
    	
    	a.setEnderchest(5, null);
    	a.setEnderchest(6, null);
    	a.setEnderchest(7, null);
    	a.setEnderchest(8, null);
    	
		Editor.setbed(a, 1);
		Editor.setbed(a, 2);
		Editor.setbed(a, 3);
		Editor.setbed(a, 4);
		
		Editor.setbed(a, 8);
		Editor.setbed(a, 7);
		Editor.setbed(a, 6);
		Editor.setbed(a, 5);
		
		a.setBedState(1, true);
		a.setBedState(2, true);
		a.setBedState(3, true);
		a.setBedState(4, true);
		
		a.setBedState(5, true);
		a.setBedState(6, true);
		a.setBedState(7, true);
		a.setBedState(8, true);
		
		a.CancelTimer();
		a.CancelLobby();
    	a.getPlayers().clear();
    	a.getBlock().clear();
        if(!a.signList.isEmpty()){
        	for(Location loc : a.signList){
                a.updateSign(loc);
        	}
        }
        
        if(!returnSpec().isEmpty()){
            for(Player pl : returnSpec().keySet()){
            	leave(pl, false, false);
            }
        }
		return;
	}

    
    
    public void clearArena(Arena a){
    	if(!a.block.isEmpty()){
    		for(Block b : a.block){
    			b.setType(Material.AIR);
    		}
    	}
    	
    	List<Entity> entList = a.getCorner1().getWorld().getEntities();
    	for(Entity current : entList){
            if (current instanceof Item){
            	if(GameListener.isInside(current.getLocation(), a.getCorner1(), a.getCorner2())){
            		current.remove();
            	}
            }
            
        }
    }
    
	public Arena createArena(Location l, Location l1, Location l2, Location l3, String s, String build) {        
        this.arenaSize++;
 
        Arena a = new Arena(l, l1, l2, l3, s,this.arenaSize, build);
        this.arenas.add(a);
 
        return a;
    }

    public boolean isInGame(Player p) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(p))
                return true;
        }
        return false;
    }

	public boolean ArenaExist(String string) {
    	for(Arena a : arenas){
    		if(a.getName().equalsIgnoreCase(string)){
    			return true;
    		}
    	}
    	return false;
	}

    public Integer getIDBackfromName(String name){
    	for(Arena a : arenas){
    		if(a.getName().equalsIgnoreCase(name)){
    			return a.getId();
    		}
    	}
    	return 987554;
    }

    @SuppressWarnings("deprecation")
	public void enterSpec(Arena a, Player p){
    	p.getInventory().clear();
    	p.updateInventory();
		Vector v1 = new Vector();
		v1.add(a.getCorner1().toVector());
		
		Vector v2 = new Vector();
		v2.add(a.getCorner2().toVector());
		
		if(v1.getY() > v2.getY()){
			v2.setY(v1.getY() - 2);
		}
		
		if(v2.getY() < v1.getY()){
			v1.setY(v2.getY() - 2);
		}
		
		Vector middle = v1.getMidpoint(v2);
		
		Location l = middle.toLocation(a.getCorner1().getWorld());
		
		p.teleport(l);
    	p.getInventory().clear();
    	p.updateInventory();
    	p.getInventory().setItem(0, Editor.is(Material.COMPASS, Main.head + "Spieler Wechseln", null, 0, 1));
    	p.getInventory().setItem(8, Editor.is(Material.NETHER_STAR, Main.head + "Back to the lobby", null, 0, 1));
    	p.updateInventory();
		if(!p.getGameMode().equals(GameMode.ADVENTURE)){
			p.setGameMode(GameMode.ADVENTURE);
		}
    	p.setSaturation(Float.MAX_VALUE);
		p.setCanPickupItems(false);
		p.setAllowFlight(true);
		p.setFlying(true);
		p.setNoDamageTicks(Integer.MAX_VALUE);
		Main.ghostFactory.addPlayer(p);
		Main.ghostFactory.setGhost(p, true);
    }
    
    public void leave(Player p, Boolean exit, Boolean save){
    	if(getSpec(p) != null){
    		if(save == true){
    			return;
    		}
    		if(exit == true){
        		Arena a = getSpec(p);
        		returnSpec().remove(p);
        		p.setFallDistance(0);
        		p.setFireTicks(0);
        		p.setCanPickupItems(true);
        		p.setAllowFlight(false);
        		p.setFlying(false);
        		p.setGameMode(GameMode.ADVENTURE);
        		p.setSaturation((float) 4.0);
        		p.teleport(a.getExit());
        		p.setSaturation((float) 4.0);
        		p.setNoDamageTicks(0);
    		}else{
    			Arena a = getSpec(p);
        		returnSpec().remove(p);
        		p.setFallDistance(0);
        		p.setFireTicks(0);
        		p.setCanPickupItems(true);
        		p.setAllowFlight(false);
        		p.setFlying(false);
        		p.setSaturation((float) 4.0);
        		p.setNoDamageTicks(0);
        		addPlayer(p, a.getId());
        		p.setSaturation((float) 4.0);
    		}
    	}
    }
    
	public boolean isLeave(Player p, Arena a, int i) {
		if(i == 0){
			return false;
		}
		
		if(a.teamGetList(i).size() - 1 <= 0){
			a.setBedState(i, false);
			Utils.sendRound("Das Team: " + ChatColor.translateAlternateColorCodes('&', a.getColor(i)) + a.getName(i) + " hat aufgegeben", true, a);
			if(checkTeam(a, p) == false){
				removePlayer(p);
				return true;
			}
			return true;
		}
		return false;
	}

	public void addSpec(Arena a,Player p) {
		if(getSpec(p) == null){
			this.spec.put(p, a);
			enterSpec(a, p);
		}else{
			Messages.sendMessage(p, "Du bist bereits ein Spectator", true);
		}
		
	}
}