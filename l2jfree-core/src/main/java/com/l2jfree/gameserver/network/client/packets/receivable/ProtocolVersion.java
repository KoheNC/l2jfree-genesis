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

import com.l2jfree.gameserver.network.client.L2Client;
import com.l2jfree.gameserver.network.client.packets.L2ClientPacket;
import com.l2jfree.gameserver.network.client.packets.sendable.ProtocolAnswer;
import com.l2jfree.network.mmocore.InvalidPacketException;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author savormix
 */
public final class ProtocolVersion extends L2ClientPacket
{
	/** Packet's identifier */
	public static final int OPCODE = 0x0e;
	
	private int _version;
	
	@Override
	protected int getMinimumLength()
	{
		return READ_D;
	}
	
	@Override
	protected void read(MMOBuffer buf) throws BufferUnderflowException, RuntimeException
	{
		_version = buf.readD();
	}
	
	@Override
	protected void runImpl() throws InvalidPacketException, RuntimeException
	{
		_log.info("Protocol: " + _version);
		
		final L2Client client = getClient();
		client.close(ProtocolAnswer.INCOMPATIBLE);
		
		/* TODO: after verifying
		final int seed = Rnd.get(Integer.MIN_VALUE, Integer.MAX_VALUE);
		client.getDeobfuscator().init(seed);
		client.setState(L2ClientState.PROTOCOL_OK);
		sendPacket(new ProtocolAnswer(true, client.getCipherKey(), seed));
		*/
	}
}
