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
package com.l2jfree.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author NB4L1
 */
public final class FutureWrapper implements Future<Object>
{
	private final Future<?> _future;
	
	public FutureWrapper(Future<?> future)
	{
		_future = future;
	}
	
	/**
	 * Just make sure to avoid wrong usage of Future.cancel(true).
	 */
	@Override
	public boolean cancel(boolean mayInterruptIfRunning)
	{
		return _future.cancel(false);
	}
	
	@Override
	public Object get() throws InterruptedException, ExecutionException
	{
		return _future.get();
	}
	
	@Override
	public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
	{
		return _future.get(timeout, unit);
	}
	
	@Override
	public boolean isCancelled()
	{
		return _future.isCancelled();
	}
	
	@Override
	public boolean isDone()
	{
		return _future.isDone();
	}
}
