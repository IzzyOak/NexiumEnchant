package com.nexiummc.nexiumenchant.handlers.custom;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.util.UnsafeList;

public class CustomEntityVillager extends EntityVillager
	{

		public CustomEntityVillager(World world, int i)
			{
				super(world);

				this.setProfession(i);
				// Removes all pathfinders already registered in the villager
				try
					{
						Field bField = PathfinderGoalSelector.class
								.getDeclaredField("b");
						bField.setAccessible(true);
						Field cField = PathfinderGoalSelector.class
								.getDeclaredField("c");
						cField.setAccessible(true);

						bField.set(goalSelector,
								new UnsafeList<PathfinderGoalSelector>());
						bField.set(targetSelector,
								new UnsafeList<PathfinderGoalSelector>());
						cField.set(goalSelector,
								new UnsafeList<PathfinderGoalSelector>());
						cField.set(targetSelector,
								new UnsafeList<PathfinderGoalSelector>());

					} catch (Exception e)
					{
						e.printStackTrace();
					}
				setUp();
			}

		public void setUp()
			{
				// Make the villager look at players
//				this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this,
//						EntityHuman.class, 8.0F));
			}

		@Override
		protected void aC()
			{
				super.aC();
				// Make his walk speed 0 so he doesn't walk around on his own
				this.getAttributeInstance(GenericAttributes.d).setValue(0.0D);
				// Make his health to max a double can be
				this.getAttributeInstance(GenericAttributes.a).setValue(
						Double.MAX_VALUE);
				// Make his knockback resistance max double can be
				this.getAttributeInstance(GenericAttributes.c).setValue(
						Double.MAX_VALUE);
			}

		@Override
		public void move(double d0, double d1, double d2)
			{
			}
	}