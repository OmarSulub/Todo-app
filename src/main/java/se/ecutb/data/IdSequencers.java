package se.ecutb.data;

public interface IdSequencers {
    /**
     * Increment and return an int
     * @return int next PersonId
     */
    int nextPersonId();

    /**
     * Increment and return an int
     * @return int next TodoId
     */
    int nextTodoId();

    /**
     * Reset counter
     */
    void clearPersonId();

    /**
     * Reset counter
     */
    void clearTodoId();
}
