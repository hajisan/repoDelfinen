public enum disciplinNavne {
    //Enumkonstanter der repræsenterer de fire svømmearter
    BUTTERFLY,
    CRAWL,
    RYGCRAWL,
    BRYSTSVØMNING;

    @Override
    public String toString() {
        return switch (this) {
            case BUTTERFLY -> "Butterfly";
            case CRAWL -> "Crawl";
            case RYGCRAWL -> "Rygcrawl";
            case BRYSTSVØMNING -> "Brystsvømning";
        };
    }
}
