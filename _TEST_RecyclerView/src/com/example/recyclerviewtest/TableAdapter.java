package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class TableAdapter<T> extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
	private Columnable<T>[] mDataset; 
	private int mRowLayoutResource;
	private int mCellLayoutResource;
	private int mTextViewResourceId;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TableRowLayout mTableRowLayoutView; 

		public ViewHolder(TableRowLayout v) {
			super(v);
			mTableRowLayoutView = v;
		}
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public TableAdapter(Columnable<T>[] myDataset, 
	    int rowLayoutResource,
	    int cellLayoutResource,
			int textViewResourceId) {
		mDataset = myDataset;
		mRowLayoutResource = rowLayoutResource;
		mCellLayoutResource = cellLayoutResource;
		mTextViewResourceId = textViewResourceId;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public TableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		/**
		 * CREATE A NEW ROW
		 */
		// create a new view
		TableRowLayout trl = (TableRowLayout) LayoutInflater.from(
				parent.getContext())
				.inflate(mRowLayoutResource, parent, false);
		// set the view's size, margins, paddings and layout parameters

		/**
		 * PUT COLUMNS INTO NEW ROW
		 */
		int columnCount = mDataset[0].getColumnCount();
		trl.inflateColumns(columnCount, mCellLayoutResource, parent);

		TableAdapter.ViewHolder vh = new ViewHolder(trl);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(TableAdapter.ViewHolder holder, int position) {
		/**
		 * REPLACE CONTENTS OF VIEW WITH NEW CONTENTS
		 */
		// - get element from your dataset at this position
		// - replace the contents of the view with that element

		/**
		 * POPULATE INFORMATION INTO COLUMNS
		 */
		int columnCount = mDataset[position].getColumnCount();
		int maxColumnCount = mDataset[0].getColumnCount();
		// use the smaller of the two column counts
		columnCount = (columnCount < maxColumnCount) ? columnCount : maxColumnCount;
		
		for (int i = 0; i < columnCount; i++) {
			int columnPosition = i;
			String columnString = mDataset[position].get(columnPosition).toString();

			holder.mTableRowLayoutView.populateColumnTextView(columnPosition,
					columnString, mTextViewResourceId);
		}
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		/**
		 * GET NUMBER OF ROWS
		 */
		return mDataset.length;
	}
	
	public T getItemAt(int rowPosition, int columnPosition) {
		return mDataset[rowPosition].get(columnPosition);
	}

	public interface Columnable<T> {
		public T get(int columnPosition);

		public int getColumnCount();
	}
}