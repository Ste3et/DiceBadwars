package me.Ste3et_C0st.GUI.Shop;

import me.Ste3et_C0st.DiceBedWars.Manager.Editor;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.Merchant;
import me.Ste3et_C0st.GUI.VillagerTradeAPI.MerchantOffer;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Swords{
	public static void openInv(Player p) {
		Merchant inv = new Merchant();
		inv.setTitle("§cSchwerter");
		
		ItemStack is = new ItemStack(Material.STICK);
		ItemMeta im = is.getItemMeta();
		im.addEnchant(Enchantment.KNOCKBACK, 2, true);
		im.setDisplayName("Rückstoßknüpel");
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.CLAY_BRICK, "§cBronze", null, 0, 2), is));
		
		is = new ItemStack(Material.GOLD_SWORD);
		im = is.getItemMeta();
		im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		im.addEnchant(Enchantment.DURABILITY, 1, true);
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 3), is));
		
		is = new ItemStack(Material.GOLD_SWORD);
		im = is.getItemMeta();
		im.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		im.addEnchant(Enchantment.DURABILITY, 1, true);
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.IRON_INGOT, "§bEisen", null, 0, 4), is));
		
		is = new ItemStack(Material.IRON_SWORD);
		im = is.getItemMeta();
		im.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		im.addEnchant(Enchantment.DURABILITY, 1, true);
		im.addEnchant(Enchantment.KNOCKBACK, 1, true);
		is.setItemMeta(im);
		
		inv.addOffer(new MerchantOffer(Editor.is(Material.GOLD_INGOT, "§6Gold", null, 0, 7), is));
		inv.openTrading(p);
	}
}