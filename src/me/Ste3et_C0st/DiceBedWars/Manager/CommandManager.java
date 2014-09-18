package me.Ste3et_C0st.DiceBedWars.Manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if(command.getName().equalsIgnoreCase("bedwars")){
				if(p.hasPermission("bedwars.admin")){
					if(args.length == 4){
						if(args[0].equalsIgnoreCase("create")){
							if(Utils.isInt(args[3])){
								Integer integer = Integer.parseInt(args[3]);
								if(integer == 2 || integer == 4 || integer == 6 || integer == 8){
									Editor.enter(args[1], p, args[2], integer);
									return true;
								}else{
									p.sendMessage("1");
									p.sendMessage("§9/bedwars create <name> <author> <2|4|6|8>");
									p.sendMessage("§9Der Author kann mehrere Namen enthalten");
									p.sendMessage("§9Diese Müssen mit einem # Getrennt werden");
									p.sendMessage("§9Bsp.: Ste3et_C0st#ManiaCoolT#ChimeiTsume#MineDrageon");
									return true;
								}
								
							}else{
								p.sendMessage("1");
								p.sendMessage("§9/bedwars create <name> <author> <2|4|6|8>");
								p.sendMessage("§9Der Author kann mehrere Namen enthalten");
								p.sendMessage("§9Diese Müssen mit einem # Getrennt werden");
								p.sendMessage("§9Bsp.: Ste3et_C0st#ManiaCoolT#ChimeiTsume#MineDrageon");
								return true;
							}
						}else{
							p.sendMessage("2");
							p.sendMessage("§9/bedwars create <name> <author> <2|4|6|8>");
							p.sendMessage("§9Der Author kann mehrere Namen enthalten");
							p.sendMessage("§9Diese Müssen mit einem # Getrennt werden");
							p.sendMessage("§9Bsp.: Ste3et_C0st#ManiaCoolT#ChimeiTsume#MineDrageon");
							return true;
						}
					}else if(args.length == 2){
						 if(args[0].equalsIgnoreCase("spectate")){
								if(ArenaManager.getManager().ArenaExist(args[1])){
									int id = ArenaManager.getManager().getIDBackfromName(args[1]);
									ArenaManager.getManager().addSpec(ArenaManager.getManager().getArena(id), p);
								}
						 }
					}else{
						p.sendMessage("3");
						p.sendMessage("§9/bedwars create <name> <author> <2|4|6|8>");
						p.sendMessage("§9Der Author kann mehrere Namen enthalten");
						p.sendMessage("§9Diese Müssen mit einem # Getrennt werden");
						p.sendMessage("§9Bsp.: Ste3et_C0st#ManiaCoolT#ChimeiTsume#MineDrageon");
						return true;
					}
				}else{
					return true;
				}
			}
		}
		return false;
	}
}