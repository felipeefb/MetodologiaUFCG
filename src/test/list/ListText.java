
package test.list;

import core.linked_list.SingleLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ListText.
 */
public class ListText {

    /** The lista1. */
    SingleLinkedList<String> lista1;
    
    /** The lista2. */
    SingleLinkedList<String> lista2;
    
    /** The busca1. */
    String[] busca1 = {
            "jose", "maria", "josefina"
    };

    /** The busca2. */
    String[] busca2 = {
            "1", "2", "3", "4", "5", "treze", "quatorze", "quinze", "sefu"
    };

    /**
     * Setup.
     */
    @Before
    public void setup() {
        lista1 = new SingleLinkedList<String>();
        lista2 = new SingleLinkedList<String>();
        preencherLista1();
        preencherLista2();
    }

    /**
     * Preencher lista2.
     */
    private void preencherLista2() {

        for (int i = 0; i < busca2.length; i++) {
            lista1.insert(busca2[i]);

        }

    }

    /**
     * Preencher lista1.
     */
    private void preencherLista1() {
        for (int i = 0; i < busca1.length; i++) {
            lista2.insert(busca1[i]);
        }

    }

    /**
     * Test searchtrue.
     */
    @Test
    public void testSearchtrue() {
        for (int i = 0; i < busca1.length; i++) {
            Assert.assertTrue(lista2.search(busca1[i]));
        }
    }

    /**
     * Test search false.
     */
    @Test
    public void testSearchFalse() {
        for (int i = 0; i < busca1.length; i++) {
            Assert.assertFalse(lista1.search(busca1[i]));
        }
    }
}
