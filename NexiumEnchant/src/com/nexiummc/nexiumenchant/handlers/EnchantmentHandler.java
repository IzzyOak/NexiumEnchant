package com.nexiummc.nexiumenchant.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;

/**
 * Methods to help with enchantments.
 * @author searchndstroy
 */
public final class EnchantmentHandler {
	
	/**
	 * @param enchantName Enchantment name.
	 * @return An enchantment with the specified name. If there is not an enchantment, null will be returned.
	 */
	public static BaseEnchantment getEnchantment(String enchantName) {
		return NexiumEnchant.getEnchantments().get(enchantName.toLowerCase());
	}
	
	/**
	 * @param itemStack Item to retrieve enchantments for.
	 * @return All enchantments on an item.
	 */
	public static Map<BaseEnchantment, Short> getEnchantments(ItemStack itemStack) {
		return getEnchantments(itemStack, new Class[0]);
	}
	
	/**
	 * @param itemStack Item to retrieve enchantments for.
	 * @param enchantTypes Only return enchantments of these types. (Example, FoodEnchantment.)
	 * @return Enchantments on this item, except with the filter.
	 */
	@SafeVarargs
	public static Map<BaseEnchantment, Short> getEnchantments(ItemStack itemStack, Class<?>... enchantTypes) {
		Map<BaseEnchantment, Short> toReturn = new HashMap<BaseEnchantment, Short>();
		if (itemStack.hasItemMeta()) {
			ItemMeta itemMeta = itemStack.getItemMeta();
			if (!itemMeta.hasLore()) {
				return toReturn;
			}
			for (String lore : itemMeta.getLore()) {
				try {
					int lastSpace = lore.lastIndexOf(' ');
					short level = RomanNumeralHandler.romanToInt(lore.substring(lastSpace + 1, lore.length()));
					String name = ChatColor.stripColor(lore.substring(0, lastSpace));
					BaseEnchantment enchantment = getEnchantment(name);
					try {
						NexiumEnchant.validateEnchantment(enchantment);
					} catch (NullPointerException ex) {}
					if ((enchantment != null) && (level > 0)) {
						if ((enchantTypes != null) && (enchantTypes.length != 0) && !isAllAccessibleFrom(enchantment.getClass(), enchantTypes)) {
							continue;
						}
						toReturn.put(enchantment, level);
					}
				} catch (IllegalArgumentException ex) {}
			}
		}
		return toReturn;
	}
	
	/**
	 * Applies a single enchantment on an item.
	 * @param itemStack Item to apply enchantment on.
	 * @param enchantment Enchantment to apply to the item.
	 * @param tier Tierlevel of the enchantment.
	 * @return True if enchanting was a success, otherwise false.
	 */
	public static boolean applyEnchantment(ItemStack itemStack, BaseEnchantment enchantment, short tier) {
		NexiumEnchant.validateEnchantment(enchantment);
		if (tier > 0) {
			if (!hasEnchantment(itemStack, enchantment)) {
				ItemMeta itemMeta = itemStack.getItemMeta();
				List<String> lore = itemMeta.getLore();
				if (lore == null) {
					lore = new ArrayList<String>();
				}
				StringBuilder loreString = new StringBuilder();
				ChatColor[] colours = enchantment.getEnchantmentLorePrefix();
				if (colours != null) {
					for (ChatColor colour : colours) {
						loreString.append(colour);
					}
				}
				lore.add(loreString.append(enchantment.name).append(" ").append(RomanNumeralHandler.intToRoman(tier)).toString());
				itemMeta.setLore(lore);
				itemStack.setItemMeta(itemMeta);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Applies multiple enchantments on an item.
	 * @param itemStack Item to apply enchantments on.
	 * @param enchantments Enchantments with their tier inside a map.
	 * @return The enchantments that could not be applied.
	 */
	public static Map<BaseEnchantment, Short> applyEnchantments(ItemStack itemStack, Map<BaseEnchantment, Short> enchantments) {
		Map<BaseEnchantment, Short> toReturn = new HashMap<BaseEnchantment, Short>(enchantments);
		for (BaseEnchantment enchantment : enchantments.keySet()) {
			if(!applyEnchantment(itemStack, enchantment, enchantments.get(enchantment))) {
				toReturn.remove(enchantment);
			}
		}
		return toReturn;
	}
	
	/**
	 * Tests an item for an enchantment.
	 * @param itemStack Item to test for.
	 * @param enchantment Enchantment to test for.
	 * @return True if the item has this enchantment, else, false.
	 */
	public static boolean hasEnchantment(ItemStack itemStack, BaseEnchantment enchantment) {
		return hasEnchantment(itemStack, enchantment, (short) 0);
	}
	
	/**
	 * @param itemStack Item to test.
	 * @param enchantment Enchantment to test for.
	 * @param tier Tests if the enchantment has this tier.
	 * @return True if the item has this enchantment and the tier matches the one on the item, else, false.
	 */
	public static boolean hasEnchantment(ItemStack itemStack, BaseEnchantment enchantment, short tier) {
		NexiumEnchant.validateEnchantment(enchantment);
		Map<BaseEnchantment, Short> enchantments = getEnchantments(itemStack);
		if (enchantments.containsKey(enchantment)) {
			if (tier > 0) {
				if (enchantments.get(enchantment) != tier) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Remove an enchantment from an item.
	 * @param itemStack Item to remove enchantment from.
	 * @param enchantment Enchantment to remove.
	 */
	public static void removeEnchantment(ItemStack itemStack, BaseEnchantment enchantment) {
		NexiumEnchant.validateEnchantment(enchantment);
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta.hasLore()) {
			List<String> loreList = itemMeta.getLore();
			int index = 0;
			for (String lore : loreList) {
				try {
					String name = lore.substring(0, lore.lastIndexOf(' ') - 1);
					BaseEnchantment possibleEnchantment = getEnchantment(name);
					if ((possibleEnchantment == enchantment)) {
						loreList.remove(index);
					}
				} catch (Exception ex) {}
				index++;
			}
		}
	}
	
	/**
	 * Remove Enchantments from an item.
	 * @param itemstack Item to remove enchantments from.
	 * @param enchantments Enchantments to remove. Use null to remove all.
	 */
	public static void removeEnchantments(ItemStack itemStack, BaseEnchantment... enchantments) {
		if (enchantments == null) {
			for (BaseEnchantment enchantment : NexiumEnchant.getEnchantments().values()) {
				removeEnchantment(itemStack, enchantment);
			}
		} else {
			for (BaseEnchantment enchantment : enchantments) {
				removeEnchantment(itemStack, enchantment);
			}
		}
	}
	
	/**
	 * @param toTest Class to test.
	 * @param types Class types to test with.
	 * @return True if the class toTest was a subclass to all of the classes in types.
	 */
	@SafeVarargs
	private static boolean isAllAccessibleFrom(Class<? extends BaseEnchantment> toTest, Class<?>... types) {
		for (Class<?> clazz : types) {
			if (!clazz.isAssignableFrom(toTest)) {
				return false;
			}
		}
		return true;
	}
	
	private EnchantmentHandler() {
		
	}
}