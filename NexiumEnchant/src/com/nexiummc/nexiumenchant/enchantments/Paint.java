package com.nexiummc.nexiumenchant.enchantments;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import com.nexiummc.nexiumenchant.enchantment.UniversalEnchantment;

public class Paint extends UniversalEnchantment {
	
//	private static final ItemStack snowBallItem = new ItemStack(Material.SNOW_BALL);
	
//	static {
//		EnchantmentHandler.applyEnchantment(snowBallItem, NexiumEnchant.projectileEnchantment, (short) 1);
//	}
	
	public Paint() {
		super("Paint", null, (short) 1,(short) 5);
	}
	
	
	@Override
	public void onShotSuccess(ItemStack bow, int ticksTravelled, short enchantLevel, LivingEntity attacker, LivingEntity defender,
			EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player){
			Player hit = (Player) event.getEntity();
			
		}
		
	}
/*	public WorldEditPlugin getWorldEdit() {
		Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		if (p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
		else return null;
	}*/
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onShotHitObject(ItemStack bow, int ticksTravelled, short enchantLevel, LivingEntity shooter,
			ProjectileHitEvent event) {
			if(event.getEntity() instanceof Snowball ){
				
		
		Snowball arrow = (Snowball)event.getEntity();
		World world = arrow.getWorld();
		BlockIterator iterator = new BlockIterator(world, arrow.getLocation().toVector(), arrow.getVelocity().normalize(), 0, 4);
		
		Block hitBlock = null;
		 
	    while(iterator.hasNext()) {
	        hitBlock = iterator.next();
	        if(hitBlock.getTypeId()!=0) //Check all non-solid blockid's here.
	            break;
	    }
	 
	    if(hitBlock.getTypeId()==159){
	    	hitBlock.setData( (byte)6);	        
	}
		
	}else if(!(event.getEntity() instanceof Snowball)){
		return true;
	}
		
		
		
		
		
		
		
		/*	Location location = event.getEntity().getLocation();
		Random newloc = new Random();
		Location max = location.add(newloc.nextInt(1), newloc.nextInt(1), newloc.nextInt(1));
		Location min = location.subtract(newloc.nextInt(1), newloc.nextInt(1), newloc.nextInt(1));
		Material replace = Material.getMaterial(159);
		Material with = Material.getMaterial(159);
		 for (int x = min.getBlockX(); x <= max.getBlockX();) {
		        for (int y = min.getBlockY(); y <= max.getBlockY();) {
		            for (int z = min.getBlockZ(); z <= max.getBlockZ();) {
		                Block blk = min.getWorld().getBlockAt(new Location(min.getWorld(), x, y, z));
		                if (blk.getType() == replace) {
		                    blk.setType(with);
		                    blk.setData( (byte)6 );
		               }
		            }
		        }
		    }
		return true;
			Location location = event.getEntity().getLocation();
		location.getBlock().setType(Material.ANVIL);
		ThrownPotion snowball;
		Random random = new Random();
		location.setY(location.getY() + 4);
		location.getWorld().playSound(location, Sound.AMBIENCE_THUNDER, 100, 0.5f);
		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 16428);
		for (int i = 0; i < 9; i++) {
			snowball = location.getWorld().spawn(location, ThrownPotion.class);
			snowball.setItem(itemStack);
			snowball.setFireTicks(10000);
			snowball.setMetadata(NexiumEnchant.ITEMSTACK_METADATA, new FixedMetadataValue(NexiumEnchant.instance(), snowBallItem));
			Vector velocity = new Vector(randomNegative(random.nextInt(10), random), 10, randomNegative(random.nextInt(10), random)).normalize();
			snowball.setVelocity(velocity);
			
		}
		return true;
		*/
		return true;
	}
	
	
	@Override
	public void onBreakBlock(ItemStack tool, Block block, short enchantLevel,
			Player player, boolean usingCorrectToolType, BlockBreakEvent event) {
		
	}
	
	@Override
	public void onInteract(ItemStack tool, Block block, short enchantLevel, Player player,
			boolean usingCorrectToolType, PlayerInteractEvent event) {
	}
	
	@Override
	public void onConsume(Player player, ItemStack itemStack, short enchantLevel, PlayerItemConsumeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAttackEntity(ItemStack tool, LivingEntity attacker,
			LivingEntity defender, short enchantLevel, boolean usingCorrectToolType,
			EntityDamageByEntityEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ChatColor[] getEnchantmentLorePrefix() {
		// TODO Auto-generated method stub
		return null;
	}
	
/*	@Override
	public com.nexiummc.nexiumenchant.particle.Particle applyParticles(
			LivingEntity attacker, LivingEntity defender, short enchantLevel,
			Event event, EnchantType type) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}