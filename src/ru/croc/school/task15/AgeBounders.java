package ru.croc.school.task15;

public class AgeBounders <T1 extends Comparable, T2> implements Comparable{

    private T1 left;
    private T2 right;

    public AgeBounders(T1 left, T2 right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        if (right != null){
            return left.toString() + "-"+ right.toString() + ": ";
        }
        else return left.toString() + "+: ";
    }

    @Override
    public int compareTo(Object o){
        return left.compareTo(((AgeBounders<?, ?>) o).left);
    }

}
