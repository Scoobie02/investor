package pl.ws.investor.domain;

public class Found {

    private Long id;
    private FoundKind foundKind;
    private String name;

    public Found(Long id, FoundKind foundKind, String name) {
        this.id = id;
        this.foundKind = foundKind;
        this.name = name;
    }

    Long getId() {
        return id;
    }


    String getName() {
        return name;
    }

    public FoundKind getFoundKind() {
        return foundKind;
    }
}
