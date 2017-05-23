/*
 *  Copyright (C) 2012 Axel Morgner
 *
 *  This file is part of structr <http://structr.org>.
 *
 *  structr is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  structr is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with structr.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.structr.android.restclient;

import android.app.Activity;

/**
 * An asynchronous connector that creates an entity on a structr REST server.
 * The execute method takes exactly one parameter, namely the entity to
 * create on the server.
 *
 * <p>The following examples show how you can use this class in your activity.</p>
 * <h5>Without handler</h5>
 * <pre>
 * new EntityCreator(this).execute(newEntity);
 * </pre>
 * <h5>With handler</h5>
 * <pre>
 * new PathEntityCreator(new EntityHandler() {
 *
 * 	public void handleProgress(Progress... progress) {
 *		// handle progress / exception
 * 	}
 *
 * 	public void handleResult(StructrObject result) {
 *		// handle result
 * 	}
 *
 * }).execute("/", newEntitiy);
 * </pre>
 *
 * @author Christian Morgner
 */
public class PathEntityCreator<T extends StructrObject> extends StructrConnector<T> {

	private EntityHandler updater = null;

	public PathEntityCreator(Activity activity) {
		this(new ThrowableToaster(activity));
	}

	public PathEntityCreator(EntityHandler updater) {
		this.updater = updater;
	}

	@Override
	protected T doInBackground(final Object... parameters) {

		String path = "/";
		T entity = null;

		for(Object obj : parameters) {

			if(obj instanceof StructrObject) {

				entity = (T)obj;

			} else if (obj instanceof String) {

				path = obj.toString();
			}
		}

		try {
			if(entity != null) {

				entity.dbCreate(path);
			}

		} catch(Throwable t) {
			publishProgress(new Progress(t));
		}

		return entity;
	}

	@Override
	protected void onProgressUpdate(Progress... progress) {
		if(updater != null) {
			updater.handleProgress(progress);
		}
	}

	@Override
	protected void onPostExecute(StructrObject entity) {
		if(updater != null) {
			updater.handleResult(entity);
		}
	}
}
