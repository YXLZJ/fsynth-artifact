package fsynth.program.repairer.brepair;

public interface BinarySearchable<T> {
    public int length();

    public BinarySearchable substring(int a, int b);

    public T get();
}
