package me.Ste3et_C0st.DiceBedWars;

import me.Ste3et_C0st.DiceBedWars.Listener.AchievementListener;
import me.Ste3et_C0st.DiceBedWars.Listener.GameListener;
import me.Ste3et_C0st.DiceBedWars.Listener.LobbyListener;
import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import me.Ste3et_C0st.DiceBedWars.Manager.CommandManager;
import me.Ste3et_C0st.DiceBedWars.Manager.GhostFactory;
import me.Ste3et_C0st.DiceBedWars.Manager.Stats;
import net.milkbowl.vault.permission.Permission;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static String head = "§7[§9DiceBW§7]§9 ";
	public static boolean debug = false;
	private static Main instance;
	public static GhostFactory ghostFactory;
	private static PlayerPoints playerPoints;
	public static Permission permission = null;
	
	@Override
	public void onEnable(){
		instance = this;
		getCommand("bedwars").setExecutor(new CommandManager());
		getCommand("stats").setExecutor(new Stats());
		Bukkit.getServer().getPluginManager().registerEvents(new LobbyListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GameListener(), this);
		load.loadMap();
		hookPlayerPoints();
		ghostFactory = new GhostFactory(this);
		RegisteredServiceProvider<Permission>permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		permission = permissionProvider.getProvider();
		AchievementListener.loadAchievements();
	}
	
	public static PlayerPoints getPlayerPoints() {
	    return playerPoints;
	}
	
	private boolean hookPlayerPoints() {
	    final Plugin plugin = this.getServer().getPluginManager().getPlugin("PlayerPoints");
	    playerPoints = PlayerPoints.class.cast(plugin);
	    return playerPoints != null; 
	}
	
	@Override
	public void onDisable(){
		for(Arena a : ArenaManager.getManager().getArenaList()){
			for(Team t : a.getTeams()){
				t.setBed();
			}
			
			if(a.isStartet() || a.isLobby() || a.getPlayers().size() > 0){
				ArenaManager.getManager().Finish(a);
				ArenaManager.getManager().clearArena(a);
			}
		}
		instance = null;
	}
	
	public static void debug(String s){
		if(debug  == false){
			return;
		}
			System.out.print(s);
	}

	
	public static Plugin getInstance() {
		return instance;
	}
}