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
package com.l2jfree.gameserver.network.client.packets.sendable;

import com.l2jfree.gameserver.gameobjects.L2Player;
import com.l2jfree.gameserver.network.client.L2Client;
import com.l2jfree.gameserver.network.client.packets.L2ServerPacket;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * @author savormix (generated)
 */
public abstract class ExChangePostState extends L2ServerPacket
{
	/**
	 * A nicer name for {@link ExChangePostState}.
	 * 
	 * @author savormix (generated)
	 * @see ExChangePostState
	 */
	public static final class MailboxUpdate extends ExChangePostState
	{
		/**
		 * Constructs this packet.
		 * 
		 * @see ExChangePostState#ExChangePostState()
		 */
		public MailboxUpdate()
		{
		}
	}
	
	private static final int[] EXT_OPCODES = { 0xb3, 0x00 };
	
	/** Constructs this packet. */
	public ExChangePostState()
	{
	}
	
	@Override
	protected int getOpcode()
	{
		return 0xfe;
	}
	
	@Override
	protected int[] getAdditionalOpcodes()
	{
		return EXT_OPCODES;
	}
	
	@Override
	protected void writeImpl(L2Client client, L2Player activeChar, MMOBuffer buf) throws RuntimeException
	{
		// TODO: when implementing, consult an up-to-date packets_game_server.xml and/or savormix
		buf.writeD(0); // Mailbox
		final int sizeA = 0; // Update count
		buf.writeD(sizeA);
		for (int i = 0; i < sizeA; i++)
		{
			buf.writeD(0); // Message ID
			buf.writeD(0); // Action
		}
	}
}
