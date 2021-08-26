package Tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrayBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
        System.out.println("~");
        arrayBinaryTree.infixOrder();
        System.out.println("~");
        arrayBinaryTree.postOrder();
    }


}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历

class ArrayBinaryTree {
    private int[] arr; //存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }

    public void infixOrder(){
        this.infixOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     *
     * @param index 数组的位置
     */
    public void preOrder(int index) {
        //如果数组为空，或者arr.length = 0;
        if(arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder(int index) {
        //如果数组为空，或者arr.length = 0;
        if(arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        //向左递归遍历
        if((index * 2 + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);

        //向右递归遍历
        if((index * 2 + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder(int index) {
        //如果数组为空，或者arr.length = 0;
        if(arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        //向左递归遍历
        if((index * 2 + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归遍历
        if((index * 2 + 2) < arr.length) {
            postOrder(2 * index + 2);
        }

        System.out.println(arr[index]);

    }
}
