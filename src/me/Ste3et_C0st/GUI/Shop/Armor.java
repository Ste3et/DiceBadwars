package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Arena;
import me.Ste3et_C0st.DiceBedWars.Manager.ArenaManager;
import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import VilligerTradesAPI.Merchant;
import VilligerTradesAPI.MerchantOffer;

public class Armor{
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§dRüstung");
		
		Arena a = ArenaManager.getManager().returnArena(p);
		int i = a.teamGetPlayer(p);
        Color c1 = null;
        
        if(a.returnColor(i).equalsIgnoreCase("&a")){
        	c1 = Color.fromRGB(16, 255, 8);
        }else if(a.returnColor(i).equalsIgnoreCase("&b")){
        	c1 = Color.fromRGB(8, 255, 255);
        }else if(a.returnColor(i).equalsIgnoreCase("&c")){
        	c1 = Color.fromRGB(255, 8, 8);
        }else if(a.returnColor(i).equalsIgnoreCase("&d")){
        	c1 = Color.fromRGB(255, 8, 251);
        }else if(a.returnColor(i).equalsIgnoreCase("&e")){
        	c1 = Color.fromRGB(247, 255, 8);
        }else if(a.returnColor(i).equalsIgnoreCase("&f")){
        	c1 = Color.fromRGB(255, 255, 255);
        }else if(a.returnColor(i).equalsIgnoreCase("&0")){
        	c1 = Color.fromRGB(000, 000, 000);
        }else if(a.returnColor(i).equalsIgnoreCase("&1")){
        	c1 = Color.fromRGB(0, 70, 145);
        }else if(a.returnColor(i).equalsIgnoreCase("&2")){
        	c1 = Color.fromRGB(46, 145, 0);
        }else if(a.returnColor(i).equalsIgnoreCase("&3")){
        	c1 = Color.fromRGB(0, 145, 128);
        }else if(a.returnColor(i).equalsIgnoreCase("&4")){
           c1 = Color.fromRGB(209, 48, 48);
        }else if(a.returnColor(i).equalsIgnoreCase("&5")){
        	c1 = Color.fromRGB(104, 0, 145);
        }else if(a.returnColor(i).equalsIgnoreCase("&6")){
        	c1 = Color.fromRGB(209, 192, 0);
        }else if(a.returnColor(i).equalsIgnoreCase("&7")){
        	c1 = Color.fromRGB(179, 179, 179);
        }else if(a.returnColor(i).equalsIgnoreCase("&8")){
        	c1 = Color.fromRGB(82, 82, 82);
        }else if(a.returnColor(i).equalsIgnoreCase("&9")){
        	c1 = Color.fromRGB(85, 3, 166);
        }
		
		ItemStack is = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta lam = (LeatherArmorMeta) is.getItemMeta();
		
		lam.setColor(c1);
		lam.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lam.addEnchant(Enchantment.DURABILITY, 1, true);
		is.setItemMeta(lam);
		
		ItemStack ia = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta lia = (LeatherArmorMeta) ia.getItemMeta();
		lia.setColor(c1);
		lia.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lia.addEnchant(Enchantment.DURABILITY, 1, true);
		ia.setItemMeta(lia);
		
		ItemStack im = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta lim = (LeatherArmorMeta) im.getItemMeta();
		lim.setColor(c1);
		lim.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		lim.addEnchant(Enchantment.DURABILITY, 1, true);
		im.setItemMeta(lim);
		
		ItemStack chest1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta ichest1 = chest1.getItemMeta();
		ichest1.setDisplayName("Kettenhemd I");
		ichest1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		ichest1.addEnchant(Enchantment.DURABILITY, 1, true);
		chest1.setItemMeta(ichest1);
		
		ItemStack chest2 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta ichest2 = chest2.getItemMeta();
		ichest2.setDisplayName("Kettenhemd II");
		ichest2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		ichest2.addEnchant(Enchantment.DURABILITY, 1, true);
		chest2.setItemMeta(ichest2);
		
		ItemStack chest3 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta ichest3 = chest3.getItemMeta();
		ichest3.setDisplayName("Kettenhemd III");
		ichest3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		ichest3.addEnchant(Enchantment.DURABILITY, 1, true);
		chest3.setItemMeta(ichest3);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 4), is));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 4), ia));
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 4), im));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 1), chest1));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 3), chest2));
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 7), chest3));
		inv.openTrading(p);
	}
}