package hello;

public class Coin {

    private final long id;
    private final String content;

    public Coin(long id, String content) {
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

