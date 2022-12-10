package ru.croc.school.task15;

public class AgeBounders <T1 extends Comparable<T1>, T2> implements Comparable<AgeBounders<T1, T2>>{

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
    public int compareTo(AgeBounders<T1, T2> o) {
        return left.compareTo(o.left);
    }
}
