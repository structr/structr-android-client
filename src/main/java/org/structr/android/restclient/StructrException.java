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

/**
 * An exception that encapsulates the HTTP response code
 * and phrase of a REST operation.
 *
 * @author Christian Morgner
 */
public class StructrException extends Throwable {

	private String responsePhrase = null;
	private String responseBody   = null;
	private int responseCode      = 0;

	public StructrException(final int responseCode, final String responsePhrase, final String responseBody) {

		this.responsePhrase = responsePhrase;
		this.responseCode   = responseCode;
		this.responseBody   = responseBody;
	}

	@Override
	public String getMessage() {
		return "Error " + responseCode + ": " + responsePhrase + ": " + responseBody;
	}

	public String getResponsePhrase() {
		return responsePhrase;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public int getResponseCode() {
		return responseCode;
	}
}
