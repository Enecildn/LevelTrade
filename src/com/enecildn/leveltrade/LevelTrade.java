package com.enecildn.leveltrade;

import org.bukkit.plugin.java.JavaPlugin;

public class LevelTrade extends JavaPlugin
{
	public void onEnable()
	{
		getCommand("lt").setExecutor(new CommandManager());
	}
}
