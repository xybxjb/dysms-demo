package cn.slipbend.controller;

import cn.slipbend.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date:
 * @Description:图片上传功能
 */
@RestController
@CrossOrigin
@RequestMapping("/upload")
@Api(value = "App接口", description = "图片上传的接口")
public class UploadController {
    @Value("${IMAGES_PATH}")
    private String webRealPath;
    @Value("${LOCAL_IMAGES_PATH}")
    private String localRealPath;

    @RequestMapping("/uploadImage")
    @ApiOperation(value = "图片上传", httpMethod = "POST",notes = "图片上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header",name="token",dataType="String",required=true,value="token"),
            @ApiImplicitParam(paramType = "form",name = "file",value = "图片",required = true,dataType = "__File"),
    })
    public Object uploadImage(MultipartFile file){
        return UploadUtil.upload(file,webRealPath);//部署在服务器改为webRealPath
    }
}


/*
@RestController
@PropertySource(value = {"classpath:const.properties"})
@RequestMapping("/upload")
@Api(value = "App接口", description = "头像上传的接口")
public class UploadController {
    @Value("${IMAGES_PATH}")
    private String webRealPath;
    @Value("${LOCAL_IMAGES_PATH}")
    private String localRealPath;
    @Autowired
    private UploadService uploadService;

    @RequestMapping("/uploadImage")
    @ApiOperation(value = "上传头像", httpMethod = "POST",notes = "上传头像")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "form",name = "file",value = "用户头像",required = true,dataType = "__file"),
    })
    public Object uploadImage(MultipartFile file, String id){
        Map<String, Object> map =new HashMap<>();
        System.out.println(file.getOriginalFilename());
        //获取上传的图片名
        String fileName = file.getOriginalFilename();
        //生成新名称
        String newFileName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
        //上传到服务器
        File dest = new File(localRealPath+newFileName);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        //保存文件
        try {
            file.transferTo(dest);
            //存入数据库
            uploadService.updateImageById(id,newFileName);
            map.put("msg","上传成功");
            map.put("fileName",newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg","上传失败");
        }
        return map;
    }
}
*/
