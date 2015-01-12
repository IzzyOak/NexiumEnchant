package com.nexiummc.nexiumenchant.enchantment.type;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;


/**
 * Defines whether this enchantment is a food enchantment or not.
 * @author searchndstroy
 */
public interface FoodEnchantment {
	
	/**
	 * Called when a player consumes an item with this enchantment on it.
	 * @param player Player involved in the consumption of the item.
	 * @param itemStack Item that was consumed.
	 * @param enchantLevel Enchantment's level.
	 * @param event Item consume event. Received in MONITOR priority and ignoreCancelled = true.
	 */
	void onConsume(Player player, ItemStack itemStack, short enchantLevel, PlayerItemConsumeEvent event);
	
}