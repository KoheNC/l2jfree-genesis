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
public abstract class PledgeShowMemberListUpdatePacket extends L2ServerPacket
{
	/**
	 * A nicer name for {@link PledgeShowMemberListUpdatePacket}.
	 * 
	 * @author savormix (generated)
	 * @see PledgeShowMemberListUpdatePacket
	 */
	public static final class UpdatePledgeMember extends PledgeShowMemberListUpdatePacket
	{
		/**
		 * Constructs this packet.
		 * 
		 * @see PledgeShowMemberListUpdatePacket#PledgeShowMemberListUpdatePacket()
		 */
		public UpdatePledgeMember()
		{
		}
	}
	
	/** Constructs this packet. */
	public PledgeShowMemberListUpdatePacket()
	{
	}
	
	@Override
	protected int getOpcode()
	{
		return 0x5b;
	}
	
	@Override
	protected void writeImpl(L2Client client, L2Player activeChar, MMOBuffer buf) throws RuntimeException
	{
		// TODO: when implementing, consult an up-to-date packets_game_server.xml and/or savormix
		buf.writeS(""); // Name
		buf.writeD(0); // Level
		buf.writeD(0); // Class
		buf.writeD(0); // Sex
		buf.writeD(0); // Race
		buf.writeD(0); // Member OID
		buf.writeD(0); // Unit
		buf.writeD(0); // Apprentice/Sponsor
	}
}
