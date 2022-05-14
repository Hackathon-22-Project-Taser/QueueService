package de.unistuttgart.hackathon.taser.QueueService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

/**
 * Controller for the QueueService REST-API
 */
@RestController
public class QueueController {

    private QueueService queueService;

    @PostConstruct
    public void init(){
        queueService = new QueueService();
    }

    /**
     * Creates a new Queue
     * @requires no queue with given identifier exists
     */
    @PostMapping("/queue/create/{identifier}")
    public void createQueue(@PathVariable final String identifier) {
        if (queueService.isIdentifierUsed(identifier)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Queue with ID %s already exists!",
                    identifier));
        }
        queueService.createQueue(identifier);
    }

    /**
     * Stores a vote of a student
     * @requires queue with the given identifier to exist
     */
    @PostMapping("/queue/store/{identifier}")
    public void storeVote(@PathVariable final String identifier) {

    }

    /**
     * Empties the queue
     * @requires queue with the given identifier to exist
     */
    @PostMapping("/queue/flush/{identifier}")
    public void flushQueue(@PathVariable final String identifier){

    }

}
