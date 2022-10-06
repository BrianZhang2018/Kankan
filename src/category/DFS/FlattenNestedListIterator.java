package category.DFS;

import java.util.*;

public class FlattenNestedListIterator implements Iterator<Integer> {

    List<Integer> list = new ArrayList();
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }
    public void flattenList(List<NestedInteger> nestedList) {
        for(NestedInteger i : nestedList) {
            if(i.isInteger()) {
                list.add(i.getInteger());
            } else {
                flattenList(i.getList());
            }
        }
    }

    int index = -1;
    @Override
    public Integer next() {
        return list.get(index);
    }

    @Override
    public boolean hasNext() {
        if(index +1 >= list.size())
            return false;

        index++;
        return true;
    }
}

interface NestedInteger {
 // @return true if this NestedInteger holds a single integer, rather than a nested list.
 public boolean isInteger();

 // @return the single integer that this NestedInteger holds, if it holds a single integer
 // Return null if this NestedInteger holds a nested list
 public Integer getInteger();

 // @return the nested list that this NestedInteger holds, if it holds a nested list
 // Return empty list if this NestedInteger holds a single integer
 public List<NestedInteger> getList();
 }
