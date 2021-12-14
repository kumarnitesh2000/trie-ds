//https://www.geeksforgeeks.org/find-all-shortest-unique-prefixes-to-represent-each-word-in-a-given-list/
public class Ques {
    static String[] findPrefixes(String arr[], int n)
    {
        String[] res = new String[n];
        Trie trie = new Trie();
        //insert in the trie
        for(String a : arr)trie.insert(a);
        // for all get the minimum prefix
        int index = 0;
        for(String a: arr){
            String min_prefix = trie.minPrefix(a);
            res[index++] = min_prefix;
        }
        return res;
    }
    public static void main(String[] args){
        int n=4;
        String[] arr = {"zebra", "dog", "duck", "dove"};
        String[] res = findPrefixes(arr,n);
        for(int i = 0;i<n;i++){
            System.out.println(arr[i]+" -> "+res[i]);
        }
    }
}

class TNode{
    TNode[] children;
    int frequency;
    TNode(){
        children = new TNode[26];
        for(int i=0;i<26;i++)this.children[i] = null;
        this.frequency = 0;
    }
}
class Trie{
    TNode root;
    Trie(){
        root = new TNode();
    }
    boolean isContains(TNode start,char c){
        return start.children[c-'a'] != null;
    }
    void put(TNode start,char c){
        start.children[c-'a'] = new TNode();
    }
    void increaseFreq(TNode start,char c){
        int freq = start.children[c-'a'].frequency;
        start.children[c-'a'].frequency = ++freq;
        return;
    }
    TNode getNext(TNode start,char c){
        return start.children[c-'a']; 
    }
    void insert(String st){
        TNode start = root;
        int len = st.length();
        for(int i=0;i<len;i++){
            char c = st.charAt(i);
            if(!isContains(start,c)){
                put(start,c);
            }
            increaseFreq(start,c);
            start = getNext(start,c);
        }
    }
    String minPrefix(String s){
        StringBuilder st = new StringBuilder();
        int n =s.length() ;
        TNode start = root;
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(start.children[c-'a'].frequency==1){
                st.append(c);
                return st.toString();
            }
            st.append(c);
            start = getNext(start,c);
        }
        return st.toString();
    }
}
