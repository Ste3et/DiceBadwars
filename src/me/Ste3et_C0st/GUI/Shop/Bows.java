package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import VilligerTradesAPI.Merchant;
import VilligerTradesAPI.MerchantOffer;

public class Bows{
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§aBögen");
		
		ItemStack is = new ItemStack(Material.BOW);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Bogen LVL I");
		im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 4), is));
		
		is = new ItemStack(Material.BOW);
		im = is.getItemMeta();
		im.setDisplayName("Bogen LVL II");
		im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 7), is));
		
		is = new ItemStack(Material.BOW);
		im = is.getItemMeta();
		im.setDisplayName("Bogen LVL III");
		im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		im.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 9), is));
		
		is = new ItemStack(Material.ARROW);
		im = is.getItemMeta();
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 1), is));
		
		inv.openTrading(p);
	}
}