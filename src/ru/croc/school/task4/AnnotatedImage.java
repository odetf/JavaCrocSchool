package ru.croc.school.task4;
import ru.croc.school.task4.Annot;

class AnnotatedImage {

    private final String imagePath;

    private final Annot[] annotations;



    public AnnotatedImage(String imagePath, Annot... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annot[] getAnnotations() {
        return this.annotations;
    }
}

