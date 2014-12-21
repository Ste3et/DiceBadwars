package me.Ste3et_C0st.DiceBedWars.Manager;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.Listener.AchievementListener;
import me.Ste3et_C0st.DiceBedWars.Listener.GameListener;
import me.Ste3et_C0st.language.Messages;
//import me.confuser.barapi.BarAPI;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;
//import org.kitteh.tag.TagAPI;

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
        
        Utils.sendRound("Der Spieler §2" + p.getName() + " §9ist beigetreten.", true, a);
        
        a.getPlayers().add(p);
        p.sendMessage("§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=§e-§6=-§6=§e-§6=§e-§6=§e-§6=§e-§6=");
        String s = a.getName().replaceAll("[0-9]","");
        p.sendMessage("§9Arena: §6" + s );
        String builder = a.getBuilder();
        builder = builder.replace(", ", "");
        p.sendMessage("§9Erbauer: §c" + builder );
        p.sendMessage("§9Gewinn: §c" + a.getMoney() + " Tokens");
        p.sendMessage("§9Teams: §c" + a.getTeamSize());
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
        if(a.getTSize() >= 2){
        	if(a.timer_lobby == null){
        		a.lobbytimer();
        	}
        }
        
		 if(Main.permission.playerInGroup(p, "Admin") || Main.permission.playerInGroup(p, "Owner") || Main.permission.playerInGroup(p, "Supporter")){
			 for(String ach : AchievementListener.Achievents){
				 String sl = ach;
				 String[] ac = sl.split("#");
				 Main.permission.playerAdd(p, "-Bedwars." + ac[0]);
			 }
		 }
		 
		 AchievementListener.setAchivements(p);
		 
	        is = new ItemStack(Material.BOOK);
	        im = is.getItemMeta();
	        im.setDisplayName(Main.head + "Achievements");
	        is.setItemMeta(im);
	        p.getInventory().setItem(4, is);
	        
	        p.updateInventory();
		 
        a.getTabList().put(p, p.getPlayerListName());
        p.setPlayerListName(Utils.trimPlayerName(p.getName(), 16));
        hideP();
        if(!a.signList.isEmpty()){
        	for(Location loc : a.signList){
                a.updateSign(loc);
        	}
        }   
        
        if(!p.getActivePotionEffects().isEmpty()){
        	for(PotionEffect pe : p.getActivePotionEffects()){
        		p.removePotionEffect(pe.getType());
        	}
        }
    }
    
    @SuppressWarnings("deprecation")
	public void hideP(){
    	List<Player> list1 = new ArrayList<>();
    	List<Player> list2 = new ArrayList<>();
    	
    	for(Player player : Bukkit.getOnlinePlayers()){
    		if(isInGame(player)){
    			list1.add(player);
    		}else if(!isInGame(player)){
    			list2.add(player);
    		}
    	}
    	
       for(Player pl : list1){
            for(Player player : list1){
            	if(returnArena(pl).getId() != returnArena(player).getId()){
            		pl.hidePlayer(player);
            	}else{
            		pl.showPlayer(player);
            	}
            }
            
            for(Player player : list2){
            	pl.hidePlayer(player);
            }
       	}
       
       for(Player pl : list2){
    	   for(Player player : Bukkit.getOnlinePlayers()){
    		   pl.showPlayer(player);
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
        
        /*if(BarAPI.hasBar(p)){
        	BarAPI.removeBar(p);
        }*/
        
        if(!p.getActivePotionEffects().isEmpty()){
        	for(PotionEffect pe : p.getActivePotionEffects()){
        		p.removePotionEffect(pe.getType());
        	}
        }
        
        p.getEnderChest().clear();
		p.setFireTicks(0);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		if(a.isLobby() && a.isStartet() == false){
			 if(!(a.getTSize() >= 2)){
				 a.CancelLobby();
				if(a.getPlayers().size() >= 1){
					Utils.sendRound("Map Start wurde beendet zu wenig Spieler :(", true, a);
				}
			}
		}
		a.getLobbyPlayer().remove(p);
        a.removeTeamPlayer(p);
        //TagAPI.refreshPlayer(p);
        p.updateInventory();
        hideP();
        
        if(!p.getActivePotionEffects().isEmpty()){
        	for(PotionEffect pe : p.getActivePotionEffects()){
        		p.removePotionEffect(pe.getType());
        	}
        }
    }
    
    public Arena returnArena(Player p){
        for(Arena a : arenas){
            if(a.getPlayers().contains(p)){
            	 return a;
            }  
        }
        return null;
    }
    
    public boolean checkTeam(Arena a, Player p, Boolean b){
    	Team team = null;
    	if(p != null){
    		team = Team.getTeam(p);
    		if(team.getSize() - 1 <= 0){
    			team.setBedState(false);
    		}
    		
    		ArenaManager.getManager().removePlayer(p);
    		a.getPlayers().remove(p);
    	    a.getLobbyPlayer().add(p);
    	}
    	
    	Integer i = 0;
    	
    	for(Team t: a.getTeams()){
    		if(t != null){
    			if(t.getSize() <= 0){
    				t.teamGetList().clear();
    			}
    		}
    	}
    	
    	for(Team t: a.getTeams()){
    		if(t != null){
    			if(t.getState() || t.getSize() >= 1){
    				i = i+ 1;
    			}
    		}
    	}
		
    	if(i == 1){
    		for(Team t : a.getTeams()){
    			if(t != null){
    				if(t.getSize() >= 1){
    					if(b == true){
    						a.Finish(t);
    					}else{
    						a.Finish(null);
    					}
    					
    	    			clearArena(a);
    	        		return true;
    				}
    			}
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

		for(Team t : a.getTeams()){
			if(t != null){
				if(t.getEnderchest() != null){
					t.getEnderchest().clear();
				}
				t.setBed();
				t.setBedState(true);
			}
		}
		
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
    	List<Entity> entList = a.getCorner1().getWorld().getEntities();
    	for(Entity current : entList){
            if (current.getType() != EntityType.VILLAGER ){
            	if(GameListener.isInside(current.getLocation(), a.getCorner1(), a.getCorner2())){
            		current.remove();
            	}
            }
            
        }
    	
    	if(!a.block.isEmpty()){
    		for(Block b : a.block){
    			b.setType(Material.AIR);
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
		p.setGameMode(GameMode.SPECTATOR);
		Main.ghostFactory.addPlayer(p);
		Main.ghostFactory.setGhost(p, true);
		hideP();
    }

    public boolean sameTeam(Team t1, Team t2){
    	if(t1.equals(t2)){
    		return true;
    	}else{
    		return false;
    	}
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
        		Main.ghostFactory.setGhost(p, false);
        		Main.ghostFactory.removePlayer(p);
        		p.teleport(a.getExit());
        		p.setSaturation((float) 4.0);
        		p.setNoDamageTicks(0);
        		p.setGameMode(GameMode.SURVIVAL);
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
        		Main.ghostFactory.setGhost(p, false);
        		Main.ghostFactory.removePlayer(p);
        		addPlayer(p, a.getId());
        		p.setSaturation((float) 4.0);
        		p.setGameMode(GameMode.SURVIVAL);
    		}
    	}
    }
    
	public boolean isLeave(Player p, Arena a, int i) {
		if(i == 0){
			return false;
		}
		
		if(a.getTeams().get(i).getSize() - 1 <= 0){
			Team t = a.getTeams().get(i);
			t.setBedState(false);
			Utils.sendRound("Das Team: " + ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + t.getName() + " hat aufgegeben", true, a);
			if(checkTeam(a, p, false) == false){
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