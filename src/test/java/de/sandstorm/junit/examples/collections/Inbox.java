package de.sandstorm.junit.examples.collections;

import de.sandstorm.junit.examples.nestedEntities.Mail;

import java.util.List;
import java.util.stream.Collectors;

/**
 * test entity containing collections
 */
public class Inbox {

    private List<Mail> content;

    /**
     * constructor
     *
     * @param content content
     */
    public Inbox(List<Mail> content) {
        this.content = content;
    }

    /**
     * @return exact clone of this instance
     */
    public Inbox duplicate() {
        return new Inbox(
            content.stream().map(Mail::duplicate).collect(Collectors.toList())
        );
    }

    public List<Mail> getContent() {
        return content;
    }
}
