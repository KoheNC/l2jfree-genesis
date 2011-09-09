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
import com.l2jfree.gameserver.gameobjects.player.PlayerAppearance;
import com.l2jfree.gameserver.network.client.L2Client;
import com.l2jfree.gameserver.network.client.packets.L2ServerPacket;
import com.l2jfree.gameserver.templates.L2PlayerTemplate;
import com.l2jfree.gameserver.templates.player.PlayerBaseTemplate;
import com.l2jfree.network.mmocore.MMOBuffer;

/**
 * Original name was <TT>CharSelectionInfo</TT>
 * 
 * @author hex1r0
 */
public class AvailableCharacters extends L2ServerPacket
{
	private final String _accountName;
	private final int _sessionId;
	private final L2Player[] _players;
	
	public AvailableCharacters(L2Client client)
	{
		_sessionId = 0; // TODO
		_accountName = client.getAccountName();
		_players = L2Player.loadAccountPlayers(_accountName);
	}
	
	@Override
	protected int getOpcode()
	{
		return 0x09;
	}
	
	@Override
	protected void writeImpl(L2Client client, MMOBuffer buf) throws RuntimeException
	{
		buf.writeD(_players.length); // TODO num of chars
		buf.writeD(0x07);
		buf.writeC(0);
		
		for (L2Player p : _players)
		{
			PlayerAppearance appearance = p.getAppearance();
			L2PlayerTemplate template = p.getTemplate();
			PlayerBaseTemplate baseTemplate = template.getPlayerBaseTemplate(appearance.getGender());
			
			buf.writeS(p.getName()); // TODO
			buf.writeD(p.getObjectId()); // TODO
			buf.writeS(_accountName);
			buf.writeD(_sessionId);
			buf.writeD(0); // TODO clan id
			buf.writeD(0); // ??
			
			buf.writeD(baseTemplate.getGender().ordinal()); // TODO sex
			buf.writeD(baseTemplate.getRace().ordinal()); // TODO race
			
			buf.writeD(template.getClassId().getId()); // TODO class id
			
			buf.writeD(0x01); // active ?? (no difference between 0 and 1)
			
			buf.writeD(p.getPosition().getX()); // TODO x
			buf.writeD(p.getPosition().getY()); // TODO y
			buf.writeD(p.getPosition().getZ()); // TODO z
			
			buf.writeF(100.0); // TODO hp cur
			buf.writeF(100.0); // TODO mp cur
			
			buf.writeD(50); // TODO SP
			buf.writeQ(50); // TODO EXP
			buf.writeF(0.5); // TODO EXP % HF
			buf.writeD(5); // TODO Level 
			
			buf.writeD(5); // TODO karma
			buf.writeD(15); // TODO pk
			buf.writeD(15); // TODO PVP
			
			for (int k = 0; k < 7; k++)
				buf.writeD(0x00);
			
			for (int slot = 0; slot < 26; slot++)
				buf.writeD(0); // TODO PaperdollSlots
			
			buf.writeD(appearance.getHairStyle()); // TODO hair style
			buf.writeD(appearance.getHairColor()); // TODO hair color
			buf.writeD(appearance.getFace()); // TODO face
			
			buf.writeF(200.0); // TODO hp max
			buf.writeF(200.0); // TODO mp max
			
			buf.writeD(0x00); // TODO delete days left before
			buf.writeD(template.getClassId().getId()); // TODO class id
			buf.writeD(1); // TODO active
			buf.writeC(0); // TODO enchant effect
			buf.writeD(0); // TODO augmentation
			
			buf.writeD(0); // TODO transformation
			
			buf.writeD(0x00);
			buf.writeD(0x00);
			buf.writeD(0x00);
			buf.writeD(0x00);
			buf.writeF(0x00);
			buf.writeF(0x00);
			
			buf.writeD(0); // vitallity points HF
		}
		
		client.definePlayerSlots(_players);
	}
}
