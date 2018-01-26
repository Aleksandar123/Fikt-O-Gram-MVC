package instagram.controllers.MVCControllers;

import instagram.models.Post;
import instagram.models.User;
import instagram.other.PictureTools;
import instagram.services.NotificationService;
import instagram.services.PostService;
import instagram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;

/**
 * Created by aleksandar on 23.8.16.
 */
@Controller
public class NewPostController {


    @Autowired
    private NotificationService notifyService;
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    private PictureTools pt = new PictureTools();

    //public static final String ROOT = "/home/aleksandar/Development/Diplomska-2-0/target/classes/public/";
    //public static final String ROOT = "${fiktogram.root_location}";
   // @Value("${fiktogram.root_location}")
    //private String ROOT;
    
    public static final String ROOT = "D:\\Diplomska-20170710T145751Z-001\\Diplomska\\Diplomska-2-0\\target\\classes\\public\\";
    


    private final ResourceLoader resourceLoader;

    @Autowired
    public NewPostController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(method = RequestMethod.GET, value = "posts/new")
    public String provideUploadInfo(Model model) throws IOException {

		/*model.addAttribute("files", Files.walk(Paths.get(ROOT))
				.filter(path -> !path.equals(Paths.get(ROOT)))
				.map(path -> Paths.get(ROOT).relativize(path))
				.map(path -> linkTo(methodOn(FileUploadController.class).getFile(path.toString())).withRel(path.toString()))
				.collect(Collectors.toList()));
*/
        return "posts/new";
    }

    @RequestMapping(method = RequestMethod.GET, value = "posts/new/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get("userImages", filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "posts/new")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("description") String post, Principal principal) {

        if(file.getSize() >4194304L ){
            notifyService.addErrorMessage("File to large. Images must be below 4mb.");
        }
        if (!file.isEmpty()) {

        Post newPost = new Post();
        newPost.setId(new Date().getTime() + 1L);
        User user = userService.findByUsername(principal.getName());
        String filename =  new Date().getTime() + ""  + "." + file.getOriginalFilename().split("\\.")[1];
        //System.out.println(ROOT + filename);
            //####
        File dir = new File(ROOT + File.separator + "userImages");
        if (!dir.exists())
            dir.mkdirs();
        String s = new Date().getTime() + "_" + file.getOriginalFilename();
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + s);



        /*if (!file.isEmpty()) {*/
                try {
                    File file1 = pt.convert(file);

                    if(pt.getFormat(file1.getAbsolutePath()).equals("not-allowed")){
                        notifyService.addErrorMessage("This type of file is not allowed!");
                        return "redirect:/";

                    }
                    System.out.println(file.getSize());
                    Files.copy(file.getInputStream(), Paths.get(serverFile.getAbsolutePath()));
                    newPost.setUploader(user);
                    newPost.setDescription(post);
                    //newPost.setImgLocation( File.separator + "userImages" + File.separator + s);//File.separator + filename);
                    newPost.setImgLocation( "/userImages/" + s);  //File.separator + filename);
                    System.out.println(File.separator + "userImages" + File.separator + s);



                    newPost.setImage(pt.cropImage(file1.getAbsoluteFile()));
                    postService.create(newPost);
                    System.out.println(newPost.toString());
                    notifyService.addInfoMessage("Successfuly uploaded");
                } catch (IOException|RuntimeException e) {
                    System.out.println(e.toString());
                    notifyService.addErrorMessage("IO Exception");
                }
        } else {
                notifyService.addErrorMessage("Error while uploading");
            }


        return "redirect:/";
    }

}
