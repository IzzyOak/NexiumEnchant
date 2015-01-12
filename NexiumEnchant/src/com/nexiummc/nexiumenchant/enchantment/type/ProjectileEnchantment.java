package com.nexiummc.nexiumenchant.enchantment.type;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author searchndstroy
 * @see {@link ProjectileEnchantment#onShotSuccess(ItemStack, int, short, LivingEntity, LivingEntity, EntityDamageByEntityEvent)}
 * @see {@link ProjectileEnchantment#onShotHitObject(ItemStack, int, short, LivingEntity, ProjectileHitEvent)}
 */
public interface ProjectileEnchantment {
	
	/**
	 * 
	 * @param itemStack Projectile item. This may be a bow.
	 * @param ticksTravelled How long the projectile travelled before hitting an entity.
	 * @param enchantLevel The enchantment level.
	 * @param attacker The entity who launched the projectile.
	 * @param defender The entity who was hit by the projectile.
	 * @param event Damage event. Priority is MONITOR and ignoreCancelled = true.
	 */
	void onShotSuccess(ItemStack itemStack, int ticksTravelled, short enchantLevel, LivingEntity attacker, LivingEntity defender, EntityDamageByEntityEvent event);
	
	/**
	 * Called when a projectile with this enchantment hits an object. This object can be anything from a block to an entity. Do not modify the hit event.
	 * @param itemStack Projectile item. This may be a bow.
	 * @param ticksTravelled How long the projectile travelled before hitting an entity.
	 * @param enchantLevel The enchantment level.
	 * @param shooter The entity who launched the projectile.
	 * @param event Hit event. Priority is MONITOR and ignoreCancelled = true.
	 * @return Whether to remove the projectile metadata. (Example, after it hits another object, this method would no longer be called, even if it hit another object.)
	 */
	boolean onShotHitObject(ItemStack itemStack, int ticksTravelled, short enchantLevel, LivingEntity shooter, ProjectileHitEvent event);
}