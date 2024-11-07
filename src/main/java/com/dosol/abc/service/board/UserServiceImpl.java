package com.dosol.abc.service.board;

import com.dosol.abc.domain.board.Board;
import com.dosol.abc.domain.user.User;
import com.dosol.abc.dto.board.BoardDTO;
import com.dosol.abc.dto.board.MyPageDTO;
import com.dosol.abc.repository.board.BoardRepository;
import com.dosol.abc.repository.board.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, BoardRepository boardRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // MyPageDTO를 반환하는 getMyPage 메서드 구현
    @Override
    public MyPageDTO getMyPage(Long userId) {
        // User 정보 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // User의 게시물(Board) 가져오기
        List<BoardDTO> boardDTOs = boardRepository.findAll().stream()
                .filter(board -> board.getUser().getUserId().equals(userId)) // 특정 유저의 게시물 필터링
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        // MyPageDTO 생성하여 반환
        return MyPageDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .profileImage("profile_image_url") // 실제 프로필 이미지 경로 설정
                .boards(boardDTOs)
                .build();
    }
}
