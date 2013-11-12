import java.util.Comparator;

public class EdgeDistanceComparator<E extends Edge<?>> implements Comparator<E> {

	@Override
	public int compare(E arg0, E arg1) {
		return arg0.getWeight() - arg1.getWeight() == 0 ? arg0.getNumber()
				- arg1.getNumber() : arg0.getWeight() - arg1.getWeight();
	}

}
