package com.Collection;
import java.util.Iterator;
import java.util.Stack;


public class BinaryTree
{
	
	TreeNode root = null;
	
	public BinaryTree (){
		root = new TreeNode(1,null,null);
	}
	
	public void createBinTree(){
		
		TreeNode node2 = new TreeNode(2,null,null);
		TreeNode node3 = new TreeNode(3,null,null);
		TreeNode node4 = new TreeNode(4,null,null);
		TreeNode node5 = new TreeNode(5,null,null);
		TreeNode node6 = new TreeNode(6,null,null);
		TreeNode node7 = new TreeNode(7,null,null);
		
		root.left = node2;
		root.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		node3.left = node6;
		node3.right = node7;
	}
	
	
	public boolean isEmpty(){
		return root==null;
	}
	
	//树的高度
	public int height()
	{
		return height(root);
	}
	
	
	private int height(TreeNode subTree)
	{
		if(subTree==null){
			return 0;
		}else{
			int lh = height(subTree.left);
			int rh = height(subTree.right);
			return (lh<rh)?(lh+1):(rh+1);
		}
	}
	
	
	//节点个数  
    public int size(){  
        return size(root);  
    }  
    
    private int size(TreeNode subTree){
    	if(subTree==null){
    		return 0;
    	}else{
    		return 1 + size(subTree.left) + size(subTree.right);
    	}
    }
    
    //返回双亲节点
    public TreeNode parentNode(TreeNode node){
    	if(node==root || root ==null){
    		return null;
    	}else{
    		return parentNode(root,node);
    	}
    }
	
    private TreeNode parentNode(TreeNode subTree,TreeNode element){
    	
    	if(subTree==null || (subTree.left==null && subTree.right==null)) return null;
    	
    	if( (subTree.left!=null && subTree.left.data==element.data) || (subTree.right!=null && subTree.right.data==element.data) ){
    		return subTree;
    	}else{
    		return (parentNode(subTree.left,element)!=null) ? parentNode(subTree.left,element) : 
    			(parentNode(subTree.right,element)!=null ? parentNode(subTree.right,element) : null);
    	}
    }
    
    
    public TreeNode getLeftChildNode(TreeNode node){
    	if(node==null)return null;
    	return  getLeftChildNode(root,node);
    }
    
    public TreeNode getLeftChildNode(TreeNode subTree,TreeNode node){
    	if(subTree==null)return null;
    	if(subTree.data==node.data){
    		return subTree.left;
    	}else{
    		TreeNode p ;
    		if((p=getLeftChildNode(subTree.left,node))!=null )
    			return p;
    		else{
    			return getLeftChildNode(subTree.right,node);
    		}
    	}
    }
	
    //在释放某个结点时，该结点的左右子树都已经释放，  ?
    //所以应该采用后续遍历，当访问某个结点时将该结点的存储空间释放  
    public void destroy(TreeNode node){
    	if(node!=null){
    		destroy(node.left);
    		destroy(node.right);
    		node=null;
    	}
    }
    
    //有错 ?
    public void traverse(TreeNode subTree){  
        System.out.println("--data:"+subTree.data);
        traverse(subTree.left);  
        traverse(subTree.right);  
    }  
    
    //前序遍历  (递归)
    public void preOrder(TreeNode subTree){ 
    	if(subTree!=null)
    		System.out.print(subTree.data);
    	
    	if(subTree.left!=null)
    		preOrder(subTree.left);
    	if(subTree.right!=null)
    		preOrder(subTree.right);
    }
	
    
    //前序遍历  (非递归)
    public void nonRecPreOrder(TreeNode subTree){
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	
    	stack.push(subTree);
    	while(stack.size()>0){
    		
    		TreeNode p = stack.pop();
    		if(p.right!=null){
    			stack.push(p.right);
    		}
	    	if(p.left!=null){
				stack.push(p.left);
			}
    	}
    }
    
    
    //中序
    public void inOrder(TreeNode subTree){
    	
    	if(subTree.left!=null)
    		inOrder(subTree.left);
    	
    	if(subTree!=null)
    		System.out.print(subTree.data);
    	
    	
    	if(subTree.right!=null)
    		inOrder(subTree.right);
    }
    
    
    //中序遍历的非递归实现  
    public void nonRecInOrder(TreeNode p){  
        Stack<TreeNode> stack =new Stack<TreeNode>();  
        TreeNode node =p;  
        while(node!=null||stack.size()>0){  
            //存在左子树  
            while(node!=null){  
                stack.push(node);  
                node=node.left;  
            }  
            //栈非空  
            if(stack.size()>0){  
                node=stack.pop();  
                System.out.print(node.data);
                node=node.right;  
            }  
        }  
    }  
    
    
    
    //后序遍历
    public void postOrder(TreeNode subTree){
    	
    	if(subTree.left!=null)
    		postOrder(subTree.left);
    	
    	if(subTree.right!=null)
    		postOrder(subTree.right);
    	
    	if(subTree!=null)
    		System.out.print(subTree.data);
    	
    }
    
	
	public static void main(String[] args)
	{
		BinaryTree tree = new BinaryTree();
		tree.createBinTree();
//		System.out.println("======"+tree.height());
//		System.out.println("++++++"+tree.size());
//		TreeNode node = tree.parentNode(new TreeNode(7,null,null));
//		if(node!=null){
//			System.out.println(node.data);
//		}else{
//			System.out.println(node);
//		}
//		
//		
//		TreeNode node2 = tree.getLeftChildNode(new TreeNode(5,null,null));
//		if(node2!=null){
//			System.out.println(node2.data);
//		}else{
//			System.out.println(node2);
//		}
		
		
//		tree.traverse(tree.root);

		/**
		 *  1245367
			4251637
			4526731
		 */
		
//		tree.preOrder(tree.root);
//		System.out.println("");
//		tree.inOrder(tree.root);
//		System.out.println("");
//		tree.postOrder(tree.root);
		
		System.out.println("========");
		tree.nonRecPreOrder(tree.root);
//		tree.nonRecInOrder(tree.root);
		
		
	}
	
	
	
	
	//查找节点
	public TreeNode find(TreeNode node,BinaryTree tree){
		if(tree==null)return null ; 
		
		
		
		return node;
	}
	
	

}
