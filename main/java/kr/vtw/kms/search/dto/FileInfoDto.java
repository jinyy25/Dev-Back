package kr.vtw.kms.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_file_info")
public class FileInfoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileCode;


    @Column(name = "organi_name", nullable = false)
    private String organiName;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "type_file", nullable = false)
    private String typeFile;

    @Column(name = "project_year", nullable = false)
    private String projectYear;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "create_dt", nullable = false)
    private String createDt;

    @Column(name = "save_name", nullable = false)
     private String saveName;

    @Column(name = "file_ext", nullable = false)
     private String fileExt;


}

