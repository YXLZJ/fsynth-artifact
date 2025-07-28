package erepair.program.repairer.erepair;

public interface BinarySearchable<T> {
    public int length();

    public BinarySearchable substring(int a, int b);

    public T get();
}
