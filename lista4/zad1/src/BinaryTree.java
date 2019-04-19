public class BinaryTree<T extends Comparable<T>> {

    protected TreeElem<T> root;

    public BinaryTree() {
        root = null;
    }


    public void insert(T elem) {

        if (root == null) {
            root = new TreeElem<>(elem);
        } else {
            TreeElem<T> temp = root;
            while (true) {
                if (elem.compareTo(temp.elem) > 0) {
                    if (temp.right == null) {
                        TreeElem<T> X = new TreeElem<>(elem);
                        temp.right = X;
                        X.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                } else if (elem.compareTo(temp.elem) < 0) {
                    if (temp.left == null) {
                        TreeElem<T> X = new TreeElem<>(elem);
                        temp.left = X;
                        X.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (elem.compareTo(temp.elem) == 0) {
                    break;
                }
            }
        }
    }

    public void inorder() {
        if (root != null)
            toString(root);
        else
            System.out.print("");
        System.out.println();
    }

    public void toString(TreeElem<T> elem) {

        TreeElem<T> temp = elem;
        {
            if (temp != null) {
                toString(temp.left);
                System.out.print(temp.elem + " ");
                toString(temp.right);
            }
        }
    }

    public void delete(T element) {

        TreeElem<T> elem = find(element);
        TreeElem<T> temp, temp2;

        if (elem == null)
            return;

        if ((elem.left == null) || (elem.right == null))
            temp = elem;
        else
            temp = successor(elem);

        if (temp.left != null)
            temp2 = temp.left;
        else
            temp2 = temp.right;

        if (temp2 != null)
            temp2.parent = temp.parent;

        if (temp.parent == null)
            root = temp2;

        else if (temp == temp.parent.left)
            temp.parent.left = temp2;
        else
            temp.parent.right = temp2;

        if (temp != elem)
            elem.elem = temp.elem;
    }

    public void printFind(T elem) {
        if (find(elem) != null)
            System.out.println("1");
        else
            System.out.println("0");
    }

    public TreeElem<T> find(T element) {
        if (root == null) {
            return root;
        } else {
            TreeElem<T> temp = root;
            while (true) {
                if (element.compareTo(temp.elem) == 0) {
                    return temp;
                }
                if ((temp.right == null) && (temp.left == null)) {
                    System.out.println("Podany element nie istnieje.");
                    return null;

                } else {
                    if (element.compareTo(temp.elem) < 0) {
                        if (temp.left != null) {
                            temp = temp.left;
                        }
                    } else {
                        if (temp.right != null) {
                            temp = temp.right;
                        }
                    }
                }
            }
        }
    }


    public TreeElem<T> min(TreeElem<T> elem) {

        TreeElem<T> temp = elem;

        while (temp.left != null)
            temp = temp.left;

        return temp;
    }

    public void min() {

        if (root == null) {
            System.out.println("");
            return;
        }

        TreeElem<T> temp = root;

        while (temp.left != null)
            temp = temp.left;

        System.out.println(temp.elem);
    }

    public void max() {

        if (root == null) {
            System.out.println("");
            return;
        }

        TreeElem<T> temp = root;

        while (temp.right != null)
            temp = temp.right;

        System.out.println(temp.elem);
    }


    public void PrintSuccessor(TreeElem<T> elem) {
        TreeElem<T> temp;

        if (elem != null) {
            if (elem.right != null)
                min(elem.right);
            else {
                temp = elem.parent;
                while ((temp != null) && (elem == temp.right) && (temp.parent != null)) {
                    elem = temp;
                    temp = temp.parent;
                }
                System.out.println(temp.elem);
            }
        }
        System.out.println("");
    }

    public TreeElem<T> successor(TreeElem<T> elem) {
        TreeElem<T> temp;

        if (elem != null) {
            if (elem.right != null)
                return min(elem.right);
            else {
                temp = elem.parent;
                while ((temp != null) && (elem == temp.right) && (temp.parent != null)) {
                    elem = temp;
                    temp = temp.parent;
                }
                return temp;
            }
        }
        return null;
    }

}
