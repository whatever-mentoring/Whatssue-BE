package gdg.whatssue.service;

import gdg.whatssue.entity.Claim;
import gdg.whatssue.entity.Club;
import gdg.whatssue.repository.ClaimRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.service.dto.AccountClaimDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final ClaimRepository claimRepository;
    private final ClubRepository clubRepository;
     public ResponseEntity<?> createClaim(AccountClaimDto dto) throws Exception {
         Long clubId = 1L; // 1로 가정
         Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                 new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
         ));
            Claim claim = Claim.builder()
                    .club(club)
                    .isClosed(false)
                    .claimDate(dto.getClaimDate())
                    .claimAmount(new BigDecimal(dto.getClaimAmount()))
                    .claimName(dto.getClaimName())
                    .build();
         claimRepository.save(claim);
         return ResponseEntity.ok("정산 청구 완료");
     }

     public ResponseEntity<?> getClaimList() throws Exception {
         Long clubId = 1L; // 1로 가정
         Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                 new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
         ));
         return ResponseEntity.ok(claimRepository.findAllByClub_ClubId(club));
     }

}
