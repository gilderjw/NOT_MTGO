package back_end;

public interface IData<T> extends Comparable<IData<T>> {
	public long getId();
	public T getValue();
}
