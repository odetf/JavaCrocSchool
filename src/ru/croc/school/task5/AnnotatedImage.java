package ru.croc.school.task5;
import ru.croc.school.task5.Annotation;

class AnnotatedImage {

    private final String imagePath;

    private final Annotation[] annotations;

    public Annotation findByPoint(int x, int y){
        Annotation annotWithPoint = null;
        Annotation annot[] = annotations;
        for (Annotation a:annot){
            if (a.getFigure().checkPoint(x, y) == true){
                annotWithPoint = a;
                break;
            }
        }
        return annotWithPoint;
    }

    public Annotation findByLabel(String label){
        Annotation annotation= null;
        Annotation annot[] = annotations;
        for (Annotation a:annot){
            if (a.getDescription().contains(label)){
                annotation = a;
                break;
            }
        }
        return annotation;
    }

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }
}