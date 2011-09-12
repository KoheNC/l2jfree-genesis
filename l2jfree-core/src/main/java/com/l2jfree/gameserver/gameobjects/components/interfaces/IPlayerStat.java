/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jfree.gameserver.gameobjects.components.interfaces;

/**
 * @author NB4L1
 */
public interface IPlayerStat extends ICharacterStat
{
	public int getMaxSP();
	
	public int getMaxCP();
	
	public int getCurrentCP();
	
	public int getCarriedWeight();
	
	public int getMaxCarriedWeight();
	
	public int getKarmaPoints();
	
	public int getVitalityPoints();
	
	public int getFamePoints();
	
	public int getPkCount();
	
	public int getPvPCount();
}