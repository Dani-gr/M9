package es.proyectotaw.banca.bancapp.dao;

import es.proyectotaw.banca.bancapp.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("select c from ClienteEntity c where c.idCliente = :id")
    public ClienteEntity buscarPorID(@Param("id") Integer id);

    //@Query("select c from ClienteEntity c where c.usuariosByIdCliente[0].email = :user and c.usuariosByIdCliente[0].password = :password")
    //public ClienteEntity autenticar (@Param("user") String user, @Param("password")String password);
}
