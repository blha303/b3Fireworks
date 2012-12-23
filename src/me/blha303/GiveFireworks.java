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
		if (args.length < 3 || args.length > 4) {
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
		int z = 0;
		List<Color> c = new ArrayList<Color>();
		String color = args[0];
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
		String type = args[1];
		@SuppressWarnings("unused")
		int power = 0;
		FireworkEffect.Type t;
		if ((type.isEmpty()) || (type.equalsIgnoreCase("ball"))) {
			power = 1;
			t = FireworkEffect.Type.BALL;
		}
		else if ((type.equalsIgnoreCase("large"))
				|| (type.equalsIgnoreCase("largeball"))) {
			power = 2;
			t = FireworkEffect.Type.BALL_LARGE;
		}
		else if (type.equalsIgnoreCase("burst")){
			power = 3;
			t = FireworkEffect.Type.BURST;
		}
		else if (type.equalsIgnoreCase("creeper")) {
			power = 1;
			t = FireworkEffect.Type.CREEPER;
		}
		else if (type.equalsIgnoreCase("star")) {
			power = 3;
			t = FireworkEffect.Type.STAR;
		}
		else {
			power = 1;
			t = FireworkEffect.Type.BALL;
		}

		boolean flicker = false;
		boolean trail = false;
		String special = args[2];

		if (special.contains("trail"))
			trail = true;
		if (special.contains("flicker"))
			flicker = true;

		try {
			i = Integer.parseInt(args[3]);
		} catch (Exception e) {

		}

		FireworkEffect effect = FireworkEffect.builder().flicker(flicker)
				.withColor(c).withFade(c).with(t).trail(trail).build();

		ItemStack f = new ItemStack(401, i);
		FireworkMeta fm = (FireworkMeta) f.getItemMeta();
//		fm.setPower(power);
		fm.setPower(2);
		fm.addEffect(effect);
		f.setItemMeta(fm);

		return f;
	}
}
