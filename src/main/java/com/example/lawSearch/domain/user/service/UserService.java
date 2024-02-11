package com.example.lawSearch.domain.user.service;

import com.example.lawSearch.domain.question.dto.response.QuestionListResponse;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionListResponse;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.domain.user.dto.request.CheckCertificationRequestDto;
import com.example.lawSearch.domain.user.dto.request.EmailCertificationRequestDto;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.dto.response.EmailCertificationResponseDto;
import com.example.lawSearch.domain.user.dto.response.MyPageResponseDto;
import com.example.lawSearch.domain.user.dto.response.UserResponseDto;
import com.example.lawSearch.domain.user.exception.CertificationNotFoundException;
import com.example.lawSearch.domain.user.exception.EmailExistException;
import com.example.lawSearch.domain.user.exception.UserNotFoundException;
import com.example.lawSearch.domain.user.exception.WrongPasswordException;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.repository.UserRepository;
import com.example.lawSearch.global.email.model.CertificationEmail;
import com.example.lawSearch.global.email.provider.EmailProvider;
import com.example.lawSearch.global.email.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final QuestionService questionService;
    private final SuggestionService suggestionService;
    private final EmailProvider emailProvider;
    private final CertificationRepository certificationRepository;

    @Transactional
    public UserResponseDto join(UserRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistException(request.getEmail());
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .age(request.getAge())
                .sex(request.isSex())
                .roles("ROLE_USER").build();

        userRepository.save(user);

        return new UserResponseDto(user.getName());
    }

    public MyPageResponseDto myPage(User user) {
        List<QuestionListResponse> questionList = questionService.findAllByUser(user);
        List<SuggestionListResponse> suggestionList = suggestionService.findAllByUser(user);

        Integer questions = (questionList == null) ? 0 : questionList.size();
        Integer suggestions = (suggestionList == null) ? 0 : suggestionList.size();

        return new MyPageResponseDto(questions, suggestions);
    }

    public boolean validationEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    @Transactional
    public void updatePassword(Long userId, String password, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException(password);
        }

        user.updatePassword(bCryptPasswordEncoder.encode(newPassword));
    }

    @Transactional
    public EmailCertificationResponseDto emailCertification(EmailCertificationRequestDto request) {
        String email = request.getEmail();

        String certificationNumber = getCertificationNumber();

        emailProvider.sendCertificationMail(email, certificationNumber);

        CertificationEmail certificationEmail = CertificationEmail.builder().email(email)
                .certificationNumber(certificationNumber).build();
        CertificationEmail certification = certificationRepository.save(certificationEmail);
        return new EmailCertificationResponseDto(certification.getId());
    }

    public Boolean checkCertification(CheckCertificationRequestDto request) {
        Long certificationId = request.getCertificationId();
        String certificationNumber = request.getCertificationNumber();

        CertificationEmail certification = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new CertificationNotFoundException(certificationId));

        if (certification.getCertificationNumber().equals(certificationNumber)) return true;
        else return false;
    }

    private static String getCertificationNumber() {
        String certificationNumber = "";

        for (int count = 0; count < 4; count++) certificationNumber += (int) (Math.random() * 10);

        return certificationNumber;
    }
}