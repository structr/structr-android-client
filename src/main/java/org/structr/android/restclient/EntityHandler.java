/**
 * Copyright (C) 2012-2015 Morgner UG (haftungsbeschränkt)
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.structr.android.restclient;

/**
 * A handler that you can register with an {@see IdEntityLoader},
 * {@see EntityCreator}, {@see EntityDeleter} or {@see EntityStorer} to
 * handle the results of the specified operation.
 *
 * @author Christian Morgner
 */
public interface EntityHandler<T extends StructrObject> {

	/**
	 * Will be called when a progress update or an exception occurs.
	 * @param progress
	 */
	public void handleProgress(final Progress... progress);


	/**
	 * Will be called when the operation is finished. Please note
	 * that the result can be null when the operation fails.
	 * @param results the result entity or null
	 */
	public void handleResult(final T result);
}
