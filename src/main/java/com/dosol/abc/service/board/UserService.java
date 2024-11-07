package com.dosol.abc.service.board;

import com.dosol.abc.domain.user.User;
import com.dosol.abc.dto.board.MyPageDTO;

public interface UserService {
    User getUserById(Long userId);
    User updateUser(User user);
    MyPageDTO getMyPage(Long userId); // MyPageDTO 반환 메서드 추가
}
