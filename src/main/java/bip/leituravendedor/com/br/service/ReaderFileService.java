package bip.leituravendedor.com.br.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import bip.leituravendedor.com.br.domain.FileSave;
import bip.leituravendedor.com.br.enums.FileStatusEnum;
import bip.leituravendedor.com.br.gateway.json.FileUUIDJson;
import bip.leituravendedor.com.br.service.file.GetFileSaveService;
import bip.leituravendedor.com.br.service.file.UpdateStatusFileSaveService;
import bip.leituravendedor.com.br.service.s3.S3Service;

@Service
public class ReaderFileService {

    @Autowired
    private GetFileSaveService getFileSaveService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ReaderFileLineService readerFileLineService;

    @Autowired
    private UpdateStatusFileSaveService updateStatusFileSaveService;

    public void execute(FileUUIDJson fileUUIDJson) throws IOException {
        //FileSave fileSave = getFileSaveService.findById(UUID.fromString(fileUUIDJson.getUuid()));
    	if (!fileUUIDJson.equals("")) {
	    	//FileSave fileSave = getFileSaveService.findByIdStatus(UUID.fromString(fileUUIDJson.getUuid()));
    		//FileSave fileSave = getFileSaveService.findById(UUID.fromString(fileUUIDJson.getUuid()));
    		FileSave fileSave = getFileSaveService.findAllIdStatus(UUID.fromString(fileUUIDJson.getUuid()), FileStatusEnum.RECEBIDO.toString());
    		if (fileSave.getStatus().toString().equals(FileStatusEnum.RECEBIDO.toString())) {
		    	S3ObjectInputStream s3ObjectInputStream = s3Service.execute(fileSave.getNewnamefile());
		        readerFileLineService.execute(s3ObjectInputStream);
		        updateStatusFileSaveService.execute(fileSave.getId());
	        }
    	}
    }

}
