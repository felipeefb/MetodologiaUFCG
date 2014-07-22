
package core.estrutura;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class EstruturaHashMap.
 */
public class EstruturaHashMap implements Estrutura {

    /** The hash map. */
    HashMap<String, String> hashMap;

    /**
     * Instantiates a new estrutura hash map.
     */
    public EstruturaHashMap() {
        hashMap = new HashMap<String, String>();
    }

    /* (non-Javadoc)
     * @see core.estrutura.Estrutura#search(java.lang.String)
     */
    @Override
    public boolean search(String word) {
        return hashMap.containsKey(word);
    }

    /* (non-Javadoc)
     * @see core.estrutura.Estrutura#insert(java.lang.String)
     */
    @Override
    public void insert(String word) {
        hashMap.put(word, null);
    }

}
