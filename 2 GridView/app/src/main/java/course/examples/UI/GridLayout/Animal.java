package course.examples.UI.GridLayout;

public class Animal {
    public int imageId;
    public String animalName;
    public String animalWiki;
    public String animalFact;

    public Animal(int imageId, String animalName, String animalWiki, String animalFact) {
        this.animalName = animalName;
        this.imageId = imageId;
        this.animalFact=animalFact;
        this.animalWiki=animalWiki;
    }

    public String getAnimalName() {return animalName;}

    public void setAnimalName(String animalName) {this.animalName = animalName;}

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAnimalWiki() {return animalWiki;}

    public void setAnimalWiki(String animalWiki) {this.animalWiki = animalWiki;}

    public String getAnimalFact() {return animalFact;}

    public void setAnimalFact(String animalFact) {this.animalFact = animalFact;}
}
