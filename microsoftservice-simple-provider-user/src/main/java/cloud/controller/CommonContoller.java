package cloud.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/common")
public class CommonContoller {
    //缓冲区大小
    @Value("${fileupload.bufferSize}")
    private int bufferSize;
    //文件上传后保存位置
    @Value("${fileupload.fileDir}")
    private String uploadFileDir;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Map<Object, Object> uploadFile(HttpServletRequest request) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        StandardMultipartHttpServletRequest servletRequest = (StandardMultipartHttpServletRequest) request;
        Iterator<String> iterator = servletRequest.getFileNames();
        while (iterator.hasNext()){
            MultipartFile file = servletRequest.getFile(iterator.next());
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
        }

        /*List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("files");
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            File saveFileDir = new File(uploadFileDir);
            if(!saveFileDir.exists()){
                saveFileDir.mkdirs();
            }
            try {
                //获取输入缓冲流
                br = new BufferedReader( new InputStreamReader(file.getInputStream(),"UTF-8"));
                //构建输出缓冲流
                bw = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(
                                new File(uploadFileDir.concat(File.separator).concat(fileName)))));
                String str = null;
                while ((str = br.readLine()) != null){
                    bw.write(str);
                    bw.flush();
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(null != bw){
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(null != br){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/
        return null;
    }

    @RequestMapping(value = "/download")
    public Map<Object, Object> download(@RequestParam(name = "fileName") String fileName,
                                        HttpServletResponse response){
        BufferedWriter bw = null;
        BufferedReader br = null;
        File file = new File(uploadFileDir.concat(File.separator).concat(fileName));
        if(!file.exists() || !file.isFile()){
            return null;
        }
        response.setHeader("content-type", "application/octet-stream;filename=" + fileName);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            //构建文件输入流
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            //构建文件输出流
            bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
            String str = null;
            while ((str = br.readLine()) != null){
                bw.write(str);
                bw.flush();
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != bw){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
