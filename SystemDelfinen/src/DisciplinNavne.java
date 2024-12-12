public enum DisciplinNavne {
    //Enumkonstanter der repræsenterer de fire svømmearter
    BUTTERFLY,
    CRAWL,
    RYGCRAWL,
    BRYSTSVØMNING;

    @Override
    public String toString() {
        return switch (this) { // Returnerer en tekstrepræsentation af enum-værdien baseret på dens navn
            case BUTTERFLY -> "Butterfly";
            case CRAWL -> "Crawl";
            case RYGCRAWL -> "Rygcrawl";
            case BRYSTSVØMNING -> "Brystsvømning";
        };
    }
}