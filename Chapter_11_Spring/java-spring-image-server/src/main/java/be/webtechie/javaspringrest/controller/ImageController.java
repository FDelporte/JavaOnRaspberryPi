package be.webtechie.javaspringrest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST calls to access the images on this device.
 */
@RestController
public class ImageController {

    /**
     * Get the value from application.properties where we define the location of the images.
     */
    @Value("${path.images}")
    private String pathImages;

    /**
     * Get a list of all the files.
     * @return An HTML string with a list of all the files.
     */
    @GetMapping("/files")
    public String getFiles() {
        StringBuilder rt = new StringBuilder();

        try {
            rt.append("<html>")
                .append("   <body>")
                .append("       <table>")
                .append("           <tr>")
                .append("               <th>File</th>")
                .append("               <th>Size</th>")
                .append("               <th>Date</th>")
                .append("           <tr>");

            File[] childFiles = (new File(this.pathImages)).listFiles();
            for (File childFile : childFiles) {
                String relativePath = childFile.getName();
                rt.append("           <tr>")
                    .append("           <td>")
                        .append("<a href='file/")
                        .append(URLEncoder.encode(relativePath, "UTF-8"))
                        .append("' target='_blank'>")
                        .append(relativePath)
                        .append("</a></td>")
                    .append("           <td style='text-align: right;'>")
                        .append(getSize(childFile)).append("</td>")
                    .append("           <td>")
                        .append(getTimestamp(childFile)).append("</td>")
                    .append("       </tr>");
            }

            rt.append("     <table>");
            rt.append(" </body>");
            rt.append("</html>");

        } catch (Exception ex) {
            throw new RuntimeException("Error accessing requested file/directory: " + ex.getMessage());
        }

        return rt.toString();
    }

    /**
     * Converts the file size in bytes to Kb.
     * 
     * @param file
     * @return
     */
    private String getSize(File file) {
        long sizeInBytes = file.length();
        long sizeInKilobytes = sizeInBytes / 1024;
        return sizeInKilobytes + "Kb";
    }

    /**
     * Converts the last modified timestamp of the file to a readable format.
     * 
     * @param file
     * @return
     */
    private String getTimestamp(File file) {
        long timestamp = file.lastModified();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * Get the request file.
     * @param fileName The filename
     * @return The file as byte array
     */
    @GetMapping(value = "/file/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable("filename") String fileName) {
        // Initiate the headers we will use in the return
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        // Get the file
        File file = new File(this.pathImages, fileName);

        // Check if the file exists.
        if (!file.exists()) {
            // Immediately return error 404.
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // Get the file as a byte array.
        byte[] media = null;
        try (InputStream in = new FileInputStream(file)) {
            media = in.readAllBytes();
        } catch (IOException ex) {
            // Oops something went wrong, return error 500.
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity(ex.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Check which type of file we are returning so we can correctly define
        // the header content type. By doing this, the browser can show 
        // the image inside the browser, otherwise it will do a download.
        if (fileName.toLowerCase().endsWith(".jpg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        } else if (fileName.toLowerCase().endsWith(".png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            headers.setContentType(MediaType.IMAGE_GIF);
        }

        // Everything OK, return the image.
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
}