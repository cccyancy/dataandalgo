package Tree.ThreadedBinaryTreeDemo;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "j");
        HeroNode node3 = new HeroNode(6, "s");
        HeroNode node4 = new HeroNode(8, "m");
        HeroNode node5 = new HeroNode(10, "k");
        HeroNode node6 = new HeroNode(14, "d");

        //二叉树创建，先不用递归

        root.setLeft(node2);
        root.setRight(node3);
        node2.setRight(node5);
        node2.setLeft(node4);
        node3.setLeft(node6);


        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试：以10号节点测试
        HeroNode leftNode = node4.getLeft();
        HeroNode rightNode = node4.getRight();
        System.out.println("8号节点的前驱节点为" + leftNode);
        System.out.println("8号节点的后驱节点为" + rightNode);

        //当线索化二叉树后，不能再使用原来的方法。
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();

    }

}

//定义threaded binary 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前驱节点
    private HeroNode pre = null;


    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            //循环的找到leftType == 1的节点，第一个找到就是8节点
            //后面随着遍历变化而变化，因为当leftType == 1时， 说明该节点是按照线索化
            //处理后的有效节点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历节点
            node = node.getRight();
        }
    }

    //编写对二叉树进行中序线索画的方法

    /**
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //如果node == null, 不能线索化
        if(node == null) {
            return;
        }
        //1. 先线索化左子树
        threadedNodes(node.getLeft());
        //2。 线索画当前节点
        //处理当前节点的前驱节点， 当前的左指针为空
        if(node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            //以8节点来理解
            //8节点的.left = null, 8节点的.leftType = 1
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点, pre的右指针为空
        if(pre != null &&  pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //！重要: 每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //3。 线索化右子树
        threadedNodes(node.getRight());

    }

    //删除这个极点
    public void delNode(int no) {
        if(root != null) {
            //如果只有一个root节点，这里立即判断root是不是就是要删除的节点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    //规划删除这个极点
    public void planDelNode(int no) {
        if(root != null) {
            //如果只有一个root节点，这里立即判断root是不是就是要删除的节点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.planDel(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if(this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if(root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}
//先创建HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//左右都默认为null
    private HeroNode right;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //说明
    //1。 如果leftType == 0 表示指向的是左子树， 如果为1， 则指向的是前驱节点
    //2。 如果rightType == 1 表示指向的是右子树， 如果是1， 则指向的是后继节点

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }


    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }



    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }
    //编写前序遍历的方法


    //递归删除节点
    //1. 如果删除的节点是叶子节点，则删除该节点
    // 2。 如果删除的节点是非叶子结点，则删除该子树
    public void delNode(int no) {
        //思路
        /*
        1。 因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
        2。 如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left = null; 并且就返回（结束递归并删除）
        3。 如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right = null; 并且返回（结束递归并删除）
        4。 如果第二部和第三步没有删除节点，那么我们就需要向左子树进行递归删除
        5。 如果第四步也没有删除节点，则应当向右子树进行递归删除
         */

        if(this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3. 如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right = null; 返回（结束递归并删除）
        if(this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4。 我们就需要向左子树进行递归删除
        if(this.left != null) {
            this.left.delNode(no);//不用return，万一左子树没有删除的节点
        }
        //5。 则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //planned del
    public void planDel(int no) {
        //非叶子节点A只有一个子节点B，则子节点B替代节点A
        //如果A有子节点B和C，让左边的B替代A

        if(this.left != null && this.left.no == no) {
            if(this.left.left != null) {
                this.left = this.left.left;
            }
            if(this.left.left == null && this.left.right != null) {
                this.left = this.left.right;
            }

            return;
        }

        if(this.right != null && this.right.no == no) {
            if(this.right.left != null) {
                this.right = this.right.left;
            }
            if(this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
            }
            return;
        }
        if(this.left != null) {
            this.left.planDel(no);//不用return，万一左子树没有删除的节点
        }
        //5。 则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.planDel(no);
        }
    }





    public void preOrder() {
        System.out.println(this);
        //递归向左子树 前序
        if(this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null) {
            this.right.preOrder();;
        }
    }
    //中序遍历
    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder() {
        if(this.left != null) {
            this.left.postOrder();
        }
        if(this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     *
     * @param no 查找number
     * @return 如果找到就返回Node， 没有找到就返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        if(this.no == no) {
            return this;
        }
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;

        if(this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null) {//说明在左子树找到
            return resNode;
        }

        //左子树没找到就找右子树
        //当前的节点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if(this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null) {//表示左节点找到
            return resNode;
        }
        System.out.println("进入中序查找");

        //如果没有找到，就和当前节点比较，如果是则返回当前节点
        if(this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if(this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;
        //判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null) {//说明左子树找到
            return resNode;
        }
        //如果左子树没有找到，则向右后序递归
        if(this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null) {//如果左右都没有找到
            return resNode;
        }
        System.out.println("进入后序查找");

        if(this.no == no) {//比较根
            return this;
        }
        return resNode;
    }
}


