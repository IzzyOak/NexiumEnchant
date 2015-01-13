package com.nexiummc.nexiumenchant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

//import net.minecraft.util.com.google.common.util.concurrent.AbstractScheduledService.Scheduler;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nexiummc.nexiumenchant.command.NexiumEnchantCommand;
import com.nexiummc.nexiumenchant.enchantment.BaseEnchantment;
import com.nexiummc.nexiumenchant.enchantments.Deepwounds;
import com.nexiummc.nexiumenchant.enchantments.Lifesteal;
import com.nexiummc.nexiumenchant.enchantments.Poison;
import com.nexiummc.nexiumenchant.enchantments.Stun;
import com.nexiummc.nexiumenchant.enchantments.Thunderblow;
import com.nexiummc.nexiumenchant.listeners.EnchantmentListener;
import com.nexiummc.nexiumenchant.scheduler.Scheduler;
import com.nexiummc.nexiumenchant.test.ExplosiveProjectile;
import com.nexiummc.nexiumenchant.test.UniversalTest;

/**
 * Main CustomEnchants-API class.
 * @author searchndstroy
 */
public final class NexiumEnchant extends JavaPlugin {
	
	
	/**
	 * This only used in projectiles.
	 */
	public static final String ITEMSTACK_METADATA = "NexiumEnchantItemMeta";
	
	/**
	 * Plugin manager.
	 */
	public static final PluginManager pluginManager = Bukkit.getPluginManager();
	
	/**
	 * Protocol manager.
	 */
//	public static ProtocolManager protocolManager;
	
	/**
	 * Plugin's instance. Will be null until onLoad is invoked.
	 */
	private static NexiumEnchant instance;
	
	/**
	 * Plugin's logger.
	 */
	private static final Logger logger = Logger.getLogger("Minecraft");
	
	/**
	 * Log message. Use {@link String#format(String, Object...)}
	 */
	private static final String logMessage = "[NexiumEnchant] %s";
	
	/**
	 * Where registered enchantments are stored.
	 */
	private static final Map<String, BaseEnchantment> enchantments = new HashMap<String, BaseEnchantment>();
	
	public static final ExplosiveProjectile projectileEnchantment = new ExplosiveProjectile();
	public static final UniversalTest enchantment = new UniversalTest();
	public static final Lifesteal ToolEnchantment = new Lifesteal();
	public static final Stun ToolEnchantment1 = new Stun();
	public static final Poison ToolEnchantment2 = new Poison();
	public static final Thunderblow ToolEnchantment3 = new Thunderblow();
	public static final Deepwounds ToolEnchantment4 = new Deepwounds();
	/**
	 * CustomEnchant-API's FileConfiguration.
	 */
	public FileConfiguration config;
	
	/**
	 * @return CustomEnchants-API's object.
	 */
	public static NexiumEnchant instance() {
		return instance;
	}
	
	/**
	 * @return All registered BaseEnchantment's objects.
	 */
	public static synchronized Map<String, BaseEnchantment> getEnchantments() {
		return Collections.unmodifiableMap(enchantments);
	}
	
	/**
	 * After unregistering your enchantment, please remove any references to the object so it can be GC'd.
	 * @param enchantment Enchantment to unregister.
	 * @see {@link CustomEnchantsAPI#validateEnchantment(enchantment)}
	 */
	public static synchronized void unregisterEnchantment(BaseEnchantment enchantment) {
		validateEnchantment(enchantment);
		enchantments.remove(enchantment);
		Scheduler.enchantChanged();
	}
	
	/**
	 * Validates an enchantment.
	 * @param enchantment Enchantment to validate.
	 * @throws IllegalArgumentException If the enchantment isn't valid.
	 * @throws NullPointerException If the enchantment is null.
	 */
	public static synchronized void validateEnchantment(BaseEnchantment enchantment) throws IllegalArgumentException, NullPointerException {
		Validate.notNull(enchantment, "Enchantment cannot be null!");
		String enchantName = enchantment.name.toLowerCase();
		BaseEnchantment possibleDuplicate = enchantments.get(enchantName);
		if (possibleDuplicate == null) {
			throw new IllegalArgumentException("Enchantment " + enchantName + " is unregistered! It cannot be used until registered again!");
		}
		if ((possibleDuplicate.getClass() != enchantment.getClass()) || (possibleDuplicate != enchantment)) {
			throw new IllegalArgumentException("Enchantment " + enchantName + " has a duplicate enchantment!");
		}
	}
	
	/**
	 * On construction of an BaseEnchantment object this is invoked. Invoking it again will result in an exception being thrown.
	 * @param enchantment Enchantment to register.
	 * @throws RuntimeException If an enchantment with this name already exists.
	 */
	public static synchronized void registerEnchantment(BaseEnchantment enchantment) throws RuntimeException {
		String name = enchantment.name.toLowerCase();
		if (!enchantments.containsKey(name)) {
			enchantments.put(name, enchantment);
			Scheduler.enchantChanged();
		} else {
			throw new RuntimeException("Enchantment name " + name + " cannot be registered twice!");
		}
	}
	
	/**
	 * Logs a severe message.
	 * @param message Message to log.
	 * @see {@link Logger#severe(String)}
	 */
	public static void severe(String message) {
		logger.severe(String.format(logMessage, message));
	}
	
	/**
	 * Logs a warning message.
	 * @param message Message to log.
	 * @see {@link Logger#warning(String)}
	 */
	public static void warning(String message) {
		logger.warning(String.format(logMessage, message));
	}
	
	/**
	 * Logs an informational message.
	 * @param message Message to log.
	 * @see {@link Logger#info(String)}
	 */
	public static void info(String message) {
		logger.info(String.format(logMessage, message));
	}
	
	@Override
	public void onLoad() {
		saveDefaultConfig();
//		protocolManager = ProtocolLibrary.getProtocolManager();
		config = getConfig();
		instance = this;
	}
	
	@Override
	public void onEnable() {
		Scheduler.start();
		getCommand("NexiumEnchant").setExecutor(new NexiumEnchantCommand());
		pluginManager.registerEvents(new EnchantmentListener(), this);
		pluginManager.registerEvents(new Thunderblow(), this);
	}
	
	@Override
	public void onDisable() {
		Scheduler.start();
	}
}