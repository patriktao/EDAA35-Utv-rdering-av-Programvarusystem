import java.util.LinkedList;

public class ListSorter {
	private LinkedList<Integer> nums;
	private LinkedList<Integer> sortedList = new LinkedList<Integer>();
		
	public ListSorter(LinkedList<Integer> nums) {
		this.nums = nums;
	}
	
	public LinkedList<Integer> getList(){return sortedList;}
	
	public void sort(){
		for (int num: nums) {
			if (sortedList.isEmpty()){
				sortedList.add(num);
			}
			else if (sortedList.getLast() <= num) {
				sortedList.add(num);
			}
			else {
				for(int i = 0; i < sortedList.size(); i++) {
					if (num <= sortedList.get(i)) {
						sortedList.add(i, num);
						i = sortedList.size();
					}
				}
			}
		}
	}
}
