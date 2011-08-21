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
package com.l2jfree.security;

import java.nio.ByteBuffer;

/**
 * This class ...
 * 
 * @deprecated Legacy class
 * @version $Revision: 1.3.4.3 $ $Date: 2005/03/27 15:29:18 $
 */
@Deprecated
public class Crypt
{
	private final byte[] _key = new byte[16];
	private boolean _isEnabled;
	
	/**
	 * Specifies the key to be shared for both encryption and decryption.
	 * 
	 * @param key a key
	 */
	public void setKey(byte[] key)
	{
		System.arraycopy(key, 0, _key, 0, key.length);
		_isEnabled = true;
	}
	
	/**
	 * Decrypts the given buffer contents starting at it's current position and updates the shared
	 * key.
	 * 
	 * @param buf packet's body
	 */
	public void decrypt(ByteBuffer buf)
	{
		if (!_isEnabled)
			return;
		
		final int sz = buf.remaining();
		int temp = 0;
		for (int i = 0; i < sz; i++)
		{
			int temp2 = buf.get(i);
			buf.put(i, (byte)(temp2 ^ _key[i & 15] ^ temp));
			temp = temp2;
		}
		
		int old = _key[8] & 0xff;
		old |= _key[9] << 8 & 0xff00;
		old |= _key[10] << 0x10 & 0xff0000;
		old |= _key[11] << 0x18 & 0xff000000;
		
		old += sz;
		
		_key[8] = (byte)(old & 0xff);
		_key[9] = (byte)(old >> 0x08 & 0xff);
		_key[10] = (byte)(old >> 0x10 & 0xff);
		_key[11] = (byte)(old >> 0x18 & 0xff);
	}
	
	/**
	 * Encrypts the given buffer contents starting at it's current position and updates the shared
	 * key.
	 * 
	 * @param buf packet's body
	 */
	public void encrypt(ByteBuffer buf)
	{
		if (!_isEnabled)
			return;
		
		int temp = 0;
		final int sz = buf.remaining();
		for (int i = 0; i < sz; i++)
		{
			int temp2 = buf.get(i);
			temp = temp2 ^ _key[i & 15] ^ temp;
			buf.put(i, (byte)temp);
		}
		
		int old = _key[8] & 0xff;
		old |= _key[9] << 8 & 0xff00;
		old |= _key[10] << 0x10 & 0xff0000;
		old |= _key[11] << 0x18 & 0xff000000;
		
		old += sz;
		
		_key[8] = (byte)(old & 0xff);
		_key[9] = (byte)(old >> 0x08 & 0xff);
		_key[10] = (byte)(old >> 0x10 & 0xff);
		_key[11] = (byte)(old >> 0x18 & 0xff);
	}
}
