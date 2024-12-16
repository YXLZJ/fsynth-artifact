package fsynth.program.repairer.brepair;

public class StringBinarySearchWrapper implements BinarySearchable<String> {
    private final String content;

    public StringBinarySearchWrapper(String content) {
        this.content = content;
    }

    @Override
    public int length() {
        return this.content.length();
    }

    @Override
    public BinarySearchable substring(int a, int b) {
        return new StringBinarySearchWrapper(this.content.substring(a, b));
    }

    @Override
    public String get() {
        return this.content;
    }
}
