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

    public Found() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoundKind getFoundKind() {
        return foundKind;
    }

    public void setFoundKind(FoundKind foundKind) {
        this.foundKind = foundKind;
    }
}
