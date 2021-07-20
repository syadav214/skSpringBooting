
public class GenericHashMap<K,V> {
    K key;
    V value;
    Node<K,V> map;

    public void put(K key,V value){
        if(this.map == null){
            this.map = new Node<>(key, value, null);
        } else {
            Node<K,V> existingNode = checkExistingKey(key, value);
            if(existingNode == null){
                this.map = new Node<>(key, value, this.map);
            }
        }
    }

    Node<K,V> checkExistingKey(K key,V value){
        return checkExistingKey(key, value, this.map);
    }

    Node<K,V> checkExistingKey(K key,V value, Node<K,V> node){
        if(node.key.equals(key)){
            node.value = value;
            return node;
        } else if(node.next != null){
            checkExistingKey(key, value, node.next);
        }
        return null;
    }

    public V get(K key){
        Node<K,V> node = this.map;

        while(node != null){
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
        }
        return  null;
    }


    class Node<K,V> {
        final K key;
        V value;
        Node<K,V> next;
        Node(K key, V value, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


    }

    public static void main(String[] args) {
        GenericHashMap<String, Integer> map = new GenericHashMap<>();
        map.put("india", 1);
        map.put("usa", 2);
        map.put("india", 3);
        System.out.println(map.get("india"));
    }
}
