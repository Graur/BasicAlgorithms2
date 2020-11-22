import java.util.*;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null
    int count;

    public BST(BSTNode<T> node) {
        Root = node;
        count = 1;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        // ищем в дереве узел и сопутствующую информацию по ключу
        BSTFind<T> bstFind = new BSTFind<>();
        if (Root != null) {
            BSTNode<T> node = searchNode(Root, key);
            bstFind.Node = node;
            if (node.NodeKey == key) {
                bstFind.NodeHasKey = true;
            } else {
                bstFind.NodeHasKey = false;
                bstFind.ToLeft = key < node.NodeKey;
            }
        }
        return bstFind;
    }

    BSTNode<T> searchNode(BSTNode<T> node, int key) {
        if (key < node.NodeKey && node.LeftChild != null) return searchNode(node.LeftChild, key);
        if (key > node.NodeKey && node.RightChild != null) return searchNode(node.RightChild, key);
        return node;
    }

    public boolean AddKeyValue(int key, T val) {
        // добавляем ключ-значение в дерево
        if (Root == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (bstFind.NodeHasKey) {
            return false; // если ключ уже есть
        }
        BSTNode<T> node = new BSTNode<>(key, val, bstFind.Node);
        if (bstFind.ToLeft) {
            bstFind.Node.LeftChild = node;
        } else bstFind.Node.RightChild = node;
        count++;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        if (!FindMax && FromNode.LeftChild != null) return FinMinMax(FromNode.LeftChild, false);
        if (FindMax && FromNode.RightChild != null) return FinMinMax(FromNode.RightChild, true);
        return FromNode;
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (!bstFind.NodeHasKey) {
            return false; // если узел не найден
        }
        BSTNode<T> node = bstFind.Node;
        //если у удаляемого узла 2 потомка, сначала находим преемника
        if (node.LeftChild != null && node.RightChild != null) {
            BSTNode<T> replace = FinMinMax(node.RightChild, false);

            // заменяем ссылки у родителя
            if (node.Parent != null && node.Parent.LeftChild == node) {
                node.Parent.LeftChild = replace;
            } else if (node.Parent != null && node.Parent.RightChild == node) {
                node.Parent.RightChild = replace;
            }
            replace.Parent = node.Parent;// переносим ссылку на родителя преемнику

            if (node.RightChild != replace) {
                replace.RightChild = node.RightChild;// переносим правого потомка к преемнику
                node.RightChild.Parent = replace;
            }
            replace.LeftChild = node.LeftChild;// переносим левого потомка к преемнику
            replace.LeftChild.Parent = replace;
            if (node == Root) {
                Root = replace;
            }

        } //если узел без потомков
        else if (node.LeftChild == null && node.RightChild == null) {
            if (node.Parent != null && node.Parent.LeftChild == node) {
                node.Parent.LeftChild = null;
            } else if (node.Parent != null && node.Parent.RightChild == node) {
                node.Parent.RightChild = null;
            }
            if (node == Root) {
                Root = null;
            }
        } else {
            //если у узла только один потомок
            if (node.RightChild != null) {
                // меняем ссылку у родителя
                if (node.Parent != null && node.Parent.LeftChild == node) {
                    node.Parent.LeftChild = node.RightChild;
                } else if (node.Parent != null && node.Parent.RightChild == node) {
                    node.Parent.RightChild = node.RightChild;
                }
                node.RightChild.Parent = node.Parent; // меняем ссылку на родителя
                if (node == Root) {
                    Root = node.RightChild;
                }
            } else {
                // меняем ссылку у родителя
                if (node.Parent != null && node.Parent.LeftChild == node) {
                    node.Parent.LeftChild = node.LeftChild;
                } else if (node.Parent != null && node.Parent.RightChild == node) {
                    node.Parent.RightChild = node.LeftChild;
                }
                node.LeftChild.Parent = node.Parent; // меняем ссылку на родителя
                if (node == Root) {
                    Root = node.LeftChild;
                }
            }
        }
        node = null;// удаляем узел
        count--;
        return true;
    }

    public int Count() {
        if (Root == null) return 0;
        return count; // количество узлов в дереве
    }

    public ArrayList<BSTNode<T>> WideAllNodes() {
        ArrayList<BSTNode<T>> list = new ArrayList<>();
        Queue<BSTNode<T>> queue = new ArrayDeque<>();
        BSTNode<T> node = Root;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            list.add(node);
            if (node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                queue.add(node.RightChild);
            }
        }
        return list;
    }

    public ArrayList<BSTNode<T>> DeepAllNodes(int order) {
        ArrayList<BSTNode<T>> list = new ArrayList<>();
        if (order == 0) {
            in_order(Root, list);
        } else if (order == 1) {
            post_order(Root, list);
        } else if (order == 2) {
            pre_order(Root, list);
        }
        return list;
    }

    public void in_order(BSTNode<T> node, ArrayList<BSTNode<T>> list) {
        if (node != null) {
            in_order(node.LeftChild, list);
            list.add(node);
            in_order(node.RightChild, list);
        }
    }

    public void post_order(BSTNode<T> node, ArrayList<BSTNode<T>> list) {
        if (node != null) {
            post_order(node.LeftChild, list);
            post_order(node.RightChild, list);
            list.add(node);
        }
    }

    public void pre_order(BSTNode<T> node, ArrayList<BSTNode<T>> list) {
        if (node != null) {
            list.add(node);
            pre_order(node.LeftChild, list);
            pre_order(node.RightChild, list);
        }
    }
}