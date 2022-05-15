package de.unistuttgart.hackathon.taser.QueueService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueService {

    private final Map<String, Queue<Map<LocalDateTime, Boolean>>> availableQueues;

    public QueueService(){
        this.availableQueues = new HashMap<>();
    }

    /**
     * Creates a Queue paired to the given identifier
     * @param identifier of the queue to create
     * @requires !(isIdentifierUsed)
     */
    public void createQueue(final String identifier) {
        if (this.isIdentifierUsed(identifier)){
            throw new IllegalArgumentException("identifier is already used");
        }
        availableQueues.put(identifier, new LinkedList<>());
    }

    /**
     * Deletes a Queue paired to the given identifier
     * @param identifier of the queue to delete
     */
    public void deleteQueue(final String identifier) {
        if (this.isIdentifierUsed(identifier)) {
            availableQueues.remove(identifier);
        }
    }


    /**
     * Returns whether an identifier is already used
     * @param identifier to check for
     * @return whether the identifier is already used
     */
    public boolean isIdentifierUsed(final String identifier){
        return availableQueues.containsKey(identifier);
    }

    /**
     * Stores a given vote in the specified queue
     * @param identifier of the queue, to store the vote
     * @param vote to be stored in the queue
     */
    public void storeVote(final String identifier, final Boolean vote) {
        final Queue<Map<LocalDateTime, Boolean>> queue = this.findQueue(identifier);
        final Map<LocalDateTime, Boolean> map = new HashMap<>();
        map.put(LocalDateTime.now(), vote);
        queue.add(map);
    }

    /**
     * Returns the queue of the given id
     * @param identifier of the queue
     * @return queue with the specified queue
     */
    private Queue<Map<LocalDateTime, Boolean>> findQueue(final String identifier) {
        return this.availableQueues.get(identifier);
    }

    /**
     * Clears a queue with the given identifier
     * @param identifier of the queue to be cleared
     */
    public void flushQueue(final String identifier) {
        final Queue<Map<LocalDateTime, Boolean>> queue = this.findQueue(identifier);
        queue.clear();
    }

    /**
     * Clears all queues
     */
    public void flushQueues() {
        for(Map.Entry<String,Queue<Map<LocalDateTime, Boolean>>> entry: this.availableQueues.entrySet()){
            entry.getValue().clear();
        }
    }

    /**
     * Gets all Queues
     * @return all Queues in a Map
     */
    public Map<String, Queue<Map<LocalDateTime, Boolean>>> getQueues() {
        return this.availableQueues;
    }


}
