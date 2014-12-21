package me.Ste3et_C0st.DiceBedWars.Manager;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceBedWars.Main;
import me.Ste3et_C0st.DiceBedWars.pluginHooks.mkremins.fanciful.FancyMessage;
import me.Ste3et_C0st.language.Messages;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandManager implements CommandExecutor{
	List<String> cmd = new ArrayList<String>();
	
	public me.Ste3et_C0st.DiceBedWars.pluginHooks.mkremins.fanciful.FancyMessage transfare(String a, String b) {
		return new FancyMessage(a)
		.tooltip(b);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			
			String s = ChatColor.BLUE + "/bedwars ";
			

			Player p = (Player) sender;
			if(command.getName().equalsIgnoreCase("bedwars")){
				if(p.hasPermission("bedwars.admin")){
					if(args.length == 4){
						if(args[0].equalsIgnoreCase("create")){
							if(Utils.isInt(args[3])){
								Integer integer = Integer.parseInt(args[3]);
								if(integer > 1 && integer < 46){
									Editor.enter(args[1], p, args[2], integer);
									return true;
								}else{
									Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
								    transfare(s + "create <name> <author> <int>", "§9Durch diesen Befehl landest du im Map Editor Modus\n"
								    		  + "§6name = &3Map name\n"
								    		  + "§6author = &3Map Erbauer\n"
								    		  + "§6int = §3Teamanzahl").send(p);
									Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
									return true;
								}
								
							}else{
								Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
								transfare(s + "create <name> <author> <int>", "§9Durch diesen Befehl landest du im Map Editor Modus\n"
							    		  + "§6name = &3Map name\n"
							    		  + "§6author = &3Map Erbauer\n"
							    		  + "§6int = §3Teamanzahl").send(p);
								Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
								return true;
							}
						}else{
							Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
							transfare(s + "create <name> <author> <int>", "§9Durch diesen Befehl landest du im Map Editor Modus\n"
						    		  + "§6name = &3Map name\n"
						    		  + "§6author = &3Map Erbauer\n"
						    		  + "§6int = §3Teamanzahl").send(p);
							Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
							return true;
						}
					}else if(args.length == 1){
						 if(args[0].equalsIgnoreCase("villager")){
							 if(p.hasPermission("bedwars.admin")){
								 p.getInventory().setItem(5, Editor.is(Material.SKULL_ITEM, Main.head + "Set Villager", null, 3, 1));
								 p.updateInventory();
								 return true;
							 }else{
								 p.sendMessage(Main.head + "§cDazu hast du keine Berechtigung");
								 return true;
							 }
						 }
					}else{
						Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
						transfare(s + "create <name> <author> <int>", "§9Durch diesen Befehl landest du im Map Editor Modus\n"
					    		  + "§6name = &3Map name\n"
					    		  + "§6author = &3Map Erbauer\n"
					    		  + "§6int = §3Teamanzahl").send(p);
						Messages.sendMessage(p, "§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=§3-§9=", false);
						return true;
					}
				}else{
					p.sendMessage(Main.head + "§cDazu hast du keine Berechtigung");
					return true;
				}
			}
		}
		return false;
	}
}