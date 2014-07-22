
package core.bst;

import core.estrutura.Estrutura;

public class BSTImpl<K extends Comparable<? super K>, V> implements BST<K, V>, Estrutura {

    protected BSTNode<K, V> root;

    public BSTImpl() {
        root = new BSTNode<K, V>();
    }

    @Override
    public BSTNode<K, V> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public int height() {
        return heightAux(root);
    }

    private int heightAux(BSTNode<K, V> node) {
        int auxLeft = 0, auxRight = 0;

        if (node.isEmpty()) {
            return -1;
        }

        auxLeft = heightAux(node.left);
        auxRight = heightAux(node.right);

        if (auxLeft > auxRight) {
            return auxLeft + 1;
        } else {
            return auxRight + 1;
        }
    }

    @Override
    public K search2(K key) {
        return searchAux(key).getKey();
    }

    private BSTNode<K, V> searchAux(K key) {
        BSTNode<K, V> aux = root;

        while (!aux.isEmpty() && !key.equals(aux.getKey())) {
            if (key.compareTo(aux.getKey()) < 0) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }

        return aux;
    }

    @Override
    public void insert2(K key) {

        BSTNode<K, V> aux = this.root;
        if (isEmpty()) {
            aux.setKey(key);
            aux.setValue(null);
            aux.setLeft(new BSTNode<K, V>());
            aux.setRight(new BSTNode<K, V>());
            aux.left.parent = aux;
            aux.right.parent = aux;
        } else {

            while (!aux.isEmpty()) {
                if (key.compareTo(aux.getKey()) > 0) {
                    aux = aux.getRight();
                } else {
                    aux = aux.getLeft();
                }
            }

            aux.setKey(key);
            aux.setValue(null);
            aux.left = new BSTNode<K, V>();
            aux.right = new BSTNode<K, V>();
            aux.left.parent = aux;
            aux.right.parent = aux;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode<K, V> node) {
        int result = 0;
        if (!node.isEmpty()) {
            result = 1 + size(node.left) + size(node.right);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean search(String word) {

        return search2((K) word) == null ? false : true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void insert(String word) {

        insert2((K) word);

    }

}
