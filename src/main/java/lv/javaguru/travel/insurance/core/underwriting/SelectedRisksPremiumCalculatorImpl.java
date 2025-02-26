package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@Component
public class SelectedRisksPremiumCalculatorImpl implements SelectedRisksPremiumCalculator{
    @Autowired
    private List<TravelRiskPremiumCalculator> calculators;
    @Override
    public List<TravelRisk> calculateTravelRisksList(TravelCalculatePremiumRequestV1 request) {
        List<TravelRisk> risks = new ArrayList<>();
        List<String> selectedRisks = request.getSelectedRisks();
        for (TravelRiskPremiumCalculator calculator : calculators) {
            if (selectedRisks.contains(calculator.getRiskIc())) {
                risks.add(new TravelRisk(calculator.getRiskIc(),
                        calculator.calculatePremium(request).setScale(2, RoundingMode.HALF_UP)));
            }
        }
        return risks;
    }
}
