package Model;

public interface UsuarioDAO {

    public void cadastrar(Usuario usuario)
            throws IllegalArgumentException, IndexOutOfBoundsException, UsuarioExisteException;

    public Usuario buscar(Usuario usuario);
}
