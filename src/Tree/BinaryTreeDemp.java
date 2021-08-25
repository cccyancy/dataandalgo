package Tree;

public class BinaryTreeDemp {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "Song");
        HeroNode node2 = new HeroNode(2, "wu");
        HeroNode node3 = new HeroNode(3, "lu");
        HeroNode node4 = new HeroNode(4, "lin");
        HeroNode node5 = new HeroNode(5, "Guan");

        //手动创建，后面可以递归创建二叉树

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

//        //测试前序
//        System.out.println("前序遍历");
//        binaryTree.setRoot(root);
//        binaryTree.preOrder();
//
//        //测试中序
//        System.out.println("前序遍历");
//        binaryTree.setRoot(root);
//        binaryTree.infixOrder();
//
//        //测试后序
//        System.out.println("前序遍历");
//        binaryTree.setRoot(root);
//        binaryTree.postOrder();

        //前序遍历方式
        System.out.println("前序遍历查找");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if(resNode != null) {
            System.out.printf("找到，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

        //中序遍历方式
        System.out.println("前序遍历查找");
        resNode = binaryTree.infixOrderSearch(5);
        if(resNode != null) {
            System.out.printf("找到，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

        //后序遍历方式
        System.out.println("前序遍历查找");
        resNode = binaryTree.postOrderSearch(5);
        if(resNode != null) {
            System.out.printf("找到，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

    }
}

//定义Binary Tree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    private String name;
    private HeroNode left;//左右都默认为null
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }
    //编写前序遍历的方法
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


