package com.nexiummc.nexiumenchant.scheduler;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import com.nexiummc.nexiumenchant.NexiumEnchant;

public final class Scheduler implements Runnable {
	
	/**
	 * Whether the scheduler has been started.
	 */
	private static boolean hasStarted = false;
	
	/**
	 * Task id. Used later to cancel the task for stopping.
	 */
	private static int taskID;
	
	/**
	 * Whether enchantments have been changed.
	 */
	private static volatile boolean enchantsChanged = false;
	
	/**
	 * List of enchantment names.
	 */
	private static List<String> enchants;
	
	/**
	 * Scheduler.
	 * @see {@link Bukkit#getScheduler()}
	 * @see {@link BukkitScheduler}
	 */
	private static final BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
	
	/**
	 * Starts the scheduler. This shouldn't be called outside the plugin core.
	 */
	public static synchronized void start() {
		if (!hasStarted) {
			hasStarted = true;
			taskID = bukkitScheduler.scheduleSyncRepeatingTask(NexiumEnchant.instance(), new Scheduler(), 0, 100);
		}
	}
	
	/**
	 * Stops the scheduler. This shouldn't be called outside the plugin core.
	 */
	public static synchronized void stop() {
		if (hasStarted) {
			hasStarted = false;
			bukkitScheduler.cancelTask(taskID);
		}
	}
	
	/**
	 * Restarts the scheduler.
	 */
	public static synchronized void restart() {
		stop();
		start();
	}
	
	/**
	 * @return A list of enchantments used for tab completion in the CustomEnchants-API command.
	 */
	public static List<String> getEnchantments() {
		return enchants;
	}
	
	/**
	 * Called when an enchantment is disabled or registered. Should not be called outside the plugin core.
	 */
	public static void enchantChanged() {
		enchantsChanged = true;
	}
	
	@Override
	public void run() {
		if (enchantsChanged) {
			enchants = Collections.unmodifiableList(new ArrayList<String>(NexiumEnchant.getEnchantments().keySet()));
		}
	}
}