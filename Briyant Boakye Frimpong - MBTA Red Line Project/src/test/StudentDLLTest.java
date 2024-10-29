package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.DoubleLinkedList;

class StudentDLLTest {

	 @Test
    void initTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertEquals(0, list.size());
        assertNull(list.getFirst());
		assertNull(list.delete(10));
        assertNull(list.get(10));
    }

    @Test
    void insertTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.insert(10);
        assertEquals(1, list.size());
        assertEquals(10, list.getFirst().getData());

        list.insert(20);
        assertEquals(2, list.size());
        assertEquals(10, list.getFirst().getData());
        assertEquals(20, list.getFirst().getNext().getData());
    }

    @Test
    void deleteTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);

        assertEquals(20, list.delete(20)); // Delete existing element
        assertEquals(2, list.size());

        assertEquals(10, list.delete(10)); // Delete head
        assertEquals(1, list.size());

        assertNull(list.delete(40)); // Attempt to delete non-existent element
    }

    @Test
    void getTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);

        assertEquals(20, list.get(20)); // Should return the node containing 20
        assertNull(list.get(40)); // Should return null for non-existent element
    }

    @Test
    void sizeTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertEquals(0, list.size());
        
        list.insert(10);
        assertEquals(1, list.size());
        
        list.insert(20);
        list.insert(30);
        assertEquals(3, list.size());

        list.delete(20);
        assertEquals(2, list.size());
    }

    @Test
    void toStringTest() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertEquals("[]", list.toString());

        list.insert(10);
        assertEquals("[10]", list.toString());

        list.insert(20);
        list.insert(30);
        assertEquals("[10, 20, 30]", list.toString());

        list.delete(20);
        assertEquals("[10, 30]", list.toString());
    }
}
