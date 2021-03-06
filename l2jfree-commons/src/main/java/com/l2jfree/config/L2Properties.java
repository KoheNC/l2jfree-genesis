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
package com.l2jfree.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javolution.util.FastMap;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author Noctarius
 */
public final class L2Properties implements Serializable
{
	private static final long serialVersionUID = -4599023842346938325L;
	
	private final Map<String, String> _map = new FastMap<String, String>();
	
	private boolean _warn = true;
	
	public L2Properties()
	{
	}
	
	public L2Properties setLog(boolean warn)
	{
		_warn = warn;
		
		return this;
	}
	
	private String get(String key)
	{
		return _map.get(key);
	}
	
	private String put(String key, String value)
	{
		return _map.put(key, value);
	}
	
	public int size()
	{
		return _map.size();
	}
	
	public Set<Map.Entry<String, String>> entrySet()
	{
		return Collections.unmodifiableSet(_map.entrySet());
	}
	
	// ===================================================================================
	
	public L2Properties(String name) throws IOException
	{
		load(new FileInputStream(name));
	}
	
	public L2Properties(File file) throws IOException
	{
		load(new FileInputStream(file));
	}
	
	public L2Properties(InputStream inStream) throws IOException
	{
		load(inStream);
	}
	
	public L2Properties(Reader reader) throws IOException
	{
		load(reader);
	}
	
	public L2Properties(Node node)
	{
		load(node);
	}
	
	public L2Properties(Properties properties)
	{
		load(properties);
	}
	
	public L2Properties(L2Properties properties)
	{
		load(properties);
	}
	
	// ===================================================================================
	
	public void load(String name) throws IOException
	{
		load(new FileInputStream(name));
	}
	
	public void load(File file) throws IOException
	{
		load(new FileInputStream(file));
	}
	
	public void load(InputStream inStream) throws IOException
	{
		final Properties prop = new Properties();
		
		try
		{
			prop.load(inStream);
		}
		finally
		{
			IOUtils.closeQuietly(inStream);
		}
		
		load(prop);
	}
	
	public void load(Reader reader) throws IOException
	{
		final Properties prop = new Properties();
		
		try
		{
			prop.load(reader);
		}
		finally
		{
			IOUtils.closeQuietly(reader);
		}
		
		load(prop);
	}
	
	public void load(Node node)
	{
		final NamedNodeMap attrs = node.getAttributes();
		
		for (int i = 0; i < attrs.getLength(); i++)
		{
			final Node attr = attrs.item(i);
			
			setProperty(attr.getNodeName(), attr.getNodeValue());
		}
	}
	
	public void load(Properties properties)
	{
		for (Map.Entry<Object, Object> entry : properties.entrySet())
			setProperty(entry.getKey(), entry.getValue());
	}
	
	public void load(L2Properties properties)
	{
		for (Map.Entry<String, String> entry : properties.entrySet())
			setProperty(entry.getKey(), entry.getValue());
	}
	
	// ===================================================================================
	
	public String getProperty(String key)
	{
		String property = get(key);
		
		if (property == null)
		{
			if (_warn)
				System.err.println("L2Properties: Missing property for key - " + key);
			
			return null;
		}
		
		return property.trim();
	}
	
	public String getProperty(Object key)
	{
		return getProperty(String.valueOf(key));
	}
	
	public String getProperty(String key, String defaultValue)
	{
		String property = getProperty(key);
		
		if (property == null)
			property = defaultValue;
		
		if (property == null)
		{
			if (_warn)
				System.err.println("L2Properties: Missing defaultValue for key - " + key);
			
			return null;
		}
		
		return property.trim();
	}
	
	public String getProperty(Object key, Object defaultValue)
	{
		return getProperty(String.valueOf(key), String.valueOf(defaultValue));
	}
	
	// ===================================================================================
	
	public String setProperty(String key, String value)
	{
		return put(key, value);
	}
	
	public String setProperty(Object key, Object value)
	{
		return setProperty(String.valueOf(key), String.valueOf(value));
	}
	
	// ===================================================================================
	
	public boolean getBoolean(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getBoolean(value);
	}
	
	public boolean getBoolean(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getBoolean(value);
	}
	
	public boolean getBoolean(Object name, boolean defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getBoolean(value);
	}
	
	public byte getByte(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getByte(value);
	}
	
	public byte getByte(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getByte(value);
	}
	
	public byte getByte(Object name, byte defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getByte(value);
	}
	
	public short getShort(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getShort(value);
	}
	
	public short getShort(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getShort(value);
	}
	
	public short getShort(Object name, short defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getShort(value);
	}
	
	public int getInteger(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getInteger(value);
	}
	
	public int getInteger(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getInteger(value);
	}
	
	public int getInteger(Object name, int defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getInteger(value);
	}
	
	public long getLong(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getLong(value);
	}
	
	public long getLong(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getLong(value);
	}
	
	public long getLong(Object name, long defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getLong(value);
	}
	
	public float getFloat(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getFloat(value);
	}
	
	public float getFloat(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getFloat(value);
	}
	
	public float getFloat(Object name, float defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getFloat(value);
	}
	
	public double getDouble(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getDouble(value);
	}
	
	public double getDouble(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getDouble(value);
	}
	
	public double getDouble(Object name, double defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getDouble(value);
	}
	
	public String getString(Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getString(value);
	}
	
	public String getString(Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getString(value);
	}
	
	public <T extends Enum<T>> T getEnum(Class<T> enumClass, Object name)
	{
		final String value = getProperty(name);
		
		return L2Parser.getEnum(enumClass, value);
	}
	
	public <T extends Enum<T>> T getEnum(Class<T> enumClass, Object name, String defaultValue)
	{
		final String value = getProperty(name, defaultValue);
		
		return L2Parser.getEnum(enumClass, value);
	}
	
	public <T extends Enum<T>> T getEnum(Class<T> enumClass, Object name, T defaultValue)
	{
		final String value = getProperty(name);
		if (value == null)
			return defaultValue;
		
		return L2Parser.getEnum(enumClass, value);
	}
	
	public boolean[] getBooleanArray(Object name, String regex)
	{
		return (boolean[])L2Parser.getArray(Boolean.TYPE, getProperty(name), regex);
	}
	
	public boolean[] getBooleanArray(Object name, String regex, String defaultValue)
	{
		return (boolean[])L2Parser.getArray(Boolean.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public byte[] getByteArray(Object name, String regex)
	{
		return (byte[])L2Parser.getArray(Byte.TYPE, getProperty(name), regex);
	}
	
	public byte[] getByteArray(Object name, String regex, String defaultValue)
	{
		return (byte[])L2Parser.getArray(Byte.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public short[] getShortArray(Object name, String regex)
	{
		return (short[])L2Parser.getArray(Short.TYPE, getProperty(name), regex);
	}
	
	public short[] getShortArray(Object name, String regex, String defaultValue)
	{
		return (short[])L2Parser.getArray(Short.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public int[] getIntegerArray(Object name, String regex)
	{
		return (int[])L2Parser.getArray(Integer.TYPE, getProperty(name), regex);
	}
	
	public int[] getIntegerArray(Object name, String regex, String defaultValue)
	{
		return (int[])L2Parser.getArray(Integer.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public long[] getLongArray(Object name, String regex)
	{
		return (long[])L2Parser.getArray(Long.TYPE, getProperty(name), regex);
	}
	
	public long[] getLongArray(Object name, String regex, String defaultValue)
	{
		return (long[])L2Parser.getArray(Long.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public float[] getFloatArray(Object name, String regex)
	{
		return (float[])L2Parser.getArray(Float.TYPE, getProperty(name), regex);
	}
	
	public float[] getFloatArray(Object name, String regex, String defaultValue)
	{
		return (float[])L2Parser.getArray(Float.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public double[] getDoubleArray(Object name, String regex)
	{
		return (double[])L2Parser.getArray(Double.TYPE, getProperty(name), regex);
	}
	
	public double[] getDoubleArray(Object name, String regex, String defaultValue)
	{
		return (double[])L2Parser.getArray(Double.TYPE, getProperty(name, defaultValue), regex);
	}
	
	public String[] getStringArray(Object name, String regex)
	{
		return (String[])L2Parser.getArray(String.class, getProperty(name), regex);
	}
	
	public String[] getStringArray(Object name, String regex, String defaultValue)
	{
		return (String[])L2Parser.getArray(String.class, getProperty(name, defaultValue), regex);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Enum<T>> T[] getEnumArray(Class<T> enumClass, Object name, String regex)
	{
		return (T[])L2Parser.getArray(enumClass, getProperty(name), regex);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Enum<T>> T[] getEnumArray(Class<T> enumClass, Object name, String regex, String defaultValue)
	{
		return (T[])L2Parser.getArray(enumClass, getProperty(name, defaultValue), regex);
	}
}
