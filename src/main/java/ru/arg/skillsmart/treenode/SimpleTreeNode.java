package ru.arg.skillsmart.treenode;
import java.util.*;
import java.util.function.Function;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null
    private int count;

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
        count = 1;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        // ваш код добавления нового дочернего узла существующему ParentNode
        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
        count++;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete
        List<SimpleTreeNode<T>> listNodesToDelete = FindNodesByValue(NodeToDelete.NodeValue);
        listNodesToDelete.forEach(node -> {
            SimpleTreeNode<T> parent = node.Parent;
            parent.Children.remove(node);
            node.Parent = null;
            node.NodeValue = null;
            List<SimpleTreeNode<T>> children = node.Children;
            if (children != null && !children.isEmpty()) {
                children.forEach(child -> {
                    MoveNode(child, parent);
                });
                node.Children.clear();
            }
            count--;
        });
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        // ваш код выдачи всех узлов дерева в определённом порядке
        List<SimpleTreeNode<T>> resultList = new ArrayList<>();
        applyFunctionToLeaf(this.Root, resultList::add);
        return resultList;
    }

    private void applyFunctionToLeaf(SimpleTreeNode<T> root, Function<SimpleTreeNode<T>, Boolean> function) {
        if (root.Children != null && !root.Children.isEmpty()) {
            root.Children.forEach(child -> applyFunctionToLeaf(child, function));
        }
        function.apply(root);
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        List<SimpleTreeNode<T>> resultList = new ArrayList<>();
        applyFunctionToLeaf(this.Root, (leaf) -> {
            boolean isEqual = leaf.NodeValue.equals(val);
            if (leaf.NodeValue.equals(val)) {
                resultList.add(leaf);
            }
            return isEqual;
        });
        return resultList;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        if (OriginalNode != null && !OriginalNode.equals(this.Root)) {
            OriginalNode.Parent = NewParent;
            if (OriginalNode.Children == null || OriginalNode.Children.isEmpty()) {
                OriginalNode.Children = new ArrayList<>();
            }
            NewParent.Children.add(OriginalNode);
        }
    }

    public int Count() {
        // количество всех узлов в дереве
        return this.count;
    }

    public int LeafCount() {
        // количество листьев в дереве
        if (count == 0) {
            return 0;
        }
        return (int) GetAllNodes().stream()
                .filter(node -> node.Children == null || node.Children.isEmpty())
                .count();
    }

    public int levelsCount() {
        // количество уровней в дереве
        final int[] leafLevelsCount = {0};
        applyFunctionToLeaf(this.Root, (leaf) -> {
            var root = leaf.Parent;
            int large = 1;
            while (root != null) {
                large ++;
                root = root.Parent;
            }
            boolean isLargestBranch = large > leafLevelsCount[0];
            if (isLargestBranch) {
                leafLevelsCount[0] = large;
            }
            return isLargestBranch;
        });
        return leafLevelsCount[0];
    }
}
