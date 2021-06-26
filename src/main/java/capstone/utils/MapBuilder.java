/**
 * 
 */
package capstone.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map Builder
 * @author Tuna
 *
 */
public class MapBuilder<K, V> {
	
	private Map<K, V> map;
	
	public static <K, V> Map<K,V> hashMap(K key, V value) {
		return new HashMap<K, V>();
	}
	
	public static <K, V> Map<K,V> treeMap(K key, V value) {
		return new TreeMap<K, V>();
	}

	public MapBuilder() {
		this.map = new HashMap<K, V>();
	}

	public MapBuilder(K key, V value) {
		this.map = new HashMap<K, V>();
		this.put(key, value);
	}

	public MapBuilder(Map<K, V> map) {
		assert map != null : "map is null";
		this.map = map;
	}
	
	public MapBuilder<K, V> put(K key, V value) {
		this.map.put(key, value);
		return this;
	}
	
	public MapBuilder<K, V> putAll(Map<? extends K, ? extends V> m) {
		this.map.putAll(m);
		return null;
	}
	
	public MapBuilder<K, V> remove(Object key) {
		this.map.remove(key);
		return null;
	}
	
	public Map<K, V> build() {
		return this.map;
	}
	
	public Map<K, V> unmodifiable() {
		return Collections.unmodifiableMap(map);
	}

}
