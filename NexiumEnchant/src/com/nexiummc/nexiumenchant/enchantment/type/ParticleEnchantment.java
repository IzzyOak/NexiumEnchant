package com.nexiummc.nexiumenchant.enchantment.type;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import com.nexiummc.nexiumenchant.particle.Particle;

/**
 * For enchantments with particles.
 * @author searchndstroy
 * @see {@link ParticleEnchantment#applyOnEntityUsing()}
 * @see {@link ParticleEnchantment#applyParticles(LivingEntity, short, Event, EnchantType)}
 * @see {@link Particle}
 * @see {@link ParticleType}
 */
public interface ParticleEnchantment {
	
	/**
	 * Used for when a particle effect can be applied.
	 * @param attacker The entity using the enchantment.
	 * @param defender May be null. The defender is the entity that was targeted in this event. Received with priority MONITOR and ignoreCancelled = true.
	 * @param enchantLevel The enchantment level.
	 * @param event The event involved in this event. Cast accordingly with the EventType.
	 * @param type What type of enchantment this was applied for. (Example, {@link EnchantType#PROJECTILE}.)
	 * @return The particle object that will apply particles.
	 * @see {@link EnchantType}
	 */
	Particle applyParticles(LivingEntity attacker, LivingEntity defender, short enchantLevel, Event event, EnchantType type);
	
}