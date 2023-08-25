package com.example.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@RestController
@Controller
public class DemoController {

//	@RequestMapping("/")
//	String home() {
//		return "Hello Spring Boot";
//	}
	
	/**
	 * demo
	 */
//	@RequestMapping("/")
//	public String index() {
//		return "index";
//	}
	
	/**
	 * demo2
	 */
//	@RequestMapping("/{num}")
//	public String index(@PathVariable int num, Model model) {
//		int sum = 0;
//		for(int i = 1 ; i <= num ; i++) {
//			sum += i;
//		}
//		model.addAttribute("msg", "sum=" + sum);
//		return "index2";
//	}
	
	/**
	 * demo3
	 */
//	@RequestMapping("/{num}")
//    public ModelAndView index(@PathVariable int num, ModelAndView mav) {
//        int sum = 0;
//        for(int i = 1 ; i <= num ; i++) {
//            sum += i;
//        }
//        mav.addObject("msg", "sum=" + sum);
//        mav.setViewName("index3");
//        return mav;
//    }
	
	/**
	 * demo4
	 */
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public ModelAndView index(ModelAndView mav) {
//		mav.setViewName("index4");
//		mav.addObject("msg", "お名前を入力してください");
//		return mav;
//	}
//
//	@RequestMapping(value="/", method=RequestMethod.POST)
//	public ModelAndView send(
//			@RequestParam("text1")String str,
//			ModelAndView mav) {
//		mav.setViewName("index4");
//		mav.addObject("msg", "こんにちは" + str + "さん！");
//		mav.addObject("value", str);
//		return mav;
//	}
	
	/**
	 * demo5
	 */
    @GetMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index5");
        mav.addObject("msg", "お名前を入力してください");
        return mav;
    }

    @PostMapping("/")
    public ModelAndView send(
            @RequestParam("text1")String str,
            ModelAndView mav) {
        mav.setViewName("index5");
        mav.addObject("msg", "こんにちは" + str + "さん！");
        mav.addObject("value", str);
        return mav;
    }
}
