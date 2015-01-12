package com.nexiummc.nexiumenchant.handlers;


/**
 * 
 * @author searchndstroy
 * @see {@link ParticleType}
 */
public final class ParticleHandler {
	
	/**
	 * 
	 * @author searchndstroy
	 */
	public enum ParticleType {
		
		/**
		 * Angry-Villager particle.
		 */
		ANGRY_VILLAGER("Angry Villager"),
		
		/**
		 * Bubble particle.
		 */
		BUBBLE("Bubble");
		
		/**
		 * A user-friendly name.
		 */
		public final String friendlyName;
		
		/**
		 * Whether this particle can be used in the current version that the server is running.
		 */
		public final boolean isInCurrentVersion;
		
		/**
		 * Particle exists since this version.
		 */
//		public final MinecraftVersion sinceVersion;
		
		/**
		 * 
		 * @param friendlyName A name that all users can understand.
		 * @return
		 */
		public static ParticleType byUserFriendlyName(String friendlyName) {
			for (ParticleType type : values()) {
				if (type.friendlyName.equalsIgnoreCase(friendlyName)) {
					return type;
				}
			}
			return null;
		}
		
		/**
		 * @param friendlyName A name that all users can understand. (Hopefully...)
		 */
		ParticleType(String friendlyName, String versionFrom) {
			this.friendlyName = friendlyName;
			isInCurrentVersion = false;
//			sinceVersion = new MinecraftVersion(versionFrom);
		}
		
		@Override
		public String toString() {
			return friendlyName;
		}
	}
	
	private ParticleHandler() {
		
	}
}