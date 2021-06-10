package bip.leituravendedor.com.br.service.file;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bip.leituravendedor.com.br.enums.FileStatusEnum;
import bip.leituravendedor.com.br.gateway.repository.FileSaveRepository;

@Service
public class UpdateStatusFileSaveService {

    @Autowired
    private FileSaveRepository fileSaveRepository;

    @Transactional
    public void execute(UUID uuid) {
        fileSaveRepository.updateStatus(FileStatusEnum.PROCESSADO.toString(), uuid);
    }
}
