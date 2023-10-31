package org.example;

public enum Commands {
    WHISPER("/w"), KICK("/kick"), EXIT("/exit"), LIST("/list");
    private final String title;

    Commands(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
