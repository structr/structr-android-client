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
 * new EntityCreator(new EntityHandler() {
 *
 * 	public void handleProgress(Progress... progress) {
 *		// handle progress / exception
 * 	}
 *
 * 	public void handleResult(StructrObject result) {
 *		// handle result
 * 	}
 *
 * }).execute(newEntitiy);
 * </pre>
 *
 * @author Christian Morgner
 */
public class EntityCreator<T extends StructrObject> extends StructrConnector<T> {

	private EntityHandler updater = null;

	public EntityCreator(Activity activity) {
		this(new ThrowableToaster(activity));
	}

	public EntityCreator(EntityHandler updater) {
		this.updater = updater;
	}

	@Override
	protected T doInBackground(final Object... parameters) {

		T entity = null;

		for(Object obj : parameters) {
			if(obj instanceof StructrObject) {
				entity = (T)obj;
			}
		}

		try {
			if(entity != null) {
				entity.dbCreate();
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
