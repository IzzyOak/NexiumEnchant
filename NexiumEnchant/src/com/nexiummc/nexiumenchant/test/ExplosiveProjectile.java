package com.nexiummc.nexiumenchant.test;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import com.nexiummc.nexiumenchant.NexiumEnchant;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantment.type.ProjectileEnchantment;

public class ExplosiveProjectile extends BaseEnchantment implements ProjectileEnchantment {
	
	public ExplosiveProjectile() {
		super("explosive projectile", null, (short) 0, (short) 5);
	}
	
	@Override
	public void onShotSuccess(ItemStack itemStack, int ticksTravelled,
			short enchantLevel, LivingEntity attacker, LivingEntity defender,
			EntityDamageByEntityEvent event) {
	
	}
	
	@Override
	public boolean onShotHitObject(ItemStack itemStack, int ticksTravelled,
			short enchantLevel, LivingEntity shooter, ProjectileHitEvent event) {
		Location location = event.getEntity().getLocation();
		location.getWorld().createExplosion(location, 5);
		NexiumEnchant.enchantment.onShotHitObject(itemStack, ticksTravelled, enchantLevel, shooter, event);
		return false;
	}
	
	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		return new ChatColor[] {ChatColor.RED};
	}
	
}