class First{
    public static void main(String[] args){
        TrieStruct trie = new TrieStruct();
        System.out.println(trie.search("hello"));
        trie.insert("hello");
        System.out.println(trie.search("hello"));
    }
}

class TrieNode{
    static int alphabet_size = 26;
    boolean isEnd;
    TrieNode[] children;
    TrieNode(){
        children = new TrieNode[alphabet_size];
        for(int i=0;i<alphabet_size;i++)this.children[i] = null;
        this.isEnd = false;
    } 
}

class TrieStruct{
    TrieNode root;
    TrieStruct(){
        System.out.println("creating root node");
        root = new TrieNode();
    }
    boolean isContains(TrieNode node,char c){
        return node.children[c-'a']!=null;
    }
    void put(char c,TrieNode node,TrieNode new_node){
        node.children[c-'a'] = new_node;
    }
    void setFlag(TrieNode node){
        node.isEnd = true;
    }
    TrieNode getNext(char c,TrieNode node){
        return node.children[c-'a'];
    }
    void insert(String key){
        TrieNode start = root;
        for(int i=0; i<key.length(); i++){
            char ch = key.charAt(i);
            // character not inserted previously
            if(!isContains(start,ch))put(ch,start,new TrieNode());
            start = getNext(ch,start);
        }
        setFlag(start);
    }
    boolean search(String key){
        TrieNode start = root;
        for(int i=0;i<key.length();i++){
            char ch = key.charAt(i);
            if(isContains(start,ch))start = getNext(ch,start);
            else return false;
        }
        return start.isEnd;
    }
    boolean startsWith(String key){
        TrieNode start = root;
        for(int i=0;i<key.length();i++){
            char ch = key.charAt(i);
            if(isContains(start,ch))start = getNext(ch,start);
            else return false;
        }
        return true;
    }
}