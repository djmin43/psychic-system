package mindongjoon.parkinglot.service;

import lombok.RequiredArgsConstructor;
import mindongjoon.parkinglot.domain.Member;
import mindongjoon.parkinglot.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

}
