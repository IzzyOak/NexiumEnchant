package com.nexiummc.nexiumenchant.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.nexiummc.nexiumenchant.NexiumEnchant;

public class Tools {
	
	NexiumEnchant main;
	
	public void applyBleed(final LivingEntity bleeder, final int bleedDuration) {
		Player defender = (Player) bleeder;
		defender.sendMessage(ChatColor.RED + "You are Bleeding!");
		defender.setMetadata("ce.bleed", new FixedMetadataValue(main, null));
		new BukkitRunnable() {

			int	seconds = bleedDuration;

			@Override
			public void run() {
				Player defender = (Player) bleeder;
				if(seconds >= 0) {
					if(!defender.isDead() && defender.hasMetadata("ce.bleed")) {
						defender.damage(1 + (((Damageable)defender).getHealth() / 15));
						seconds--;
					} else {
						defender.removeMetadata("ce.bleed", main);
						this.cancel();
					}
				} else {
					defender.removeMetadata("ce.bleed", main);
					defender.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
					this.cancel();
				}
			}
		}.runTaskTimer(main, 0l, 20l);

	}
}
