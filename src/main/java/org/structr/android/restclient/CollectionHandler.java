/**
 * Copyright (C) 2012-2015 Morgner UG (haftungsbeschränkt)
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.structr.android.restclient;

import java.util.List;

/**
 * A handler that you can register with a {@see PathCollectionLoader} or
 * a {@see SortedCollectionLoader} to handle the results of a REST GET
 * operation on a collection resource.
 *
 * @author Christian Morgner
 */
public interface CollectionHandler<T extends StructrObject> {

	/**
	 * Will be called when a progress update or an exception occurs.
	 * @param progress
	 */
	public void handleProgress(Progress... progress);

	/**
	 * Will be called when the load operation is finished. Please note
	 * that the result list can be null when the operation fails.
	 * @param results the result list or null
	 */
	public void handleResults(List<T> results);
}
