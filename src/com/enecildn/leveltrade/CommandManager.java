package com.enecildn.leveltrade;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] data)
	{
		if (label.equalsIgnoreCase("lt"))
		{
			if (sender instanceof ConsoleCommandSender)
			{
				sender.sendMessage("This command can be used by only players");
			}
			else
			{
				if (data.length == 2)
				{
					if (this.getPlayer(data[0]) != null)
					{
						if (isParsable(data[1]))
						{
							Player player = this.getPlayer(data[0]);
							int level = Integer.parseInt(data[1]);
							if (((Player) sender).getLevel() >= level)
							{
								((Player) sender).setLevel(((Player) sender).getLevel() - level);
								player.setLevel(player.getLevel() + level);
								sender.sendMessage("Sent " + level + " levels to " + player.getName() + ".");
								player.sendMessage("Received " + level + " levels from " + ((Player) sender).getName() + ".");
							}
							else
							{
								sender.sendMessage("<level> can be at most " + ((Player) sender).getLevel() + " levels.");
							}
						}
						else
						{
							sender.sendMessage("<level> must be an integer.");
						}
					}
					else
					{
						sender.sendMessage("Player does not exist.");
					}
				}
				else
				{
					sender.sendMessage("/lt <player> <level>");
				}
			}
			return true;
		}
		return false;
	}

	private Player getPlayer(String string)
	{
		for (Player player : Bukkit.getServer().getOnlinePlayers())
		{
			if (player.getName().equalsIgnoreCase(string))
			{
				return player;
			}
		}
		return null;
	}

	private boolean isParsable(String string)
	{
		try
		{
			Integer.parseInt(string);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
