package bip.leituravendedor.com.br.service.file;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.istack.NotNull;

import bip.leituravendedor.com.br.domain.FileSave;
import bip.leituravendedor.com.br.enums.FileStatusEnum;
import bip.leituravendedor.com.br.gateway.repository.FileSaveRepository;

@Service
public class GetFileSaveService {

    @Autowired
    private FileSaveRepository fileSaveRepository;

    public FileSave findById(UUID uuid) {
        return fileSaveRepository.findById(uuid).get();
    }
	
    public FileSave findByIdStatus(@NotNull UUID uuid) {
    	String recebido = FileStatusEnum.RECEBIDO.toString();
        return fileSaveRepository.findByIdStatus(recebido, uuid);
    }
    
}
