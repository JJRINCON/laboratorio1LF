package models;

public class CharacterString {

    private Production actualProduction;
    private String characters;

    public CharacterString(Production actualProduction, String characters) {
        this.actualProduction = actualProduction;
        this.characters = characters;
    }

    public Production getActualProduction() {
        return actualProduction;
    }

    public String getCharacters() {
        return characters;
    }

    @Override
    public String toString() {
        return characters;
    }
}
