
package core.bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestBST2.
 */
public class TestBST2 {

    /** The tree1. */
    protected BSTImpl<Integer, Integer> tree1;

    /** The tree2. */
    protected BSTImpl<Integer, Integer> tree2;

    /** The tree3. */
    protected BSTImpl<String, String> tree3;

    /** The tree4. */
    protected BSTImpl<Integer, Integer> tree4;

    /** The tree5. */
    protected BSTImpl<String, String> tree5;

    /** The Constant SIZE. */
    protected static final int SIZE = 5;

    /**
     * Sets the up.
     * 
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        // Somente filhos a direita
        tree1 = new BSTImpl<Integer, Integer>();
        for (int i = 0; i < SIZE; i++) {
            tree1.insert2(i);
        }

        // Somente filhos a esquerda
        tree2 = new BSTImpl<Integer, Integer>();
        for (int i = SIZE - 1; i >= 0; i--) {
            tree2.insert2(i);
        }

        // �rvore completa
        tree3 = new BSTImpl<String, String>();
        tree3.insert2("5");
        tree3.insert2("3");
        tree3.insert2("1");
        tree3.insert2("4");
        tree3.insert2("8");
        tree3.insert2("6");
        tree3.insert2("9");

        // �rvore vazia
        tree4 = new BSTImpl<Integer, Integer>();
        tree5 = new BSTImpl<String, String>();
        tree5.insert("aew");
        tree5.insert("vlw");
        tree5.insert("moral");
    }

    /**
     * Test get root.
     */
    @Test
    public void testGetRoot() {
        assertEquals(new Integer(0), tree1.getRoot().key);
        assertEquals(new Integer(4), tree2.getRoot().key);
        assertEquals("5", tree3.getRoot().key);
        assertEquals(null, tree4.getRoot().key);
    }

    /**
     * Test is empty.
     */
    @Test
    public void testIsEmpty() {
        assertFalse(tree1.isEmpty());
        assertFalse(tree2.isEmpty());
        assertFalse(tree3.isEmpty());
        assertTrue(tree4.isEmpty());
    }

    /**
     * Test height.
     */
    @Test
    public void testHeight() {
        assertEquals(4, tree1.height());
        assertEquals(4, tree2.height());
        assertEquals(2, tree3.height());
        assertEquals(-1, tree4.height());
    }

    /**
     * Test search.
     */
    @Test
    public void testSearch() {
        // testa a busca por todos os elementos
        for (int i = SIZE - 1; i >= 0; i--) {
            assertEquals(new Integer(i), tree1.search2(i));
        }

        // testa a busca por um elemento que n�o existe
        assertEquals(null, tree2.search2(10));

        // testa a busca por elementos a esquerda e a direita
        // assertEquals(new Integer(3), tree3.search2("3"));
        // assertEquals(new Integer(8), tree3.search2("8"));
        // assertEquals(new Integer(6), tree3.search2("6"));
        // assertEquals(new Integer(4), tree3.search2("4"));
        assertEquals(null, tree3.search2("100"));
        boolean resp = tree3.search("3");
        assertTrue(resp);
        resp = tree3.search("100");
        assertFalse(resp);
        resp = tree5.search("aew");
        assertTrue(resp);
        resp = tree5.search("2");
        // testa a busca em uma �rvore vazia
        assertEquals(null, tree4.search2(10));
    }

    /**
     * Test insert.
     */
    @Test
    public void testInsert() {
        int[] keys = {
                8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15
        };
        // insere elementos de 1 a 15 de forma que a �rvore fique completa
        for (int i : keys) {
            tree4.insert2(i);
        }
    }

    /**
     * Test size.
     */
    @Test
    public void testSize() {
        assertEquals(5, tree1.size());
        tree1.insert2(5);
        assertEquals(SIZE + 1, tree1.size());

        assertEquals(5, tree2.size());
        tree2.insert2(5);
        assertEquals(SIZE + 1, tree2.size());

        assertEquals(7, tree3.size());

        assertEquals(0, tree4.size());
        tree4.insert2(0);
        assertEquals(1, tree4.size());
    }

}
