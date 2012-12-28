package me.blha303.Fireworks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class b3Fireworks extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		int i = 1;
		if (args.length < 3 || args.length > 4) {
			return false;
		}
		try {
			try {
				i = Integer.parseInt(args[4]);
			} catch (ArrayIndexOutOfBoundsException e1) {
				i = Integer.parseInt(args[3]);
			}
		} catch (Exception e) {
			i = 1;
		}

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (command.getLabel().equalsIgnoreCase("givefw")) {
				if (!p.hasPermission("command.givefw")) {
					p.sendMessage("You can't use this command.");
					return true;
				}
				FireworkEffect fe = Build(args[0], args[1], args[2]);
				if (p.getItemInHand().getTypeId() == 401) {
					ItemStack f = p.getItemInHand();
					FireworkMeta fm = (FireworkMeta) f.getItemMeta();
					fm.addEffect(fe);
					f.setItemMeta(fm);
					p.sendMessage("Firework updated!");
					return true;
				}
				ItemStack f = new ItemStack(401, i);
				FireworkMeta fm = (FireworkMeta) f.getItemMeta();
				fm.setPower(2);
				fm.addEffect(fe);
				f.setItemMeta(fm);
				p.getInventory().addItem(f);
				if (i > 1) {
					p.sendMessage("Here's your fireworks!");
				} else {
					p.sendMessage("Here's your firework!");
				}
				return true;
			}
			if (command.getLabel().equalsIgnoreCase("givefwplayer")) {
				if (!p.hasPermission("command.givefwplayer")) {
					p.sendMessage("You can't use this command.");
					return true;
				}
				FireworkEffect fe = Build(args[1], args[2], args[3]);
				Player dest = getServer().getPlayer(args[0]);
				if (dest != null) {
					if (dest.getItemInHand().getTypeId() == 401) {
						ItemStack f = dest.getItemInHand();
						FireworkMeta fm = (FireworkMeta) f.getItemMeta();
						fm.addEffect(fe);
						f.setItemMeta(fm);
						p.sendMessage("Firework given to player!");
						return true;
					}
				} else {
					p.sendMessage(args[0]
							+ " isn't a valid (online) player name");
					return true;
				}
			}
			if (command.getLabel().equalsIgnoreCase("launchfw")) {
				if (!p.hasPermission("command.launchfw")) {
					p.sendMessage("You can't use this command.");
					return true;
				}
				FireworkEffect fe = Build(args[0], args[1], args[2]);
				Firework f = (Firework) p.getWorld().spawnEntity(
						p.getLocation(), EntityType.FIREWORK);
				FireworkMeta fm = (FireworkMeta) f.getFireworkMeta();
				fm.setPower(2);
				fm.addEffect(fe);
				f.setFireworkMeta(fm);
				p.sendMessage("Firework launched!");
				return true;
			}
		} else {
			System.out.println("[GiveFireworks] Must be used by a player.");
			return true;
		}
		return false;
	}

	// http://stackoverflow.com/a/2275030
	public boolean contains(String haystack, String needle) {
		haystack = haystack == null ? "" : haystack;
		needle = needle == null ? "" : needle;
		return haystack.toLowerCase().contains(needle.toLowerCase());
	}

	/**
	 * Takes three arguments: <br>
	 * Color (can be multiple words concatenated. no spaces) <br>
	 * Type (can be one of ball, large, burst, creeper, star) <br>
	 * Special (can be trail or flicker, or both words. no spaces)<br>
	 * More info at <a href="http://dev.bukkit.org/server-mods/b3Fireworks">the
	 * project page</a>
	 */
	public FireworkEffect Build(String color, String type, String special) {
		int z = 0;
		List<Color> c = new ArrayList<Color>();
		if (color.contains("aqua")) {
			z += 1;
			c.add(Color.AQUA);
		}
		if (color.contains("black")) {
			z += 1;
			c.add(Color.BLACK);
		}
		if (color.contains("blue")) {
			z += 1;
			c.add(Color.BLUE);
		}
		if (color.contains("fuchsia")) {
			z += 1;
			c.add(Color.FUCHSIA);
		}
		if (color.contains("gray")) {
			z += 1;
			c.add(Color.GRAY);
		}
		if (color.contains("green")) {
			z += 1;
			c.add(Color.GREEN);
		}
		if (color.contains("lime")) {
			z += 1;
			c.add(Color.LIME);
		}
		if (color.contains("maroon")) {
			z += 1;
			c.add(Color.MAROON);
		}
		if (color.contains("navy")) {
			z += 1;
			c.add(Color.NAVY);
		}
		if (color.contains("olive")) {
			z += 1;
			c.add(Color.OLIVE);
		}
		if (color.contains("orange")) {
			z += 1;
			c.add(Color.ORANGE);
		}
		if (color.contains("purple")) {
			z += 1;
			c.add(Color.PURPLE);
		}
		if (color.contains("red")) {
			z += 1;
			c.add(Color.RED);
		}
		if (color.contains("silver")) {
			z += 1;
			c.add(Color.SILVER);
		}
		if (color.contains("teal")) {
			z += 1;
			c.add(Color.TEAL);
		}
		if (color.contains("white")) {
			z += 1;
			c.add(Color.WHITE);
		}
		if (color.contains("yellow")) {
			z += 1;
			c.add(Color.YELLOW);
		}
		if (color.contains("christmas")) {
			z += 1;
			c.add(Color.RED);
			c.add(Color.GREEN);
			c.add(Color.WHITE);
		}
		if (z == 0) {
			c.add(Color.RED);
			c.add(Color.BLACK);
			c.add(Color.YELLOW);
		}

		z = 0;
		@SuppressWarnings("unused")
		int power = 0;
		FireworkEffect.Type t;
		if ((type.isEmpty()) || (type.equalsIgnoreCase("ball")))
			t = FireworkEffect.Type.BALL;
		else if ((type.equalsIgnoreCase("large"))
				|| (type.equalsIgnoreCase("largeball")))
			t = FireworkEffect.Type.BALL_LARGE;
		else if (type.equalsIgnoreCase("burst"))
			t = FireworkEffect.Type.BURST;
		else if (type.equalsIgnoreCase("creeper"))
			t = FireworkEffect.Type.CREEPER;
		else if (type.equalsIgnoreCase("star"))
			t = FireworkEffect.Type.STAR;
		else
			t = FireworkEffect.Type.BALL;

		boolean flicker = false;
		boolean trail = false;

		if (special.contains("trail"))
			trail = true;
		if (special.contains("flicker"))
			flicker = true;

		FireworkEffect fe = FireworkEffect.builder().flicker(flicker)
				.withColor(c).withFade(c).with(t).trail(trail).build();

		return fe;
	}
}
