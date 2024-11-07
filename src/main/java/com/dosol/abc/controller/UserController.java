package com.dosol.abc.controller;

import com.dosol.abc.domain.user.User;
import com.dosol.abc.dto.board.MyPageDTO;
import com.dosol.abc.service.board.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 마이페이지 정보 조회
    @GetMapping("/mypage")
    public ResponseEntity<MyPageDTO> getMyPage(@RequestParam Long userId) {
        MyPageDTO myPageDTO = userService.getMyPage(userId); // UserService의 getMyPage 호출
        return ResponseEntity.ok(myPageDTO);
    }

    // 마이페이지 정보 업데이트
    @PutMapping("/mypage")
    public ResponseEntity<User> updateMyPage(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}
