package model;

public class HashTable<K, V> {
    private HashNode<K, V>[] buckets;
    private int numOfBuckets;
    private int size;

    public HashTable() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.numOfBuckets = capacity;
        buckets = new HashNode[numOfBuckets];
        this.size = 0;
    }

    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key.toString() + "=" + value.toString();
        }

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(K key, V value) {
        if (key == null && value == null) {
            throw new IllegalArgumentException("La llave y el valor son nulos");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = buckets[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = buckets[bucketIndex];
        HashNode<K, V> node = new HashNode<>(key, value);
        node.next = head;
        buckets[bucketIndex] = node;
    }

    public Task get(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = buckets[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                return (Task) head.value;
            }
            head = head.next;
        }
        return null;
    }

    public boolean getBoolean(K key) {
        boolean isValid = false;
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = buckets[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                isValid = true;
                return isValid;
            }
            head = head.next;
        }
        return isValid;
    }

    public Task remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key null");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = buckets[bucketIndex];
        HashNode<K, V> previous = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            previous = head;
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        size--;
        if (previous != null) {
            previous.next = head.next;
        } else {
            buckets[bucketIndex] = head.next;
        }
        return (Task) head.value;
    }

    public V modify(K key, V newValue) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = buckets[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = newValue;
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    private int getBucketIndex(K key) {
        int hash = key.hashCode() % buckets.length;
        if (hash < 0) {
            hash += buckets.length;
        }
        return hash;
    }

    public int getNumOfBuckets() {
        return numOfBuckets;
    }

    public void setNumOfBuckets(int numOfBuckets) {
        this.numOfBuckets = numOfBuckets;
    }
}
