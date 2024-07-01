package kr.vtw.kms.viewer.service;


import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


@Service("PdfSlideService")
public class PdfSlideService {

    @Value("${pdf.path}")
    private String pdfview;


    /* variables*/
    private PDDocument srcDocument;
    private int slideCount;
    private String[] dataListSlideImgSrc = null;
    private PDFRenderer pdfRenderer;

    public String[] pdfViewer(String srcdoc) {
        String filePath = pdfview+srcdoc; // PDF 파일 경로

        int nStartNo = 1;
        int nEndNo = 200;
        String[] list =null;
        try {
            // 1. 파일 열고 슬라이드 갯수 가져오기
            int slideCount = libPdfFileOpen(filePath);
            if (slideCount < nEndNo) nEndNo = slideCount;

            // 2. 슬라이드 이미지 나누기
            list = libPdfViewSlideImage(nStartNo, nEndNo);

            // 3. 파일 닫기
            closePdfFile();

        } catch (Exception e) {
            // 예외 처리: 파일 처리 중에 오류가 발생한 경우
            e.printStackTrace(); // 적절한 예외 처리 방법을 선택하여 사용하세요.
        } finally {
            // 항상 파일을 닫도록 finally 블록에서 닫는 작업을 수행합니다.
            closePdfFile();
        }
        return list;
    }


    //1. 파일열고 슬라이드 갯수
    private int libPdfFileOpen(String filePath) {
        try {
            srcDocument = Loader.loadPDF(new File(filePath));
            PDPage pdfPage = srcDocument.getPage(0);
            PDRectangle srcPageSize = pdfPage.getMediaBox();
            float scale = 1;
            int nSlideWidth = (int) (srcPageSize.getWidth() * scale);
            int nSlideHeight = (int) (srcPageSize.getHeight() * scale);
            slideCount = srcDocument.getNumberOfPages();
            dataListSlideImgSrc = new String[slideCount];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return slideCount;
    }

    //2. 슬라이드 이미지 나누기
    private String[] libPdfViewSlideImage(int nStart, int nEnd) {

        String strDataImg = "";
        nStart = nStart - 1; // 0~19 / 20~39 ......
        int dpi = 96; // use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi

        pdfRenderer = new PDFRenderer(srcDocument);


        for (int i = nStart; i < nEnd; i++) {
              try {

                    BufferedImage srcSlideBufImg = pdfRenderer.renderImageWithDPI(i, dpi, ImageType.RGB);
                    // ================================================================================
                    // 이미지형식으로 변경
                    ByteArrayOutputStream byArrOutstream = new ByteArrayOutputStream();
                    javax.imageio.ImageIO.write(srcSlideBufImg, "png", byArrOutstream);
                    strDataImg = DatatypeConverter.printBase64Binary(byArrOutstream.toByteArray());

              } catch (Exception e) {
                  e.printStackTrace();
              }
            dataListSlideImgSrc[i] = "data:image/png;base64," + strDataImg;
          };



        return dataListSlideImgSrc;
    }

    //3. 파일 닫기
    private void closePdfFile() {
        try {
            if (srcDocument != null) {
                srcDocument.close();
            }
        } catch (IOException e) {
            // Handle the exception appropriately, show a user-friendly message or log it
            e.printStackTrace();
        }
    }

}
