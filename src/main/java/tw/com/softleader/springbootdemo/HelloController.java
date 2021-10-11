package tw.com.softleader.springbootdemo;

import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(name = "/parm", method = {RequestMethod.GET, RequestMethod.POST})
    public HelloObj helloObj(@RequestParam String name, @RequestParam Integer number){
        return new HelloObj(name, number);
    }
}
