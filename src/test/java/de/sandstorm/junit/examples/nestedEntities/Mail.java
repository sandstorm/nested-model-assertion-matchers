package de.sandstorm.junit.examples.nestedEntities;

import de.sandstorm.junit.examples.simpleEntity.Person;

/**
 * test entity with references to another one
 */
public class Mail {

    private final Person recipient;
    private final Person sender;

    /**
     * constructor for mail with unknown sender
     *
     * @param recipient
     */
    public Mail(Person recipient) {
        this(recipient, null);
    }

    /**
     * constructor
     *
     * @param recipient
     * @param sender
     */
    public Mail(Person recipient, Person sender) {
        this.recipient = recipient;
        this.sender = sender;
    }

    /**
     * @return exact clone of this instance
     */
    public Mail duplicate() {
        return new Mail(
            recipient.duplicate(),
            sender == null ? null : sender.duplicate()
        );
    }

    public Person getRecipient() {
        return recipient;
    }

    public Person getSender() {
        return sender;
    }
}
