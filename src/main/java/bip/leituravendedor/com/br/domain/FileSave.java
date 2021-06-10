package bip.leituravendedor.com.br.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "information_files")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileSave {

    @Id
    @GeneratedValue
    private UUID id;
    private String path;
    private String newnamefile;
    private String status;

}
