package me.Ste3et_C0st.DiceBedWars.Manager;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Team;
import me.Ste3et_C0st.DiceBedWars.Listener.AchievementListener;
import me.Ste3et_C0st.DiceBedWars.Listener.GameListener;
import me.Ste3et_C0st.DiceBedWars.Listener.Spawner;
import me.Ste3et_C0st.language.German;
import me.Ste3et_C0st.language.Messages;
import me.clip.actionannouncer.ActionAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
//import org.kitteh.tag.TagAPI;






import com.connorlinfoot.titleapi.TitleAPI;


//import io.puharesource.mc.titlemanager.TitleManager;
//import io.puharesource.mc.titlemanager.api.TitleObject;
//import io.puharesource.mc.titlemanager.api.events.TitleEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arena { 

    Integer id;
    //Location
    Location lobby = null;
    Location exit = null;
    Location corner1 = null;
    Location corner2 = null;
    //Timer
    Integer timer_task = null;
    Integer timer_time = 3600;
    Integer timer_lobby = null;
    Integer timer_ltime = 30;
    Integer finishT = null;
    //
    String MapName = "";
    String Builder = "";
    Integer money = 15;
    List<Block> block = new ArrayList<Block>();
    List<Location> signList = new ArrayList<Location>();
    List<Location> Gold = new ArrayList<Location>();
    List<Location> Eisen = new ArrayList<Location>();
    List<Location> Bronze = new ArrayList<Location>();
    
    List<Player> Spectator = new ArrayList<Player>();
    List<Player> LobbyPlayer = new ArrayList<Player>();
    List<Team> Teams = new ArrayList<Team>();
    
    Integer spawnerBronze = null;
    Integer spawnerGold = null;
    Integer spawnerEisen = null;
    HashMap<Player ,String> tabList = new HashMap<Player, String>();
    
    final List<Player> players = new ArrayList<Player>();
 
    public Arena(Location spawn, Location Corner1, Location Corner2, Location exit,String s, int id, String bui){
    	this.lobby = spawn;
        this.exit = exit;
        this.exit.setYaw((float) 0.75);
        this.corner1 = Corner1;
        this.corner2 = Corner2;
        this.MapName = s;
        this.id = id;
        this.Builder = bui;
    }
    
    public HashMap<Player, String> getTabList(){
    	return this.tabList;
    }
    
    public List<Team> getTeams(){
    	return this.Teams;
    }
    
    public List<Player> getSpec(){
    	return this.Spectator;
    }
    
    public List<Player> getLobbyPlayer(){
    	return this.LobbyPlayer;
    }
    
    
    public void cancelSpawner(){
    	if(this.spawnerBronze != null){
    		Bukkit.getScheduler().cancelTask(this.spawnerBronze);
    		this.spawnerBronze = null;
    	}
    	
    	if(this.spawnerGold != null){
    		Bukkit.getScheduler().cancelTask(this.spawnerGold);
    		spawnerGold = null;
    	}
    	
    	if(this.spawnerEisen != null){
    		Bukkit.getScheduler().cancelTask(this.spawnerEisen);
    		this.spawnerEisen = null;
    	}
    }
    
    public int getId() {
        return this.id;
    }
    
    public List<Location> returnGold() {
        return this.Gold;
    }
    
    public List<Location> returnBronze() {
        return this.Bronze;
    }
    
    public List<Location> returnEisen() {
        return this.Eisen;
    }
    
    public boolean emptyteams(){
    	int i = 0;
    	for(Team t : Teams){
    		if(t.getSize() == 0){
    			i++;
    		}
    	}
    	if(i == Teams.size()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public int getTSize(){
    	int i = 0;
    	for(Team t : Teams){
    		if(t != null){
    			if(t.getSize() > 0){
    				i++;
    			}
    		}
    	}
    	
    	return i;
    }
    
    public boolean teamAddPlayer(Player p, int i){
    	int u = 0;
    	for(Team t : Teams){
    		if(u == i){
    			Team.addPlayer(t, p);
    			return true;
    		}
    		
    		u++;
    	}
		return false;
    }
    
    public void StartTimer(){
    	timer_task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
			@Override
			public void run() {
				
				if(emptyteams()){
					CancelTimer();
				}
				
				if(timer_time <= 0){
					CancelTimer();
					for(Player p : getPlayers()){
						Messages.sendMessage(p, German.arena.get(0) , true);
						Finish(null);
					}
				}
				timer_time--;
				for(Player p : getPlayers()){
					if(!p.getGameMode().equals(GameMode.SURVIVAL)){
						p.setGameMode(GameMode.SURVIVAL);
					}
					int minutes = (int) Math.floor(timer_time / 60);
					int seconds = timer_time % 60;
					String s = "";
					if(seconds < 10){
						s = "0";
					}else{
						s = "";
					}
					
					String a = "";
					if(minutes < 10){
						a = "0";
					}else{
						a = "";
					}
					
					ActionAPI.sendPlayerAnnouncement(p, "§9Runde unentschieden in §e" + a + minutes + ":" + s + seconds);
				}

			}
    		
    	}, 0L, 20L);
    	
        if(!signList.isEmpty()){
        	for(Location loc : signList){
                updateSign(loc);
        	}
        }  
    }
    
    @SuppressWarnings("deprecation")
	public void startGame(){

    	ItemStack bronze = new ItemStack(Material.CLAY_BRICK);
    	ItemMeta bim = bronze.getItemMeta();
    	bim.setDisplayName("§cBronze");
    	bronze.setItemMeta(bim);
    	new Spawner(bronze, "Bronze", ArenaManager.getManager().getArena(getId()));
    	
    	ItemStack gold = new ItemStack(Material.GOLD_INGOT);
    	ItemMeta gim = gold.getItemMeta();
    	gim.setDisplayName("§6Gold");
    	gold.setItemMeta(gim);
    	new Spawner(gold, "Gold", ArenaManager.getManager().getArena(getId()));
    	
    	ItemStack eisen = new ItemStack(Material.IRON_INGOT);
    	ItemMeta eim = eisen.getItemMeta();
    	eim.setDisplayName("§bEisen");
    	eisen.setItemMeta(eim);
    	new Spawner(eisen, "Eisen", ArenaManager.getManager().getArena(getId()));
    	
        ItemStack is = new ItemStack(Material.NETHER_STAR);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Main.head + "Back to the lobby");
        is.setItemMeta(im);
        
        if(!signList.isEmpty()){
        	for(Location loc : signList){
                updateSign(loc);
        	}
        }
        
        List<Entity> entList = corner1.getWorld().getEntities();
        
        for(Entity current : entList){
            if (current instanceof Item){
            	if(GameListener.isInside(current.getLocation(), corner1, corner2)){
            		((Item) current).setItemStack(new ItemStack(Material.AIR));
            		((Item) current).setTicksLived(0);
            		 current.remove();
            	}
            }
            }
        
        
        for(Team t : Teams){
        	if(t != null){
            		if(t.getSize() <= 0){
            			t.setBedState(false);
            		}else{
                    	for(Player p : t.teamGetList()){
                    		p.setFlying(false);
                    		p.setFlySpeed(0.1F);
                    		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + Utils.trimPlayerName(p.getName(), 14));
                    		p.getInventory().clear();
                    		p.updateInventory();
                    		p.teleport(t.getSpawn());
                    		//TagAPI.refreshPlayer(p);
                    	}
                    }
        	}
        }
        ArenaManager.getManager().hideP();
    	StartTimer();
    }
    
    public void lobbytimer(){
    	ArenaManager.getManager().clearArena(ArenaManager.getManager().getArena(getId()));
    	this.timer_lobby = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
    		int timer = timer_ltime;
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				timer--;
				int i = 0;
				
				for(Team t : Teams){
					if(t != null){
		        		if(t.getSize() >= 1){
		        			i++;
		        		}
					}
				}
				
				if(!(i >= 2)){
					CancelLobby();
				}
				
				if(timer == 10){
					String s = Builder;
					s = s.replace("#", ", ");
					Utils.sendRound("Map erbaut von: §6" + s, true, ArenaManager.getManager().getArena(getId()));
				}
				
				if(timer == 2){
					ArenaManager.getManager().clearArena(ArenaManager.getManager().getArena(getId()));
				}
				
				if(timer == 20 || timer == 10 || timer <= 5 ){
					GameListener.sendSound(getPlayers(), Sound.ORB_PICKUP);
				}
				
				for(Player p : getPlayers()){
					if(timer <= 5){
						TitleAPI.sendFullTitle(p, 20, 20, 20, "§eSpiel Startet in","§c" + timer + " §bSekunde/n");
					}
					
					if(!p.getGameMode().equals(GameMode.SURVIVAL)){
						p.setGameMode(GameMode.SURVIVAL);
						//TagAPI.refreshPlayer(p);
					}
					p.setLevel(timer);
				}
				
				
				if(timer <= 0){
					CancelLobby();
					startGame();
			        if(!signList.isEmpty()){
			        	for(Location loc : signList){
			                updateSign(loc);
			        	}
			        }   
				}
				
				if(emptyteams()){
					CancelLobby();
				}
				
			}
    		
    	}, 0L, 20L);
    	
        if(!signList.isEmpty()){
        	for(Location loc : signList){
                updateSign(loc);
        	}
        }   
    }
    
    
    
    @SuppressWarnings("deprecation")
	public void Finish(Team t){
    	if(t != null){
    		Utils.sendRound2("§9Das Team: " + ChatColor.translateAlternateColorCodes('&', t.getTeamColor()) + t.getName() + " §9hat die Bedwars Map gewonnen", true, ArenaManager.getManager().getArena(this.id));
    	}else{
    		Utils.sendRound2("Die Runde hat keiner gewonnen", true, ArenaManager.getManager().getArena(this.id));
    	}
    	
    	if(t != null){
        	for(Player pl : this.players){
        		pl.teleport(lobby);
        		
        		Main.getPlayerPoints().getAPI().give(pl.getUniqueId(), getMoney());
        		Messages.sendMessage(pl, "§9Dein Team hat die Bedwars Map gewonnen :).", true);
        		Messages.sendMessage(pl, "§9Du hast§6 " + this.money + " Tokens §9erhalten.", true);
        		
        		AchievementListener.giveAchivement(pl, 0);
        		
        		ArenaManager.getManager().removePlayer(pl);
                pl.setPlayerListName(getTabList().get(pl));
                getTabList().remove(pl);
                getLobbyPlayer().add(pl);
                for(Player player : Bukkit.getOnlinePlayers()){
                		pl.showPlayer(player);
                }
        	}
    	}else{
    		for(Player pl : this.players){
        		pl.teleport(lobby);
        		
        		ArenaManager.getManager().removePlayer(pl);
                pl.setPlayerListName(getTabList().get(pl));
                getTabList().remove(pl);
                getLobbyPlayer().add(pl);
                for(Player player : Bukkit.getOnlinePlayers()){
                		pl.showPlayer(player);
                }
        	}
    	}


    	this.finishT = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
    		int timer = 10;
			@Override
			public void run() {
				
				timer--;
				
				for(Player pl : LobbyPlayer){
					pl.setLevel(timer);
				}
				
				if(timer <= 0){
					ChancelFinish();
					ArenaManager.getManager().Finish(ArenaManager.getManager().getArena(getId()));
			        if(!signList.isEmpty()){
			        	for(Location loc : signList){
			                updateSign(loc);
			        	}
			        }   
				}
				
				if(LobbyPlayer.isEmpty()){
					ChancelFinish();
				}
				
				if(timer < 6 && timer != 0){
					Utils.sendRound2("§9Arena wird in §6" + timer + " Sekunde/n §9neugestartet", true, ArenaManager.getManager().getArena(getId()));
					GameListener.sendSound(LobbyPlayer, Sound.ITEM_PICKUP);
				}
			}
    		
    	}, 0L, 20L); 
    	
    	ArenaManager.getManager().hideP();
    }
    
    public void ChancelFinish() {
    	if(this.finishT != null){
			Bukkit.getScheduler().cancelTask(this.finishT);
			this.finishT = null;
			if(!signList.isEmpty()){
	        	for(Location loc : signList){
	                updateSign(loc);
	        	}
	        } 
    	}
	}
    
    public void CancelLobby() {
    	if(this.timer_lobby != null){
    			Bukkit.getScheduler().cancelTask(this.timer_lobby);
    			this.timer_lobby = null;
    			this.timer_ltime = 30;
    			if(!signList.isEmpty()){
		        	for(Location loc : signList){
		                updateSign(loc);
		        	}
		        } 
    	}
		
	}

    public void CancelTimer() {
    	if(this.timer_task != null){
    			Bukkit.getScheduler().cancelTask(this.timer_task);
    			this.timer_task = null;
    			this.timer_time = 3600;
    			if(!signList.isEmpty()){
		        	for(Location loc : signList){
		                updateSign(loc);
		        	}
		        } 
    	}
	}

	public List<Player> getPlayers() {
        return this.players;
    }

	public String getName() {
		return this.MapName;
	}

	public Location getCorner1() {
		return corner1;
	}

	public Integer getGameTimer() {
		return this.timer_time;
	}

	public Integer getRoundTimer() {
		return this.timer_ltime;
	}

	public Integer getMoney() {

		return this.money;
	}

	public Location getLobby() {
		return this.lobby;
	}

	public Location getCorner2() {
		return this.corner2;
	}

	public Location getExit() {
		return this.exit;
	}

	public boolean isSignExist(Location s){
		for(Location ls : getSign()){
			if(ls.equals(s)){
				return true;
			}
		}
		return false;
	}
	
	public List<Location> getSign(){
		return this.signList;
	}
	
	public void addSign(Location s){
		this.signList.add(s);
	}
	
    public void updateSign(Location loc)
    {
        if(!isSignExist(loc)){
        	addSign(loc);
        }
        String s = "";
        int iAD = 0;
        iAD = getId();
        
		if(isStartet() != false){
			s = "§cRunning";
		}else if(isLobby() != false){
			s = "§6" + getPlayers().size() + "/" + getTeamSize()*4;
		}else{
			s = "§8" + getPlayers().size() + "/" + getTeamSize()*4;
		}
        
        BlockState state = loc.getBlock().getState();
        if(state instanceof Sign == false)
            return;
        org.bukkit.block.Sign sign = (org.bukkit.block.Sign)state;
        sign.setLine(1, "§8BedWars-" + iAD);
        sign.setLine(3, s);
        
        sign.update();
    }

	public void setMoney(int mone) {
		this.money = mone;
	}

	public List<Block> getBlock() {
		return this.block;
	}

	public void addGold(Location loc) {
		this.Gold.add(loc);
	}
	
	public void addBronze(Location loc) {
		this.Bronze.add(loc);
	}
	
	public void addEisen(Location loc) {
		this.Eisen.add(loc);
	}


	public boolean isStartet() {
		if(this.timer_task != null){
			return true;
		}else{
			return false;
		}
	}


	public boolean isLobby() {
		if(this.timer_lobby == null){
			return false;
		}else{
			return true;
		}
	}


	public void setSpawner(String string, int Spawn) {
		if(string.equalsIgnoreCase("Gold")){
			if(this.spawnerGold == null){
				this.spawnerGold = Spawn;
				return;
			}
		}
		
		if(string.equalsIgnoreCase("Eisen")){
			if(this.spawnerEisen == null){
				this.spawnerEisen = Spawn;
				return;
			}
		}
		
		if(string.equalsIgnoreCase("Bronze")){
			if(this.spawnerBronze == null){
				this.spawnerBronze = Spawn;
				return;
			}
		}
	}

	public String getBuilder() {
		
		return this.Builder;
	}

	public Integer returnTeamFromColor(String string) {
		int i = 0;
		for(Team t : getTeams()){
			if(t.Name.equalsIgnoreCase(string)){
				return i;
			}
			i++;
		}
		return null;
	}

	public int getTeamSize() {
		int i = 0;
		for(Team t : Teams){
			if(t != null){
				i++;
			}
		}
		return i;
	}

	public void removeTeamPlayer(Player p) {
		Team.removePlayer(p);
	}

	public int getIDBack(String color) {
		int i = 0;
		for(Team t : Teams){
			if(t != null){
				if(t.getName().equalsIgnoreCase(color)){
					return i;	
				}
				
				i++;
			}
		}
		return i;
	}
}