public class RBTree<T extends Comparable<T>> {

    protected RBTreeElem<T> root, wartownik;
    // zapis w kolejnosći preorder

    public RBTree() {
        wartownik = new RBTreeElem<>();
        wartownik.color = 'B';
        wartownik.parent = wartownik;
        wartownik.left = wartownik;
        wartownik.right = wartownik;
        root = wartownik;
    }

    public void leftRotate(RBTreeElem<T> elem) {
        RBTreeElem<T> temp, temp2;
        temp = elem.right;
        if (temp != wartownik) {
            temp2 = elem.parent;
            elem.right = temp.left;
            if (elem.right != wartownik) elem.right.parent = elem;

            temp.left = elem;
            temp.parent = temp2;
            elem.parent = temp;

            if (temp2 != wartownik) {
                if (temp2.left == elem)
                    temp2.left = temp;
                else
                    temp2.right = temp;
            } else root = temp;
        }
    }

    public void rightRotate(RBTreeElem<T> elem) {
        RBTreeElem<T> temp, temp2;
        temp = elem.left;
        if (temp != wartownik) {
            temp2 = elem.parent;
            elem.left = temp.right;
            if (elem.left != wartownik) elem.left.parent = elem;

            temp.right = elem;
            temp.parent = temp2;
            elem.parent = temp;

            if (temp2 != wartownik) {
                if (temp2.left == elem)
                    temp2.left = temp;
                else
                    temp2.right = temp;
            } else root = temp;
        }
    }

    public void insert(T elem) {
        RBTreeElem<T> temp = new RBTreeElem<>();
        temp.left = wartownik;
        temp.right = wartownik;
        temp.parent = root;
        temp.elem = elem;

        if (temp.parent == wartownik)
            root = temp;
        else
            searchForLeafToReplace(temp, elem);

        temp.color = 'R';

        fixInsert(temp);

        root.color = 'B';
    }

    private void searchForLeafToReplace(RBTreeElem<T> elem, T key) {
        while (true) {
            if (elem.parent.elem.compareTo(key) > 0) {
                if (elem.parent.left == wartownik) {
                    elem.parent.left = elem;
                    break;
                }
                elem.parent = elem.parent.left;
            } else {
                if (elem.parent.right == wartownik) {
                    elem.parent.right = elem;
                    break;
                }
                elem.parent = elem.parent.right;
            }
        }
    }

    private void fixInsert(RBTreeElem<T> elem) {
        RBTreeElem<T> elem2;
        while (elem != root && elem.parent.color == 'R') {
            if (elem.parent == elem.parent.parent.left) {

                elem2 = elem.parent.parent.right;

                if (case1(elem, elem2)) continue;
                case2(elem, true);
                case3(elem, true);
                break;
            } else {

                elem2 = elem.parent.parent.left;

                if (case1(elem, elem2)) continue;
                case2(elem, false);
                case3(elem, false);
                break;
            }
        }
    }

    private boolean case1(RBTreeElem<T> elem, RBTreeElem<T> elem2) {
        if (elem2.color == 'R') {
            elem.parent.color = 'B';
            elem2.color = 'B';
            elem.parent.parent.color = 'R';
            elem = elem.parent.parent;
            return true;
        }
        return false;
    }

    private void case2(RBTreeElem<T> elem, boolean isLeft) {
        if (isLeft) {
            if (elem == elem.parent.right) {
                elem = elem.parent;
                leftRotate(elem);
            }
        } else {
            if (elem == elem.parent.left) {
                elem = elem.parent;
                rightRotate(elem);
            }
        }
    }

    private void case3(RBTreeElem<T> elem, boolean isLeft) {
        if (isLeft) {
            elem.parent.color = 'B';
            elem.parent.parent.color = 'R';
            rightRotate(elem.parent.parent);
        } else {
            elem.parent.color = 'B';
            elem.parent.parent.color = 'R';
            leftRotate(elem.parent.parent);
        }
    }

    public RBTreeElem<T> successor(RBTreeElem<T> elem) {
        RBTreeElem<T> temp;

        if (elem != wartownik) {
            if (elem.right != wartownik) return min(elem.right);
            else {
                temp = elem.parent;
                while ((temp != wartownik) && (elem == temp.right)) {
                    elem = temp;
                    temp = temp.parent;
                }
                return temp;
            }
        }
        return wartownik;
    }

    public void printSuccessor(T element){
        RBTreeElem<T> elem = find(element);
        elem =  successor(elem);
        if(elem.elem != null)
            System.out.println(elem.elem);
        else
            System.out.println("");
    }

    public RBTreeElem<T> min(RBTreeElem<T> elem) {
        if (elem != wartownik)
            while (elem.left != wartownik) elem = elem.left;
        return elem;
    }

    public void min() {

        if (root == null) {
            System.out.println("");
            return;
        }

        RBTreeElem<T> elem = root;
        if (elem != wartownik)
            while (elem.left != wartownik) elem = elem.left;

        System.out.print(elem.elem);
    }

    public void max() {

        if (root == null) {
            System.out.println("");
            return;
        }

        RBTreeElem<T> elem = root;
        if (elem != wartownik)
            while (elem.right != wartownik) elem = elem.right;

        System.out.print(elem.elem);
    }

    public void delete(RBTreeElem<T> elem) {
        RBTreeElem<T> temp, temp2;
        temp = new RBTreeElem<>();
        temp2 = new RBTreeElem<>();

        deleteWithoutFix(elem, temp, temp2);
        fixDelete(temp, temp2);

        temp2.color = 'B';
    }

    public void deleteFind(T element) {
        RBTreeElem<T> elem =find(element);
        if(elem!=wartownik)
            delete(elem);
    }

    private void deleteWithoutFix(RBTreeElem<T> elem, RBTreeElem<T> temp, RBTreeElem<T> temp2) {
        if ((elem.left == wartownik) || (elem.right == wartownik))
            temp = elem;
        else
            temp = successor(elem);

        if (temp.left != wartownik)
            temp2 = temp.left;
        else
            temp2 = temp.right;

        temp2.parent = temp.parent;

        if (temp.parent == wartownik)
            root = temp2;
        else if (temp == temp.parent.left)
            temp.parent.left = temp2;
        else
            temp.parent.right = temp2;

        if (temp != elem)
            elem.elem = temp.elem;
    }

    private void fixDelete(RBTreeElem<T> elem, RBTreeElem<T> elem2) {
        RBTreeElem<T> temp;
        if (elem.color == 'B')
            while ((elem2 != root) && (elem2.color == 'B'))
                if (elem2 == elem2.parent.left) {
                    temp = elem2.parent.right;

                    fixCase1(temp, elem2, true);

                    if (fixCase2(temp, elem2))
                        continue;

                    fixCase3(temp, elem2, true);

                    fixCase4(temp, elem2, true);
                } else {
                    temp = elem2.parent.left;

                    fixCase1(temp, elem2, false);

                    if (fixCase2(temp, elem2))
                        continue;

                    fixCase3(temp, elem2, false);

                    fixCase4(temp, elem2, false);

                }
    }

    private void fixCase1(RBTreeElem<T> elem, RBTreeElem<T> elem2, boolean isLeft) {
        if (isLeft) {
            if (elem.color == 'R') {
                elem.color = 'B';
                elem2.parent.color = 'R';
                leftRotate(elem2.parent);
                elem = elem2.parent.right;
            }
        } else {
            if (elem.color == 'R') {
                elem.color = 'B';
                elem2.parent.color = 'R';
                rightRotate(elem2.parent);
                elem = elem2.parent.left;
            }
        }
    }

    private boolean fixCase2(RBTreeElem<T> elem, RBTreeElem<T> elem2) {
        if ((elem.left.color == 'B') && (elem.right.color == 'B')) {
            elem.color = 'R';
            elem2 = elem2.parent;
            return true;
        }
        return false;
    }

    private void fixCase3(RBTreeElem<T> elem, RBTreeElem<T> elem2, boolean isLeft) {
        if (isLeft) {
            if (elem.right.color == 'B') {
                elem.left.color = 'B';
                elem.color = 'R';
                rightRotate(elem);
                elem = elem2.parent.right;
            }
        } else {
            if (elem.left.color == 'B') {
                elem.right.color = 'B';
                elem.color = 'R';
                leftRotate(elem);
                elem = elem2.parent.left;
            }
        }
    }

    private void fixCase4(RBTreeElem<T> elem, RBTreeElem<T> elem2, boolean isLeft) {
        if (isLeft) {
            elem.color = elem2.parent.color;
            elem2.parent.color = 'B';
            elem.right.color = 'B';
            leftRotate(elem2.parent);
            elem2 = root;
        } else {
            elem.color = elem2.parent.color;
            elem2.parent.color = 'B';
            elem.left.color = 'B';
            rightRotate(elem2.parent);
            elem2 = root;
        }
    }

    public void inorder() {
        if (root != wartownik)
            toString(root);
        else
            System.out.print("");
        System.out.println();
    }

    public void toString(RBTreeElem<T> elem) {

        RBTreeElem<T> temp = elem;
        {
            if (temp != wartownik) {
                toString(temp.left);
                System.out.print(temp.elem + " ");
                toString(temp.right);
            }
        }
    }

    public void printFind(T elem){
        if(find(elem)!=wartownik)
            System.out.println("1");
        else
            System.out.println("0");
    }

    public RBTreeElem<T> find(T elem) {

        if (root == wartownik) {
            return root;
        } else {
            RBTreeElem<T> temp = root;
            while (true) {
                if (elem.compareTo(temp.elem) == 0) {
                    return temp;
                }
                if (elem.compareTo(temp.elem) < 0) {
                    if (temp.left == wartownik) {
                        return temp.left;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == wartownik) {
                        return temp.right;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
    }
}






