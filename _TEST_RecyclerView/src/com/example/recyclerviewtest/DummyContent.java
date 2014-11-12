package com.example.recyclerviewtest;

import com.example.recyclerviewtest.TableAdapter.Columnable;

public class DummyContent {

	public static class RowData implements Columnable<ObjectPlus> {

		private String mName;
		private int mColumnCount;
		private ObjectPlus[] mObjects;

		public RowData(String name, int columnCount) {
			mName = name;
			mColumnCount = columnCount;
			mObjects = new ObjectPlus[columnCount];
			for (int i = 0; i < columnCount; i++) {
				mObjects[i] = new ObjectPlus(i);
			}
		}

		@Override
		public ObjectPlus get(int columnPosition) {
			return mObjects[columnPosition];
		}

		@Override
		public int getColumnCount() {
			return mColumnCount;
		}
	}
	
	public static class ObjectPlus {
		private String name;
		
		public ObjectPlus(int name) {
			this.name = String.valueOf(name);
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		public void setText(String text) {name = text;}
	}

	public static Columnable<ObjectPlus>[] DUMMY_DATA = new RowData[] {
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5), 
			new RowData("second", 5),
			new RowData("third", 5), 
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5), 
			new RowData("second", 5),
			new RowData("third", 5), 
			new RowData("fourth", 5),
			new RowData("fifth", 5), 
			new RowData("sixth", 5) };

}
