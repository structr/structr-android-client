/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.structr.android.restclient;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;

/**
 *
 * @author Christian Morgner
 */
public abstract class CollectionHandlerListAdapter<T extends StructrObject> implements CollectionHandler<T>, ListAdapter {

	private ListView listView = null;
	private List<T> list      = null;

	public CollectionHandlerListAdapter(final ListView listView) {
		this.listView = listView;
	}

	public abstract View getViewForItem(final T item, final View view, final ViewGroup vg);

	@Override
	public void handleProgress(final Progress... progress) {
	}

	@Override
	public void handleResults(final List<T> results) {

		this.list = results;
		listView.setAdapter(this);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int i) {
		return true;
	}

	@Override
	public void registerDataSetObserver(final DataSetObserver dso) {
	}

	@Override
	public void unregisterDataSetObserver(final DataSetObserver dso) {
	}

	@Override
	public int getCount() {

		if (list != null) {
			return list.size();
		}

		return 0;
	}

	@Override
	public Object getItem(final int i) {

		if (list != null) {
			return list.get(i);
		}

		return null;
	}

	@Override
	public long getItemId(final int i) {

		if (list != null) {
			return list.get(i).hashCode();
		}

		return -1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(final int i, final View view, final ViewGroup vg) {
		return getViewForItem(list.get(i), view, vg);
	}

	@Override
	public int getItemViewType(int i) {
		return -1;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return list != null && list.isEmpty();
	}

	public void clear() {

		if (list != null) {
			list.clear();
		}
	}
}
