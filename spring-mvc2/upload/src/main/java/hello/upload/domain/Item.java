package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    // 첨부 파일
    private UploadFile attachFile;
    // 첨부 이미지들
    private List<UploadFile> imageFiles;
}
