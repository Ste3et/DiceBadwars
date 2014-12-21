package me.Ste3et_C0st.DiceBedWars.Manager;

import me.Ste3et_C0st.DiceBedWars.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,String[] arg3) {
		if(arg1.getName().equalsIgnoreCase("stats")){
			if(arg0 instanceof Player){
				Player p = (Player) arg0;
				p.sendMessage("§cDicecraft-Arcade");
				p.sendMessage("§7=§8-§7=§8-§7=§8-§7=§8-§7=§8-§7=§8-§7=-§7=§8-§7=§8-§7=§8-§7=");
				p.sendMessage("§7Online: §b" + Bukkit.getOnlinePlayers().length + "§7/§b" + Bukkit.getMaxPlayers());
				p.sendMessage("§7Tokens: §b" + Main.getPlayerPoints().getAPI().look(p.getUniqueId()));
				p.sendMessage("§7=§8-§7=§8-§7=§8-§7=§8-§7=§8-§7=§8-§7=-§7=§8-§7=§8-§7=§8-§7=");
				return true;
			}
		}
		return false;
	}

}
