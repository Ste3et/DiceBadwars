package me.Ste3et_C0st.DiceBedWars.Manager;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.Listener.GameListener;
import me.Ste3et_C0st.DiceBedWars.Listener.Spawner;
import me.Ste3et_C0st.language.German;
import me.Ste3et_C0st.language.Messages;
import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
 


import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.kitteh.tag.TagAPI;

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
    Location team_1_spawn = null;
    Location team_2_spawn = null;
    Location team_3_spawn = null;
    Location team_4_spawn = null;
    
    Location team_5_spawn = null;
    Location team_6_spawn = null;
    Location team_7_spawn = null;
    Location team_8_spawn = null;
    //Team-ChatColor
    String team_1_color = null;
    String team_2_color = null;
    String team_3_color = null;
    String team_4_color = null;
    
    String team_5_color = null;
    String team_6_color = null;
    String team_7_color = null;
    String team_8_color = null;
    //Team-Name
    String team_1_name = "";
    String team_2_name = "";
    String team_3_name = "";
    String team_4_name = "";
    
    String team_5_name = "";
    String team_6_name = "";
    String team_7_name = "";
    String team_8_name = "";
    //Team-Spieler
    List<Player> team_1_player = new ArrayList<Player>();
    List<Player> team_2_player = new ArrayList<Player>();
    List<Player> team_3_player = new ArrayList<Player>();
    List<Player> team_4_player = new ArrayList<Player>();
    
    List<Player> team_5_player = new ArrayList<Player>();
    List<Player> team_6_player = new ArrayList<Player>();
    List<Player> team_7_player = new ArrayList<Player>();
    List<Player> team_8_player = new ArrayList<Player>();
    //Team-Bed
    Block team_1_bed = null;
    Block team_2_bed = null;
    Block team_3_bed = null;
    Block team_4_bed = null;
    
    Block team_5_bed = null;
    Block team_6_bed = null;
    Block team_7_bed = null;
    Block team_8_bed = null;
    
    
    BlockFace team_1_bed_f = null;
    BlockFace team_2_bed_f = null;
    BlockFace team_3_bed_f = null;
    BlockFace team_4_bed_f = null;
    
    BlockFace team_5_bed_f = null;
    BlockFace team_6_bed_f = null;
    BlockFace team_7_bed_f = null;
    BlockFace team_8_bed_f = null;
    
    Boolean bed1 = true;
    Boolean bed2 = true;
    Boolean bed3 = true;
    Boolean bed4 = true;
    
    Boolean bed5 = true;
    Boolean bed6 = true;
    Boolean bed7 = true;
    Boolean bed8 = true;
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
    
    Inventory Enderchest1 = null;
    Inventory Enderchest2 = null;
    Inventory Enderchest3 = null;
    Inventory Enderchest4 = null;
    
    Inventory Enderchest5 = null;
    Inventory Enderchest6 = null;
    Inventory Enderchest7 = null;
    Inventory Enderchest8 = null;
    
    List<Player> Spectator = new ArrayList<Player>();
    List<Player> LobbyPlayer = new ArrayList<Player>();
    
    Integer spawnerBronze = null;
    Integer spawnerGold = null;
    Integer spawnerEisen = null;
    HashMap<Player ,String> tabList = new HashMap<Player, String>();
    
    final List<Player> players = new ArrayList<Player>();
 
    public Arena(Location spawn, Location Corner1, Location Corner2, Location exit,String s, int id, String bui){
    	this.lobby = spawn;
        this.exit = exit;
        this.corner1 = Corner1;
        this.corner2 = Corner2;
        this.MapName = s;
        this.id = id;
        this.Builder = bui;
    }
    
    public HashMap<Player, String> getTabList(){
    	return this.tabList;
    }
    
    public List<Player> getSpec(){
    	return this.Spectator;
    }
    
    public List<Player> getLobbyPlayer(){
    	return this.LobbyPlayer;
    }
    
    public Inventory getEnderchest(int i){
    	if(i == 1){
    		return this.Enderchest1;
    	}
    	
    	if(i == 2){
    		return this.Enderchest2;
    	}
    	
    	if(i == 3){
    		return this.Enderchest3;
    	}
    	
    	if(i == 4){
    		return this.Enderchest4;
    	}
    	
    	if(i == 5){
    		return this.Enderchest5;
    	}
    	
    	if(i == 6){
    		return this.Enderchest6;
    	}
    	
    	if(i == 7){
    		return this.Enderchest7;
    	}
    	
    	if(i == 8){
    		return this.Enderchest8;
    	}
		return null;
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
    
    public void setBedState(int i, Boolean b) {
        if(i == 1){
        	this.bed1 = b;
        }else if(i == 2){
        	this.bed2 = b;
        }else if(i == 3){
        	this.bed3 = b;
        }else if(i == 4){
        	this.bed4 = b;
        }else if(i == 5){
        	this.bed5 = b;
        }else if(i == 6){
        	this.bed6 = b;
        }else if(i == 7){
        	this.bed7 = b;
        }else if(i == 8){
        	this.bed8 = b;
        }else{
        	return;
        }
    }
    
    public Boolean isBed(int i) {
        if(i == 1){
        	return this.bed1;
        }else if(i == 2){
        	return this.bed2;
        }else if(i == 3){
        	return this.bed3;
        }else if(i == 4){
        	return this.bed4;
        }else if(i == 5){
        	return this.bed5;
        }else if(i == 6){
        	return this.bed6;
        }else if(i == 7){
        	return this.bed7;
        }else if(i == 8){
        	return this.bed8;
        }else{
        	return false;
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

    public void createTeam(String s, Location l, String cs, Integer i){
    	if(i == 1){
    		this.team_1_name = s;
    		this.team_1_spawn = l;
    		this.team_1_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 2){
    		this.team_2_name = s;
    		this.team_2_spawn = l;
    		this.team_2_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 3){
    		this.team_3_name = s;
    		this.team_3_spawn = l;
    		this.team_3_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 4){
    		this.team_4_name = s;
    		this.team_4_spawn = l;
    		this.team_4_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 5){
    		this.team_5_name = s;
    		this.team_5_spawn = l;
    		this.team_5_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 6){
    		this.team_6_name = s;
    		this.team_6_spawn = l;
    		this.team_6_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 7){
    		this.team_7_name = s;
    		this.team_7_spawn = l;
    		this.team_7_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}else if(i == 8){
    		this.team_8_name = s;
    		this.team_8_spawn = l;
    		this.team_8_color = ChatColor.translateAlternateColorCodes('&', cs);
    	}
    }

    public int teamGetPlayer(Player p){
    	if(team_1_player.contains(p)){
    		return 1;
    	}
    	
    	if(team_2_player.contains(p)){
    		return 2;
    	}
    	
    	if(team_3_player.contains(p)){
    		return 3;
    	}
    	
    	if(team_4_player.contains(p)){
    		return 4;
    	}
    	
    	if(team_5_player.contains(p)){
    		return 5;
    	}
    	
    	if(team_6_player.contains(p)){
    		return 6;
    	}
    	
    	if(team_7_player.contains(p)){
    		return 7;
    	}
    	
    	if(team_8_player.contains(p)){
    		return 8;
    	}
		return 0;
    }
    
    public boolean emptyteams(){
    	if(team_1_player.size() == 0 && team_2_player.size() == 0 && team_3_player.size() == 0 && team_4_player.size() == 0){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public boolean teamAddPlayer(Player p, int i){
    	if(teamGetPlayer(p) == 0){
    		
    		if(i == 1){
    			team_1_player.add(p);
    			return true;
    		}else if(i == 2){
    			team_2_player.add(p);
    			return true;
    		}else if(i == 3){
    			team_3_player.add(p);
    			return true;
    		}else if(i == 4){
    			team_4_player.add(p);
    			return true;
    		}else if(i == 5){
    			team_5_player.add(p);
    			return true;
    		}else if(i == 6){
    			team_6_player.add(p);
    			return true;
    		}else if(i == 7){
    			team_7_player.add(p);
    			return true;
    		}else if(i == 8){
    			team_8_player.add(p);
    			return true;
    		}
    		
    		return false;
    	}else{
    		return false;
    	}
    }

    public int teamIsBed(Block b){
    	if(team_1_bed.equals(b)){
    		return 1;
    	}
    	
    	if(team_2_bed.equals(b)){
    		return 2;
    	}
    	
    	if(team_3_bed.equals(b)){
    		return 3;
    	}
    	
    	if(team_4_bed.equals(b)){
    		return 4;
    	}
    	
    	if(team_5_bed.equals(b)){
    		return 5;
    	}
    	
    	if(team_6_bed.equals(b)){
    		return 6;
    	}
    	
    	if(team_7_bed.equals(b)){
    		return 7;
    	}
    	
    	if(team_8_bed.equals(b)){
    		return 8;
    	}
    	
    	return 0;
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
						BarAPI.hasBar(p);
						BarAPI.removeBar(p);
						Messages.sendMessage(p, German.arena.get(0) , true);
						Finish(0);
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
					BarAPI.setMessage(p, "§9Runde unentschieden in §e" + a + minutes + ":" + s + seconds);
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
        
        if(teamGetList(1).isEmpty()){
        	setBedState(1, false);
        }else{
        	for(Player p : this.team_1_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(1)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_1_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }
    	
        if(teamGetList(2).isEmpty()){
        	setBedState(2, false);
        }else{
        	for(Player p : this.team_2_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(2)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_2_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }

        if(teamGetList(3).isEmpty()){
        	setBedState(3, false);
        }else{
        	for(Player p : this.team_3_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(3)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_3_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }

        if(teamGetList(4).isEmpty()){
        	setBedState(4, false);
        }else{
        	for(Player p : this.team_4_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(4)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_4_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }
        
        if(teamGetList(5).isEmpty()){
        	setBedState(5, false);
        }else{
        	for(Player p : this.team_5_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(5)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_5_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }
        
        if(teamGetList(6).isEmpty()){
        	setBedState(6, false);
        }else{
        	for(Player p : this.team_6_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(6)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_6_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }
        
        
        if(teamGetList(7).isEmpty()){
        	setBedState(7, false);
        }else{
        	for(Player p : this.team_7_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(7)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_7_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }
        
        if(teamGetList(8).isEmpty()){
        	setBedState(8, false);
        }else{
        	for(Player p : this.team_8_player){
        		p.setFlying(false);
        		p.setFlySpeed(0.1F);
        		p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', getColor(8)) + Utils.trimPlayerName(p.getName(), 14));
        		p.getInventory().clear();
                p.getInventory().setItem(8, is);
        		p.updateInventory();
        		p.teleport(this.team_8_spawn);
        		TagAPI.refreshPlayer(p);
        	}
        }

    	StartTimer();
    }
    
    public void lobbytimer(){
    	ArenaManager.getManager().clearArena(ArenaManager.getManager().getArena(getId()));
    	this.timer_lobby = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
    		int timer = timer_ltime;
			@Override
			public void run() {
				
				timer--;
				int i = 0;
				
				if(team_1_player.size() >= 1){
				i++;	
				}
				
				if(team_2_player.size() >= 1){
					i++;	
				}
				
				if(team_3_player.size() >= 1){
					i++;	
				}
				
				if(team_4_player.size() >= 1){
					i++;	
				}
				
				if(team_5_player.size() >= 1){
					i++;	
				}
				
				if(team_6_player.size() >= 1){
					i++;	
				}
				
				if(team_7_player.size() >= 1){
					i++;	
				}
				
				if(team_8_player.size() >= 1){
					i++;	
				}
				
				
				if(!(i >= 2)){
					CancelLobby();
				}
				
				if(timer == 10){
					String s = Builder;
					s = s.replace("#", ", ");
					Utils.sendRound("Map erbaut von: §6" + s, true, ArenaManager.getManager().getArena(getId()));
				}
				
				for(Player p : getPlayers()){
					
					if(timer == 20 || timer == 10 || timer <= 5 ){
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
					}
					
					
					
					if(!p.getGameMode().equals(GameMode.SURVIVAL)){
						p.setGameMode(GameMode.SURVIVAL);
						TagAPI.refreshPlayer(p);
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
    
    
    public void Finish(int i){
    	if(i != 0){
    		Utils.sendRound2("§9Das Team: " + ChatColor.translateAlternateColorCodes('&', this.getColor(i)) + this.getName(i) + " §9hat die Bedwars Map gewonnen", true, ArenaManager.getManager().getArena(this.id));
    	}else{
    		Utils.sendRound2("§9Das Team: " + ChatColor.translateAlternateColorCodes('&', "Die Runde hat keiner gewonnen"), true, ArenaManager.getManager().getArena(this.id));
    	}
    	
    	if(i != 0){
        	for(Player pl : this.players){
        		pl.teleport(lobby);
        		
        		Main.getPlayerPoints().getAPI().give(pl.getUniqueId(), getMoney());
        		Messages.sendMessage(pl, "§9Dein Team hat die Bedwars Map gewonnen :).", true);
        		Messages.sendMessage(pl, "§9Du hast§6 " + this.money + " Tokens §9erhalten.", true);
        		
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
				}
			}
    		
    	}, 0L, 20L); 
    }
    
    protected void ChancelFinish() {
    	if(this.finishT != null){
			Bukkit.getScheduler().cancelTask(this.finishT);
			this.finishT = null;
    	}
	}
    
    protected void CancelLobby() {
    	if(this.timer_lobby != null){
    			Bukkit.getScheduler().cancelTask(this.timer_lobby);
    			this.timer_lobby = null;
    			this.timer_ltime = 30;
    	}
		
	}

	protected void CancelTimer() {
    	if(this.timer_task != null){
    			Bukkit.getScheduler().cancelTask(this.timer_task);
    			this.timer_task = null;
    			this.timer_time = 3600;
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

	public Location getTeam(int i) {
		if(i == 1){
			return team_1_spawn;
		}else if(i == 2){
			return team_2_spawn;
		}else if(i == 3){
			return team_3_spawn;
		}else if(i == 4){
			return team_4_spawn;
		}else if(i == 5){
			return team_5_spawn;
		}else if(i == 6){
			return team_6_spawn;
		}else if(i == 7){
			return team_7_spawn;
		}else if(i == 8){
			return team_8_spawn;
		}
		return null;
	}
	
	public Integer getTeamSize() {
		int i = 0;
		if(this.team_1_color != null){
			i++;
		}
		
		if(this.team_2_color != null){
			i++;
		}
		
		if(this.team_3_color != null){
			i++;
		}
		
		if(this.team_4_color != null){
			i++;
		}
		
		if(this.team_5_color != null){
			i++;
		}
		
		if(this.team_6_color != null){
			i++;
		}
		
		if(this.team_7_color != null){
			i++;
		}
		
		if(this.team_8_color != null){
			i++;
		}
		
		return i;
	}

	public String getName(int i) {
		if(i == 1){
			return team_1_name;
		}else if(i == 2){
			return team_2_name;
		}else if(i == 3){
			return team_3_name;
		}else if(i == 4){
			return team_4_name;
		}else if(i == 5){
			return team_5_name;
		}else if(i == 6){
			return team_6_name;
		}else if(i == 7){
			return team_7_name;
		}else if(i == 8){
			return team_8_name;
		}
		return null;
	}

	public String returnColor(int i) {
		if(i == 1){
			return team_1_color;
		}else if(i == 2){
			return team_2_color;
		}else if(i == 3){
			return team_3_color;
		}else if(i == 4){
			return team_4_color;
		}else if(i == 5){
			return team_5_color;
		}else if(i == 6){
			return team_6_color;
		}else if(i == 7){
			return team_7_color;
		}else if(i == 8){
			return team_8_color;
		}
		return null;
	}

	public Location getBed(int i) {
		if(i == 1){
			return team_1_bed.getLocation();
		}else if(i == 2){
			return team_2_bed.getLocation();
		}else if(i == 3){
			return team_3_bed.getLocation();
		}else if(i == 4){
			return team_4_bed.getLocation();
		}else if(i == 5){
			return team_5_bed.getLocation();
		}else if(i == 6){
			return team_6_bed.getLocation();
		}else if(i == 7){
			return team_7_bed.getLocation();
		}else if(i == 8){
			return team_8_bed.getLocation();
		}
		return null;
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
	
	public BlockFace getRotation(int i) {
		if(i == 1){
			return team_1_bed_f;
		}else if(i == 2){
			return team_2_bed_f;
		}else if(i == 3){
			return team_3_bed_f;
		}else if(i == 4){
			return team_4_bed_f;
		}else if(i == 5){
			return team_5_bed_f;
		}else if(i == 6){
			return team_6_bed_f;
		}else if(i == 7){
			return team_7_bed_f;
		}else if(i == 8){
			return team_8_bed_f;
		}
		return null;
	}
	
	public Block getBlock(int i) {
		if(i == 1){
			return team_1_bed;
		}else if(i == 2){
			return team_2_bed;
		}else if(i == 3){
			return team_3_bed;
		}else if(i == 4){
			return team_4_bed;
		}else if(i == 5){
			return team_5_bed;
		}else if(i == 6){
			return team_6_bed;
		}else if(i == 7){
			return team_7_bed;
		}else if(i == 8){
			return team_8_bed;
		}
		return null;
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

	public void setBed(int u, Block b) {
		if(u == 1){
			this.team_1_bed = b;
		}else if(u == 2){
			this.team_2_bed = b;
		}else if(u == 3){
			this.team_3_bed = b;
		}else if(u == 4){
			this.team_4_bed = b;
		}else if(u == 5){
			this.team_5_bed = b;
		}else if(u == 6){
			this.team_6_bed = b;
		}else if(u == 7){
			this.team_7_bed = b;
		}else if(u == 8){
			this.team_8_bed = b;
		}else{
			return;
		}
	}

	public void setTeamName(int u, String tName) {
		if(u == 1){
			this.team_1_name = tName;
		}else if(u == 2){
			this.team_2_name = tName;
		}else if(u == 3){
			this.team_3_name = tName;
		}else if(u == 4){
			this.team_4_name = tName;
		}else if(u == 5){
			this.team_5_name = tName;
		}else if(u == 6){
			this.team_6_name = tName;
		}else if(u == 7){
			this.team_7_name = tName;
		}else if(u == 8){
			this.team_8_name = tName;
		}else{
			return;
		}
	}

	public void setColor(int u, String tName) {
		if(u == 1){
			this.team_1_color = tName;
		}else if(u == 2){
			this.team_2_color = tName;
		}else if(u == 3){
			this.team_3_color = tName;
		}else if(u == 4){
			this.team_4_color = tName;
		}else if(u == 5){
			this.team_5_color = tName;
		}else if(u == 6){
			this.team_6_color = tName;
		}else if(u == 7){
			this.team_7_color = tName;
		}else if(u == 8){
			this.team_8_color = tName;
		}else{
			return;
		}
	}

	public void setSpawn(int u, Location tSpawn) {
		if(u == 1){
			this.team_1_spawn = tSpawn;
		}else if(u == 2){
			this.team_2_spawn = tSpawn;
		}else if(u == 3){
			this.team_3_spawn = tSpawn;
		}else if(u == 4){
			this.team_4_spawn = tSpawn;
		}else if(u == 5){
			this.team_5_spawn = tSpawn;
		}else if(u == 6){
			this.team_6_spawn = tSpawn;
		}else if(u == 7){
			this.team_7_spawn = tSpawn;
		}else if(u == 8){
			this.team_8_spawn = tSpawn;
		}else{
			return;
		}
	}

	public List<Block> getBlock() {
		return this.block;
	}

	public void setFace(int i, BlockFace bf) {
		if(i == 1){
			this.team_1_bed_f = bf;
		}else if(i == 2){
			this.team_2_bed_f = bf;
		}else if(i == 3){
			this.team_3_bed_f = bf;
		}else if(i == 4){
			this.team_4_bed_f = bf;
		}else if(i == 5){
			this.team_5_bed_f = bf;
		}else if(i == 6){
			this.team_6_bed_f = bf;
		}else if(i == 7){
			this.team_7_bed_f = bf;
		}else if(i == 8){
			this.team_8_bed_f = bf;
		}else{
			return;
		}
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

	public String getColor(int i) {
		if(i == 1){
			return this.team_1_color;
		}else if(i == 2){
			return this.team_2_color;
		}else if(i == 3){
			return this.team_3_color;
		}else if(i == 4){
			return this.team_4_color;
		}else if(i == 5){
			return this.team_5_color;
		}else if(i == 6){
			return this.team_6_color;
		}else if(i == 7){
			return this.team_7_color;
		}else if(i == 8){
			return this.team_8_color;
		}else{
			return "&f";
		}
	}

	public boolean isStartet() {
		if(this.timer_task != null){
			return true;
		}else{
			return false;
		}
	}

	public Integer teamSize(int i) {
		if(i == 1){
			return this.team_1_player.size();
		}else if(i == 2){
			return this.team_2_player.size();
		}else if(i == 3){
			return this.team_3_player.size();
		}else if(i == 4){
			return this.team_4_player.size();
		}else if(i == 5){
			return this.team_5_player.size();
		}else if(i == 6){
			return this.team_6_player.size();
		}else if(i == 7){
			return this.team_7_player.size();
		}else if(i == 8){
			return this.team_8_player.size();
		}else{
			return null;
		}
	}
	
    public Integer returnTeamFromColor(String team){
    	if(team_1_name.equalsIgnoreCase(team)){
    		return 1;
    	}else if(team_2_name.equalsIgnoreCase(team)){
    		return 2;
    	}else if(team_3_name.equalsIgnoreCase(team)){
    		return 3;
    	}else if(team_4_name.equalsIgnoreCase(team)){
    		return 4;
    	}else if(team_5_name.equalsIgnoreCase(team)){
    		return 5;
    	}else if(team_6_name.equalsIgnoreCase(team)){
    		return 6;
    	}else if(team_7_name.equalsIgnoreCase(team)){
    		return 7;
    	}else if(team_8_name.equalsIgnoreCase(team)){
    		return 8;
    	}else if(team == null){
    		return null;
    	}else{
    		return 0;
    	}
    }
	
	public List<Player> teamGetList(int i) {
		if(i == 1){
			return this.team_1_player;
		}else if(i == 2){
			return this.team_2_player;
		}else if(i == 3){
			return this.team_3_player;
		}else if(i == 4){
			return this.team_4_player;
		}else if(i == 5){
			return this.team_5_player;
		}else if(i == 6){
			return this.team_6_player;
		}else if(i == 7){
			return this.team_7_player;
		}else if(i == 8){
			return this.team_8_player;
		}else{
			return null;
		}
	}

	public void teamRemovePlayer(Player p, Integer i) {
		if(i == 1){
		    team_1_player.remove(p);
		}else if(i == 2){
			team_2_player.remove(p);
		}else if(i == 3){
			team_3_player.remove(p);
		}else if(i == 4){
			team_4_player.remove(p);
		}else if(i == 5){
			team_5_player.remove(p);
		}else if(i == 6){
			team_6_player.remove(p);
		}else if(i == 7){
			team_7_player.remove(p);
		}else if(i == 8){
			team_8_player.remove(p);
		}else{
			return;
		}
	}

	public boolean isLobby() {
		if(this.timer_lobby == null){
			return false;
		}else{
			return true;
		}
	}

	public Boolean getBedState(int i) {
		if(i == 1){
		    return this.bed1;
		}else if(i == 2){
			return this.bed2;
		}else if(i == 3){
			return this.bed3;
		}else if(i == 4){
			return this.bed4;
		}else if(i == 5){
			return this.bed5;
		}else if(i == 6){
			return this.bed6;
		}else if(i == 7){
			return this.bed7;
		}else if(i == 8){
			return this.bed8;
		}else{
			return false;
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

	public void setEnderchest(int i, Inventory is) {
		if(i == 1){
		    this.Enderchest1 = is;
		}else if(i == 2){
			this.Enderchest2 = is;
		}else if(i == 3){
			this.Enderchest3 = is;
		}else if(i == 4){
			this.Enderchest4 = is;
		}else if(i == 5){
			this.Enderchest5 = is;
		}else if(i == 6){
			this.Enderchest6 = is;
		}else if(i == 7){
			this.Enderchest7 = is;
		}else if(i == 8){
			this.Enderchest8 = is;
		}else{
			return;
		}
	}
	
}