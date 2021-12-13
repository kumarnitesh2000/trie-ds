class Bits{
    public static void main(String[] args){
        TrieBitStruct trie = new TrieBitStruct();
        int min_xor = trie.findMinXor(new int[]{9,5,3});
        int max_xor = trie.findMaxXor(new int[]{9,5,3});
        System.out.println("min xor: "+min_xor);
        System.out.println("max xor: "+max_xor);
    }
}

class Node{
    int num;
    Node[] children;
    Node(){
        this.children = new Node[2];
        this.children[0] = this.children[1] = null;
    }
}

class TrieBitStruct{
    Node root;
    TrieBitStruct(){
        this.root = new Node();
    }
    boolean isContains(Node node,int bit_val){
        return node.children[bit_val]!=null;
    }
    void put(Node node,Node new_node,int bit_val){
        node.children[bit_val] = new_node;
    }
    Node getNext(Node node,int bit_val){
        return node.children[bit_val];
    }
    void insert(int num){
        Node start = root;
        for(int bit = 31; bit >= 0; bit--){
            int bitVal = (num & (1<<bit)) >= 1 ? 1: 0;
            if(!isContains(start,bitVal))put(start,new Node(),bitVal);
            start = getNext(start,bitVal);
        }
        start.num = num;
    }
    int notBitVal(int bitVal) {
        return bitVal==0 ? 1: 0;
    }
    int currMinXor(int num){
        Node start = root;
        for(int bit=31;bit>=0;bit--){
            int bitVal = (num & (1<<bit))>=1 ? 1: 0;
            if(isContains(start,bitVal)){
                start = getNext(start,bitVal); 
            }else if(isContains(start,notBitVal(bitVal))){
                start = getNext(start,notBitVal(bitVal)); 
            }else{
                return Integer.MAX_VALUE;
            }
        }
        return num ^ start.num;
    }
    int findMinXor(int[] arr){
        int min_xor = arr[0]^arr[1];
        insert(arr[0]);
        for(int i=1;i<arr.length;i++){
            min_xor = Math.min(min_xor,currMinXor(arr[i]));
            insert(arr[i]);
        }
        return min_xor; 
    }
    int currMaxXor(int num){
        Node start = root;
        for(int bit=31;bit>=0;bit--){
            int bitVal = (num & (1<<bit))>=1 ? 1: 0;
            if(isContains(start,notBitVal(bitVal))){
                start = getNext(start,notBitVal(bitVal));
            }else if(isContains(start,bitVal)){
                start = getNext(start,bitVal);
            }else{
                return Integer.MIN_VALUE;
            }
        }
        return num ^ start.num;
    }
    int findMaxXor(int[] arr){
        int max_xor = arr[0]^arr[1];
        insert(arr[0]);
        for(int i=1;i<arr.length;i++){
            max_xor = Math.max(max_xor,currMaxXor(arr[i]));
            insert(arr[i]);
        }
        return max_xor;
    }
}