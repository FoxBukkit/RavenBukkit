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
package de.doridian.ravenbukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class RavenBukkit extends JavaPlugin {
	public static RavenBukkit instance;

	@Override
	public void onEnable() {
		instance = this;
		super.onEnable();

	}
}
