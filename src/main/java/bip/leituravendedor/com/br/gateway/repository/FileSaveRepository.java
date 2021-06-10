package bip.leituravendedor.com.br.gateway.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sun.istack.NotNull;

import bip.leituravendedor.com.br.domain.FileSave;

public interface FileSaveRepository extends CrudRepository<FileSave, UUID> {

    @Modifying
    @Query("update FileSave set status = ?1 where id = ?2")
    void updateStatus(String status, UUID uuid);
    
	@Query("select id, path, newnamefile, status from FileSave where status = ?1 and id = ?2")
	FileSave findByIdStatus(@NotNull String status, @NotNull UUID uuid);

}
