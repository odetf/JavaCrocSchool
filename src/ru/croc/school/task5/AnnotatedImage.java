package ru.croc.school.task5;
import ru.croc.school.task5.Annotation;

class AnnotatedImage {

    private final String imagePath;

    private final Annotation[] annotations;

    Annotation findByPoint(AnnotatedImage annotatedImage, int x, int y, Annotation annotWithPoint){
        Annotation annot[] = annotatedImage.annotations;
        for (Annotation a:annot){
            if (a.getFigure().checkPoint(x, y) == true){
                annotWithPoint = a;
                break;
            }
        }
        return annotWithPoint;
    }

    Annotation findByLabel(AnnotatedImage annotatedImage, String label, Annotation annotation){
        Annotation annot[] = annotatedImage.annotations;
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