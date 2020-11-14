package ru.arg.skillsmart.treenode;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;
import ru.arg.skillsmart.treenode.fortests.SimpleTree;
import ru.arg.skillsmart.treenode.fortests.SimpleTreeNode;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SimpleTreeNodeTest {

    @Test
    public void addChild() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);

        assertEquals(9, (int) tree.Root.NodeValue);
        assertEquals(1, tree.Root.Children.size());
        assertEquals(2, child4.Children.size());
        System.out.println(child4.Children);
    }

    @Test
    public void deleteNode() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.DeleteNode(child4);

        assertEquals(9, (int) tree.Root.NodeValue);
        assertEquals(2, tree.Root.Children.size());
        assertEquals(0, child4.Children.size());
        assertNull(child4.Parent);
        assertNull(child4.NodeValue);
        System.out.println(tree.Root.Children);
    }
    @Test
    public void deleteNode2() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);
        tree.DeleteNode(child6);

        List<SimpleTreeNode<Integer>> list= tree.GetAllNodes();

        for (SimpleTreeNode<Integer> s : list) {
            System.out.println(s.NodeValue);
        }
    }
    @Test
    public void deleteNode3() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);

        List<SimpleTreeNode<Integer>> list= tree.GetAllNodes();

        for (SimpleTreeNode<Integer> s : list) {
            System.out.println(s.NodeValue);
        }
    }

    @Test
    public void getAllNodes() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);

        List<SimpleTreeNode<Integer>> list= tree.GetAllNodes();

        for (SimpleTreeNode<Integer> s : list) {
            System.out.println(s.NodeValue);
        }
    }

    @Test
    public void findNodesByValue() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(111, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(111, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(111, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);

        List<SimpleTreeNode<Integer>> list= tree.FindNodesByValue(111);

        for (SimpleTreeNode<Integer> s : list) {
            System.out.println(s.NodeValue);
        }
    }

    @Test
    public void moveNode() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);
        tree.MoveNode(child4, child17);

        assertEquals(child17, child4.Parent);

        List<SimpleTreeNode<Integer>> list= tree.GetAllNodes();

        for (SimpleTreeNode<Integer> s : list) {
            System.out.println(s.NodeValue);
        }
    }

    @Test
    public void count() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        assertEquals(4,tree.Count() );
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        assertEquals(6,tree.Count() );
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);

        assertEquals(9,tree.Count() );
        tree.DeleteNode(child3);
        assertEquals(8,tree.Count());
    }

    @Test
    public void leafCount() {
        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
        tree.AddChild(tree.Root, child4);
        tree.AddChild(tree.Root, child17);
        tree.AddChild(child17, child22);
        tree.AddChild(child4, child3);
        tree.AddChild(child4, child6);
        tree.AddChild(child6, child5);
        tree.AddChild(child6, child7);
        tree.AddChild(child22, child20);
        assertEquals(4,tree.LeafCount());
        tree.DeleteNode(child7);
        assertEquals(3,tree.LeafCount());
    }

//    @Test
//    void levelsCount() {
//        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(9, null));
//        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, tree.Root);
//        SimpleTreeNode<Integer> child17 = new SimpleTreeNode<>(17, tree.Root);
//        SimpleTreeNode<Integer> child22 = new SimpleTreeNode<>(22, child17);
//        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, child4);
//        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, child4);
//        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child6);
//        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child6);
//        SimpleTreeNode<Integer> child20 = new SimpleTreeNode<>(20, child22);
//        tree.AddChild(tree.Root, child4);
//        tree.AddChild(tree.Root, child17);
//        tree.AddChild(child17, child22);
//        tree.AddChild(child4, child3);
//        tree.AddChild(child4, child6);
//        tree.AddChild(child6, child5);
//        tree.AddChild(child6, child7);
//        tree.AddChild(child22, child20);
//        List<Integer> list= tree.LevelsCount();
//
//        for (Integer i : list) {
//            System.out.println(i);
//        }
//    }
//
//    @Test
//    void evenTrees() {
//        SimpleTree<Integer> tree = new SimpleTree<>(new SimpleTreeNode<>(1, null));
//        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, tree.Root);
//        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, tree.Root);
//        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, tree.Root);
//        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child2);
//        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child2);
//        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, child3);
//        SimpleTreeNode<Integer> child8 = new SimpleTreeNode<>(8, child6);
//        SimpleTreeNode<Integer> child9 = new SimpleTreeNode<>(9, child8);
//        SimpleTreeNode<Integer> child10 = new SimpleTreeNode<>(10, child8);
//        tree.AddChild(tree.Root, child2);
//        tree.AddChild(tree.Root, child3);
//        tree.AddChild(tree.Root, child6);
//        tree.AddChild(child2, child5);
//        tree.AddChild(child2, child7);
//        tree.AddChild(child3, child4);
//        tree.AddChild(child6, child8);
//        tree.AddChild(child8, child9);
//        tree.AddChild(child8, child10);
//
//        System.out.println(tree.EvenTrees());
//    }
}