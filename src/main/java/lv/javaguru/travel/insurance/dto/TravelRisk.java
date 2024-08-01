package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lv.javaguru.travel.insurance.dto.util.PremiumBigDecimalSerializer;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelRisk {
    String riskIc;

    @JsonSerialize(using = PremiumBigDecimalSerializer.class)
    BigDecimal premium;
}
