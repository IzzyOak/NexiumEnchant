package com.nexiummc.nexiumenchant.particle;

import com.nexiummc.nexiumenchant.NexiumEnchant;


/**
 * This class represents a list of particles and their locations. Apply the particles using the method 'applyParticles'.
 * @author searchndstroy
 */
public final class Particle {
	
	/**
	 * World particle type.
	 */
	private static final PacketType particle = PacketType.Play.Server.WORLD_PARTICLES;
	
	public Particle() {
		
	}
	
	/**
	 * Spawns particles.
	 */
	public void applyParticles() {
		NexiumEnchant.protocolManager.broadcastServerPacket(new PacketContainer(particle));
	}
	
}