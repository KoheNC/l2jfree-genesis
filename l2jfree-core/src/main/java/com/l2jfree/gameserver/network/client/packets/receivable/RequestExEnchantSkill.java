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
package com.l2jfree.gameserver.network.client.packets.receivable;

import java.nio.BufferUnderflowException;

import com.l2jfree.gameserver.network.client.packets.L2ClientPacket;
import com.l2jfree.network.mmocore.InvalidPacketException;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author savormix (generated)
 */
public abstract class RequestExEnchantSkill extends L2ClientPacket
{
	/**
	 * A nicer name for {@link RequestExEnchantSkill}.
	 * 
	 * @author savormix (generated)
	 * @see RequestExEnchantSkill
	 */
	public static final class RequestEnchantSkill extends RequestExEnchantSkill
	{
		/**
		 * Constructs this packet.
		 * 
		 * @see RequestExEnchantSkill#RequestExEnchantSkill()
		 */
		public RequestEnchantSkill()
		{
		}
	}
	
	/** Packet's identifier */
	public static final int OPCODE = 0xd0;
	/** Additional packet's identifiers */
	public static final int[] EXT_OPCODES = { 0x0f, 0x00 };
	/** Second packet's identifier */
	public static final int OPCODE_2 = 0x0f;
	
	/** Constructs this packet. */
	public RequestExEnchantSkill()
	{
	}
	
	@Override
	protected int getMinimumLength()
	{
		return READ_D + READ_D;
	}
	
	@Override
	protected void read(MMOBuffer buf) throws BufferUnderflowException, RuntimeException
	{
		// TODO: when implementing, consult an up-to-date packets_game_server.xml and/or savormix
		buf.readD(); // Skill
		buf.readD(); // Level
	}
	
	@Override
	protected void runImpl() throws InvalidPacketException, RuntimeException
	{
		// TODO: implement
	}
}
