package me.blha303;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GiveFireworks extends JavaPlugin {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length != 4) {
			return false;
		}
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!p.hasPermission("command.givefw")) {
				p.sendMessage("You can't use this command.");
				return true;
			}
			ItemStack i = getFirework(args);
			p.getInventory().addItem(i);
			return true;
		} else {
			System.out.println("[GiveFireworks] Must be used by a player.");
			return true;
		}
	}

	// http://stackoverflow.com/a/2275030
	public boolean contains(String haystack, String needle) {
		haystack = haystack == null ? "" : haystack;
		needle = needle == null ? "" : needle;
		return haystack.toLowerCase().contains(needle.toLowerCase());
	}

	public ItemStack getFirework(String[] args) {
		int i = 1;
		List<Color> c = new ArrayList<Color>();
		String color = args[0];
		if (color.contains("aqua"))
			c.add(Color.AQUA);
		else if (color.contains("black"))
			c.add(Color.BLACK);
		else if (color.contains("blue"))
			c.add(Color.BLUE);
		else if (color.contains("fuchsia"))
			c.add(Color.FUCHSIA);
		else if (color.contains("gray"))
			c.add(Color.GRAY);
		else if (color.contains("green"))
			c.add(Color.GREEN);
		else if (color.contains("lime"))
			c.add(Color.LIME);
		else if (color.contains("maroon"))
			c.add(Color.MAROON);
		else if (color.contains("navy"))
			c.add(Color.NAVY);
		else if (color.contains("olive"))
			c.add(Color.OLIVE);
		else if (color.contains("orange"))
			c.add(Color.ORANGE);
		else if (color.contains("purple"))
			c.add(Color.PURPLE);
		else if (color.contains("red"))
			c.add(Color.RED);
		else if (color.contains("silver"))
			c.add(Color.SILVER);
		else if (color.contains("teal"))
			c.add(Color.TEAL);
		else if (color.contains("white"))
			c.add(Color.WHITE);
		else if (color.contains("yellow"))
			c.add(Color.YELLOW);
		else {
			c.add(Color.RED);
		}

		String type = args[1];
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
		String special = args[2];

		if (special.contains("trail"))
			trail = true;
		else if (special.contains("flicker")) {
			flicker = true;
		}

		if (args.length > 3) {
			String amount = args[3];
			i = Integer.parseInt(amount);
		}

		FireworkEffect effect = FireworkEffect.builder().flicker(flicker)
				.withColor(c).withFade(c).with(t).trail(trail).build();

		ItemStack f = new ItemStack(401, i);
		FireworkMeta fm = (FireworkMeta) f.getItemMeta();
		fm.setPower(2);
		fm.addEffect(effect);
		f.setItemMeta(fm);

		return f;
	}
}
