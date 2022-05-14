package de.unistuttgart.hackathon.taser.QueueService;

public class Vote {
    private final boolean isLost;

    public Vote(final boolean isLost){
        this.isLost = isLost;
    }

    /**
     * Returns whether the voter is lost
     * @return whether the vote
     */
    public boolean isLost() {
        return isLost;
    }
}
