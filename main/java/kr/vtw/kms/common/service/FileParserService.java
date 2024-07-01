package kr.vtw.kms.common.service;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor;
import kr.vtw.kms.common.dto.ParserDto;
import kr.vtw.kms.common.dto.SourceDto;
import kr.vtw.kms.common.mapper.FileMapper;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("FileParserService")
public class FileParserService {

    @Value("${pdf.path}")
    private String ppttopdfPath;


    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    public FileMapper mapper;

    public int getPdfSource(int filecd){
        ParserDto parserDto = mapper.getTempName(filecd);
        Path filePath = null;
        String pdfText = null;

        if(parserDto.getFileExt().equals("pptx"))filePath = Paths.get(ppttopdfPath, parserDto.getTmpName());
        else filePath = Paths.get(uploadPath, parserDto.getTmpName());

        try {
            File file = filePath.toFile();
       		PDDocument document = null;

       		try {
       			document = Loader.loadPDF(file);
       		} catch (IOException e) {
       			e.printStackTrace();
       		}

       		PDFTextStripper stripper = new PDFTextStripper();
            pdfText = stripper.getText(document);

       		document.close();
            String match = "[^\uAC00-\uD7A30-9a-zA-Z]";

           	pdfText = pdfText.replaceAll(match, " ");

            SourceDto sourceDto = new SourceDto();
            sourceDto.setFilecd(filecd);
            sourceDto.setText(pdfText);
            int insert = mapper.insertSource(sourceDto);
            int update =0;
            if(insert>0){
                update = mapper.updateFile(filecd);
            }

            return update;
       	} catch (Exception e) {
            e.printStackTrace();
       	}

        return 0;
    }


    public int getHwpSource(int filecd){
        ParserDto parserDto = mapper.getTempName(filecd);
        Path filePath = Paths.get(uploadPath, parserDto.getTmpName());
        String hwpText = null;

        try{
       		HWPFile hwpFile = HWPReader.fromFile(filePath.toString());
       		if (hwpFile != null) {
                hwpText = TextExtractor.extract(hwpFile, TextExtractMethod.InsertControlTextBetweenParagraphText);

       		}
            SourceDto sourceDto = new SourceDto();
            sourceDto.setFilecd(filecd);
            sourceDto.setText(hwpText);
            int insert = mapper.insertSource(sourceDto);
            int result = mapper.updateFile(filecd);


            return result;
       	}catch(Exception e){
            e.printStackTrace();
       	}

        return 0;
    }


}
