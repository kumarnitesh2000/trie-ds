public class Second {
    public static void main(String[] args){
        CustomTrieDistinctSubstring trie = new CustomTrieDistinctSubstring();
        System.out.println(trie.distinctSubstring("aaba"));  
    }
}

class CustomTrieDistinctSubstring extends TrieStruct{
    CustomTrieDistinctSubstring(){
        super();
    }
    int distinctSubstring(String key){
        int cnt=0;
        int n = key.length();
        for(int i = 0;i<n;i++){
            TrieNode start = root;
            for(int j = i;j<n;j++){
                char ch = key.charAt(j);
                if(!isContains(start,ch)){
                    put(ch, start, new TrieNode());
                    setFlag(start);
                    cnt++;
                }
                start = getNext(ch,start);
            }
        }
        return cnt;
    }
}