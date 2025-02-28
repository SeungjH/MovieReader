// --== CS400 Spring 2023 File Header Information ==--
// Name: <Hyeonmin Yang>
// Email: <hyang486@wisc.edu>
// Team: <BJ>
// TA: <Naman Gupta>
// Lecturer: <CS400 Lecture002>
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements RedBlackTreeInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[]) new Node[3];

        public Node(T data) {
            this.data = data;
        }

        public int blackHeight = 0;

        /**
         * @return true when this node has a parent and is the right child of
         *         that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * This method is working for resoloving property violation of RBT. newNode's
     * default blackHeight
     * is red. So if newNode meet the parent who's blackHeight is red, with this
     * method we can resolve the
     * violation of property with rotating and color swap.
     * Basically we have three cases
     * case 1 - newNode's parent is red node, it's sibling is black node or null,
     * it's parent is black node.
     * And newNode and parent is same side child of each's parent
     * case 2 - newNode's parent is red node, it's sibling is black node or null,
     * it's parent is black node.
     * And newNode and parent is different side of each's parent
     * case 3 - newNode's parent is red, it's sibling is red node, it's parent is
     * black
     * And new Node and parent can be same or different side of each's parent
     * We we resolving case 2 and 3 we use this method recursivly.
     * After resolving violation, each collision, this method keep setting root's
     * color to black.
     * And with resolving violation we can make RBT have same blackHeigth(number of
     * black node) on every side.
     * Every condition write if parent's sibling is null first, because if not it
     * can
     * occure NullpointException.
     */
//    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
//        // This is for the base case (when the newnode is black node or it's paretn is
//        // null or it's parent's parent is null
//        // escape this method with return command)
//        if (newNode.blackHeight == 1 || newNode.context[0] == null
//                || newNode.context[0].context[0] == null) {
//            return;
//        } // If the newNode's parent is red resolve the violation
//        else if (newNode.context[0].blackHeight == 0) {
//            // When newNode is rightChild
//            if (newNode.isRightChild()) {
//                // for the case 1 (newNode : rightChild , parent : rightChild)
//                if (newNode.context[0].isRightChild() && (newNode.context[0].context[0].context[1] == null
//                        || newNode.context[0].context[0].context[1].blackHeight == 1)) {
//                    // Do left rotation with newNode's parent and its parent
//                    rotate(newNode.context[0], newNode.context[0].context[0]);
//                    // Do color swap (change newNode's parent to black node and parent's leftChild
//                    // to red node)
//                    newNode.context[0].blackHeight = 1;
//                    newNode.context[0].context[1].blackHeight = 0;
//                } // for the case 2 (newNode : rightChild , parent : leftChild)
//                else if (!newNode.context[0].isRightChild() && (newNode.context[0].context[0].context[2] == null
//                        || newNode.context[0].context[0].context[2].blackHeight == 1)) {
//                    // Do left rotation with newNode and its parent
//                    rotate(newNode, newNode.context[0]);
//                    // After change it's form then resolve this violation with case 1.
//                    enforceRBTreePropertiesAfterInsert(newNode.context[1]);
//                } // for the case3 (newNode : rightchild , parent leftChild)
//                else if (!newNode.context[0].isRightChild()
//                        && newNode.context[0].context[0].context[2].blackHeight == 0) {
//                    // Do color swap
//                    newNode.context[0].blackHeight = 1;
//                    newNode.context[0].context[0].context[2].blackHeight = 1;
//                    newNode.context[0].context[0].blackHeight = 0;
//                    // Consider the case if the new Node's grandparent is root, set root's color to
//                    // black
//                    this.root.blackHeight = 1;
//                    // Resolve the violation with grandparent (this is the case that grandparent is
//                    // not root)
//                    enforceRBTreePropertiesAfterInsert(newNode.context[0].context[0]);
//                } // for the case 3(newNode : rightChild , parent : rightChild)
//                else if (newNode.context[0].isRightChild()
//                        && newNode.context[0].context[0].context[1].blackHeight == 0) {
//                    // Do color swap
//                    newNode.context[0].blackHeight = 1;
//                    newNode.context[0].context[0].context[1].blackHeight = 1;
//                    newNode.context[0].context[0].blackHeight = 0;
//                    // Resolve the violation with grandparent (this is the case that grandparent is
//                    // not root)
//                    this.root.blackHeight = 1;
//                    // Resolve the violation with grandparent
//                    enforceRBTreePropertiesAfterInsert(newNode.context[0].context[0]);
//                }
//                // newNode is leftChild
//            } else if (!newNode.isRightChild()) {
//                // for the case 1 (newNode : leftChild , parent: leftChild)
//                if (!newNode.context[0].isRightChild() && (newNode.context[0].context[0].context[2] == null
//                        || newNode.context[0].context[0].context[2].blackHeight == 1)) {
//                    // Do right rotation with newNode's parent and its parent
//                    rotate(newNode.context[0], newNode.context[0].context[0]);
//                    // Do color Swap
//                    newNode.context[0].blackHeight = 1;
//                    newNode.context[0].context[2].blackHeight = 0;
//                } // for the case 2 (newNode , leftChild : parent :righChild)
//                else if (newNode.context[0].isRightChild() && (newNode.context[0].context[0].context[1] == null
//                        || newNode.context[0].context[0].context[1].blackHeight == 1)) {
//                    // Do right rotation with newNode and its parent
//                    rotate(newNode, newNode.context[0]);
//                    // After change it's form then resolve this violation with case 1.
//                    enforceRBTreePropertiesAfterInsert(newNode.context[2]);
//                } // for the case 3 (newNode : leftChild , parent : leftChild)
//                else if (!newNode.context[0].isRightChild()
//                        && newNode.context[0].context[0].context[2].blackHeight == 0) {
//                    // Do color swap
//                    newNode.context[0].blackHeight = 1;
//                    newNode.context[0].context[0].context[2].blackHeight = 1;
//                    newNode.context[0].context[0].blackHeight = 0;
//                    // Consider the case if the new Node's grandparent is root, set root's color to
//                    // black
//                    this.root.blackHeight = 1;
//                    // Resolve the violation with grandparent (this is the case that grandparent is
//                    // not root)
//                    enforceRBTreePropertiesAfterInsert(newNode.context[0].context[0]);
//                } // for the case 3(newNode : leftchild , parent : rightChild)
//                else if (newNode.context[0].isRightChild()
//                        && newNode.context[0].context[0].context[1].blackHeight == 0) {
//                    // Do color swap
//                    newNode.context[0].blackHeight = 1;
//                    newNode.context[0].context[0].context[1].blackHeight = 1;
//                    newNode.context[0].context[0].blackHeight = 0;
//                    // Consider the case if the new Node's grandparent is root, set root's color to
//                    // black
//                    this.root.blackHeight = 1;
//                    // Resolve the violation with grandparent
//                    enforceRBTreePropertiesAfterInsert(newNode.context[0].context[0]);
//                }
//            }
//        }
//
//    }
    protected void enforceRBTreeProtertiesAfterInsert(Node<T> newRedNode){
        //addressing nullpointerexception
        if(newRedNode == null || newRedNode.context[0] == null ||
            newRedNode.context[0].context[0] == null){
            root.blackHeight = 1;

        }
        else{
            // if the newRedNode's parent is red
            if (newRedNode.context[0].blackHeight == 0) {
                // case 1: aunt is black or null
                // solution: rotate (depends on the newRedNode's location), rotate again, and
                // color swap
                //node instance (node's parent's parent)
                // aunt is at right
                if (newRedNode.context[0].context[0].context[2] == null || newRedNode.context[0].
                    context[0].context[2].blackHeight == 1 ) {
                    // rotate if this is true
                    if (newRedNode.context[0].context[2] == newRedNode) {
                        Node<T> newN = newRedNode.context[0];
                        rotate(newRedNode, newRedNode.context[0]);

                        rotate(newRedNode, newRedNode.context[0]);
                        colorSwapHelper(newRedNode, newRedNode.context[0]);
                    } else {
                        Node<T> newN = newRedNode.context[0];
                        rotate(newN, newN.context[0]);
                        colorSwapHelper(newN.context[2], newN);
                        root.blackHeight = 1;
                    }
                }

                // aunt is at left
                else if (newRedNode.context[0].context[0].context[1] == null || newRedNode.context[0].context[0].context[1].blackHeight == 1) {
                    if (newRedNode.context[0].context[1] == newRedNode) {
                        // System.out.println(newRedNode.data.toString());
                        rotate(newRedNode, newRedNode.context[0]);

                        rotate(newRedNode, newRedNode.context[0]);
                        colorSwapHelper(newRedNode, newRedNode.context[0]);
                    } else {
                        Node<T> newN = newRedNode.context[0];
                        rotate(newN, newN.context[0]);
                        colorSwapHelper(newN.context[1], newN);
                        root.blackHeight = 1;
                    }
                }
                // case 2: aunt is red
                // solution: recolor
                // aunt is at right or left
                else if (newRedNode.context[0].context[0].context[2] != null && newRedNode.context[0]
                    .context[0].context[1] != null) {
                    // aunt is at right
                    if (newRedNode.context[0].context[0].context[2].blackHeight == 0) {
                        newRedNode.context[0].blackHeight = 1;
                        newRedNode.context[0].context[0].blackHeight = 0;
                        newRedNode.context[0].context[0].context[2].blackHeight = 1;
                        root.blackHeight = 1;
                    }
                    // aunt is at left
                    if (newRedNode.context[0].context[0].context[1].blackHeight == 0) {
                        newRedNode.context[0].blackHeight = 1;
                        newRedNode.context[0].context[0].blackHeight = 0;
                        newRedNode.context[0].context[0].context[1].blackHeight = 1;
                        root.blackHeight = 1;
                    }
                    // case 2-1: after recoloring, the parent is red and has case 1 violation
                    // with the parent's parent
                    if (newRedNode.context[0].context[0].blackHeight == 0) {
                        enforceRBTreeProtertiesAfterInsert(newRedNode.context[0].context[0]);
                    }
                    //enforceRBTreeProtertiesAfterInsert(newRedNode.context[0].context[0]);
                }
            }
        }
    }
    /**
     * childN's color will be changed to parent's color after rotating and parent's
     * color
     * will be changed to childN's previous color
     *
     * @param
     */
    private void colorSwapHelper(Node<T> childN, Node<T> parentN) {
        int temp = childN.blackHeight;
        childN.blackHeight = parentN.blackHeight;
        parentN.blackHeight = temp;

    }

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * 
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null)
            throw new NullPointerException(
                    "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            this.root = newNode;
            this.root.blackHeight = 1;
            size++;
            return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        enforceRBTreeProtertiesAfterInsert(newNode);
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        enforceRBTreeProtertiesAfterInsert(newNode);
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2];
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * 
     * @param child  is the node being rotated from child to parent position
     *               (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *               (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *                                  node references are not initially
     *                                  (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if (parent == null) {
            throw new IllegalArgumentException("parent must not be null");
        }
        // if child is null, then throw an IllegalArgumentException
        if (child == null) {
            throw new IllegalArgumentException("No realtion nodes (child is null)");
        }

        if (!child.context[0].equals(parent)) {
            throw new IllegalArgumentException("No realtion nodes");
        }

        // Case 1 : if the parent node is root
        if (parent == root) {

            // 1-1 child node (left child)
            if (!child.isRightChild()) {
                root = child;
                parent.context[1] = child.context[2];
                child.context[2] = parent;
                parent.context[0] = root;
            }
            // 1-2 child node (right child)
            else if (child.isRightChild()) {
                root = child;
                parent.context[2] = child.context[1];
                child.context[1] = parent;
                parent.context[0] = root;
            } else {
                throw new IllegalArgumentException("Provided child and parent node are not initially related");
            }
        }
        // Case 2 : if the parent node is not root
        else {
            // 2-1 child node is leftchild
            if (!child.isRightChild()) {
                // if parent is rightside node
                if (parent.isRightChild()) {
                    parent.context[0].context[2] = child;
                }
                // if parent is leftside node
                else {
                    parent.context[0].context[1] = child;
                }
                // make rotation
                parent.context[1] = child.context[2];
                child.context[0] = parent.context[0];
                parent.context[0] = child;
                child.context[2] = parent;
            }
            // 2-2 child node is rightchild
            else if (child.isRightChild()) {
                // if parent is leftside node
                if (!parent.isRightChild()) {
                    parent.context[0].context[1] = child;
                }
                // if parent is leftside node
                else {
                    parent.context[0].context[2] = child;
                }

                // make rotation
                parent.context[2] = child.context[1];
                child.context[0] = parent.context[0];
                parent.context[0] = child;
                child.context[1] = parent;
            } else {
                throw new IllegalArgumentException("Provided child and parent node are not initially related");
            }

        }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * 
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * 
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * 
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException(
                        "The following value is not in the tree and cannot be deleted: " + data.toString());
            }
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
            }
            this.size--;
            return true;
        }
    }

    /**
     * Checks whether the tree contains the value *data*.
     * 
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Helper method that will replace a node with a replacement node. The
     * replacement
     * node may be null to remove the node from the tree.
     * 
     * @param nodeToReplace   the node to replace
     * @param replacementNode the replacement for the node (may be null)
     */
    protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
        if (nodeToReplace == null) {
            throw new NullPointerException("Cannot replace null node.");
        }
        if (nodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementNode != null)
                replacementNode.context[0] = null;
            this.root = replacementNode;
        } else {
            // set the parent of the replacement node
            if (replacementNode != null)
                replacementNode.context[0] = nodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (nodeToReplace.isRightChild()) {
                nodeToReplace.context[0].context[2] = replacementNode;
            } else {
                nodeToReplace.context[0].context[1] = replacementNode;
            }
        }
    }

    /**
     * Helper method that will return the inorder successor of a node with two
     * children.
     * 
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a steop to the right
        Node<T> current = node.context[2];
        while (true) {
            // then go left as often as possible to find the successor
            if (current.context[1] == null) {
                // we found the successor
                return current;
            } else {
                current = current.context[1];
            }
        }
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
     * 
     * @return the node that contains the data, or null of no such node exists
     */
    protected Node<T> findNodeWithData(T data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null node and did not find data, so it's not in the tree
        return null;
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * 
     * @return string containing the ordered values of this tree (in-order
     *         traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if (!nodeStack.isEmpty() || popped.context[2] != null)
                        sb.append(", ");
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * 
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while (!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if (next.context[1] != null)
                    q.add(next.context[1]);
                if (next.context[2] != null)
                    q.add(next.context[2]);
                sb.append(next.data.toString());
                if (!q.isEmpty())
                    sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    /**
     * This method is working for show the subtree data based on user input
     * This algorithm is using inorder traversal, first store the every node
     * to ArrayList then with iterator, store specific nodes that is include
     * the range to result ArrayList.
     * 
     * @param data1 first node that user want to see
     * @param data2 the last node that user want to see
     * @return The ArrayList that contains specific nodes in data1 ~ data2
     */
    @Override
    public ArrayList<T> SubTreeData(T data1, T data2) throws NullPointerException {
        // if the datas are null then throw exception
        if (data1 == null || data2 == null) {
            throw new NullPointerException("Data is null");
        }

        // ArrayList for store all ndoes
        ArrayList<T> inorder = new ArrayList<>();
        // ArrayList for store specific nodes(for return)
        ArrayList<T> result = new ArrayList<>();
        Node<T> current = this.root;
        Node<T> tracer;
        // inorder traversal with adding nodes in inorder ArrayList
        while (current != null) {
            if (current.context[1] == null) {
                inorder.add(current.data);
                current = current.context[2];
            } else {
                tracer = current.context[1];
                while (tracer.context[2] != null && tracer.context[2] != current) {
                    tracer = tracer.context[2];
                }
                if (tracer.context[2] == null) {
                    tracer.context[2] = current;
                    current = current.context[1];
                } else {
                    tracer.context[2] = null;
                    inorder.add(current.data);
                    current = current.context[2];
                }
            }
        }
        // with linear searching add specific nodes that are in the range to result
        // ArrayList.
        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i).compareTo(data1) >= 0 && inorder.get(i).compareTo(data2) <= 0) {
                result.add(inorder.get(i));
            }
        }
        return result;
    }

    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * 
     * @param args
     */
    public static void main(String[] args) {
    }

}
