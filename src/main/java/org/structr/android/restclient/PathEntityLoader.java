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

import android.app.Activity;

/**
 * An asynchronous connector that fetches a single entity from a REST server.
 * The execute method takes at least two parameters, the entity type and
 * the path to load the entity from.
 *
 * <p>The following example shows how you can use this class in your activity.</p>
 * <pre>
 * new PathEntityLoader(new EntityHandler() {
 *
 *	public void handleProgress(Progress... progress) {
 *		// handle progress / exception
 *	}
 *
 *	public void handleResults(StructrObject result) {
 *		// handle result
 *	}
 *
 * }).execute(Example.class, "/examples/0001");
 * </pre>
 *
 * @author Christian Morgner
 */
public class PathEntityLoader<T extends StructrObject> extends StructrConnector<T> {

	private EntityHandler<T> updater = null;

	public PathEntityLoader(Activity activity) {
		this(new ThrowableToaster(activity));
	}

	public PathEntityLoader(final EntityHandler<T> updater) {
		this.updater = updater;
	}

	@Override
	protected T doInBackground(final Object... parameters) {

		StringBuilder path = new StringBuilder();
		Class<T> type      = null;

		for(Object obj : parameters) {

			if(obj instanceof Class) {
				type = (Class)obj;
			} else {
				path.append(obj.toString());
			}
		}

		try {
			return StructrObject.dbLoad(type, path.toString());

		} catch(Throwable t) {
			publishProgress(new Progress(t));
		}

		return null;
	}

	@Override
	protected void onProgressUpdate(final Progress... progress) {
		if(updater != null) {
			updater.handleProgress(progress);
		}
	}

	@Override
	protected void onPostExecute(final T entity) {
		if(updater != null) {
			updater.handleResult(entity);
		}
	}
}
