package models;

public class ItemList {

	public final int MAXIMUM_SIZE = 100;
	private String[] items;
	private int size;
	public ItemList() {
		items = new String[MAXIMUM_SIZE];
		size = 0;
	}
	public int getSize() {
		return size;
	}
	public String[] getItems() {
		return items;
	}
	
	public void add(String item) {
		if(size < MAXIMUM_SIZE)
			items[size++] = item;
	}
	
	public void remove(int index) {
		if((index >= 0) && (index < size)) {//o <index && index < size
			for(int i=index; i<size-1; i++)
				items[i] = items[i+1];
			size--;
		}
	}
	
	
}
