package com.sda.group11.onlinestore.controller;

//@Controller
//public class SecurityController {
//
//    @Autowired
//    BCryptPasswordEncoder bCryptEncoder;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @GetMapping("/register")
//    public String register(Model model){
//        User user=new User();
//        model.addAttribute("userAccount", user);
//        return "security/register";
//    }
//
//    @PostMapping("/register/save")
//    public String saveUser(Model model,User user){
//        user.setPassword(bCryptEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "redirect:/product";
//    }
//}
