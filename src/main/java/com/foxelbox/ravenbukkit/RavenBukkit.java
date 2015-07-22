/**
 * This file is part of RavenBukkit.
 *
 * RavenBukkit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RavenBukkit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RavenBukkit.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.foxelbox.ravenbukkit;

import de.doridian.dependencies.config.Configuration;
import net.kencochrane.raven.DefaultRavenFactory;
import net.kencochrane.raven.Raven;
import net.kencochrane.raven.RavenFactory;
import net.kencochrane.raven.jul.SentryHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Handler;
import java.util.logging.Level;

public class RavenBukkit extends JavaPlugin {
	public static RavenBukkit instance;
    public Configuration configuration;

    private String dsn;
    private Raven raven;

    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();

        getDataFolder().mkdirs();
        configuration = new Configuration(getDataFolder());
        dsn = configuration.getValue("dsn", "https://publicKey:secretKey@host:port/1");

        RavenFactory.registerFactory(new DefaultRavenFactory());
        raven = DefaultRavenFactory.ravenInstance(dsn);

        __addHandlerToLogging(Bukkit.getServer().getLogger());
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            __addHandlerToLogging(plugin.getLogger());
        }
    }

    private void __addHandlerToLogging(java.util.logging.Logger logger) {
        if(logger == null) {
            return;
        }
        Handler sentryHandler = new SentryHandler(raven);
        sentryHandler.setLevel(Level.WARNING);
        logger.addHandler(sentryHandler);
    }
}
