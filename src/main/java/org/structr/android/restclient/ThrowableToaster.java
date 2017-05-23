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

import android.content.Context;
import android.widget.Toast;
import java.util.List;

/**
 * A helper class that converts messages from Throwable instances into
 * Android toast messages with a display length of LENGTH_SHORT.
 *
 * @author Christian Morgner
 */
public class ThrowableToaster<T extends StructrObject> implements EntityHandler<T>, CollectionHandler<T> {

	private Context context = null;

	public ThrowableToaster(final Context context) {
		this.context = context;
	}

	public void handleProgress(final Progress... progress) {

		for(final Progress t : progress) {
			Toast.makeText(context, t.getThrowable().getMessage(), Toast.LENGTH_SHORT);
		}
	}

	public void handleResult(final T result) {
	}

	public void handleResults(final List<T> results) {
	}
}
