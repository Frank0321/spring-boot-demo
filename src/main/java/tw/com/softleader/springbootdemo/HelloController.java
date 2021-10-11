package tw.com.softleader.springbootdemo;

import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class HelloController {

    @GetMapping
    public String hello(){
        return "hello";
    }

    @GetMapping("ex")
    public String throwEx(){
        throw new RuntimeException("=====throwEx=====");
    }

    @GetMapping(value = "/obj", produces = "application/xml")
    public HelloObj helloObj() {
        return new HelloObj("world", 2021);
    }

    @RequestMapping(value = "/param", method = {GET, POST})
    public HelloObj helloObj(@RequestParam String name, @RequestParam Integer number){
        return new HelloObj(name, number);
    }
}
