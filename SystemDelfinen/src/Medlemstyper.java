public enum Medlemstyper {
    //De forskellige typer medlemskaber der har forskellige priser
    AKTIV_JUNIOR,
    AKTIV_SENIOR,
    AKTIV_SENIOR_60PLUS,
    PASSIV;

    //Metode der returnerer den korrekte pris ud fra medlemskabet
    public double getKontingent() {
        return switch (this) {
            case PASSIV -> 500;
            case AKTIV_JUNIOR -> 1000;
            case AKTIV_SENIOR -> 1600;
            case AKTIV_SENIOR_60PLUS -> 1600 * 0.75;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case PASSIV -> "passiv";
            case AKTIV_JUNIOR -> "aktiv";
            case AKTIV_SENIOR -> "aktiv";
            case AKTIV_SENIOR_60PLUS -> "aktiv";
        };
    }


    public String pænToString() {
        return switch (this) {
            case PASSIV -> "Passivt medlemskab";
            case AKTIV_JUNIOR -> "Aktivt juniormedlemskab";
            case AKTIV_SENIOR -> "Aktivt seniormedlemskab";
            case AKTIV_SENIOR_60PLUS -> "Aktivt 60+-medlemskab";
        };
    }
}