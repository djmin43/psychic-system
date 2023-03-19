package mindongjoon.parkinglot.controller;

import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

}
