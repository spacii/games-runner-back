package com.api.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static com.api.resources.RecourcesConstants.CONTENT_TYPE;
import static com.api.resources.RecourcesConstants.IMAGE_CONTENT;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

    @GetMapping("/resources/{fileType}/{fileFormat}/{fileName}")
    public Mono<ResponseEntity<byte[]>> getImage(@PathVariable("fileType") String fileType, @PathVariable("fileFormat") String fileFormat, @PathVariable("fileName") String fileName) {
        try {
            return Mono.just(ResponseEntity.status(HttpStatus.OK)
                    .header(CONTENT_TYPE, IMAGE_CONTENT + fileFormat)
                    .body(IOUtils.toByteArray(getClass().getResourceAsStream("/" + fileType + "/"+fileName+"."+fileFormat))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //@ResponseBody
    //@GetMapping(value = "/resources/videos/{fileName}.mp4")
    //public FileSystemResource getVideo(@PathVariable("fileName") String fileName) {
    //    return new FileSystemResource("./src/main/resources/videos/"+fileName+".mp4");
    //}


//    @GetMapping("/resources/logos/{fileType}/{fileName}")
//    public Mono<ResponseEntity<byte[]>> getLogo(@PathVariable("fileName") String fileName, @PathVariable("fileType") String fileType) {
//        try {
//            return Mono.just(ResponseEntity.status(HttpStatus.OK)
//                    .header(CONTENT_TYPE, IMAGE_CONTENT + "png")
//                    .body(IOUtils.toByteArray(getClass().getResourceAsStream("/title/" +fileName+"."+fileType))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    @GetMapping("/resources/videos/{fileType}/{fileName}")
    public Mono<ResponseEntity<byte[]>> getVideo(@RequestHeader(value = "Range", required = false) String httpRangeList,
                                                    @PathVariable("fileType") String fileType,
                                                    @PathVariable("fileName") String fileName) {
        return Mono.just(resourcesService.prepareContent(fileName, fileType, httpRangeList));
    }
}
