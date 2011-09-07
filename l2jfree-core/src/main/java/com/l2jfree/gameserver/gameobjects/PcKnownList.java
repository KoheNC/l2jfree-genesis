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
package com.l2jfree.gameserver.gameobjects;

/**
 * @author NB4L1
 */
public final class PcKnownList extends ObjectKnownList
{
	public PcKnownList(L2PcInstance activeChar)
	{
		super(activeChar);
	}
	
	@Override
	public L2PcInstance getActiveChar()
	{
		return (L2PcInstance)super.getActiveChar();
	}
	
	@Override
	protected int getDistanceToAddObject(L2Object obj)
	{
		// H5 retail value 2011-09-07
		if (obj instanceof L2ItemInstance)
			return 2500;
		
		return super.getDistanceToRemoveObject(obj);
	}
	
	@Override
	protected int getDistanceToRemoveObject(L2Object obj)
	{
		// H5 retail value 2011-09-07
		if (obj instanceof L2ItemInstance)
			return 3000;
		
		return super.getDistanceToRemoveObject(obj);
	}
}
