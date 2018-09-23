
import java.util.LinkedHashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;


public class LRU<K,V> extends LinkedHashMap<K,V> {
	
	private static final long serialVersionUID=-4794975964067719956L;
	private int size;
	
	private LRU(int size) {
		super(size,0.75f,true);
		this.size=size;
	}
	
    public static <K,V> LRU<K,V> newInstance(int size){
    	return new LRU<K,V>(size);
    }
	
    public void setMaxSize(int size) {
    	this.size=size;
    }
	
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    	
    	return size()>size;
    }
    
    public static void main(String args[]) {
    	
    	LRU<String,String> xt = LRU.newInstance(2);
    	xt.put("1", "1");
    	xt.put("2", "2");
    	xt.put("3", "3");
    	
    	System.out.println(xt);
    }
    
    
    
	
}
