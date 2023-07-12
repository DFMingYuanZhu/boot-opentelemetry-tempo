package org.example;

/**
 * @author ：zhumingyuan
 * @description：TODO
 * @date ：2023/7/10 13:27
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
