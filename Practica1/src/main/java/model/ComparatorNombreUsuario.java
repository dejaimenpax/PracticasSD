package model;
import java.util.Comparator;
public class ComparatorNombreUsuario implements Comparator<Usuario> {
	    @Override
	    public int compare(Usuario o1, Usuario o2) {
	    	return o1.getFullName().compareTo(o2.getFullName());
	    }
}