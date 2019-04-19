//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

class TreeElem<T extends Comparable<T>> {
    T elem;
    protected TreeElem<T> left;
    protected TreeElem<T> right;
    protected TreeElem<T> parent;

    TreeElem(T var1) {
        this.elem = var1;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
