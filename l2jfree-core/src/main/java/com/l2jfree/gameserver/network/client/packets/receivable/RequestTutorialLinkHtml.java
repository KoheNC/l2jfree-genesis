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
import com.l2jfree.network.ClientProtocolVersion;
import com.l2jfree.network.mmocore.InvalidPacketException;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author savormix (generated)
 */
public abstract class RequestTutorialLinkHtml extends L2ClientPacket
{
	/**
	 * A nicer name for {@link RequestTutorialLinkHtml}.
	 * 
	 * @author savormix (generated)
	 * @see RequestTutorialLinkHtml
	 */
	public static final class RequestLinkTutorialHtml extends RequestTutorialLinkHtml
	{
		// only for convenience
	}
	
	/** Packet's identifier */
	public static final int OPCODE = 0x85;
	
	@Override
	protected int getMinimumLength()
	{
		return READ_S;
	}
	
	/* Fields for storing read data */
	
	@Override
	protected void read(MMOBuffer buf) throws BufferUnderflowException, RuntimeException
	{
		// TODO: when implementing, consult an up-to-date packets_game_server.xml and/or savormix
		if (getClient().getVersion().isNewerThanOrEqualTo(ClientProtocolVersion.GODDESS_OF_DESTRUCTION))
			buf.readD(); // ??? 1
		buf.readS(); // Relative path to file
	}
	
	@Override
	protected void runImpl() throws InvalidPacketException, RuntimeException
	{
		// TODO: implement
	}
}
