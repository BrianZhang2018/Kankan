package OOD;

import java.util.*;
/**
 * "重要的一点在OOD的时候一定要记住：基本的structure(map, list ...)满足不了需求的时候，就自己创建一个新的class去实现，例如本题的 Operation class"
 *
 * Addepar phone screening
 *
 * Created by brianzhang on 4/29/21.
 */
public class Table {

    public static void main(String[] args) {}

    Map<String, Row> tm = new HashMap<>();
    LinkedList<Operation> ll = new LinkedList<>();
    boolean isInTransaction = false;

    public void createRow(String rowName){
        if(!tm.containsKey(rowName)) return;
        tm.put(rowName, new Row(rowName));

        if(isInTransaction) ll.add(new Operation("Create", rowName, "", "", null));
    }
    public void deleteRow(String rowName){
        if(!tm.containsKey(rowName)) return;
        Row r = tm.remove(rowName);

        if(isInTransaction) ll.add(new Operation("Delete", rowName, "", "", r));
    }
    public void updateRow(String rowName, String colName, String colValue){
        if(!tm.containsKey(rowName)) return;
        Row r = tm.get(rowName);
        r.map.put(colName, colValue);

        if(isInTransaction) ll.add(new Operation("Update", rowName, colName, colValue, r));
    }

    public void beginTransaction(){
        if(isInTransaction) return;

        isInTransaction = true;
    }

    public void commitTransaction(){
        isInTransaction = false;
    }

    public void rollbackTransaction(){

        if(ll.size() >0) {
            while (ll.size() > 0) {
                Operation o = ll.poll();
                if (o.action.equals("Create")) {
                    tm.remove(o.rowName);
                } else if (o.action.equals("Delete")) {
                    tm.put(o.rowName, o.r);
                } else if (o.action.equals("Update")) {
                    tm.get(o.rowName).map.put(o.colName, o.colVal);
                }
            }
        }
        ll.clear();
    }
}

// 解题思路: 创建自己的Operation Class
class Operation {
    String action, rowName;
    String colName, colVal;
    Row r;
    public Operation(String action, String rowName, String colName, String colVal, Row r){
        this.action = action;
        this.rowName = rowName;
        this.colName = colName;
        this.colVal = colVal;
        this.r = r;
    }
}

class Row {
    String name;
    Map<String,String> map;

    public Row(String rowName){
        this.name = rowName;
        map = new HashMap<>();
    }
}
