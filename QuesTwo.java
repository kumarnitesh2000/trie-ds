//https://practice.geeksforgeeks.org/problems/unique-rows-in-boolean-matrix/1
import java.util.*;
class QuesTwo
{
    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][],int r, int c)
    {
        BTrie trie = new BTrie();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<r;i++){
            int[] arr = a[i];
            boolean is_pres = trie.insert(arr);
            if(!is_pres){
                ArrayList<Integer> lis = new ArrayList<Integer>();
                for(int entry: arr)lis.add(entry);
                res.add(lis);
            }
        }
        return res;
    }
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> res = uniqueRow(new int[][]{{1,1,0,1},{1,0,0,1},{1,1,0,1}},3,4);
        for(ArrayList<Integer> r: res)System.out.println(r); 
    }
}

class BNode{
    boolean isEnd;
    BNode[] children;
    BNode(){
        this.children = new BNode[2];
        this.children[0] = this.children[1] = null;
        isEnd = false;
    }
}


class BTrie{
    BNode root;
    BTrie(){
        this.root = new BNode();
    }
    boolean isContains(BNode node, int a){
        return node.children[a]!=null; 
    }
    void put(BNode node, int a){
        node.children[a] = new BNode();
    }
    BNode getNext(BNode node,int a){
        return node.children[a];
    }
    boolean insert(int[] arr){
        BNode start = root;
        for(int i=0; i<arr.length; i++){
            int a = arr[i];
            if(!isContains(start,a)){
                put(start,a);
            } 
            start = getNext(start,a);
        } 
        if(start.isEnd==false){
            start.isEnd = true;
            return false;
        }else{
            return true;
        }
    }
}